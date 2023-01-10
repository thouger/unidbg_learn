package cn.missfresh.module.base.common.dialog;

import android.view.View;
import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class SelectSkuDialog$7 implements View.OnClickListener {
    final /* synthetic */ a a;

    SelectSkuDialog$7(a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(11313, false);
        TextView textView = this.a.o;
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.parseInt(this.a.o.getText().toString()) - 1 < 1 ? 1 : Integer.parseInt(this.a.o.getText().toString()) - 1);
        sb.append("");
        textView.setText(sb.toString());
        if (this.a.u == null) {
            int parseInt = Integer.parseInt(this.a.o.getText().toString());
            a aVar = this.a;
            if (parseInt < a.a(aVar, aVar.a.getStock(), this.a.a.getSeckill_limit())) {
                this.a.n.setEnabled(true);
            }
        } else {
            int parseInt2 = Integer.parseInt(this.a.o.getText().toString());
            a aVar2 = this.a;
            if (parseInt2 < a.a(aVar2, aVar2.u.getStock(), this.a.u.getSeckill_limit())) {
                this.a.n.setEnabled(true);
            }
        }
        a aVar3 = this.a;
        a.b(aVar3, aVar3.o.getText().toString());
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(11313);
    }
}
