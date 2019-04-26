package com.service.mm.mailbox.client;

import com.service.mm.client.ClientConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.gov.minameddelanden.externalschema.invoice.Invoice;
import se.gov.minameddelanden.schema.message.v3.MessageHeader;
import se.gov.minameddelanden.schema.message.v3.OfficialMatter;
import se.gov.minameddelanden.schema.message.v3.SealedDelivery;
import se.gov.minameddelanden.schema.service.v3.DeliverSecure;
import se.gov.minameddelanden.schema.service.v3.DeliverSecureResponse;
import se.gov.minameddelanden.service.ApplicationFaultV3;
import se.gov.minameddelanden.service.ServicePortV3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServiceImplClientTest {

    private ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfiguration.class);
    private ServicePortV3 proxyV3 = (ServicePortV3) context.getBean("mailboxv3");

    @BeforeMethod
    public void setUp() {
        Assert.assertNotNull(proxyV3);
    }

    @Test
    public void deliverSecure() throws ApplicationFaultV3, URISyntaxException, JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Invoice.class);

        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Collection<Invoice> invoices = Arrays.asList(Objects.requireNonNull(Paths.get(
                getClass().getClassLoader()
                        .getResource(".").toURI())
                .toFile().listFiles((dir, name) -> name.endsWith(".xml")
                ))).stream().map(file -> {
            try {
                return (Invoice) jaxbUnmarshaller.unmarshal(file);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        OfficialMatter officialMatter = new OfficialMatter();
        officialMatter.getAny().addAll(invoices);

        DeliverSecure request = new DeliverSecureBuilderImpl()
                .setOfficialMatter(officialMatter)
                .build();

        DeliverSecureResponse result = proxyV3.deliverSecure(request);

        Assert.assertNotNull(result);
    }

}