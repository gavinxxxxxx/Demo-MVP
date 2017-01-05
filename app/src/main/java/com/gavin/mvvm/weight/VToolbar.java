package com.gavin.mvvm.weight;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

/**
 * 这是一个拒绝状态栏的toolbar
 *
 * @author gavin.xiong
 * @date 2016/7/27
 */
public class VToolbar extends Toolbar {

    public VToolbar(Context context) {
        super(context);
    }

    public VToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
//        if (SPUtil.getBoolean(Constants.IntentExtra.ENTER_STATUS_ENABLE)) {
            setMarginTop();
//        }
        super.onAttachedToWindow();
    }

    private void setMarginTop() {
        ViewGroup.LayoutParams params = getLayoutParams();
        if (params == null) {
            return;
        }
        if (params instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) params).setMargins(0, getStatusHeight(), 0, 0);
        } else if (params instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) params).setMargins(0, getStatusHeight(), 0, 0);
        } else if (params instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) params).setMargins(0, getStatusHeight(), 0, 0);
        }
        setLayoutParams(params);
    }

    /**
     * 获取设备状态栏高度
     */
    private int getStatusHeight() {
        int statusBarHeight = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(o);
            statusBarHeight = Resources.getSystem().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

}
