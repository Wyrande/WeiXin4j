package com.lb.weixin.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterUtils {

    public static String getSHA1(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            // 加密
            byte[] digest = md.digest(str.getBytes());
            // 处理加密结果
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (byte b : digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b & 15]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 解析XML数据包
     *
     * @param request
     * @return
     */
    public static Map<String, String> parseRequest2Map(HttpServletRequest request) {

        // 解析XML
        SAXReader reader = new SAXReader();
        Map<String, String> requestMap = new HashMap<>();
        try {
            ServletInputStream is = request.getInputStream();
            Document document = reader.read(is);
            // 获取根节点
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                requestMap.put(element.getName(), element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestMap;
    }

    public static void parseRequest2Map(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        String respXML = "";
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(respXML);
        out.flush();
        out.close();
    }
}
