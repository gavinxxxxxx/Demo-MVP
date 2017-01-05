package com.gavin.mvvm.weight;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * 这里是萌萌哒注释君
 *
 * @author gavin.xiong 2016/11/10
 */
public class VDrawerLayout extends DrawerLayout {

    public VDrawerLayout(Context context) {
        this(context, null);
    }

    public VDrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewCompat.setOnApplyWindowInsetsListener(this, new android.support.v4.view.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                return insets.consumeSystemWindowInsets();
            }
        });
    }
}
