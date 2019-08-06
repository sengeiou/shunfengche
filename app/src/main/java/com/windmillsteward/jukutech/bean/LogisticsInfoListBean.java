package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class LogisticsInfoListBean extends BaseData {

    /**
     * logistics_single_number : 700357097585
     * code : YTO
     * name : 圆通速递
     * logistics_info_id : 1
     * order_sn : 2018040203041965062287
     * logistics_info : [{"AcceptStation":"【广东省东莞市虎门公司】 已收件","AcceptTime":"2018-03-28 23:33:32"},{"AcceptStation":"【广东省东莞市虎门公司】 已打包","AcceptTime":"2018-03-28 23:54:23"},{"AcceptStation":"【虎门转运中心】 已收入","AcceptTime":"2018-03-29 03:39:06"},{"AcceptStation":"【虎门转运中心】 已发出 下一站 【广州转运中心】","AcceptTime":"2018-03-29 05:31:50"},{"AcceptStation":"【广州转运中心】 已收入","AcceptTime":"2018-03-29 09:21:46"},{"AcceptStation":"【广州转运中心】 已发出 下一站 【广东省广州市白云龙归公司】","AcceptTime":"2018-03-29 10:15:07"},{"AcceptStation":"【广东省广州市白云龙归公司】 派件人: 林海东 派件中 派件员电话18307545618","AcceptTime":"2018-03-29 15:23:31"},{"AcceptStation":"客户 签收人: 本人签收 已签收 感谢使用圆通速递，期待再次为您服务","AcceptTime":"2018-03-29 19:27:00"}]
     * status : 3
     */

    private String logistics_single_number;
    private String code;
    private String name;
    private String commodity_img;
    private int logistics_info_id;
    private String order_sn;
    private int status;
    private List<LogisticsInfoBean> logistics_info;

    public String getLogistics_single_number() {
        return logistics_single_number;
    }

    public void setLogistics_single_number(String logistics_single_number) {
        this.logistics_single_number = logistics_single_number;
    }

    public String getCommodity_img() {
        return commodity_img;
    }

    public void setCommodity_img(String commodity_img) {
        this.commodity_img = commodity_img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLogistics_info_id() {
        return logistics_info_id;
    }

    public void setLogistics_info_id(int logistics_info_id) {
        this.logistics_info_id = logistics_info_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<LogisticsInfoBean> getLogistics_info() {
        return logistics_info;
    }

    public void setLogistics_info(List<LogisticsInfoBean> logistics_info) {
        this.logistics_info = logistics_info;
    }

    public static class LogisticsInfoBean {
        /**
         * AcceptStation : 【广东省东莞市虎门公司】 已收件
         * AcceptTime : 2018-03-28 23:33:32
         */

        private String AcceptStation;
        private String AcceptTime;

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }
    }
}
