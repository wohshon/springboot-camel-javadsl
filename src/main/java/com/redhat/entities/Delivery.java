package com.redhat.entities;

import java.util.Date;

import lombok.Data;

@Data
public class Delivery {
    Address deliveryAddress;
    Date deliveryDate;
    String remarks;
}
