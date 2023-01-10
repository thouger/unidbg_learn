package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.List;

public class ZhiChiCidsModel implements Serializable {
    private List<String> cids;

    public List<String> getCids() {
        return this.cids;
    }

    public void setCids(List<String> list) {
        this.cids = list;
    }
}
