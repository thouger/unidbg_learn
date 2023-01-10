package cn.missfresh.module.base.common.dialog.dclog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sobot.chat.utils.r;

public class LogDialog extends Dialog implements View.OnClickListener {
    private Context a;
    private TextView b;
    private a c;

    public interface a {
        void a(OnClickType onClickType);
    }

    public LogDialog(Context context, a aVar) {
        super(context, R.style.log_dialog);
        this.a = context;
        this.c = aVar;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(11568, false);
        super.onCreate(bundle);
        setContentView(R.layout.dialog_show_log_layout);
        findViewById(R.id.closeId).setOnClickListener(this);
        findViewById(R.id.cleanId).setOnClickListener(this);
        findViewById(R.id.getId).setOnClickListener(this);
        findViewById(R.id.logId).setOnClickListener(this);
        findViewById(R.id.postId).setOnClickListener(this);
        this.b = (TextView) findViewById(R.id.context_textId);
        Window window = getWindow();
        window.setGravity(80);
        window.setWindowAnimations(R.style.dialog_window_anim);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = r.a((Activity) this.a);
        attributes.height = (r.b((Activity) this.a) * 2) / 3;
        getWindow().setAttributes(attributes);
        getWindow().setFlags(8, 8);
        setCanceledOnTouchOutside(false);
        AppMethodBeat.o(11568);
    }

    public void a(String str) {
        AppMethodBeat.i(11570, false);
        this.b.setText(str);
        AppMethodBeat.o(11570);
    }

    private void a() {
        AppMethodBeat.i(11572, false);
        this.b.setText("");
        AppMethodBeat.o(11572);
    }

    @Override // android.app.Dialog
    public void show() {
        AppMethodBeat.i(11573, false);
        super.show();
        AppMethodBeat.o(11573);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(11576, false);
        int id = view.getId();
        if (id == R.id.closeId) {
            a();
            dismiss();
        } else if (id != R.id.cleanId) {
            if (id == R.id.logId) {
                this.c.a(OnClickType.log);
            } else if (id == R.id.postId) {
                this.c.a(OnClickType.post);
            } else if (id == R.id.getId) {
                this.c.a(OnClickType.get);
            }
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(11576);
    }

    public enum OnClickType {
        get,
        post,
        log;

        public static OnClickType valueOf(String str) {
            AppMethodBeat.i(11563, false);
            OnClickType onClickType = (OnClickType) Enum.valueOf(OnClickType.class, str);
            AppMethodBeat.o(11563);
            return onClickType;
        }

        static {
            AppMethodBeat.i(11564, false);
            AppMethodBeat.o(11564);
        }
    }
}
