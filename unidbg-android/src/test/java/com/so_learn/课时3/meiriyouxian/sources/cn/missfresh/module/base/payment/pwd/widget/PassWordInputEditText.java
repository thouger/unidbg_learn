package cn.missfresh.module.base.payment.pwd.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import com.bangcle.andjni.JniLib;

public class PassWordInputEditText extends EditText {
    private b a;

    public interface b {
        void a();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return (InputConnection) JniLib.cL(this, editorInfo, 318);
    }

    public PassWordInputEditText(Context context) {
        super(context);
    }

    public PassWordInputEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PassWordInputEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnDeleteKeyClickListener(b bVar) {
        this.a = bVar;
    }

    class a extends InputConnectionWrapper {
        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean deleteSurroundingText(int i, int i2) {
            return JniLib.cZ(this, Integer.valueOf(i), Integer.valueOf(i2), 316);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean sendKeyEvent(KeyEvent keyEvent) {
            return JniLib.cZ(this, keyEvent, 317);
        }

        public a(InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
        }
    }
}
