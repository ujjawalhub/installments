package com.daofab.installment.dao;

import com.daofab.installment.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildDao extends JpaRepository<Child, Integer> {

    List<Child> findAllByParentId(Integer parentId);

}
