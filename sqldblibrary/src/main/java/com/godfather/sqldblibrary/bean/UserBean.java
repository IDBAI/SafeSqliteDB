package com.godfather.sqldblibrary.bean;

import java.io.Serializable;

/**
 * 用户示例
 */
public class UserBean implements Serializable {
    public static final long serialVersionUID = 3943267343179492569L;
    public int ID;
    /**
     *
     */
    public String userId;
    /**
     * 手机号码
     */
    public String mobileNum;
    /**
     * 小区ID
     */
    public String communityId;
    /**
     * 保留字段
     */
    public String tag;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "ID=" + ID +
                ", userId='" + userId + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                ", communityId='" + communityId + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
