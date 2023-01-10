package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SobotQueryFormModel implements Serializable {
    private ArrayList<SobotFieldModel> field;
    private String formDoc;
    private String formTitle;
    private boolean openFlag;

    public ArrayList<SobotFieldModel> getField() {
        return this.field;
    }

    public void setField(ArrayList<SobotFieldModel> arrayList) {
        this.field = arrayList;
    }

    public boolean isOpenFlag() {
        return this.openFlag;
    }

    public void setOpenFlag(boolean z) {
        this.openFlag = z;
    }

    public String getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(String str) {
        this.formTitle = str;
    }

    public String getFormDoc() {
        return this.formDoc;
    }

    public void setFormDoc(String str) {
        this.formDoc = str;
    }
}
