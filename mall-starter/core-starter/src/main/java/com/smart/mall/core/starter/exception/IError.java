package com.smart.mall.core.starter.exception;

/**
 *
 * @author guwenchang
 * @date 2019-05-17
 */
public interface IError {


    /**
     * 错误吗
     * @return
     */
    int getCode();

    /**
     * 错误信息
     * @return
     */
    String getMessage();
}
