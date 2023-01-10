package cn.missfresh.module.user.mine.view;

import android.app.Activity;
import android.provider.Telephony;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.bean.BaseMsgBean;
import cn.missfresh.module.base.bean.MsgActionListBean$MsgActionBean;
import cn.missfresh.module.base.common.interfaces.s;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.module.mvp.mvp.interfaces.IPresenter;
import cn.missfresh.module.mvp.mvp.module.BaseMVPActivity;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.mine.adapter.MsgListAdapter;
import cn.missfresh.module.user.mine.presenter.MsgListPresenter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.refreshlayout.MFRefreshLayout;
import com.alibaba.android.arouter.b.a;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.scwang.smartrefresh.layout.a.b;
import com.scwang.smartrefresh.layout.a.d;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import java.util.List;

public class MsgListActivity extends BaseMVPActivity<MsgListPresenter> implements MultiStateLayout.d, b {
    public int a;
    private MsgListAdapter b;
    private MultiStateLayout c;
    private MFRefreshLayout d;
    private int e;
    private d f = new AnonymousClass1(this);
    private b g = new AnonymousClass2(this);
    private s h = new AnonymousClass3();

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.mine.view.MsgListActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void onWindowFocusChanged(boolean z) {
        MsgListActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ IPresenter f() {
        AppMethodBeat.i(9580, false);
        MsgListPresenter l = l();
        AppMethodBeat.o(9580);
        return l;
    }

    public MsgListActivity() {
        AppMethodBeat.i(9544, false);
        AppMethodBeat.o(9544);
    }

    /* access modifiers changed from: protected */
    public void a() {
        AppMethodBeat.i(9548, false);
        StatisticsManager.n("page_view", new Object[0]);
        a.a().a(this);
        this.z.setCenterTxt(a(this.a));
        this.c.c();
        this.d.autoRefresh();
        AppMethodBeat.o(9548);
    }

    private String a(int i) {
        AppMethodBeat.i(9551, false);
        if (i == 1) {
            String string = getString(R.string.message_center_action);
            AppMethodBeat.o(9551);
            return string;
        } else if (i == 2) {
            String string2 = getString(R.string.message_center_order);
            AppMethodBeat.o(9551);
            return string2;
        } else if (i == 3) {
            String string3 = getString(R.string.message_center_system);
            AppMethodBeat.o(9551);
            return string3;
        } else if (i == 4) {
            String string4 = getString(R.string.message_center_gift);
            AppMethodBeat.o(9551);
            return string4;
        } else if (i != 5) {
            AppMethodBeat.o(9551);
            return "";
        } else {
            String string5 = getString(R.string.message_center_vip);
            AppMethodBeat.o(9551);
            return string5;
        }
    }

    /* access modifiers changed from: protected */
    public MsgListPresenter l() {
        AppMethodBeat.i(9553, false);
        MsgListPresenter msgListPresenter = new MsgListPresenter(this);
        AppMethodBeat.o(9553);
        return msgListPresenter;
    }

    /* access modifiers changed from: protected */
    public void g() {
        AppMethodBeat.i(9557, false);
        this.c.setOnRefreshListener(this);
        this.d.setOnRefreshListener(this.f);
        this.d.setOnLoadMoreListener(this.g);
        AppMethodBeat.o(9557);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: cn.missfresh.module.user.mine.view.MsgListActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void k() {
        AppMethodBeat.i(9560, false);
        as.a((Activity) this, getResources().getColor(R.color.white_f5));
        n();
        this.c = (MultiStateLayout) findViewById(R.id.multi_state_layout_msg);
        this.d = (MFRefreshLayout) findViewById(R.id.mf_rl_layout_msg);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.msg_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.b = new MsgListAdapter();
        this.b.a(this.h);
        recyclerView.setAdapter(this.b);
        AppMethodBeat.o(9560);
    }

    private void n() {
        AppMethodBeat.i(9562, false);
        this.z.setCenterVisibility(0);
        this.z.setLeftButtonVisibility(0);
        this.z.getBottomDivider().setVisibility(8);
        AppMethodBeat.o(9562);
    }

    /* access modifiers changed from: protected */
    public int M_() {
        return R.layout.activity_message_list;
    }

    @Override // cn.missfresh.module.base.widget.MultiStateLayout.d
    public void f_() {
        AppMethodBeat.i(9568, false);
        a();
        AppMethodBeat.o(9568);
    }

    /* renamed from: cn.missfresh.module.user.mine.view.MsgListActivity$1  reason: invalid class name */
    class AnonymousClass1 implements d {
        final /* synthetic */ MsgListActivity a;

        AnonymousClass1(MsgListActivity msgListActivity) {
            JniLib.cV(this, msgListActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ENTERPRISE_PRIVACY_PERMISSIONS));
        }

        public void b(RefreshLayout refreshLayout) {
            AppMethodBeat.i(9523, false);
            this.a.e = 1;
            this.a.v.a(this.a.a, 1);
            AppMethodBeat.o(9523);
        }
    }

    /* renamed from: cn.missfresh.module.user.mine.view.MsgListActivity$2  reason: invalid class name */
    class AnonymousClass2 implements b {
        final /* synthetic */ MsgListActivity a;

        AnonymousClass2(MsgListActivity msgListActivity) {
            JniLib.cV(this, msgListActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ENTERPRISE_PRIVACY_DEFAULT_APPS));
        }

        public void a(RefreshLayout refreshLayout) {
            AppMethodBeat.i(9531, false);
            this.a.v.a(this.a.a, this.a.e);
            AppMethodBeat.o(9531);
        }
    }

    @Override // cn.missfresh.module.user.mine.view.b
    public void a(List<BaseMsgBean> list, boolean z) {
        AppMethodBeat.i(9570, false);
        if (list != null && list.size() != 0) {
            this.c.c();
            this.b.a(list, z);
            this.d.setNoMoreData(!z);
        } else if (this.b.getItemCount() == 0) {
            this.c.a();
            this.d.setEnableRefresh(false);
        }
        a(true, z);
        AppMethodBeat.o(9570);
    }

    @Override // cn.missfresh.module.user.mine.view.b
    public void a(int i, String str) {
        AppMethodBeat.i(9572, false);
        a(false, false);
        if (this.b.getItemCount() == 0) {
            this.c.b();
        }
        AppMethodBeat.o(9572);
    }

    private void a(boolean z, boolean z2) {
        AppMethodBeat.i(9577, false);
        if (this.d.getState() == RefreshState.Refreshing) {
            this.d.finishRefresh(z);
        } else if (this.d.getState() == RefreshState.Loading) {
            if (z2) {
                this.d.finishLoadMore(z);
            } else {
                this.d.finishLoadMoreWithNoMoreData();
            }
        }
        AppMethodBeat.o(9577);
    }

    /* renamed from: cn.missfresh.module.user.mine.view.MsgListActivity$3  reason: invalid class name */
    class AnonymousClass3 extends s {
        AnonymousClass3() {
        }

        @Override // cn.missfresh.module.base.common.interfaces.s
        public void b(int i, int i2, Object obj) {
            AppMethodBeat.i(9540, false);
            if (i2 == 1) {
                MsgActionListBean$MsgActionBean msgActionListBean$MsgActionBean = (MsgActionListBean$MsgActionBean) MsgListActivity.this.b.a().get(i);
                a.a().a("/promotion/new_h5event").withString("h5_title", msgActionListBean$MsgActionBean.title).withString("h5_url", msgActionListBean$MsgActionBean.link).navigation();
                StatisticsManager.n("click_message", "pos", Integer.valueOf(i), Telephony.CellBroadcasts.V1_MESSAGE_IDENTIFIER, Long.valueOf(((MsgActionListBean$MsgActionBean) MsgListActivity.this.b.a().get(i)).id));
            }
            AppMethodBeat.o(9540);
        }
    }
}
