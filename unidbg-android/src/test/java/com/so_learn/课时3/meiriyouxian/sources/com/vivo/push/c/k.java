package com.vivo.push.c;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.h;
import com.vivo.push.b.o;
import com.vivo.push.b.x;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.d;
import com.vivo.push.f;
import com.vivo.push.g;
import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.util.n;
import com.vivo.push.util.y;
import java.util.HashMap;

/* compiled from: OnMessageReceiveTask */
public final class k extends o {
    public k(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_ADB, false);
        o oVar = (o) gVar;
        d.a().a(new h(String.valueOf(oVar.e)));
        if (!ClientConfigManagerImpl.getInstance(this.a).isEnablePush()) {
            n.d("OnMessageTask", "command  " + gVar + " is ignore by disable push ");
            x xVar = new x(1020);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("messageID", String.valueOf(oVar.e));
            String b = y.b(this.a, this.a.getPackageName());
            if (!TextUtils.isEmpty(b)) {
                hashMap.put("remoteAppId", b);
            }
            xVar.c = hashMap;
            d.a().a(xVar);
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_ADB);
        } else if (!d.a().f || a(y.d(this.a), oVar.b(), oVar.d)) {
            UnvarnishedMessage c = oVar.c();
            if (c != null) {
                int targetType = c.getTargetType();
                String tragetContent = c.getTragetContent();
                n.d("OnMessageTask", "tragetType is " + targetType + " ; target is " + tragetContent);
                f.b(new AnonymousClass1(c));
                AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_ADB);
                return;
            }
            n.a("OnMessageTask", " message is null");
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_ADB);
        } else {
            x xVar2 = new x(1021);
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.put("messageID", String.valueOf(oVar.e));
            String b2 = y.b(this.a, this.a.getPackageName());
            if (!TextUtils.isEmpty(b2)) {
                hashMap2.put("remoteAppId", b2);
            }
            xVar2.c = hashMap2;
            d.a().a(xVar2);
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_ADB);
        }
    }

    /* compiled from: OnMessageReceiveTask */
    /* renamed from: com.vivo.push.c.k$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ UnvarnishedMessage a;

        AnonymousClass1(UnvarnishedMessage unvarnishedMessage) {
            this.a = unvarnishedMessage;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.BLUETOOTH_DIALOG_FRAGMENT, false);
            k.this.c.onTransmissionMessage(k.this.a, this.a);
            AppMethodBeat.o(MetricsProto.MetricsEvent.BLUETOOTH_DIALOG_FRAGMENT);
        }
    }
}
