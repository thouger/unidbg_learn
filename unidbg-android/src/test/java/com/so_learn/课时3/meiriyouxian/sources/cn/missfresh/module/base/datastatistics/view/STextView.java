package cn.missfresh.module.base.datastatistics.view;

import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class STextView extends AppCompatTextView {
    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        AppMethodBeat.i(12882, false);
        super.setText(charSequence, bufferType);
        StatisticsManager.onEventToMRYX(this);
        AppMethodBeat.o(12882);
    }
}
