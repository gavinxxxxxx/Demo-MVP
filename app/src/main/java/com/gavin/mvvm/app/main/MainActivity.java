package com.gavin.mvvm.app.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gavin.mvvm.R;
import com.gavin.mvvm.app.base.BindingActivity;
import com.gavin.mvvm.base.RxBus;
import com.gavin.mvvm.databinding.ActMainBinding;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BindingActivity<ActMainBinding>
        implements NavigationView.OnNavigationItemSelectedListener {

    private Subscription rxSubscription;

    @Override
    protected int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    protected void afterCreate(@Nullable Bundle savedInstanceState) {
//        EventBus.getDefault().register(this);

        // 状态栏深色图标
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (savedInstanceState == null) {
            loadRootFragment(R.id.holder, PermissionFragment.newInstance());
        }

        initNavigationView();
        subscribeDrawerToggleEvent();
    }

    /**
     * 初始化左侧菜单
     */
    private void initNavigationView() {
        binding.navigation.setNavigationItemSelectedListener(this);

        TextView textView = (TextView) binding.navigation.getMenu().findItem(R.id.nav_tip).getActionView();
        textView.setText("9+");
        TextView textView2 = (TextView) binding.navigation.getMenu().findItem(R.id.nav_msg).getActionView().findViewById(R.id.textView);
        textView2.setText("提示信息");
        SwitchCompat switchCompat = (SwitchCompat) binding.navigation.getMenu().findItem(R.id.nav_switch).getActionView();
        switchCompat.setChecked(true);
    }

    /**
     * 订阅侧滑菜单开关事件（用 RxBus 替代 EventBus）
     */
    private void subscribeDrawerToggleEvent() {
        rxSubscription = RxBus.getDefault().toObservable(DrawerToggleEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DrawerToggleEvent>() {
                    @Override
                    public void call(DrawerToggleEvent event) {
                        if (event.open && !binding.drawer.isDrawerOpen(GravityCompat.START)) {
                            binding.drawer.openDrawer(GravityCompat.START);
                        } else if (!event.open && binding.drawer.isDrawerOpen(GravityCompat.START)) {
                            binding.drawer.closeDrawer(GravityCompat.START);
                        }
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        binding.drawer.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_bottom_navigation:

                break;
            case R.id.nav_tag_group:

                break;
            case R.id.nav_vector:

                break;
            case R.id.nav_rxJava:

                break;
            case R.id.nav_retrofit:

                break;
            case R.id.nav_dagger2:

                break;
        }
        return false;
    }

    private void startWithPopDelayed(final SupportFragment fragment) {
        binding.navigation.postDelayed(new Runnable() {
            @Override
            public void run() {
                startWithPop(fragment);
            }
        }, 380);
    }

    @Override
    public void onBackPressedSupport() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressedSupport();
        }
    }

    @Override
    protected void onDestroy() {
        if (rxSubscription != null && !rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
        super.onDestroy();
    }

}
