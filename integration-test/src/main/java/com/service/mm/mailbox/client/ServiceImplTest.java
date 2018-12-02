package com.service.mm.mailbox.client;

import com.service.mm.client.ClientConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.gov.minameddelanden.schema.message.v3.SealedDelivery;
import se.gov.minameddelanden.schema.service.v3.DeliverSecure;
import se.gov.minameddelanden.schema.service.v3.DeliverSecureResponse;
import se.gov.minameddelanden.service.ApplicationFaultV3;
import se.gov.minameddelanden.service.ServicePortV3;

public class ServiceImplTest {

    private ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfiguration.class);
    private ServicePortV3 proxyV3 = (ServicePortV3) context.getBean("mailboxv3");

    @BeforeMethod
    public void setUp() {
        Assert.assertNotNull(proxyV3);
    }

    @Test
    public void deliverSecure() throws ApplicationFaultV3 {
        DeliverSecure request = new DeliverSecure();
        SealedDelivery sealedDeliveryV3 = new SealedDelivery();
        request.setDeliverSecure(sealedDeliveryV3);

        DeliverSecureResponse result = proxyV3.deliverSecure(request);

        Assert.assertNotNull(result);
    }
}