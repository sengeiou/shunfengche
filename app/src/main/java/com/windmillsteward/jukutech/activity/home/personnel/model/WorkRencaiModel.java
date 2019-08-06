package com.windmillsteward.jukutech.activity.home.personnel.model;

import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * @date: on 2018/10/6
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class WorkRencaiModel  {
    /**
     * totalRow : 1
     * pageNumber : 1
     * firstPage : true
     * lastPage : true
     * totalPage : 1
     * pageSize : 5
     * list : [{"recruitment_id":29,"work_third_area_name":"天河区","update_time":"2018-10-10","title":"招聘水泥工3名,搬砖工4名,特级搬砖工4名","add_time":1539152935,"work_second_area_name":"广州市"}]
     */

    private int totalRow;
    private int pageNumber;
    private boolean firstPage;
    private boolean lastPage;
    private int totalPage;
    private int pageSize;
    private List<ListBean> list;

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }


    public static class ListBean implements MultiItemEntity{
        /**
         * recruitment_id : 29
         * work_third_area_name : 天河区
         * update_time : 2018-10-10
         * title : 招聘水泥工3名,搬砖工4名,特级搬砖工4名
         * add_time : 1539152935
         * work_second_area_name : 广州市
         */
        private int type;
        private int recruitment_id;
        private int when_tutor_id;
        private String work_third_area_name;
        private String update_time;
        private String title;
        private int add_time;
        private String time;
        private String work_second_area_name;
        private String education_background_name;
        private String coach_subject_name;
        private List<String> service_contents;
        private String salary;
        private String person_name;
        private String service_name;
        private List<String> work_type_ids;

        public List<String> getWork_type_ids() {
            return work_type_ids;
        }

        public void setWork_type_ids(List<String> work_type_ids) {
            this.work_type_ids = work_type_ids;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getPerson_name() {
            return person_name;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public int getWhen_tutor_id() {
            return when_tutor_id;
        }

        public void setWhen_tutor_id(int when_tutor_id) {
            this.when_tutor_id = when_tutor_id;
        }

        public List<String> getService_contents() {
            return service_contents;
        }

        public void setService_contents(List<String> service_contents) {
            this.service_contents = service_contents;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getCoach_subject_name() {
            return coach_subject_name;
        }

        public void setCoach_subject_name(String coach_subject_name) {
            this.coach_subject_name = coach_subject_name;
        }

        /**
         * sex : 1
         * info_id : 10
         * name : 特种工-啊明
         * age : 22
         */

        private int sex;
        private int info_id;
        private String name;
        private int age;

        /**
         * work_third_area_id : 3040
         * area_name : 广州市天河区
         * require_id : 1
         * sex_name : 男
         * user_name : 方小小
         * work_second_area_id : 289
         * user_avatar_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg
         */

        private int work_third_area_id;
        private String area_name;
        private int require_id;
        private String sex_name;
        private String work_hour_name;
        private String user_name;
        private int work_second_area_id;
        private String user_avatar_url;

        /**
         * work_experience : 30
         */

        private int work_experience;
        //求职招聘模块
        /**
         * job_class_id_two : 86
         * recruitment_num : 3
         */

        private String job_class_id_two;
        private int recruitment_num;
        private int salary_type;
        private int resume_id;
        private String benefit_name;
        private String end_salary_fee;
        private String salary_fee;
        private String job_name;
        private String company_name;

        /**
         * true_name : 小白
         */

        private String true_name;
        /**
         * lookfor_bell_worker_id : 1
         * recruit_number : 3
         * add_time : 2018-10-12
         */

        //特种工
        private int lookfor_bell_worker_id;
        private int recruit_number;
        /**
         * when_bell_worker_id : 1
         */

        private int when_bell_worker_id;
        private int look_for_tutor_id;
        private int job_id;
        /**
         * end_salary_fee : 0.1
         * total_match_num : 4
         * work_type_name : 搬砖工/砌墙工/油漆工/泥洼工
         * salary_fee : 0.1
         * user_id : 194
         * add_time : 1558141059000
         * enterprise_name : 个人
         */

        private int total_match_num;
        private String work_type_name;
        private int user_id;
        private String enterprise_name;

        public String getWork_hour_name() {
            return work_hour_name;
        }

        public void setWork_hour_name(String work_hour_name) {
            this.work_hour_name = work_hour_name;
        }

        public int getResume_id() {
            return resume_id;
        }

        public void setResume_id(int resume_id) {
            this.resume_id = resume_id;
        }

        public String getEducation_background_name() {
            return education_background_name;
        }

        public void setEducation_background_name(String education_background_name) {
            this.education_background_name = education_background_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public String getBenefit_name() {
            return benefit_name;
        }

        public void setBenefit_name(String benefit_name) {
            this.benefit_name = benefit_name;
        }

        public String getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(String end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
        }

        public String getSalary_fee() {
            return salary_fee;
        }

        public void setSalary_fee(String salary_fee) {
            this.salary_fee = salary_fee;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
        }

        public int getLook_for_tutor_id() {
            return look_for_tutor_id;
        }

        public void setLook_for_tutor_id(int look_for_tutor_id) {
            this.look_for_tutor_id = look_for_tutor_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getRecruitment_id() {
            return recruitment_id;
        }

        public void setRecruitment_id(int recruitment_id) {
            this.recruitment_id = recruitment_id;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }

        @Override
        public int getItemType() {
            return type;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getInfo_id() {
            return info_id;
        }

        public void setInfo_id(int info_id) {
            this.info_id = info_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getWork_third_area_id() {
            return work_third_area_id;
        }

        public void setWork_third_area_id(int work_third_area_id) {
            this.work_third_area_id = work_third_area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getRequire_id() {
            return require_id;
        }

        public void setRequire_id(int require_id) {
            this.require_id = require_id;
        }

        public String getSex_name() {
            return sex_name;
        }

        public void setSex_name(String sex_name) {
            this.sex_name = sex_name;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getWork_second_area_id() {
            return work_second_area_id;
        }

        public void setWork_second_area_id(int work_second_area_id) {
            this.work_second_area_id = work_second_area_id;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public int getWork_experience() {
            return work_experience;
        }

        public void setWork_experience(int work_experience) {
            this.work_experience = work_experience;
        }

        public String getJob_class_id_two() {
            return job_class_id_two;
        }

        public void setJob_class_id_two(String job_class_id_two) {
            this.job_class_id_two = job_class_id_two;
        }

        public int getRecruitment_num() {
            return recruitment_num;
        }

        public void setRecruitment_num(int recruitment_num) {
            this.recruitment_num = recruitment_num;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public int getLookfor_bell_worker_id() {
            return lookfor_bell_worker_id;
        }

        public void setLookfor_bell_worker_id(int lookfor_bell_worker_id) {
            this.lookfor_bell_worker_id = lookfor_bell_worker_id;
        }

        public int getRecruit_number() {
            return recruit_number;
        }

        public void setRecruit_number(int recruit_number) {
            this.recruit_number = recruit_number;
        }

        public int getWhen_bell_worker_id() {
            return when_bell_worker_id;
        }

        public void setWhen_bell_worker_id(int when_bell_worker_id) {
            this.when_bell_worker_id = when_bell_worker_id;
        }

        public int getTotal_match_num() {
            return total_match_num;
        }

        public void setTotal_match_num(int total_match_num) {
            this.total_match_num = total_match_num;
        }

        public String getWork_type_name() {
            return work_type_name;
        }

        public void setWork_type_name(String work_type_name) {
            this.work_type_name = work_type_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getEnterprise_name() {
            return enterprise_name;
        }

        public void setEnterprise_name(String enterprise_name) {
            this.enterprise_name = enterprise_name;
        }
    }
}
