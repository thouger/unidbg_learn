package cn.missfresh.module.base.manager;

import android.app.AlarmManager;
import android.view.View;
import cn.missfresh.module.base.bean.BaseSubscibe;
import cn.missfresh.module.base.manager.bean.SubscribeSKUBean;
import cn.missfresh.module.base.network.k;
import cn.missfresh.module.base.support.modelsupport.event.bean.EventProductSubscribe;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import com.huawei.hms.framework.common.ContainerUtils;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.taobao.accs.common.Constants;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: SubscibeProductManager */
@Deprecated
public class n {
    private List<SubscribeSKUBean> a;
    private String b;
    private final int c;
    private HashMap<String, String> d;

    /* compiled from: SubscibeProductManager */
    public interface b {
        void a(View view, BaseSubscibe baseSubscibe);
    }

    /* synthetic */ n(AnonymousClass1 r1) {
        this();
    }

    private n() {
        AppMethodBeat.i(14549, false);
        this.b = getClass().getSimpleName();
        this.c = 43200000;
        this.d = new HashMap<>();
        f.a(SubscribeSKUBean.class, WhereBuilder.b("saveTime", "<", Long.valueOf(System.currentTimeMillis() - AlarmManager.INTERVAL_HALF_DAY)));
        this.a = f.a(SubscribeSKUBean.class);
        if (this.a == null) {
            this.a = new ArrayList();
        }
        AppMethodBeat.o(14549);
    }

    public static n a() {
        AppMethodBeat.i(14550, false);
        n nVar = a.a;
        AppMethodBeat.o(14550);
        return nVar;
    }

    /* compiled from: SubscibeProductManager */
    private static class a {
        private static n a = new n(null);

        static {
            AppMethodBeat.i(14548, false);
            AppMethodBeat.o(14548);
        }
    }

    /* compiled from: SubscibeProductManager */
    /* renamed from: cn.missfresh.module.base.manager.n$1  reason: invalid class name */
    class AnonymousClass1 extends k {
        final /* synthetic */ View a;
        final /* synthetic */ BaseSubscibe b;
        final /* synthetic */ b c;
        final /* synthetic */ n d;

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.k
        public void a(JSONObject jSONObject) {
            AppMethodBeat.i(14547, false);
            if (jSONObject.getIntValue(Constants.KEY_HTTP_CODE) == 0 || jSONObject.getIntValue(Constants.KEY_HTTP_CODE) == 208) {
                this.a.setTag(jSONObject.getString("content"));
                this.b.setSubscibe(true);
                this.d.a(this.b.getSku());
                b bVar = this.c;
                if (bVar != null) {
                    bVar.a(this.a, this.b);
                }
                EventBus.getDefault().post(new EventProductSubscribe(this.b.getSku()));
            } else {
                cn.missfresh.ui.a.a.a(jSONObject.getString("msg"));
            }
            AppMethodBeat.o(14547);
        }
    }

    public boolean a(String str) {
        AppMethodBeat.i(14552, false);
        if (cn.missfresh.utils.b.a(str)) {
            AppMethodBeat.o(14552);
            return true;
        } else if (!c(str)) {
            SubscribeSKUBean subscribeSKUBean = new SubscribeSKUBean(str);
            this.a.add(subscribeSKUBean);
            subscribeSKUBean.setSaveTime(System.currentTimeMillis());
            boolean a2 = f.a(subscribeSKUBean);
            AppMethodBeat.o(14552);
            return a2;
        } else {
            AppMethodBeat.o(14552);
            return true;
        }
    }

    public boolean a(String str, boolean z) {
        AppMethodBeat.i(14553, false);
        boolean z2 = true;
        if (z) {
            z2 = true & b(str);
        }
        boolean a2 = f.a(SubscribeSKUBean.class, WhereBuilder.b("sku", ContainerUtils.KEY_VALUE_DELIMITER, str)) & z2;
        AppMethodBeat.o(14553);
        return a2;
    }

    public boolean b(String str) {
        AppMethodBeat.i(14554, false);
        SubscribeSKUBean subscribeSKUBean = new SubscribeSKUBean(str);
        if (this.a.contains(subscribeSKUBean)) {
            boolean remove = this.a.remove(subscribeSKUBean);
            AppMethodBeat.o(14554);
            return remove;
        }
        AppMethodBeat.o(14554);
        return false;
    }

    public boolean c(String str) {
        AppMethodBeat.i(14556, false);
        if (cn.missfresh.utils.b.a(str) || cn.missfresh.utils.b.a(this.a)) {
            AppMethodBeat.o(14556);
            return false;
        }
        boolean contains = this.a.contains(new SubscribeSKUBean(str));
        AppMethodBeat.o(14556);
        return contains;
    }
}
