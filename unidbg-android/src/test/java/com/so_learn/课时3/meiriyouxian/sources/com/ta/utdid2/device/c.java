package com.ta.utdid2.device;

import android.Manifest;
import android.bluetooth.BluetoothInputHost;
import android.content.Context;
import android.os.Binder;
import android.provider.Settings;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import com.android.internal.midi.MidiConstants;
import com.ta.utdid2.a.a.b;
import com.ta.utdid2.a.a.d;
import com.ta.utdid2.a.a.e;
import com.ta.utdid2.a.a.f;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class c {
    private static c a = null;
    private static final Object e = new Object();
    private static final String k = (".UTSystemConfig" + File.separator + "Global");

    /* renamed from: a  reason: collision with other field name */
    private com.ta.utdid2.b.a.c f336a = null;

    /* renamed from: a  reason: collision with other field name */
    private d f337a = null;
    private com.ta.utdid2.b.a.c b = null;

    /* renamed from: b  reason: collision with other field name */
    private Pattern f338b = Pattern.compile("[^0-9a-zA-Z=/+]+");
    private String h = null;
    private String i = "xx_utdid_key";
    private String j = "xx_utdid_domain";
    private Context mContext = null;

    private c(Context context) {
        this.mContext = context;
        this.b = new com.ta.utdid2.b.a.c(context, k, "Alvin2", false, true);
        this.f336a = new com.ta.utdid2.b.a.c(context, ".DataStorage", "ContextData", false, true);
        this.f337a = new d();
        this.i = String.format("K_%d", Integer.valueOf(f.a(this.i)));
        this.j = String.format("D_%d", Integer.valueOf(f.a(this.j)));
    }

    private void c() {
        com.ta.utdid2.b.a.c cVar = this.b;
        if (cVar != null) {
            if (f.m4071a(cVar.getString("UTDID2"))) {
                String string = this.b.getString("UTDID");
                if (!f.m4071a(string)) {
                    f(string);
                }
            }
            boolean z = false;
            if (!f.m4071a(this.b.getString("DID"))) {
                this.b.remove("DID");
                z = true;
            }
            if (!f.m4071a(this.b.getString("EI"))) {
                this.b.remove("EI");
                z = true;
            }
            if (!f.m4071a(this.b.getString("SI"))) {
                this.b.remove("SI");
                z = true;
            }
            if (z) {
                this.b.commit();
            }
        }
    }

    public static c a(Context context) {
        if (context != null && a == null) {
            synchronized (e) {
                if (a == null) {
                    a = new c(context);
                    a.c();
                }
            }
        }
        return a;
    }

    private void f(String str) {
        com.ta.utdid2.b.a.c cVar;
        if (b(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() == 24 && (cVar = this.b) != null) {
                cVar.putString("UTDID2", str);
                this.b.commit();
            }
        }
    }

    private void g(String str) {
        com.ta.utdid2.b.a.c cVar;
        if (str != null && (cVar = this.f336a) != null && !str.equals(cVar.getString(this.i))) {
            this.f336a.putString(this.i, str);
            this.f336a.commit();
        }
    }

    private void h(String str) {
        if (e() && b(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length()) {
                String str2 = null;
                try {
                    str2 = Settings.System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
                } catch (Exception unused) {
                }
                if (!b(str2)) {
                    try {
                        Settings.System.putString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk", str);
                    } catch (Exception unused2) {
                    }
                }
            }
        }
    }

    private void i(String str) {
        String str2;
        try {
            str2 = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        } catch (Exception unused) {
            str2 = null;
        }
        if (!str.equals(str2)) {
            try {
                Settings.System.putString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp", str);
            } catch (Exception unused2) {
            }
        }
    }

    private void j(String str) {
        if (e() && str != null) {
            i(str);
        }
    }

    private String g() {
        com.ta.utdid2.b.a.c cVar = this.b;
        if (cVar == null) {
            return null;
        }
        String string = cVar.getString("UTDID2");
        if (f.m4071a(string) || this.f337a.c(string) == null) {
            return null;
        }
        return string;
    }

    private boolean b(String str) {
        if (str != null) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length() && !this.f338b.matcher(str).find()) {
                return true;
            }
        }
        return false;
    }

    public synchronized String getValue() {
        if (this.h != null) {
            return this.h;
        }
        return h();
    }

    public synchronized String h() {
        this.h = i();
        if (!TextUtils.isEmpty(this.h)) {
            return this.h;
        }
        try {
            byte[] c = m4078c();
            if (c != null) {
                this.h = b.encodeToString(c, 2);
                f(this.h);
                String c2 = this.f337a.c(c);
                if (c2 != null) {
                    j(c2);
                    g(c2);
                }
                return this.h;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public synchronized String i() {
        String str;
        String str2 = "";
        try {
            str2 = Settings.System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
        } catch (Exception unused) {
        }
        if (b(str2)) {
            return str2;
        }
        e eVar = new e();
        boolean z = false;
        try {
            str = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        } catch (Exception unused2) {
            str = null;
        }
        if (!f.m4071a(str)) {
            String e2 = eVar.e(str);
            if (b(e2)) {
                h(e2);
                return e2;
            }
            String d = eVar.d(str);
            if (b(d)) {
                String c = this.f337a.c(d);
                if (!f.m4071a(c)) {
                    j(c);
                    try {
                        str = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
                    } catch (Exception unused3) {
                    }
                }
            }
            String d2 = this.f337a.d(str);
            if (b(d2)) {
                this.h = d2;
                f(d2);
                g(str);
                h(this.h);
                return this.h;
            }
        } else {
            z = true;
        }
        String g = g();
        if (b(g)) {
            String c2 = this.f337a.c(g);
            if (z) {
                j(c2);
            }
            h(g);
            g(c2);
            this.h = g;
            return g;
        }
        String string = this.f336a.getString(this.i);
        if (!f.m4071a(string)) {
            String d3 = eVar.d(string);
            if (!b(d3)) {
                d3 = this.f337a.d(string);
            }
            if (b(d3)) {
                String c3 = this.f337a.c(d3);
                if (!f.m4071a(d3)) {
                    this.h = d3;
                    if (z) {
                        j(c3);
                    }
                    f(this.h);
                    return this.h;
                }
            }
        }
        return null;
    }

    /* renamed from: c  reason: collision with other method in class */
    private byte[] m4078c() throws Exception {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int nextInt = new Random().nextInt();
        byte[] bytes = com.ta.utdid2.a.a.c.getBytes((int) (System.currentTimeMillis() / 1000));
        byte[] bytes2 = com.ta.utdid2.a.a.c.getBytes(nextInt);
        byteArrayOutputStream.write(bytes, 0, 4);
        byteArrayOutputStream.write(bytes2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            str = d.a(this.mContext);
        } catch (Exception unused) {
            str = "" + new Random().nextInt();
        }
        byteArrayOutputStream.write(com.ta.utdid2.a.a.c.getBytes(f.a(str)), 0, 4);
        byteArrayOutputStream.write(com.ta.utdid2.a.a.c.getBytes(f.a(b(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    public static String b(byte[] bArr) throws Exception {
        byte[] bArr2 = {69, 114, 116, -33, 125, -54, -31, 86, -11, 11, -78, MidiConstants.STATUS_POLYPHONIC_AFTERTOUCH, -17, -99, BluetoothInputHost.SUBCLASS1_KEYBOARD, 23, -95, -126, -82, -64, 113, 116, -16, -103, 49, -30, 9, -39, 33, MidiConstants.STATUS_CONTROL_CHANGE, -68, -78, -117, 53, 30, -122, BluetoothInputHost.SUBCLASS1_KEYBOARD, -104, 74, -49, 106, 85, -38, -93};
        Mac instance = Mac.getInstance(KeyProperties.KEY_ALGORITHM_HMAC_SHA1);
        instance.init(new SecretKeySpec(e.a(bArr2), instance.getAlgorithm()));
        return b.encodeToString(instance.doFinal(bArr), 2);
    }

    private boolean e() {
        return this.mContext.checkPermission(Manifest.permission.WRITE_SETTINGS, Binder.getCallingPid(), Binder.getCallingUid()) == 0;
    }
}
