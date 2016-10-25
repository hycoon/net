package com.hycoon.net;

import org.apache.http.HttpEntity;

import java.util.HashMap;
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

    public ICallback callback;

    public IRequestListener listener;

    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
    }

    public Request(String url) {
        this.url = url;
        this.method = method.GET;
    }

    public void setCallback(ICallback callback) {
        this.callback = callback;
    }

    public void setRequestLisener(IRequestListener lisener) {
        this.listener = lisener;
    }

    public void addHeader(String key, String value) {

        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put(key, value);

    }


    public void execute() {
        RequestTask task = new RequestTask(this);
        task.execute();

    }

    public void cancel() {
        if (callback != null) {
            callback.cancel();
        }
    }


}
