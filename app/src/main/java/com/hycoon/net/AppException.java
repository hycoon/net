package com.hycoon.net;

/**
 * 类 名： com.hycoon.net
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/25 09 57
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public class AppException extends Exception {

    private static final long serialVersionUID = 1L;


    public enum ExceptionStatus {
        IOException, FileNotFoundException, IllegalStateException, ParseException, CancelException, ParameterException,TimeoutException

    }

    private ExceptionStatus status;

    public AppException(ExceptionStatus status, String message) {
        super(message);
        this.status = status;
    }


    public ExceptionStatus getStatus() {

        return status;

    }
}
