package com.gavin.mvvm.app.base;

import android.app.Application;

import com.gavin.mvvm.inject.component.ApplicationComponent;
import com.gavin.mvvm.inject.component.DaggerApplicationComponent;
import com.gavin.mvvm.inject.module.ApplicationModule;
import com.gavin.mvvm.utils.L;

/**
 * 自定义Application
 *
 * @author gavin.xiong 2016/5/18
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        ApplicationComponent.Instance.init(DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build());
    }
}
