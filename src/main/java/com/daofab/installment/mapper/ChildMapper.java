package com.daofab.installment.mapper;

import com.daofab.installment.entity.Child;
import com.daofab.installment.model.ChildData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChildMapper {
    ChildData map(Child child);

    Child map(ChildData child);

    List<ChildData> map(List<Child> childs);
}
