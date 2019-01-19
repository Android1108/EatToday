package com.zhoudb.eattoday3.network.api;

import com.zhoudb.eattoday3.bean.BaseBean.BaseBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILogin {
    @POST("/login/")
    Call<BaseBean> login(@Body RequestBody body);
}
