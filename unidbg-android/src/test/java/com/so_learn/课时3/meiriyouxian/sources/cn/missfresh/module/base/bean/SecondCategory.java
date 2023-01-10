package cn.missfresh.module.base.bean;

import java.io.Serializable;

public class SecondCategory implements Serializable {
    public int index;
    public String name;
    public int position;

    public SecondCategory(String str, int i, int i2) {
        this.name = str;
        this.position = i;
        this.index = i2;
    }

    public SecondCategory() {
    }
}
