package com.harry.core.exceptions;

import com.harry.exceptions.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(String resourceName) {
        super(ErrorCode.ORDER_NOT_FOUND, resourceName + " does not exist");
    }

}