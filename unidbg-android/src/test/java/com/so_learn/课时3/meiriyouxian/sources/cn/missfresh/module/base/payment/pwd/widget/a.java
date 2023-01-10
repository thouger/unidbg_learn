package cn.missfresh.module.base.payment.pwd.widget;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import com.bangcle.andjni.JniLib;

/* compiled from: MryxPayPwdTransformationMethod */
public class a extends PasswordTransformationMethod {
    private String a;

    /* compiled from: MryxPayPwdTransformationMethod */
    /* renamed from: cn.missfresh.module.base.payment.pwd.widget.a$a  reason: collision with other inner class name */
    private class C0027a implements CharSequence {
        final /* synthetic */ a a;
        private CharSequence b;

        public C0027a(a aVar, CharSequence charSequence) {
            JniLib.cV(this, aVar, charSequence, 322);
        }

        @Override // java.lang.CharSequence
        public char charAt(int i) {
            return JniLib.cC(this, Integer.valueOf(i), 319);
        }

        @Override // java.lang.CharSequence
        public int length() {
            return JniLib.cI(this, 320);
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int i, int i2) {
            return (CharSequence) JniLib.cL(this, Integer.valueOf(i), Integer.valueOf(i2), 321);
        }
    }

    @Override // android.text.method.PasswordTransformationMethod, android.text.method.TransformationMethod
    public CharSequence getTransformation(CharSequence charSequence, View view) {
        return (CharSequence) JniLib.cL(this, charSequence, view, 323);
    }

    public a(String str) {
        this.a = str;
    }
}
