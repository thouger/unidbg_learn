package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.statistics.common.a;
import com.umeng.commonsdk.utils.b;

/* compiled from: IDFATracker */
public class c extends a {
    private static final String a = "idfa";
    private Context b;

    public c(Context context) {
        super(a);
        this.b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        String a2 = FieldManager.allow(b.w) ? a.a(this.b) : null;
        return a2 == null ? "" : a2;
    }
}
