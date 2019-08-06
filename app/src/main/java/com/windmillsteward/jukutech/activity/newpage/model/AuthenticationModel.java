package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/21
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 资产认证 model
 */
public class AuthenticationModel {
    /**
     * assets_audit_reason : 图片不清晰
     * deed_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
     * driver_license_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
     * audit_assets_status : 3
     */

    private String assets_audit_reason;
    private String deed_url;
    private String driver_license_url;
    private int audit_assets_status;

    public String getAssets_audit_reason() {
        return assets_audit_reason;
    }

    public void setAssets_audit_reason(String assets_audit_reason) {
        this.assets_audit_reason = assets_audit_reason;
    }

    public String getDeed_url() {
        return deed_url;
    }

    public void setDeed_url(String deed_url) {
        this.deed_url = deed_url;
    }

    public String getDriver_license_url() {
        return driver_license_url;
    }

    public void setDriver_license_url(String driver_license_url) {
        this.driver_license_url = driver_license_url;
    }

    public int getAudit_assets_status() {
        return audit_assets_status;
    }

    public void setAudit_assets_status(int audit_assets_status) {
        this.audit_assets_status = audit_assets_status;
    }
}
