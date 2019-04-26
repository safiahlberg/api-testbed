package com.service.mm.mailbox.client;

import com.service.mm.client.ClientConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.gov.minameddelanden.externalschema.invoice.Invoice;
import se.gov.minameddelanden.schema.message.v3.OfficialMatter;
import se.gov.minameddelanden.schema.service.v3.DeliverSecure;
import se.gov.minameddelanden.schema.service.v3.DeliverSecureResponse;
import se.gov.minameddelanden.service.ApplicationFaultV3;
import se.gov.minameddelanden.service.ServicePortV3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServiceImplClientTest {
    private static Logger logger = LogManager.getLogger(ServiceImplClientTest.class);

    private ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfiguration.class);
    private ServicePortV3 proxyV3 = (ServicePortV3) context.getBean("mailboxv3");

    @BeforeMethod
    public void setUp() {
        Assert.assertNotNull(proxyV3);
    }

    @Test
    public void deliverSecureWithOfficialMatter() throws ApplicationFaultV3, URISyntaxException, JAXBException, IOException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Invoice.class);

        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:invoice*.xml");

        Collection<Invoice> invoices = Arrays.asList(Objects.requireNonNull(resources
                )).stream().map(resource -> {
            try {
                final Invoice invoice = (Invoice) jaxbUnmarshaller.unmarshal(resource.getInputStream());
                logger.info(() -> String.format("Unmarshalled file \"%s\"", resource.getFilename()));

                return invoice;
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        Assert.assertNotNull(invoices);
        Assert.assertNotEquals(invoices.size(), 0);

        OfficialMatter officialMatter = new OfficialMatter();
        officialMatter.getAny().addAll(invoices);

        DeliverSecure request = new DeliverSecureBuilderImpl()
                .setOfficialMatter(officialMatter)
                .build();

        DeliverSecureResponse result = proxyV3.deliverSecure(request);

        Assert.assertNotNull(result);
    }

}