package com.sobot.chat.api.model;

import java.io.Serializable;

public class SobotLocationModel implements Serializable {
    private String lat;
    private String lng;
    private String localLabel;
    private String localName;
    private String snapshot;

    public String getLocalName() {
        return this.localName;
    }

    public void setLocalName(String str) {
        this.localName = str;
    }

    public String getLocalLabel() {
        return this.localLabel;
    }

    public void setLocalLabel(String str) {
        this.localLabel = str;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String str) {
        this.lat = str;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String str) {
        this.lng = str;
    }

    public String getSnapshot() {
        return this.snapshot;
    }

    public void setSnapshot(String str) {
        this.snapshot = str;
    }
}
