package com.daofab.installment.services;

import com.daofab.installment.dao.ChildDao;
import com.daofab.installment.dao.ParentDao;
import com.daofab.installment.entity.Parent;
import com.daofab.installment.mapper.ChildMapper;
import com.daofab.installment.mapper.ParentMapper;
import com.daofab.installment.model.ChildData;
import com.daofab.installment.model.ParentData;
import com.daofab.installment.utils.ControllerResponse;
import com.daofab.installment.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class TranscationService {

    @Autowired
    ParentDao parentDao;

    @Autowired
    ChildDao childDao;

    @Autowired
    ChildMapper childMapper;

    @Autowired
    ParentMapper parentMapper;

    @Transactional
    public ControllerResponse getParentData(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")).ascending());
        Page<Parent> parentPageData = parentDao.findAllByPageable(pageable);
        log.info(parentPageData.toString());
        return ResponseUtils.createPageResponse(parentPageData, parentMapper.map(parentPageData.getContent()),
                "Parent data not found");
    }

    @Transactional
    public ControllerResponse getChildData(Integer parentId) {
        List<ChildData> childDataList = childMapper.map(childDao.findAllByParentId(parentId));
        log.info(childDataList.toString());
        return ResponseUtils.createDataResponse(childDataList, "Child not found for requested parent Id");
    }

}
