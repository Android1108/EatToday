package com.zhoudb.eattoday3.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/5/15.
 */

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request =chain.request();

        Request newRequest;
        Request.Builder builder =request.newBuilder();

        Log.d("url",request.url().toString());


        newRequest =builder.build();
        Response response =chain.proceed(newRequest);

        return  response.newBuilder().build();
    }

}
