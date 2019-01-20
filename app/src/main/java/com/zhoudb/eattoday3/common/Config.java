package com.zhoudb.eattoday3.common;

import android.os.Environment;

import java.io.File;

public class Config {
    //public static final String SERVER_URL = "http://www.smarttracklock.com:80";
    //public static final String SERVER_URL = "http://haoshi.imwork.net:50380";

    //中东服务器
    //public static final String SERVER_URL = "http://150.109.126.130:8080";

    //腾讯云测试服务器
    //public static final String SERVER_URL = "http://192.168.1.119:8000";
    public static final String SERVER_URL = "https://promoprod.alipay.com";

    //内部测试服务器
    //public static final String SERVER_URL = "http://192.168.1.16:8080";


    public static final String STORAGE_PATH = Environment.getExternalStorageDirectory().getPath() +
            File.separator + "Holl" +File.separator;
    public static final String UPLOAD_IMAGE_NAME = "uploadImage.jpg";
}
