/*
 * Copyright 2016 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */
package com.redhat;

import java.io.FileOutputStream;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.redhat.entities.Address;
import com.redhat.entities.Customer;
import com.redhat.entities.Delivery;
import com.redhat.entities.Order;
import com.redhat.entities.OrderItem;
import com.redhat.entities.Payment;
import com.redhat.entities.Product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.redhat")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
            generateXML();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void generateXML() throws Exception{
        JAXBContext contextObj = JAXBContext.newInstance(Order.class);  
        Marshaller marshallerObj = contextObj.createMarshaller();  
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
        Order order = new Order();
        order.setId("");
        order.setOrderId("ord00001");
        Customer cust = new Customer();
        cust.setAge(25);
        cust.setLastName("Whitehurst");
        cust.setFirstName("Jim");
        cust.setEmail("jw@gmail.com");
        cust.setIsVIP(Boolean.TRUE);
        Address addr= new Address();
        addr.setAddress1("Singapore expo lane 1, Changi");
        addr.setAddress2("ABC Building Old ABC building near carpark");
        addr.setCountry("Singapore");
        addr.setPostalCode("123456");
        order.setCustomer(cust);
        cust.setAddress(addr);

        Product product = new Product();
        product.setProductId("p111111");
        product.setDescription("The Apple M1 chip is here. Our first chip designed specifically for Mac");
        product.setShortDesc("Until now, a Mac needed multiple chips to deliver all of its features");
        product.setImg("/images/product.png");
        product.setName("apple watch");
        product.setPrice(1000.0);
        OrderItem orderItem1 = new OrderItem();
        //orderItem1.setId("");
        orderItem1.setOrderItemId("item00001");
        orderItem1.setProduct(product);
        orderItem1.setQty(500);
        order.getOrderItems().add(orderItem1);

        product = new Product();
        product.setProductId("p22222");
        //product.setId("");
        product.setDescription("The Orange M1 chip is here. Our first chip designed specifically for Mac");
        product.setShortDesc("Until now, a Mac needed multiple chips to deliver all of its features");
        product.setImg("/images/product.png");
        product.setName("IPhone 12");
        product.setPrice(1000.0);
        orderItem1 = new OrderItem();
        //orderItem1.setId("");
        orderItem1.setOrderItemId("item00002");
        orderItem1.setProduct(product);
        orderItem1.setQty(250);
        order.getOrderItems().add(orderItem1);

        product = new Product();
        product.setProductId("p33333");
        //product.setId("");
        product.setDescription("The PineApple M1 chip is here. Our first chip designed specifically for Mac");
        product.setShortDesc("Until now, a Mac needed multiple chips to deliver all of its features");
        product.setImg("/images/product.png");
        product.setName("MacBook Pro");
        product.setPrice(1000.0);
        orderItem1 = new OrderItem();
        //orderItem1.setId("");
        orderItem1.setOrderItemId("item00003");
        orderItem1.setProduct(product);
        orderItem1.setQty(2000);
        order.getOrderItems().add(orderItem1);

        Payment payment = new Payment();
        payment.setBillingAddress(addr);
        payment.setPaymentType("Card");
        payment.setRemarks("Super long remarks just say something useless here, testing 123 This is a very long remarks which does nothing but to take up some space so that the pay load can be bigger than it was");
        Delivery delivery = new Delivery();
        delivery.setDeliveryAddress(addr);
        delivery.setDeliveryDate(new Date());
        delivery.setRemarks("Call before Delivery This is a very long remarks which does nothing but to take up some space so that the pay load can be bigger than it was");
        order.setDelivery(delivery);
        order.setPayment(payment);
        marshallerObj.marshal(order, new FileOutputStream("order.xml"));  
  
    }
}
