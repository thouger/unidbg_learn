package com.vivo.push.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.telephony.RILConstants;
import java.util.HashMap;

/* compiled from: BaseSharePreference */
public class a {
    protected Context a;
    private String b;
    private volatile SharedPreferences c;
    private HashMap<String, String> d = new HashMap<>();
    private HashMap<String, Long> e = new HashMap<>();
    private HashMap<String, Integer> f = new HashMap<>();
    private HashMap<String, Boolean> g = new HashMap<>();

    public a() {
        AppMethodBeat.i(1012, false);
        AppMethodBeat.o(1012);
    }

    public final void a(Context context, String str) {
        AppMethodBeat.i(1014, false);
        if (!TextUtils.isEmpty(str)) {
            this.b = str;
            this.c = context.getSharedPreferences(this.b, 0);
            this.a = context;
            AppMethodBeat.o(1014);
            return;
        }
        RuntimeException runtimeException = new RuntimeException("sharedFileName can't be null");
        AppMethodBeat.o(1014);
        throw runtimeException;
    }

    public final void a(String str, String str2) {
        AppMethodBeat.i(1016, false);
        this.d.put(str, str2);
        b();
        if (this.c != null) {
            SharedPreferences.Editor edit = this.c.edit();
            edit.putString(str, str2);
            a(edit);
        }
        AppMethodBeat.o(1016);
    }

    public final void a(String str, int i) {
        AppMethodBeat.i(1020, false);
        this.f.put(str, Integer.valueOf(i));
        b();
        if (this.c != null) {
            SharedPreferences.Editor edit = this.c.edit();
            edit.putInt(str, i);
            a(edit);
        }
        AppMethodBeat.o(1020);
    }

    public final void a(String str, long j) {
        AppMethodBeat.i(1024, false);
        this.e.put(str, Long.valueOf(j));
        b();
        if (this.c != null) {
            SharedPreferences.Editor edit = this.c.edit();
            edit.putLong(str, j);
            a(edit);
        }
        AppMethodBeat.o(1024);
    }

    public final String a(String str) {
        AppMethodBeat.i(1027, false);
        String str2 = this.d.get(str);
        if (str2 != null) {
            AppMethodBeat.o(1027);
            return str2;
        }
        b();
        if (this.c != null) {
            str2 = this.c.getString(str, null);
            if (!TextUtils.isEmpty(str2) && !str2.equals(null)) {
                this.d.put(str, str2);
            }
        }
        AppMethodBeat.o(1027);
        return str2;
    }

    public final int b(String str) {
        AppMethodBeat.i(1030, false);
        Integer num = this.f.get(str);
        if (num != null) {
            int intValue = num.intValue();
            AppMethodBeat.o(1030);
            return intValue;
        }
        b();
        if (this.c != null) {
            num = Integer.valueOf(this.c.getInt(str, 0));
            if (!num.equals(0)) {
                this.f.put(str, num);
            }
        }
        int intValue2 = num.intValue();
        AppMethodBeat.o(1030);
        return intValue2;
    }

    public final long b(String str, long j) {
        AppMethodBeat.i(1032, false);
        Long l = this.e.get(str);
        if (l != null) {
            long longValue = l.longValue();
            AppMethodBeat.o(1032);
            return longValue;
        }
        b();
        if (this.c != null) {
            l = Long.valueOf(this.c.getLong(str, j));
            if (!l.equals(Long.valueOf(j))) {
                this.e.put(str, l);
            }
        }
        long longValue2 = l.longValue();
        AppMethodBeat.o(1032);
        return longValue2;
    }

    public final void c(String str) {
        AppMethodBeat.i(RILConstants.RIL_UNSOL_VOICE_RADIO_TECH_CHANGED, false);
        this.e.remove(str);
        this.f.remove(str);
        this.g.remove(str);
        this.d.remove(str);
        b();
        if (this.c != null) {
            SharedPreferences.Editor edit = this.c.edit();
            if (this.c.contains(str)) {
                edit.remove(str);
                a(edit);
            }
        }
        AppMethodBeat.o(RILConstants.RIL_UNSOL_VOICE_RADIO_TECH_CHANGED);
    }

    public static void a(SharedPreferences.Editor editor) {
        AppMethodBeat.i(1037, false);
        if (editor == null) {
            AppMethodBeat.o(1037);
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            editor.apply();
            AppMethodBeat.o(1037);
        } else {
            editor.commit();
            AppMethodBeat.o(1037);
        }
    }

    public final void a() {
        AppMethodBeat.i(1040, false);
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.d.clear();
        b();
        if (this.c != null) {
            SharedPreferences.Editor edit = this.c.edit();
            edit.clear();
            a(edit);
        }
        AppMethodBeat.o(1040);
    }

    private void b() {
        AppMethodBeat.i(RILConstants.RIL_UNSOL_RADIO_CAPABILITY, false);
        if (this.c == null) {
            Context context = this.a;
            if (context != null) {
                this.c = context.getSharedPreferences(this.b, 0);
            } else {
                RuntimeException runtimeException = new RuntimeException("SharedPreferences is not init", new Throwable());
                AppMethodBeat.o(RILConstants.RIL_UNSOL_RADIO_CAPABILITY);
                throw runtimeException;
            }
        }
        AppMethodBeat.o(RILConstants.RIL_UNSOL_RADIO_CAPABILITY);
    }
}
