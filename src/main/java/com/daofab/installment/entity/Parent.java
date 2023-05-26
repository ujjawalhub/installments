package com.daofab.installment.entity;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "parent")
public class Parent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAT_SEQ_ID")
    @SequenceGenerator(name = "PAT_SEQ_ID", sequenceName = "PAT_SEQ_ID")
    private Integer id;

    private String sender;

    private String receiver;

    private BigDecimal totalAmount;

    @Transient
    private BigDecimal totalPaidAmount;

}
