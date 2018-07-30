package com.service.mm.mailbox.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.gov.minameddelanden.service.ServicePortV3;

@Configuration
public class CxfClientConfiguration {

    @Bean(name = "mailboxv3")
    public Object generateProxyMailboxV3() {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setServiceClass(ServicePortV3.class);
        proxyFactory.setAddress("http://localhost:8080/cxf/services/mailbox/v3");

        return proxyFactory.create();
    }

    @Bean(name = "recipentv3")
    public Object generateProxyRecipientV3() {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setServiceClass(ServicePortV3.class);
        proxyFactory.setAddress("http://localhost:8080/cxf/services/recipient/v3");

        return proxyFactory.create();
    }

}
