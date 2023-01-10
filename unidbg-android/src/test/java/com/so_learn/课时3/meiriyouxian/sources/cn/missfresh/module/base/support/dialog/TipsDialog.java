package cn.missfresh.module.base.support.dialog;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

public class TipsDialog extends BaseTipDialog {
    public static final String a = TipsDialog.class.getName();
    private FragmentManager d;
    private boolean e = false;
    private String f;
    private String g;
    private String h;
    private boolean i;
    private String j;
    private int k = -1;
    private int l;
    private int m;
    private Typeface n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private View.OnClickListener s = new AnonymousClass1();

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public double d() {
        return 0.77d;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public int e() {
        return -1;
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AppMethodBeat.onResume(this);
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public TipsDialog() {
        AppMethodBeat.i(21871, false);
        AppMethodBeat.o(21871);
    }

    static /* synthetic */ void a(TipsDialog tipsDialog, a aVar) {
        AppMethodBeat.i(21881, false);
        tipsDialog.a(aVar);
        AppMethodBeat.o(21881);
    }

    static {
        AppMethodBeat.i(21883, false);
        AppMethodBeat.o(21883);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public int R_() {
        return R.layout.dialog_tips_layout;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public void a(View view) {
        int i = 0;
        AppMethodBeat.i(21873, false);
        this.o = (TextView) view.findViewById(R.id.tip_tv);
        this.p = (TextView) view.findViewById(R.id.left_tv);
        this.q = (TextView) view.findViewById(R.id.right_tv);
        this.r = (TextView) view.findViewById(R.id.tv_title);
        this.p.setOnClickListener(this.s);
        this.q.setOnClickListener(this.s);
        this.p.setVisibility(b.a(this.g) ? 8 : 0);
        this.q.setVisibility(b.a(this.h) ? 8 : 0);
        TextView textView = this.r;
        if (b.a(this.j)) {
            i = 8;
        }
        textView.setVisibility(i);
        setCancelable(this.i);
        m();
        AppMethodBeat.o(21873);
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.TipsDialog$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(21857, false);
            if (view.getId() == R.id.left_tv) {
                if (TipsDialog.this.c != null) {
                    TipsDialog.this.c.a(100, null);
                }
                TipsDialog.this.dismiss();
            } else if (view.getId() == R.id.right_tv) {
                if (TipsDialog.this.c != null) {
                    TipsDialog.this.c.a(101, null);
                }
                TipsDialog.this.dismiss();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(21857);
        }
    }

    private void m() {
        AppMethodBeat.i(21875, false);
        this.o.setText(this.f);
        int i = this.k;
        if (i > 0) {
            this.o.setTextColor(i);
        }
        this.o.getPaint().setTypeface(this.n);
        this.p.setText(this.g);
        this.q.setText(this.h);
        int i2 = this.l;
        if (i2 > 0) {
            this.o.setGravity(i2);
        }
        this.r.setText(this.j);
        int i3 = this.m;
        if (i3 > 0) {
            this.o.setTextSize((float) i3);
        }
        AppMethodBeat.o(21875);
    }

    private void a(a aVar) {
        AppMethodBeat.i(21876, false);
        this.f = aVar.a;
        this.g = aVar.b;
        this.h = aVar.c;
        this.k = aVar.e;
        this.n = aVar.f;
        this.l = aVar.j;
        this.c = aVar.g;
        this.d = aVar.h;
        this.i = aVar.i;
        this.j = aVar.d;
        this.m = aVar.k;
        AppMethodBeat.o(21876);
    }

    public void k() {
        AppMethodBeat.i(21877, false);
        FragmentManager fragmentManager = this.d;
        if (fragmentManager == null) {
            AppMethodBeat.o(21877);
            return;
        }
        if (this.e) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.show(this);
            beginTransaction.commitAllowingStateLoss();
        } else {
            show(fragmentManager, a);
        }
        AppMethodBeat.o(21877);
    }

    public static a l() {
        AppMethodBeat.i(21879, false);
        a aVar = new a();
        AppMethodBeat.o(21879);
        return aVar;
    }

    public static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private int e = -1;
        private Typeface f = Typeface.DEFAULT_BOLD;
        private BaseTipDialog.a g;
        private FragmentManager h;
        private boolean i;
        private int j;
        private int k;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a c(String str) {
            this.c = str;
            return this;
        }

        public a a(int i) {
            this.e = i;
            return this;
        }

        public a a(BaseTipDialog.a aVar) {
            this.g = aVar;
            return this;
        }

        public a a(boolean z) {
            this.i = z;
            return this;
        }

        public a b(int i) {
            this.j = i;
            return this;
        }

        public a d(String str) {
            this.d = str;
            return this;
        }

        public a c(int i) {
            this.k = i;
            return this;
        }

        public TipsDialog a(FragmentActivity fragmentActivity) {
            AppMethodBeat.i(21864, false);
            FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            this.h = supportFragmentManager;
            TipsDialog tipsDialog = (TipsDialog) supportFragmentManager.findFragmentByTag(TipsDialog.a);
            if (tipsDialog == null) {
                tipsDialog = new TipsDialog();
                tipsDialog.e = false;
            } else {
                tipsDialog.e = true;
            }
            TipsDialog.a(tipsDialog, this);
            AppMethodBeat.o(21864);
            return tipsDialog;
        }
    }
}
