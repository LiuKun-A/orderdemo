package com.hfjy.service.order.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by HFJY on 2017/7/6.
 */
public class PaymentUpdateStatusParam {
    @NotNull(message = "{payment.status.notBlank}")
    private Integer status;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
