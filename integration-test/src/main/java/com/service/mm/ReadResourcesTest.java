package com.service.mm;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.testng.Assert;
import org.testng.annotations.Test;
import se.gov.minameddelanden.externalschema.invoice.Invoice;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReadResourcesTest {

    @Test
    public void readResources() throws URISyntaxException, IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:invoice*.xml");

        Assert.assertNotNull(resources);
        Assert.assertNotEquals(resources.length, 0);
    }

    @Test
    public void readAndConvertResources() throws JAXBException, IOException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Invoice.class);

        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:invoice*.xml");

        Collection<Invoice> invoices = Arrays.asList(Objects.requireNonNull(resources
        )).stream().map(resource -> {
            try {
                final Invoice invoice = (Invoice) jaxbUnmarshaller.unmarshal(resource.getInputStream());
                System.out.println(String.format("Unmarshalled file \"%s\"", resource.getFilename()));

                return invoice;
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        Assert.assertNotNull(invoices);
        Assert.assertNotEquals(invoices.size(), 0);

    }
}
