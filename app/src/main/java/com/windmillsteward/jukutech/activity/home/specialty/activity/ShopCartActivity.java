package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.shoppingcart.fragment.ShoppingCartFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：
 * 时间：2018/5/7/007
 * 作者：xjh
 */
public class ShopCartActivity extends BaseActivity {

    private FrameLayout fl_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcart);
        initView();

        setParamInt(R.id.fl_content);
        ShoppingCartFragment f = new ShoppingCartFragment();
        Bundle args = new Bundle();
        args.putBoolean(Define.INTENT_DATA,true);
        f.setArguments(args);
        startFragment(null, f);
    }

    private void initView() {
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
    }
}
