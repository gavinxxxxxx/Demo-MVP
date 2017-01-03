package com.gavin.demo301_mvvm.app.news;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.gavin.demo301_mvvm.R;
import com.gavin.demo301_mvvm.app.main.DrawerToggleEvent;
import com.gavin.demo301_mvvm.base.BaseFragment;
import com.gavin.demo301_mvvm.databinding.FragNewsBinding;
import com.gavin.demo301_mvvm.model.TodayNews;
import com.gavin.demo301_mvvm.net.ClientAPI;
import com.gavin.demo301_mvvm.utils.L;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 今日热点新闻
 *
 * @author gavin.xiong 2016/12/28
 */
public class NewsFragment extends BaseFragment<FragNewsBinding> {

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.frag_news;
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {
        initView();
        getTodayNews();
    }

    private void initView() {
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

    private ClientAPI getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder().create()))
                .addCallAdapterFactory(
                        RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BASIC))
                        .build())
                .build();
        return retrofit.create(ClientAPI.class);
    }

    private void getTodayNews() {
        getApi().getTodayNews()
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