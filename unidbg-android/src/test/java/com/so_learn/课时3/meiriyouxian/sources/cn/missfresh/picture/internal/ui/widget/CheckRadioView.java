package cn.missfresh.picture.internal.ui.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.res.ResourcesCompat;
import cn.missfresh.picture.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class CheckRadioView extends AppCompatImageView {
    private Drawable a;
    private int b;
    private int c;

    public CheckRadioView(Context context) {
        super(context);
        AppMethodBeat.i(17806, false);
        a();
        AppMethodBeat.o(17806);
    }

    public CheckRadioView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(17808, false);
        a();
        AppMethodBeat.o(17808);
    }

    private void a() {
        AppMethodBeat.i(17809, false);
        this.b = ResourcesCompat.getColor(getResources(), R.color.item_checkCircle_backgroundColor, getContext().getTheme());
        this.c = ResourcesCompat.getColor(getResources(), R.color.check_original_radio_disable, getContext().getTheme());
        setChecked(false);
        AppMethodBeat.o(17809);
    }

    public void setChecked(boolean z) {
        AppMethodBeat.i(17810, false);
        if (z) {
            setImageResource(R.drawable.ic_preview_radio_on);
            this.a = getDrawable();
            this.a.setColorFilter(this.b, PorterDuff.Mode.SRC_IN);
        } else {
            setImageResource(R.drawable.ic_preview_radio_off);
            this.a = getDrawable();
            this.a.setColorFilter(this.c, PorterDuff.Mode.SRC_IN);
        }
        AppMethodBeat.o(17810);
    }

    public void setColor(int i) {
        AppMethodBeat.i(17812, false);
        if (this.a == null) {
            this.a = getDrawable();
        }
        this.a.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        AppMethodBeat.o(17812);
    }
}
