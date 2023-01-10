package com.sdk.mobile.manager.login.manager;

import java.util.Map;

public class TextViewManager extends ViewManager {
    private Map<String, Boolean> viewsBold;
    private Map<String, String> viewsText;
    private Map<String, Integer> viewsTextColor;
    private Map<String, Integer> viewsTextSize;

    public Map<String, Boolean> getViewsBold() {
        return this.viewsBold;
    }

    public Map<String, String> getViewsText() {
        return this.viewsText;
    }

    public Map<String, Integer> getViewsTextColor() {
        return this.viewsTextColor;
    }

    public Map<String, Integer> getViewsTextSize() {
        return this.viewsTextSize;
    }

    public void setViewsBold(Map<String, Boolean> map) {
        this.viewsBold = map;
    }

    public void setViewsText(Map<String, String> map) {
        this.viewsText = map;
    }

    public void setViewsTextColor(Map<String, Integer> map) {
        this.viewsTextColor = map;
    }

    public void setViewsTextSize(Map<String, Integer> map) {
        this.viewsTextSize = map;
    }
}
