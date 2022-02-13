package com.service.mm.mailbox.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.gov.minameddelanden.recipient.Recipient;
import se.gov.minameddelanden.recipient.RecipientPortV3;
import se.gov.minameddelanden.service.Service;
import se.gov.minameddelanden.service.ServicePortV3;

@Configuration
public class MetroClientConfiguration {

    @Bean(name = "mailboxv3")
    public Object generateProxyMailboxV3() {
        Service service = new Service();

        ServicePortV3 port = service.getServicePortV3();
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/metro/services/mailbox/v3");

        return port;
    }

    @Bean(name = "recipientv3")
    public Object generateProxyRecipientV3() {
        Recipient recipient = new Recipient();

        RecipientPortV3 port = recipient.getRecipientPortV3();
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/metro/services/recipient/v3");

        return port;
    }

}