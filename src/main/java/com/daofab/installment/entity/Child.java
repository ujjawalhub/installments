package com.daofab.installment.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "child")
@Builder
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHD_SEQ_ID")
    @SequenceGenerator(name = "CHD_SEQ_ID", sequenceName = "CHD_SEQ_ID")
    private Integer id;

    private Integer parentId;

    private BigDecimal paidAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId", referencedColumnName = "id", insertable = false, updatable = false)
    private Parent parent;

}