package com.gavin.demo301_mvvm.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gavin.demo301_mvvm.R;
import com.gavin.demo301_mvvm.base.BaseFragment;
import com.gavin.demo301_mvvm.databinding.FragGridLayoutBinding;

/**
 * 测试
 */
public class GridLayoutFragment extends BaseFragment<FragGridLayoutBinding> {

    @Override
    protected int getLayout() {
        return R.layout.frag_grid_layout;
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {

    }
}
