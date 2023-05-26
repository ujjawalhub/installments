package com.daofab.installment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentData {
    Integer id;
    String sender;
    String receiver;
    BigDecimal totalAmount;
    BigDecimal totalPaidAmount;
}
