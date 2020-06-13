package com.lb.weixin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public interface weixinServer {
    @RequestMapping(value = "",method = RequestMethod.GET)
    String getMessage(HttpServletRequest request, HttpServletResponse response);

    @RequestMapping(value = "",method = RequestMethod.POST)
    String postMessage(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
