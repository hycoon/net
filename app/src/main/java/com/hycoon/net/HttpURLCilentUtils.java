package com.hycoon.net;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 类 名： com.hycoon.net
 * 功 能： HttpURLCilentUtils
 * 作 者： Administrator
 * 时 间： 2016/10/17 19 04
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public class HttpURLCilentUtils {

    public static HttpResponse execute(Request request) throws IOException {

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
                throw new IllegalStateException(" the request method " + request.method.name() + " can't be supportted");


        }


        return null;
    }


    private static HttpResponse get(Request request) throws IOException {
        URL url = new URL(request.url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod(String.valueOf(Request.RequestMethod.GET));
        return (HttpResponse) conn.getInputStream();
    }

    private static HttpResponse post(Request request) throws IOException {
        URL url = new URL(request.url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(String.valueOf(Request.RequestMethod.POST));
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.connect();

        return null;
    }
}
