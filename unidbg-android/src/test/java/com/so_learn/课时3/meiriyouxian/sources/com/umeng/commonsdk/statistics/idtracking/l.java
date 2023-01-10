package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.utils.b;

/* compiled from: UTDIdTracker */
public class l extends a {
    private static final String a = "utdid";
    private Context b;

    public l(Context context) {
        super("utdid");
        this.b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            if (FieldManager.allow(b.u)) {
                return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, this.b);
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
