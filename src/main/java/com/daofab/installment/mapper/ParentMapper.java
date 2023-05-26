package com.daofab.installment.mapper;

import com.daofab.installment.entity.Parent;
import com.daofab.installment.model.ParentData;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParentMapper {
    ParentData map(Parent parent);

    Parent map(ParentData parent);

    List<ParentData> map(List<Parent> masterTargets);
}
