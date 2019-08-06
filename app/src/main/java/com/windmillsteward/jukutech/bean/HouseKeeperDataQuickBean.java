package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：管家快讯列表实体
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HouseKeeperDataQuickBean extends BaseData {

    private int total_row;//总行数
    private int total_page;//总页码
    private int page;//当前页码
    private int page_count;//页容量
    private String content;//快讯详情html
    private List<ListBean> list;//

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTotal_row() {
        return total_row;
    }

    public void setTotal_row(int total_row) {
        this.total_row = total_row;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public class ListBean {

        private String cover_url;//封面图
        private String url;
        private String title;//标题
        private String add_time;//发布时间
        private int news_flash_id;//快讯id

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getNews_flash_id() {
            return news_flash_id;
        }

        public void setNews_flash_id(int news_flash_id) {
            this.news_flash_id = news_flash_id;
        }
    }


}
