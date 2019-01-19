package com.zhoudb.eattoday3.network;

import com.google.gson.GsonBuilder;
import com.zhoudb.eattoday3.common.Config;
import com.zhoudb.eattoday3.network.bean.JsonItem;
import com.zhoudb.eattoday3.network.bean.JsonItemArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/4/25.
 */

public class RetrofitHelper {
    private static RetrofitHelper retrofitHelper;
    private Retrofit mRetrofit;

    protected RetrofitHelper(){

    }

    public static RetrofitHelper getRetrofitHelper(){
        if(retrofitHelper == null){
            retrofitHelper = new RetrofitHelper();
        }

        return retrofitHelper;
    }

    public Retrofit getRetrofit(){
        if(mRetrofit == null){
            synchronized (RetrofitHelper.class){
                if(mRetrofit == null){
                    OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10,TimeUnit.SECONDS);


                    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Config.SERVER_URL)

                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").create()));

                    mRetrofit = builder.client(okHttpClientBuilder.build()).build();
                }
            }
        }
        return mRetrofit;
    }




    public static <T> T creat(Class<T> cls){
        return getRetrofitHelper().getRetrofit().create(cls);
    }



    public static RequestBody getBody(JsonItemArray itemArray, JsonItem... jsonItemArray) {
        JSONObject jsonObject = new JSONObject();

        if (itemArray == null) {
            return getBody(jsonItemArray);
        }
        String key = itemArray.getKey();
        String[] strArray = itemArray.getStrList();
        JsonItem[][] jsonItemArrays = itemArray.getJsonItems();
        JSONArray jsonArray = new JSONArray();


        if (strArray != null && strArray.length > 0) {
            for (String item : strArray) {
                if (item == null) {
                    continue;
                }
                jsonArray.put(item);
            }
            try {
                jsonObject.put(key, jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (jsonItemArrays != null && jsonItemArrays.length > 0) {
            try {
                for (JsonItem[] jsonItems : jsonItemArrays) {
                    JSONObject itemObject = new JSONObject();
                    for (JsonItem item : jsonItems) {
                        if (item == null) {
                            continue;
                        }
                        itemObject.put(item.getKey(), item.getValue());
                    }
                    jsonArray.put(itemObject);

                }
                jsonObject.put(key, jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (jsonItemArray != null && jsonItemArray.length > 0) {
            try {
                for (JsonItem item : jsonItemArray) {
                    if (item == null) {
                        continue;
                    }
                    jsonObject.put(item.getKey(), item.getValue());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
    }

    public static RequestBody getBody(JsonItem... jsonItems){
        JSONObject jo = new JSONObject();
        if (jsonItems != null && jsonItems.length > 0){
            for (JsonItem jsonItem : jsonItems){
                try {
                    jo.put(jsonItem.getKey(),jsonItem.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(jo.toString());
        return RequestBody.create(MediaType.parse("application/json"),jo.toString());
    }





    public static MultipartBody.Part getBody(File file){
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file",file.getName(),requestBody);
        return part;
    }


}
