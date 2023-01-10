package com.google.android.exoplayer2.source.dash.a;

import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;

/* compiled from: EventStream */
public final class e {
    public final EventMessage[] a;
    public final long[] b;
    public final String c;
    public final String d;
    public final long e;

    public e(String str, String str2, long j, long[] jArr, EventMessage[] eventMessageArr) {
        this.c = str;
        this.d = str2;
        this.e = j;
        this.b = jArr;
        this.a = eventMessageArr;
    }

    public String a() {
        return this.c + NotificationIconUtil.SPLIT_CHAR + this.d;
    }
}
