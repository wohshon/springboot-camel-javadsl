package com.redhat;

import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;

import com.redhat.entities.Order;
import com.redhat.entities.OrderItem;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.ThreadPoolProfile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
@Component
public class MyRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        /** */
        ThreadPoolProfile poolProfile = new ThreadPoolProfile("masterPoolProfile");
        poolProfile.setMaxPoolSize(100);
        poolProfile.setMaxQueueSize(1000);
        poolProfile.setPoolSize(40);
        poolProfile.setKeepAliveTime(1L);
        poolProfile.setTimeUnit(TimeUnit.MINUTES);
        getContext().getExecutorServiceManager().setDefaultThreadPoolProfile(poolProfile);
        
        restConfiguration().component("servlet");
        JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
        JAXBContext orderItemjaxbContext = JAXBContext.newInstance(OrderItem.class);
        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(jaxbContext);
        JaxbDataFormat orderItemjaxbDataFormat = new JaxbDataFormat(orderItemjaxbContext);
        jaxbDataFormat.setSchema("classpath:order.xsd");


        rest("/rest")
            .post("/order/{id}")
            //.consumes(MediaType.APPLICATION_JSON_VALUE)
            .consumes(MediaType.APPLICATION_XML_VALUE)
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .to("direct:main");
            
        from("direct:main")
        .multicast().parallelProcessing()
        .to("direct:spiltOrderItems","direct:order");

        from("direct:order")
            //.log("${body}")
            //.log("${header.id}")
            .unmarshal(jaxbDataFormat)
            //.log("....after unmarshalling: ${body}")
            //.log("....ID: ${body.getId}")
            //after unmarshalling, save to db
            .bean(MyTransformer.class,"convertJson");

        from("direct:spiltOrderItems")
            .split().tokenizeXML("orderItems")
            .streaming()
            .threads(20)
            .to("direct:saveOrderItems");

        from("direct:saveOrderItems")
            //.log("----------------------------Item-----------------------")
            //.log("${body}")
            .unmarshal(orderItemjaxbDataFormat)
            .transacted()
            //.log("after......${body}")
           .to("jpa:com.redhat.entities.OrderItem?usePersist=true");

     }


    
}
