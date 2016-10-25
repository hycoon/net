package com.hycoon.net;

import org.apache.http.HttpResponse;

/**
 * 类 名： com.hycoon.net
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/18 19 14
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public interface ICallback<T> {

    void onFailure(AppException result);

    void onSuccess(T result);

    T handle(HttpResponse response, IRequestListener listener) throws AppException;

    T handle(HttpResponse response) throws AppException;

    void cancel();

    int retryCount();

    T preRequest();

    T postRequest(T t);


}
