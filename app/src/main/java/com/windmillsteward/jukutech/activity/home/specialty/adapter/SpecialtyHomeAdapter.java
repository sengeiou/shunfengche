package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.HomeGuessYouLikeAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyHomeActivity;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.bean.GuessYouLikeBean;
import com.windmillsteward.jukutech.bean.HomeBean;
import com.windmillsteward.jukutech.bean.SeckillBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.CountDownUtils;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 描述：特产首页recyclerview适配器
 * author:cyq
 * 2018-06-08
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SpecialtyHomeAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<HomeBean> list;
    public CountDownUtils countDownUtils;

    private RecyclerViewDataCallBack mDataCallBack;

    private BannerViewHolder topBannerViewHolder;
    private ViewPageViewHolder viewPageViewHolder;

    private RecommendGridViewHolder recommendGridViewHolder;
    private GuessYouLikeGridViewHolder guessYouLikeGridViewHolder;
    private SeckillViewHolder seckillViewHolder;

    private List<SliderPictureInfo> bannerList = new ArrayList<>();
    private List<SpecialtyClassBean> specialtyClassBeanList = new ArrayList<>();
    private List<SpecialtyHomeRecommendBean> recommendGridBeanList = new ArrayList<>();
    private GuessYouLikeBean guessYouLikeBean = new GuessYouLikeBean();
    private SeckillBean seckillBean = new SeckillBean();

    public static final int BANNER = 100;//轮播图
    public static final int CLASS_VIEW_PAGE = 101;//商品分类
    public static final int SECKILL = 102;//秒杀
    public static final int RECOMMEND_GRID = 103;//推荐
    public static final int GUESS_YOU_LIKE = 104;//猜你喜欢
    public static final int BOTTOM = 105;//底部

    public SpecialtyHomeAdapter(Context context, List<HomeBean> list, RecyclerViewDataCallBack mDataCallBack) {
        this.context = context;
        this.list = list;
        this.mDataCallBack = mDataCallBack;
    }

    /**
     * 顶部banner
     *
     * @param list
     */
    public void updateTopBanner(List<SliderPictureInfo> list) {
        if (topBannerViewHolder == null) {
            return;
        }
        this.bannerList = list;
        topBannerViewHolder.setFlyBanner(1, list);
    }


    /**
     * 推荐列表
     *
     * @param list
     */
    public void updateRecommendList(List<SpecialtyHomeRecommendBean> list) {
        if (recommendGridViewHolder == null) {
            return;
        }
        recommendGridViewHolder.setRecommendData(list);
    }

    /**
     * 更新猜你喜欢列表
     *
     * @param bean
     */
    public void updateGuessYouLike(GuessYouLikeBean bean) {
        if (recommendGridViewHolder == null) {
            return;
        }
        guessYouLikeGridViewHolder.setGuessYouLikeData(bean);
    }

    /**
     * 更新商品分类列表
     *
     * @param specialtyClassBeanList
     */
    public void updateViewPageData(List<SpecialtyClassBean> specialtyClassBeanList) {
        if (viewPageViewHolder == null) {
            return;
        }
        this.specialtyClassBeanList = specialtyClassBeanList;
        viewPageViewHolder.setViewPageData(specialtyClassBeanList);
    }

    /**
     * 秒杀列表
     *
     * @param seckillBean
     */
    public void updateSeckillData(SeckillBean seckillBean) {
        if (seckillViewHolder == null) {
            return;
        }
        this.seckillBean = seckillBean;
        seckillViewHolder.setSeckillData(seckillBean);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case BANNER:
                view = LayoutInflater.from(context).inflate(R.layout.home_top_banner, parent, false);
                topBannerViewHolder = new BannerViewHolder(view);
                return topBannerViewHolder;
            case CLASS_VIEW_PAGE:
                view = LayoutInflater.from(context).inflate(R.layout.specialty_home_viewpage, parent, false);
                viewPageViewHolder = new ViewPageViewHolder(view);
                return viewPageViewHolder;
            case RECOMMEND_GRID:
                view = LayoutInflater.from(context).inflate(R.layout.specialty_home_recommend, parent, false);
                recommendGridViewHolder = new RecommendGridViewHolder(view);
                return recommendGridViewHolder;
            case GUESS_YOU_LIKE:
                view = LayoutInflater.from(context).inflate(R.layout.home_guess_you_like, parent, false);
                guessYouLikeGridViewHolder = new GuessYouLikeGridViewHolder(view);
                return guessYouLikeGridViewHolder;
            case BOTTOM:
                view = LayoutInflater.from(context).inflate(R.layout.home_bottom, parent, false);
                return new BottomViewHolder(view);
            case SECKILL:
                view = LayoutInflater.from(context).inflate(R.layout.specialty_home_seckill_list, parent, false);
                seckillViewHolder = new SeckillViewHolder(view);
                return seckillViewHolder;
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            bannerHolder.setFlyBanner(1, bannerList);
        } else if (position == 1) {
            ViewPageViewHolder viewPageViewHolder = (ViewPageViewHolder) holder;
            viewPageViewHolder.setViewPageData(specialtyClassBeanList);
        } else if (position == 2) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setSeckillData(seckillBean);
        } else if (position == 3) {
            RecommendGridViewHolder recommendGridViewHolder = (RecommendGridViewHolder) holder;
            recommendGridViewHolder.setRecommendData(recommendGridBeanList);
        } else if (position == 4) {
            GuessYouLikeGridViewHolder guessYouLikeGridViewHolder = (GuessYouLikeGridViewHolder) holder;
            guessYouLikeGridViewHolder.setGuessYouLikeData(guessYouLikeBean);
        }

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        HomeBean homeBean = list.get(position);
        String viewType = homeBean.getViewType();
        switch (viewType) {
            case Define.BANNER:

                return BANNER;
            case Define.CLASS_VIEW_PAGE:

                return CLASS_VIEW_PAGE;
            case Define.SECKILL:

                return SECKILL;
            case Define.RECOMMEND_GRID:

                return RECOMMEND_GRID;
            case Define.GUESS_YOU_LIKE:

                return GUESS_YOU_LIKE;
            case Define.BOTTOM:

                return BOTTOM;
            default:
                return position;
        }

    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        FlyBanner flyBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);

            flyBanner = (FlyBanner) itemView.findViewById(R.id.fl_banner);
            ArrayList<Integer> images = new ArrayList<>();
            images.add(R.mipmap.icon_default_banner);
            flyBanner.setImages(images);
        }

        public void setFlyBanner(int type, final List<SliderPictureInfo> list) {

            ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
            int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
            layoutParams.width = screenWH;
            layoutParams.height = 575 * layoutParams.width / 1080;
            flyBanner.setLayoutParams(layoutParams);

            List<String> picList = new ArrayList<>();
            for (SliderPictureInfo info : list) {
                String pic_url = info.getPic_url();
                picList.add(pic_url);
            }

            if (picList.size() > 0) {

                flyBanner.setImagesUrl(picList);

            }
            flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (list.size() == 0) {
                        return;
                    }
                    SliderPictureInfo sliderPictureInfo = list.get(position);
                    mDataCallBack.setOnBannerClick(sliderPictureInfo, position);
                }
            });

        }
    }

    public class ViewPageViewHolder extends RecyclerView.ViewHolder {

        private ViewPager mViewpager;
        private LinearLayout lay_ll_points;
        private ImageView[] ivPoints;//小圆点图片的集合
        private int totalPage; //总的页数
        private int mPageSize = 10; //每页显示的最大的数量
        private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中

        public ViewPageViewHolder(View itemView) {
            super(itemView);
            mViewpager = (ViewPager) itemView.findViewById(R.id.mViewpager);
            lay_ll_points = (LinearLayout) itemView.findViewById(R.id.lay_ll_points);
        }

        public void setViewPageData(List<SpecialtyClassBean> specialtyClassBeanList) {
            if (specialtyClassBeanList == null || specialtyClassBeanList.size() == 0) {
                return;
            }
            lay_ll_points.removeAllViews();
            totalPage = (int) Math.ceil(specialtyClassBeanList.size() * 1.0 / mPageSize);
            viewPagerList = new ArrayList<View>();
            for (int i = 0; i < totalPage; i++) {
                //每个页面都是inflate出一个新实例
                final GridView gridView = (GridView) View.inflate(context, R.layout.specialty_home_viewpage_gridview, null);
                gridView.setAdapter(new SpecialtyMenuGridViewAdapter(context, specialtyClassBeanList, i, mPageSize));
                //添加item点击监听
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long arg3) {
                        // TODO Auto-generated method stub
                        Object obj = gridView.getItemAtPosition(position);
                        if (obj != null && obj instanceof SpecialtyClassBean) {
//                                            Toast.makeText(MyActivity.this, ((ProdctBean)obj).getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //每一个GridView作为一个View对象添加到ViewPager集合中
                viewPagerList.add(gridView);
            }
            //设置ViewPager适配器
            mViewpager.setAdapter(new MyMenuViewPageAdapter(viewPagerList));

            //添加小圆点
            ivPoints = new ImageView[totalPage];
            for (int i = 0; i < totalPage; i++) {
                //循坏加入点点图片组
                ivPoints[i] = new ImageView(context);
                if (i == 0) {
                    ivPoints[i].setImageResource(R.mipmap.icon_step_g);
                } else {
                    ivPoints[i].setImageResource(R.mipmap.icon_step);
                }
                ivPoints[i].setPadding(8, 8, 8, 8);
                lay_ll_points.addView(ivPoints[i]);
            }
            //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
            mViewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    // TODO Auto-generated method stub
                    //currentPage = position;
                    for (int i = 0; i < totalPage; i++) {
                        if (i == position) {
                            ivPoints[i].setImageResource(R.mipmap.icon_step_g);
                        } else {
                            ivPoints[i].setImageResource(R.mipmap.icon_step);
                        }
                    }
                }
            });

        }
    }

    public class RecommendGridViewHolder extends RecyclerView.ViewHolder {

        //        private MyExpandableListView elv_content;
        private RecyclerView mRecyclerView;


        public RecommendGridViewHolder(View itemView) {
            super(itemView);
//            elv_content = (MyExpandableListView) itemView.findViewById(R.id.elv_content);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.mRecyclerView);

        }

        public void setRecommendData(List<SpecialtyHomeRecommendBean> list) {
            if (list == null || list.size() == 0) {
                return;
            }

            SpecialtyRecommendGroupAdapter adapter = new SpecialtyRecommendGroupAdapter(context, list, mDataCallBack);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        }

    }

    public class GuessYouLikeGridViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        MyGridView gv_content;
        RelativeLayout lay_rl_bg;

        public GuessYouLikeGridViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            gv_content = (MyGridView) itemView.findViewById(R.id.gv_content);
            lay_rl_bg = (RelativeLayout) itemView.findViewById(R.id.lay_rl_bg);
        }

        public void setGuessYouLikeData(GuessYouLikeBean bean) {
            List<GuessYouLikeBean.ListBean> list = bean.getList();
            String list_name = bean.getList_name();
            if (list == null || list.size() == 0) {
                lay_rl_bg.setVisibility(View.GONE);
            } else {
                lay_rl_bg.setVisibility(View.VISIBLE);
                tv_title.setText(TextUtils.isEmpty(list_name) ? "" : list_name);
            }

            HomeGuessYouLikeAdapter adapter = new HomeGuessYouLikeAdapter(context, bean.getList());
            gv_content.setAdapter(adapter);
            gv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GuessYouLikeBean.ListBean item = (GuessYouLikeBean.ListBean) parent.getAdapter().getItem(position);

                    mDataCallBack.setOnGuessYouLikeClick(item);

                }
            });
        }
    }

    /**
     * 底部item
     */
    public class BottomViewHolder extends RecyclerView.ViewHolder {

        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 秒杀item
     */
    public class SeckillViewHolder extends RecyclerView.ViewHolder implements CountDownUtils.OnCountDownUpdateListener {

        private TextView tv_kill_time;
        private TextView tv_more;

        private ImageView[] ivPoints;//小圆点图片的集合
        private ViewPager mViewpager;
        private LinearLayout lay_ll_points;

        private int totalPage; //总的页数
        private int mPageSize = 4; //每页显示的最大的数量
        private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
        private long end_time = 0;
        private long start_time;
        private boolean is_current_list_seckill_start;// 当前列表是否开始秒杀 true开始秒杀  false还没开始秒杀

        public SeckillViewHolder(View itemView) {
            super(itemView);
            mViewpager = (ViewPager) itemView.findViewById(R.id.mViewpager);
            tv_kill_time = (TextView) itemView.findViewById(R.id.tv_kill_time);
            tv_more = (TextView) itemView.findViewById(R.id.tv_more);
            lay_ll_points = (LinearLayout) itemView.findViewById(R.id.lay_ll_points);
        }

        public void setSeckillData(SeckillBean bean) {
            if (bean == null || bean.getList() == null) {
                return;
            }
            //倒计时
            start_time = bean.getStart_time();
            end_time = bean.getEnd_time();
            long currentTimesTamp = TimeUtils.getCurrentTimesTamp();
            if (currentTimesTamp < start_time) {// 如果当前时间小于开始时间就是 即将开始 否则就是 正在抢购
                is_current_list_seckill_start = false;
                tv_kill_time.setText(String.format(Locale.getDefault(), "%s", TimeUtils.formatDuring(start_time, TimeUtils.FORMAT_DURING_TYPE_ALL)));
                if (countDownUtils == null) {
                    long current_time = System.currentTimeMillis();
                    Long aLong = start_time - current_time / 1000;
                    int count = Integer.valueOf(aLong + "");
                    countDownUtils = new CountDownUtils(count, 1, 1);
                    countDownUtils.setOnCountDownUpdateListener(this);
                }
                countDownUtils.startCountDown();
            } else {
                is_current_list_seckill_start = true;
                tv_kill_time.setText(String.format(Locale.getDefault(), "%s", TimeUtils.formatDuring(end_time, TimeUtils.FORMAT_DURING_TYPE_ALL)));
                if (countDownUtils == null) {
                    long current_time = System.currentTimeMillis();
                    Long aLong = end_time - current_time / 1000;
                    int count = Integer.valueOf(aLong + "");
                    countDownUtils = new CountDownUtils(count, 1, 1);
                    countDownUtils.setOnCountDownUpdateListener(this);
                }
                countDownUtils.startCountDown();
            }

            lay_ll_points.removeAllViews();
            totalPage = (int) Math.ceil(bean.getList().size() * 1.0 / mPageSize);
            viewPagerList = new ArrayList<View>();
            for (int i = 0; i < totalPage; i++) {
                //每个页面都是inflate出一个新实例
                final GridView gridView = (GridView) View.inflate(context, R.layout.specialty_seckill_viewpage_gridview, null);
                gridView.setAdapter(new SpecialtySeckillMenuGridViewAdapter(context, bean.getList(), i, mPageSize, mDataCallBack));
                //添加item点击监听
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long arg3) {
                        // TODO Auto-generated method stub
//                        SeckillBean.ListBean bean = (SeckillBean.ListBean)gridView.getItemAtPosition(position);
//                        mDataCallBack.setOnSeckillGoodsDetailClick(bean);

                    }
                });
                //每一个GridView作为一个View对象添加到ViewPager集合中
                viewPagerList.add(gridView);
            }
            //设置ViewPager适配器
            mViewpager.setAdapter(new MyMenuViewPageAdapter(viewPagerList));

            //添加小圆点
            ivPoints = new ImageView[totalPage];
            for (int i = 0; i < totalPage; i++) {
                //循坏加入点点图片组
                ivPoints[i] = new ImageView(context);
                if (i == 0) {
                    ivPoints[i].setImageResource(R.mipmap.icon_step_g);
                } else {
                    ivPoints[i].setImageResource(R.mipmap.icon_step);
                }
                ivPoints[i].setPadding(8, 8, 8, 8);
                lay_ll_points.addView(ivPoints[i]);
            }
            if (totalPage == 1) {
                lay_ll_points.setVisibility(View.GONE);
            } else {
                lay_ll_points.setVisibility(View.VISIBLE);
            }
            //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
            mViewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    // TODO Auto-generated method stub
                    //currentPage = position;
                    for (int i = 0; i < totalPage; i++) {
                        if (i == position) {
                            ivPoints[i].setImageResource(R.mipmap.icon_step_g);
                        } else {
                            ivPoints[i].setImageResource(R.mipmap.icon_step);
                        }
                    }
                }
            });
            tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDataCallBack.setOnSeckillMoreClick(seckillBean);
                }
            });

        }

        @Override
        public void countDownUpdate(final boolean isEnd, final int number) {
            SpecialtyHomeActivity activity = (SpecialtyHomeActivity) AppManager.getAppManager().getActivity(SpecialtyHomeActivity.class);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_kill_time.setText(String.format(Locale.getDefault(), "%s", TimeUtils.getLeftTime(number)));
                    if (isEnd) {
                        if (is_current_list_seckill_start) {// 当前列表是否开始秒杀 true开始秒杀  false还没开始秒杀
                            countDownUtils.stopCountDown();//activity被杀掉的时候也要执行这行代码
                            tv_kill_time.setText("00:00:00");
                        } else {
                            is_current_list_seckill_start = true;
                            tv_kill_time.setText(String.format(Locale.getDefault(), "%s", TimeUtils.formatDuring(end_time, TimeUtils.FORMAT_DURING_TYPE_ALL)));
                            long current_time = System.currentTimeMillis();
                            Long aLong = end_time - current_time / 1000;
                            int count = Integer.valueOf(aLong + "");
                            countDownUtils = new CountDownUtils(count, 1, 1);
                            countDownUtils.setOnCountDownUpdateListener(SeckillViewHolder.this);
                            countDownUtils.startCountDown();
                        }
                    }


                }
            });
        }
    }

    public interface RecyclerViewDataCallBack {
        //banner图
        void setOnBannerClick(SliderPictureInfo data, int position);

        //猜你喜欢
        void setOnGuessYouLikeClick(GuessYouLikeBean.ListBean item);

        //推荐
        void setOnRecommendClick(SpecialtyHomeRecommendBean.ListBean listBean);

        //推荐更多
        void setOnRecommendMoreClick(SpecialtyHomeRecommendBean recommendGridBean);

        //秒杀商品详情点击
        void setOnSeckillGoodsDetailClick(SeckillBean.ListBean bean);

        //秒杀更多点击
        void setOnSeckillMoreClick(SeckillBean seckillBean);
    }

}

