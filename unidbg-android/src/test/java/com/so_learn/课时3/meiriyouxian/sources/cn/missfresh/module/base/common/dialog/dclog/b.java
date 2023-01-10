package cn.missfresh.module.base.common.dialog.dclog;

import android.content.Context;
import cn.missfresh.module.base.common.dialog.dclog.LogDialog;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

/* compiled from: LogShowFactory */
public class b implements LogDialog.a {
    private String a = b.class.getSimpleName();
    private LogDialog b;

    public b(LogDialog logDialog) {
        AppMethodBeat.i(11590, false);
        this.b = logDialog;
        AppMethodBeat.o(11590);
    }

    public void a(Context context) {
        AppMethodBeat.i(11592, false);
        b(context);
        a();
        AppMethodBeat.o(11592);
    }

    public void b(Context context) {
        AppMethodBeat.i(11595, false);
        if (e.m() && this.b == null) {
            this.b = new LogDialog(context, this);
        }
        AppMethodBeat.o(11595);
    }

    public void a() {
        AppMethodBeat.i(11598, false);
        LogDialog logDialog = this.b;
        if (logDialog != null && !logDialog.isShowing() && e.m()) {
            this.b.show();
            a(a.c);
        }
        AppMethodBeat.o(11598);
    }

    public void b() {
        AppMethodBeat.i(11601, false);
        LogDialog logDialog = this.b;
        if (logDialog != null) {
            logDialog.dismiss();
            this.b = null;
        }
        AppMethodBeat.o(11601);
    }

    public void c() {
        AppMethodBeat.i(11603, false);
        LogDialog logDialog = this.b;
        if (logDialog != null) {
            logDialog.dismiss();
            this.b = null;
        }
        AppMethodBeat.o(11603);
    }

    public void d() {
        AppMethodBeat.i(11606, false);
        c();
        AppMethodBeat.o(11606);
    }

    public void a(List<String> list) {
        AppMethodBeat.i(11608, false);
        if (this.b != null) {
            int size = list.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(list.get(i) + "\n");
            }
            this.b.a(sb.toString());
        }
        AppMethodBeat.o(11608);
    }

    /* compiled from: LogShowFactory */
    /* renamed from: cn.missfresh.module.base.common.dialog.dclog.b$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[LogDialog.OnClickType.values().length];

        static {
            AppMethodBeat.i(11587, false);
            try {
                a[LogDialog.OnClickType.get.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[LogDialog.OnClickType.log.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[LogDialog.OnClickType.post.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            AppMethodBeat.o(11587);
        }
    }

    @Override // cn.missfresh.module.base.common.dialog.dclog.LogDialog.a
    public void a(LogDialog.OnClickType onClickType) {
        AppMethodBeat.i(11611, false);
        int i = AnonymousClass1.a[onClickType.ordinal()];
        if (i == 1) {
            a(a.a);
        } else if (i == 2) {
            a(a.c);
        } else if (i == 3) {
            a(a.b);
        }
        AppMethodBeat.o(11611);
    }
}
