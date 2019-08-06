package com.windmillsteward.jukutech.interfaces;

/**
 * 接口常量
 *
 * @author chenyuqiang
 * @date 2017/6/1
 * Created by  2017 广州聚酷软件科技有限公司 All Right Reserved
 */
public interface APIS {
    String Host = "http://shunfengche.qishare.cn/windmillsteward";// 服务器测试服地址
//    String Host = "http://www2.shunfengcheguanjia.com/windmillsteward";// 服务器正式服地址

    //******************************************** 公共接口 ********************************************\\
    String LOGIN = Host + "/user_api/register_login/login";// 登录
    String URL_SECOND_AREA_LIST = Host + "/user_api/common_list/second_area_list";  // 城市列表
    String URL_THIRD_AREA_LIST = Host + "/user_api/common_list/third_area_list";  // 区列表
    String URL_THIRD_FOURTH_AREA_LIST = Host + "/user_api/common_list/third_fourth_area_list";  // 区和街道数据
    String UPLOAD = "http://telltie1.weqxin.com/promotion_api/common/upload";  // 单文件上传
    String URL_FILE_UPLOAD = Host + "/user_api/common/imgUpload";// 上传多文件
    String URL_IS_AUTHEN = Host + "/user_api/common_list/is_authen";  // 判断是否认证
    String URL_IS_AUTHEN_ENTERPRISE = Host + "/user_api/common_list/is_authen_enterprise";  // 判断是否企业
    // 类型【1：用户注册费用，21：人才驿站发布简历，22：人才驿站发布职位，23：人才驿站职位联系费用，24：人才驿站简历联系费用,31:思想智库发布需求，32:思想智库联系费用，43:智慧家庭联系费用，51：发布卖房屋需求，52：发布买房屋需求，53：卖房联系(联系房主)，54：买房联系(联系买房的人)，55：发布出租需求，56：发布租房需求，57：租房联系(联系房东)，58：求租联系(联系租房的人)，64：旅游联系费用  65:发布旅游费用 70.发布保险信息  71.联系保险发布人 81.发布产品  82.资金放贷 83.联系产品发布人 84.联系资金放贷人 90.发布法律费用 100.发布我要卖车 101.发布我要买车 102.车主发布租车信息 103.乘客发布租车信息】
    String URL_IS_CHARGE = Host + "/user_api/common_list/is_charge";  // 判断是否收费
    String URL_BANNER_LIST = Host + "/user_api/common_list/banner_list";  // banner图列表
    String URL_HOUSEKEEPER_DATA_QUICK = Host + "/user_api/index/list_news_flash";  // 管家快讯列表
    String URL_TRAVEL_RECOMMEND_LIST = Host + "/user_api/index/get_travel_recommend";  // 旅游推荐
    String URL_BEFORE_REGISTER = Host + "/user_api/register_login/verify_register_code";  //点击注册按钮时，先请求这个接口
    String URL_REGISTER = Host + "/user_api/register_login/register";  //真正的注册接口
    String URL_GET_RESET_PASSWORD_CODE = Host + "/user_api/register_login/get_repassword_code";  //获取重置密码时的验证码
    String URL_RESET_PASSWORD = Host + "/user_api/register_login/reset_password";  //重置登录密码
    String URL_GET_REGISTER_CODE = Host + "/user_api/register_login/get_register_code";  //注册-获取验证码
    String URL_BEFORE_PAY = Host + "/user_api/common/common_before_pay";  // 支付前调用
    String URL_AFTER_PAY = Host + "/user_api/common/common_pay_success";  // 支付后调用
    String URL_SEARCH_MODULE_DATA = Host + "/user_api/index/search_module_msg";  // 首页顶部搜索
    String URL_SEARCH_MODULE_DATA_NEW = Host + "/user_api/index/search_module_msg_new";  // 首页顶部搜索最新版
    String URL_HOUSEKEEPER_DATA_QUICK_DETAIL = Host + "/user_api/index/get_news_flash";  // 管家快讯列表详情
    String URL_CHECK_UPDATE = Host + "/user_api/client_version/get_latest_apk";  // 检查更新
    String URL_UNREAD_MESSAGE_COUNT = Host + "/user_api/index/count_unread_notification";  // 统计未读的消息记录
    String URL_MESSAGE_LIST = Host + "/user_api/index/list_notification";  // 消息列表(需要登录)
    String URL_MESSAGE_LIST_DETAIL = Host + "/user_api/index/get_notification_detail";  // 消息详情
    String URL_IS_AUDIT = Host + "/user_api/common_list/is_audit";  // 判断该模块是否需要审核
    String URL_HOME_RECOMMEND_LIST = Host + "/user_api/index/module_recommend_list";  // 首页推荐模块
    String URL_TEST_ITEMS_LIST = Host + "/user_api/smart_fire/test_items_list";  // 检测项目列表
    String URL_HOT_CITY_LIST = Host + "/user_api/common/popular_area_list";  // 获取热门城市接口
    String URL_CONFIRM_PAY = Host + "/user_api/common/confirm_pay";  // 确认支付接口
    String URL_INFO_FEE = Host + "/user_api/common/info_fee";  // 查询信息费
    String URL_INFO_GET_AGE_LIST = Host + "/user_api/common/get_age_list";  //年龄范围列表
    String URL_WALLET_RECHARGE = Host + "/user_api/user_center/wallet_recharge";  //钱包充值
    String URL_USER_AGREEMENT = Host + "/user_api/register_login/get_registedRule";  //会员协议
    String URL_START_IMAGE = Host + "/user_api/index/system_banner/get_start_image";  //获取启动图
    String URL_DELETE_FINISHED_ORDER = Host + "/user_api/user_center/match_delete_order";  //删除已完成订单
    String URL_DELETE_FINISHED_COUPON = Host + "/user_api/user_center/delete_coupon";  //删除已完成订单

    //******************************************** 聊天********************************************
    String URL_ADD_GROUP = Host + "/user_api/user_center/creatGroup";//创建or加入群聊
    String URL_Exit_GROUP = Host + "/user_api/user_center/deleteGroup";//解散群聊
    String URL_SEND_RED_PACKET = Host + "/user_api/red_packet/sendRedPacket";//发红包
    String URL_GET_RED_PACKET = Host + "/user_api/red_packet/getRedPacket";//抢红包
    String URL_RED_PACKET_DETAIL = Host + "/user_api/red_packet/redPacketDetail";//红包详情
    String URL_HX_CONTACT_LIST = Host + "/user_api/user_center/get_myUserList";//获取环信联系人列表
    String URL_HX_CONTACT_URL = Host + "/user_api/user_center/user_avatar_url";//获取环信用户的头像
    String URL_HX_USER_INFO = Host + "/user_api/user_center/get_hx_user_info";//获取环信用户的资料
    String URL_HX_USER_INFO_LIST = Host + "/user_api/user_center/get_hx_user_infos";//获取多个环信用户的资料



    //******************************************** 人才驿站 ********************************************
    String URL_POSITION_LIST = Host + "/user_api/talent_post/job/job_list";  // 职位列表
    String URL_SALARY_LIST = Host + "/user_api/talent_post/common/salary_list"; // 薪资选择
    String URL_POP_MORE = Host + "/user_api/talent_post/common/more_condition"; // popup框中的更多
    String URL_GET_CHANGE_PHONE_CODE = Host + "/user_api/common/get_verify_code"; // 获取发布需求更换手机号码的验证码
    String URL_CHANGE_PHONE = Host + "/user_api/common/verify_code"; // 校验更换手机号码的验证码




    String URL_RESUME_LIST = Host + "/user_api/talent_post/resume/resume_list"; // 简历列表
    String URL_MY_PUBLISH_LIST = Host + "/user_api/talent_post/common/my_issue"; // 我发布的
    String URL_CREATE_RESUME = Host + "/user_api/talent_post/resume/creat_resume"; // 提交简历
    String URL_ADD_RESUME = Host + "/user_api/talent_post/resume/add_resume";   // 发布简历
    String URL_BENEFIT_LIST = Host + "/user_api/talent_post/job/benefit_list";   // 福利列表
    String URL_ADD_JOB = Host + "/user_api/talent_post/job/add_job";  // 发布招聘
    String URL_JOB_DETAIL = Host + "/user_api/talent_post/job/job_detail";  // 职位详情
    String URL_RESUME_DETAIL = Host + "/user_api/talent_post/resume/resume_detail";  // 简历详情
    String URL_COLLECT_RESUME = Host + "/user_api/talent_post/resume/collect_resume";  // 收藏简历
    String URL_CANCEL_COLLECT_RESUME = Host + "/user_api/talent_post/resume/cancel_collect_resume";  // 取消收藏简历
    String URL_COLLECT_JOB = Host + "/user_api/talent_post/job/collect_job";  // 收藏职位
    String URL_CANCEL_COLLECT_JOB = Host + "/user_api/talent_post/job/cancel_collect_job";  // 取消收藏职位
    String URL_APPLY_JOB = Host + "/user_api/talent_post/job/apply_job";  // 申请职位
    String URL_EMPLOY_RESUME = Host + "/user_api/talent_post/common/employ_resume";  // 应聘简历
    String URL_JOB_CLASS = Host + "/user_api/talent_post/job/job_class";  // 加载职业分类
    String URL_DELETE_POSITION = Host + "/user_api/talent_post/job/delete_job";  // 删除职位
    String URL_DELETE_RESUME = Host + "/user_api/talent_post/resume/delete_resume";  // 删除简历
    String URL_EDIT_RESUME = Host + "/user_api/talent_post/resume/edit_resume";  // 编辑简历
    String URL_EDIT_JOB = Host + "/user_api/talent_post/job/edit_job";  // 编辑简历
    String URL_LABOUR_SERVICE_CENTER_LIST = Host + "/user_api/labor_intermediary/list";  // 劳务中介列表
    String URL_POSITION_CLASS_LIST = Host + "/user_api/talent_post/common/index_class_list";  // 人才驿站首页的职位分类列表
    String URL_LABOR_DETAIL = Host + "/user_api/labor_intermediary/detail";  // 劳务中介详情
    String URL_LABOR_COLLECT = Host + "/user_api/labor_intermediary/collect";  // 收藏劳务
    String URL_LABOR_CANCEL_COLLECT = Host + "/user_api/labor_intermediary/cancel_collect";  // 取消收藏劳务
    String URL_LABOR_APPLY = Host + "/user_api/labor_resume/application";  // 发布劳务工/特种工信息
    String URL_LABOR_LAST_APPLY_DATA = Host + "/user_api/labor_resume/application_info";  // 查询最后一次发布劳务工/特种工信息
    String URL_LW_TZ_RENCAI_INFO_LIST_DETAIL = Host + "/user_api/labor_resume/labor_detail";  // 劳务和特种--人才信息列表详情
    String URL_LW_TZ_WORK_INFO_LIST_DETAIL = Host + "/user_api/labor_resume/recruitment_detail";  // 劳务和特种--工作信息列表详情
    String URL_LW_TZ_QZ_PIPEI_RENCAI = Host + "/user_api/labor_resume/pleased_people";  // 劳务和特种--强制匹配人才，服务器进行匹配，不用客户端发布
    String URL_LW_TZ_QZ_PIPEI_GONGZUO = Host + "/user_api/labor_resume/pleased_work";  // 劳务和特种--强制匹配工作，服务器进行匹配，不用客户端发布
    String URL_JIAJIAO_QZ_PIPEI_RENCAI = Host + "/user_api/tutor/pleased_people";  // 家教--强制匹配人才，服务器进行匹配，不用客户端发布
    String URL_JIAJIAO_QZ_PIPEI_GONGZUO = Host + "/user_api/tutor/pleased_work";  // 家教--强制匹配工作，服务器进行匹配，不用客户端发布
    String URL_BM_YS_YES_QZ_PIPEI_RENCAI = Host + "/user_api/house_keep/pleased_people";  // 保姆月嫂育儿嫂--强制匹配人才，服务器进行匹配，不用客户端发布
    String URL_BM_YS_YES_QZ_PIPEI_GONGZUO = Host + "/user_api/house_keep/pleased_work";  // 保姆月嫂育儿嫂--强制匹配工作，服务器进行匹配，不用客户端发布
    String URL_ZDG_QZ_PIPEI_GONGZUO = Host + "/user_api/hourly_worker/pleased_work";  // 钟点工--强制匹配工作，服务器进行匹配，不用客户端发布
    String URL_ZDG_QZ_PIPEI_RENCAI = Host + "/user_api/hourly_worker/pleased_people";  // 钟点工--强制匹配人才，服务器进行匹配，不用客户端发布
    String URL_QIUZHI_QZ_PIPEI_GONGZUO = Host + "/user_api/recruitment/pleased_work";  // 求职招聘--强制匹配工作，服务器进行匹配，不用客户端发布
    String URL_QIUZHI_QZ_PIPEI_RENCAI = Host + "/user_api/recruitment/pleased_people";  // 求职招聘--强制匹配人才，服务器进行匹配，不用客户端发布
    String URL_ZDG_WORK_INFO_LIST_DETAIL = Host + "/user_api/hourly_worker/apply_work";  // 钟点工--工作信息列表详情
    String URL_ZDG_RENCAI_INFO_LIST_DETAIL = Host + "/user_api/hourly_worker/apply_people";  // 钟点工--人才信息列表详情
    String URL_LABOR_CLASS = Host + "/user_api/labor_resume/work_type_list";  // 劳务工种分类
    String URL_TALENT_HOME_INDEX_CLASS = Host + "/user_api/talent_post/index_class_list";  // 人才驿站首页模块名称和图片
    String URL_TALENT_GET_INDEX_RECOMMENDS = Host + "/user_api/talent_post/get_index_recommends";  // 人才驿站首页模块名称和图片
    String URL_TALENT_LAOWU_HOME_LIST = Host + "/user_api/labor_resume/list";  // 劳务中介首页---人才信息列表
    String URL_TALENT_LIST_WORK_TYPE = Host + "/user_api/labor_resume/list_work_type";  // 工种下拉列表
    String URL_TALENT_MATCH_POSITIONE = Host + "/user_api/labor_resume/match_position";  // 劳务工/特种工-我要找工作-系统匹配职位
    String URL_TALENT_RECRUITMENT = Host + "/user_api/labor_resume/recruitment";  // 发布招聘劳务工/特种工
    String URL_TALENT_MATCH_POSITION_DETAIL = Host + "/user_api/labor_resume/match_position_detail";  // 劳务工/特种工-我要找工作-系统匹配职位详情
    String URL_LW_TZ_POSITION_DETAIL = Host + "/user_api/labor_resume/application_detail";  // 劳务工/特种工-我要找工作已发布职位-职位详情-
    String URL_TALENT_IS_RECRUITMENT = Host + "/user_api/common/is_recruitment";  // 查询是否发布过招聘信息
    String URL_TALENT_PUBLISHED_POSITION = Host + "/user_api/labor_resume/published_position";  // 劳务工/特种工 - 已发布职位
    String URL_TALENT_MATCH_RESUME = Host + "/user_api/labor_resume/match_resume";  //劳务工/特种工-已匹配到的简历
    String URL_TALENT_CONFIRM_PAY = Host + "/user_api/labor_resume/confirm_pay";  //劳务工/特种工-已发布职位详情-确认支付
    String URL_TALENT_CONFIRM_FINISH_WORK = Host + "/user_api/labor_resume/confirm_finish_work";  //劳务工/特种工-已发布职位详情-确认支付
    String URL_TALENT_PUBLISHED_POSITION_DETAIL = Host + "/user_api/labor_resume/published_position_detail";  //已发布职位详情
    String URL_TALENT_COMPLAINT = Host + "/user_api/labor_resume/complaint";  //劳务工/特种工-已发布职位详情-投诉
    String URL_TALENT_EVALUATION = Host + "/user_api/labor_resume/evaluation";  //劳务工/特种工-已发布职位-职位详情-评价
    String URL_TALENT_MATCH_RESUME_DETAIL = Host + "/user_api/labor_resume/match_resume_detail";  //劳务工/特种工-已匹配到的简历详情
    String URL_TALENT_ALL_PAY = Host + "/user_api/labor_resume/all_pay";  //劳务工/特种工-已发布职位详情-全部支付
    String URL_TALENT_REQUIRE_LIST = Host + "/user_api/talent_post/house_keep/require_list";  //首页保姆月嫂育儿嫂人才信息列表
    String URL_TALENT_WORK_REQUIRE_LIST = Host + "/user_api/talent_post/house_keep/work_require_list";  //首页保姆月嫂育儿嫂工作信息列表
    String URL_TALENT_QUERY_LAST_REQUIRE = Host + "/user_api/talent_post/house_keep/query_last_require";  //我要当保姆-点击我要当保姆
    String URL_TALENT_EDUCATION_LIST = Host + "/user_api/talent_post/house_keep/education_list";  //学历列表
    String URL_TALENT_SERVICE_CONTENT_LIST = Host + "/user_api/talent_post/house_keep/service_content_list";  //获取保姆月嫂育儿嫂服务内容
    String URL_TALENT_WORK_EXPERIENCE_LIST = Host + "/user_api/talent_post/house_keep/work_experience_list";  //从业经验列表
    String URL_TALENT_PERSON_TYPE_LIST = Host + "/user_api/talent_post/house_keep/person_type_list";  //人员类型列表
    String URL_TALENT_ADD_REQUIRE = Host + "/user_api/talent_post/house_keep/add_require";  //我要当保姆-发布我要当保姆
    String URL_TALENT_MATCH_REQUIRE_LIST = Host + "/user_api/talent_post/house_keep/match_require_list";  //我要找保姆-获取已匹配到的保姆信息列表
    String URL_TALENT_MATCH_RECRUITMENT = Host + "/user_api/talent_post/house_keep/match_recruitment";  //我要当保姆-获取系统匹配到的保姆工作
    String URL_COMMON_BM_ZHAOPIN_ORDER_DETAIL = Host + "/user_api/house_keep/recruiter_detail";  //我要找保姆-招聘方订单详情
    String URL_TALENT_GET_CONFIGURATION = Host + "/user_api/talent_post/house_keep/get_configuration";  //我要找保姆-获取发布前的配置数据
    String URL_TALENT_ADD_RECRUITMENT = Host + "/user_api/talent_post/house_keep/add_recruitment";  //我要找保姆-发布我要找保姆
    String URL_TALENT_POSTED_RECRUITMENT_LIST = Host + "/user_api/talent_post/house_keep/posted_recruitment_list";  //我要找保姆-已发布需求列表
    String URL_TALENT_RECRUITMENT_DETAIL = Host + "/user_api/talent_post/house_keep/recruitment_detail";  //我要找保姆-已发布的需求详情（用工明细）
    String URL_TALENT_MATCH_RECRUITMENT_DETAIL = Host + "/user_api/talent_post/house_keep/match_recruitment_detail";  //我要当保姆-获取系统匹配到的工作详情
    String URL_TALENT_EMPLOYEE_CONFIRM_WORK_DONE = Host + "/user_api/talent_post/house_keep/employee_confirm_work_done";  //我要当保姆-工作详情-确定完成工作
    String URL_TALENT_MATCH_REQUIRE_DETAIL = Host + "/user_api/talent_post/house_keep/match_require_detail";  //我要找保姆-获取已匹配到的保姆信息详情
    String URL_COMM_BM_ORDER_DETAIL = Host + "/user_api/house_keep/application_detail";  //我要找保姆-获取已匹配到的保姆信息详情
    String URL_TALENT_BOSS_CONFIRM_WORK_DONE = Host + "/user_api/talent_post/house_keep/boss_confirm_work_done";  //我要找保姆-保姆详情-确认完成工作
    String URL_TALENT_EVALUATION_BM = Host + "/user_api/talent_post/house_keep/evaluation";  //我要找保姆-保姆详情-确认完成工作后-评价
    String URL_TALENT_UNINTERESTED_REQUIRE = Host + "/user_api/talent_post/house_keep/uninterested_require";  //我要找保姆-点击确认不感兴趣
    String URL_TALENT_PROJECT_PAYMENTRE = Host + "/user_api/talent_post/house_keep/project_payment";  //支付工程尾款
    String URL_TALENT_ASSET_AUTHENTICATION = Host + "/user_api/marriage/asset_authentication";  //点击资产认证
    String URL_TALENT_SUBMISSION_ASSET_AUTHENTICATION = Host + "/user_api/marriage/submission_asset_authentication";  //提交认证
    String URL_TALENT_MARRIAGE_MESSAGE_LIST = Host + "/user_api/marriage/marriage_message_list";  //姻缘服务牵手成功信息
    String URL_TALENT_LOOKING_FOR_OBJECTS = Host + "/user_api/marriage/looking_for_objects";  //点击我要找对象
    String URL_TALENT_QUERY_LAST_REQUIRE_JIAJIAO = Host + "/user_api/talent_post/tutor/query_last_require";  //点击我要当家教
    String URL_TALENT_WHEN_TUTOR_LIST = Host + "/user_api/talent_post/tutor/when_tutor_list";  //家教首页人才信息列表
    String URL_TALENT_LOOK_FOR_TUTOR_LIST = Host + "/user_api/talent_post/tutor/look_for_tutor_list";  //家教首页工作信息列表
    String URL_TALENT_COACH_GRADE_LIST = Host + "/user_api/talent_post/tutor/coach_grade_list";  //辅导年级列表
    String URL_TALENT_COACH_SUBJECT_LIST = Host + "/user_api/talent_post/tutor/coach_subject_list";  //辅导科目列表
    String URL_TALENT_EDUCATION_IST = Host + "/user_api/talent_post/tutor/education_ist";  //学历列表
    String URL_TALENT_ADD_WHEN_TUTOR = Host + "/user_api/talent_post/tutor/add_when_tutor";  //我要当家教-发布我要当家教
    String URL_TALENT_MATCH_LOOKFOR_TUTOR = Host + "/user_api/talent_post/tutor/match_lookfor_tutor";  //我要当家教-系统匹配的家教工作
    String URL_TALENT_GET_CONFIGURATION_JIAJIAO = Host + "/user_api/talent_post/tutor/get_configuration";  //我要找家教-获取发布前的配置数据
    String URL_TALENT_ADD_LOOKFOR_TUTOR = Host + "/user_api/talent_post/tutor/add_lookfor_tutor";  //我要找家教-发布我要找家教
    String URL_TALENT_POSTED_LOOKFOR_TUTOR_LIST = Host + "/user_api/talent_post/tutor/posted_lookfor_tutor_list";  //我要找家教-已发布的需求列表
    String URL_TALENT_LOOKFOR_TUTOR_DETAIL = Host + "/user_api/talent_post/tutor/lookfor_tutor_detail";  //我要找家教-已发布需求详情
    String URL_TALENT_MATCH_WHEN_TUTOR_LIST = Host + "/user_api/talent_post/tutor/match_when_tutor_list";  //我要找家教-已匹配到的家教信息列表
    String URL_TALENT_MATCH_WHEN_TUTOR_DETAIL = Host + "/user_api/talent_post/tutor/match_when_tutor_detail";  //我要找家教-已匹配到家教信息详情
    String URL_JIAJIAO_YINGPIN_ORDER_DETAIL = Host + "/user_api/tutor/application_detail";  //我要当家教--应聘方订单详情
    String URL_JIAJIAO_ZHAOPIN_ORDER_DETAIL = Host + "/user_api/tutor/recruiter_detail";  //我要找家教--招聘方订单详情
    String URL_TALENT_BOSS_CONFIRM_WORK_DONE_JIAJIAO = Host + "/user_api/talent_post/tutor/boss_confirm_work_done";  //我要找家教-家教详情-确认完成工作
    String URL_TALENT_UNINTERESTED_REQUIRE_JIAJIAO = Host + "/user_api/talent_post/tutor/uninterested_require";  //我要找家教-点击不感兴趣
    String URL_TALENT_JOB_WANTED = Host + "/user_api/recruitment/job_wanted";  //点击我要求职
    String URL_QIUZHI_ZHAOPIN_INFO = Host + "/user_api/recruitment_job/detail";  //强制匹配，获取招聘方信息
    String URL_JIAJIAO_WORK_LIST_DETAIL = Host + "/user_api/tutor/apply_work";  //家教-招聘信息列表详情
    String URL_JIAJIAO_RENCAI_LIST_DETAIL = Host + "/user_api/tutor/apply_people";  //家教-人才信息列表详情
    String URL_BM_YS_YES_RENCAI_LIST_DETAIL = Host + "/user_api/house_keep/apply_people";  //保姆月嫂育儿嫂-人才信息列表详情
    String URL_BM_YS_YES_WORK_LIST_DETAIL = Host + "/user_api/house_keep/apply_work";  //保姆月嫂育儿嫂-工作信息列表详情
    String URL_TALENT_INDEX_JOP_INFORMATION_LIST = Host + "/user_api/recruitment/index_jop_information_list";  //获取求职招聘首页工作信息列表
    String URL_TALENT_INDEX_PERSONNEL_INFORMATION_LIST = Host + "/user_api/recruitment/index_personnel_information_list";  //获取求职招聘首页人才信息列表
    String URL_TALENT_APPLICATION_INFO = Host + "/user_api/hourly_worker/application_info";  //查询最后一次发布钟点工信息
    String URL_TALENT_LIST = Host + "/user_api/hourly_worker/list";  //钟点工首页/工作信息/人才信息列表
    String URL_TALENT_PROJECT_PAYMENT = Host + "/user_api/talent_post/tutor/project_payment";  //支付工程尾款
    String URL_JIAJIAO_COMFIRM_USER = Host + "/user_api/talent_post/tutor/confirm_employment";  //家教确认用工
    String URL_BM_YS_YES_COMFIRM_USER = Host + "/user_api/talent_post/house_keep/confirm_employment";  //保姆育儿嫂月嫂确认用工
    String URL_TALENT_EVALUATION_JIAJIAO = Host + "/user_api/talent_post/tutor/evaluation";  //我要找家教-家教详情-确认完成工作后-评价
    String URL_RESUME_EVALUATION = Host + "/user_api/talent_post/recruitment/evaluation";  //我要招聘-简历详情-评价
    String URL_TALENT_MATCH_LOOKFOR_TUTOR_DETAIL = Host + "/user_api/talent_post/tutor/match_lookfor_tutor_detail";  //我要当家教-系统匹配的家教工作详情
    String URL_TALENT_EMPLOYEE_CONFIRM_WORK_DONE_JIAJIAO = Host + "/user_api/talent_post/tutor/employee_confirm_work_done";  //我要当家教-工作详情-确认完成工作
    String URL_TALENT_WORK_YEAR = Host + "/user_api/recruitment/work_year";  //工作经验下拉列表
    String URL_TALENT_GET_JOB_CLASS_LIST = Host + "/user_api/common/get_job_class_list";  //职业分类列表
    String URL_TALENT_SALARY = Host + "/user_api/recruitment/salary";  //薪资待遇下拉列表
    String URL_TALENT_BENEFIT = Host + "/user_api/recruitment/benefit";  //福利下拉列表
    String URL_TALENT_RELEASE_JOB_WANTED = Host + "/user_api/recruitment/release_job_wanted";  //发布我要求职
    String URL_TALENT_GET_JOB_LIST = Host + "/user_api/recruitment/get_job_list";  //我要求职--获取匹配到的职位
    String URL_TALENT_GET_EDUCATION_BACKGROUND_LIST = Host + "/user_api/common/get_education_background_list";  //通用学历列表
    String URL_TALENT_LIST_WORK_TYPE_ZDG = Host + "/user_api/hourly_worker/list_work_type";  //钟点工服务类型下拉列表
    String URL_TALENT_APPLICATION_ZDG = Host + "/user_api/hourly_worker/application";  //发布钟点工信息(我要当钟点工)
    String URL_TALENT_MATCH_POSITION_ZDG = Host + "/user_api/hourly_worker/match_position";  //钟点工-我要找工作-系统匹配职位
    String URL_TALENT_MATCH_POSITION_DETAIL_ZDG = Host + "/user_api/hourly_worker/match_position_detail";  //钟点工-我要找工作-系统匹配职位详情
    String URL_ZDG_YINGPIN_ORDER_DETAIL = Host + "/user_api/hourly_worker/application_detail";  //钟点工-我要找工作-应聘方订单详情
    String URL_TALENT_CONFIRM_FINISH_WORK_ZDG = Host + "/user_api/hourly_worker/confirm_finish_work";  //钟点工-我要找工作-系统匹配职位详情-确认完成工作
    String URL_TALENT_RECRUITMENT_ZDG = Host + "/user_api/hourly_worker/recruitment";  //钟点工-发布招聘
    String URL_TALENT_PUBLISHED_POSITION_ZDG = Host + "/user_api/hourly_worker/published_position";  //钟点工-已发布职位
    String URL_TALENT_MATCH_RESUME_ZDG = Host + "/user_api/hourly_worker/match_resume";  //钟点工-已发布职位
    String URL_TALENT_MATCH_RESUME_DETAIL_ZDG = Host + "/user_api/hourly_worker/match_resume_detail";  //钟点工-已发匹配简历详情
    String URL_TALENT_GET_JOB_CLASS_LIST_COMMON = Host + "/user_api/common/get_job_class_list";  //职业分类列表
    String URL_TALENT_GET_JOB_DETAILS = Host + "/user_api/recruitment/get_job_details";  //我要求职--获取匹配到的职位详情
    String URL_TALENT_UNINTERESTED_JOB = Host + "/user_api/recruitment/uninterested_job";  //我要求职--点击不感兴趣
    String URL_QIUZHI_RENCAI_LIST_DETAIL = Host + "/user_api/recruitment_resume/detail";  //求职招聘-人才列表详情(强制匹配)
    String URL_TALENT_UNINTERESTED_JOB_SEEKER = Host + "/user_api/recruitment/uninterested_job_seeker";  //我要招聘--点击不感兴趣
    String URL_TALENT_GET_JOB_SEEKER_CONTACT_INFORMATION = Host + "/user_api/recruitment/get_job_seeker_contact_information";  //我要招聘--获取联系方式
    String URL_TALENT_GET_JOB_CONTACT_INFORMATION = Host + "/user_api/recruitment/get_job_contact_information";  //我要求职--点击获取联系方式
    String URL_TALENT_PUBLISHED_JOB_LIST = Host + "/user_api/recruitment/published_job_list";  //我要招聘--查询已发布的职位
    String URL_TALENT_ALL_JOB_SEEKER_DETAILS = Host + "/user_api/recruitment/all_job_seeker_details";  //我要招聘--查询所有已匹配到的简历
    String URL_TALENT_RELEASER_RECRUIT = Host + "/user_api/recruitment/releaser_recruit";  //发布我要招聘
    String URL_TALENT_PUBLISHED_JOB_DETAILS = Host + "/user_api/recruitment/published_job_details";  //我要招聘--查询已发布的职位详情
    String URL_QIUZHI_YINGPIN_ORDER_DETAIL = Host + "/user_api/recruitment/application_detail";  //我要求职--应聘方订单详情
    String URL_QIUZHI_ZHAOPIN_ORDER_DETAIL = Host + "/user_api/recruitment/recruiter_detail";  //求职招聘--招聘方订单详情
    String URL_YINYUAN_ORDER_DETAIL = Host + "/user_api/marriage/application_detail";  //姻缘-我要找对象详情
    String URL_TALENT_JOB_SEEKER_DETAILS = Host + "/user_api/recruitment/job_seeker_details";  //我要招聘--查询已发布职位中已匹配到的简历详情
    String URL_TALENT_GET_MONTHLY_INCOME_LIST = Host + "/user_api/marriage/get_monthly_income_list";  //月收入列表
    String URL_TALENT_PUBLISHED_POSITION_DETAIL_ZDG = Host + "/user_api/hourly_worker/published_position_detail";  //钟点工-已发布职位详情
    String URL_TALENT_ALL_PAY_ZDG = Host + "/user_api/hourly_worker/all_pay";  //钟点工-已发布职位详情-全部支付
    String URL_TALENT_COMPLAINT_ZDG = Host + "/user_api/hourly_worker/complaint";  //钟点工-已发布职位详情-投诉
    String URL_TALENT_CONFIRM_PAY_ZDG = Host + "/user_api/hourly_worker/confirm_pay";  //钟点工-已发布职位详情-确认支付
    String URL_TALENT_EVALUATION_ZDG = Host + "/user_api/hourly_worker/evaluation";  //钟点工-已发布职位-职位详情-评价
    String URL_TALENT_RELEASE_LOOKING_FOR_OBJECTS = Host + "/user_api/marriage/release_looking_for_objects";  //发布我要找对象
    String URL_TALENT_MARRIAGE_MATCH_LIST = Host + "/user_api/marriage/marriage_match_list";  //获取已匹配到的姻缘对象列表
    String URL_TALENT_GET_MATCH_ORDER_LIST = Host + "/user_api/user_center/get_match_order_list";  //订单列表
    String URL_TALENT_GET_MATCH_ORDER_LIST_NEW = Host + "/user_api/user_center/get_match_order_list_new";  //新的订单列表，改了数据格式
    String URL_TALENT_ADD_REQUIRE_FW = Host + "/user_api/smart_home/add_require";  //发布需求
    String URL_TALENT_MARRIAGE_MATCH_DETAIL = Host + "/user_api/marriage/marriage_match_detail";  //获取已匹配到的姻缘对象详情
    String URL_TALENT_UNINTERESTED_REQUIRE_YINYUAN = Host + "/user_api/marriage/uninterested_require";  //点击不感兴趣 姻缘
    String URL_TALENT_GET_CONTACT_INFORMATION = Host + "/user_api/marriage/get_contact_information";  //点击获取联系方式 姻缘
    String URL_TALENT_INDEX_CLASS_LIST = Host + "/user_api/common/index_class_list";  //分类接口
    String URL_TALENT_FINISH_SMARTHOMEORDER = Host + "/user_api/user_center/finish_smartHomeOrder";  //发布人确认支付智慧生活订单
    String URL_TALENT_REQUIRE_ORDER_DETAIL = Host + "/user_api/smart_home/require_order_detail";  //智慧生活订单详情
    String URL_DINGCAN_REFUSE_ORDER = Host + "/user_api/smart_home/cancel_service";  //智慧生活-订餐酒店门票商家拒绝订单
    String URL_DINGCAN_CONFIRM_ORDER = Host + "/user_api/smart_home/confirm_service";  //智慧生活-订餐酒店门票商家确认订单
    String URL_DINGCAN_RECOMMEND_LIST = Host + "/user_api/smart_home/recommend_service";  //智慧生活-订餐酒店门票商家确认订单
    String URL_TALENT_DINGCAN_SERVICE_LIST = Host + "/user_api/smart_home/service_list";  //获取智慧生活订餐酒店用餐列表
    String URL_TALENT_DINGCAN_SERVICE_YUDING = Host + "/user_api/smart_home/booking_service";  //获取智慧生活订餐酒店预定
    String URL_TALENT_COMPLAINT_SMART = Host + "/user_api/smart_home/complaint";  //投诉-点击确认拨打电话
    String URL_TALENT_EMPLOYEE_FINISH_SMARTHOMEORDER = Host + "/user_api/user_center/employee_finish_smartHomeOrder";  //抢单人-任务完成确认
    String URL_TALENT_CANCEL_REQUIRE = Host + "/user_api/smart_home/cancel_require";  //发布人取消智慧生活需求订单
    String URL_TALENT_CONVENIEN_SEARCH = Host + "/user_api/convenien_service/convenien_search";  //便民服务搜索
    String URL_TALENT_INITIAL_VALUE = Host + "/user_api/common/initial_value";  //滚动数值参数
    String URL_TALENT_NOTICE_GET_POSITION = Host + "/user_api/smart_home/notice_get_position";  //招聘方发通知给应聘方共享位置
    String URL_TALENT_GET_POSITION = Host + "/user_api/smart_home/get_position";  //招聘方获取经纬度
    String URL_TALENT_SEND_POSITION = Host + "/user_api/smart_home/send_position";  //应聘方上传经纬度
    String URL_TALENT_PROMOTE_RESUME = Host + "/user_api/user_center/promote_resume";  //推广奖励接口

    //*************************************智慧家庭*****************************************************************
    String URL_REQUIRE_LIST = Host + "/user_api/smart_home/require_list";  // 智慧需求家庭列表
    String URL_REQUIRE_DETAIL = Host + "/user_api/smart_home/require_detail";  // 智慧家庭详情
    String URL_COLLECT_REQUIRE = Host + "/user_api/smart_home/collect_require";  // 收藏智慧家庭
    String URL_CANCEL_COLLECT_REQUIRE = Host + "/user_api/smart_home/cancel_collect_require";  // 取消收藏智慧家庭
    String URL_REQUIRE_CLASS_LIST = Host + "/user_api/smart_home/require_class_list";  // 获取需求分类
    String URL_ADD_REQUIRE = Host + "/user_api/smart_home/add_require";  // 发布需求
    String URL_DELETE_REQUIRE_1 = Host + "/user_api/smart_home/delete_require";  // 删除需求
    String URL_EDIT_REQUIRE_1 = Host + "/user_api/smart_home/edit_require";  // 编辑需求
    String URL_BOOK_HOTEL = Host + "/user_api/hotel/book_hotel";  // 预定前
    String URL_ROB_REQUIRE = Host + "/user_api/smart_home/rob_require";  // 抢单
    String URL_CANCEL_REQUIRE = Host + "/user_api/user_center/cancel_smartHomeOrder";  // 取消订单
    String URL_SURE_REQUIRE = Host + "/user_api/user_center/affirm_smartHomeOrder";  // 确定订单
    String URL_ONFINISH_REQUIRE = Host + "/user_api/user_center/noFinish_smartHomeOrder";  // 未完成
    String URL_FINISH_REQUIRE = Host + "/user_api/user_center/finish_smartHomeOrder";  // 完成
    String URL_CANCEL_PERSON = Host + "/user_api/smart_home/cancel_require_user";  // 取消抢单人
    String URL_CAN_ROB_REQUIRE = Host + "/user_api/smart_home/can_rob_require";  // 抢单支付前调用，判断是否可以抢单支付
    String URL_SMART_EVALUATION = Host + "/user_api/smart_home/evaluation";  //智慧生活-评价
    String URL_SMART_FDR_COMPLETE = Host + "/user_api/user_center/order_finish_smartHomeOrder";  //智慧生活-发单人-确认完成

    //*************************************房屋租售*****************************************************************
    String URL_HOUS_SALE_LIST = Host + "/user_api/house_sale/house_sale_list";  // 获取需求分类
    String URL_HOUS_PRICE_CLASS = Host + "/user_api/house_sale/common/price_condition";  // 价格分类
    String URL_HOUS_MORE = Host + "/user_api/house_rent/more_condition";  // 更多的分类
    String URL_HOUSE_PUBLISH = Host + "/user_api/house/issue_house_demand";  // 发布房屋
    String URL_HOUSE_DETAIL = Host + "/user_api/house_rent_sale/house_detail";  // 房屋详情
    String URL_HOUSE_COLLECT = Host + "/user_api/house_rent_sale/house_collect";  // 房屋详情
    String URL_HOUSE_CANCEL_COLLECT = Host + "/user_api/house_rent_sale/cancel_collect_house";  // 房屋详情
    String URL_DELETE_HOUSE = Host + "/user_api/house_rent_sale/delete_house";  // 删除发布
    String URL_EDIT_HOUSE = Host + "/user_api/house/edit_house_demand";  // 编辑房屋
    String URL_HOUSE_FONFID = Host + "/user_api/house_sale/common/configuration";  // 房屋租房配置
    String URL_HOUSE_TYPE = Host + "/user_api/house_rent/house_type_list";  // 房屋类型


    //*************************************旅游住宿*****************************************************************
    String URL_TRAVEL_LIST = Host + "/user_api/travel/travel_list";  // 旅游列表(全部或单独分类)
    String URL_TRAVEL_DETAIL = Host + "/user_api/travel/travel_detail";  // 旅游详情
    String URL_COLLECT_TRAVEL = Host + "/user_api/travel/collect_travel";  // 收藏旅游
    String URL_DELETE_TRAVEL = Host + "/user_api/travel/delete_travel";  //删除旅游
    String URL_CANCEL_COLLECT_TRAVEL = Host + "/user_api/travel/cancel_collect_travel";  // 取消收藏旅游
    String URL_TRAVEL_CLASS_LIST = Host + "/user_api/travel/travel_class_list";  // 旅游分类列表
    String URL_TRAVEL_AREA_LIST = Host + "/user_api/travel/travel_area_list";  // 旅游地区列表
    String URL_PUBLISH_TRAVEL = Host + "/user_api/travel/add_travel";  // 发布旅游
    String URL_EDIT_TRAVEL = Host + "/user_api/travel/edit_travel";  // 编辑旅游
    String URL_TRAVEL_PRICE_LIST = Host + "/user_api/travel/travel_price_list";  // 旅游价格
    String URL_HOTEL_LIST = Host + "/user_api/hotel/hotel_list";  // 酒店列表
    String URL_RECOMMEND_HOTEL_LIST = Host + "/user_api/hotel/recommend_hotel_list";  // 首页推荐酒店列表
    String URL_HOTEL_TYPE_LIST = Host + "/user_api/hotel/hotel_type_list";  // 酒店类型列表
    String URL_HOUSE_TYPE_LIST = Host + "/user_api/hotel/home_hotel_type_list";  // 房源类型列表
    String URL_HOTEL_PRICE_LIST = Host + "/user_api/hotel/hotel_price_list";  // 酒店价格数据
    String URL_HOTEL_DETAIL = Host + "/user_api/hotel/hotel_detail";  // 酒店详情
    String URL_ADD_HOTEL = Host + "/user_api/hotel/add_hotel";  // 发布酒店
    String URL_HOTEL_FACILITY = Host + "/user_api/hotel/hotel_facility_list";  // 酒店设施
    String URL_COLLECT_HOTEL = Host + "/user_api/hotel/collect_hotel";  // 收藏酒店
    String URL_CANCEL_COLLECT_HOTEL = Host + "/user_api/hotel/cancel_collect_hotel";  // 取消收藏酒店
    String URL_SEARCH_HOTEL_AND_TRAVEL = Host + "/user_api/travel/search_hotel_and_travel";  // 搜索

    //*************************************车辆买卖*****************************************************************
    String URL_CAR_CLASS_LIST = Host + "/user_api/car_deal/car_class_list";  // 获取车牌数据
    String URL_CAR_PRICE_LIST = Host + "/user_api/car_deal/car_price_list";  // 获取车辆价格
    String URL_RECOMMEND_CAR_LIST = Host + "/user_api/car_deal/recommend_car_list";  // 获取推荐车辆
    String URL_BUY_CAR_LIST = Host + "/user_api/car_deal/buy_car_list";  // 买车需求列表
    String URL_CAR_LIST = Host + "/user_api/car_deal/car_list";  // 车辆列表
    String URL_CAR_DETAIL = Host + "/user_api/car_deal/car_detail";  // 车辆详情
    String URL_MORE_CAR_LIST = Host + "/user_api/car_deal/more_class_list";  // 筛选
    String URL_CAR_COLLECT = Host + "/user_api/car_deal/car_collect";  // 收藏
    String URL_CAR_COLLECT_CANCEL = Host + "/user_api/car_deal/car_cancel_collect";  // 取消车辆收藏
    String URL_BUY_CAR_DETAIL = Host + "/user_api/car_deal/buy_car_detail";  // 买车详情
    String URL_BUY_CAR_COLLECT = Host + "/user_api/car_deal/buy_car_collect";  // 买车收藏
    String URL_BUY_CAR_CANCEL_COLLECT = Host + "/user_api/car_deal/buy_car_cancel_collect";  // 买车取消收藏
    String URL_SALE_CAR = Host + "/user_api/car_deal/sale_car";  // 发布卖车
    String URL_EDIT_SALE_CAR = Host + "/user_api/car_deal/edit_sale_car";  // 编辑卖车
    String URL_BUY_CAR = Host + "/user_api/car_deal/buy_car";  // 发布买车
    String URL_EDIT_BUY_CAR = Host + "/user_api/car_deal/edit_buy_car";  // 编辑我要买车
    String URL_CAR_RENT_LIST = Host + "/user_api/car_deal/car_rent_list";  // 租车信息列表
    String URL_CAR_OWNER_ISSUE = Host + "/user_api/car_deal/car_owner_issue";  // 我是车主发布信息
    String URL_EDIT_CAR_OWNER_ISSUE = Host + "/user_api/car_deal/edit_car_owner_issue";  // 我是车主编辑租车信息
    String URL_CAR_PASSENGER_ISSUE = Host + "/user_api/car_deal/car_passenger_issue";  // 我是乘客发布信息
    String URL_EDIT_CAR_PASSENGER_ISSUE = Host + "/user_api/car_deal/edit_car_passenger_issue";  // 我是乘客编辑租车信息
    String URL_CAR_RENT_DETAIL = Host + "/user_api/car_deal/car_rent_detail";  // 租车详细信息
    String URL_CAR_RENT_COLLECT = Host + "/user_api/car_deal/car_rent_collect";  // 租车收藏
    String URL_CAR_RENT_CANCEL_COLLECT = Host + "/user_api/car_deal/car_rent_cancel_collect";  // 租车取消收藏
    String URL_DELETE_CAR_DETAIL = Host + "/user_api/car_deal/delete_car_detail";  // 删除车辆信息
    String URL_DELETE_BUY_CAR_DETAIL = Host + "/user_api/car_deal/delete_buy_car_detail";  // 删除我要买车
    String URL_DELETE_CAR_RENT_DETAIL = Host + "/user_api/car_deal/delete_car_rent_detail";  // 删除租车信息（车主和乘客都是这个接口）

    //*************************************便民服务*****************************************************************
    String URL_HOME_CUSTOMER_CLASSIC_LIST = Host + "/user_api/convenien_service/convenien_type_list";  // 便民服务分类
    String URL_HOME_CUSTOMER_LIST = Host + "/user_api/convenien_service/convenien_classid";  // 便民服务列表页面


    //*************************************保险服务*****************************************************************
    String URL_INSURANCE_TYPE_LIST = Host + "/user_api/insurance/list_insurance_type";  // 险种列表
    String URL_INSURANCE_LIST = Host + "/user_api/insurance/list";  // 保险列表
    String URL_ADD_INSURANCE = Host + "/user_api/insurance/add";  // 发布保险信息
    String URL_INSURANCE_DETAIL = Host + "/user_api/insurance/detail";  // 保险详情
    String URL_INSURANCE_COLLECT = Host + "/user_api/insurance/collect";  // 收藏保险详情
    String URL_INSURANCE_CANCEL_COLLECT = Host + "/user_api/insurance/cancel_collect";  // 取消收藏保险详情
    String URL_INSURANCE_DELETE = Host + "/user_api/insurance/delete";  // 删除保险服务
    String URL_HEALTH_LIST = Host + "/user_api/health/health_list";  // 大健康列表
    String URL_HEALTH_DETAIL = Host + "/user_api/health/health_detail";  // 大健康列表
    String URL_BUY_HEALTH = Host + "/user_api/health/app_buy_health";  // 购买大健康


    //*************************************名优特产*****************************************************************
    String URL_SPECIALTY_LIST = Host + "/user_api/specialty/specialty_list";  // 名优特产列表
    String URL_CATEGORY_LIST = Host + "/user_api/specialty/category_list";  // 特产列表---类目筛选条件列表
    String URL_SPECIALTY_DETAIL = Host + "/user_api/specialty/specialty_detail";  // 特产详情
    String URL_COLLECT_COMMODITY = Host + "/user_api/specialty/collect_commodity";  // 店铺详情----商品收藏(该接口需要登录访问)
    String URL_COLLECT_STORE = Host + "/user_api/specialty/collect_store";  // 店铺详情----店铺收藏(该接口需要登录访问)
    String URL_COMMODITY_CATEGORY_LIST = Host + "/user_api/specialty/commodity_category_list";  // 店铺详情--商品分类列表
    String URL_STORE_COMMODITY_LIST = Host + "/user_api/specialty/store_commodity_list";  // 店铺详情--商品分类列表
    String URL_STORE_INFO = Host + "/user_api/specialty/store_info";  // 店铺信息(商品详情->>进店逛逛里面的dian'p)
    String URL_ADD_SHOPPING_TO_CART = Host + "/user_api/specialty/add_shopping_cart_commodity";  // 将商品加入购物车
    String URL_SHOPPING_CART_DETAIL = Host + "/user_api/specialty/shopping_cart_detail";  // 购物车详情
    String URL_EDIT_SHOPPING_CART = Host + "/user_api/specialty/edit_shopping_cart";  // 编辑购物车
    String URL_BEFORE_ADD_ORDER = Host + "/user_api/specialty/before_add_order";  // 立即购买/购物车里点击去结算 -下单前请求数据
    String URL_SHOPPING_ADDRESS_LIST = Host + "/user_api/user_center/shipping_address_list";  // 收货地址列表
    String URL_GET_AREA_LIST = Host + "/user_api/user_center/get_areaList_by_parentId";  // 根据地址id获取下级的地址列表
    String URL_ADD_SHOPPING_ADDRESS = Host + "/user_api/user_center/add_shipping_address";  // 添加收货地址
    String URL_EDIT_SHOPPING_ADDRESS = Host + "/user_api/user_center/edit_shipping_address";  // 编辑收货地址
    String URL_ADD_ORDER = Host + "/user_api/specialty/add_order";  // 提交订单
    String URL_USE_COUPON_ORDER = Host + "/user_api/specialty/order_user_coupon";  // 订单使用优惠券，重新获取支付金额
    String URL_SPECIALTY_ORDER_LIST = Host + "/user_api/specialty/order_list";  // 特产订单列表
    String URL_DELETE_SPECIALTY_ORDER = Host + "/user_api/specialty/delete_order";  // 删除订单
    String URL_CLOSE_SPECIALTY_ORDER = Host + "/user_api/specialty/close_order";  // 关闭订单
    String URL_CONFIRM_SPECIALTY_ORDER = Host + "/user_api/specialty/confirm_order";  // 确认收货
    String URL_SPECIALTY_ORDER_DETAIL = Host + "/user_api/specialty/order_detail";  // 特产订单详情
    String URL_LOGISTICS_INFO = Host + "/user_api/logisticsInfo/logistics_info";  // 查看物流
    String URL_APPLY_REASON_LIST = Host + "/user_api/specialty/reason_list";  // 获取退款退货原因列表
    String URL_APPLY_SALE_REFUND = Host + "/user_api/specialty/apply_sale_refund";  // 申请售后
    String URL_AFTER_SALE_LIST = Host + "/user_api/specialty/after_sales_list";  // 退款售后列表
    String URL_AFTER_SALE_DETAIL = Host + "/user_api/specialty/refund_detail";  // 售后详情
    String URL_REFUND_DELIVERY = Host + "/user_api/specialty/refund_delivery";  // 退货发货
    String URL_GOODS_CLASS_LIST = Host + "/user_api/specialty/commodity_category_list";  // 店铺详情--商品分类列表
    String URL_SPECIALTY_HOME_RECOMMEND_LIST = Host + "/user_api/specialty/specialty_index_recommend";  // 特产首页推荐分类商品
    String URL_GUESS_YOU_LIKE = Host + "/user_api/specialty/app_recommend_specialty_list";  // 猜你喜欢列表
    String URL_SPECIALTY_HOME_SECKILL_LIST = Host + "/user_api/specialty/index_spike_list";  // 特产首页秒杀列表
    String SPIKE_LIST = Host + "/user_api/specialty/spike_list";  // 秒杀列表

    //*************************************首页*****************************************************************
    String URL_HOME_LIST = Host + "/user_api/index/system_banner/banner_list";//新版首页
    String URL_IS_GET_HONGBAO = Host + "/user_api/common/is_get_coupon";//判断是否有优惠券红包领取
    String URL_GET_COUPON = Host + "/user_api/common/get_coupon";//领取优惠券红包
    String URL_IS_HAVE_HONGBAO = Host + "/user_api/common/have_red_cash";//是否有现金红包抢
    String URL_GET_MONEY_HONGBAO = Host + "/user_api/common/get_red_cash";//是否抢到现金红包
    String URL_HOME_BOTTOM_DATA = Host + "/user_api/common/get_popular_list";//首页底部-当地热门

    //*************************************资本管理*****************************************************************
    String URL_CAPITAL_LIST = Host + "/user_api/capital/list_capital";  // 产品和放贷列表
    String URL_QUERY_CONDITION = Host + "/user_api/capital/query_condition";  // 查询条件(产品类型,期限)
    String URL_CAPITAL_DETAIL = Host + "/user_api/capital/detail";  // 资本详情
    String URL_CAPITAL_COLLECT = Host + "/user_api/capital/collect";  // 收藏资本管理
    String URL_CAPITAL_CANCEL_COLLECT = Host + "/user_api/capital/cancel_collect";  // 删除收藏资本管理
    String URL_CAPITAL_ADD = Host + "/user_api/capital/add";  // 发布产品和资金放贷
    String URL_CAPITAL_DELETE = Host + "/user_api/capital/delete";  // 删除资本信息


    //*************************************法律专家*****************************************************************
    String URL_LEGAL_EXPERT_TYPE = Host + "/user_api/legal_expert/list_legal_expert_type";  // 查询法律专家类型
    String URL_LEGAL_EXPERT_ADD = Host + "/user_api/legal_expert/add";  // 添加法律专家需求
    String URL_LEGAL_EXPERT_EDIT = Host + "/user_api/legal_expert/edit";  // 编辑法律专家需求
    String URL_LEGAL_EXPERT_DETAIL = Host + "/user_api/legal_expert/detail";  // 法律专家详情
    String URL_LEGAL_EXPERT_DELETE = Host + "/user_api/legal_expert/delete";  // 删除法律专家


    //*************************************思想智库*****************************************************************
    String URL_REQUIRE_HOTEL = Host + "/user_api/intelligence_library/require_list";  // 思想列表
    String URL_SMART_FIRE = Host + "/user_api/smart_fire/list";  // 智慧消防列表
    String URL_CLASS_LIST = Host + "/user_api/intelligence_library/class_list";  // 思想智库分类
    String URL_CONSUMPTION_TYPE_LIST = Host + "/user_api/smart_fire/consumption_type_list";  // 智慧消防分类
    String URL_CLASS_LIST_TWO = Host + "/user_api/intelligence_library/index_class_list";  // 思想智库分类
    String URL_IDEA_DETAIL = Host + "/user_api/intelligence_library/require_detail";  // 思想智库详情
    String URL_ADD_IDEATHINK = Host + "/user_api/intelligence_library/add_require";  // 发布思想智库
    String URL_COLLECT_IDEA = Host + "/user_api/intelligence_library/collect_require";  // 发布思想智库
    String URL_CANCEL_COLLECT_IDEA = Host + "/user_api/intelligence_library/cancel_collect_require";  // 发布思想智库
    String URL_DELETE_REQUIRE = Host + "/user_api/intelligence_library/delete_require";  // 删除思想智库
    String URL_EDIT_REQUIRE = Host + "/user_api/intelligence_library/edit_require";  // 编辑思想智库
    String URL_ADD_SMART_FIRE = Host + "/user_api/smart_fire/add";  // 智慧消防发布
    String URL_CANCEL_COLLECT = Host + "/user_api/smart_fire/cancel_collect";  // 取消智慧消防收藏
    String URL_COLLECT = Host + "/user_api/smart_fire/collect";  // 收藏智慧消防
    String URL_SMART_FIRE_DETAIL = Host + "/user_api/smart_fire/detail";  // 智慧消防详情

    //*************************************我的*****************************************************************
    String URL_GET_MY_INFO = Host + "/user_api/user_center/user_info";  // 获取我的个人信息
    String URL_EDIT_MY_INFO = Host + "/user_api/user_center/edit_user_info";  // 编辑我的个人信息
    String URL_MY_ISSUE = Host + "/user_api/user_center/my_issue";  // 我的发布
    String URL_MY_COLLECT = Host + "/user_api/user_center/my_collect";  // 我的收藏
    String URL_DELETE_MY_COLLECT = Host + "/user_api/user_center/delete_my_collect";  // 我的收藏
    String URL_MY_Order = Host + "/user_api/user_center/my_order";  // 我的订单
    String URL_ORDER_DETAIL = Host + "/user_api/user_center/smarthome_order_detail";  // 订单详情
    String URL_URGE_SMARTHOMEORDER = Host + "/user_api/user_center/urge_smartHomeOrder";  // 催单-智慧家庭
    String URL_EMPLOYEE_CANCEL_ORDER = Host + "/user_api/user_center/employee_cancel_order";  // 抢单人取消订单
    String URL_ORDER_HOTEL_DETAIL = Host + "/user_api/user_center/hotel_order_detail";  // 酒店景丹详情
    String URL_FINISH_SMARTHOMEORDER = Host + "/user_api/user_center/employee_finish_smartHomeOrder";  // 智慧家庭完成订单
    String URL_PERSONAL_AUTHENTICATION = Host + "/user_api/user_center/user_authenticate";  // 个人认证
    String URL_COMPANY_AUTHENTICATION = Host + "/user_api/user_center/user_enterprise_authenticate";  //企业认证
    String URL_MODULE_INTRODUCTION = Host + "/user_api/user_center/module_introduce";  //服务中心-模块介绍
    String URL_FEEDBACK = Host + "/user_api/user_center/leave_message";  //服务中心-留言反馈
    String URL_SERVICE_PHONE = Host + "/user_api/user_center/service_hotline";  //服务中心-客服电话
    String URL_ABOUT_US = Host + "/user_api/user_center/about_us";  //关于我们
    String URL_COUNT_ORDER_NUMBER = Host + "/user_api/specialty/count_order_number";  //统计待付款待收货待发货已取消退款的订单个数
    String URL_LOGOUT = Host + "/user_api/logout";  //退出登录
    String URL_MODULE_INTRODUCE = Host + "/user_api/user_center/module_introduce";  //模块介绍
    String URL_COMMIT_DUIHUAN_CODE = Host + "/user_api/user_center/redemption_code";  //提交兑换码
    String URL_COMMIT_DUIHUAN_H5 = Host + "/user_api/user_center/up_code";  //兑换码H5页面

    //*************************************钱包*****************************************************************
    String URL_MY_WALLET = Host + "/user_api/user_center/my_wallet";  // 我的钱包
    String URL_MY_WALLET_DETAIL_LIST = Host + "/user_api/user_center/my_wallet_list";  // 我的钱包明细列表
    String URL_MY_WALLET_DETAIL_LIST_DETAIL = Host + "/user_api/user_center/wallet_detail";  // 我的钱包明细列表详情
    String URL_ADD_WITHDRAW_ACCOUNT = Host + "/user_api/user_center/add_account";  // 添加提现账号
    String URL_APPLY_WITHDRAW = Host + "/user_api/user_center/apply_withdrawal";  // 申请提现
    String URL_WITHDRAW_ACCOUNT_LIST = Host + "/user_api/user_center/account_list";  // 提现列表
    String URL_DELETE_ACCOUNT = Host + "/user_api/user_center/delete_account";  // 删除提现账号
    String URL_CANCEL_HOTEL_ORDER = Host + "/user_api/user_center/cancel_hotel_order";  // 取消酒店订单
    String URL_CDELETE_HOTEL_ORDER = Host + "/user_api/user_center/delete_order";  // 删除订单
    String URL_TUIGUANG_WALLET_DETAIL_LIST = Host + "/user_api/user_center/my_promot_list";  // 推广已获得奖励列表


    //*************************************资金托管*****************************************************************
    String URL_TRUSTEESHIP_LIST = Host + "/user_api/trusteeship/list";  // 资金托管列表
    String URL_ADD_TRUSTEESHIP = Host + "/user_api/trusteeship/add";  // 添加资金托管
    String URL_TRUSTEESHIP_DETAIL = Host + "/user_api/trusteeship/detail";  // 资金托管详情
    String URL_CANCEL_TRUSTEESHIP = Host + "/user_api/trusteeship/cancel";  // 取消托管
    String URL_REPORT_TRUSTEESHIP = Host + "/user_api/trusteeship/report";  // 举报(资金托管)
    String URL_RETURN_REPORT_TRUSTEESHIP = Host + "/user_api/trusteeship/withdraw_report";  // 撤回举报
    String URL_AGREE_REFUND = Host + "/user_api/trusteeship/agree_report";  // 同意退款
    String URL_REFUSE_REFUND = Host + "/user_api/trusteeship/refuse_report";  // 拒绝退款

    //*************************************优惠券*****************************************************************
    String URL_MY_COUPON = Host + "/user_api/user_center/coupon_list";  // 我的优惠券列表
    String URL_USE_COUPON = Host + "/user_api/order/order_use_coupon";  // 使用优惠券，重新计算金额显示

    //*************************************客服模块*****************************************************************
    String URL_CUSTOMER_LIST = Host + "/user_api/customer/list_customer";  // 客服列表
    String URL_GET_CUSTOMER_LIST = Host + "/user_api/user_center/customer_url";  // 返回客服聊天请求地址
}
