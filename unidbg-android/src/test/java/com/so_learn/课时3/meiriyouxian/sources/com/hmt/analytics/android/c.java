package com.hmt.analytics.android;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import com.hmt.analytics.a.a;
import com.hmt.analytics.objects.e;
import com.hmt.analytics.util.i;
import com.hmt.analytics.util.k;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: GetInfoFromFile */
public class c implements Runnable {
    public static Object a = new Object();
    private static final String e = c.class.getSimpleName();
    private Context b;
    private int c = 1;
    private a d = null;
    private boolean f = true;
    private boolean g = true;

    public c(Context context) {
        this.b = context.getApplicationContext();
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (a) {
            if (!((String) i.b(this.b, "hmt_send_switch", "1")).equals("1")) {
                a.a(e, "Forbid send data by sendSwitch!");
            } else if (this.c != 0) {
                c();
            } else if (h.a(this.b)) {
                b();
            }
        }
    }

    private void b() {
        a.a(e, "Send every day client data!");
        h.c("sendEveryDayClientData-start");
        String g = a.g(this.b);
        if (g != null && !g.equals("")) {
            JSONObject a2 = e.a(this.b);
            JSONArray jSONArray = new JSONArray();
            try {
                jSONArray.put(0, h.a(this.b, a2, "client_data_list"));
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("client_data_list", jSONArray);
                if (i.a(this.b, h.a(this.b, g.h), jSONObject.toString(), g.o, a.d(this.b))) {
                    a();
                }
            } catch (JSONException e2) {
                String str = e;
                a.a(str, "Collected:" + e2.getMessage());
            }
        }
    }

    private void a(m mVar, String str, String str2) {
        ArrayList<com.hmt.analytics.util.e> a2;
        int i = 0;
        while (true) {
            if (i >= g.y / g.t) {
                break;
            }
            new ArrayList();
            try {
                a2 = mVar.a(str2, g.t);
            } catch (SQLiteException e2) {
                a.a(e, "Collected:" + e2.getMessage());
                return;
            }
            if (a2 == null || a2.size() == 0) {
                break;
            }
            Context context = this.b;
            if (new k(context, a2, str, a.d(context)).a()) {
                mVar.b(str2, a2.get(a2.size() - 1).a().intValue());
                if (a2.size() < g.t) {
                    break;
                }
                i++;
            } else if (str2.equals("hmtInfo")) {
                this.f = false;
            } else if (str2.equals("reqInfo")) {
                this.g = false;
            }
        }
        a.a(e, "No data 4 upload!");
        mVar.a(str2);
    }

    public void a() {
        i.a(this.b, "hmt_init_savetime", "upload_save_time", Long.valueOf(System.currentTimeMillis()));
        i.a(this.b, "hmt_send_all_data_success_once", (Object) true);
        i.a(this.b, "hmt_all_data_send_time", Long.valueOf(System.currentTimeMillis()));
    }

    private void c() {
        a.a(e, "Empty data in the database!");
        h.c("sendUploadData-start");
        try {
            m a2 = m.a(this.b);
            a(a2, h.a(this.b, g.h), "hmtInfo");
            a(a2, h.a(this.b, g.i), "reqInfo");
            if (this.f && this.g) {
                a();
            }
            h.c("sendUploadData-end");
            a aVar = this.d;
            if (aVar != null) {
                aVar.a();
            }
        } catch (SQLiteException e2) {
            String str = e;
            a.a(str, "Collected:" + e2.getMessage());
        }
    }
}
