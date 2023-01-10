package com.sobot.chat.presenter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.sobot.chat.api.model.SobotCusFieldConfig;
import com.sobot.chat.api.model.SobotFieldModel;
import com.sobot.chat.listener.b;
import com.sobot.chat.utils.ac;
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.kpswitch.util.c;

/* access modifiers changed from: package-private */
public class StCusFieldPresenter$5 implements View.OnClickListener {
    final /* synthetic */ SobotCusFieldConfig a;
    final /* synthetic */ TextView b;
    final /* synthetic */ EditText c;
    final /* synthetic */ LinearLayout d;
    final /* synthetic */ TextView e;
    final /* synthetic */ Context f;
    final /* synthetic */ TextView g;
    final /* synthetic */ b h;
    final /* synthetic */ SobotFieldModel i;

    StCusFieldPresenter$5(SobotCusFieldConfig sobotCusFieldConfig, TextView textView, EditText editText, LinearLayout linearLayout, TextView textView2, Context context, TextView textView3, b bVar, SobotFieldModel sobotFieldModel) {
        this.a = sobotCusFieldConfig;
        this.b = textView;
        this.c = editText;
        this.d = linearLayout;
        this.e = textView2;
        this.f = context;
        this.g = textView3;
        this.h = bVar;
        this.i = sobotFieldModel;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (2 == this.a.getFieldType()) {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
            this.c.setFocusableInTouchMode(true);
            this.c.setFocusable(true);
            this.c.requestFocus();
        } else {
            for (int i = 0; i < this.d.getChildCount(); i++) {
                if ((this.d.getChildAt(i) instanceof EditText) && this.d.getChildAt(i).getVisibility() == 0) {
                    this.d.setVisibility(0);
                    TextView textView = this.e;
                    Context context = this.f;
                    textView.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray2")));
                    this.e.setTextSize(12.0f);
                    this.g.setVisibility(8);
                    EditText editText = (EditText) this.d.getChildAt(i);
                    editText.setFocusable(true);
                    c.a(editText);
                    editText.setOnFocusChangeListener(new AnonymousClass1(editText));
                }
            }
        }
        b bVar = this.h;
        if (bVar != null) {
            bVar.a(view, this.a.getFieldType(), this.i);
        }
    }

    /* renamed from: com.sobot.chat.presenter.StCusFieldPresenter$5$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnFocusChangeListener {
        final /* synthetic */ EditText a;

        AnonymousClass1(EditText editText) {
            this.a = editText;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                StCusFieldPresenter$5.this.g.setVisibility(8);
            } else if (ac.a((Object) this.a.getText().toString().trim())) {
                StCusFieldPresenter$5.this.e.setTextSize(14.0f);
                StCusFieldPresenter$5.this.e.setTextColor(ContextCompat.getColor(StCusFieldPresenter$5.this.f, q.c(StCusFieldPresenter$5.this.f, "sobot_common_gray1")));
                StCusFieldPresenter$5.this.d.setVisibility(8);
                StCusFieldPresenter$5.this.g.setVisibility(0);
            }
        }
    }
}
