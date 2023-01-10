package cn.missfresh.module.base.payment.recharge.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.widget.LoadMoreListView;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.module.base.widget.PriceTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import in.srain.cube.views.ptr.interfaces.PtrHandler;
import in.srain.cube.views.ptr.widget.PtrFrameLayout;
import in.srain.cube.views.ptr.widget.PtrMissFreshFrameLayout;

public class ValueCardRecordDetailActivity extends BaseFragmentActivity {
    private MultiStateLayout a;
    private PtrMissFreshFrameLayout j;
    private LoadMoreListView k;
    private LayoutInflater l;
    private a m;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.ValueCardRecordDetailActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements PtrHandler {
        final /* synthetic */ ValueCardRecordDetailActivity a;

        AnonymousClass1(ValueCardRecordDetailActivity valueCardRecordDetailActivity) {
            JniLib.cV(this, valueCardRecordDetailActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_DATE_PICKER));
        }

        @Override // in.srain.cube.views.ptr.interfaces.PtrHandler
        public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
            return JniLib.cZ(this, ptrFrameLayout, view, view2, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_WRITE_NFC));
        }

        @Override // in.srain.cube.views.ptr.interfaces.PtrHandler
        public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        }
    }

    public static void a(Context context) {
        JniLib.cV(context, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_ACTION));
    }

    private void r() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_EXTRA));
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_NETWORK_TYPE));
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(17981, false);
        super.onCreate(bundle);
        setContentView(R.layout.fragment_purchase_history_layout);
        a();
        r();
        AppMethodBeat.o(17981);
    }

    private void a() {
        AppMethodBeat.i(17985, false);
        e_("\u5361\u7247\u8bb0\u5f55");
        this.e.setCenterVisibility(0);
        this.e.setLeftButtonVisibility(0);
        this.l = LayoutInflater.from(this.f);
        this.a = (MultiStateLayout) findViewById(R.id.multi_state_layout);
        this.j = (PtrMissFreshFrameLayout) findViewById(R.id.ptr_order);
        this.k = (LoadMoreListView) findViewById(R.id.lv_my_order);
        this.k.addHeaderView(this.l.inflate(R.layout.item_purchase_card_detail_header_layout, (ViewGroup) null, false));
        this.j.setPtrHandler(new AnonymousClass1(this));
        this.m = new a(this, null);
        this.k.setAdapter((ListAdapter) this.m);
        this.a.c();
        AppMethodBeat.o(17985);
    }

    /* access modifiers changed from: private */
    public class a extends BaseAdapter {
        @Override // android.widget.Adapter
        public int getCount() {
            return JniLib.cI(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_MANAGE_MOBILE_PLAN));
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0;
        }

        private a() {
        }

        /* synthetic */ a(ValueCardRecordDetailActivity valueCardRecordDetailActivity, AnonymousClass1 r2) {
            this();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            C0029a aVar;
            AppMethodBeat.i(17962, false);
            if (view == null) {
                view = ValueCardRecordDetailActivity.this.l.inflate(R.layout.item_purchase_card_detail_item_layout, viewGroup, false);
                aVar = new C0029a(this, null);
                aVar.a = (TextView) view.findViewById(R.id.name);
                aVar.b = (PriceTextView) view.findViewById(R.id.price);
            } else {
                aVar = (C0029a) view.getTag();
            }
            aVar.a.setText("\u5f20\u4e09");
            AppMethodBeat.o(17962);
            return view;
        }

        /* renamed from: cn.missfresh.module.base.payment.recharge.view.ValueCardRecordDetailActivity$a$a  reason: collision with other inner class name */
        private class C0029a {
            TextView a;
            PriceTextView b;
            final /* synthetic */ a c;

            private C0029a(a aVar) {
                JniLib.cV(this, aVar, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_TIME_PICKER));
            }

            /* synthetic */ C0029a(a aVar, AnonymousClass1 r2) {
                this(aVar);
            }
        }
    }
}
