package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;
import com.gongwen.marqueen.util.OnItemClickListener;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.GuessYouLikeBean;
import com.windmillsteward.jukutech.bean.HomeBean;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;
import com.windmillsteward.jukutech.bean.PublicSelectInfo;
import com.windmillsteward.jukutech.bean.RecommendGridBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.TravelRecommendBean;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.MyExpandableListView;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：首页recyclerview适配器
 * author:cyq
 * 2018-05-19
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<HomeBean> list;

    private RecyclerViewDataCallBack mDataCallBack;

    private BannerViewHolder topBannerViewHolder;
    private GridViewHolder gridViewHolder;
    private MarqueeViewHolder marqueeViewHolder;
    private BannerViewHolder middleBannerViewHolder;
    private RecommendGridViewHolder recommendGridViewHolder;
    private GuessYouLikeGridViewHolder guessYouLikeGridViewHolder;
//    private TravelViewHolder travelViewHolder;

    private List<SliderPictureInfo> topBannerList = new ArrayList<>();
    private List<SliderPictureInfo> middleBannerList = new ArrayList<>();
    private List<RecommendGridBean> recommendGridBeanList = new ArrayList<>();
    private GuessYouLikeBean guessYouLikeBean = new GuessYouLikeBean();
    private HouseKeeperDataQuickBean houseKeeperDataQuickBean = new HouseKeeperDataQuickBean();

    public HomeRecyclerViewAdapter(Context context, List<HomeBean> list, RecyclerViewDataCallBack mDataCallBack) {
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
        this.topBannerList = list;
        topBannerViewHolder.setFlyBanner(1, list);
    }

    /**
     * 跑马灯
     *
     * @param houseKeeperDataQuickBean
     */
    public void updateMarqueeData(HouseKeeperDataQuickBean houseKeeperDataQuickBean) {
        if (marqueeViewHolder == null) {
            return;
        }
        this.houseKeeperDataQuickBean = houseKeeperDataQuickBean;
        marqueeViewHolder.setMarquee(houseKeeperDataQuickBean);
    }

    /**
     * 中间banner
     *
     * @param list
     */
    public void updateMiddleBanner(List<SliderPictureInfo> list) {
        if (middleBannerViewHolder == null) {
            return;
        }
        this.middleBannerList = list;
        middleBannerViewHolder.setFlyBanner(2, list);
    }

    /**
     * 推荐
     *
     * @param list
     */
    public void updateRecommendList(List<RecommendGridBean> list) {
        if (recommendGridViewHolder == null) {
            return;
        }
        this.recommendGridBeanList = list;
        recommendGridViewHolder.setRecommendData(list);
    }

    /**
     * 猜你喜欢
     * @param bean
     */
    public void updateGuessYouLikeList(GuessYouLikeBean bean) {
        if (guessYouLikeGridViewHolder == null) {
            return;
        }
        this.guessYouLikeBean = bean;
        guessYouLikeGridViewHolder.setGuessYouLikeData(bean);
    }


//    /**
//     * 更新旅游推荐数据
//     */
//    public void updateTravelData(TravelRecommendBean travelRecommendBean) {
//        if (travelViewHolder == null) {
//            return;
//        }
//        this.travelRecommendBean = travelRecommendBean;
//        travelViewHolder.setTravelData(travelRecommendBean);
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.home_top_banner, parent, false);
                topBannerViewHolder = new BannerViewHolder(view);
                return topBannerViewHolder;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.home_module_grid, parent, false);
                gridViewHolder = new GridViewHolder(view);
                return gridViewHolder;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.home_marquee, parent, false);
                marqueeViewHolder = new MarqueeViewHolder(view);
                return marqueeViewHolder;
            case 3:
                view = LayoutInflater.from(context).inflate(R.layout.home_middle_banner, parent, false);
                middleBannerViewHolder = new BannerViewHolder(view);
                return middleBannerViewHolder;
            case 4:
                view = LayoutInflater.from(context).inflate(R.layout.home_recommend_grid, parent, false);
                recommendGridViewHolder = new RecommendGridViewHolder(view);
                return recommendGridViewHolder;
            case 5:
                view = LayoutInflater.from(context).inflate(R.layout.home_guess_you_like, parent, false);
                guessYouLikeGridViewHolder = new GuessYouLikeGridViewHolder(view);
                return guessYouLikeGridViewHolder;
            case 6:
                view = LayoutInflater.from(context).inflate(R.layout.home_bottom, parent, false);
                return new BottomViewHolder(view);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            bannerHolder.setFlyBanner(1, topBannerList);
        } else if (position == 1) {
            GridViewHolder gridHolder = (GridViewHolder) holder;
            gridHolder.setGridView();
        } else if (position == 2) {
            MarqueeViewHolder marqueeViewHolder = (MarqueeViewHolder) holder;
            marqueeViewHolder.setMarquee(houseKeeperDataQuickBean);
        } else if (position == 3) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            bannerHolder.setFlyBanner(2, middleBannerList);
        } else if (position == 4) {
            RecommendGridViewHolder recommendGridViewHolder = (RecommendGridViewHolder) holder;
            recommendGridViewHolder.setRecommendData(recommendGridBeanList);
        } else if (position == 5) {
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
            case "top_banner":

                return 0;

            case "module_grid":

                return 1;

            case "marquee":

                return 2;

            case "middle_banner":

                return 3;
            case "recommend_grid":

                return 4;
            case "guess_you_like":

                return 5;
            case "bottom":

                return 6;
            default:
                return position;
        }

    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        FlyBanner flyBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);

            flyBanner = (FlyBanner) itemView.findViewById(R.id.fl_banner);

        }

        public void setFlyBanner(int type, final List<SliderPictureInfo> list) {
            List<String> picList = new ArrayList<>();
            for (SliderPictureInfo info : list) {
                String pic_url = info.getPic_url();
                picList.add(pic_url);
            }
            if (list.size() > 0) {
                flyBanner.setImagesUrl(picList);
            }

            flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (list.size() == 0){
                        return;
                    }
                    SliderPictureInfo sliderPictureInfo = list.get(position);
                    mDataCallBack.setOnBannerClick(sliderPictureInfo, position);
                }
            });

            if (type == 1){
                ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
                int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
                layoutParams.width = screenWH - GraphicUtil.dp2px((Activity) context, 0);
                layoutParams.height = 575 * layoutParams.width / 1080;
                flyBanner.setLayoutParams(layoutParams);
            }else if (type == 2){
                ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
                int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
                layoutParams.width = screenWH - GraphicUtil.dp2px((Activity) context, 20);
                layoutParams.height =  275*layoutParams.width/1010;
                flyBanner.setLayoutParams(layoutParams);
            }

        }
    }


    public class GridViewHolder extends RecyclerView.ViewHolder {

        MyGridView gv_content;

        public GridViewHolder(View itemView) {
            super(itemView);

            gv_content = (MyGridView) itemView.findViewById(R.id.gv_content);
        }

        public void setGridView() {
            PublicSelectInfo a = new PublicSelectInfo();
            a.setId(1);
            a.setName("人才驿站");
            a.setResource_id(R.mipmap.icon_personnel_new);

            PublicSelectInfo b = new PublicSelectInfo();
            b.setId(2);
            b.setName("思想智库");
            b.setResource_id(R.mipmap.icon_wisdom_new);

            PublicSelectInfo c = new PublicSelectInfo();
            c.setId(3);
            c.setName("智慧生活");
            c.setResource_id(R.mipmap.icon_family_new);

            PublicSelectInfo d = new PublicSelectInfo();
            d.setId(4);
            d.setName("房屋租售");
            d.setResource_id(R.mipmap.icon_lease_new);

            PublicSelectInfo e = new PublicSelectInfo();
            e.setId(5);
            e.setName("住宿旅行");
            e.setResource_id(R.mipmap.icon_tour_new);

            PublicSelectInfo f = new PublicSelectInfo();
            f.setId(6);
            f.setName("汽车服务");
            f.setResource_id(R.mipmap.icon_carsale_new);

            PublicSelectInfo g = new PublicSelectInfo();
            g.setId(7);
            g.setName("大健康");
            g.setResource_id(R.mipmap.icon_secure_new);

            PublicSelectInfo h = new PublicSelectInfo();
            h.setId(8);
            h.setName("名优特产");
            h.setResource_id(R.mipmap.icon_gift_new);

            PublicSelectInfo i = new PublicSelectInfo();
            i.setId(9);
            i.setName("资本管理");
            i.setResource_id(R.mipmap.icon_capital_new);

            PublicSelectInfo j = new PublicSelectInfo();
            j.setId(10);
            j.setName("法律专家");
            j.setResource_id(R.mipmap.icon_law_new);

            List<PublicSelectInfo> list = new ArrayList<>();
            list.add(a);
            list.add(b);
            list.add(c);
            list.add(d);
            list.add(e);
            list.add(f);
            list.add(g);
            list.add(h);
            list.add(i);
            list.add(j);
            gv_content.setAdapter(new HomeFunctionAdapter(context, list));
            gv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mDataCallBack.setOnFunctionClick(position);
                }
            });

        }

    }

    public class MarqueeViewHolder extends RecyclerView.ViewHolder {

        private MarqueeView<RelativeLayout, HouseKeeperDataQuickBean.ListBean> marqueeView;
        private TextView tv_more;

        public MarqueeViewHolder(View itemView) {
            super(itemView);

            marqueeView = (MarqueeView<RelativeLayout, HouseKeeperDataQuickBean.ListBean>) itemView.findViewById(R.id.marqueeView);
            tv_more = (TextView) itemView.findViewById(R.id.tv_more);
        }

        public void setMarquee(HouseKeeperDataQuickBean houseKeeperDataQuickBean) {
            List<HouseKeeperDataQuickBean.ListBean> list = houseKeeperDataQuickBean.getList();
//            MarqueeFactory<RelativeLayout, HouseKeeperDataQuickBean.ListBean> marqueeFactory = new MarqueeViewAdapter(context);
//            marqueeFactory.setData(list);
//            marqueeView.setInAndOutAnim(R.anim.in_top, R.anim.out_bottom);
//            marqueeView.setMarqueeFactory(marqueeFactory);
//            marqueeView.startFlipping();
//            marqueeView.setOnItemClickListener(new OnItemClickListener<RelativeLayout, HouseKeeperDataQuickBean.ListBean>() {
//                @Override
//                public void onItemClickListener(RelativeLayout mView, HouseKeeperDataQuickBean.ListBean mData, int mPosition) {
//                    mDataCallBack.setOnMarqueeViewClick(mPosition);
//                }
//            });
//            tv_more.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mDataCallBack.setOnMarqueeViewMoreClick();
//                }
//            });
        }

    }


    public class RecommendGridViewHolder extends RecyclerView.ViewHolder {

        private MyExpandableListView elv_content;


        public RecommendGridViewHolder(View itemView) {
            super(itemView);
            elv_content = (MyExpandableListView) itemView.findViewById(R.id.elv_content);

        }

        public void setRecommendData(final List<RecommendGridBean> list) {
            if (list == null || list.size() == 0) {
                return;
            }

            HomeRecommendGridViewAdapter adapter = new HomeRecommendGridViewAdapter(context, list,mDataCallBack);
            elv_content.setAdapter(adapter);
            // 展开所有
            for (int i = 0, length = adapter.getGroupCount(); i < length; i++) {
                elv_content.expandGroup(i);
            }
            elv_content.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return true;
                }
            });
            elv_content.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    return false;
                }
            });
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

            String list_name = bean.getList_name();
            List<GuessYouLikeBean.ListBean> list = bean.getList();

            if (list == null || list.size() == 0){
                lay_rl_bg.setVisibility(View.GONE);
            }else{
                lay_rl_bg.setVisibility(View.VISIBLE);
                tv_title.setText(TextUtils.isEmpty(list_name) ? "" : list_name);
            }

            HomeGuessYouLikeAdapter adapter = new HomeGuessYouLikeAdapter(context, list);
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


    public class BottomViewHolder extends RecyclerView.ViewHolder {

        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface RecyclerViewDataCallBack {
        //顶部banner图
        void setOnBannerClick(SliderPictureInfo data, int position);

        //十个功能模块
        void setOnFunctionClick(int position);

        //跑马灯
        void setOnMarqueeViewClick(int position);

        //跑马灯更多
        void setOnMarqueeViewMoreClick();

        //推荐
        void setOnRecommendClick(RecommendGridBean.ListBean data);
        //推荐-更多
        void setOnRecommendMoreClick(RecommendGridBean data);

        //猜你喜欢
        void setOnGuessYouLikeClick(GuessYouLikeBean.ListBean bean);

        //管家推荐banner图
//        void setOnMiddleBannerClick(int position);
        //旅游推荐
        void setOnRecommondClick(TravelRecommendBean.ListBean data, int position);
    }

}

