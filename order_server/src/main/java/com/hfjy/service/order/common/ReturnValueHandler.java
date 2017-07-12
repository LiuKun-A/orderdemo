package com.hfjy.service.order.common;

import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回值统一处理类，实现返回统一格式
 */
@ControllerAdvice
public class ReturnValueHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ReturnValue returnValue = new ReturnValue();
        if (o instanceof BusinessException) {
            returnValue.setStatus(1);
            returnValue.setMsg(((BusinessException) o).getMsg());
        } else {
            returnValue.setData(o);
            returnValue.setStatus(0);
        }
        if (!o.getClass().equals(String.class)) {
            return returnValue;
        } else {
            return JSON.toJSONString(returnValue);
        }
    }
}
