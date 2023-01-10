package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SobotLeaveMsgParamModel implements Serializable {
    private ArrayList<SobotFieldModel> field;

    public ArrayList<SobotFieldModel> getField() {
        return this.field;
    }

    public void setField(ArrayList<SobotFieldModel> arrayList) {
        this.field = arrayList;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotLeaveMsgParamModel{field=" + this.field + '}';
    }
}
