package com.ta.utdid2.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.ta.utdid2.a.a.f;
import com.ta.utdid2.b.a.b;
import java.io.File;
import java.util.Map;

public class c {
    private SharedPreferences.Editor a;

    /* renamed from: a  reason: collision with other field name */
    private SharedPreferences f327a;

    /* renamed from: a  reason: collision with other field name */
    private b.a f328a;

    /* renamed from: a  reason: collision with other field name */
    private b f329a;

    /* renamed from: a  reason: collision with other field name */
    private d f330a;
    private String b = "";
    private String c = "";
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i;
    private Context mContext;

    /* JADX WARNING: Removed duplicated region for block: B:84:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0181 A[Catch:{ Exception -> 0x018d }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public c(android.content.Context r10, java.lang.String r11, java.lang.String r12, boolean r13, boolean r14) {
        /*
        // Method dump skipped, instructions count: 398
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.c.<init>(android.content.Context, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    private d a(String str) {
        File a = m4073a(str);
        if (a == null) {
            return null;
        }
        this.f330a = new d(a.getAbsolutePath());
        return this.f330a;
    }

    /* renamed from: a  reason: collision with other method in class */
    private File m4073a(String str) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", externalStorageDirectory.getAbsolutePath(), File.separator, str));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private void a(SharedPreferences sharedPreferences, b bVar) {
        b.a a;
        if (sharedPreferences != null && bVar != null && (a = bVar.mo4075a()) != null) {
            a.b();
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    a.a(key, (String) value);
                } else if (value instanceof Integer) {
                    a.a(key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    a.a(key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    a.a(key, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    a.a(key, ((Boolean) value).booleanValue());
                }
            }
            try {
                a.commit();
            } catch (Exception unused) {
            }
        }
    }

    private void a(b bVar, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit;
        if (!(bVar == null || sharedPreferences == null || (edit = sharedPreferences.edit()) == null)) {
            edit.clear();
            for (Map.Entry<String, ?> entry : bVar.getAll().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    edit.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    edit.putInt(key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    edit.putLong(key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    edit.putFloat(key, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    edit.putBoolean(key, ((Boolean) value).booleanValue());
                }
            }
            edit.commit();
        }
    }

    /* renamed from: b  reason: collision with other method in class */
    private boolean m4074b() {
        b bVar = this.f329a;
        if (bVar == null) {
            return false;
        }
        boolean a = bVar.m4072a();
        if (!a) {
            commit();
        }
        return a;
    }

    private void b() {
        b bVar;
        SharedPreferences sharedPreferences;
        if (this.a == null && (sharedPreferences = this.f327a) != null) {
            this.a = sharedPreferences.edit();
        }
        if (this.h && this.f328a == null && (bVar = this.f329a) != null) {
            this.f328a = bVar.mo4075a();
        }
        m4074b();
    }

    public void putString(String str, String str2) {
        if (!f.m4071a(str) && !str.equals("t")) {
            b();
            SharedPreferences.Editor editor = this.a;
            if (editor != null) {
                editor.putString(str, str2);
            }
            b.a aVar = this.f328a;
            if (aVar != null) {
                aVar.a(str, str2);
            }
        }
    }

    public void remove(String str) {
        if (!f.m4071a(str) && !str.equals("t")) {
            b();
            SharedPreferences.Editor editor = this.a;
            if (editor != null) {
                editor.remove(str);
            }
            b.a aVar = this.f328a;
            if (aVar != null) {
                aVar.a(str);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0085, code lost:
        if (r6.f328a.commit() == false) goto L_0x0087;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x009f A[Catch:{ Exception -> 0x00a9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean commit() {
        /*
            r6 = this;
            long r0 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r2 = r6.a
            r3 = 0
            if (r2 == 0) goto L_0x0021
            boolean r4 = r6.i
            if (r4 != 0) goto L_0x0017
            android.content.SharedPreferences r4 = r6.f327a
            if (r4 == 0) goto L_0x0017
            java.lang.String r4 = "t"
            r2.putLong(r4, r0)
        L_0x0017:
            android.content.SharedPreferences$Editor r0 = r6.a
            boolean r0 = r0.commit()
            if (r0 != 0) goto L_0x0021
            r0 = r3
            goto L_0x0022
        L_0x0021:
            r0 = 1
        L_0x0022:
            android.content.SharedPreferences r1 = r6.f327a
            if (r1 == 0) goto L_0x0032
            android.content.Context r1 = r6.mContext
            if (r1 == 0) goto L_0x0032
            java.lang.String r2 = r6.b
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            r6.f327a = r1
        L_0x0032:
            r1 = 0
            java.lang.String r1 = android.os.Environment.getExternalStorageState()     // Catch:{ Exception -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r2 = move-exception
            r2.printStackTrace()
        L_0x003c:
            boolean r2 = com.ta.utdid2.a.a.f.m4071a(r1)
            if (r2 != 0) goto L_0x00a9
            java.lang.String r2 = "mounted"
            boolean r4 = r1.equals(r2)
            if (r4 == 0) goto L_0x0088
            com.ta.utdid2.b.a.b r4 = r6.f329a
            if (r4 != 0) goto L_0x007b
            java.lang.String r4 = r6.c
            com.ta.utdid2.b.a.d r4 = r6.a(r4)
            if (r4 == 0) goto L_0x0088
            java.lang.String r5 = r6.b
            com.ta.utdid2.b.a.b r4 = r4.a(r5, r3)
            r6.f329a = r4
            boolean r4 = r6.i
            if (r4 != 0) goto L_0x006b
            android.content.SharedPreferences r4 = r6.f327a
            com.ta.utdid2.b.a.b r5 = r6.f329a
            r6.a(r4, r5)
            goto L_0x0072
        L_0x006b:
            com.ta.utdid2.b.a.b r4 = r6.f329a
            android.content.SharedPreferences r5 = r6.f327a
            r6.a(r4, r5)
        L_0x0072:
            com.ta.utdid2.b.a.b r4 = r6.f329a
            com.ta.utdid2.b.a.b$a r4 = r4.mo4075a()
            r6.f328a = r4
            goto L_0x0088
        L_0x007b:
            com.ta.utdid2.b.a.b$a r4 = r6.f328a     // Catch:{ Exception -> 0x0087 }
            if (r4 == 0) goto L_0x0088
            com.ta.utdid2.b.a.b$a r4 = r6.f328a     // Catch:{ Exception -> 0x0087 }
            boolean r4 = r4.commit()     // Catch:{ Exception -> 0x0087 }
            if (r4 != 0) goto L_0x0088
        L_0x0087:
            r0 = r3
        L_0x0088:
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L_0x009b
            java.lang.String r2 = "mounted_ro"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00a9
            com.ta.utdid2.b.a.b r1 = r6.f329a
            if (r1 == 0) goto L_0x00a9
        L_0x009b:
            com.ta.utdid2.b.a.d r1 = r6.f330a     // Catch:{ Exception -> 0x00a9 }
            if (r1 == 0) goto L_0x00a9
            com.ta.utdid2.b.a.d r1 = r6.f330a     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r2 = r6.b     // Catch:{ Exception -> 0x00a9 }
            com.ta.utdid2.b.a.b r1 = r1.a(r2, r3)     // Catch:{ Exception -> 0x00a9 }
            r6.f329a = r1     // Catch:{ Exception -> 0x00a9 }
        L_0x00a9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.c.commit():boolean");
    }

    public String getString(String str) {
        m4074b();
        SharedPreferences sharedPreferences = this.f327a;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(str, "");
            if (!f.m4071a(string)) {
                return string;
            }
        }
        b bVar = this.f329a;
        if (bVar != null) {
            return bVar.getString(str, "");
        }
        return "";
    }
}
