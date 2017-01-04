package com.gavin.mvvm.app.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import org.greenrobot.eventbus.EventBus;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 今日热点新闻
 *
 * @author gavin.xiong 2016/12/28
 */
public class TodayNewsFragment2 extends BindingFragment<FragNewsBinding>
        implements TodayNewsContract.View, SwipeRefreshLayout.OnRefreshListener {

    private TodayNewsContract.Presenter mPresenter;

    public static TodayNewsFragment2 newInstance() {
        return new TodayNewsFragment2();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_news;
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {
        // // TODO: 2017/1/4 初始化1
        mPresenter = new TodayNewsPresenter(this, getDataLayer());
        init();
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

        binding.refreshLayout.setOnRefreshListener(this);
        //为下拉刷新组件设置CircleProgress主色调
        binding.refreshLayout.setColorSchemeColors(ContextCompat.getColor(_mActivity, R.color.textColorSecondary));
    }

    @Override
    public void setPresenter(@NonNull TodayNewsContract.Presenter presenter) {
        // // TODO: 2017/1/4 初始化2
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadTodayNews(true);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        binding.refreshLayout.setRefreshing(active);
    }

    @Override
    public void showTodayNews(TodayNews todayNews) {
        TodayNewsAdapter adapter = new TodayNewsAdapter(_mActivity, todayNews.getStories());
        binding.recycler.setLayoutManager(new LinearLayoutManager(_mActivity));
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void showTodayNewsDetailsUi(String newsId) {
        // TODO: 2017/1/4
    }

    @Override
    public void showLoadingTodayNewsError(Throwable e) {
        Snackbar.make(binding.refreshLayout, "onError - " + e.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showLoadingTodayNewsEntity() {
        Snackbar.make(binding.refreshLayout, "空空如也", Snackbar.LENGTH_INDEFINITE).show();
    }
}