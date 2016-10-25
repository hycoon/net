package com.hycoon.net;

import com.hycoon.net.utilities.TextUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类 名： com.hycoon.net
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/18 17 59
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public abstract class AbstractCallback<T> implements ICallback<T> {
    public String path;
    //取消操作
    private boolean isCancelled;

    public void checkIfIsCancelled() throws AppException {
        if (isCancelled) {
            throw new AppException(AppException.ExceptionStatus.CancelException, "the request  has bean cancelled");
        }

    }

    //默认情况下是不重试的
    @Override
    public int retryCount() {
        return 0;
    }


    @Override
    public T preRequest() {
        return null;
    }

    @Override
    public T postRequest(T t) {
        return t;
    }

    //解析网络请求返回数据
    @Override
    public T handle(HttpResponse response, IRequestListener listener) throws AppException {
        //从网络请求中获得的数据 json、xml、string、image 、file
        checkIfIsCancelled();
        HttpEntity entity = response.getEntity();
        //判断返回值进行操作
        try {
            switch (response.getStatusLine().getStatusCode()) {
                case HttpStatus.SC_OK:
                    //如果路径有效 写入SD卡中
                    if (TextUtil.isVaidate(path)) {
                        FileOutputStream fos = new FileOutputStream(path);
                        InputStream is = entity.getContent();
                        byte[] buffer = new byte[2048];
                        int len = -1;
                        //下载文件的总大小
                        long contentLength = entity.getContentLength();
                        long curPos = 0;
                        while ((len = is.read(buffer)) != -1) {
                            checkIfIsCancelled();
                            //进度
                            curPos += len;
                            //监听回调
                            listener.onProgressUpdate((int) (curPos / 1024), (int) (contentLength / 1024));
                            fos.write(buffer, 0, len);
                        }
                        is.close();
                        fos.flush();
                        fos.close();
                        bindData(path);
                    } else {
                        return bindData(EntityUtils.toString(entity, "UTF-8"));
                    }
                    break;
                default:
                    break;
            }


            return null;
        } catch (IOException e) {
            throw new AppException(AppException.ExceptionStatus.IOException, e.getMessage());
        } catch (IllegalStateException e) {
            throw new AppException(AppException.ExceptionStatus.IllegalStateException, e.getMessage());
        } catch (ParseException e) {
            throw new AppException(AppException.ExceptionStatus.ParseException.IllegalStateException, e.getMessage());
        }
    }

    @Override
    public T handle(HttpResponse response) throws AppException {
        return handle(response, null);
    }

    protected abstract T bindData(String content);

    public AbstractCallback<T> cache(String path) {
        this.path = path;
        return this;
    }

    public void cancel() {
        isCancelled = true;
    }
}
