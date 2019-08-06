package com.windmillsteward.jukutech.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SpanUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMClientListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMMultiDeviceListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.chat.Constant;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.activity.chat.activity.ChatActivity;
import com.windmillsteward.jukutech.activity.chat.activity.GroupsActivity;
import com.windmillsteward.jukutech.activity.chat.fragment.ContactListFragment;
import com.windmillsteward.jukutech.activity.chat.fragment.ConversationListFragment;
import com.windmillsteward.jukutech.activity.chat.utils.EaseCommonUtils;
import com.windmillsteward.jukutech.activity.classification.fragment.ClassificationFragment;
import com.windmillsteward.jukutech.activity.customer.fragment.CustomerServiceFragment;
import com.windmillsteward.jukutech.activity.home.fragment.HomeFragmentThree;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyCouponActivity;
import com.windmillsteward.jukutech.activity.mine.activity.MyWalletActivity;
import com.windmillsteward.jukutech.activity.newpage.me.MeFragment;
import com.windmillsteward.jukutech.activity.newpage.order.HomeOrderFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.CheckVersionUpdateBean;
import com.windmillsteward.jukutech.bean.CityBean;
import com.windmillsteward.jukutech.bean.IsHaveHongBaoBean;
import com.windmillsteward.jukutech.bean.event.NotifyHomeLocation;
import com.windmillsteward.jukutech.bean.event.NotifyOrderUnReadCount;
import com.windmillsteward.jukutech.bean.event.NotifyReceivedMsgRefreshOrder;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.CheckVersionUtil;
import com.windmillsteward.jukutech.utils.CountDownUtils;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.ResUtils;
import com.windmillsteward.jukutech.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.AfterPermissionGranted;

/**
 * 描述：主activity
 * author:cyq
 * 2017-09-20
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MainActivity extends BaseActivity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener, CheckVersionUpdateView, MainView {
    private RadioGroup rg_arc_main;
    private Button rb_home;//首页
    public Button rb_order;//订单
    public Button rb_custormer;//客服
    private Button rb_conversation;//会话
    private Button rb_contactperson;//联系人
    private Button rb_mine;//我的
    private TextView unread_msg_number;//未读消息

    private LinearLayout lay_ll_bottom;
    private RelativeLayout lay_rl_root;

    private RelativeLayout lay_rl_hongbao;
    private ImageView iv_hongbao_bg;
    private TextView btn_hb_djs;
    private ImageView iv_hongbao_close;

    //    private HomeFragmentTwo mHomeFragment3;//首页
    private HomeFragmentThree mHomeFragment3;//首页2
    private ClassificationFragment mClassificationFragment;//分类
    private HomeOrderFragment mOrderFragment;//订单
    public CustomerServiceFragment mCustomerServiceFragment;//客服
    //    private MineFragment mMineFragment;//我的
    public MeFragment mMineFragment;//我的

    private CheckVersionUpdatePresenter checkUpdatePresenter;
    private MainPresenter mainPresenter;

    public static int width;//整个屏幕的宽度，提供给fragment做适配

    private String latitude;
    private String longitude;
    public String city = "";


    public String district = "";
    private long exitTime = 0;// 双击两次后退出程序的时间间隔
    private PopupWindow popupWindow;
    private PopupWindow moneyPopupWindow;
    private PopupWindow orderPopupWindow;
    public CountDownUtils countDownUtils;
    private long hb_start_time;
    private long hb_end_time;
    private long serverTime;
    private boolean isDjs;//是否在倒计时
    private Button btn_hb_djs_pop;//pop里面的抢红包按钮
    private ImageView iv_hongbao_bg_pop;//pop里面的抢红包按钮

    private android.app.AlertDialog.Builder exceptionBuilder;
    private boolean isExceptionDialogShow = false;
    private BroadcastReceiver internalDebugReceiver;
    private ConversationListFragment conversationListFragment;
    private ContactListFragment contactListFragment;
    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager broadcastManager;

    private boolean autoLogin = false;
    boolean isShowGoingOrder = true;//是否显示首页进行中订单弹框
    private int currIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arc_activity_main);
        setParamInt(R.id.lay_ll_content);

        registerBroadcastReceiver();
        EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
        EMClient.getInstance().addClientListener(clientListener);
        EMClient.getInstance().addMultiDeviceListener(new MyMultiDeviceListener());
        //debug purpose only
        registerInternalDebugReceiver();

        checkVersionUpdate();
        initFragment();
        initView();

        getAllData();
//        if (!DemoHelper.getInstance().isLoggedIn()) {
//            autoLogin = true;
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            return;
//        }
    }

    EMClientListener clientListener = new EMClientListener() {
        @Override
        public void onMigrate2x(boolean success) {
//            Toast.makeText(MainActivity.this, "onUpgradeFrom 2.x to 3.x " + (success ? "success" : "fail"), Toast.LENGTH_LONG).show();
            if (success) {
                refreshUIWithMessage();
            }
        }
    };


    /**
     * 判断服务器是否有优惠券红包和现金红包
     */
    public void getAllData() {
        if (!TextUtils.isEmpty(getAccessToken())) {
            if (mainPresenter == null) {
                mainPresenter = new MainPresenter(this);
            }
            mainPresenter.onGoingOrderNum(1);//获取进行中的订单数量
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!TextUtils.isEmpty(getAccessToken())) {
            mHomeFragment3.initUnReadMessage();
        }
    }

    private void initFragment() {
//        mHomeFragment = new HomeFragment();
        mHomeFragment3 = new HomeFragmentThree();
        mOrderFragment = new HomeOrderFragment();
//        mClassificationFragment = new ClassificationFragment();
//        mCustomerServiceFragment = new CustomerServiceFragment();
        conversationListFragment = new ConversationListFragment();
        contactListFragment = new ContactListFragment();
        mMineFragment = new MeFragment();
    }


    private void initView() {
//        rg_arc_main = (RadioGroup) findViewById(R.id.rg_arc_main);
        rb_home = (Button) findViewById(R.id.rb_home);
        rb_contactperson = (Button) findViewById(R.id.rb_contactperson);
        rb_conversation = (Button) findViewById(R.id.rb_conversation);
        rb_custormer = (Button) findViewById(R.id.rb_custormer);
        rb_order = (Button) findViewById(R.id.rb_order);
        rb_mine = (Button) findViewById(R.id.rb_mine);
        unread_msg_number = (TextView) findViewById(R.id.unread_msg_number);
        unread_msg_number.bringToFront();
//        rg_arc_main.setOnCheckedChangeListener(this);
        rb_home.setOnClickListener(this);
        rb_contactperson.setOnClickListener(this);
        rb_conversation.setOnClickListener(this);
        rb_custormer.setOnClickListener(this);
        rb_order.setOnClickListener(this);
        rb_mine.setOnClickListener(this);
//        rb_home.setChecked(true);
        changeButtonStatus(0);

        lay_ll_bottom = (LinearLayout) findViewById(R.id.lay_ll_bottom);
        lay_rl_root = (RelativeLayout) findViewById(R.id.lay_rl_root);
        lay_rl_hongbao = (RelativeLayout) findViewById(R.id.lay_rl_hongbao);
        iv_hongbao_close = (ImageView) findViewById(R.id.iv_hongbao_close);
        iv_hongbao_bg = (ImageView) findViewById(R.id.iv_hongbao_bg);
        btn_hb_djs = (TextView) findViewById(R.id.btn_hb_djs);

        iv_hongbao_close.setOnClickListener(this);

        width = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);

    }

    public void initPermision() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermission();
        }
    }

    /**
     * 检查更新
     */
    private void checkVersionUpdate() {
        if (checkUpdatePresenter == null) {
            checkUpdatePresenter = new CheckVersionUpdatePresenter(this);
        }
        checkUpdatePresenter.checkUpdate();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:// 首页2
                if (mHomeFragment3.isTop) {
                    ImmersionBar.with(this).reset().fitsSystemWindows(false).statusBarDarkFont(false, 0.2f).transparentStatusBar().init();
                } else {
                    ImmersionBar.with(this).reset().barColor(R.color.white).statusBarDarkFont(true, 0.2f).init();
                }
                startFragment(null, mHomeFragment3);
                break;
            case R.id.rb_custormer:// 客服
                ImmersionBar.with(this).reset().fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).init();
                startFragment(null, mCustomerServiceFragment);
                break;
            case R.id.rb_order:// 订单
                ImmersionBar.with(this).reset().fitsSystemWindows(true).statusBarDarkFont(true, 0.2f).barColor(R.color.white).init();
                startFragment(null, mOrderFragment);
                break;
            case R.id.rb_contactperson://联系人
                ImmersionBar.with(this).reset().fitsSystemWindows(true).statusBarColor(R.color.common_color).statusBarDarkFont(false, 0.2f).init();
                startFragment(null, contactListFragment);
                break;
            case R.id.rb_conversation:// 会话
                ImmersionBar.with(this).reset().fitsSystemWindows(true).statusBarColor(R.color.common_color).statusBarDarkFont(false, 0.2f).init();
                startFragment(null, conversationListFragment);
                break;
            case R.id.rb_mine:// 我
                ImmersionBar.with(this).reset().fitsSystemWindows(false).transparentStatusBar().init();
                startFragment(null, mMineFragment);
                break;
        }
    }

    @Override
    public boolean isNeedEvenbus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receivedMsgRefreshOrder(NotifyReceivedMsgRefreshOrder data) {
        unread_msg_number.setVisibility(View.VISIBLE);
        if (mOrderFragment != null){
            mOrderFragment.refresh();
        }
//        showTips(data.getType()+"<--------");
    }


    //刷新订单未读状态红点
    @Subscribe
    public void notifyPageRefresh(NotifyOrderUnReadCount data) {
        if (data.getTotalUnreadNum() > 0) {
            unread_msg_number.setVisibility(View.VISIBLE);
        } else {
            unread_msg_number.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_hongbao_close:
                lay_rl_hongbao.setVisibility(View.GONE);
                iv_hongbao_close.setVisibility(View.GONE);
                break;
            case R.id.rb_home:// 首页2
                currIndex = 0;
                changeButtonStatus(currIndex);
                break;
            case R.id.rb_custormer:// 客服
                currIndex = 1;
                changeButtonStatus(currIndex);
                break;
            case R.id.rb_order:// 订单
                currIndex = 2;
                changeButtonStatus(currIndex);
                break;
            case R.id.rb_contactperson://联系人
                currIndex = 4;
                changeButtonStatus(currIndex);
                break;
            case R.id.rb_conversation:// 会话
                currIndex = 3;
                changeButtonStatus(currIndex);
                break;
            case R.id.rb_mine:// 我
                currIndex = 5;
                changeButtonStatus(currIndex);
                break;
        }
    }

    public void changeButtonStatus(int index) {

        if (index == 0) {
            rb_home.setSelected(true);
            rb_order.setSelected(false);
            rb_custormer.setSelected(false);
            rb_conversation.setSelected(false);
            rb_contactperson.setSelected(false);
            rb_mine.setSelected(false);
            if (mHomeFragment3.isTop) {
                ImmersionBar.with(this).reset().fitsSystemWindows(false).statusBarDarkFont(false, 0.2f).transparentStatusBar().init();
            } else {
                ImmersionBar.with(this).reset().barColor(R.color.white).statusBarDarkFont(true, 0.2f).init();
            }
            startFragment(null, mHomeFragment3);
        } else if (index == 1) {
            rb_custormer.setSelected(true);
            rb_home.setSelected(false);
            rb_order.setSelected(false);
            rb_conversation.setSelected(false);
            rb_contactperson.setSelected(false);
            rb_mine.setSelected(false);
            ImmersionBar.with(this).reset().fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true, 0.2f).init();
            startFragment(null, mCustomerServiceFragment);
        } else if (index == 2) {
            rb_order.setSelected(true);
            rb_home.setSelected(false);
            rb_custormer.setSelected(false);
            rb_conversation.setSelected(false);
            rb_contactperson.setSelected(false);
            rb_mine.setSelected(false);
            ImmersionBar.with(this).reset().fitsSystemWindows(true).statusBarDarkFont(true, 0.2f).barColor(R.color.white).init();
            startFragment(null, mOrderFragment);
        } else if (index == 3) {
            rb_conversation.setSelected(true);
            rb_home.setSelected(false);
            rb_order.setSelected(false);
            rb_custormer.setSelected(false);
            rb_contactperson.setSelected(false);
            rb_mine.setSelected(false);
            ImmersionBar.with(this).reset().fitsSystemWindows(true).statusBarColor(R.color.common_color).statusBarDarkFont(false, 0.2f).init();
            startFragment(null, conversationListFragment);
        } else if (index == 4) {
            rb_contactperson.setSelected(true);
            rb_home.setSelected(false);
            rb_order.setSelected(false);
            rb_custormer.setSelected(false);
            rb_conversation.setSelected(false);
            rb_mine.setSelected(false);
            ImmersionBar.with(this).reset().fitsSystemWindows(true).statusBarColor(R.color.common_color).statusBarDarkFont(false, 0.2f).init();
            startFragment(null, contactListFragment);
        } else if (index == 5) {
            rb_mine.setSelected(true);
            rb_home.setSelected(false);
            rb_order.setSelected(false);
            rb_custormer.setSelected(false);
            rb_conversation.setSelected(false);
            rb_contactperson.setSelected(false);
            ImmersionBar.with(this).reset().fitsSystemWindows(false).transparentStatusBar().init();
            startFragment(null, mMineFragment);
        }
    }

    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // notify new message
            for (EMMessage message : messages) {
                DemoHelper.getInstance().getNotifier().vibrateAndPlayTone(message);
            }
            refreshUIWithMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            refreshUIWithMessage();
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            refreshUIWithMessage();
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
        }
    };

    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
//                updateUnreadLabel();
//                if (currentTabIndex == 0) {
                // refresh conversation list
                if (conversationListFragment != null) {
                    conversationListFragment.refresh();
                }
//                }
            }
        });
    }

    private void registerInternalDebugReceiver() {
        internalDebugReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                DemoHelper.getInstance().logout(false, new EMCallBack() {

                    @Override
                    public void onSuccess() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                finish();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        });
                    }

                    @Override
                    public void onProgress(int progress, String status) {
                    }

                    @Override
                    public void onError(int code, String message) {
                    }
                });
            }
        };
        IntentFilter filter = new IntentFilter(getPackageName() + ".em_internal_debug");
        registerReceiver(internalDebugReceiver, filter);
    }

    private void registerBroadcastReceiver() {
        broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_CONTACT_CHANAGED);
        intentFilter.addAction(Constant.ACTION_GROUP_CHANAGED);
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if (conversationListFragment != null) {
                    conversationListFragment.refresh();
                }
                if (contactListFragment != null) {
                    contactListFragment.refresh();
                }
                String action = intent.getAction();
                if (action.equals(Constant.ACTION_GROUP_CHANAGED)) {
                    if (EaseCommonUtils.getTopActivity(MainActivity.this).equals(GroupsActivity.class.getName())) {
                        GroupsActivity.instance.onResume();
                    }
                }
//                updateUnreadLabel();
//                updateUnreadAddressLable();
//                if (currentTabIndex == 0) {
//                    // refresh conversation list
//                    if (conversationListFragment != null) {
//                        conversationListFragment.refresh();
//                    }
//                } else if (currentTabIndex == 1) {
//                    if(contactListFragment != null) {
//                        contactListFragment.refresh();
//                    }
//                }
//                String action = intent.getAction();
//                if(action.equals(Constant.ACTION_GROUP_CHANAGED)){
//                    if (EaseCommonUtils.getTopActivity(MainActivity.this).equals(GroupsActivity.class.getName())) {
//                        GroupsActivity.instance.onResume();
//                    }
//                }
            }
        };
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    public class MyContactListener implements EMContactListener {
        @Override
        public void onContactAdded(String username) {
        }

        @Override
        public void onContactDeleted(final String username) {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (ChatActivity.activityInstance != null && ChatActivity.activityInstance.toChatUsername != null &&
                            username.equals(ChatActivity.activityInstance.toChatUsername)) {
                        String st10 = getResources().getString(R.string.have_you_removed);
                        Toast.makeText(MainActivity.this, ChatActivity.activityInstance.getToChatUsername() + st10, Toast.LENGTH_LONG)
                                .show();
                        ChatActivity.activityInstance.finish();
                    }
                }
            });
//            updateUnreadAddressLable();
        }

        @Override
        public void onContactInvited(String username, String reason) {
        }

        @Override
        public void onFriendRequestAccepted(String username) {
        }

        @Override
        public void onFriendRequestDeclined(String username) {
        }
    }

    public class MyMultiDeviceListener implements EMMultiDeviceListener {

        @Override
        public void onContactEvent(int event, String target, String ext) {

        }

        @Override
        public void onGroupEvent(int event, String target, final List<String> username) {
            switch (event) {
                case EMMultiDeviceListener.GROUP_LEAVE:
                    ChatActivity.activityInstance.finish();
                    break;
                default:
                    break;
            }
        }
    }

    private void unregisterBroadcastReceiver() {
        broadcastManager.unregisterReceiver(broadcastReceiver);
    }

    /**
     * 显示首页切换城市pop
     *
     * @param city
     * @param selectCity
     * @param list
     * @param district
     */
    public void showCityDialog(final String city, String selectCity, final List<CityBean> list, final String district) {
        //弹框，询问是否切换
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_confirm_cancel, null);
        // 设置按钮的点击事件
        TextView tv_title = (TextView) contentView.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) contentView.findViewById(R.id.tv_content);
        Button btn_confirm = (Button) contentView.findViewById(R.id.btn_confirm);
        Button btn_cancel = (Button) contentView.findViewById(R.id.btn_cancel);
        tv_title.setText("温馨提示");
        tv_content.setText(new SpanUtils()
                .append("您当前浏览的城市是 " + selectCity + "\n")
                .append("当前定位城市是 ")
                .setForegroundColor(ResUtils.getColor(R.color.color_aaaaaa))
                .append(city)
                .setForegroundColor(ResUtils.getCommRed())
                .append(",是否切换?")
                .setForegroundColor(ResUtils.getColor(R.color.color_aaaaaa))
                .create());
        btn_confirm.setText("暂不切换");
        btn_cancel.setText("立即切换");


        final PopupWindow popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 设置好参数之后再show
        popupWindow.showAtLocation(lay_rl_root, Gravity.CENTER, 0, 0);


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                Hawk.put(Define.CURR_CITY_THIRD_NAME, district);
                for (int i = 0; i < list.size(); i++) {
                    if (TextUtils.isEmpty(city)) {
                        return;
                    }
                    if (list.get(i).getArea_name().contains(city)) {
                        int area_id = list.get(i).getArea_id();
                        Hawk.put(Define.CURR_CITY_ID, area_id);
                        Hawk.put(Define.CURR_CITY_NAME, city);
                        mHomeFragment3.getAreaList();
                        break;
                    }
                }
            }
        });
    }

    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_hongbao, null);
        // 设置按钮的点击事件
        ImageView iv_hongbao = (ImageView) contentView.findViewById(R.id.iv_hongbao);
        ImageView iv_close = (ImageView) contentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
        iv_hongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.getHongBao();
            }
        });

        popupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 设置好参数之后再show
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }

    /**
     * 大屏红包
     */
    public void showMoneyHbPopupWindow() {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_money_hongbao, null);
        // 设置按钮的点击事件
        iv_hongbao_bg_pop = (ImageView) contentView.findViewById(R.id.iv_hongbao_bg);
        btn_hb_djs_pop = (Button) contentView.findViewById(R.id.btn_hb_djs);
        ImageView iv_close = (ImageView) contentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (moneyPopupWindow != null) {
                    moneyPopupWindow.dismiss();
                }
            }
        });
        btn_hb_djs_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDjs) {//不在倒计时才可以抢
                    mainPresenter.getMoneyHongBao();
                }
            }
        });
        if (isDjs) {//如果在倒计时
            iv_hongbao_bg_pop.setImageResource(R.mipmap.icon_big_hb_daojishi);
            btn_hb_djs_pop.setText("");
            setCountTime();
        } else {
            iv_hongbao_bg_pop.setImageResource(R.mipmap.icon_big_hb_msq);
            btn_hb_djs_pop.setText("马上抢红包");
        }

        moneyPopupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        moneyPopupWindow.setTouchable(true);
        moneyPopupWindow.setFocusable(true);
        moneyPopupWindow.setClippingEnabled(false);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        moneyPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 设置好参数之后再show
        moneyPopupWindow.showAtLocation(lay_rl_root, Gravity.CENTER, 0, 0);
    }

    /**
     * 正在进行中的订单popwindow
     *
     * @param num
     */
    public void showOnGoingOrderPopWindow(int num) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_on_going_order, null);

        TextView tv_title = (TextView) contentView.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) contentView.findViewById(R.id.tv_content);
        Button btn_confirm = (Button) contentView.findViewById(R.id.btn_confirm);
        Button btn_cancel = (Button) contentView.findViewById(R.id.btn_cancel);
        tv_title.setText("温馨提示");
        tv_content.setText(new SpanUtils()
                .append("您有").append(" " + num)
                .setForegroundColor(ResUtils.getCommRed())
                .append(" 个订单正在进行中")
                .setForegroundColor(ResUtils.getColor(R.color.color_aaaaaa))
                .create());
        btn_confirm.setText("取消");
        btn_cancel.setText("立即查看");

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderPopupWindow != null) {
                    orderPopupWindow.dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (orderPopupWindow != null) {
                            orderPopupWindow.dismiss();
                        }
                        currIndex = 1;
                        changeButtonStatus(currIndex);
                    }
                });
            }
        });

        // 设置按钮的点击事件
        orderPopupWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        orderPopupWindow.setTouchable(true);
        orderPopupWindow.setFocusable(true);
        orderPopupWindow.setClippingEnabled(false);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        orderPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 设置好参数之后再show
        orderPopupWindow.showAtLocation(lay_rl_root, Gravity.CENTER, 0, 0);
    }

    /**
     * 共用一个倒计时
     */
    private void setCountTime() {
        if (countDownUtils != null) {
            countDownUtils.setOnCountDownUpdateListener(new CountDownUtils.OnCountDownUpdateListener() {
                @Override
                public void countDownUpdate(final boolean isEnd, final int number) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isDjs = true;
                            btn_hb_djs.setText(String.format(Locale.getDefault(), "%s", TimeUtils.getLeftTime(number)));
                            if (btn_hb_djs_pop != null) {
                                btn_hb_djs_pop.setText(String.format(Locale.getDefault(), "%s", TimeUtils.getLeftTime(number)));
                            }
                            if (isEnd) {
                                if (btn_hb_djs_pop != null) {
                                    btn_hb_djs_pop.setText("马上抢红包");
                                }
                                if (iv_hongbao_bg_pop != null) {
                                    iv_hongbao_bg_pop.setImageResource(R.mipmap.icon_big_hb_msq);
                                }
                                isDjs = false;
                                countDownUtils.stopCountDown();
                                btn_hb_djs.setText("马上抢红包");
                            }
                        }
                    });
                }
            });
        }
    }


    @Override
    public void getOnGoingOrderNum(int num, int unReadNum) {
        if (isShowGoingOrder) {
            if (num > 0) {
                if (orderPopupWindow == null) {
                    showOnGoingOrderPopWindow(num);
                } else {
                    TextView tv_content = (TextView) orderPopupWindow.getContentView().findViewById(R.id.tv_content);
                    tv_content.setText(new SpanUtils()
                            .append("您有").append(" " + num)
                            .setForegroundColor(ResUtils.getCommRed())
                            .append(" 个订单正在进行中")
                            .setForegroundColor(ResUtils.getColor(R.color.color_aaaaaa))
                            .create());
                    orderPopupWindow.showAtLocation(lay_rl_root, Gravity.CENTER, 0, 0);
                }
            }
        }
        if (unReadNum > 0) {
            unread_msg_number.setVisibility(View.VISIBLE);
        } else {
            unread_msg_number.setVisibility(View.GONE);
        }

        //红包在最上面，所以先请求订单弹框
        mainPresenter.isGetHongBao();//优惠券红包
//        mainPresenter.isHaveHongBao();//现金红包，暂时屏蔽
    }


    @Override
    public void isHaveHongBao(IsHaveHongBaoBean bean) {
        if (bean != null) {
            int is_have = bean.getIs_have();
            hb_start_time = bean.getStart_time();
            hb_end_time = bean.getEnd_time();
            serverTime = bean.getTime();
//            hb_start_time = 1548750930;
//            is_have = 1;
            if (is_have == 1) {//有红包抢,获取倒计时时间
                //等于1有红包抢
                lay_rl_hongbao.setVisibility(View.VISIBLE);
                iv_hongbao_close.setVisibility(View.VISIBLE);
                if (hb_start_time >= serverTime) {//如果开始时间大于当前时间，则开始倒计时
                    isDjs = true;
                    iv_hongbao_bg.setImageResource(R.mipmap.icon_small_hb_daojishi);
                    btn_hb_djs.setText(String.format(Locale.getDefault(), "%s", TimeUtils.formatDuring(hb_start_time, TimeUtils.FORMAT_DURING_TYPE_ALL)));
                    Long aLong = hb_start_time - serverTime;
                    int count = Integer.valueOf(aLong + "");
                    countDownUtils = new CountDownUtils(count, 1, 1);
                    setCountTime();
                    countDownUtils.startCountDown();
                } else {//如果小于当前时间，则直接抢
                    isDjs = false;
                    btn_hb_djs.setText("马上抢红包");
                    iv_hongbao_bg.setImageResource(R.mipmap.icon_small_hb_msq);
                }
            } else {//没有红包抢
                lay_rl_hongbao.setVisibility(View.GONE);
                iv_hongbao_close.setVisibility(View.GONE);
            }
            lay_rl_hongbao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //显示大图红包，用popwindow
                    showMoneyHbPopupWindow();
                }
            });
        }

    }

    @Override
    public void getMoneyHongBao(String msg, IsHaveHongBaoBean bean) {
        if (bean != null) {
            int is_get = bean.getIs_get();
            if (is_get == 1) {
                if (moneyPopupWindow != null) {
                    moneyPopupWindow.dismiss();
                }
                startAtvDonFinish(MyWalletActivity.class);
                mainPresenter.isHaveHongBao();
            }
            showTips(msg);//抢没抢成功都提示
        }
    }

    @Override
    public void isGetCouponSuccess(int is_coupon) {
        if (is_coupon == 0) {
            if (popupWindow == null) {
                showPopupWindow(lay_rl_root);
            } else {
                popupWindow.showAtLocation(lay_rl_root, Gravity.CENTER, 0, 0);
            }
        }
    }

    @Override
    public void getCouponHongBaoSuccess() {
        //跳到优惠券列表
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        startAtvDonFinish(MyCouponActivity.class);
    }

    @Override
    public void getCheckVersionDataSuccess(CheckVersionUpdateBean bean) {
        if (bean == null) {
            return;
        }
        CheckVersionUtil.checkVersionUpdate(this, bean, false);
    }

    @Override
    public void getCheckVersionDataFailed(int code, String msg) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showTips("再按一次退出程序", 1);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
//                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        mHomeFragment3.initLocal();
    }

    @AfterPermissionGranted(Define.ALL_JURISDICTION_APPLY)
    private void getPermission() {
        // CAMERA 照相机
        // ACCESS_COARSE_LOCATION 通过WiFi或移动基站的方式获取用户错略的经纬度信息，定位精度大概误差在30~1500米
        // ACCESS_FINE_LOCATION 通过GPS芯片接收卫星的定位信息，定位精度达10米以内
        // WRITE_EXTERNAL_STORAGE 允许程序写入外部存储，如SD卡上写文件
        String[] perms = {android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA};

        checkPermission(perms);
    }
    // ------------------------------------权限检查结束代码------------------------------------

    @Override
    protected void onPause() {
        super.onPause();
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
        EMClient.getInstance().removeClientListener(clientListener);
        DemoHelper sdkHelper = DemoHelper.getInstance();
        sdkHelper.popActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DemoHelper sdkHelper = DemoHelper.getInstance();
        sdkHelper.pushActivity(this);
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    @Subscribe
    public void location(NotifyHomeLocation event) {
        if (mHomeFragment3 != null)
            mHomeFragment3.initLocal();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadcastReceiver();
        try {
            unregisterReceiver(internalDebugReceiver);
        } catch (Exception e) {
        }
    }
}
