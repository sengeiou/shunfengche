package com.windmillsteward.jukutech.activity.customer.fragment;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.customer.activity.CustomerListView;
import com.windmillsteward.jukutech.activity.customer.presenter.CustomerListPresenter;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：客服
 * author:cyq
 * 2017-09-20
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class CustomerServiceFragment extends BaseFragment implements CustomerListView {

    //图片
    private final static int FILE_CHOOSER_RESULT_CODE = 128;
    //拍照
    private final static int FILE_CAMERA_RESULT_CODE = 129;

    private CustomerListPresenter presenter;

    private WebView webView;

    //5.0以下使用
    private ValueCallback<Uri> uploadMessage;
    // 5.0及以上使用
    private ValueCallback<Uri[]> uploadMessageAboveL;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateView(R.layout.fragment_customer_service_new);
        initView(view);
        initData();
        return view;
    }


    private void initView(View view) {
        webView = (WebView) view.findViewById(R.id.webView);
    }

    private void initData() {
        presenter = new CustomerListPresenter(this);
        if (!TextUtils.isEmpty(getAccessToken())){
            presenter.getCustomerUrl(1, KV.get(Define.CURR_CITY_THIRD_ID,0), getAccessToken());
        }

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            this.webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("custom://refreshPage/")) {//刷新页面
//                    webView.reload();
                    return true;
                } else if (url.contains("custom://hideBack/")) {//进入聊天页面隐藏底部
//                    MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
//                    activity.hideBottom(true);
                    return true;
                } else if (url.contains("custom://chatBack/")) {//返回聊天列表显示底部
//                    MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
//                    activity.hideBottom(false);
                    return true;
                }
                return true;
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                dismiss();
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismiss();

            }
        });
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebChromeClient(new WebChromeClient() {
            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android  >= 3.0
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            //For Android  >= 4.1
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                openImageChooserActivity();
                return true;
            }
        });
    }

    private void openImageChooserActivity() {
        takePhoto();
    }

    //选择图片
    private void takePhoto() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null == uploadMessage && null == uploadMessageAboveL) return;
        if (resultCode != getActivity().RESULT_OK) {//同上所说需要回调onReceiveValue方法防止下次无法响应js方法
            if (uploadMessageAboveL != null) {
                uploadMessageAboveL.onReceiveValue(null);
                uploadMessageAboveL = null;
            }
            if (uploadMessage != null) {
                uploadMessage.onReceiveValue(null);
                uploadMessage = null;
            }
            return;
        }
        Uri result = null;
      if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (data != null) {
                result = data.getData();
            }
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        }
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(Intent intent) {
        Uri[] results = null;
        if (intent != null) {
            String dataString = intent.getDataString();
            ClipData clipData = intent.getClipData();
            if (clipData != null) {
                results = new Uri[clipData.getItemCount()];
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    results[i] = item.getUri();
                }
            }
            if (dataString != null)
                results = new Uri[]{Uri.parse(dataString)};
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            if (!TextUtils.isEmpty(getAccessToken())){
                presenter.getCustomerUrl(1, KV.get(Define.CURR_CITY_THIRD_ID,0), getAccessToken());
            }
        }
    }



    @Override
    public void getCustomerUrlSuccess(String url) {
        if (TextUtils.isEmpty(url)){
            return;
        }
        webView.loadUrl(url);
    }


    @Override
    public void getCustomerUrlFailed(int code, String msg) {
        showTips(msg);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.webView != null) {
            this.webView.removeAllViews();
            this.webView.destroy();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    public int registStartMode() {
        return singleTask;
    }

}