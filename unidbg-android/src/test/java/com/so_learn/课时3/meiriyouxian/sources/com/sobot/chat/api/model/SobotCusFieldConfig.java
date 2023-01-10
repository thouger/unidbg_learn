package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import com.sobot.chat.api.model.SobotProvinInfo;
import java.io.Serializable;

public class SobotCusFieldConfig implements Serializable {
    private String companyId;
    private String createId;
    private String createTime;
    private String fieldId;
    private String fieldName;
    private String fieldRemark;
    private int fieldStatus;
    private int fieldType;
    private String fieldVariable;
    private int fillFlag;
    private String id;
    private boolean isChecked;
    private String limitChar;
    private String limitOptions;
    private int operateType;
    private SobotProvinInfo.SobotProvinceModel provinceModel;
    private int sortNo;
    private String updateId;
    private String updateTime;
    private String value;
    private int workShowFlag;
    private int workSortNo;

    public SobotProvinInfo.SobotProvinceModel getProvinceModel() {
        return this.provinceModel;
    }

    public void setProvinceModel(SobotProvinInfo.SobotProvinceModel sobotProvinceModel) {
        this.provinceModel = sobotProvinceModel;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotCusFieldConfig{companyId='" + this.companyId + DateFormat.QUOTE + ", createId='" + this.createId + DateFormat.QUOTE + ", createTime=" + this.createTime + ", fieldId='" + this.fieldId + DateFormat.QUOTE + ", fieldName='" + this.fieldName + DateFormat.QUOTE + ", fieldRemark='" + this.fieldRemark + DateFormat.QUOTE + ", fieldStatus=" + this.fieldStatus + ", fieldType=" + this.fieldType + ", fieldVariable='" + this.fieldVariable + DateFormat.QUOTE + ", fillFlag=" + this.fillFlag + ", operateType=" + this.operateType + ", sortNo=" + this.sortNo + ", updateId='" + this.updateId + DateFormat.QUOTE + ", updateTime=" + this.updateTime + ", workShowFlag=" + this.workShowFlag + ", workSortNo=" + this.workSortNo + ", isChecked=" + this.isChecked + ", id=" + this.id + ", value=" + this.value + '}';
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
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

    public String getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(String str) {
        this.fieldId = str;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String str) {
        this.fieldName = str;
    }

    public String getFieldRemark() {
        return this.fieldRemark;
    }

    public void setFieldRemark(String str) {
        this.fieldRemark = str;
    }

    public int getFieldStatus() {
        return this.fieldStatus;
    }

    public void setFieldStatus(int i) {
        this.fieldStatus = i;
    }

    public int getFieldType() {
        return this.fieldType;
    }

    public void setFieldType(int i) {
        this.fieldType = i;
    }

    public String getFieldVariable() {
        return this.fieldVariable;
    }

    public void setFieldVariable(String str) {
        this.fieldVariable = str;
    }

    public int getFillFlag() {
        return this.fillFlag;
    }

    public void setFillFlag(int i) {
        this.fillFlag = i;
    }

    public int getOperateType() {
        return this.operateType;
    }

    public void setOperateType(int i) {
        this.operateType = i;
    }

    public int getSortNo() {
        return this.sortNo;
    }

    public void setSortNo(int i) {
        this.sortNo = i;
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

    public int getWorkShowFlag() {
        return this.workShowFlag;
    }

    public void setWorkShowFlag(int i) {
        this.workShowFlag = i;
    }

    public int getWorkSortNo() {
        return this.workSortNo;
    }

    public void setWorkSortNo(int i) {
        this.workSortNo = i;
    }

    public String getLimitOptions() {
        return this.limitOptions;
    }

    public void setLimitOptions(String str) {
        this.limitOptions = str;
    }

    public String getLimitChar() {
        return this.limitChar;
    }

    public void setLimitChar(String str) {
        this.limitChar = str;
    }
}
