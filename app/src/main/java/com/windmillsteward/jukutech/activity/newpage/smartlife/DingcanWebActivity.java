package com.windmillsteward.jukutech.activity.newpage.smartlife;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.customview.popup.DingcanYudingPopurWindow;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;

import java.lang.reflect.Type;
import java.net.IDN;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DingcanWebActivity extends BaseInitActivity {
    public static final int REQUEST_CODE = 1025;
    public static final int RESULT_CODE_SUCCESS = 300;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.tv_yuding)
    TextView tvYuding;
    @Bind(R.id.lay_ll_root)
    LinearLayout layLlRoot;

    public static final String URL = "url";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String ID = "service_id";

    private String url = "";
    private String title = "";
    private int type;
    private int service_id;

    public static void go(Activity activity, int type, String url, int service_id,String title) {
        Intent intent = new Intent(activity, DingcanWebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        bundle.putInt(ID, service_id);
        bundle.putString(URL, url);
        bundle.putString(TITLE, title);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,REQUEST_CODE);
    }


    @Override
    protected void initView(View view) {
        showContentView();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dingcan_web;
    }

    @Override
    protected void initData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString(URL);
            title = extras.getString(TITLE);
            type = extras.getInt(TYPE);
            service_id = extras.getInt(ID);
            setMainTitle(title);
        }
        initWebViewSetting();
        initWebChromeClient();
        initWebViewClient();
//        webView.loadData(url, "text/html;charset=utf-8", "UTF-8");
        webView.loadUrl(url);
        tvYuding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DingcanYudingPopurWindow dingcanYudingPopurWindow = new DingcanYudingPopurWindow(type, service_id, DingcanWebActivity.this, new DingcanYudingPopurWindow.DataCallBack() {
                    @Override
                    public void yuDingSuccess() {
                        setResult(RESULT_CODE_SUCCESS);
                        MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
                        if (activity != null){
                            activity.changeButtonStatus(2);
                        }
                        finish();
                    }
                });
                dingcanYudingPopurWindow.showAtMiddle(layLlRoot);
            }
        });
    }

    @Override
    protected void refreshPageData() {

    }

    private void initWebViewSetting() {
        WebSettings settings = this.webView.getSettings();
        //支持JS
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持插件
        settings.setPluginState(WebSettings.PluginState.ON);
        //设置适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持缩放
        settings.setSupportZoom(false);
        //隐藏原生的缩放控件
        settings.setDisplayZoomControls(false);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.supportMultipleWindows();
        settings.setSupportMultipleWindows(true);
        //设置缓存模式
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(webView.getContext().getCacheDir().getAbsolutePath());
        //设置可访问文件
        settings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true);
        //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setNeedInitialFocus(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");
        //5.0以上版本不支持http和https图片混合加载
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            this.webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    private void initWebViewClient() {
        webView.setWebViewClient(new WebViewClient() {

            //页面开始加载时
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }


            //页面完成加载时
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setMainTitle(webView.getTitle());
            }

            //是否在WebView内加载新页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String req) {
                Map<String, String> map = new HashMap<String, String>();
                LoginSuccessInfo loginSuccessInfo = (LoginSuccessInfo) Hawk.get(Define.LOGIN_SUCCESS);
                map.put("user_id", loginSuccessInfo.getUser_id() + "");
                view.loadUrl(req, map);
                return true;
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                WebResourceResponse webResourceResponse = super.shouldInterceptRequest(view, url);
                return webResourceResponse;
            }

            //网络错误时回调的方法
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                dismiss();
                /**
                 * 在这里写网络错误时的逻辑,比如显示一个错误页面
                 *
                 * 这里我偷个懒不写了
                 * */
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });
    }

    private void initWebChromeClient() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.webView != null) {
            this.webView.removeAllViews();
            this.webView.destroy();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

}
