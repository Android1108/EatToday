package com.zhoudb.eattoday3.network.api;

import com.zhoudb.eattoday3.bean.BaseBean.AliBean;
import com.zhoudb.eattoday3.bean.BaseBean.BaseBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ILogin {
    @POST("/login/")
    Call<BaseBean> login(@Body RequestBody body);

    @GET("/ali/")
    Call<AliBean> alibean(@Query("campInfo") String campInfo,@Query("bindMobile") String bindMobile,@Query("bizType") String bizType,@Query("shareId") String shareId,@Query("snsScene") String snsScene,@Query("sign") String sign,@Query("json_ua") String json_ua,@Query("_json_token") String _json_token,@Query("_callback") String _callback);

}
