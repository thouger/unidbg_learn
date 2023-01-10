package com.vivo.push.ups;

import android.content.Context;
import android.os.Bundle;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;

public class VUpsManager {
    private static VUpsManager mVUpsManager;

    public void onCommandResult(Context context, Bundle bundle) {
    }

    public static VUpsManager getInstance() {
        AppMethodBeat.i(3032, false);
        if (mVUpsManager == null) {
            mVUpsManager = new VUpsManager();
        }
        VUpsManager vUpsManager = mVUpsManager;
        AppMethodBeat.o(3032);
        return vUpsManager;
    }

    /* renamed from: com.vivo.push.ups.VUpsManager$1  reason: invalid class name */
    class AnonymousClass1 implements IPushActionListener {
        final /* synthetic */ UPSRegisterCallback a;
        final /* synthetic */ Context b;

        AnonymousClass1(UPSRegisterCallback uPSRegisterCallback, Context context) {
            this.a = uPSRegisterCallback;
            this.b = context;
        }

        @Override // com.vivo.push.IPushActionListener
        public final void onStateChanged(int i) {
            AppMethodBeat.i(2990, false);
            this.a.onResult(new TokenResult(i, PushClient.getInstance(this.b).getRegId()));
            AppMethodBeat.o(2990);
        }
    }

    public void registerToken(Context context, String str, String str2, String str3, UPSRegisterCallback uPSRegisterCallback) {
        AppMethodBeat.i(3037, false);
        PushClient.getInstance(context).turnOnPush(new AnonymousClass1(uPSRegisterCallback, context));
        AppMethodBeat.o(3037);
    }

    /* renamed from: com.vivo.push.ups.VUpsManager$2  reason: invalid class name */
    class AnonymousClass2 implements IPushActionListener {
        final /* synthetic */ UPSRegisterCallback a;

        AnonymousClass2(UPSRegisterCallback uPSRegisterCallback) {
            this.a = uPSRegisterCallback;
        }

        @Override // com.vivo.push.IPushActionListener
        public final void onStateChanged(int i) {
            AppMethodBeat.i(3002, false);
            this.a.onResult(new TokenResult(i, ""));
            AppMethodBeat.o(3002);
        }
    }

    public void unRegisterToken(Context context, UPSRegisterCallback uPSRegisterCallback) {
        AppMethodBeat.i(3042, false);
        PushClient.getInstance(context).turnOffPush(new AnonymousClass2(uPSRegisterCallback));
        AppMethodBeat.o(3042);
    }

    public void turnOnPush(Context context, UPSTurnCallback uPSTurnCallback) {
        AppMethodBeat.i(3047, false);
        PushClient.getInstance(context).initialize();
        uPSTurnCallback.onResult(new CodeResult(0));
        AppMethodBeat.o(3047);
    }

    /* renamed from: com.vivo.push.ups.VUpsManager$3  reason: invalid class name */
    class AnonymousClass3 implements IPushActionListener {
        final /* synthetic */ UPSTurnCallback a;

        AnonymousClass3(UPSTurnCallback uPSTurnCallback) {
            this.a = uPSTurnCallback;
        }

        @Override // com.vivo.push.IPushActionListener
        public final void onStateChanged(int i) {
            AppMethodBeat.i(3014, false);
            this.a.onResult(new CodeResult(i));
            AppMethodBeat.o(3014);
        }
    }

    public void turnOffPush(Context context, UPSTurnCallback uPSTurnCallback) {
        AppMethodBeat.i(3054, false);
        PushClient.getInstance(context).turnOffPush(new AnonymousClass3(uPSTurnCallback));
        AppMethodBeat.o(3054);
    }
}
