package com.daofab.installment.utils;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

public class ResponseUtils {

    public static <T> ControllerResponse<T> createPageResponse(Page pageData, T data, String error) {
        ControllerResponse controllerResponse = new ControllerResponse();
        try {
            if (pageData != null && data != null) {
                controllerResponse.setSuccess(true);
                controllerResponse.setPage(pageData.getPageable().getPageNumber());
                controllerResponse.setPageSize(pageData.getPageable().getPageSize());
                controllerResponse.setTotal(pageData.getTotalElements());
                controllerResponse.setData(data);
            } else {
                controllerResponse.setSuccess(false);
                controllerResponse.setErrorMessage(error);
            }
        } catch (Exception ex) {
            throw new ServiceException("SERVICE_EXCEPTION", ex);
        }
        return controllerResponse;
    }

    public static <T> ControllerResponse<T> createDataResponse(T objectData, String error) {
        ControllerResponse controllerResponse = new ControllerResponse();
        try {
            if (objectData != null) {
                controllerResponse.setSuccess(true);
                controllerResponse.setData(objectData);
            } else {
                controllerResponse.setSuccess(false);
                controllerResponse.setErrorMessage(error);
            }
        } catch (Exception ex) {
            throw new ServiceException("SERVICE_EXCEPTION", ex);
        }
        return controllerResponse;
    }
}
