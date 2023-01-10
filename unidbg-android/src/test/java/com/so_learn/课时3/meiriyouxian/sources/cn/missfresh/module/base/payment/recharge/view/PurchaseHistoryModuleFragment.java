package cn.missfresh.module.base.payment.recharge.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseModuleFragment;
import cn.missfresh.module.base.widget.LoadMoreListView;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.module.base.widget.PriceTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import in.srain.cube.views.ptr.interfaces.PtrHandler;
import in.srain.cube.views.ptr.widget.PtrFrameLayout;
import in.srain.cube.views.ptr.widget.PtrMissFreshFrameLayout;

public class PurchaseHistoryModuleFragment extends BaseModuleFragment {
    private MultiStateLayout c;
    private PtrMissFreshFrameLayout d;
    private LoadMoreListView e;
    private LayoutInflater f;
    private a g;
    private b h;

    /* renamed from: cn.missfresh.module.base.payment.recharge.view.PurchaseHistoryModuleFragment$1  reason: invalid class name */
    class AnonymousClass1 implements PtrHandler {
        final /* synthetic */ PurchaseHistoryModuleFragment a;

        AnonymousClass1(PurchaseHistoryModuleFragment purchaseHistoryModuleFragment) {
            JniLib.cV(this, purchaseHistoryModuleFragment, 515);
        }

        @Override // in.srain.cube.views.ptr.interfaces.PtrHandler
        public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
            return JniLib.cZ(this, ptrFrameLayout, view, view2, 513);
        }

        @Override // in.srain.cube.views.ptr.interfaces.PtrHandler
        public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            JniLib.cV(this, ptrFrameLayout, 514);
        }
    }

    private class a {
        public void a() {
            JniLib.cV(this, 521);
        }
    }

    class c {
        ImageView a;
        PriceTextView b;
        TextView c;
        TextView d;
        TextView e;
        TextView f;
        final /* synthetic */ PurchaseHistoryModuleFragment g;

        c(PurchaseHistoryModuleFragment purchaseHistoryModuleFragment) {
            JniLib.cV(this, purchaseHistoryModuleFragment, 523);
        }
    }

    public static PurchaseHistoryModuleFragment g() {
        return (PurchaseHistoryModuleFragment) JniLib.cL(Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE));
    }

    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onHiddenChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), 524);
    }

    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onResume() {
        JniLib.cV(this, 525);
    }

    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void setUserVisibleHint(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_SUPPORT_DISCLAIMER));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(17633, false);
        this.f = layoutInflater;
        View inflate = layoutInflater.inflate(R.layout.fragment_purchase_history_layout, viewGroup, false);
        AppMethodBeat.o(17633);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(17635, false);
        super.onViewCreated(view, bundle);
        this.c = (MultiStateLayout) view.findViewById(R.id.multi_state_layout);
        this.d = (PtrMissFreshFrameLayout) view.findViewById(R.id.ptr_order);
        this.e = (LoadMoreListView) view.findViewById(R.id.lv_my_order);
        this.d.setPtrHandler(new AnonymousClass1(this));
        this.h = new b(this, null);
        this.e.setAdapter((ListAdapter) this.h);
        this.c.c();
        AppMethodBeat.o(17635);
    }

    /* access modifiers changed from: private */
    public class b extends BaseAdapter {
        @Override // android.widget.Adapter
        public int getCount() {
            return JniLib.cI(this, 522);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0;
        }

        private b() {
        }

        /* synthetic */ b(PurchaseHistoryModuleFragment purchaseHistoryModuleFragment, AnonymousClass1 r2) {
            this();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            AppMethodBeat.i(17617, false);
            if (view == null) {
                view = PurchaseHistoryModuleFragment.this.f.inflate(R.layout.item_purchase_card_history_layout, viewGroup, false);
                cVar = new c(PurchaseHistoryModuleFragment.this);
                cVar.a = (ImageView) view.findViewById(R.id.cardImage);
                cVar.b = (PriceTextView) view.findViewById(R.id.cardPrice);
                cVar.c = (TextView) view.findViewById(R.id.purchaseTime);
                cVar.d = (TextView) view.findViewById(R.id.comeFrom);
                cVar.e = (TextView) view.findViewById(R.id.fromWhos);
                cVar.f = (TextView) view.findViewById(R.id.giftFriends);
                view.setTag(cVar);
                cVar.e.setOnClickListener(new PurchaseHistoryModuleFragment$PurchaseHistoryAdapter$1(this));
                cVar.f.setOnClickListener(new PurchaseHistoryModuleFragment$PurchaseHistoryAdapter$2(this));
                view.setOnClickListener(new PurchaseHistoryModuleFragment$PurchaseHistoryAdapter$3(this));
            } else {
                cVar = (c) view.getTag();
            }
            cVar.b.setPriceWithRMB(10000);
            AppMethodBeat.o(17617);
            return view;
        }
    }
}
