package cn.missfresh.module.base.im;

import android.text.TextUtils;
import cn.missfresh.module.base.api.MainApiManager;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.network.h;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.reactivex.u;
import java.util.HashMap;

/* compiled from: VirtualPhoneHelper */
public class c {
    public static void a(String str, String str2, String str3, a aVar) {
        AppMethodBeat.i(13499, false);
        HashMap hashMap = new HashMap();
        hashMap.put("orderId", str);
        hashMap.put("receiverPhone", str2);
        hashMap.put("warehouseCode", str3);
        RequestParam requestParam = new RequestParam();
        requestParam.setParam(hashMap);
        MainApiManager.getMainApi().obtainVirtualPhone(requestParam).a((u) new h()).subscribe(new AnonymousClass1(null, aVar));
        AppMethodBeat.o(13499);
    }

    /* compiled from: VirtualPhoneHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.im.c$1  reason: invalid class name */
    public static class AnonymousClass1 extends i<String> {
        final /* synthetic */ a a;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass1(a aVar, a aVar2) {
            super(aVar);
            this.a = aVar2;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(13495, false);
            a((String) obj);
            AppMethodBeat.o(13495);
        }

        public void a(String str) {
            AppMethodBeat.i(13493, false);
            if (TextUtils.isEmpty(str)) {
                a aVar = this.a;
                if (aVar != null) {
                    aVar.a(null);
                }
                AppMethodBeat.o(13493);
                return;
            }
            try {
                JSONObject parseObject = JSON.parseObject(str);
                if (parseObject != null) {
                    if (parseObject.containsKey("phone")) {
                        if (this.a != null) {
                            this.a.a(parseObject.getString("phone"));
                        }
                        AppMethodBeat.o(13493);
                        return;
                    }
                }
                if (this.a != null) {
                    this.a.a(null);
                }
                AppMethodBeat.o(13493);
            } catch (Exception unused) {
                a aVar2 = this.a;
                if (aVar2 != null) {
                    aVar2.a(null);
                }
            }
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(13494, false);
            a aVar = this.a;
            if (aVar != null) {
                aVar.a(null);
            }
            d.b("VirtualPhoneHelper", "onFail, code=" + i + ", " + str);
            AppMethodBeat.o(13494);
        }
    }
}
