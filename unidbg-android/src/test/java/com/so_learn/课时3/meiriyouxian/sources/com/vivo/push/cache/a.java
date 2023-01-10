package com.vivo.push.cache;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.util.e;
import com.vivo.push.util.n;
import com.vivo.push.util.y;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AppConfigSettings */
public final class a extends c<com.vivo.push.model.a> {
    /* access modifiers changed from: protected */
    @Override // com.vivo.push.cache.c
    public final String a() {
        return "com.vivo.pushservice.back_up";
    }

    public a(Context context) {
        super(context);
    }

    @Override // com.vivo.push.cache.c
    public final List<com.vivo.push.model.a> a(String str) {
        AppMethodBeat.i(2858, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(2858);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.trim().split("@#")) {
                String trim = str2.trim();
                String[] split = trim.trim().split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                if (split.length >= 2) {
                    try {
                        arrayList.add(new com.vivo.push.model.a(split[0], trim.substring(split[0].length() + 1)));
                    } catch (Exception e) {
                        n.d("AppConfigSettings", "str2Clients E: " + e);
                    }
                }
            }
        }
        AppMethodBeat.o(2858);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    @Override // com.vivo.push.cache.c
    public final String b(String str) throws Exception {
        AppMethodBeat.i(2862, false);
        String str2 = new String(e.a(e.a(a), e.a(b), Base64.decode(str, 2)), FileOpt.ENCODE_STR);
        AppMethodBeat.o(2862);
        return str2;
    }

    public final com.vivo.push.model.a c(String str) {
        int i = 2866;
        AppMethodBeat.i(2866, false);
        synchronized (c) {
            try {
                for (com.vivo.push.model.a aVar : this.d) {
                    if (!TextUtils.isEmpty(aVar.a) && aVar.a.equals(str)) {
                        return aVar;
                    }
                }
                AppMethodBeat.o(2866);
                return null;
            } finally {
                AppMethodBeat.o(i);
            }
        }
    }

    public final int b() {
        AppMethodBeat.i(2871, false);
        com.vivo.push.model.a c = c("push_mode");
        if (c == null || TextUtils.isEmpty(c.b)) {
            AppMethodBeat.o(2871);
            return -1;
        }
        try {
            int parseInt = Integer.parseInt(c.b);
            AppMethodBeat.o(2871);
            return parseInt;
        } catch (Exception unused) {
            AppMethodBeat.o(2871);
            return -1;
        }
    }

    public static boolean a(int i) {
        AppMethodBeat.i(2875, false);
        if (i != -1) {
            int i2 = i & 1;
            AppMethodBeat.o(2875);
            return i2 != 0;
        }
        boolean equals = y.b("persist.sys.log.ctrl", "no").equals("yes");
        AppMethodBeat.o(2875);
        return equals;
    }
}
