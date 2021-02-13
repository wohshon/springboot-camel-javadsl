package com.redhat.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import lombok.Data;

@Data
@Entity
public class Product {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // uses mysql auto generation
	//@Column(name = "id")
    Integer id;
    //@Column(name = "name")
	String name;
	//@Column(name="product_id", unique = true)
	private String productId;
	//@Column(name = "short_desc")
	private String shortDesc;
	//@Column(name = "description")
	private String description;
	//@Column(name = "price")
	private Double price;
	//@Column(name="img")
	private String img;
}
