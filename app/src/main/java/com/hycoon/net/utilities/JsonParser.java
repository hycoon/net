package com.hycoon.net.utilities;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * 类 名： com.hycoon.net.utilities
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/19 16 29
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public class JsonParser {


    public static Gson gson = new Gson();

    public static <T> T deserializeFromJson(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }

    public static <T> T deserializeFromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static String serializeToJson(Object object) {
        return gson.toJson(object);
    }

}
