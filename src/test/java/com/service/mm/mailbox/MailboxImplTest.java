package com.testcorp.mm.mailbox;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.gov.minameddelanden.schema.message.v3.SealedDelivery;
import se.gov.minameddelanden.schema.service.DeliveryResult;
import se.gov.minameddelanden.service.ApplicationFaultV3;
import se.gov.minameddelanden.service.ServicePortV3;

public class MailboxImplTest {

    private ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfiguration.class);
    private ServicePortV3 proxyV3 = (ServicePortV3) context.getBean("clientv3");

    @BeforeMethod
    public void setUp() {
        Assert.assertNotNull(proxyV3);
    }

    @Test
    public void deliverSecure() throws ApplicationFaultV3 {
        SealedDelivery sealedDeliveryV3 = new SealedDelivery();

        DeliveryResult result = proxyV3.deliverSecure(sealedDeliveryV3);

        Assert.assertNotNull(result);
    }
}