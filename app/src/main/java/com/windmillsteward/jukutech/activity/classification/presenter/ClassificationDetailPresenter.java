package com.windmillsteward.jukutech.activity.classification.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.classification.fragment.ClassificationDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.CarClassListBean;
import com.windmillsteward.jukutech.bean.ClassificationClassBean;
import com.windmillsteward.jukutech.bean.ClassificationPersonalBean;
import com.windmillsteward.jukutech.bean.HotelTypeBean;
import com.windmillsteward.jukutech.bean.HouseTypeBean;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBean;
import com.windmillsteward.jukutech.bean.InsuranceListTypeBean;
import com.windmillsteward.jukutech.bean.JobClassBean;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.NameAndIdBean;
import com.windmillsteward.jukutech.bean.RequireClassBean;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;
import com.windmillsteward.jukutech.bean.TravelClassBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/3/14/014
 * 作者：xjh
 */
public class ClassificationDetailPresenter extends BaseNetModelImpl {

    private static final int PERSONAL_1 = 1;
    private static final int PERSONAL_2 = 2;
    private static final int IDEA_THINK = 3;
    private static final int FAMILY_CLASS = 4;
    private static final int HOUSE_CLASS = 5;
    private static final int HOUSE_CLASS_1 = 6;
    private static final int TRAVEL_CLASS = 7;
    private static final int HOTEL_CLASS = 8;
    private static final int INSURANCE_LIST = 9;
    private static final int CAR_LIST = 10;
    private static final int SPECIALTY_LIST = 11;
    private static final int CAPITAL_CLASS = 12;
    private static final int LEGAL_EXPERT = 13;

    private ClassificationDetailView view;
    private List<JobClassBean> jobClassBeans = new ArrayList<>();
    private List<HouseTypeBean> houseTypeBeans = new ArrayList<>();
    private List<TravelClassBean> travelClassBeans = new ArrayList<>();

    public ClassificationDetailPresenter(ClassificationDetailView view) {
        this.view = view;
    }

    public void loadPersonalClassData() {
        DataLoader dataLoader = new DataLoader(this, PERSONAL_1);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_JOB_CLASS);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    private void loadResumeClassData() {
        DataLoader dataLoader = new DataLoader(this, PERSONAL_2);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_POP_MORE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadIdeaClassData() {
        DataLoader dataLoader = new DataLoader(this, IDEA_THINK);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadFamilyClassData() {
        DataLoader dataLoader = new DataLoader(this, FAMILY_CLASS);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_REQUIRE_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadBuyHouseClassData() {
        DataLoader dataLoader = new DataLoader(this, HOUSE_CLASS);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("type",2);
        httpInfo.setUrl(APIS.URL_HOUSE_TYPE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    private void loadLeaseHouseClassData() {
        DataLoader dataLoader = new DataLoader(this, HOUSE_CLASS_1);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("type",1);
        httpInfo.setUrl(APIS.URL_HOUSE_TYPE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadTravelClassData() {
        DataLoader dataLoader = new DataLoader(this, TRAVEL_CLASS);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_TRAVEL_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    private void loadHOTELClassData() {
        DataLoader dataLoader = new DataLoader(this, HOTEL_CLASS);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_HOTEL_TYPE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadInsuranceListData() {
        DataLoader dataLoader = new DataLoader(this, INSURANCE_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_INSURANCE_TYPE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadCarNameListData() {
        DataLoader dataLoader = new DataLoader(this, CAR_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_CAR_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadSpcialtyClassData() {
        DataLoader dataLoader = new DataLoader(this, SPECIALTY_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("parent_id", 0);
        httpInfo.setUrl(APIS.URL_CATEGORY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadCapitalClassData() {
        DataLoader dataLoader = new DataLoader(this, CAPITAL_CLASS);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 1);
        httpInfo.setUrl(APIS.URL_QUERY_CONDITION);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadLegalClassData() {
        DataLoader dataLoader = new DataLoader(this, LEGAL_EXPERT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_LEGAL_EXPERT_TYPE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case PERSONAL_1:
                type = new TypeReference<BaseResultInfo<List<JobClassBean>>>() {}.getType();
                break;
            case PERSONAL_2:
                type = new TypeReference<BaseResultInfo<MoreBean>>() {}.getType();
                break;
            case IDEA_THINK:
                type = new TypeReference<BaseResultInfo<List<IdeaThinkClassBean>>>(){}.getType();
                break;
            case FAMILY_CLASS:
                type = new TypeReference<BaseResultInfo<List<RequireClassBean>>>() {
                }.getType();
                break;
            case HOUSE_CLASS_1:
            case HOUSE_CLASS:
                type = new TypeReference<BaseResultInfo<List<HouseTypeBean>>>(){}.getType();
                break;
            case TRAVEL_CLASS:
                type = new TypeReference<BaseResultInfo<List<TravelClassBean>>>() {
                }.getType();
                break;
            case HOTEL_CLASS:
                type = new TypeReference<BaseResultInfo<List<HotelTypeBean>>>(){}.getType();
                break;
            case INSURANCE_LIST:
                type = new TypeReference<BaseResultInfo<List<InsuranceListTypeBean>>>(){}.getType();
                break;
            case CAR_LIST:
                type = new TypeReference<BaseResultInfo<List<CarClassListBean>>>() {
                }.getType();
                break;
            case SPECIALTY_LIST:
                type = new TypeReference<BaseResultInfo<List<SpecialtyClassBean>>>() {
                }.getType();
                break;
            case CAPITAL_CLASS:
                type = new TypeReference<BaseResultInfo<List<NameAndIdBean>>>(){}.getType();
                break;
            case LEGAL_EXPERT:
                type = new TypeReference<BaseResultInfo<List<NameAndIdBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case PERSONAL_1:
                view.dismiss();
                List<JobClassBean> jobClassBeans = (List<JobClassBean>) result.getData();
                if (jobClassBeans!=null) {
                    this.jobClassBeans.clear();
                    this.jobClassBeans.addAll(jobClassBeans);
                }
                loadResumeClassData();
                break;
            case PERSONAL_2:
                view.dismiss();
                List<ClassificationPersonalBean> list = new ArrayList<>();
                ClassificationPersonalBean bean = new ClassificationPersonalBean();
                bean.setPersonalTitle("职位");
                List<ClassificationClassBean> classBeans = new ArrayList<>();
                for (JobClassBean classBean : this.jobClassBeans) {
                    ClassificationClassBean bean1 = new ClassificationClassBean();
                    bean1.setId(classBean.getJob_class_id());
                    bean1.setText(classBean.getClass_name());
                    bean1.setImage(classBean.getImage());
                    classBeans.add(bean1);
                }
                bean.setList(classBeans);
                list.add(bean);
                MoreBean data = (MoreBean) result.getData();
                ClassificationPersonalBean bean2 = new ClassificationPersonalBean();
                bean2.setPersonalTitle("简历");
                List<ClassificationClassBean> classBeans1 = new ArrayList<>();
                if (data!=null) {
                    List<MoreBean.EducationListBean> education_list = data.getEducation_list();
                    if (education_list!=null) {
                        for (MoreBean.EducationListBean listBean : education_list) {
                            ClassificationClassBean bean1 = new ClassificationClassBean();
                            bean1.setId(listBean.getEducation_background_id());
                            bean1.setText(listBean.getEducation_name());
                            bean1.setImage(listBean.getImage());
                            classBeans1.add(bean1);
                        }
                    }
                }
                bean2.setList(classBeans1);
                list.add(bean2);
                view.onLoadClassSuccess(list);
                break;
            case IDEA_THINK:
                view.dismiss();
                List<IdeaThinkClassBean> ideaThinkClassBeans = (List<IdeaThinkClassBean>) result.getData();
                List<ClassificationPersonalBean> list2 = new ArrayList<>();

                if (ideaThinkClassBeans!=null) {

                    for (IdeaThinkClassBean classBean : ideaThinkClassBeans) {
                        ClassificationPersonalBean ideaBean2 = new ClassificationPersonalBean();
                        ideaBean2.setPersonalTitle(classBean.getFirst_class_name());
                        List<ClassificationClassBean> classBeans2 = new ArrayList<>();
                        List<IdeaThinkClassBean.SecondClassListBean> second_class_list = classBean.getSecond_class_list();
                        if (second_class_list!=null) {
                            for (IdeaThinkClassBean.SecondClassListBean secondClassListBean : second_class_list) {
                                ClassificationClassBean _ideaBean2 = new ClassificationClassBean();
                                _ideaBean2.setId(secondClassListBean.getSecond_class_id());
                                _ideaBean2.setText(secondClassListBean.getSecond_class_name());
                                _ideaBean2.setImage(secondClassListBean.getImage());
                                classBeans2.add(_ideaBean2);
                            }
                        }
                        ideaBean2.setList(classBeans2);
                        list2.add(ideaBean2);
                    }
                }
                view.onLoadClassSuccess(list2);
                break;
            case FAMILY_CLASS:
                view.dismiss();
                List<RequireClassBean> familyClassBeans = (List<RequireClassBean>) result.getData();
                List<ClassificationPersonalBean> list3 = new ArrayList<>();
                ClassificationPersonalBean familyBean3 = new ClassificationPersonalBean();
                familyBean3.setPersonalTitle("类别");
                List<ClassificationClassBean> classBeans3 = new ArrayList<>();
                if (familyClassBeans!=null) {
                    for (RequireClassBean classBean : familyClassBeans) {
                        ClassificationClassBean _classBeans3 = new ClassificationClassBean();
                        _classBeans3.setId(classBean.getRequire_class_id());
                        _classBeans3.setText(classBean.getClass_name());
                        _classBeans3.setImage(classBean.getImage());
                        classBeans3.add(_classBeans3);
                    }
                }
                familyBean3.setList(classBeans3);
                list3.add(familyBean3);
                view.onLoadClassSuccess(list3);
                break;
            case HOUSE_CLASS:
                view.dismiss();
                List<HouseTypeBean> houseTypeBeans = (List<HouseTypeBean>) result.getData();
                if (houseTypeBeans!=null) {
                    this.houseTypeBeans.clear();
                    this.houseTypeBeans.addAll(houseTypeBeans);
                }
                loadLeaseHouseClassData();
                break;
            case HOUSE_CLASS_1:
                List<ClassificationPersonalBean> list4 = new ArrayList<>();
                ClassificationPersonalBean houseBean4 = new ClassificationPersonalBean();
                houseBean4.setPersonalTitle("住宅类型");
                List<ClassificationClassBean> classBeans4 = new ArrayList<>();
                if (this.houseTypeBeans!=null) {
                    for (HouseTypeBean classBean : this.houseTypeBeans) {
                        ClassificationClassBean _classBeans3 = new ClassificationClassBean();
                        _classBeans3.setId(classBean.getHouse_type_id());
                        _classBeans3.setText(classBean.getHouse_type_name());
                        _classBeans3.setImage(classBean.getImage());
                        classBeans4.add(_classBeans3);
                    }
                }
                houseBean4.setList(classBeans4);
                list4.add(houseBean4);

                List<HouseTypeBean> _houseTypeBeans = (List<HouseTypeBean>) result.getData();
                ClassificationPersonalBean _houseBean4 = new ClassificationPersonalBean();
                _houseBean4.setPersonalTitle("出租类型");
                List<ClassificationClassBean> _classBeans4 = new ArrayList<>();
                if (_houseTypeBeans!=null) {
                    for (HouseTypeBean classBean : _houseTypeBeans) {
                        ClassificationClassBean _classBeans3 = new ClassificationClassBean();
                        _classBeans3.setId(classBean.getHouse_type_id());
                        _classBeans3.setText(classBean.getHouse_type_name());
                        _classBeans3.setImage(classBean.getImage());
                        _classBeans4.add(_classBeans3);
                    }
                }
                _houseBean4.setList(_classBeans4);
                list4.add(_houseBean4);
                view.onLoadClassSuccess(list4);
                break;
            case TRAVEL_CLASS:
                view.dismiss();
                List<TravelClassBean> travelClassBeans = (List<TravelClassBean>) result.getData();
                if (travelClassBeans!=null) {
                    this.travelClassBeans.clear();
                    this.travelClassBeans.addAll(travelClassBeans);
                }
                loadHOTELClassData();
                break;
            case HOTEL_CLASS:
                view.dismiss();
                List<ClassificationPersonalBean> list5 = new ArrayList<>();
                ClassificationPersonalBean travelBean5 = new ClassificationPersonalBean();
                travelBean5.setPersonalTitle("旅游类型");
                List<ClassificationClassBean> classBeans5 = new ArrayList<>();
                if (this.travelClassBeans!=null) {
                    for (TravelClassBean classBean : this.travelClassBeans) {
                        ClassificationClassBean _classBeans3 = new ClassificationClassBean();
                        _classBeans3.setId(classBean.getClass_id());
                        _classBeans3.setText(classBean.getClass_name());
                        _classBeans3.setImage(classBean.getImage());
                        classBeans5.add(_classBeans3);
                    }
                }
                travelBean5.setList(classBeans5);
                list5.add(travelBean5);

                List<HotelTypeBean> hotelTypeBeans = (List<HotelTypeBean>) result.getData();
                ClassificationPersonalBean _travelBean5 = new ClassificationPersonalBean();
                _travelBean5.setPersonalTitle("酒店星级");
                List<ClassificationClassBean> _classBeans5 = new ArrayList<>();
                if (hotelTypeBeans!=null) {
                    for (HotelTypeBean classBean : hotelTypeBeans) {
                        ClassificationClassBean _classBeans3 = new ClassificationClassBean();
                        _classBeans3.setId(classBean.getHotel_type());
                        _classBeans3.setText(classBean.getType_name());
                        _classBeans3.setImage(classBean.getImage());
                        _classBeans5.add(_classBeans3);
                    }
                }
                _travelBean5.setList(_classBeans5);
                list5.add(_travelBean5);
                view.onLoadClassSuccess(list5);
                break;
            case INSURANCE_LIST:
                view.dismiss();
                List<InsuranceListTypeBean> insuranceListBeans = (List<InsuranceListTypeBean>) result.getData();
                List<ClassificationPersonalBean> list7 = new ArrayList<>();
                ClassificationPersonalBean classificationPersonalBean7 = new ClassificationPersonalBean();
                classificationPersonalBean7.setPersonalTitle("险种");
                List<ClassificationClassBean> classificationClassBeans7 = new ArrayList<>();
                if (insuranceListBeans!=null) {
                    for (InsuranceListTypeBean insuranceListBean : insuranceListBeans) {
                        ClassificationClassBean classificationClassBean = new ClassificationClassBean();
                        classificationClassBean.setId(insuranceListBean.getId());
                        classificationClassBean.setText(insuranceListBean.getName());
                        classificationClassBean.setImage(insuranceListBean.getImage());
                        classificationClassBeans7.add(classificationClassBean);
                    }
                }
                classificationPersonalBean7.setList(classificationClassBeans7);
                list7.add(classificationPersonalBean7);
                view.onLoadClassSuccess(list7);
                break;
            case CAR_LIST:
                view.dismiss();
                List<ClassificationPersonalBean> list_5 = new ArrayList<>();
                List<CarClassListBean> carClassListBeans = (List<CarClassListBean>) result.getData();
                if (carClassListBeans!=null) {
                    ClassificationPersonalBean classBean5 = new ClassificationPersonalBean();
                    classBean5.setPersonalTitle("买车");
                    List<ClassificationClassBean> bean_list_5 = new ArrayList<>();
                    for (int i = 0; i < ((carClassListBeans.size()>8)?8:carClassListBeans.size()); i++) {

                        CarClassListBean classBean = carClassListBeans.get(i);
                        ClassificationClassBean _classBeans3 = new ClassificationClassBean();
                        _classBeans3.setId(classBean.getBrand_id());
                        _classBeans3.setText(classBean.getBrand_name());
                        _classBeans3.setImage(classBean.getBrand_image());
                        bean_list_5.add(_classBeans3);
                    }
                    classBean5.setList(bean_list_5);
                    list_5.add(classBean5);
                }
                ClassificationPersonalBean classBean5_1 = new ClassificationPersonalBean();
                classBean5_1.setPersonalTitle("租车");
                List<ClassificationClassBean> bean_list_car_1 = new ArrayList<>();
                ClassificationClassBean bean_car = new ClassificationClassBean();
                bean_car.setId(1);
                bean_car.setText("找车主");
                bean_car.setImage("http://sfcgj.oss-cn-qingdao.aliyuncs.com/6016341527133363104icon_find_car.png");
                bean_list_car_1.add(bean_car);
                ClassificationClassBean bean_car_1 = new ClassificationClassBean();
                bean_car_1.setId(2);
                bean_car_1.setText("找乘客");
                bean_car_1.setImage("http://sfcgj.oss-cn-qingdao.aliyuncs.com/5282101527133462615icon_find_passenger.png");
                bean_list_car_1.add(bean_car_1);
                classBean5_1.setList(bean_list_car_1);
                list_5.add(classBean5_1);
                view.onLoadClassSuccess(list_5);
                break;
            case SPECIALTY_LIST:
                view.dismiss();
                List<SpecialtyClassBean> specialtyClassBeans = (List<SpecialtyClassBean>) result.getData();
                List<ClassificationPersonalBean> list_6 = new ArrayList<>();
                if (specialtyClassBeans!=null) {
                    for (SpecialtyClassBean classBean : specialtyClassBeans) {
                        List<SpecialtyClassBean.ChildBeanX> child = classBean.getChild();//二级列表
                        ClassificationPersonalBean classBean6 = new ClassificationPersonalBean();
                        classBean6.setPersonalTitle(classBean.getName());
                        List<ClassificationClassBean> bean_list_6 = new ArrayList<>();
                        if (child!=null) {
                            for (SpecialtyClassBean.ChildBeanX beanX : child) {
                                ClassificationClassBean _classBeans6 = new ClassificationClassBean();
                                _classBeans6.setId(beanX.getClass_id());
                                _classBeans6.setText(beanX.getName());
                                _classBeans6.setImage(beanX.getImage());
                                _classBeans6.setId_two(classBean.getClass_id());
                                bean_list_6.add(_classBeans6);
                            }
                        }
                        classBean6.setList(bean_list_6);
                        list_6.add(classBean6);
                    }
                }

                view.onLoadClassSuccess(list_6);
                break;
            case CAPITAL_CLASS:
                List<NameAndIdBean> capitalClassBeans = (List<NameAndIdBean>) result.getData();
                List<ClassificationPersonalBean> list_7 = new ArrayList<>();
                ClassificationPersonalBean classBean7 = new ClassificationPersonalBean();
                classBean7.setPersonalTitle("产品类型");
                List<ClassificationClassBean> bean_list_7 = new ArrayList<>();
                if (capitalClassBeans!=null) {
                    for (NameAndIdBean classBean : capitalClassBeans) {
                        ClassificationClassBean _classBeans6 = new ClassificationClassBean();
                        _classBeans6.setId(classBean.getId());
                        _classBeans6.setText(classBean.getName());
                        _classBeans6.setImage(classBean.getImage());
                        bean_list_7.add(_classBeans6);
                    }
                }
                classBean7.setList(bean_list_7);
                list_7.add(classBean7);
                view.onLoadClassSuccess(list_7);
                break;
            case LEGAL_EXPERT:
                List<NameAndIdBean> legalClassBeans = (List<NameAndIdBean>) result.getData();
                List<ClassificationPersonalBean> list_8 = new ArrayList<>();
                ClassificationPersonalBean classBean8 = new ClassificationPersonalBean();
                classBean8.setPersonalTitle("类型");
                List<ClassificationClassBean> bean_list_8 = new ArrayList<>();
                if (legalClassBeans!=null) {
                    for (NameAndIdBean classBean : legalClassBeans) {
                        ClassificationClassBean _classBeans6 = new ClassificationClassBean();
                        _classBeans6.setId(classBean.getId());
                        _classBeans6.setText(classBean.getName());
                        _classBeans6.setImage(classBean.getImage());
                        bean_list_8.add(_classBeans6);
                    }
                }
                classBean8.setList(bean_list_8);
                list_8.add(classBean8);
                view.onLoadClassSuccess(list_8);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,1);
    }
}
