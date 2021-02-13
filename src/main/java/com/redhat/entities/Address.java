package com.redhat.entities;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import lombok.Data;

@Data
public class Address {

    String address1;
    String address2;
    String country;
    String unitNumber;
    String postalCode;

}
