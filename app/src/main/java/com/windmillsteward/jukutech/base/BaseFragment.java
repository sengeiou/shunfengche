package com.windmillsteward.jukutech.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.event.NotifyHomeLocation;
import com.windmillsteward.jukutech.customview.dialog.LoadingDialog;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * fragment基类
 *
 * @author zhuxian
 * @time 2016/10/9 18:22
 */
public class BaseFragment extends Fragment implements EasyPermissions.PermissionCallbacks {
    private LoadingDialog loadingDialog;//加载动画对话框
    private Bundle b;//fragment之间的切换传值
    public static final int standard = 1;//标准启动模式
    public static final int singleTask = 2;//单例模式
    private View contentView;
    public Intent intent;
    private BaseActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return container;
    }

    public void showContentView() {

    }


    public void showErrorView() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        intent = new Intent();
        super.onViewCreated(view, savedInstanceState);
    }


    /**
     * 获取切换fragment传过来的值
     *
     * @return
     */
    public Bundle getBundle() {
        return b;
    }

    /**
     * 切换fragment传值
     *
     * @param b
     */
    public void setBundle(Bundle b) {
        this.b = b;
    }

    /**
     * 镇充布局
     *
     * @param resource 布局ID
     * @return
     */
    public View inflateView(int resource) {
        return inflateView(resource, getActivity().getLayoutInflater());
    }

    /**
     * 镇充布局
     *
     * @param resource 布局ID
     * @return
     */
    public View inflateView(int resource, LayoutInflater inflater) {
        contentView = inflater.inflate(resource, null);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return contentView;
    }

    /**
     * fragment启动模式，singleTask和standard两种启动模式
     *
     * @return
     */
    public int registStartMode() {
        return standard;
    }

    /**
     * 按设定的时间长短提示
     *
     * @param content  提示内容
     * @param duration 设定的时间 单位毫秒
     */
    public void showTips(String content, int duration) {
        if (mActivity != null) {
            Toast toast = Toast.makeText(mActivity, content, duration);
//		toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    /**
     * 按设定的时间长短提示
     *
     * @param content 提示内容
     */
    public void showTips(String content) {
        showTips(content, Toast.LENGTH_SHORT);
    }

    /**
     * @param content 展示对话框的内容
     */
    public void showDialog(String content) {
        if (TextUtils.isEmpty(content)) {
            showDialog();
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getActivity());
        }
        loadingDialog.showLoading(content);
    }

    /**
     * 展示对话框的内容
     */
    public void showDialog() {
        showDialog(getResources().getString(R.string.loading));
    }

    /**
     * 取消对话框
     */
    public void dismiss() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getActivity());
        }
        loadingDialog.dismiss();
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
     * 界面跳转且关闭此界面
     *
     * @param cla 要开车去的地方
     */
    public void startAtvAndFinish(Class cla) {
        intent.setClass(getActivity(), cla);
        startActivity(intent);
        getActivity().finish();

        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转且关闭此界面
     *
     * @param cla 要开车去的地方
     */
    public void startAtvAndFinish(Class cla, Bundle bundle) {
        intent.setClass(getActivity(), cla);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().finish();

        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转不关闭此界面
     *
     * @param cla 要开车去的地方
     */
    public void startAtvDonFinish(Class cla) {
        intent.setClass(getActivity(), cla);
        startActivity(intent);
        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转不关闭此界面
     *
     * @param cla 要开车去的地方
     */
    public void startAtvDonFinish(Class cla, Bundle bundle) {
        intent.setClass(getActivity(), cla);
        intent.putExtras(bundle);
        startActivity(intent);
        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转且获取值
     *
     * @param cla 要开车去的地方
     */
    public void startAtvDonFinishForResult(Class cla, int code) {
        intent.setClass(getActivity(), cla);
        startActivityForResult(intent, code);

        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转且获取值
     *
     * @param cla 要开车去的地方
     */
    public void startAtvDonFinishForResult(Class cla, int code, Intent intent) {
        intent.setClass(getActivity(), cla);
        startActivityForResult(intent, code);
        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    /**
     * 界面跳转且获取值
     *
     * @param cla 要开车去的地方
     */
    public void startAtvDonFinishForResult(Class cla, Bundle bundle, int code) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cla);
        intent.putExtras(bundle);
        startActivityForResult(intent, code);

        LogUtil.e("startAtvDonFinish: 跳转到该界面： " + cla.getSimpleName());
    }

    private List<Callback.Cancelable> cancelableList = new ArrayList<>();

    /**
     * 添加请求到队列
     */
    public void addCall(Callback.Cancelable cancelable) {
        cancelableList.add(cancelable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        /**
         * 取消所有的请求
         */
        if (cancelableList != null){
            if (cancelableList.size() != 0){
                for (Callback.Cancelable cancelable : cancelableList) {
                    if (cancelable != null) {
                        if (!cancelable.isCancelled()) {
                            cancelable.cancel();
                        }
                    }
                }
            }
        }

    }

    /**
     * 检查权限
     *
     * @param permissions
     * @return
     */
    public boolean checkPermission(String[] permissions) {
        if (EasyPermissions.hasPermissions(getActivity(), permissions)) {// 有了
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms.contains(android.Manifest.permission.ACCESS_COARSE_LOCATION) ||
                perms.contains(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            //定位成功 通知首页开始定位
            EventBus.getDefault().post(new NotifyHomeLocation());
        }
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
                Toast.makeText(getActivity(), "当前系统不支持此方法,请手动设置应用权限", Toast.LENGTH_LONG).show();
            }
        }
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


}
