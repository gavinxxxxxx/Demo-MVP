package com.gavin.mvvm.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gavin.mvvm.inject.component.ApplicationComponent;
import com.gavin.mvvm.service.base.DataLayer;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2016/12/30  2016/12/30
 */
public abstract class BaseFragment extends SupportFragment {

    @Inject
    DataLayer mDataLayer;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ApplicationComponent.Instance.get().inject(this);
        afterCreate(savedInstanceState);
    }

    protected abstract void afterCreate(@Nullable Bundle savedInstanceState);

    public DataLayer getDataLayer() {
        return mDataLayer;
    }
}
