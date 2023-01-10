package com.sobot.chat.widget;

import android.app.Dialog;
import android.content.Context;
import android.media.TtmlUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sobot.chat.utils.q;

public class ReSendDialog extends Dialog {
    public Button a;
    public Button b;
    public a c = null;
    private Context d;
    private TextView e;

    public interface a {
        void a(int i);
    }

    public ReSendDialog(Context context) {
        super(context, q.a(context, "style", "sobot_noAnimDialogStyle"));
        this.d = context;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(q.a(this.d, TtmlUtils.TAG_LAYOUT, "sobot_resend_message_dialog"));
        this.e = (TextView) findViewById(q.a(this.d, "id", "sobot_message"));
        this.e.setText(q.f(this.d, "sobot_resendmsg"));
        this.a = (Button) findViewById(q.a(this.d, "id", "sobot_negativeButton"));
        this.a.setText(q.f(this.d, "sobot_button_send"));
        this.b = (Button) findViewById(q.a(this.d, "id", "sobot_positiveButton"));
        this.b.setText(q.f(this.d, "sobot_btn_cancle"));
        this.a.setOnClickListener(new AnonymousClass1());
        this.b.setOnClickListener(new AnonymousClass2());
    }

    /* renamed from: com.sobot.chat.widget.ReSendDialog$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ReSendDialog.this.c.a(0);
        }
    }

    /* renamed from: com.sobot.chat.widget.ReSendDialog$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ReSendDialog.this.c.a(1);
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }
}
