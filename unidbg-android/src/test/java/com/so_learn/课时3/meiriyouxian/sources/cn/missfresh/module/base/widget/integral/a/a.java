package cn.missfresh.module.base.widget.integral.a;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.widget.integral.bean.DailySignIn;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.common.Constants;
import java.util.Map;
import okhttp3.Request;

/* compiled from: DailySignInPresenter */
public class a {
    private String a = getClass().getSimpleName();
    private AbstractC0034a b;

    /* compiled from: DailySignInPresenter */
    /* renamed from: cn.missfresh.module.base.widget.integral.a.a$a  reason: collision with other inner class name */
    public interface AbstractC0034a {
        void a(DailySignIn dailySignIn);

        void a(String str);

        void c();
    }

    public a(AbstractC0034a aVar) {
        AppMethodBeat.i(24153, false);
        this.b = aVar;
        AppMethodBeat.o(24153);
    }

    public void a() {
        AppMethodBeat.i(24154, false);
        this.b.c();
        c.a(this.a, i.bu, (Map<String, String>) null, new AnonymousClass1(j.c()));
        AppMethodBeat.o(24154);
    }

    /* compiled from: DailySignInPresenter */
    /* renamed from: cn.missfresh.module.base.widget.integral.a.a$1  reason: invalid class name */
    class AnonymousClass1 extends m {
        final /* synthetic */ String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a() {
            AppMethodBeat.i(24148, false);
            super.a();
            a.this.b.a("\u60a8\u9700\u8981\u5148\u767b\u5f55");
            AppMethodBeat.o(24148);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(24149, false);
            super.a(i);
            a.this.b.a(this.a);
            AppMethodBeat.o(24149);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(24150, false);
            super.a(request, exc);
            a.this.b.a(this.a);
            AppMethodBeat.o(24150);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void b() {
            AppMethodBeat.i(24151, false);
            super.b();
            a.this.b.a("\u8bf7\u7ed1\u5b9a\u624b\u673a\u53f7");
            AppMethodBeat.o(24151);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(24152, false);
            super.a(str);
            try {
                JSONObject parseObject = JSONObject.parseObject(str);
                if (parseObject.getIntValue(Constants.KEY_HTTP_CODE) == 0) {
                    DailySignIn dailySignIn = (DailySignIn) JSONObject.parseObject(parseObject.getString("data"), DailySignIn.class);
                    if (dailySignIn != null) {
                        a.this.b.a(dailySignIn);
                    } else {
                        a.this.b.a("\u83b7\u53d6\u7b7e\u5230\u4fe1\u606f\u9519\u8bef");
                    }
                } else {
                    a.this.b.a(parseObject.getString("msg"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                a.this.b.a(this.a);
            }
            AppMethodBeat.o(24152);
        }
    }
}
