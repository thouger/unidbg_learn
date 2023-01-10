package cn.missfresh.module.base.network.bean;

import com.bangcle.andjni.JniLib;

public class TokenBean {
    public String originalPath;
    public String targetName;

    public TokenBean() {
        JniLib.cV(this, 94);
    }
}
