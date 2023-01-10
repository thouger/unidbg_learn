package cn.missfresh.module.base.common.resourcemanager.viewmodel;

import android.net.Uri;
import android.opengl.EGL14;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.common.config.a;
import cn.missfresh.module.base.common.resourcemanager.api.RMApiManager;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipWrapBean;
import cn.missfresh.module.base.network.h;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.sherlock.Sherlock;
import cn.missfresh.sherlock.to.NetworkTO;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMViewModel extends BaseViewModel {
    private MutableLiveData<List<SkipBean>> a = new MutableLiveData<>();
    private MutableLiveData<List<SkipBean>> b = new MutableLiveData<>();
    private MutableLiveData<List<SkipBean>> c = new MutableLiveData<>();
    private MutableLiveData<List<SkipBean>> d = new MutableLiveData<>();
    private MutableLiveData<List<SkipBean>> e = new MutableLiveData<>();
    private MutableLiveData<List<SkipBean>> f = new MutableLiveData<>();
    private MutableLiveData<List<SkipBean>> g = new MutableLiveData<>();
    private MutableLiveData<List<SkipBean>> h = new MutableLiveData<>();
    private MutableLiveData<List<SkipBean>> i = new MutableLiveData<>();

    public RMViewModel() {
        AppMethodBeat.i(12391, false);
        AppMethodBeat.o(12391);
    }

    static /* synthetic */ void a(RMViewModel rMViewModel, int i, String str, Map map) {
        AppMethodBeat.i(12422, false);
        rMViewModel.a(i, str, map);
        AppMethodBeat.o(12422);
    }

    static /* synthetic */ void a(RMViewModel rMViewModel, String str) {
        AppMethodBeat.i(EGL14.EGL_BACK_BUFFER, false);
        rMViewModel.a(str);
        AppMethodBeat.o(EGL14.EGL_BACK_BUFFER);
    }

    static /* synthetic */ void a(RMViewModel rMViewModel, List list) {
        AppMethodBeat.i(EGL14.EGL_TEXTURE_FORMAT, false);
        rMViewModel.a(list);
        AppMethodBeat.o(EGL14.EGL_TEXTURE_FORMAT);
    }

    static /* synthetic */ boolean a(RMViewModel rMViewModel, String str, List list) {
        AppMethodBeat.i(EGL14.EGL_MIPMAP_TEXTURE, false);
        boolean a = rMViewModel.a(str, list);
        AppMethodBeat.o(EGL14.EGL_MIPMAP_TEXTURE);
        return a;
    }

    public MutableLiveData<List<SkipBean>> a() {
        return this.i;
    }

    public MutableLiveData<List<SkipBean>> b() {
        return this.a;
    }

    public MutableLiveData<List<SkipBean>> c() {
        return this.h;
    }

    public MutableLiveData<List<SkipBean>> d() {
        return this.b;
    }

    public void a(int i, List<String> list) {
        AppMethodBeat.i(12399, false);
        a(i, list, "");
        AppMethodBeat.o(12399);
    }

    public void a(int i, List<String> list, String str) {
        AppMethodBeat.i(12402, false);
        HashMap hashMap = new HashMap();
        hashMap.put("saleGroupType", Integer.valueOf(a.b));
        hashMap.put("sceneList", list);
        hashMap.put("fromSource", str);
        hashMap.put("bizChannel", "MRYX");
        hashMap.put("operationPlatform", "AS");
        hashMap.put("sellerType", Integer.valueOf(i));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("param", hashMap);
        RMApiManager.getRMApi().getResourceList(hashMap2).a(new h()).subscribe(new AnonymousClass1(this, list, hashMap2));
        AppMethodBeat.o(12402);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.common.resourcemanager.viewmodel.RMViewModel$1  reason: invalid class name */
    public class AnonymousClass1 extends i<List<SkipWrapBean>> {
        final /* synthetic */ List a;
        final /* synthetic */ Map b;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass1(cn.missfresh.module.mvp.a.a aVar, List list, Map map) {
            super(aVar);
            this.a = list;
            this.b = map;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(EGL14.EGL_TEXTURE_RGBA, false);
            a((List) obj);
            AppMethodBeat.o(EGL14.EGL_TEXTURE_RGBA);
        }

        public void a(List<SkipWrapBean> list) {
            AppMethodBeat.i(12376, false);
            if (b.a(list)) {
                RMViewModel.a(RMViewModel.this, this.a);
                AppMethodBeat.o(12376);
                return;
            }
            for (String str : this.a) {
                if (!RMViewModel.a(RMViewModel.this, str, list)) {
                    RMViewModel.a(RMViewModel.this, str);
                }
            }
            AppMethodBeat.o(12376);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(EGL14.EGL_NO_TEXTURE, false);
            RMViewModel.a(RMViewModel.this, i, str, this.b);
            RMViewModel.a(RMViewModel.this, this.a);
            AppMethodBeat.o(EGL14.EGL_NO_TEXTURE);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean a(String str, List<SkipWrapBean> list) {
        AppMethodBeat.i(12406, false);
        for (SkipWrapBean skipWrapBean : list) {
            if (str.equals(skipWrapBean.scene)) {
                char c = '\uffff';
                switch (str.hashCode()) {
                    case -1653634393:
                        if (str.equals("HOME_TOAST")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -626426241:
                        if (str.equals("USER_JIU_GONG_GE")) {
                            c = 3;
                            break;
                        }
                        break;
                    case -166084064:
                        if (str.equals("USER_BANNER")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 517438145:
                        if (str.equals("USER_TOP")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 638847764:
                        if (str.equals("HOME_BEAR")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1238021516:
                        if (str.equals("HOME_LEFT_BEAR")) {
                            c = 7;
                            break;
                        }
                        break;
                    case 1270514213:
                        if (str.equals("ORDER_BEAR")) {
                            c = 5;
                            break;
                        }
                        break;
                    case 1496039585:
                        if (str.equals("HOME_OPEN_SCREEN")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 1642738667:
                        if (str.equals("SEARCH_BEAR")) {
                            c = '\b';
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        this.a.setValue(skipWrapBean.resourceList);
                        AppMethodBeat.o(12406);
                        return true;
                    case 1:
                        this.c.setValue(skipWrapBean.resourceList);
                        AppMethodBeat.o(12406);
                        return true;
                    case 2:
                        this.d.setValue(skipWrapBean.resourceList);
                        AppMethodBeat.o(12406);
                        return true;
                    case 3:
                        this.e.setValue(skipWrapBean.resourceList);
                        AppMethodBeat.o(12406);
                        return true;
                    case 4:
                        this.f.setValue(skipWrapBean.resourceList);
                        AppMethodBeat.o(12406);
                        return true;
                    case 5:
                        this.g.setValue(skipWrapBean.resourceList);
                        AppMethodBeat.o(12406);
                        return true;
                    case 6:
                        this.h.setValue(skipWrapBean.resourceList);
                        AppMethodBeat.o(12406);
                        return true;
                    case 7:
                        this.i.setValue(skipWrapBean.resourceList);
                        AppMethodBeat.o(12406);
                        return true;
                    case '\b':
                        this.b.setValue(skipWrapBean.resourceList);
                        AppMethodBeat.o(12406);
                        return true;
                }
            }
        }
        AppMethodBeat.o(12406);
        return false;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        if (r4.equals("HOME_BEAR") != false) goto L_0x0072;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r4) {
        /*
        // Method dump skipped, instructions count: 280
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.common.resourcemanager.viewmodel.RMViewModel.a(java.lang.String):void");
    }

    private void a(List<String> list) {
        AppMethodBeat.i(12411, false);
        for (String str : list) {
            a(str);
        }
        AppMethodBeat.o(12411);
    }

    private void a(int i, String str, Map<String, Object> map) {
        String str2;
        AppMethodBeat.i(12414, false);
        if (i != -1001) {
            AppMethodBeat.o(12414);
            return;
        }
        try {
            if (cn.missfresh.module.base.common.config.b.b.endsWith(NotificationIconUtil.SPLIT_CHAR)) {
                str2 = cn.missfresh.module.base.common.config.b.b + "ms/rmp/getResourceList";
            } else {
                str2 = cn.missfresh.module.base.common.config.b.b + "/ms/rmp/getResourceList";
            }
            NetworkTO networkTO = new NetworkTO();
            networkTO.setUrl(str2);
            networkTO.setRequestMethod("POST");
            networkTO.setDomain(Uri.parse(cn.missfresh.module.base.common.config.b.b).getHost());
            networkTO.setRequestParameter(map.toString());
            networkTO.setResponseCode("70003");
            networkTO.setResponseMessage(str);
            networkTO.setStatusCode(i + "");
            Sherlock.reportNetworkInfo(networkTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(12414);
    }
}
