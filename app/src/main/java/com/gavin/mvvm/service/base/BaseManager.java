package com.gavin.mvvm.service.base;

import com.gavin.mvvm.inject.component.ApplicationComponent;
import com.gavin.mvvm.net.ClientAPI;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * @author lsxiao
 * @date 2015-11-03 22:28
 */
public abstract class BaseManager {
    @Inject
    ClientAPI mApi;
    @Inject
    Gson mGson;

    public BaseManager() {
        ApplicationComponent.Instance.get().inject(this);
    }

    public ClientAPI getApi() {
        return mApi;
    }

    public Gson getGson() {
        return mGson;
    }
}
