package com.sobot.chat.api.model;

import android.text.TextUtils;
import java.io.Serializable;

public class ConsultingContent implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean isAutoSend;
    private String sobotGoodsDescribe;
    private String sobotGoodsFromUrl;
    private String sobotGoodsImgUrl;
    private String sobotGoodsLable;
    private String sobotGoodsTitle;

    public ConsultingContent() {
    }

    public ConsultingContent(String str, String str2, String str3, String str4, String str5) {
        this.sobotGoodsTitle = str;
        this.sobotGoodsImgUrl = str2;
        this.sobotGoodsFromUrl = str3;
        this.sobotGoodsLable = str4;
        this.sobotGoodsDescribe = str5;
    }

    public String getSobotGoodsLable() {
        return this.sobotGoodsLable;
    }

    public void setSobotGoodsLable(String str) {
        this.sobotGoodsLable = str;
    }

    public String getSobotGoodsFromUrl() {
        return this.sobotGoodsFromUrl;
    }

    public void setSobotGoodsFromUrl(String str) {
        this.sobotGoodsFromUrl = str;
    }

    public String getSobotGoodsImgUrl() {
        return this.sobotGoodsImgUrl;
    }

    public void setSobotGoodsImgUrl(String str) {
        this.sobotGoodsImgUrl = str;
    }

    public String getSobotGoodsTitle() {
        return this.sobotGoodsTitle;
    }

    public void setSobotGoodsTitle(String str) {
        this.sobotGoodsTitle = str;
    }

    public String getSobotGoodsDescribe() {
        return this.sobotGoodsDescribe;
    }

    public void setSobotGoodsDescribe(String str) {
        this.sobotGoodsDescribe = str;
    }

    public boolean isAutoSend() {
        return this.isAutoSend;
    }

    public void setAutoSend(boolean z) {
        this.isAutoSend = z;
    }

    @Override // java.lang.Object
    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5 = null;
        if (TextUtils.isEmpty(this.sobotGoodsTitle)) {
            str = null;
        } else {
            str = "\"" + this.sobotGoodsTitle + "\"";
        }
        if (TextUtils.isEmpty(this.sobotGoodsFromUrl)) {
            str2 = null;
        } else {
            str2 = "\"" + this.sobotGoodsFromUrl + "\"";
        }
        if (TextUtils.isEmpty(this.sobotGoodsDescribe)) {
            str3 = null;
        } else {
            str3 = "\"" + this.sobotGoodsDescribe + "\"";
        }
        if (TextUtils.isEmpty(this.sobotGoodsLable)) {
            str4 = null;
        } else {
            str4 = "\"" + this.sobotGoodsLable + "\"";
        }
        if (!TextUtils.isEmpty(this.sobotGoodsImgUrl)) {
            str5 = "\"" + this.sobotGoodsImgUrl + "\"";
        }
        return "{\"title\":" + str + ",\"url\":" + str2 + ",\"description\":" + str3 + ",\"label\":" + str4 + ",\"thumbnail\":" + str5 + "}";
    }
}
