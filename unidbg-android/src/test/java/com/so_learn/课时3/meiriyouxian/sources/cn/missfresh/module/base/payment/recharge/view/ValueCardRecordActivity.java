package cn.missfresh.module.base.payment.recharge.view;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.google.android.material.tabs.TabLayout;

public class ValueCardRecordActivity extends BaseFragmentActivity {

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.ValueCardRecordActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements TabLayout.OnTabSelectedListener {
        final /* synthetic */ ValueCardRecordActivity a;

        AnonymousClass1(ValueCardRecordActivity valueCardRecordActivity) {
            JniLib.cV(this, valueCardRecordActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_CHOOSE_TYPE));
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }
    }

    public static void a(Context context) {
        JniLib.cV(context, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_AP_EDIT));
    }

    public static void a(BaseFragmentActivity baseFragmentActivity) {
        JniLib.cV(baseFragmentActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_PBC));
    }

    private void r() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_PIN));
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_SAVED_AP_EDIT));
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(17928, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_value_card_record_activity);
        a();
        r();
        AppMethodBeat.o(17928);
    }

    private void a() {
        AppMethodBeat.i(17929, false);
        e_("\u5361\u7247\u8bb0\u5f55");
        this.e.setCenterVisibility(0);
        this.e.setLeftButtonVisibility(0);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setOnTabSelectedListener(new AnonymousClass1(this));
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new AnonymousClass2(getSupportFragmentManager()));
        AppMethodBeat.o(17929);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.ValueCardRecordActivity$2  reason: invalid class name */
    public class AnonymousClass2 extends FragmentPagerAdapter {
        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return JniLib.cI(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_NEED_LOCKSCREEN));
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            return (Fragment) JniLib.cL(this, Integer.valueOf(i), 600);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return (CharSequence) JniLib.cL(this, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_EDIT_PROFILE));
        }

        AnonymousClass2(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
    }
}
