package com.hfjy.service.order.common;

import java.util.Map;

/**
 * Created by HFJY on 2017/7/4.
 */
public class BusinessException extends Exception {
    private Long code;
    private String msg;
    private Map<Object,Object> ext;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<Object, Object> getExt() {
        return ext;
    }

    public void setExt(Map<Object, Object> ext) {
        this.ext = ext;
    }
}
