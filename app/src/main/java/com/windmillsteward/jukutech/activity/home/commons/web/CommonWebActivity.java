package com.windmillsteward.jukutech.activity.home.commons.web;

import android.os.Build;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;


/**
 * 描述：
 * 时间：2018/3/10/010
 * 作者：xjh
 */
public class CommonWebActivity extends BaseActivity {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private WebView webView;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            url = extras.getString(Define.INTENT_DATA);
        } else {
            finish();
            return;
        }
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setBlockNetworkImage(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }
        });
        showDialog("");
        this.webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                dismiss();
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismiss();
                toolbar_iv_title.setText(webView.getTitle());
            }
        });
//        this.webView.loadUrl("https://www2.shunfengcheguanjia.com/windmillsteward/user_api/user_center/share_register?user_id=15067");
        this.webView.loadUrl(url);

    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        webView = (WebView) findViewById(R.id.webView);

        handleBackEvent(toolbar_iv_back);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            this.webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.webView != null) {
            this.webView.removeAllViews();
            this.webView.destroy();
        }
    }

}
