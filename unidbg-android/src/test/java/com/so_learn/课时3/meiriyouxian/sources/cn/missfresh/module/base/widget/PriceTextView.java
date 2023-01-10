package cn.missfresh.module.base.widget;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class PriceTextView extends AppCompatTextView {
    public PriceTextView(Context context) {
        super(context);
    }

    public PriceTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setPrice(int i) {
        AppMethodBeat.i(23798, false);
        setText(at.a(i));
        AppMethodBeat.o(23798);
    }

    public void setPriceWithRMB(int i) {
        AppMethodBeat.i(23799, false);
        setText(String.format(getResources().getString(R.string.product_price), at.a(i)));
        AppMethodBeat.o(23799);
    }

    public void setNewPriceWithRMB(String str) {
        AppMethodBeat.i(23800, false);
        setText(str);
        AppMethodBeat.o(23800);
    }

    public void setPriceWithRMBAndStrikeThru(int i) {
        AppMethodBeat.i(23801, false);
        String string = getResources().getString(R.string.product_price);
        getPaint().setFlags(16);
        setText(String.format(string, at.a(i)));
        AppMethodBeat.o(23801);
    }

    public void setPriceWithRMBBlack(int i) {
        AppMethodBeat.i(23803, false);
        setText(String.format(getResources().getString(R.string.product_price_black), at.a(i)));
        AppMethodBeat.o(23803);
    }

    public void setPriceWithRMBYuan(int i) {
        AppMethodBeat.i(23804, false);
        setText(String.format(getResources().getString(R.string.product_price_yuan), at.a(i)));
        AppMethodBeat.o(23804);
    }

    public void setPrecisePrice(int i) {
        AppMethodBeat.i(23805, false);
        setText(at.c(i));
        AppMethodBeat.o(23805);
    }

    public void setSpannablePriceWithRMB(int i) {
        AppMethodBeat.i(23806, false);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(String.format(getResources().getString(R.string.product_price), at.a(i)));
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(55), 0, 1, 33);
        setText(spannableStringBuilder);
        AppMethodBeat.o(23806);
    }
}
