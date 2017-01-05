package com.gavin.mvvm.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gavin.mvvm.inject.component.ApplicationComponent;
import com.gavin.mvvm.service.base.DataLayer;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2016/12/30  2016/12/30
 */
public abstract class BaseActivity extends SupportActivity {

    @Inject
    DataLayer mDataLayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        ApplicationComponent.Instance.get().inject(this);
        afterCreate(savedInstanceState);
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    protected abstract void afterCreate(@Nullable Bundle savedInstanceState);

    public DataLayer getDataLayer() {
        return mDataLayer;
    }

    public abstract void setContentView();
}
