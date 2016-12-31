package com.gavin.demo301_mvvm.net;

import com.gavin.demo301_mvvm.model.News;
import com.gavin.demo301_mvvm.model.StartImage;
import com.gavin.demo301_mvvm.model.TodayNews;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * API
 *
 * @author gavin.xiong 2016/12/9
 */
public interface ClientAPI {

    /**
     * 获取今日日报新闻列表
     *
     * @return TodayNews
     */
    @GET("news/latest")
    Observable<TodayNews> getTodayNews();

    /**
     * 获取启动图片
     *
     * @return StartImage
     */
    @GET("start-image/1080*1776")
    Observable<StartImage> getStartImage();

    /**
     * 获取新闻
     *
     * @param newsId long
     * @return News
     */
    @GET("news/{newsId}")
    Observable<News> getNews(@Path("newsId") long newsId);
}
