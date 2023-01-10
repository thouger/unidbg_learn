package cn.missfresh.module.base.support.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;

public class GraphCodeDialog extends Dialog {
    final String a = "GraphCodeDialog";
    ImageView b;
    private TextView c;
    private EditText d;

    public GraphCodeDialog(Context context) {
        super(context, R.style.style_dialog_transparent_bg);
        AppMethodBeat.i(21492, false);
        a();
        AppMethodBeat.o(21492);
    }

    private void a() {
        AppMethodBeat.i(21494, false);
        setContentView(R.layout.dialog_graph_code_layout);
        this.b = (ImageView) findViewById(R.id.graphCodeIV);
        this.d = (EditText) findViewById(R.id.graphCodeET);
        this.c = (TextView) findViewById(R.id.comfirm);
        setCancelable(false);
        AppMethodBeat.o(21494);
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.GraphCodeDialog$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(21487, false);
            Context context = GraphCodeDialog.this.getContext();
            d.c(context, i.az + "?uq=" + this.a, (ImageView) view);
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(21487);
        }
    }

    public void a(String str, g gVar) {
        AppMethodBeat.i(21495, false);
        AnonymousClass1 r1 = new AnonymousClass1(str);
        this.b.setOnClickListener(r1);
        r1.onClick(this.b);
        this.c.setOnClickListener(new AnonymousClass2(gVar, str));
        super.show();
        AppMethodBeat.o(21495);
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.GraphCodeDialog$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ g a;
        final /* synthetic */ String b;

        AnonymousClass2(g gVar, String str) {
            this.a = gVar;
            this.b = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(21490, false);
            String obj = GraphCodeDialog.this.d.getText().toString();
            if (obj.length() == 0) {
                a.a("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u9a8c\u8bc1\u7801");
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(21490);
                return;
            }
            this.a.a(this.b, obj);
            GraphCodeDialog.this.dismiss();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(21490);
        }
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        AppMethodBeat.i(21496, false);
        cancel();
        AppMethodBeat.o(21496);
    }
}
