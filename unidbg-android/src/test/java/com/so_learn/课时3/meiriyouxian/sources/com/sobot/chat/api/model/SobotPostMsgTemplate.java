package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class SobotPostMsgTemplate implements Serializable {
    private String templateId;
    private String templateName;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "SobotPostMsgTemplate{templateName='" + this.templateName + DateFormat.QUOTE + ", templateId='" + this.templateId + DateFormat.QUOTE + '}';
    }
}
