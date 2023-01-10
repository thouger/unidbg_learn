package cn.missfresh.application.flutter;

import androidx.lifecycle.Observer;
import cn.missfresh.module.food.bean.RelationStatus;

/* compiled from: lambda */
/* renamed from: cn.missfresh.application.flutter.-$$Lambda$HomeFlutterFragment$hpAim_wpsraylSZMjgdGOjBuN70  reason: invalid class name */
public final /* synthetic */ class $$Lambda$HomeFlutterFragment$hpAim_wpsraylSZMjgdGOjBuN70 implements Observer {
    private final /* synthetic */ HomeFlutterFragment f$0;

    public /* synthetic */ $$Lambda$HomeFlutterFragment$hpAim_wpsraylSZMjgdGOjBuN70(HomeFlutterFragment homeFlutterFragment) {
        this.f$0 = homeFlutterFragment;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        this.f$0.a((RelationStatus) obj);
    }
}
