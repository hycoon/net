package com.hycoon.net;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;

/**
 * 类 名： com.hycoon.net
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/18 18 33
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {

    private Request request;

    public RequestTask(Request request) {
        this.request = request;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        if (request.callback != null) {
            if (result instanceof AppException) {
                request.callback.onFailure((AppException) result);
            } else {
                request.callback.onSuccess(result);
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        request.listener.onProgressUpdate(values[0], values[1]);

    }

    @Override
    protected Object doInBackground(Void... params) {

        int retryCount = 0;
        int retry = 0;
        if (request.callback != null) {
            retryCount = request.callback.retryCount();
        }

        return request(retry, retryCount);
    }


    private Object request(int retry, int retryCount) {

        try {
            Object result = null;

            if (request.callback != null) {
                result = request.callback.preRequest();
                if (result != null) {
                    return result;
                }
            }


            HttpResponse response = HttpCilentUtil.execute(request);
            if (request.callback != null) {
                //处理完后返回的callback
                if (request.listener != null) {
                    result = request.callback.handle(response, new IRequestListener() {
                        @Override
                        public void onProgressUpdate(int curPos, int contentLong) {
                            publishProgress(curPos, contentLong);
                        }
                    });
                } else {
                    result = request.callback.handle(response);
                }

                return request.callback.postRequest(result);

            } else {
                return null;
            }

        } catch (AppException e) {
            if (e.getStatus() == AppException.ExceptionStatus.TimeoutException) {
                if (retry < retryCount) {
                    return request(retry++, retryCount);
                }
            }
            return e;
        }


    }
}
