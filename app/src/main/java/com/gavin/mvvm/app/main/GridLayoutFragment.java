package com.gavin.mvvm.app.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gavin.mvvm.R;
import com.gavin.mvvm.app.base.BindingFragment;
import com.gavin.mvvm.databinding.FragGridLayoutBinding;

/**
 * 测试
 */
public class GridLayoutFragment extends BindingFragment<FragGridLayoutBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.frag_grid_layout;
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {

    }
}
