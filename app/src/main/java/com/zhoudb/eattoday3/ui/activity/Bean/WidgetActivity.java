package com.zhoudb.eattoday3.ui.activity.Bean;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author k-lm on 2017/11/14.
 */

public abstract class WidgetActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLayout();
        initView();
        init();
        initData();

    }

    /**
     * 返回layoutId
     *
     * @return layoutId
     */
    protected abstract int getLayoutId();

    /**
     * 用于初始化布局
     */
    void initLayout() {
        setContentView(getLayoutId());
       // SoftHideKeyBoardUtil.assistActivity(this);


    }

    /**
     * 用于初始化view ，仅用于base类
     */
     void initView() {

    }



    protected void init() {

    }

    protected void initData() {
    }









}
