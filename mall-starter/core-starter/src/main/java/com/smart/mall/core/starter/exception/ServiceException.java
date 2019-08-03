package com.smart.mall.core.starter.exception;

import lombok.Getter;

/**
 * 服务异常
 * @author guwenchang
 * @date 2019-08-03
 */
public final class ServiceException extends RuntimeException {

    @Getter
    private final IError error;

    public ServiceException(IError error) {
        super(error.getMessage());
        this.error = error;
    }

    public ServiceException(IError error, Throwable cause) {
        super(cause);
        this.error = error;
    }
}
