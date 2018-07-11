package com.testcorp.mm.mailbox;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.gov.minameddelanden.service.ServicePortV3;

@Configuration
public class ClientConfiguration {

    @Bean(name = "clientv3")
    public Object generateProxy() {
        return proxyFactoryBeanV3().create();
    }

    @Bean
    public JaxWsProxyFactoryBean proxyFactoryBeanV3() {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setServiceClass(ServicePortV3.class);
        proxyFactory.setAddress("http://localhost:8080/services/mailbox/v3");

        return proxyFactory;
    }
}
