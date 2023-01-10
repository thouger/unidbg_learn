package cn.missfresh.flutter.feature.base;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import cn.missfresh.flutter.FlutterCallAndroid;
import cn.missfresh.module.base.base.c;
import cn.missfresh.module.base.bean.LoginEvent;
import cn.missfresh.module.base.helper.b;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.order.shoppingcartnew.providers.bean.EventShoppingCartMergeBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.android.TransparencyMode;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseFlutterFragment extends FlutterFragment implements c, b.a {
    private Map<String, Object> a;
    private FlutterCallAndroid b;
    private FlutterEngine c;
    private Observer<LoginEvent> d = new AnonymousClass1();

    public void E_() {
    }

    public boolean F_() {
        return false;
    }

    public void N_() {
    }

    public abstract String a();

    public void b(int i) {
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public void w_() {
    }

    public void z_() {
    }

    @Override // io.flutter.embedding.android.FlutterFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        o.a(this.d);
        EventBus.getDefault().register(this);
    }

    @Override // io.flutter.embedding.android.FlutterFragment, io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host, io.flutter.embedding.android.FlutterEngineProvider
    public FlutterEngine provideFlutterEngine(Context context) {
        if (this.c == null) {
            this.c = super.provideFlutterEngine(context);
        }
        return this.c;
    }

    @Override // io.flutter.embedding.android.FlutterFragment, io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host, io.flutter.embedding.android.FlutterEngineConfigurator
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        d.c("FlutterMineFragment=>", "configureFlutterEngine is called:" + flutterEngine);
        Bundle arguments = getArguments();
        if (arguments != null && !arguments.isEmpty()) {
            this.a = new HashMap();
            for (String str : arguments.keySet()) {
                this.a.put(str, arguments.get(str));
            }
        }
        flutterEngine.getNavigationChannel().setInitialRoute(a());
        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        FragmentActivity activity = getActivity();
        Map map = this.a;
        if (map == null) {
            map = new HashMap();
        }
        this.b = new FlutterCallAndroid(activity, map);
        this.b.a(getActivity(), flutterEngine);
        b(flutterEngine);
    }

    /* access modifiers changed from: protected */
    public FlutterCallAndroid e() {
        return this.b;
    }

    @Override // io.flutter.embedding.android.FlutterFragment, io.flutter.embedding.android.FlutterActivityAndFragmentDelegate.Host
    public TransparencyMode getTransparencyMode() {
        return TransparencyMode.opaque;
    }

    @Override // io.flutter.embedding.android.FlutterFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // io.flutter.embedding.android.FlutterFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // io.flutter.embedding.android.FlutterFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    private static void b(FlutterEngine flutterEngine) {
        try {
            Class.forName("io.flutter.plugins.GeneratedPluginRegistrant").getDeclaredMethod("registerWith", FlutterEngine.class).invoke(null, flutterEngine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public boolean f() {
        return isAdded() & isVisible() & getUserVisibleHint() & (!isHidden()) & g();
    }

    /* access modifiers changed from: protected */
    public boolean g() {
        return getActivity() != null && !getActivity().isFinishing();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onHandleEvent(EventShoppingCartMergeBean eventShoppingCartMergeBean) {
        a("shopCartMergeSuccess", null);
    }

    private void a(String str, Object obj) {
        if (l() != null) {
            l().invokeMethod(str, obj);
        }
    }

    private MethodChannel l() {
        FlutterCallAndroid flutterCallAndroid = this.b;
        if (flutterCallAndroid == null) {
            return null;
        }
        return flutterCallAndroid.a();
    }

    /* renamed from: cn.missfresh.flutter.feature.base.BaseFlutterFragment$1  reason: invalid class name */
    class AnonymousClass1 implements Observer<LoginEvent> {
        AnonymousClass1() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(21255, false);
            a((LoginEvent) obj);
            AppMethodBeat.o(21255);
        }

        public void a(LoginEvent loginEvent) {
            AppMethodBeat.i(21254, false);
            if (loginEvent == null) {
                AppMethodBeat.o(21254);
                return;
            }
            if (200 == loginEvent.getType()) {
                BaseFlutterFragment.this.z_();
            } else if (300 == loginEvent.getType()) {
                BaseFlutterFragment.this.w_();
                o.c(500);
            } else if (400 == loginEvent.getType()) {
                BaseFlutterFragment.this.E_();
            } else if (500 == loginEvent.getType()) {
                BaseFlutterFragment.this.w_();
            }
            AppMethodBeat.o(21254);
        }
    }

    @Override // cn.missfresh.module.base.helper.b.a
    public boolean Q_() {
        return b.a(this);
    }

    public void a(int i) {
        this.b.a().invokeMethod("onTabSelect", Integer.valueOf(i));
    }

    public void a(FlutterEngine flutterEngine) {
        this.c = flutterEngine;
    }

    public FlutterCallAndroid k() {
        return this.b;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
