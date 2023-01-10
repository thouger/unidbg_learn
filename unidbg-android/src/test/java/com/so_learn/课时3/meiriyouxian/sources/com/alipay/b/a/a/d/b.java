package com.alipay.b.a.a.d;

import com.alipay.b.a.a.e.b.a;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;

public final class b {
    private File a = null;
    private a b = null;

    public b(String str, a aVar) {
        this.a = new File(str);
        this.b = aVar;
    }

    private static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "id");
            jSONObject.put("error", str);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    /* access modifiers changed from: private */
    public synchronized void b() {
        if (this.a != null) {
            if (this.a.exists() && this.a.isDirectory() && this.a.list().length != 0) {
                ArrayList arrayList = new ArrayList();
                for (String str : this.a.list()) {
                    arrayList.add(str);
                }
                Collections.sort(arrayList);
                String str2 = (String) arrayList.get(arrayList.size() - 1);
                int size = arrayList.size();
                if (str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + MsgConstant.CACHE_LOG_FILE_EXT)) {
                    if (arrayList.size() >= 2) {
                        str2 = (String) arrayList.get(arrayList.size() - 2);
                        size--;
                    } else {
                        return;
                    }
                }
                if (!this.b.a(a(com.alipay.b.a.a.a.b.a(this.a.getAbsolutePath(), str2)))) {
                    size--;
                }
                for (int i = 0; i < size; i++) {
                    new File(this.a, (String) arrayList.get(i)).delete();
                }
            }
        }
    }

    public final void a() {
        new Thread(new c(this)).start();
    }
}
