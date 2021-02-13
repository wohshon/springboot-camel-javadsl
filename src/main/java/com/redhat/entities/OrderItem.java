package com.redhat.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@Entity
@XmlRootElement(name="orderItems")
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // uses mysql auto generation
	//@Column(name = "id")    
    Integer id;
    //@Column(name = "order_item_id")
    String orderItemId;
    @OneToOne(cascade = CascadeType.PERSIST)
    Product product;
    //@Column(name = "qty")
    Integer qty;
}
