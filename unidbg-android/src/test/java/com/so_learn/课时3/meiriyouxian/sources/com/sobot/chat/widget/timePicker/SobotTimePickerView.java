package com.sobot.chat.widget.timePicker;

import android.content.Context;
import android.media.TtmlUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.timePicker.lib.SobotWheelView;
import com.sobot.chat.widget.timePicker.view.SobotBasePickerView;
import com.unionpay.tsmservice.data.Constant;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class SobotTimePickerView extends SobotBasePickerView implements View.OnClickListener {
    private int A;
    private int B;
    private Calendar C;
    private Calendar D;
    private Calendar E;
    private int F;
    private int G;
    private boolean H;
    private boolean I;
    private boolean J;
    private int K;
    private int L;
    private int M;
    private int N;
    private float O = 1.6f;
    private boolean P;
    private String Q;
    private String R;
    private String S;
    private String T;
    private String U;
    private String V;
    private SobotWheelView.DividerType W;
    com.sobot.chat.widget.timePicker.view.a a;
    private String j;
    private com.sobot.chat.widget.timePicker.b.a k;
    private Button l;
    private ImageView m;
    private TextView n;
    private b o;
    private int p = 17;
    private boolean[] q;
    private String r;
    private String s;
    private String t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public interface b {
        void a(Date date, View view);
    }

    public SobotTimePickerView(a aVar) {
        super(aVar.d);
        this.o = aVar.e;
        this.p = aVar.g;
        this.q = aVar.f;
        this.r = aVar.h;
        this.s = aVar.i;
        this.t = aVar.j;
        this.u = aVar.k;
        this.v = aVar.l;
        this.w = aVar.m;
        this.x = aVar.n;
        this.y = aVar.o;
        this.z = aVar.p;
        this.A = aVar.q;
        this.B = aVar.r;
        this.F = aVar.v;
        this.G = aVar.w;
        this.D = aVar.t;
        this.E = aVar.u;
        this.C = aVar.s;
        this.H = aVar.x;
        this.J = aVar.z;
        this.I = aVar.y;
        this.Q = aVar.H;
        this.R = aVar.I;
        this.S = aVar.J;
        this.T = aVar.K;
        this.U = aVar.L;
        this.V = aVar.M;
        this.L = aVar.B;
        this.K = aVar.A;
        this.M = aVar.C;
        this.k = aVar.c;
        this.j = aVar.b;
        this.O = aVar.F;
        this.P = aVar.G;
        this.W = aVar.E;
        this.N = aVar.D;
        this.c = aVar.a;
        a(aVar.d);
    }

    public static class a {
        private int A;
        private int B;
        private int C;
        private int D;
        private SobotWheelView.DividerType E;
        private float F = 1.6f;
        private boolean G;
        private String H;
        private String I;
        private String J;
        private String K;
        private String L;
        private String M;
        public ViewGroup a;
        private String b = "sobot_pickerview_time";
        private com.sobot.chat.widget.timePicker.b.a c;
        private Context d;
        private b e;
        private boolean[] f = {true, true, true, true, true, true};
        private int g = 17;
        private String h;
        private String i;
        private String j;
        private int k;
        private int l;
        private int m;
        private int n;
        private int o;
        private int p = 17;
        private int q = 18;
        private int r = 18;
        private Calendar s;
        private Calendar t;
        private Calendar u;
        private int v;
        private int w;
        private boolean x = false;
        private boolean y = true;
        private boolean z = true;

        public a(Context context, b bVar) {
            this.d = context;
            this.e = bVar;
        }

        public a a(boolean[] zArr) {
            this.f = zArr;
            return this;
        }

        public a a(String str) {
            this.j = str;
            return this;
        }

        public a a(int i) {
            this.k = i;
            return this;
        }

        public a b(int i) {
            this.l = i;
            return this;
        }

        public a a(ViewGroup viewGroup) {
            this.a = viewGroup;
            return this;
        }

        public a c(int i) {
            this.n = i;
            return this;
        }

        public a d(int i) {
            this.o = i;
            return this;
        }

        public a e(int i) {
            this.m = i;
            return this;
        }

        public a f(int i) {
            this.p = i;
            return this;
        }

        public a g(int i) {
            this.r = i;
            return this;
        }

        public a a(Calendar calendar) {
            this.s = calendar;
            return this;
        }

        public a a(float f) {
            this.F = f;
            return this;
        }

        public a h(int i) {
            this.C = i;
            return this;
        }

        public a i(int i) {
            this.D = i;
            return this;
        }

        public a j(int i) {
            this.B = i;
            return this;
        }

        public a k(int i) {
            this.A = i;
            return this;
        }

        public a a(String str, String str2, String str3, String str4, String str5, String str6) {
            this.H = str;
            this.I = str2;
            this.J = str3;
            this.K = str4;
            this.L = str5;
            this.M = str6;
            return this;
        }

        public a a(boolean z) {
            this.z = z;
            return this;
        }

        public SobotTimePickerView a() {
            return new SobotTimePickerView(this);
        }
    }

    private void a(Context context) {
        int i;
        c(this.I);
        a(this.N);
        c();
        d();
        com.sobot.chat.widget.timePicker.b.a aVar = this.k;
        if (aVar == null) {
            LayoutInflater.from(context).inflate(q.a(context, TtmlUtils.TAG_LAYOUT, "sobot_pickerview_time"), this.b);
            this.n = (TextView) b(q.a(context, "id", "tvTitle"));
            this.l = (Button) b(q.a(context, "id", "btnSubmit"));
            this.m = (ImageView) b(q.a(context, "id", "btnCancel"));
            this.l.setTag("submit");
            this.m.setTag(Constant.CASH_LOAD_CANCEL);
            this.l.setOnClickListener(this);
            this.m.setOnClickListener(this);
            this.l.setText(TextUtils.isEmpty(this.r) ? q.f(context, "sobot_btn_submit") : this.r);
            this.n.setText(TextUtils.isEmpty(this.t) ? "" : this.t);
            Button button = this.l;
            int i2 = this.u;
            if (i2 == 0) {
                i2 = this.d;
            }
            button.setTextColor(i2);
            TextView textView = this.n;
            int i3 = this.w;
            if (i3 == 0) {
                i3 = this.g;
            }
            textView.setTextColor(i3);
            this.l.setTextSize((float) this.z);
            this.n.setTextSize((float) this.A);
            RelativeLayout relativeLayout = (RelativeLayout) b(q.a(context, "id", "rv_topbar"));
            int i4 = this.y;
            if (i4 == 0) {
                i4 = this.f;
            }
            relativeLayout.setBackgroundColor(i4);
        } else {
            aVar.a(LayoutInflater.from(context).inflate(q.a(context, TtmlUtils.TAG_LAYOUT, this.j), this.b));
        }
        LinearLayout linearLayout = (LinearLayout) b(q.a(context, "id", "timepicker"));
        int i5 = this.x;
        if (i5 == 0) {
            i5 = this.h;
        }
        linearLayout.setBackgroundColor(i5);
        this.a = new com.sobot.chat.widget.timePicker.view.a(linearLayout, this.q, this.p, this.B);
        int i6 = this.F;
        if (!(i6 == 0 || (i = this.G) == 0 || i6 > i)) {
            n();
        }
        Calendar calendar = this.D;
        if (calendar == null || this.E == null) {
            if (this.D != null && this.E == null) {
                o();
            } else if (this.D == null && this.E != null) {
                o();
            }
        } else if (calendar.getTimeInMillis() <= this.E.getTimeInMillis()) {
            o();
        }
        p();
        this.a.a(this.Q, this.R, this.S, this.T, this.U, this.V);
        b(this.I);
        this.a.a(this.H);
        this.a.c(this.M);
        this.a.a(this.W);
        this.a.a(this.O);
        this.a.e(this.K);
        this.a.d(this.L);
        this.a.a(Boolean.valueOf(this.J));
    }

    private void n() {
        this.a.a(this.F);
        this.a.b(this.G);
    }

    private void o() {
        this.a.a(this.D, this.E);
        if (this.D == null || this.E == null) {
            Calendar calendar = this.D;
            if (calendar != null) {
                this.C = calendar;
                return;
            }
            Calendar calendar2 = this.E;
            if (calendar2 != null) {
                this.C = calendar2;
                return;
            }
            return;
        }
        Calendar calendar3 = this.C;
        if (calendar3 == null || calendar3.getTimeInMillis() < this.D.getTimeInMillis() || this.C.getTimeInMillis() > this.E.getTimeInMillis()) {
            this.C = this.D;
        }
    }

    private void p() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Calendar instance = Calendar.getInstance();
        Calendar calendar = this.C;
        if (calendar == null) {
            instance.setTimeInMillis(System.currentTimeMillis());
            i5 = instance.get(1);
            i = instance.get(2);
            i2 = instance.get(5);
            i3 = instance.get(11);
            i4 = instance.get(12);
            i6 = instance.get(13);
        } else {
            i5 = calendar.get(1);
            i = this.C.get(2);
            i2 = this.C.get(5);
            i3 = this.C.get(11);
            i4 = this.C.get(12);
            i6 = this.C.get(13);
        }
        this.a.a(i5, i, i2, i3, i4, i6);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (((String) view.getTag()).equals("submit")) {
            a();
        }
        g();
    }

    public void a() {
        if (this.o != null) {
            try {
                this.o.a(com.sobot.chat.widget.timePicker.view.a.a.parse(this.a.a()), this.i);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.sobot.chat.widget.timePicker.view.SobotBasePickerView
    public boolean b() {
        return this.P;
    }
}
