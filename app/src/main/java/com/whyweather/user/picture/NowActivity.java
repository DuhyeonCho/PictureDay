package com.whyweather.user.picture;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.whyweather.user.picture.Weather.WeatherMain;
import com.whyweather.user.picture.forecast.Forecast;
import com.whyweather.user.picture.forecast.Title;

import java.util.ArrayList;

public class NowActivity extends FragmentActivity {

    private TabLayout mTab;
    private ViewPager mPager;
    private MyAdater mAdater;
    private NowFragment mNowFragment;
    private ForecastFragment mForecastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now);

        mTab = (TabLayout) findViewById(R.id.map_tab);
        mPager = (ViewPager) findViewById(R.id.map_pager);

        WeatherMain data = (WeatherMain) getIntent().getSerializableExtra("data");
        Forecast forecast = (Forecast) getIntent().getSerializableExtra("forecast");

        mAdater = new MyAdater(getSupportFragmentManager());

        mPager.setAdapter(mAdater);
        mTab.setupWithViewPager(mPager);

        mNowFragment = new NowFragment().newInstance(data);
        mForecastFragment = new ForecastFragment().newInstance((ArrayList<Title>) forecast.getTitle());
    }

    public void onClick(View view) {
        finish();
    }

    public class MyAdater extends FragmentPagerAdapter {

        public MyAdater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mNowFragment;
                case 1:
                    return mForecastFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    String a = "현재 날씨";
                    return a;
                case 1:
                    String b = "날씨 예보";
                    return b;
            }
            return null;
        }
    }
}
