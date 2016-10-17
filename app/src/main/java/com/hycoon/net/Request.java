package com.hycoon.net;

import org.apache.http.HttpEntity;

import java.util.Map;

/**
 * 类 名： com.hycoon.net
 * 功 能： Request bean
 * 作 者： Administrator
 * 时 间： 2016/10/13 10 00
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public class Request {


    public enum RequestMethod {
        GET, POST, PUT, DELETE
    }

    public RequestMethod method;

    public String url;

    public HttpEntity entity;

    public Map<String, String> headers;


    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
    }

    public Request(String url) {
        this.url = url;
        this.method = method.GET;
    }
}
