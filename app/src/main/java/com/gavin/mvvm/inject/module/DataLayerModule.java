package com.gavin.mvvm.inject.module;

import com.gavin.mvvm.service.DailyManager;
import com.gavin.mvvm.service.base.DataLayer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author lsxiao
 * @date 2015-11-04 00:44
 */
@Module
public class DataLayerModule {

    @Singleton
    @Provides
    public DailyManager provideDailyManager() {
        return new DailyManager();
    }


    @Singleton
    @Provides
    public DataLayer provideDataLayer(DailyManager dailyManager) {
        return new DataLayer(dailyManager);
    }

}
