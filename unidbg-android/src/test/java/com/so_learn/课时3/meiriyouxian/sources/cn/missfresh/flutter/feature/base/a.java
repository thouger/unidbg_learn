package cn.missfresh.flutter.feature.base;

import androidx.fragment.app.FragmentActivity;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BaseFlutterFeature */
public abstract class a implements b {
    private final FragmentActivity a;
    private final MethodChannel b;
    public Map<String, MethodChannel.Result> c = new HashMap();

    protected a(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        this.a = fragmentActivity;
        this.b = methodChannel;
    }

    public MethodChannel.Result a(String str) {
        return this.c.get(str);
    }

    public void a(String str, MethodChannel.Result result) {
        this.c.put(str, result);
    }

    public void b(String str) {
        this.c.remove(str);
    }

    /* access modifiers changed from: protected */
    public FragmentActivity b() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public MethodChannel c() {
        return this.b;
    }
}
