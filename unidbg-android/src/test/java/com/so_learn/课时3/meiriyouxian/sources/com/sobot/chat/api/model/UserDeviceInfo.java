package com.sobot.chat.api.model;

import java.io.Serializable;

public class UserDeviceInfo implements Serializable {
    private String appName;
    private String appVersion;
    private String phoneEquipmentModel;
    private String phoneMACAddr;
    private String phoneOS;
    private String phoneOSLanguage;
    private String phoneOSVersion;
    private String phoneOperator;
    private String phoneScreenSize;
    private String phoneScreesResolution;
    private String phoneUUID;

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getPhoneEquipmentModel() {
        return this.phoneEquipmentModel;
    }

    public void setPhoneEquipmentModel(String str) {
        this.phoneEquipmentModel = str;
    }

    public String getPhoneScreenSize() {
        return this.phoneScreenSize;
    }

    public void setPhoneScreenSize(String str) {
        this.phoneScreenSize = str;
    }

    public String getPhoneScreesResolution() {
        return this.phoneScreesResolution;
    }

    public void setPhoneScreesResolution(String str) {
        this.phoneScreesResolution = str;
    }

    public String getPhoneOS() {
        return this.phoneOS;
    }

    public void setPhoneOS(String str) {
        this.phoneOS = str;
    }

    public String getPhoneOSVersion() {
        return this.phoneOSVersion;
    }

    public void setPhoneOSVersion(String str) {
        this.phoneOSVersion = str;
    }

    public String getPhoneOSLanguage() {
        return this.phoneOSLanguage;
    }

    public void setPhoneOSLanguage(String str) {
        this.phoneOSLanguage = str;
    }

    public String getPhoneMACAddr() {
        return this.phoneMACAddr;
    }

    public void setPhoneMACAddr(String str) {
        this.phoneMACAddr = str;
    }

    public String getPhoneUUID() {
        return this.phoneUUID;
    }

    public void setPhoneUUID(String str) {
        this.phoneUUID = str;
    }

    public String getPhoneOperator() {
        return this.phoneOperator;
    }

    public void setPhoneOperator(String str) {
        this.phoneOperator = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }
}
