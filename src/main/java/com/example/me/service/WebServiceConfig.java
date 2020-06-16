package com.example.me.service;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import static com.example.me.constants.Constants.*;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, SOAP_URL_MAPPING);
    }

    @Bean
    public XsdSchema bundleSchema() {
        return new SimpleXsdSchema(new ClassPathResource(SCHEMA_FILE_NAME));
    }

    @Bean(name=WSDL_BEAN_NAME)
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema bundleSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(bundleSchema);
        definition.setLocationUri(LOCATION_URI);
        definition.setPortTypeName(BUNDLE_SERVICE_PORT);
        definition.setTargetNamespace(SOAP_NAMESPACE);
        return definition;
    }
}