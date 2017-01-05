package com.gavin.mvvm.app.news;

import android.support.annotation.NonNull;

import com.gavin.mvvm.base.BasePresenter;
import com.gavin.mvvm.base.BaseView;
import com.gavin.mvvm.model.TodayNews;

/**
 * This specifies the contract between the view and the presenter.
 * 这指定视图和主持人之间的合同。
 *
 * @author gavin.xiong 2017/1/3  2017/1/3
 */
public interface TodayNewsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showTodayNews(TodayNews todayNews);

        void showTodayNewsDetailsUi(String newsId);

        void showLoadingTodayNewsError(Throwable e);

        void showLoadingTodayNewsEntity();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadTodayNews(boolean forceUpdate);

        void openTodayNewsDetails(@NonNull TodayNews requestedTask);
    }

}
