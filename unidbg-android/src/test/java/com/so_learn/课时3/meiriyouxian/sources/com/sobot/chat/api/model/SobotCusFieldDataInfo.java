package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotCusFieldDataInfo implements Serializable {
    private String companyId;
    private String createId;
    private String createTime;
    private String dataId;
    private String dataName;
    private int dataStatus;
    private String dataValue;
    private String fieldId;
    private String fieldVariable;
    private boolean isChecked;
    private String parentDataId;
    private String updateId;
    private String updateTime;

    @Override // java.lang.Object
    public String toString() {
        return "SobotCusFieldDataInfo{companyId='" + this.companyId + DateFormat.QUOTE + ", createId='" + this.createId + DateFormat.QUOTE + ", createTime=" + this.createTime + ", dataId='" + this.dataId + DateFormat.QUOTE + ", dataName='" + this.dataName + DateFormat.QUOTE + ", dataStatus=" + this.dataStatus + ", dataValue='" + this.dataValue + DateFormat.QUOTE + ", fieldId='" + this.fieldId + DateFormat.QUOTE + ", fieldVariable='" + this.fieldVariable + DateFormat.QUOTE + ", parentDataId='" + this.parentDataId + DateFormat.QUOTE + ", updateId='" + this.updateId + DateFormat.QUOTE + ", updateTime=" + this.updateTime + ", isChecked=" + this.isChecked + '}';
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String str) {
        this.companyId = str;
    }

    public String getCreateId() {
        return this.createId;
    }

    public void setCreateId(String str) {
        this.createId = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getDataId() {
        return this.dataId;
    }

    public void setDataId(String str) {
        this.dataId = str;
    }

    public String getDataName() {
        return this.dataName;
    }

    public void setDataName(String str) {
        this.dataName = str;
    }

    public int getDataStatus() {
        return this.dataStatus;
    }

    public void setDataStatus(int i) {
        this.dataStatus = i;
    }

    public String getDataValue() {
        return this.dataValue;
    }

    public void setDataValue(String str) {
        this.dataValue = str;
    }

    public String getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(String str) {
        this.fieldId = str;
    }

    public String getFieldVariable() {
        return this.fieldVariable;
    }

    public void setFieldVariable(String str) {
        this.fieldVariable = str;
    }

    public String getParentDataId() {
        return this.parentDataId;
    }

    public void setParentDataId(String str) {
        this.parentDataId = str;
    }

    public String getUpdateId() {
        return this.updateId;
    }

    public void setUpdateId(String str) {
        this.updateId = str;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(String str) {
        this.updateTime = str;
    }
}
