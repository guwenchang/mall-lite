package com.smart.mall.core.starter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * 基础错误信息
 * @author guwenchang
 * @date 2019-05-17
 */
@Getter
@AllArgsConstructor
public enum BaseError implements IError {
    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),

    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(1, "服务器异常"),

    /**
     * 校验错误
     */
    VIOLATION_ERROR(2, "服务器异常"),
    /**
     * 未登录
     */
    UNAUTHORIZED(-1, "未登录");
    /**
     * code编码
     */
    final int code;
    /**
     * 中文信息描述
     */
    final String message;
}
