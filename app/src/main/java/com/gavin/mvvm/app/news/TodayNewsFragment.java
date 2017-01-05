package com.gavin.mvvm.app.news;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.gavin.mvvm.R;
import com.gavin.mvvm.app.base.BindingFragment;
import com.gavin.mvvm.app.main.DrawerToggleEvent;
import com.gavin.mvvm.databinding.FragNewsBinding;
import com.gavin.mvvm.model.TodayNews;
import com.gavin.mvvm.utils.L;

import org.greenrobot.eventbus.EventBus;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 今日热点新闻
 *
 * @author gavin.xiong 2016/12/28
 */
public class TodayNewsFragment extends BindingFragment<FragNewsBinding> {

    public static TodayNewsFragment newInstance() {
        return new TodayNewsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_news;
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {
        init();
        getTodayNews();
    }

    private void init() {
        binding.toolbar.inflateMenu(R.menu.main);
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DrawerToggleEvent(true));
            }
        });
        binding.toolbar.setTitle("南淮小石子");

        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTodayNews();
            }
        });
        //为下拉刷新组件设置CircleProgress主色调
        binding.refreshLayout.setColorSchemeColors(ContextCompat.getColor(_mActivity, R.color.textColorSecondary));
    }


    private void getTodayNews() {
        getDataLayer().getDailyService().getTodayNews()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        L.e(Looper.myLooper() == Looper.getMainLooper());
                        binding.refreshLayout.setRefreshing(true);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TodayNews>() {

                    @Override
                    public void onNext(TodayNews todayNews) {
                        TodayNewsAdapter adapter = new TodayNewsAdapter(_mActivity, todayNews.getStories());
                        binding.recycler.setLayoutManager(new LinearLayoutManager(_mActivity));
                        binding.recycler.setAdapter(adapter);
                    }

                    @Override
                    public void onCompleted() {
                        binding.refreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        binding.refreshLayout.setRefreshing(false);
                        Snackbar.make(binding.refreshLayout,
                                "onError - " + e.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
                    }
                });
    }
}