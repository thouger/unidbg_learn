package cn.missfresh.module.base.network;

import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;

/* compiled from: MissFreshJsonRequestCallBack */
public abstract class k extends m {
    private String a = "MissFreshJsonRequestCallBack";

    /* access modifiers changed from: protected */
    public abstract void a(JSONObject jSONObject);

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        JniLib.cV(this, str, 175);
    }
}
