package com.vivo.push.cache;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.model.a;
import com.vivo.push.util.n;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PushConfigSettings */
public final class e extends c<a> {
    /* access modifiers changed from: protected */
    @Override // com.vivo.push.cache.c
    public final String a() {
        return "com.vivo.pushservice.other";
    }

    public e(Context context) {
        super(context);
    }

    @Override // com.vivo.push.cache.c
    public final List<a> a(String str) {
        AppMethodBeat.i(2890, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(2890);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.trim().split("@#")) {
            String trim = str2.trim();
            String[] split = trim.trim().split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            if (split.length >= 2) {
                try {
                    arrayList.add(new a(split[0], trim.substring(split[0].length() + 1)));
                } catch (Exception e) {
                    n.d("PushConfigSettings", "str2Clients E: " + e);
                }
            }
        }
        AppMethodBeat.o(2890);
        return arrayList;
    }

    public final String c(String str) {
        int i = 2899;
        AppMethodBeat.i(2899, false);
        synchronized (c) {
            try {
                for (a aVar : this.d) {
                    if (!TextUtils.isEmpty(aVar.a) && aVar.a.equals(str)) {
                        return aVar.b;
                    }
                }
                AppMethodBeat.o(2899);
                return null;
            } finally {
                AppMethodBeat.o(i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.vivo.push.cache.c
    public final String b(String str) throws Exception {
        AppMethodBeat.i(2904, false);
        String str2 = new String(com.vivo.push.util.e.a(com.vivo.push.util.e.a(a), com.vivo.push.util.e.a(b), Base64.decode(str, 2)), FileOpt.ENCODE_STR);
        AppMethodBeat.o(2904);
        return str2;
    }
}
