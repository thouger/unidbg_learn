package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.f.b;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import java.util.HashMap;
import java.util.Map;

public class TMNTokenClient {
    private static TMNTokenClient a;
    private Context b = null;

    /* renamed from: com.alipay.apmobilesecuritysdk.face.TMNTokenClient$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Map a;
        final /* synthetic */ InitResultListener b;
        final /* synthetic */ String c;

        AnonymousClass1(Map map, InitResultListener initResultListener, String str) {
            this.a = map;
            this.b = initResultListener;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            int a = new a(TMNTokenClient.this.b).a(this.a);
            InitResultListener initResultListener = this.b;
            if (initResultListener != null) {
                if (a == 0) {
                    this.b.onResult(a.a(TMNTokenClient.this.b, this.c), 0);
                    return;
                }
                initResultListener.onResult("", a);
            }
        }
    }

    public interface InitResultListener {
        void onResult(String str, int i);
    }

    private TMNTokenClient(Context context) {
        if (context != null) {
            this.b = context;
            return;
        }
        throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
    }

    public static TMNTokenClient getInstance(Context context) {
        if (a == null) {
            synchronized (TMNTokenClient.class) {
                if (a == null) {
                    a = new TMNTokenClient(context);
                }
            }
        }
        return a;
    }

    public void intiToken(String str, String str2, String str3, InitResultListener initResultListener) {
        if (com.alipay.b.a.a.a.a.a(str) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (com.alipay.b.a.a.a.a.a(str2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("utdid", UtdidWrapper.getUtdid(this.b));
        hashMap.put("tid", "");
        hashMap.put("userId", "");
        hashMap.put("appName", str);
        hashMap.put("appKeyClient", str2);
        hashMap.put("appchannel", "openapi");
        hashMap.put(TextToSpeech.Engine.KEY_PARAM_SESSION_ID, str3);
        hashMap.put("rpcVersion", "8");
        b.a().a(new AnonymousClass1(hashMap, initResultListener, str));
    }
}
