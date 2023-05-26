package com.daofab.installment.dao;

import com.daofab.installment.entity.Parent;
import com.daofab.installment.model.ParentData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParentDao extends JpaRepository<Parent, Integer> {

    @Query("SELECT NEW com.daofab.installment.entity.Parent(p.id, p.sender, p.receiver, p.totalAmount, SUM(c.paidAmount) as totalPaidAmount)" +
            " FROM Parent as p LEFT JOIN Child as c ON p.id = c.parentId" +
            " GROUP by p.id")
    Page<Parent> findAllByPageable(Pageable pageable);

}
