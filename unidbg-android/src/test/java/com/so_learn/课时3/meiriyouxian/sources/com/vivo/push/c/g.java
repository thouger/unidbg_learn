package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.t;
import com.vivo.push.d;
import com.vivo.push.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: OnDelTagsReceiveTask */
public final class g extends o {
    public g(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_SERVICE_ACCESS_WARNING, false);
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
                d.a().b(arrayList3);
            }
            d.a().a(tVar.f, arrayList5.size() > 0 ? 10000 : i);
            f.b(new AnonymousClass1(i, arrayList3, arrayList5, str));
        }
        if (arrayList4.size() > 0 || arrayList6.size() > 0) {
            if (arrayList4.size() > 0) {
                d a = d.a();
                if (arrayList4.contains(a.i)) {
                    a.d();
                }
            }
            d.a().a(tVar.f, i);
            f.b(new AnonymousClass2(i, arrayList4, arrayList6, str));
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_SERVICE_ACCESS_WARNING);
    }

    /* compiled from: OnDelTagsReceiveTask */
    /* renamed from: com.vivo.push.c.g$1  reason: invalid class name */
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
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_WPS_SETUP, false);
            g.this.c.onDelTags(g.this.a, this.a, this.b, this.c, this.d);
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_WPS_SETUP);
        }
    }

    /* compiled from: OnDelTagsReceiveTask */
    /* renamed from: com.vivo.push.c.g$2  reason: invalid class name */
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
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_VPN_CANNOT_CONNECT, false);
            g.this.c.onDelAlias(g.this.a, this.a, this.b, this.c, this.d);
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_VPN_CANNOT_CONNECT);
        }
    }
}
