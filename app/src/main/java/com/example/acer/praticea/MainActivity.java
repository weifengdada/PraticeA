package com.example.acer.praticea;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> list;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyAdapter adapter;

    private View home;
    private View search;
    private View other;
    private Banner banner;
    private boolean fromUser;
    private List<Integer> list3;
    private String[] titles = {"页面1", "页面2", "页面3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home = getLayoutInflater().inflate(R.layout.fragmenta, null);
        search = getLayoutInflater().inflate(R.layout.fragmentb, null);
        other = getLayoutInflater().inflate(R.layout.fragmentc, null);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        list = new ArrayList<>();
        list.add(home);
        list.add(search);
        list.add(other);

        //ViewPager的适配器
        adapter = new MyAdapter(this,list,titles);
        viewPager.setAdapter(adapter);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
        banner= home.findViewById(R.id.lunbo);
        list3 = new ArrayList<>();
        list3.add(R.drawable.a);
        list3.add(R.drawable.b);
        list3.add(R.drawable.c);
        list3.add(R.drawable.d);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list3);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == list3.size() - 1 && fromUser) {
                    viewPager.setCurrentItem(1);
                     tabLayout.getTabAt(1).select();
                }
            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {
                //修改切换状态（手动/自动）
                if (state == 1) {
                    fromUser = true;
                } else {
                    fromUser = false;
                }
            }
        });

    }


}
