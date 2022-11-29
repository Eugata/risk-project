package com.kkcloud.risk.vo;

import java.util.ArrayList;

public class ResponseVO<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }

    public ResponseVO(int code, String message, T data){
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }


    public ResponseVO(ServiceStatusCode serviceStatusCode) {
        this.code = serviceStatusCode.getCode();
        this.message = serviceStatusCode.getMsg();
        this.data = (T) new ArrayList();
    }
    public ResponseVO(ServiceStatusCode serviceStatusCode,T data) {
        this.code = serviceStatusCode.getCode();
        this.message = serviceStatusCode.getMsg();
        this.data = data;

    }



}
