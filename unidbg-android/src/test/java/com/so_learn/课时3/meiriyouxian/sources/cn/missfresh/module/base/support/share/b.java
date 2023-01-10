package cn.missfresh.module.base.support.share;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

/* compiled from: QQShareHelper */
public class b {
    private static String a = "";

    public static void a(Context context, a aVar) {
        AppMethodBeat.i(22449, false);
        if (aVar.f != null) {
            a(context, aVar.f);
        }
        Intent intent = new Intent(context, QQShareActivity.class);
        intent.putExtra("picPath", a);
        intent.putExtra("shareType", aVar.a);
        intent.putExtra("title", aVar.b);
        intent.putExtra("summary", aVar.c);
        intent.putExtra("targetUrl", aVar.d);
        intent.putExtra("imageUrl", aVar.e);
        g.a().a(aVar.f);
        intent.setFlags(268435456);
        context.startActivity(intent);
        AppMethodBeat.o(22449);
    }

    private static void a(Context context, Bitmap bitmap) {
        AppMethodBeat.i(22450, false);
        try {
            cn.missfresh.basiclib.utils.a.a(context, bitmap, "MF_" + System.currentTimeMillis() + ".jpg");
        } catch (Exception e) {
            d.a("QQShareHelper", e);
        }
        AppMethodBeat.o(22450);
    }

    /* compiled from: QQShareHelper */
    public static class a {
        public int a;
        public String b;
        public String c;
        public String d;
        public String e;
        public Bitmap f;

        public a() {
        }

        public a(int i, String str, String str2, String str3, String str4) {
            this(i, str, str2, str3, str4, null);
        }

        public a(int i, String str, String str2, String str3, String str4, Bitmap bitmap) {
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = str4;
            this.f = bitmap;
        }
    }
}
