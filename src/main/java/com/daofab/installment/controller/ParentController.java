package com.daofab.installment.controller;

import com.daofab.installment.dao.ParentDao;
import com.daofab.installment.model.ParentData;
import com.daofab.installment.services.TranscationService;
import com.daofab.installment.utils.ControllerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "/parent")
public class ParentController {

    @Autowired
    TranscationService transcationService;

    @GetMapping
    public Callable<ControllerResponse> getPaginatedParent(@RequestParam(value = "page") Integer page,
                                                           @RequestParam(value = "size") Integer size) {
        return ()-> transcationService.getParentData(page, size);
    }

    @GetMapping( value = "/{parentId}" )
    public Callable<ControllerResponse> getChildWithParentId(@PathVariable(value = "parentId") Integer parentId) {
        return ()-> transcationService.getChildData(parentId);
    }

}
