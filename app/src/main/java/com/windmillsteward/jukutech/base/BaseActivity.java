package com.windmillsteward.jukutech.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.hawk.Hawk;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.EaseUI;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.event.NotifyHomeLocation;
import com.windmillsteward.jukutech.customview.dialog.LoadingDialog;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.interfaces.MainLocationListener;
import com.windmillsteward.jukutech.utils.LogUtil;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.SystemUtil;
import com.windmillsteward.jukutech.utils.view.UtilHelpers;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * activity基类,所有的activity都需要求继承此基类
 *
 * @author zhuxian
 * @time 2016/10/9 18:23
 */
public class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private LoadingDialog loadingDialog;//加载动画对话框
    private Stack<BaseFragment> fragmentStack;//将fragment存进栈对列
    private FragmentManager fragmentManager;//fragment管理器
    private int paramInt;//用于存放fragment的布局id
    public Intent intent;
    public ImmersionBar mImmersionBar;

    protected InputMethodManager inputMethodManager;

    /**
     * 保存所有的请求 当结束的时候需要清理
     */
    private List<Callback.Cancelable> cancelableList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        AppManager.getAppManager().addActivity(this); // 添加Activity到堆栈
        intent = new Intent();
        mImmersionBar = ImmersionBar
                .with(this);//初始化
        mImmersionBar.statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).fitsSystemWindows(true).keyboardEnable(true).init();
        PushAgent.getInstance(this).onAppStart();

        if (isNeedEvenbus()) {
            EventBus.getDefault().register(this);
        }

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    /**
     * 添加到列表中
     *
     * @param cancelable
     */
    public void addCall(Callback.Cancelable cancelable) {
        cancelableList.add(cancelable);
    }

    @Override
    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
        MobclickAgent.onResume(this);
        EaseUI.getInstance().getNotifier().reset();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                UtilHelpers.hideKeyboard(ev, view, BaseActivity.this);//调用方法判断是否需要隐藏键盘
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 检查用户是否认证
     */
    public void checkUserAuthen(final OnUserAuthenListener listener) {
        if (isLogin()) {
            showDialog();
            addCall(new NetUtil().setUrl(APIS.URL_IS_AUTHEN)
                    .setCallBackData(new BaseNewNetModelimpl<AuthenResultBean>() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showTips(msg);
                            dismiss();
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo<AuthenResultBean> respnse, String source) {
                            dismiss();
                            AuthenResultBean bean = respnse.getData();
                            if (bean != null) {
                                if (bean.getIs_authen() == 1) {
                                    if (listener != null)
                                        listener.isAuthen();
                                } else if (bean.getIs_authen() == 0) {
                                    if (listener != null)
                                        listener.isNotAuthen();
//                                    startAtvDonFinish(PersonalAuthenticationActivity.class);
                                } else if (bean.getIs_authen() == 2) {
                                    showTips("客服正在审核认证信息，审核完成后可发布信息", 1);
                                }
                            }
                        }

                        @Override
                        protected Type getType() {
                            return new TypeReference<BaseResultInfo<AuthenResultBean>>() {
                            }.getType();
                        }
                    }).buildPost());
        } else {
            startAtvDonFinish(LoginActivity.class);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SystemUtil.dismissKeyBorwd(this);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {//解决fragment有时候context为空的情况
            outState.remove("android:support:fragments");
        }
    }

    //定位模块
    private LocationClient mLocClient;
    private CretinLocationListener locationListener;
    private MainLocationListener mainLocationListener;

    /**
     * 定位
     */
    public void getLocationOnce(MainLocationListener listener) {
        if (!chekcLocationPermission())
            return;
        showDialog("正在定位...");
        mainLocationListener = listener;
        mLocClient = new LocationClient(this);
        locationListener = new CretinLocationListener();

        mLocClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(0); // 大于1000才循环
//        option.setIsNeedAddress(true);//允许获取当前的位置信息，不开启则获取不了当前城市
//        option.setOpenGps(true); // 打开gps
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09
        option.setScanSpan(3000);//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
        option.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
        option.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocatio
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setOpenGps(true);//可选，默认false，设置是否开启Gps定位
        option.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    private class CretinLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            dismiss();
            if (location == null) {
                if (mainLocationListener != null)
                    mainLocationListener.locationFail("定位失败");
                return;
            }
            String latitude = String.valueOf(location.getLatitude());
            String longitude = String.valueOf(location.getLongitude());
            String address = location.getAddress().address;
            if (mainLocationListener != null) {
                mainLocationListener.locationSuccess(address, longitude + "," + latitude, location);
            }

            mLocClient.unRegisterLocationListener(locationListener);//注销定位

            if (StringUtil.isAllNotEmpty(longitude, latitude))
                KV.put(Define.CURR_LONGLAT_ADDRESS, longitude + "," + latitude);
        }

    }


    /**
     * 跳转manager
     *
     * @param managerActvityClass
     * @param tag
     */
    public void startManagerActivity(Class managerActvityClass, String tag) {
        Intent intent = new Intent(this, managerActvityClass);
        intent.putExtra(BackFragmentActivity.TAG_FRAGMENT, tag);
        startActivity(intent);
    }

    /**
     * 跳转manager
     *
     * @param managerActvityClass
     * @param tag
     * @param bundle
     */
    public void startManagerActivity(Class managerActvityClass, String tag, Bundle bundle) {
        Intent intent = new Intent(this, managerActvityClass);
        intent.putExtra(BackFragmentActivity.TAG_FRAGMENT, tag);
        if (bundle != null)
            intent.putExtra(BaseFragmentActivity.ARGS, bundle);
        startActivity(intent);
    }

    /**
     * 设置装fragment的layout
     *
     * @param paramInt 存放fragment的布局id
     */
    public void setParamInt(int paramInt) {
        this.paramInt = paramInt;
    }

    /**
     * 启动fragment 主要是应对tab效果
     *
     * @param b 需要传给fragment的内容
     */
    public void startFragment(Bundle b, BaseFragment f) {
        if (f == null)
            return;
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        if (fragmentStack == null) {
            fragmentStack = new Stack<BaseFragment>();
        }
        try {
            BaseFragment currentF = null;
            if (fragmentStack.size() > 0) {// 当前堆栈容量大于0，隐藏栈顶的对象
                FragmentTransaction t1 = fragmentManager.beginTransaction();//fragment事务
                currentF = fragmentStack.peek();
                t1.hide(currentF).commitAllowingStateLoss();
            }
            FragmentTransaction t = fragmentManager.beginTransaction();//fragment事务
            // 针对单例模式的fragment做处理
            if (fragmentStack.contains(f) && f.registStartMode() == BaseFragment.singleTask) {
                fragmentStack.remove(f);
                fragmentStack.push(f);
                f.setBundle(b);
                t.show(f).commitAllowingStateLoss();
                return;
            }
            f.setBundle(b);
            t.add(paramInt, f).commitAllowingStateLoss();
            fragmentStack.push(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAccessToken() {
        return Hawk.get(Define.ACCESS_TOKEN, "");
    }

    public boolean isLogin() {
        if (TextUtils.isEmpty(getAccessToken())) {
            return false;
        } else {
            return true;
        }
    }

    public int getCurrCityId() {
        return Hawk.get(Define.CURR_CITY_ID, 0);
    }

    public String getCurrCityName() {
        return Hawk.get(Define.CURR_CITY_NAME, "");
    }

    /**
     * 按设定的时间长短提示
     *
     * @param content  提示内容
     * @param duration 设定的时间 单位毫秒
     */
    public void showTips(String content, int duration) {
        Toast toast = Toast.makeText(this, content, duration);
//        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showTips(String content) {
        showTips(content, Toast.LENGTH_LONG);
    }

    /**
     * @param content 展示对话框的内容
     */
    public void showDialog(String content) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);//初始化对化框
        }
        loadingDialog.showLoading(content);
    }

    public void showDialog() {
        showDialog(getResources().getString(R.string.loading));
    }

    public void showContentView() {

    }


    public void showErrorView() {
    }

    /**
     * 取消对话框
     */
    public void dismiss() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);//初始化对化框
        }
        loadingDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态

        /**
         * 取消所有的请求
         */
        if (cancelableList != null) {
            for (Callback.Cancelable cancelable : cancelableList) {
                if (cancelable != null){
                    if (!cancelable.isCancelled()){
                        cancelable.cancel();
                    }
                }
            }
        }
        if (isNeedEvenbus()) {
            EventBus.getDefault().unregister(this);
        }
        if (fragmentStack != null) {//销毁fragment单列对象，和资源
            fragmentStack.clear();
        }
        if (mLocClient != null) {
            mLocClient.stop();
        }
        if (loadingDialog != null){
//            if (loadingDialog.isShow()){
//                loadingDialog.dismiss();
//            }
            loadingDialog = null;//回收
        }
        AppManager.getAppManager().finishActivity(this);// 结束Activity&从堆栈中移除

    }

    /**
     * 界面跳转且关闭此界面
     *
     * @param cla 要开车去的地方
     */
    public void startAtvAndFinish(Class cla) {
        intent.setClass(this, cla);
        startActivity(intent);
        finish();
        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转且关闭此界面
     *
     * @param cla 要开车去的地方
     */
    public void startAtvAndFinish(Class cla, Bundle bundle) {
        intent.setClass(this, cla);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转不关闭此界面
     *
     * @param cla 要开车去的地方
     */
    public void startAtvDonFinish(Class cla) {
        intent.setClass(this, cla);
        startActivity(intent);
        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转不关闭此界面
     *
     * @param cla 要开车去的地方
     */
    public void startAtvDonFinish(Class cla, Bundle bundle) {
        intent.setClass(this, cla);
        intent.putExtras(bundle);
        startActivity(intent);
        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 带数据返回的界面跳转
     *
     * @param cla         类
     * @param requestCode 跳转码
     * @return null
     * @author lc
     * @time 2016/9/28 18:10
     */
    public void startAtvDonFinishForResult(Class cla, int requestCode) {
        intent.setClass(this, cla);
        startActivityForResult(intent, requestCode);

        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 带数据返回的界面跳转
     *
     * @param cla         类
     * @param requestCode 跳转码
     * @return null
     * @author lc
     * @time 2016/9/28 18:10
     */
    public void startAtvDonFinishForResult(Class cla, int requestCode, Bundle bundle) {
        intent.setClass(this, cla);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);

        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转且获取值
     *
     * @param cla 要开车去的地方
     */
    public void startAtvDonFinishForResult(Class cla, int code, Intent intent) {
        intent.setClass(this, cla);
        startActivityForResult(intent, code);

        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 处理标题栏的返回事件
     * 直接返回
     *
     * @param v view
     */
    public void handleBackEvent(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 处理标题栏的返回事件
     * 返回带参数
     *
     * @param v view
     */
    public void handleBackEventData(View v, final int resultCode, final Intent data) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(resultCode, data);
                finish();
            }
        });
    }

    public boolean isNeedEvenbus() {
        return false;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms.contains(android.Manifest.permission.ACCESS_COARSE_LOCATION) ||
                perms.contains(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            //定位成功 通知首页开始定位
            EventBus.getDefault().post(new NotifyHomeLocation());
        }
    }

    /**
     * 检查权限
     *
     * @param permissions
     * @return
     */
    public boolean checkPermission(String[] permissions) {
        if (EasyPermissions.hasPermissions(this, permissions)) {// 有了
            return true;
        } else {
            // 还没
            EasyPermissions.requestPermissions(this, getPerDesc(Arrays.asList(permissions)) + "未授予"
                            + ",继续运行可能会崩溃",
                    Define.ALL_JURISDICTION_APPLY, permissions);
        }
        return false;
    }

    /**
     * 检查定位权限
     *
     * @return
     */
    public boolean chekcLocationPermission() {
        String[] perms = {android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION};
        return checkPermission(perms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 获取权限描述
     *
     * @param perms
     * @return
     */
    private String getPerDesc(List<String> perms) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < perms.size(); i++) {
            String s = perms.get(i);
            switch (s) {
                case android.Manifest.permission.CAMERA:// 照相机
                    stringBuffer.append("照相机" + "、");
                    break;
                case android.Manifest.permission.ACCESS_COARSE_LOCATION:// 通过WiFi或移动基站的方式获取用户错略的经纬度信息，定位精度大概误差在30~1500米
                    stringBuffer.append("定位" + "、");
                    break;
                case android.Manifest.permission.ACCESS_FINE_LOCATION:// 通过GPS芯片接收卫星的定位信息，定位精度达10米以内
                    stringBuffer.append("GPS" + "定位" + "、");
                    break;
                case android.Manifest.permission.WRITE_EXTERNAL_STORAGE:// 允许程序写入外部存储，如SD卡上写文件
                    stringBuffer.append("SD卡" + "、");
                    break;
                case android.Manifest.permission.READ_PHONE_STATE:// 访问电话状态
                    stringBuffer.append("拨号、");
                    break;
                case android.Manifest.permission.READ_CONTACTS:// 允许应用访问联系人通讯录信息
                    stringBuffer.append("通讯录、");
                    break;
            }
        }
        if (stringBuffer.toString().endsWith("、")) {
            return stringBuffer.toString().substring(0, stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        // 某些权限被拒绝
        //（可选）检查用户是否拒绝任何权限，并检查“不要再询问”。
        // 这将显示一个对话框，指示他们在应用设置中启用权限。
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            try {
                new AppSettingsDialog.Builder(this).setTitle("需要权限").setRationale(getPerDesc(perms) + "权限未授予"
                        + "继续运行可能会崩溃").build().show();
            } catch (java.lang.Exception Exception) {
                Toast.makeText(this, "当前系统不支持此方法,请手动设置应用权限", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
