package com.service.mm.mailbox.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.gov.minameddelanden.service.Service;
import se.gov.minameddelanden.service.ServicePortV3;

import javax.xml.ws.BindingProvider;

@Configuration
public class MetroClientConfiguration {

    @Bean(name = "clientv3")
    public Object generateProxyV3() {
        Service service = new Service();

        ServicePortV3 port = service.getServicePortV3();
        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/metro/services/mailbox/v3");

        return port;
    }


}