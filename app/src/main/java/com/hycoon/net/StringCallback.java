package com.hycoon.net;

/**
 * 类 名： com.hycoon.net
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/18 19 06
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public abstract class StringCallback extends AbstractCallback {
    @Override
    protected Object bindData(String content) {
        return content;
    }
}
