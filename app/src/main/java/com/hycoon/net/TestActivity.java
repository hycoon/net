package com.hycoon.net;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hycoon.net.entities.ModuleEntity;
import com.hycoon.net.utilities.JsonCallback;
import com.hycoon.net.utilities.Trace;

import java.io.File;
import java.util.ArrayList;

/**
 * 类 名： com.hycoon.net
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/18 19 22
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public class TestActivity extends Activity {
    private static final String TAG = "hycoon";
    private TextView testTv;

    public TestActivity(TextView testTv) {
        this.testTv = testTv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        DownloadTest();

    }


    private void assignViews() {
        testTv = (TextView) findViewById(R.id.test_tv);

    }


    public void DownloadTest() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "demo.apk";
        final Request request = new Request("http://www.bjwcb.cn/pub/download/wcb-1.0.3.17.apk");
        request.addHeader("Content-Type", "*/*");
        request.setCallback(new FileCallback() {

            @Override
            public void onFailure(AppException exception) {

                if (exception.getStatus() == AppException.ExceptionStatus.CancelException) {
                    Trace.d("user cancelled  mannul!");

                }
                exception.printStackTrace();
                Trace.e(exception.getMessage());

            }

            @Override
            public void onSuccess(String path) {
//                Trace.d(path);
            }


        }.cache(path));

        request.setRequestLisener(new IRequestListener() {
            @Override
            public void onProgressUpdate(int curPos, int contentLong) {
                Trace.d("6");
//                Log.d(TAG, "downloading: " + curPos + "/" + contentLong);
                if (curPos > 100) {

                    request.cancel();

                }


            }
        });
        request.execute();
    }


    public void jsonTest() {
        final Request request = new Request("http://www.stay4it.com");
        request.addHeader("Content-Type", "*/*");
        request.setCallback(new JsonCallback<ArrayList<ModuleEntity>>() {
            public ArrayList<ModuleEntity> preRequest() {

                //读数据库操作
                //TODO  query  from  database
                return null;

            }

            public ArrayList<ModuleEntity> postRequest(ArrayList<ModuleEntity> entities) {
                //TODO   insert
                //TODO    comparator

                return entities;


            }


            @Override

            public void onFailure(AppException result) {
                result.printStackTrace();
            }

            @Override
            public void onSuccess(ArrayList<ModuleEntity> result) {
                ArrayList<ModuleEntity> entities = (ArrayList<ModuleEntity>) result;
                for (ModuleEntity moduleEntity : entities) {
                    Log.d(TAG, moduleEntity.getName());
                }
            }


            @Override

            public int retryCount() {
                return 3;

            }

        }

                .

                        setReturnType(new TypeToken<ArrayList<ModuleEntity>>() {
                                }

                                        .

                                                getType()

                        ));
        request.execute();
    }


}
