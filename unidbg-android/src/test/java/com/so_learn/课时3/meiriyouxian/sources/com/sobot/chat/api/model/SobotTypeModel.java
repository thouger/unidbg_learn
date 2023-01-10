package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;
import java.util.ArrayList;

public class SobotTypeModel implements Serializable {
    private String companyId;
    private String createId;
    private String createTime;
    private boolean isChecked;
    private ArrayList<SobotTypeModel> items;
    private int nodeFlag;
    private String parentId;
    private String remark;
    private String typeId;
    private int typeLevel;
    private String typeName;
    private String updateId;
    private String updateTime;
    private int validFlag;

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

    public int getNodeFlag() {
        return this.nodeFlag;
    }

    public void setNodeFlag(int i) {
        this.nodeFlag = i;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String str) {
        this.parentId = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String str) {
        this.typeId = str;
    }

    public int getTypeLevel() {
        return this.typeLevel;
    }

    public void setTypeLevel(int i) {
        this.typeLevel = i;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
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

    public int getValidFlag() {
        return this.validFlag;
    }

    public void setValidFlag(int i) {
        this.validFlag = i;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public ArrayList<SobotTypeModel> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<SobotTypeModel> arrayList) {
        this.items = arrayList;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotTypeModel{companyId='" + this.companyId + DateFormat.QUOTE + ", createId='" + this.createId + DateFormat.QUOTE + ", createTime=" + this.createTime + ", nodeFlag=" + this.nodeFlag + ", parentId='" + this.parentId + DateFormat.QUOTE + ", remark='" + this.remark + DateFormat.QUOTE + ", typeId='" + this.typeId + DateFormat.QUOTE + ", typeLevel=" + this.typeLevel + ", typeName='" + this.typeName + DateFormat.QUOTE + ", updateId='" + this.updateId + DateFormat.QUOTE + ", updateTime=" + this.updateTime + ", validFlag=" + this.validFlag + ", isChecked=" + this.isChecked + ", items=" + this.items + '}';
    }
}
