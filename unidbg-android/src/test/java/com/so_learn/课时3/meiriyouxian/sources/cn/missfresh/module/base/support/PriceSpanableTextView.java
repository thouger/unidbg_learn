package cn.missfresh.module.base.support;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;

public class PriceSpanableTextView extends AppCompatTextView {
    public PriceSpanableTextView(Context context) {
        this(context, null);
    }

    public PriceSpanableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(String str, int i, String str2, int i2, int i3, int i4, int i5, int i6, int i7) {
        AppMethodBeat.i(BaseConstants.ERR_SVR_MSG_NOT_SELF_FRIEND, false);
        if (i7 == 0) {
            setText("");
        } else {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            a(spannableStringBuilder, str, i, i5);
            a(spannableStringBuilder, str2, i2, i6);
            a(spannableStringBuilder, at.a(i3), i4, i6);
            if (i7 == 2) {
                spannableStringBuilder.setSpan(new AnonymousClass1(i6), 0, spannableStringBuilder.length(), 18);
            }
            setText(spannableStringBuilder);
        }
        AppMethodBeat.o(BaseConstants.ERR_SVR_MSG_NOT_SELF_FRIEND);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.PriceSpanableTextView$1  reason: invalid class name */
    public class AnonymousClass1 extends StrikethroughSpan {
        final /* synthetic */ int a;

        AnonymousClass1(int i) {
            this.a = i;
        }

        @Override // android.text.style.StrikethroughSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            AppMethodBeat.i(20001, false);
            super.updateDrawState(textPaint);
            textPaint.setColor(this.a);
            AppMethodBeat.o(20001);
        }
    }

    private void a(SpannableStringBuilder spannableStringBuilder, String str, int i, int i2) {
        AppMethodBeat.i(20015, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(20015);
            return;
        }
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append((CharSequence) str);
        if (i > 0) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i, true), length, spannableStringBuilder.length(), 18);
        }
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i2), length, spannableStringBuilder.length(), 18);
        AppMethodBeat.o(20015);
    }
}
