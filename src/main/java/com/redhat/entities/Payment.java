package com.redhat.entities;

import lombok.Data;

@Data
public class Payment {
    
    String paymentType;
    Address billingAddress;
    String remarks;
     
}
