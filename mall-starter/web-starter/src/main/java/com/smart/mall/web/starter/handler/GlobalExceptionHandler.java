package com.smart.mall.web.starter.handler;


import com.google.common.collect.Lists;
import com.smart.mall.core.starter.exception.ApiException;
import com.smart.mall.core.starter.exception.BaseError;
import com.smart.mall.core.starter.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 安全异常处理
 * @author guwenchang
 * @date 2019-04-22 18:47
 */
@Slf4j
@Configuration
@ConditionalOnWebApplication
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResult handleError(MissingServletRequestParameterException e) {
        log.warn("缺少请求参数", e.getMessage());
        String message = String.format("缺少必要的请求参数: %s", e.getParameterName());
        return ApiResult.error(BaseError.VIOLATION_ERROR, message);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResult handleError(MethodArgumentTypeMismatchException e) {
        log.warn("请求参数格式错误", e.getMessage());
        String message = String.format("请求参数格式错误: %s", e.getName());
        return ApiResult.error(BaseError.VIOLATION_ERROR, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleError(MethodArgumentNotValidException e) {
        log.warn("参数验证失败", e.getMessage());
        return handleError(e.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    public ApiResult handleError(BindException e) {
        log.warn("参数绑定失败", e.getMessage());
        return handleError(e.getBindingResult());
    }

    private ApiResult handleError(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> fieldErrorMsgList = Lists.newArrayList();
        for (FieldError fieldError : fieldErrors) {
            fieldErrorMsgList.add(String.format("%s:%s", fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ApiResult.error(BaseError.VIOLATION_ERROR, String.join(",",fieldErrorMsgList));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult handleError(ConstraintViolationException e) {
        log.warn("参数验证失败", e.getMessage());
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return ApiResult.error(BaseError.VIOLATION_ERROR, message);
    }



    @ExceptionHandler(ApiException.class)
    public ApiResult handleError(ApiException e) {
        log.error("业务异常", e);
        return ApiResult.error(e.getError(), e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ApiResult handleError(Throwable e) {
        log.error("服务器异常", e);
        return ApiResult.error(BaseError.INTERNAL_SERVER_ERROR,e.getMessage());
    }
}
