package com.hfjy.service.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HFJY on 2017/7/4.
 */
@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "ok";
    }
}
