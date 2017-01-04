package com.gavin.mvvm.app.news;

import android.support.annotation.NonNull;

import com.gavin.mvvm.model.TodayNews;
import com.gavin.mvvm.service.base.DataLayer;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Listens to user actions from the UI ({@link TodayNewsFragment2}), retrieves the data and updates the
 * UI as required.
 *
 * @author gavin.xiong 2017/1/4  2017/1/4
 */
public class TodayNewsPresenter implements TodayNewsContract.Presenter {

    private DataLayer mDataLayer;

    private final TodayNewsContract.View mTodayNewsView;

    private boolean mFirstLoad = true;

    public TodayNewsPresenter(@NonNull TodayNewsContract.View todayNewsView, DataLayer dataLayer) {
        mTodayNewsView = checkNotNull(todayNewsView, "todayNewsView cannot be null!");
        mDataLayer = checkNotNull(dataLayer, "dataLayer cannot be null!");
        mTodayNewsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadTodayNews(false);
    }

    @Override
    public void result(int requestCode, int resultCode) {
        // TODO: 2017/1/4
    }

    /**
     * @param forceUpdate 强制更新？
     */
    @Override
    public void loadTodayNews(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.
        loadTodayNews(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    private void loadTodayNews(boolean forceUpdate, final boolean showLoadingUI) {
        if (forceUpdate) {
//            mTasksRepository.refreshTasks();
        }

        mDataLayer.getDailyService().getTodayNews()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (showLoadingUI) {
                            mTodayNewsView.setLoadingIndicator(true);
                        }
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TodayNews>() {

                    @Override
                    public void onNext(TodayNews todayNews) {
                        mTodayNewsView.showTodayNews(todayNews);
                    }

                    @Override
                    public void onCompleted() {
                        if (showLoadingUI) {
                            mTodayNewsView.setLoadingIndicator(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (showLoadingUI) {
                            mTodayNewsView.setLoadingIndicator(false);
                        }
                        mTodayNewsView.showLoadingTodayNewsError(e);
                    }
                });

    }

    @Override
    public void openTodayNewsDetails(@NonNull TodayNews requestedTask) {
        checkNotNull(requestedTask, "requestedTask cannot be null!");
        // TODO: 2017/1/4
    }
}
