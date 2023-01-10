package com.sobot.chat.activity.base;

import android.Manifest;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.TtmlUtils;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.application.MyApplication;
import com.sobot.chat.b;
import com.sobot.chat.b.a;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.listener.d;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.s;
import com.sobot.chat.widget.image.SobotRCImageView;
import com.sobot.chat.widget.statusbar.c;
import java.util.Locale;

public abstract class SobotBaseActivity extends FragmentActivity {
    public ZhiChiApi b;
    public d c;

    /* access modifiers changed from: protected */
    public abstract int a();

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    /* access modifiers changed from: protected */
    public void b(View view) {
    }

    /* access modifiers changed from: protected */
    public abstract void c();

    public SobotBaseActivity q() {
        return this;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26) {
            if (!b.a(1)) {
                setRequestedOrientation(7);
            } else {
                setRequestedOrientation(6);
            }
        }
        if (b.a(1) && b.a(4)) {
            com.sobot.chat.b.b.a().a(this);
            getWindow().setFlags(1024, 1024);
        }
        setContentView(a());
        int h = h();
        if (h != 0) {
            try {
                c.a(this, h);
            } catch (Exception unused) {
            }
        }
        i();
        getWindow().setSoftInputMode(2);
        this.b = a.a(getApplicationContext()).a();
        MyApplication.getInstance().addActivity(this);
        if (findViewById(a("sobot_layout_titlebar")) != null) {
            g();
            f();
        }
        try {
            a(bundle);
            b();
            c();
        } catch (Exception e) {
            e.printStackTrace();
        }
        e();
        if (k() != null) {
            displayInNotch(k());
        }
    }

    public void displayInNotch(View view) {
        if (b.a(1) && b.a(4) && view != null) {
            com.sobot.chat.b.b.a().a(this, new AnonymousClass1(view));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.base.SobotBaseActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements a.AbstractC0139a {
        final /* synthetic */ View a;

        AnonymousClass1(View view) {
            this.a = view;
        }

        @Override // com.sobot.chat.b.a.AbstractC0139a
        public void a(a.b bVar) {
            if (bVar.a) {
                for (Rect rect : bVar.b) {
                    View view = this.a;
                    if (!(view instanceof WebView) || !(view.getParent() instanceof LinearLayout)) {
                        View view2 = this.a;
                        if (!(view2 instanceof WebView) || !(view2.getParent() instanceof RelativeLayout)) {
                            this.a.setPadding(rect.right + this.a.getPaddingLeft(), this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
                        } else {
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.a.getLayoutParams();
                            layoutParams.leftMargin = rect.right + 14;
                            this.a.setLayoutParams(layoutParams);
                        }
                    } else {
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.a.getLayoutParams();
                        layoutParams2.leftMargin = rect.right + 14;
                        this.a.setLayoutParams(layoutParams2);
                    }
                }
            }
        }
    }

    public void e() {
        Locale locale = (Locale) s.d(this, "SobotLanguage");
        if (locale != null) {
            Resources resources = getResources();
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            resources.updateConfiguration(configuration, displayMetrics);
            return;
        }
        Resources resources2 = getResources();
        DisplayMetrics displayMetrics2 = resources2.getDisplayMetrics();
        Configuration configuration2 = new Configuration();
        configuration2.locale = null;
        resources2.updateConfiguration(configuration2, displayMetrics2);
    }

    /* access modifiers changed from: protected */
    public void f() {
        if (l() != null) {
            a(l());
            l().setOnClickListener(new AnonymousClass2());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.base.SobotBaseActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotBaseActivity.this.b(view);
        }
    }

    /* access modifiers changed from: protected */
    public void g() {
        if (k() != null) {
            a(k());
            k().setOnClickListener(new AnonymousClass3());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.base.SobotBaseActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SobotBaseActivity.this.a(view);
        }
    }

    /* access modifiers changed from: protected */
    public int h() {
        if (-1 != com.sobot.chat.c.m) {
            return getResources().getColor(com.sobot.chat.c.m);
        }
        if (-1 != com.sobot.chat.c.l) {
            return getResources().getColor(com.sobot.chat.c.l);
        }
        return f("sobot_status_bar_color");
    }

    /* access modifiers changed from: protected */
    public void i() {
        View j = j();
        if (j != null) {
            if (-1 != com.sobot.chat.c.k) {
                j.setBackgroundColor(getResources().getColor(com.sobot.chat.c.k));
            }
            if (-1 != com.sobot.chat.c.l) {
                j.setBackgroundColor(getResources().getColor(com.sobot.chat.c.l));
            }
            int b = s.b((Context) this, "robot_current_themeImg", 0);
            if (b != 0) {
                j.setBackgroundResource(b);
            }
        }
    }

    /* access modifiers changed from: protected */
    public View j() {
        return findViewById(a("sobot_layout_titlebar"));
    }

    /* access modifiers changed from: protected */
    public TextView k() {
        return (TextView) findViewById(a("sobot_tv_left"));
    }

    /* access modifiers changed from: protected */
    public TextView l() {
        return (TextView) findViewById(a("sobot_tv_right"));
    }

    /* access modifiers changed from: protected */
    public SobotRCImageView m() {
        return (SobotRCImageView) findViewById(a("sobot_avatar_iv"));
    }

    /* access modifiers changed from: protected */
    public View n() {
        return findViewById(a("sobot_text_title"));
    }

    /* access modifiers changed from: protected */
    public void a(int i, String str, boolean z) {
        TextView l = l();
        if (l != null && (l instanceof TextView)) {
            TextView textView = l;
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            } else {
                textView.setText("");
            }
            if (i != 0) {
                Drawable drawable = getResources().getDrawable(i);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable, null);
            } else {
                textView.setCompoundDrawables(null, null, null, null);
            }
            if (z) {
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i, String str, boolean z) {
        TextView k = k();
        if (k != null && (k instanceof TextView)) {
            TextView textView = k;
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            } else {
                textView.setText("");
            }
            if (i != 0) {
                Drawable drawable = getResources().getDrawable(i);
                if (-1 != com.sobot.chat.c.b) {
                    drawable = r.a(getApplicationContext(), drawable, com.sobot.chat.c.b);
                }
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textView.setCompoundDrawables(drawable, null, null, null);
            } else {
                textView.setCompoundDrawables(null, null, null, null);
            }
            if (z) {
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        com.sobot.chat.core.http.a.a().a(this);
        MyApplication.getInstance().deleteActivity(this);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void a(View view) {
        onBackPressed();
    }

    @Override // android.app.Activity
    public void setTitle(CharSequence charSequence) {
        View n = n();
        if (n != null && (n instanceof TextView)) {
            TextView textView = (TextView) n;
            textView.setText(charSequence);
            a(textView);
        }
    }

    @Override // android.app.Activity
    public void setTitle(int i) {
        m().setVisibility(8);
        View n = n();
        if (n != null && (n instanceof TextView)) {
            TextView textView = (TextView) n;
            textView.setVisibility(0);
            textView.setText(i);
        }
    }

    public int a(String str) {
        return q.a(this, "id", str);
    }

    public int b(String str) {
        return q.a(this, "drawable", str);
    }

    public int c(String str) {
        return q.a(this, TtmlUtils.TAG_LAYOUT, str);
    }

    public int d(String str) {
        return q.a(this, "color", str);
    }

    public String e(String str) {
        return q.f(this, str);
    }

    public int f(String str) {
        int d = d(str);
        if (d != 0) {
            return getResources().getColor(d);
        }
        return 0;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 193) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                try {
                    if (iArr[i2] != 0) {
                        String str = "sobot_no_permission_text";
                        if (strArr[i2] != null && strArr[i2].equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                            str = "sobot_no_write_external_storage_permission";
                        } else if (strArr[i2] != null && strArr[i2].equals(Manifest.permission.RECORD_AUDIO)) {
                            str = "sobot_no_record_audio_permission";
                        } else if (strArr[i2] != null && strArr[i2].equals(Manifest.permission.CAMERA)) {
                            str = "sobot_no_camera_permission";
                        }
                        if (this.c != null) {
                            this.c.a(this, e(str));
                            return;
                        }
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            }
            if (this.c != null) {
                this.c.a();
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean o() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(getApplicationContext()) < 23 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 193);
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean p() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(getApplicationContext()) < 23) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.CAMERA}, 193);
            return false;
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == 0) {
            return true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.CAMERA}, 193);
            return false;
        }
    }

    private void a(TextView textView) {
        if (-1 != com.sobot.chat.c.b) {
            textView.setTextColor(getResources().getColor(com.sobot.chat.c.b));
        }
    }

    public static boolean r() {
        Camera camera;
        try {
            camera = Camera.open(0);
            try {
                camera.setParameters(camera.getParameters());
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            camera = null;
        }
        if (camera == null) {
            return false;
        }
        camera.release();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.uiMode & 48;
        if (i == 16) {
            m.d("=====\u5173\u95ed\u591c\u95f4\u6a21\u5f0f====");
            recreate();
        } else if (i == 32) {
            m.d("=====\u5f00\u542f\u591c\u95f4\u6a21\u5f0f====");
            recreate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean s() {
        return (getWindow().getAttributes().flags & 1024) == 1024;
    }
}
