package cn.missfresh.module.base.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

public class BindWxAndTelLayout extends LinearLayout {
    private View a;
    private View b;
    private View c;
    private TextView d;

    public BindWxAndTelLayout(Context context) {
        this(context, null);
    }

    public BindWxAndTelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BindWxAndTelLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        AppMethodBeat.i(23622, false);
        super.onFinishInflate();
        this.a = findViewById(R.id.toBindingWXLayout);
        this.b = findViewById(R.id.toBindingWXButton);
        this.c = findViewById(R.id.toBindingWXLineView);
        this.d = (TextView) findViewById(R.id.toBindingWXContent);
        AppMethodBeat.o(23622);
    }

    public void setBindBackground(int i) {
        AppMethodBeat.i(23623, false);
        this.a.setBackgroundColor(i);
        AppMethodBeat.o(23623);
    }

    public void a(String str, Activity activity, String str2) {
        AppMethodBeat.i(23624, false);
        if (!b.a(str)) {
            a(str, activity, str2, activity.getClass().getSimpleName());
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        AppMethodBeat.o(23624);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.BindWxAndTelLayout$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23620, false);
            o.a(true, this.a);
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23620);
        }
    }

    public void a(String str, Activity activity, String str2, String str3) {
        AppMethodBeat.i(23625, false);
        this.d.setText(str);
        this.b.setOnClickListener(new AnonymousClass1(str3));
        a();
        AppMethodBeat.o(23625);
    }

    public void a() {
        AppMethodBeat.i(23626, false);
        this.c.setVisibility(0);
        this.a.setVisibility(0);
        AppMethodBeat.o(23626);
    }

    public void b(String str, Activity activity, String str2) {
        AppMethodBeat.i(23627, false);
        if (!b.a(str)) {
            b(str, activity, str2, activity.getClass().getSimpleName());
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        AppMethodBeat.o(23627);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.BindWxAndTelLayout$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ String a;

        AnonymousClass2(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23621, false);
            o.a((Activity) BindWxAndTelLayout.this.getContext(), true, this.a, "");
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23621);
        }
    }

    public void b(String str, Activity activity, String str2, String str3) {
        AppMethodBeat.i(23628, false);
        this.d.setText(str);
        this.b.setOnClickListener(new AnonymousClass2(str3));
        a();
        AppMethodBeat.o(23628);
    }

    public void b() {
        AppMethodBeat.i(23629, false);
        this.c.setVisibility(8);
        this.a.setVisibility(8);
        AppMethodBeat.o(23629);
    }
}
