package com.sijla.d;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.sijla.a.a;
import com.sijla.common.d;
import com.sijla.g.a.c;
import com.sijla.g.b;
import com.sijla.g.i;
import com.sijla.g.j;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e extends c {
    private static boolean d;
    private Context b;
    private boolean c;

    @Override // com.sijla.d.c
    public void a(Intent intent) {
    }

    public e(Context context) {
        this(context, false);
    }

    public e(Context context, boolean z) {
        this.b = context;
        this.c = z;
        this.a = "UDFER";
    }

    @Override // com.sijla.d.c
    public void m() {
        a.a(new 1(this));
    }

    public List<File> a(Context context, String str) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        if (!b.a(str)) {
            File file = new File(str);
            if (file.exists() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                HashMap hashMap = new HashMap();
                for (File file2 : listFiles) {
                    if (file2 != null && file2.exists()) {
                        String name = file2.getName();
                        if (file2.isDirectory() || !name.contains(".csv")) {
                            c.a(file2);
                        } else if (!name.endsWith(".lock")) {
                            try {
                                String substring = name.substring(0, name.indexOf(".csv"));
                                StringBuilder sb = new StringBuilder();
                                if (hashMap.containsKey(substring)) {
                                    sb.append((CharSequence) ((StringBuilder) hashMap.get(substring)));
                                }
                                String b = b(context, b.b(b.a(file2)));
                                if (!TextUtils.isEmpty(b)) {
                                    sb.append(b);
                                    hashMap.put(substring, sb);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            file2.delete();
                        }
                    }
                }
                for (String str2 : hashMap.keySet()) {
                    String sb2 = ((StringBuilder) hashMap.get(str2)).toString();
                    if (!TextUtils.isEmpty(sb2)) {
                        File a = c.a(str + (str2 + ".csv-" + System.currentTimeMillis()), b.g(sb2));
                        if (a != null && a.exists()) {
                            arrayList.add(a);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static String b(Context context, String str) {
        try {
            if (str.contains(context.getPackageName())) {
                return str;
            }
            return b.c(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public void a(Context context) {
        String[] strArr = {com.sijla.g.a.b.d(context), com.sijla.g.a.b.e(context)};
        for (String str : strArr) {
            try {
                a(context, str);
                a(context, str, true);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x00ed: APUT  
      (r9v5 java.lang.String[])
      (1 ??[boolean, int, float, short, byte, char])
      (wrap: java.lang.String : 0x00e9: INVOKE  (r7v4 java.lang.String) = (r7v3 java.lang.Object) type: VIRTUAL call: java.lang.Object.toString():java.lang.String)
     */
    private void a(Context context, String str, boolean z) {
        String str2;
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null && listFiles.length != 0) {
            HashMap hashMap = new HashMap();
            for (File file : listFiles) {
                if (file != null && file.exists() && file.isFile()) {
                    hashMap.put(file.getName(), file);
                }
            }
            try {
                JSONArray optJSONArray = com.sijla.b.c.a.optJSONArray("truthurls");
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    optJSONArray = new JSONArray();
                    optJSONArray.put(com.sijla.common.a.g);
                    optJSONArray.put("http://b.qchannel03.cn/n/ard");
                }
                String a = a(context, z);
                ArrayList arrayList = new ArrayList();
                boolean z2 = com.sijla.b.c.a.optInt("repeatReportTruth", 0) == 0;
                int i = 0;
                boolean z3 = false;
                while (true) {
                    if (i >= optJSONArray.length()) {
                        break;
                    }
                    String optString = optJSONArray.optString(i, com.sijla.common.a.g);
                    new d();
                    if (i > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(a);
                        sb.append("&r=");
                        sb.append(z2 ? 1 : 0);
                        str2 = sb.toString();
                    } else {
                        str2 = a;
                    }
                    d a2 = i.a(optString + str2, new JSONObject(), hashMap);
                    Log.d("qlog", "STATUS:" + a2.b());
                    if (a2.b()) {
                        if (!z3) {
                            z3 = true;
                        }
                        if (z2) {
                            break;
                        }
                    } else {
                        String[] strArr = new String[3];
                        strArr[0] = optString;
                        Object a3 = a2.a();
                        if (a3 != null) {
                            strArr[1] = a3.toString();
                            strArr[2] = com.sijla.g.d.b();
                            arrayList.add(strArr);
                        }
                    }
                    i++;
                }
                a(str, z3);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static String a(Context context, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appkey", b.j(context));
            jSONObject.put("uid", j.b(context));
            jSONObject.put("sdk", com.sijla.common.a.a + "");
            if ("".equals(com.sijla.b.c.a.optString("rootdir", ""))) {
                jSONObject.put("sm", "1");
            }
            if (z) {
                jSONObject.put("type", "3");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String stringBuffer = com.sijla.g.b.a.a().a(jSONObject).toString();
        return "?" + stringBuffer;
    }

    /* access modifiers changed from: protected */
    public void a(String str, boolean z) {
        try {
            File[] listFiles = new File(str).listFiles();
            if (listFiles != null) {
                int i = 0;
                if (!z) {
                    if (listFiles.length <= 9) {
                        int length = listFiles.length;
                        while (i < length) {
                            File file = listFiles[i];
                            if (file != null && file.exists() && !file.getName().endsWith(".lock")) {
                                file.renameTo(new File(file.getAbsolutePath() + ".lock"));
                            }
                            i++;
                        }
                        return;
                    }
                }
                int length2 = listFiles.length;
                while (i < length2) {
                    File file2 = listFiles[i];
                    if (file2 != null && file2.exists()) {
                        c.a(file2);
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        if (d) {
            return;
        }
        if (this.c || b()) {
            d = true;
            b.b(this.b);
            a(this.b);
            d = false;
        }
    }

    private boolean b() {
        return b.a(this.b, "lastPostTime", com.sijla.b.c.a.optLong("timepost", 3600)) && com.sijla.g.a.a.h(this.b);
    }

    @Override // com.sijla.d.c
    public void j() {
        a.a(new 2(this));
    }

    @Override // com.sijla.d.c
    public void l() {
        a.a(new 3(this));
    }

    @Override // com.sijla.d.c
    public void a() {
        super.a();
        a.a(new 4(this));
    }
}
