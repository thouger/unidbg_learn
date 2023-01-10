package cn.missfresh.module.base.network;

import android.app.Activity;
import android.view.View;
import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;

class MFGlobalErrorHandler$2 implements View.OnClickListener {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ Activity b;

    MFGlobalErrorHandler$2(JSONObject jSONObject, Activity activity) {
        JniLib.cV(this, jSONObject, activity, 11);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        JniLib.cV(this, view, 10);
    }
}
