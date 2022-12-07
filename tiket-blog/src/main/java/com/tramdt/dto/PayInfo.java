package com.tramdt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayInfo {
    public int finalPrice;
    public String nameCustomer;
    public int quantity;
}