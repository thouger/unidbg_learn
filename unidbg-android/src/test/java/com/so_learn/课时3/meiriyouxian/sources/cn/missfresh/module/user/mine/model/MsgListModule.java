package cn.missfresh.module.user.mine.model;

import cn.missfresh.module.base.bean.BaseMsgBean;
import cn.missfresh.module.base.bean.MsgActionListBean;
import cn.missfresh.module.base.network.api.MFApiManager;
import cn.missfresh.module.base.network.j;
import cn.missfresh.module.mvp.mvp.impl.MVPModel;
import cn.missfresh.module.mvp.mvp.interfaces.IModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MsgListModule extends MVPModel {
    private List<BaseMsgBean> a = new ArrayList();
    private boolean b;

    public MsgListModule() {
        AppMethodBeat.i(8848, false);
        AppMethodBeat.o(8848);
    }

    public void a(int i, int i2, IModel.a aVar) {
        AppMethodBeat.i(8851, false);
        HashMap hashMap = new HashMap();
        hashMap.put("param", new HashMap());
        a(MFApiManager.getMFApi().getActionMsgListForNet(hashMap), new AnonymousClass1(aVar), aVar.c());
        AppMethodBeat.o(8851);
    }

    /* renamed from: cn.missfresh.module.user.mine.model.MsgListModule$1  reason: invalid class name */
    class AnonymousClass1 extends j<MsgActionListBean> {
        final /* synthetic */ IModel.a a;

        AnonymousClass1(IModel.a aVar) {
            this.a = aVar;
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(8842, false);
            a((MsgActionListBean) obj);
            AppMethodBeat.o(8842);
        }

        public void a(MsgActionListBean msgActionListBean) {
            AppMethodBeat.i(8839, false);
            MsgListModule.this.a.clear();
            MsgListModule.this.a.addAll(msgActionListBean.getMessageList());
            MsgListModule.this.b = msgActionListBean.haveNextPage;
            this.a.a();
            AppMethodBeat.o(8839);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(8840, false);
            this.a.a(i, str);
            AppMethodBeat.o(8840);
        }
    }

    public List<BaseMsgBean> a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }
}
