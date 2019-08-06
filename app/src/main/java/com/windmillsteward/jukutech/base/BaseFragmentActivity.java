package com.windmillsteward.jukutech.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;


import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.event.NotifyHomeLocation;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 有参数传递的需求的Fragment的宿主Activity,且有参数传递的需求，继承我
 *
 * @param <T>
 */
public abstract class BaseFragmentActivity<T extends Parcelable> extends BaseActivity {
    //传递参数的key intent.putExtra(key, value);
    public static final String ARGS = "args";
    //传递参数的key intent.putExtra(key, value);
    public static final String TAG_FRAGMENT = "tag_fragment";
    //Activity间传的参数(该对象应可序列化)
    public T args;
    //用来区分寄生哪个Fragment 用Fragment的TAG为其赋值
    public String tag_fragment;
    //如果请求一个Fragment需要数据回来 类似startActivityResult
    public Parcelable requestData;

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
    }

    //处理Intent,如有参数传过来，从Intent里取
    protected void handleIntent(Intent intent) {
        tag_fragment = intent.getStringExtra(TAG_FRAGMENT);
        args = intent.getParcelableExtra(ARGS);
    }

    //添加fragment 不加入回退栈 isAnimation是否开启动画，Fragmeng间的跳转传true
    public void replaceFragment(BaseFragment fragment, boolean isAnimation) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (isAnimation) {
                //transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
                transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out, R.anim.slide_right_in, R.anim.slide_right_out);
            }
            transaction.replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName());
            transaction.commit();
        }
    }

    //移除fragment
    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    //移除fragment
    public void closeAllFragment() {
        finish();
    }

    //回到第一个Fragment
    public void backFirstFragment() {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (int i = fragments.size(); i > 1; i--) {
            fragmentManager.popBackStack();
        }
    }

    /**
     * 根据TAG寻找指定的Fragment
     *
     * @param tag
     * @return
     */
    public boolean findFragment(String tag) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Fragment f :
                    fragments) {
                try {
                    if (f.getClass().getSimpleName().equals(tag)) {
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }
}
