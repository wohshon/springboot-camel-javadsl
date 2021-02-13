package com.redhat.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="order")  
public class Order {
    
    String id;
    String orderId;
    List<OrderItem> orderItems=new ArrayList<OrderItem>();
    Customer customer;
    Payment payment;
    Delivery delivery;
    
}
