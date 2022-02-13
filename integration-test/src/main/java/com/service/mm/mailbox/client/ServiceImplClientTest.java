package com.service.mm.mailbox.client;

import com.service.mm.client.ClientConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import se.gov.minameddelanden.schema.message.v3.OfficialMatter;
import se.gov.minameddelanden.schema.service.DeliveryResult;
import se.gov.minameddelanden.schema.service.v3.DeliverSecure;
import se.gov.minameddelanden.service.ApplicationFaultV3;
import se.gov.minameddelanden.service.ServicePortV3;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServiceImplClientTest {

    private final ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfiguration.class);
    private final ServicePortV3 proxyV3 = (ServicePortV3) context.getBean("mailboxv3");

    @BeforeMethod
    public void setUp() {
        Assert.assertNotNull(proxyV3);
    }

    @Test
    public void deliverSecureWithOfficialMatter() throws ApplicationFaultV3, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();


        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:invoice*.xml");

        Collection<Element> invoices = readAndConvertInvoiceFiles(dBuilder, resources);

        Assert.assertNotNull(invoices);
        Assert.assertNotEquals(invoices.size(), 0);

        OfficialMatter officialMatter = new OfficialMatter();
        officialMatter.getAny().addAll(invoices);

        DeliverSecure request = new DeliverSecureBuilderImpl()
                .setOfficialMatter(officialMatter)
                .build();

        DeliveryResult result = proxyV3.deliverSecure(request.getDeliverSecure());

        Assert.assertNotNull(result);
    }

    private List<Element> readAndConvertInvoiceFiles(DocumentBuilder dBuilder, Resource[] resources) {
        return Arrays.stream(Objects.requireNonNull(resources
                )).map(resource -> {
            try {
                Document doc = dBuilder.parse(resource.getInputStream());

                return doc.getDocumentElement();
            } catch (IOException | SAXException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

}