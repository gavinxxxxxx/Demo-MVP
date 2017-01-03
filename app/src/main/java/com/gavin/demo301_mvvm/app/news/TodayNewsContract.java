package com.gavin.demo301_mvvm.app.news;

import android.support.annotation.NonNull;

import com.gavin.demo301_mvvm.base.BasePresenter;
import com.gavin.demo301_mvvm.base.BaseView;
import com.gavin.demo301_mvvm.model.TodayNews;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 * 这指定视图和主持人之间的合同。
 *
 * @author gavin.xiong 2017/1/3  2017/1/3
 */
public interface TodayNewsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showTodayNews(List<TodayNews> todayNewsList);

        void showTodayNewsDetailsUi(String newsId);

        void showLoadingTodayNewsError();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadTodayNews(boolean forceUpdate);

        void openTodayNewsDetails(@NonNull TodayNews requestedTask);
    }

}
