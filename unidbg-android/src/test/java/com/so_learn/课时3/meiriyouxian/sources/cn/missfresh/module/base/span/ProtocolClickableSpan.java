package cn.missfresh.module.base.span;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ProtocolClickableSpan extends ClickableSpan implements View.OnClickListener {
    private final int a;
    private final a b;

    public interface a {
        void a(int i);
    }

    public ProtocolClickableSpan(int i, a aVar) {
        this.a = i;
        this.b = aVar;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        AppMethodBeat.i(19687, false);
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(this.a);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(19687);
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        AppMethodBeat.i(19689, false);
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(true);
        AppMethodBeat.o(19689);
    }
}
