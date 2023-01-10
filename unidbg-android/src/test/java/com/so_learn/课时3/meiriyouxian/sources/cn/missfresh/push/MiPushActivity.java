package cn.missfresh.push;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.missfresh.pushlib.MFMiPushActivity;
import cn.missfresh.pushlib.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import org.json.JSONException;

public class MiPushActivity extends MFMiPushActivity {
    private static b a;

    @Override // cn.missfresh.pushlib.MFMiPushActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public static void a(b bVar) {
        a = bVar;
    }

    @Override // com.umeng.message.UmengNotifyClickActivity, com.taobao.agoo.BaseNotifyClickActivity
    public void onMessage(Intent intent) {
        AppMethodBeat.i(24474, false);
        super.onMessage(intent);
        String a2 = a(intent.getStringExtra("body"));
        if (!TextUtils.isEmpty(a2)) {
            try {
                Bundle b = b(a2);
                if (a != null) {
                    a.a(this, b);
                }
            } catch (JSONException unused) {
                b bVar = a;
                if (bVar != null) {
                    bVar.a(this, (Bundle) null);
                }
            }
        } else {
            b bVar2 = a;
            if (bVar2 != null) {
                bVar2.a(this, (Bundle) null);
            }
        }
        finish();
        AppMethodBeat.o(24474);
    }
}
