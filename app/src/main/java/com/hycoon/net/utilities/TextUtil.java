package com.hycoon.net.utilities;

/**
 * 类 名： com.hycoon.net.utilities
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/18 18 05
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public class TextUtil {
    public static boolean isVaidate(String content) {
        if (content != null && !"".equals(content.trim())) {
            return true;
        }
        return false;
    }
}
