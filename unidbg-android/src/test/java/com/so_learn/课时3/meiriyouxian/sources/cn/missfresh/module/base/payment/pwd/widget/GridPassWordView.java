package cn.missfresh.module.base.payment.pwd.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.payment.pwd.widget.PassWordInputEditText;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.bangcle.andjni.JniLib;

public class GridPassWordView extends LinearLayout implements TextWatcher, View.OnClickListener, PassWordInputEditText.b {
    private static final int a = aw.b(6);
    private ColorStateList b;
    private int c = 16;
    private int d;
    private int e;
    private int f;
    private Drawable g;
    private Drawable h;
    private int i;
    private int j;
    private boolean k;
    private String l;
    private int m;
    private int n;
    private String[] o;
    private TextView[] p;
    private PassWordInputEditText q;
    private a r;
    private PasswordTransformationMethod s;

    public interface a {
        void a(String str);

        void b(String str);
    }

    private void a(Context context) {
        JniLib.cV(this, context, 310);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        JniLib.cV(this, context, attributeSet, Integer.valueOf(i), 311);
    }

    private void e() {
        JniLib.cV(this, 312);
    }

    private void f() {
        JniLib.cV(this, 313);
    }

    private Drawable g() {
        return (Drawable) JniLib.cL(this, 314);
    }

    private boolean getPassWordVisibility() {
        return JniLib.cZ(this, 315);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), 303);
    }

    public void c() {
        JniLib.cV(this, 304);
    }

    public boolean d() {
        return JniLib.cZ(this, 305);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        JniLib.cV(this, view, 306);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        JniLib.cV(this, parcelable, 307);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        return (Parcelable) JniLib.cL(this, 308);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
    }

    public void setCustomArr(TextView textView) {
        JniLib.cV(this, textView, 309);
    }

    static {
        AppMethodBeat.i(16689, false);
        AppMethodBeat.o(16689);
    }

    public GridPassWordView(Context context) {
        super(context);
        AppMethodBeat.i(16631, false);
        a(context, null, 0);
        AppMethodBeat.o(16631);
    }

    public GridPassWordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(16633, false);
        a(context, attributeSet, 0);
        AppMethodBeat.o(16633);
    }

    public GridPassWordView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(16634, false);
        a(context, attributeSet, i);
        AppMethodBeat.o(16634);
    }

    private void b(Context context, AttributeSet attributeSet, int i) {
        AppMethodBeat.i(16637, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GridPasswordView, i, 0);
        this.b = obtainStyledAttributes.getColorStateList(R.styleable.GridPasswordView_gpvTextColor);
        if (this.b == null) {
            this.b = ColorStateList.valueOf(getResources().getColor(17170435));
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.GridPasswordView_gpvTextSize, -1);
        if (dimensionPixelSize != -1) {
            this.c = aw.b((float) dimensionPixelSize);
        }
        this.d = (int) obtainStyledAttributes.getDimension(R.styleable.GridPasswordView_gpvLineWidth, (float) aw.b(1));
        this.e = obtainStyledAttributes.getColor(R.styleable.GridPasswordView_gpvLineColor, -1433892728);
        this.f = obtainStyledAttributes.getColor(R.styleable.GridPasswordView_gpvGridColor, -1);
        this.n = obtainStyledAttributes.getInt(R.styleable.GridPasswordView_gpvBGCornor, 0);
        this.g = obtainStyledAttributes.getDrawable(R.styleable.GridPasswordView_gpvLineColor);
        if (this.g == null) {
            this.g = new ColorDrawable(this.e);
        }
        this.h = g();
        this.i = obtainStyledAttributes.getInt(R.styleable.GridPasswordView_gpvPasswordLength, 6);
        this.j = obtainStyledAttributes.getInt(R.styleable.GridPasswordView_gpvChildViewSpaceLength, a);
        this.k = obtainStyledAttributes.getBoolean(R.styleable.GridPasswordView_gpvChildViewStyle, false);
        this.l = obtainStyledAttributes.getString(R.styleable.GridPasswordView_gpvPasswordTransformation);
        if (TextUtils.isEmpty(this.l)) {
            this.l = "\u25cf";
        }
        this.m = obtainStyledAttributes.getInt(R.styleable.GridPasswordView_gpvPasswordType, 0);
        obtainStyledAttributes.recycle();
        int i2 = this.i;
        this.o = new String[i2];
        this.p = new TextView[i2];
        AppMethodBeat.o(16637);
    }

    private void setChildViewsStyle(View[] viewArr) {
        AppMethodBeat.i(16643, false);
        if (this.k) {
            for (View view : viewArr) {
                view.setBackground(g());
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                marginLayoutParams.rightMargin = this.j;
                view.setLayoutParams(marginLayoutParams);
            }
        } else {
            super.setBackgroundDrawable(this.h);
            setBackgroundDrawable(this.h);
        }
        AppMethodBeat.o(16643);
    }

    private void b(Context context) {
        AppMethodBeat.i(16644, false);
        LayoutInflater from = LayoutInflater.from(context);
        from.inflate(R.layout.layout_password_input, this);
        this.q = (PassWordInputEditText) findViewById(R.id.et_password_input);
        this.q.setMaxEms(this.i);
        this.q.addTextChangedListener(this);
        this.q.setOnDeleteKeyClickListener(this);
        setCustomArr(this.q);
        this.p[0] = this.q;
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.d, -1);
        for (int i = 1; i < this.i; i++) {
            if (!this.k) {
                View inflate = from.inflate(R.layout.layout_password_divider, (ViewGroup) null);
                inflate.setBackgroundDrawable(this.g);
                addView(inflate, layoutParams);
            }
            TextView textView = (TextView) from.inflate(R.layout.layout_password_show, (ViewGroup) this, false);
            setCustomArr(textView);
            addView(textView);
            this.p[i] = textView;
        }
        setChildViewsStyle(this.p);
        setOnClickListener(this);
        AppMethodBeat.o(16644);
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        AppMethodBeat.i(16648, false);
        if (charSequence == null) {
            AppMethodBeat.o(16648);
            return;
        }
        String charSequence2 = charSequence.toString();
        if (charSequence2.length() == 1) {
            this.o[0] = charSequence2;
            e();
        } else if (charSequence2.length() == 2) {
            String substring = charSequence2.substring(1);
            int i4 = 0;
            while (true) {
                String[] strArr = this.o;
                if (i4 >= strArr.length) {
                    break;
                } else if (strArr[i4] == null) {
                    strArr[i4] = substring;
                    this.p[i4].setText(substring);
                    e();
                    break;
                } else {
                    i4++;
                }
            }
            this.q.removeTextChangedListener(this);
            this.q.setText(this.o[0]);
            if (this.q.getText().length() >= 1) {
                this.q.setSelection(1);
            }
            this.q.addTextChangedListener(this);
        }
        AppMethodBeat.o(16648);
    }

    @Override // cn.missfresh.module.base.payment.pwd.widget.PassWordInputEditText.b
    public void a() {
        AppMethodBeat.i(16654, false);
        int length = this.o.length - 1;
        while (true) {
            if (length < 0) {
                break;
            }
            String[] strArr = this.o;
            if (strArr[length] != null) {
                strArr[length] = null;
                this.p[length].setText((CharSequence) null);
                e();
                break;
            }
            this.p[length].setText((CharSequence) null);
            length--;
        }
        AppMethodBeat.o(16654);
    }

    public String getPassWord() {
        int i = 0;
        AppMethodBeat.i(16658, false);
        StringBuilder sb = new StringBuilder();
        while (true) {
            String[] strArr = this.o;
            if (i < strArr.length) {
                if (strArr[i] != null) {
                    sb.append(strArr[i]);
                }
                i++;
            } else {
                String sb2 = sb.toString();
                AppMethodBeat.o(16658);
                return sb2;
            }
        }
    }

    public void b() {
        int i = 0;
        AppMethodBeat.i(16661, false);
        while (true) {
            String[] strArr = this.o;
            if (i < strArr.length) {
                strArr[i] = null;
                this.p[i].setText((CharSequence) null);
                i++;
            } else {
                AppMethodBeat.o(16661);
                return;
            }
        }
    }

    public void setPassword(String str) {
        AppMethodBeat.i(16663, false);
        b();
        if (b.a(str)) {
            AppMethodBeat.o(16663);
            return;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            String[] strArr = this.o;
            if (i < strArr.length) {
                strArr[i] = charArray[i] + "";
                this.p[i].setText(this.o[i]);
            }
        }
        AppMethodBeat.o(16663);
    }

    public void setPasswordVisibility(boolean z) {
        PasswordTransformationMethod passwordTransformationMethod;
        AppMethodBeat.i(16665, false);
        TextView[] textViewArr = this.p;
        for (TextView textView : textViewArr) {
            if (z) {
                passwordTransformationMethod = null;
            } else {
                passwordTransformationMethod = this.s;
            }
            textView.setTransformationMethod(passwordTransformationMethod);
            if (textView instanceof EditText) {
                EditText editText = (EditText) textView;
                editText.setSelection(editText.getText().length());
            }
        }
        AppMethodBeat.o(16665);
    }

    public void setOnPasswordChangedListener(a aVar) {
        this.r = aVar;
    }

    public void setPasswordType(int i) {
        AppMethodBeat.i(16672, false);
        boolean passWordVisibility = getPassWordVisibility();
        int i2 = i != 1 ? i != 2 ? i != 3 ? 18 : 225 : 145 : 129;
        for (TextView textView : this.p) {
            textView.setInputType(i2);
        }
        setPasswordVisibility(passWordVisibility);
        AppMethodBeat.o(16672);
    }
}
