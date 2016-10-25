package com.hycoon.net.utilities;

import com.hycoon.net.AbstractCallback;

import java.lang.reflect.Type;

/**
 * 类 名： com.hycoon.net
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/18 19 06
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public abstract class JsonCallback<T> extends AbstractCallback<T> {
    private Class<T> mReturnClass;
    private Type mReturnType;


    @Override
    protected T bindData(String content) {

        if (mReturnClass != null) {

            return JsonParser.deserializeFromJson(content, mReturnClass);

        } else if (mReturnType != null) {

            return JsonParser.deserializeFromJson(content, mReturnType);
        }


        return null;
    }

    public JsonCallback<T> setReturnClass(Class<T> clz) {
        this.mReturnClass = clz;
        return this;
    }


    public JsonCallback<T> setReturnType(Type type) {
        this.mReturnType = type;
        return this;
    }


}
