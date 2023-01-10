package cn.missfresh.flutter.feature.base;

import android.Manifest;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import cn.missfresh.basiclib.ui.permission.b;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.location_api.bean.Location;
import cn.missfresh.module.base.bean.EventAppAddress;
import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.event.k;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.common.Constants;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.List;

public class AddressFlutterFeature extends a {
    private c a;
    private a b;
    private boolean d;
    private Observer e;
    private Observer f;
    private Observer<EventAppAddress> g = new AnonymousClass1();

    static /* synthetic */ void a(AddressFlutterFeature addressFlutterFeature, double d, double d2, ILocationService iLocationService) {
        AppMethodBeat.i(21243, false);
        addressFlutterFeature.a(d, d2, iLocationService);
        AppMethodBeat.o(21243);
    }

    static /* synthetic */ void b(AddressFlutterFeature addressFlutterFeature) {
        AppMethodBeat.i(21245, false);
        addressFlutterFeature.f();
        AppMethodBeat.o(21245);
    }

    public AddressFlutterFeature(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        super(fragmentActivity, methodChannel);
        AppMethodBeat.i(21226, false);
        ((ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation()).d().a(fragmentActivity, this.g, true);
        AppMethodBeat.o(21226);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.base.AddressFlutterFeature$1  reason: invalid class name */
    public class AnonymousClass1 implements Observer<EventAppAddress> {
        AnonymousClass1() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(21206, false);
            a((EventAppAddress) obj);
            AppMethodBeat.o(21206);
        }

        public void a(EventAppAddress eventAppAddress) {
            AppMethodBeat.i(21204, false);
            AddressFlutterFeature.this.c().invokeMethod("listenUpdateChromeInfo", JSON.toJSONString(eventAppAddress.userLocationBean));
            AppMethodBeat.o(21204);
        }
    }

    public void a(MethodCall methodCall, MethodChannel.Result result) {
        UserAddress userAddress;
        AppMethodBeat.i(21229, false);
        if (methodCall.method.equals("contact")) {
            e();
            result.success(true);
        } else {
            String str = null;
            if (methodCall.method.equals("updateAddress")) {
                try {
                    userAddress = (UserAddress) JSONObject.parseObject((String) methodCall.argument("address"), UserAddress.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    userAddress = null;
                }
                a(userAddress);
                result.success(true);
            } else if (methodCall.method.equals("address")) {
                if (cn.missfresh.module.base.manager.c.q().k() != null) {
                    str = JSON.toJSONString(cn.missfresh.module.base.manager.c.q().k());
                }
                result.success(str);
            } else if (methodCall.method.equals("getChromeInfo")) {
                cn.missfresh.module.base.manager.c.m();
                result.success(cn.missfresh.module.base.manager.c.m());
            } else if (methodCall.method.equals("getLocation")) {
                ILocationService iLocationService = (ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation();
                if (this.a == null) {
                    this.a = new c();
                }
                this.c.put("getLocation", result);
                if (this.a.a(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    a();
                } else {
                    f();
                }
            } else if (methodCall.method.equals("jumpToAddressList")) {
                try {
                    g();
                    a(methodCall.method, result);
                    UserAddress userAddress2 = (UserAddress) JSON.parseObject((String) methodCall.argument("addressInfo"), UserAddress.class);
                    com.alibaba.android.arouter.b.a.a().a("/user/address_list").withInt("cur_address_id", userAddress2.id).withInt("from", 0).withBoolean("isdatasort", true).withString("lat_lng", userAddress2.latLng).withInt("match_scope", userAddress2.matchScope).withString("sub_title", "\u4ee5\u4e0b\u5730\u5740\u8d85\u51fa\u53ef\u4fee\u6539\u8303\u56f4").navigation(b());
                } catch (Exception unused) {
                }
            } else if (methodCall.method.equals("updateChromeInfo")) {
                ((ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation()).a(2);
            }
        }
        AppMethodBeat.o(21229);
    }

    private void d() {
        AppMethodBeat.i(21230, false);
        if (this.a == null) {
            this.a = new c();
        }
        AppMethodBeat.o(21230);
    }

    private void e() {
        AppMethodBeat.i(21232, false);
        d();
        if (this.b == null) {
            this.b = new a(this, null);
        }
        cn.missfresh.module.base.permission.a.a(b(), Manifest.permission.READ_CONTACTS, this.b, this.a);
        AppMethodBeat.o(21232);
    }

    /* access modifiers changed from: private */
    public class a implements b {
        private a() {
        }

        /* synthetic */ a(AddressFlutterFeature addressFlutterFeature, AnonymousClass1 r2) {
            this();
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void l_(String str) {
            AppMethodBeat.i(21222, false);
            cn.missfresh.ui.a.a.b("\u9700\u8981\u5728\u624b\u673a\u8bbe\u7f6e\u4e2d\u6253\u5f00\u6bcf\u65e5\u4f18\u9c9c\u7684\u901a\u8baf\u5f55\u6743\u9650");
            AppMethodBeat.o(21222);
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void t_() {
            AppMethodBeat.i(21223, false);
            AddressFlutterFeature.this.b().startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 258);
            AppMethodBeat.o(21223);
        }
    }

    public void a() {
        AppMethodBeat.i(21233, false);
        if (!this.d) {
            ILocationService iLocationService = (ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation();
            this.f = new AnonymousClass2(iLocationService);
            iLocationService.a().b().a(b(), this.f);
            iLocationService.a().a();
            this.e = new AnonymousClass3(iLocationService);
            iLocationService.b().observe(b(), this.e);
        } else {
            cn.missfresh.ui.a.a.a("\u6b63\u5728\u5b9a\u4f4d\u4e2d");
        }
        AppMethodBeat.o(21233);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.base.AddressFlutterFeature$2  reason: invalid class name */
    public class AnonymousClass2 implements Observer<Location> {
        final /* synthetic */ ILocationService a;

        AnonymousClass2(ILocationService iLocationService) {
            this.a = iLocationService;
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(21210, false);
            a((Location) obj);
            AppMethodBeat.o(21210);
        }

        public void a(Location location) {
            AppMethodBeat.i(21209, false);
            this.a.a().b().b(AddressFlutterFeature.this.f);
            Log.d("ZONE", "location   false");
            if (Location.ERROR_OK == location.getCode()) {
                AddressFlutterFeature.a(AddressFlutterFeature.this, location.getLat(), location.getLng(), this.a);
            } else {
                AddressFlutterFeature.b(AddressFlutterFeature.this);
            }
            AppMethodBeat.o(21209);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.base.AddressFlutterFeature$3  reason: invalid class name */
    public class AnonymousClass3 implements Observer<Integer> {
        final /* synthetic */ ILocationService a;

        AnonymousClass3(ILocationService iLocationService) {
            this.a = iLocationService;
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(21215, false);
            a((Integer) obj);
            AppMethodBeat.o(21215);
        }

        public void a(Integer num) {
            AppMethodBeat.i(21213, false);
            this.a.b().removeObserver(AddressFlutterFeature.this.e);
            Log.d("ZONE", "onGranted   false");
            if (num == null || num.intValue() != 1) {
                AddressFlutterFeature.b(AddressFlutterFeature.this);
            } else {
                AddressFlutterFeature.this.a();
            }
            AppMethodBeat.o(21213);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.base.AddressFlutterFeature$4  reason: invalid class name */
    public class AnonymousClass4 implements ILocationService.a {
        AnonymousClass4() {
        }

        @Override // cn.missfresh.module.base.common.providers.ILocationService.a
        public void a(boolean z, String str, List<TencentSearchData> list) {
            AppMethodBeat.i(21219, false);
            AddressFlutterFeature.this.d = false;
            MethodChannel.Result result = (MethodChannel.Result) AddressFlutterFeature.this.c.get("getLocation");
            if (result == null) {
                AppMethodBeat.o(21219);
                return;
            }
            if (cn.missfresh.utils.b.a(list) || !z) {
                AddressFlutterFeature.b(AddressFlutterFeature.this);
            } else {
                JSONObject jSONObject = new JSONObject();
                TencentSearchData tencentSearchData = list.get(0);
                jSONObject.put("success", true);
                JSONObject jSONObject2 = new JSONObject();
                if (tencentSearchData.ad_info != null) {
                    jSONObject2.put("city", tencentSearchData.ad_info.city);
                    jSONObject2.put("address", tencentSearchData.title);
                    jSONObject2.put(Constants.KEY_HTTP_CODE, tencentSearchData.ad_info.adcode);
                }
                if (tencentSearchData.location != null) {
                    jSONObject2.put("lat", Float.valueOf(tencentSearchData.location.lat));
                    jSONObject2.put("lng", Float.valueOf(tencentSearchData.location.lng));
                }
                jSONObject.put("address", jSONObject2);
                Log.d("ZONE", "onTransCompleted   false");
                result.success(jSONObject.toJSONString());
                AddressFlutterFeature.this.b("getLocation");
            }
            AppMethodBeat.o(21219);
        }
    }

    private void a(double d, double d2, ILocationService iLocationService) {
        AppMethodBeat.i(21234, false);
        iLocationService.a(String.valueOf(d), String.valueOf(d2), new AnonymousClass4());
        AppMethodBeat.o(21234);
    }

    private void f() {
        AppMethodBeat.i(21236, false);
        this.d = false;
        cn.missfresh.ui.a.a.a("\u5b9a\u4f4d\u5931\u8d25~");
        MethodChannel.Result result = (MethodChannel.Result) this.c.get("getLocation");
        if (result == null) {
            AppMethodBeat.o(21236);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("success", false);
        result.success(jSONObject.toJSONString());
        b("getLocation");
        Log.d("ZONE", "requestLocationFailed   false");
        AppMethodBeat.o(21236);
    }

    private void a(UserAddress userAddress) {
        AppMethodBeat.i(21237, false);
        cn.missfresh.module.base.manager.c.q().b(userAddress);
        if (userAddress != null) {
            cn.missfresh.module.base.manager.c.a(userAddress.area_code, userAddress.city, userAddress.address_1, userAddress.getLatAndLng().lat, userAddress.getLatAndLng().lng);
            ILocationService iLocationService = (ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation();
            if (!cn.missfresh.utils.b.a(iLocationService.h().getValue())) {
                iLocationService.h().setValue("");
            }
        }
        AppMethodBeat.o(21237);
    }

    private void g() {
        AppMethodBeat.i(21239, false);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        AppMethodBeat.o(21239);
    }

    private void h() {
        AppMethodBeat.i(21240, false);
        EventBus.getDefault().unregister(this);
        AppMethodBeat.o(21240);
    }

    @Subscribe
    public void onHandleEvent(k kVar) {
        AppMethodBeat.i(21242, false);
        if (kVar == null) {
            b("jumpToAddressList");
            h();
            AppMethodBeat.o(21242);
            return;
        }
        UserAddress userAddress = kVar.a;
        if (userAddress != null) {
            if (!kVar.b) {
                a("jumpToAddressList").success(userAddress.toString());
            }
            b("jumpToAddressList");
            h();
        }
        AppMethodBeat.o(21242);
    }
}
