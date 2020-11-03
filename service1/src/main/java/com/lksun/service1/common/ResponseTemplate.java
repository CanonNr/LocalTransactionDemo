package com.lksun.service1.common;

import lombok.Data;

@Data
public class ResponseTemplate {
    public int code;
    public Object data;
    public String message;

    public static ResponseTemplate failed(String message){
        ResponseTemplate response = new ResponseTemplate();
        response.code = 400;
        response.data = null;
        response.message = message;
        return response;
    }

    public static ResponseTemplate success(Object data){
        ResponseTemplate response = new ResponseTemplate();
        response.code = 200;
        response.data = data;
        response.message = "请求成功";
        return response;
    }
}
