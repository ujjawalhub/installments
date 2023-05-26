package com.daofab.installment.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerResponse<T> {
    private boolean isSuccess = true;
    private String errorMessage;
    private Integer page;
    private Integer pageSize;
    private Long total;
    private T data;
}
