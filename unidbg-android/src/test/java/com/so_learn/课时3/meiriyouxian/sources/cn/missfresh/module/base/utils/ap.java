package cn.missfresh.module.base.utils;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.c.g;
import io.reactivex.q;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.TimeUnit;

/* compiled from: RxView */
public class ap {

    /* compiled from: RxView */
    public interface a {
        void a(View view);
    }

    /* compiled from: RxView */
    /* renamed from: cn.missfresh.module.base.utils.ap$1  reason: invalid class name */
    static class AnonymousClass1 implements g<View> {
        final /* synthetic */ a a;

        AnonymousClass1(a aVar) {
            this.a = aVar;
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(23412, false);
            a((View) obj);
            AppMethodBeat.o(23412);
        }

        public void a(View view) throws Exception {
            AppMethodBeat.i(23411, false);
            this.a.a(view);
            AppMethodBeat.o(23411);
        }
    }

    public static void a(a aVar, View... viewArr) {
        AppMethodBeat.i(23415, false);
        for (View view : viewArr) {
            a(view).e(800, TimeUnit.MILLISECONDS).e(new AnonymousClass1(aVar));
        }
        AppMethodBeat.o(23415);
    }

    private static q a(View view) {
        AppMethodBeat.i(23416, false);
        ak.a(view, "view == null");
        q a2 = q.a((s) new b(view));
        AppMethodBeat.o(23416);
        return a2;
    }

    /* compiled from: RxView */
    /* access modifiers changed from: private */
    public static class b implements s {
        private View a;

        public b(View view) {
            this.a = view;
        }

        @Override // io.reactivex.s
        public void a(r rVar) throws Exception {
            AppMethodBeat.i(23414, false);
            ak.a();
            this.a.setOnClickListener(new RxView$ViewClickOnSubscribe$1(this, rVar));
            AppMethodBeat.o(23414);
        }
    }
}
