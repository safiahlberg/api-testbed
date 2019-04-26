package com.service.mm.far.client;

import com.service.mm.client.ClientConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import se.gov.minameddelanden.recipient.ApplicationFaultV3;
import se.gov.minameddelanden.recipient.RecipientPortV3;
import se.gov.minameddelanden.schema.recipient.v3.IsReachable;
import se.gov.minameddelanden.schema.recipient.v3.IsReachableResponse;

public class RecipientImplTest {

    private ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfiguration.class);
    private RecipientPortV3 proxyV3 = (RecipientPortV3) context.getBean("recipientv3");

    @BeforeMethod
    public void setUp() {
        Assert.assertNotNull(proxyV3);
    }

    @Test
    public void isReachable() throws ApplicationFaultV3 {

        IsReachable request = new IsReachable();
        request.setSenderOrgNr("senderOrgNr");
        request.getRecipientIds().add("recipientId");
        IsReachableResponse result = proxyV3.isReachable(request );

        Assert.assertNotNull(result);
    }
}
