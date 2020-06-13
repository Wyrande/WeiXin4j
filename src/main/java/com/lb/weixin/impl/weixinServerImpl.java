package com.lb.weixin.impl;

import com.lb.weixin.controller.weixinServer;
import com.lb.weixin.utils.ParameterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class weixinServerImpl implements weixinServer {
    @Value("${weixin.token}")
    private String token;

    @Override
    public String getMessage(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hello world");
        // 获取微信平台发过来的验证参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        // 1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[]{token, timestamp, nonce};
        Arrays.sort(strs);
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        String collectStr = strs[0] + strs[1] + strs[2];
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String sign = ParameterUtils.getSHA1(collectStr);
        // 4) 验证成功原样返回echostr
        if (sign.equals(signature)) {
            return echostr;
        } else {
            return "";
        }
    }

    @Override
    public String postMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("post message");
        Map<String, String> requestMap = ParameterUtils.parseRequest2Map(request);
        return "post message";
    }
}
