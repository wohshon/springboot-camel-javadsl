package com.redhat.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import lombok.Data;

@Data
public class Customer {
    String id;
    Address address;
    String firstName;
    String lastName;
    int age;
    String email;
    Boolean isVIP;
}
