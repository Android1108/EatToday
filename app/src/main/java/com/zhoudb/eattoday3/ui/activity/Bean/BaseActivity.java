package com.zhoudb.eattoday3.ui.activity.Bean;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author k-lm on 2017/11/14.
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 加载进度条显示
     */
    private ProgressDialog progressDialog;

    private boolean isDestroyed = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    /**
     * 吐司提示
     */
    public void showToast(String context) {
        Toast.makeText(this, context, Toast.LENGTH_SHORT).show();
    }

    /**
     * 吐司提示
     */
    public void showToast(@StringRes int context) {
        Toast.makeText(this, context, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示加载对话框
     */
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (progressDialog.isShowing()) {
            return;
        }

        progressDialog.show();
        progressDialog.setMessage("正在加载请稍候");
    }

    /**
     * 显示加载对话框
     *
     * @param msg 消息内容
     */
    public void showProgressDialog(String msg) {
        showProgressDialog();
        progressDialog.setMessage(msg);
    }


    /**
     * 关闭加载框
     */
    public void closeProgressDialog() {
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        progressDialog.dismiss();
    }

    public void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void startActivityForResult(Class<? extends Activity> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }


    public BaseActivity getThis() {
        return this;
    }

    @Override
    protected void onDestroy() {
        closeProgressDialog();
        isDestroyed = true;

        super.onDestroy();
    }


    @Override
    public boolean isDestroyed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return super.isDestroyed();
        }
        return isDestroyed;
    }


}
