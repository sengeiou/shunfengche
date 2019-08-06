package com.windmillsteward.jukutech.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.windmillsteward.jukutech.R;

import java.util.List;


/**
 * Created by chenxin on 2016/5/23.
 * 如果需要Fragment复用(加入回退栈，宿主Activity应该继承我)
 */
public abstract class BackFragmentActivity<T extends Parcelable> extends BaseFragmentActivity<T> {

    public static final int ANIMATION_RIGHT_TO_LEFT = 0;
    public static final int ANIMATION_BOTTOM_TO_UP = 1;
    public static final int ANIMATION_CENTER_SCALE = 2;

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_backfragment_activity);
        //避免重复添加Fragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null || fragments.isEmpty()) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment, true, false);
            }
        }
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }

    //添加fragment isAddBackStack 是否添加到回退栈

    /**
     * @param fragment       要添加的Fragment
     * @param isAddBackStack 是否加入回退栈
     * @param isAnimation    是否开启切换动画 第一个不需要添加
     */
    public void addFragment(BaseFragment fragment, boolean isAddBackStack, boolean isAnimation) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (isAnimation) {
                transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_right_in, R.anim.slide_right_out);
            }
            transaction.add(getFragmentContentId(), fragment, fragment.getClass().getSimpleName());
            if (isAddBackStack) {
                transaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            transaction.commit();
        }
    }

    /**
     * @param fragment       要添加的Fragment
     * @param isAddBackStack 是否加入回退栈
     *                       默认开启动画
     */
    public void addFragment(BaseFragment fragment, boolean isAddBackStack, boolean isAnimation, int animationFlag) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (isAnimation) {
                if (animationFlag == ANIMATION_RIGHT_TO_LEFT)
                    transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_right_in, R.anim.slide_right_out);
                if (animationFlag == ANIMATION_BOTTOM_TO_UP)
                    transaction.setCustomAnimations(R.anim.anim_bottom_in, R.anim.anim_bottom_out, R.anim.anim_bottom_in, R.anim.anim_bottom_out);
                if (animationFlag == ANIMATION_CENTER_SCALE)
                    transaction.setCustomAnimations(R.anim.anim_center_in, R.anim.anim_center_out, R.anim.anim_center_in, R.anim.anim_center_out);
            }
            transaction.add(getFragmentContentId(), fragment, fragment.getClass().getSimpleName());
            if (isAddBackStack) {
                transaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            transaction.commit();
        }
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            removeFragment();
        }
        return true;
    }

}
