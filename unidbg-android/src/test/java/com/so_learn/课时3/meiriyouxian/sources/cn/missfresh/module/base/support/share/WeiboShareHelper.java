package cn.missfresh.module.base.support.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.network.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.common.Constants;
import okhttp3.Request;
import org.json.JSONException;

public class WeiboShareHelper {
    private Context a;

    public interface a {
        void a(String str, boolean z);
    }

    public static void a(Context context, b bVar) {
        AppMethodBeat.i(22530, false);
        if (context == null) {
            cn.missfresh.ui.a.a.a("\u7f51\u7edc\u5f00\u5c0f\u5dee\u5566~\u8bf7\u91cd\u65b0\u5c1d\u8bd5");
            AppMethodBeat.o(22530);
        } else if (bVar == null || (TextUtils.isEmpty(bVar.a) && TextUtils.isEmpty(bVar.c))) {
            cn.missfresh.ui.a.a.a(context.getResources().getString(R.string.net_work_error));
            AppMethodBeat.o(22530);
        } else {
            Intent intent = new Intent(context, WBShareActivity.class);
            intent.putExtra("mTitle", bVar.a);
            intent.putExtra("mText", bVar.b);
            intent.putExtra("mImageUrl", bVar.c);
            intent.putExtra("mWebUrl", bVar.d);
            intent.putExtra("mBitmapBytes", bVar.f);
            g.a().a(bVar.e);
            intent.setFlags(268435456);
            context.startActivity(intent);
            AppMethodBeat.o(22530);
        }
    }

    public WeiboShareHelper(Context context) {
        this.a = context;
    }

    public static class b {
        public String a;
        public String b;
        public String c;
        public String d;
        public Bitmap e;
        public byte[] f;

        public b(String str, String str2, String str3, String str4, Bitmap bitmap) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = bitmap;
        }

        public b() {
        }

        public boolean a() {
            return (this.c == null && this.e == null && this.f == null) ? false : true;
        }
    }

    public void a(String str, a aVar) {
        AppMethodBeat.i(22534, false);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", str);
        c.a(this, i.bz, null, jSONObject, new AnonymousClass1(aVar));
        AppMethodBeat.o(22534);
    }

    /* renamed from: cn.missfresh.module.base.support.share.WeiboShareHelper$1  reason: invalid class name */
    class AnonymousClass1 implements c.b {
        final /* synthetic */ a a;

        @Override // cn.missfresh.module.base.network.c.b
        public void b(String str) {
        }

        AnonymousClass1(a aVar) {
            this.a = aVar;
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void a() {
            AppMethodBeat.i(22524, false);
            a("", false);
            AppMethodBeat.o(22524);
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(22525, false);
            a("", false);
            AppMethodBeat.o(22525);
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(22526, false);
            a("", false);
            AppMethodBeat.o(22526);
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(22527, false);
            if (!cn.missfresh.utils.b.a(str)) {
                try {
                    org.json.JSONObject jSONObject = new org.json.JSONObject(str);
                    int i = jSONObject.getInt(Constants.KEY_HTTP_CODE);
                    String simpleName = getClass().getSimpleName();
                    d.d(simpleName, "result code:" + i);
                    a(jSONObject.getString("short_url"), false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    a("", false);
                }
            } else {
                a("", false);
            }
            AppMethodBeat.o(22527);
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void b() {
            AppMethodBeat.i(22528, false);
            a("", false);
            AppMethodBeat.o(22528);
        }

        private void a(String str, boolean z) {
            AppMethodBeat.i(22529, false);
            a aVar = this.a;
            if (aVar != null) {
                aVar.a(str, z);
            }
            AppMethodBeat.o(22529);
        }
    }
}
