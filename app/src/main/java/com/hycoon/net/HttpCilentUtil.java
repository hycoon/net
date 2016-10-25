package com.hycoon.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.Map;

/**
 * 类 名： com.hycoon.net
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/13 09 43
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public class HttpCilentUtil {

    public static HttpResponse execute(Request request) throws AppException {

        switch (request.method) {

            case GET:
                return get(request);

            case POST:
                return post(request);

            case PUT:

                break;
            case DELETE:

                break;
            default:
                throw new AppException(AppException.ExceptionStatus.ParameterException, " the request method " + request.method.name() + " can't be supportted");
        }


        return null;
    }


    private static HttpResponse get(Request request) throws AppException {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(request.url);
            setHeader(get, request.headers);
            return client.execute(get);
        } catch (ConnectTimeoutException e) {
            throw new AppException(AppException.ExceptionStatus.TimeoutException, e.getMessage());
        } catch (IOException e) {
            throw new AppException(AppException.ExceptionStatus.IOException, e.getMessage());
        }

    }


    private static HttpResponse post(Request request) throws AppException {


        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(request.url);
            if (request.entity == null) {
                throw new IllegalStateException("you should set post content when use POST to request ");
            }
            setHeader(post, request.headers);
            post.setEntity(request.entity);

            return client.execute(post);
        } catch (IOException e) {
            throw new AppException(AppException.ExceptionStatus.IOException, e.getMessage());
        }


    }


    private static void setHeader(HttpUriRequest mHttpUriRequest, Map<String, String> headers) {
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                mHttpUriRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }
}
