package com.smart.mall.core.starter.util;

import com.smart.mall.core.starter.constant.CommonConstant;
import com.smart.mall.core.starter.model.ApiResult;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import javax.servlet.ServletRequest;
import java.util.UUID;
/**
 *
 * @author guwenchang
 * @date 2019-08-03
 */
public class MallUtil {

    public static Integer getUserId(ServletRequest request) {
        return (Integer) request.getAttribute(CommonConstant.REQUEST_ATTR_USER_ID_KEY);
    }

    public static void setUserId(ServletRequest request, Integer userId) {
        request.setAttribute(CommonConstant.REQUEST_ATTR_USER_ID_KEY, userId);
    }

    public static Integer getUserType(ServletRequest request) {
        return (Integer) request.getAttribute(CommonConstant.REQUEST_ATTR_USER_TYPE_KEY);
    }

    public static void setUserType(ServletRequest request, Integer userType) {
        request.setAttribute(CommonConstant.REQUEST_ATTR_USER_TYPE_KEY, userType);
    }

    public static ApiResult getCommonResult(ServletRequest request) {
        return (ApiResult) request.getAttribute(CommonConstant.REQUEST_ATTR_COMMON_RESULT);
    }

    public static void setApiResult(ServletRequest request, ApiResult result) {
        request.setAttribute(CommonConstant.REQUEST_ATTR_COMMON_RESULT, result);
    }

    /**
     * 获得链路追踪编号
     *
     * 一般来说，通过链路追踪编号，可以将访问日志，错误日志，链路追踪日志，logger 打印日志等，结合在一起，从而进行排错。
     *
     * 默认情况下，我们使用 Apache SkyWalking 的 traceId 作为链路追踪编号。当然，可能会存在并未引入 Skywalking 的情况，此时使用 UUID 。
     *
     * @return 链路追踪编号
     */
    public static String getTraceId() {
        String traceId = TraceContext.traceId();
        if (StringUtil.hasText(traceId)) {
            return traceId;
        }
        // TODO 芋艿 多次调用会问题
        return UUID.randomUUID().toString();
    }

}
