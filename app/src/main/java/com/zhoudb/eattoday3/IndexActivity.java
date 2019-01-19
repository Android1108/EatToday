package com.zhoudb.eattoday3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.TextView;

import com.zhoudb.eattoday3.adapter.IndexFragmentAdapter;
import com.zhoudb.eattoday3.ui.activity.Bean.WidgetActivity;
import com.zhoudb.eattoday3.ui.fragment.MineFragment;
import com.zhoudb.eattoday3.ui.fragment.SelectDishFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class IndexActivity extends WidgetActivity {

    private TextView mTextMessage;
    private List<Fragment> mFragmentList;

    @BindView(R.id.aty_index_vp)
    ViewPager mViewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected void init() {
        super.init();
        mFragmentList=new ArrayList<>();
        MineFragment mineFragment=new MineFragment();
        SelectDishFragment selectDishFragment=new SelectDishFragment();
        mFragmentList.add(mineFragment);
        mFragmentList.add(selectDishFragment);
        IndexFragmentAdapter mIndexFragmentAdapter=new IndexFragmentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mIndexFragmentAdapter);





    }
}
