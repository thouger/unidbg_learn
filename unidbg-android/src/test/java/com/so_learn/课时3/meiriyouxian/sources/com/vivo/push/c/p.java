package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.t;
import com.vivo.push.d;
import com.vivo.push.f;
import com.vivo.push.g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: OnSetTagsReceiveTask */
public final class p extends o {
    public p(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DEFAULT_EMERGENCY_APP_PICKER, false);
        t tVar = (t) gVar;
        ArrayList<String> arrayList = tVar.c;
        ArrayList<String> arrayList2 = tVar.d;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        int i = tVar.g;
        String str = tVar.f;
        if (arrayList != null) {
            for (String str2 : arrayList) {
                if (str2.startsWith("ali/")) {
                    arrayList4.add(str2.replace("ali/", ""));
                } else if (str2.startsWith("tag/")) {
                    arrayList3.add(str2.replace("tag/", ""));
                }
            }
        }
        if (arrayList2 != null) {
            for (String str3 : arrayList2) {
                if (str3.startsWith("ali/")) {
                    arrayList6.add(str3.replace("ali/", ""));
                } else if (str3.startsWith("tag/")) {
                    arrayList5.add(str3.replace("tag/", ""));
                }
            }
        }
        if (arrayList3.size() > 0 || arrayList5.size() > 0) {
            if (arrayList3.size() > 0) {
                d.a().a(arrayList3);
            }
            d.a().a(tVar.f, arrayList5.size() > 0 ? 10000 : i);
            f.b(new AnonymousClass1(i, arrayList3, arrayList5, str));
        }
        if (arrayList4.size() > 0 || arrayList6.size() > 0) {
            if (arrayList4.size() > 0) {
                d a = d.a();
                String str4 = (String) arrayList4.get(0);
                a.i = str4;
                a.g.a("APP_ALIAS", str4);
            }
            d.a().a(tVar.f, i);
            f.b(new AnonymousClass2(i, arrayList4, arrayList6, str));
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.DEFAULT_EMERGENCY_APP_PICKER);
    }

    /* compiled from: OnSetTagsReceiveTask */
    /* renamed from: com.vivo.push.c.p$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ List b;
        final /* synthetic */ List c;
        final /* synthetic */ String d;

        AnonymousClass1(int i, List list, List list2, String str) {
            this.a = i;
            this.b = list;
            this.c = list2;
            this.d = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(774, false);
            p.this.c.onSetTags(p.this.a, this.a, this.b, this.c, this.d);
            AppMethodBeat.o(774);
        }
    }

    /* compiled from: OnSetTagsReceiveTask */
    /* renamed from: com.vivo.push.c.p$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ List b;
        final /* synthetic */ List c;
        final /* synthetic */ String d;

        AnonymousClass2(int i, List list, List list2, String str) {
            this.a = i;
            this.b = list;
            this.c = list2;
            this.d = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_PREMIUM_SMS_DENY, false);
            p.this.c.onSetAlias(p.this.a, this.a, this.b, this.c, this.d);
            AppMethodBeat.o(MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_PREMIUM_SMS_DENY);
        }
    }
}
