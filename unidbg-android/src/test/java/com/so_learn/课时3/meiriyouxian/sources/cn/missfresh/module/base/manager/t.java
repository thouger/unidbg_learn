package cn.missfresh.module.base.manager;

import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.common.event.l;
import cn.missfresh.module.base.network.a;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import cn.missfresh.utils.e;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.Request;

/* compiled from: UserAddressInteractor */
public class t {
    private static volatile t a;
    private final String b = getClass().getSimpleName();

    private t() {
        AppMethodBeat.i(14939, false);
        AppMethodBeat.o(14939);
    }

    public static t a() {
        AppMethodBeat.i(14942, false);
        if (a == null) {
            synchronized (t.class) {
                try {
                    if (a == null) {
                        a = new t();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(14942);
                    throw th;
                }
            }
        }
        t tVar = a;
        AppMethodBeat.o(14942);
        return tVar;
    }

    public void a(String str, boolean z, String str2, String str3, boolean z2) {
        Map<String, String> map;
        AppMethodBeat.i(14943, false);
        if (e.a(str2) || Integer.parseInt(str3) == 0) {
            map = c.a("selectAddressId", str);
        } else {
            map = c.a("selectLatLng", str2, "matchScope", str3);
        }
        c.a(this, i.t, map, new AnonymousClass1(z2, z));
        AppMethodBeat.o(14943);
    }

    /* compiled from: UserAddressInteractor */
    /* renamed from: cn.missfresh.module.base.manager.t$1  reason: invalid class name */
    class AnonymousClass1 extends m {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        AnonymousClass1(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void b() {
            AppMethodBeat.i(14924, false);
            if (!this.a) {
                super.b();
            }
            AppMethodBeat.o(14924);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a() {
            AppMethodBeat.i(14926, false);
            if (!this.a) {
                super.a();
            }
            AppMethodBeat.o(14926);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(14929, false);
            EventBus.getDefault().post(new l(false));
            AppMethodBeat.o(14929);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(14931, false);
            EventBus.getDefault().post(new l(false));
            AppMethodBeat.o(14931);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            int i;
            int i2;
            int i3;
            AppMethodBeat.i(14936, false);
            a c = c(str);
            if (c.a != 0) {
                d.b(t.this.b, "dialog error");
                EventBus.getDefault().post(new l(false));
                cn.missfresh.ui.a.a.a(c.b);
            } else if (!b.a(str)) {
                try {
                    JSONObject parseObject = JSONObject.parseObject(str);
                    if (parseObject != null) {
                        List<UserAddress> parseArray = JSON.parseArray(parseObject.getString("addresses"), UserAddress.class);
                        String string = parseObject.getString("tips");
                        if (this.b) {
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            ArrayList arrayList3 = new ArrayList();
                            i3 = 0;
                            int i4 = 0;
                            int i5 = 0;
                            for (UserAddress userAddress : parseArray) {
                                if (userAddress.usable == 0) {
                                    i3++;
                                    arrayList.add(userAddress);
                                } else if (userAddress.usable == 1) {
                                    arrayList2.add(userAddress);
                                    i5++;
                                } else {
                                    i4++;
                                    arrayList3.add(userAddress);
                                }
                            }
                            if (arrayList2.size() != 0) {
                                ((UserAddress) arrayList2.get(0)).title = "\u90e8\u5206\u5546\u54c1\u53ef\u80fd\u4e0d\u652f\u6301\u4ee5\u4e0b\u5730\u5740\u914d\u9001";
                            }
                            if (arrayList3.size() != 0) {
                                ((UserAddress) arrayList3.get(0)).title = "\u4ee5\u4e0b\u5730\u5740\u8d85\u51fa\u914d\u9001\u8303\u56f4,\u65e0\u6cd5\u4f7f\u7528";
                            }
                            parseArray.clear();
                            parseArray.addAll(arrayList);
                            parseArray.addAll(arrayList2);
                            parseArray.addAll(arrayList3);
                            i = i4;
                            i2 = i5;
                        } else {
                            i3 = 0;
                            i2 = 0;
                            i = 0;
                        }
                        EventBus.getDefault().post(new l(parseObject.getIntValue("select_index"), parseArray, i3, i2, i, string));
                    }
                } catch (Exception e) {
                    d.a(t.this.b, e);
                    EventBus.getDefault().post(new l(false));
                }
            }
            AppMethodBeat.o(14936);
        }
    }
}
