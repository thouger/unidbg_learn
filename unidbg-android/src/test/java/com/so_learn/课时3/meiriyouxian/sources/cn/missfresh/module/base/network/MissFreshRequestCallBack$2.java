package cn.missfresh.module.base.network;

import android.app.Activity;
import android.view.View;
import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;

class MissFreshRequestCallBack$2 implements View.OnClickListener {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ Activity b;
    final /* synthetic */ m c;

    MissFreshRequestCallBack$2(m mVar, JSONObject jSONObject, Activity activity) {
        JniLib.cV(this, mVar, jSONObject, activity, 15);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        JniLib.cV(this, view, 14);
    }
}
