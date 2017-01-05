package com.gavin.mvvm.app.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class RecyclerHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public final T binding;

    public RecyclerHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}