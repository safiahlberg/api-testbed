package com.service.mm;

import com.service.mm.mailbox.ServiceConfiguration;
import com.sun.xml.ws.transport.http.servlet.WSServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.register(ServiceConfiguration.class);

        container.addListener(new ContextLoaderListener(context));
        // container.addListener(new WSServletContextListener());

        ServletRegistration.Dynamic dispatcher
                = container.addServlet("dispatcher", new WSServlet());

        dispatcher.addMapping("/services");
    }

}
