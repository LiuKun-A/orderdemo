package com.hfjy.service.order.common;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

/**
 * 异步统一处理类
 */

@ControllerAdvice
public class ExceptionHandler {
    // 异常处理
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BusinessException exception(Exception exception, WebRequest request) {
        BusinessException businessException = new BusinessException();
        businessException.setCode(100L);
        businessException.setMsg(Strings.isNullOrEmpty(exception.getMessage()) ? JSON.toJSONString(getExceptionInfo(exception)) : exception.getMessage());
        return businessException;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public BusinessException validException(MethodArgumentNotValidException exception, WebRequest request) {
        BusinessException businessException = new BusinessException();
        businessException.setCode(200L);
        businessException.setMsg(getValidMsg(exception));
        return businessException;
    }

    private String getValidMsg(MethodArgumentNotValidException excption) {
        StringBuilder builder = new StringBuilder("");
        if (excption.getBindingResult().hasErrors()) {
            List<ObjectError> errors = excption.getBindingResult().getAllErrors();
            if (errors != null) {
                int i = 0;
                for (ObjectError error : errors) {
                    builder.append(error.getDefaultMessage());
                    if (i != errors.size()-1) {
                        builder.append("\n");
                    }
                    i++;
                }
            }
        }
        return builder.toString();
    }

    // 封装异常基本信息(类名.方法名，第几行)
    private Map<String, Object> getExceptionInfo(Exception e) {
        Map<String, Object> result = Maps.newHashMap();
        StackTraceElement element = e.getStackTrace()[0];
        StackTraceElement parentElem = e.getStackTrace()[1];
        result.put("methodName", element.getClassName() + "." + element.getMethodName() + "()");
        result.put("lineNumber", element.getLineNumber());
        result.put("调用者methodName", parentElem.getClassName() + "." + parentElem.getMethodName() + "()");
        result.put("调用者lineNumber", parentElem.getLineNumber());
        result.put("文件名", element.getFileName());
        result.put("调用者fileName", element.getFileName());
        return result;
    }
}
