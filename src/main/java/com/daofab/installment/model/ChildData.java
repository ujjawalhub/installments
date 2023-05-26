package com.daofab.installment.model;

import com.daofab.installment.entity.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildData {
    Integer id;
    Integer parentId;
    BigDecimal paidAmount;
    Parent parent;
}
