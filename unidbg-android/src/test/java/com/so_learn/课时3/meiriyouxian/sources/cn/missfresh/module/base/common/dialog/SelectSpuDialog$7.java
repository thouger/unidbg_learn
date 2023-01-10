package cn.missfresh.module.base.common.dialog;

import android.view.View;
import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class SelectSpuDialog$7 implements View.OnClickListener {
    final /* synthetic */ b a;

    SelectSpuDialog$7(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(11430, false);
        TextView textView = this.a.m;
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.parseInt(this.a.m.getText().toString()) - 1 < 1 ? 1 : Integer.parseInt(this.a.m.getText().toString()) - 1);
        sb.append("");
        textView.setText(sb.toString());
        if (this.a.r == null) {
            int parseInt = Integer.parseInt(this.a.m.getText().toString());
            b bVar = this.a;
            if (parseInt < b.a(bVar, bVar.a.getStock(), this.a.a.getSeckill_limit())) {
                this.a.l.setEnabled(true);
            }
        } else {
            int parseInt2 = Integer.parseInt(this.a.m.getText().toString());
            b bVar2 = this.a;
            if (parseInt2 < b.a(bVar2, bVar2.r.getStock(), this.a.r.getSeckill_limit())) {
                this.a.l.setEnabled(true);
            }
        }
        b bVar3 = this.a;
        b.b(bVar3, bVar3.m.getText().toString());
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(11430);
    }
}
