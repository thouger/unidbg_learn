package com.sobot.chat.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.TtmlUtils;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.sobot.chat.activity.SobotCameraActivity;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.b;
import com.sobot.chat.b.a;
import com.sobot.chat.c;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.listener.d;
import com.sobot.chat.listener.e;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.widget.dialog.SobotPermissionTipDialog;
import java.io.File;

public abstract class SobotBaseFragment extends Fragment {
    public ZhiChiApi U;
    protected File V;
    public d W;
    private Activity a;

    public SobotBaseFragment L() {
        return this;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.a == null) {
            this.a = (Activity) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.U = a.a(getContext().getApplicationContext()).a();
        if (b.a(4) && b.a(1)) {
            com.sobot.chat.b.b.a().a(getActivity());
            getActivity().getWindow().setFlags(1024, 1024);
        }
    }

    public void a(View view) {
        if (b.a(1) && b.a(4) && view != null) {
            com.sobot.chat.b.b.a().a(getActivity(), new AnonymousClass1(view));
        }
    }

    /* renamed from: com.sobot.chat.fragment.SobotBaseFragment$1  reason: invalid class name */
    class AnonymousClass1 implements a.AbstractC0139a {
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
                            this.a.setPadding(rect.right, this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
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

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        com.sobot.chat.core.http.a.a().a(this);
        com.sobot.chat.core.http.a.a().a("sobot_global_request_cancel_tag");
        super.onDestroyView();
    }

    public int e(String str) {
        return q.a(Z(), "id", str);
    }

    public int f(String str) {
        return q.a(Z(), "drawable", str);
    }

    public int g(String str) {
        return q.a(Z(), TtmlUtils.TAG_LAYOUT, str);
    }

    public int h(String str) {
        return q.a(Z(), "dimen", str);
    }

    public String i(String str) {
        return q.f(Z(), str);
    }

    public float j(String str) {
        return getResources().getDimension(h(str));
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 193) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                try {
                    if (iArr[i2] != 0) {
                        String str = "sobot_no_permission_text";
                        if ((strArr[i2] != null && strArr[i2].equals("android.permission.WRITE_EXTERNAL_STORAGE")) || (strArr[i2] != null && strArr[i2].equals(Manifest.permission.READ_EXTERNAL_STORAGE))) {
                            str = "sobot_no_write_external_storage_permission";
                        } else if (strArr[i2] != null && strArr[i2].equals(Manifest.permission.RECORD_AUDIO)) {
                            str = "sobot_no_record_audio_permission";
                        } else if (strArr[i2] != null && strArr[i2].equals(Manifest.permission.CAMERA)) {
                            str = "sobot_no_camera_permission";
                        }
                        if (this.W != null) {
                            this.W.a(Z(), i(str));
                            return;
                        }
                        return;
                    }
                } catch (Exception unused) {
                    return;
                }
            }
            if (this.W != null) {
                this.W.a();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(View view, int i, String str) {
        if (view != null && (view instanceof TextView)) {
            TextView textView = (TextView) view;
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
            textView.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void b(View view, int i, String str) {
        if (view != null && (view instanceof TextView)) {
            TextView textView = (TextView) view;
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            } else {
                textView.setText("");
            }
            if (i != 0) {
                Drawable drawable = getResources().getDrawable(i);
                if (-1 != c.b) {
                    drawable = r.a(getContext(), drawable, c.b);
                }
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textView.setCompoundDrawables(drawable, null, null, null);
            } else {
                textView.setCompoundDrawables(null, null, null, null);
            }
            a(textView);
        }
    }

    /* access modifiers changed from: protected */
    public boolean M() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(Z().getApplicationContext()) < 23) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(Z(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.READ_EXTERNAL_STORAGE}, 193);
            return false;
        } else if (ContextCompat.checkSelfPermission(Z(), Manifest.permission.READ_EXTERNAL_STORAGE) == 0) {
            return true;
        } else {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.READ_EXTERNAL_STORAGE}, 193);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean N() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(Z().getApplicationContext()) < 23) {
            return true;
        }
        return ContextCompat.checkSelfPermission(Z(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(Z(), Manifest.permission.READ_EXTERNAL_STORAGE) == 0;
    }

    /* access modifiers changed from: protected */
    public boolean O() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(Z().getApplicationContext()) < 23) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(Z(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.RECORD_AUDIO}, 193);
            return false;
        } else if (ContextCompat.checkSelfPermission(Z(), Manifest.permission.RECORD_AUDIO) == 0) {
            return true;
        } else {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.RECORD_AUDIO}, 193);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean P() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(Z().getApplicationContext()) < 23) {
            return true;
        }
        return ContextCompat.checkSelfPermission(Z(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(Z(), Manifest.permission.RECORD_AUDIO) == 0;
    }

    /* access modifiers changed from: protected */
    public boolean Q() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(Z().getApplicationContext()) < 23) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(Z(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 193);
            return false;
        } else if (ContextCompat.checkSelfPermission(Z(), Manifest.permission.CAMERA) != 0) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 193);
            return false;
        } else if (ContextCompat.checkSelfPermission(Z(), Manifest.permission.RECORD_AUDIO) == 0) {
            return true;
        } else {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, 193);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean R() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(Z().getApplicationContext()) < 23) {
            return true;
        }
        return ContextCompat.checkSelfPermission(Z(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(Z(), Manifest.permission.RECORD_AUDIO) == 0 && ContextCompat.checkSelfPermission(Z(), Manifest.permission.CAMERA) == 0;
    }

    /* access modifiers changed from: protected */
    public boolean S() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(getContext()) < 23) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(Z(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", Manifest.permission.CAMERA}, 193);
            return false;
        } else if (ContextCompat.checkSelfPermission(Z(), Manifest.permission.CAMERA) == 0) {
            return true;
        } else {
            requestPermissions(new String[]{Manifest.permission.CAMERA, "android.permission.WRITE_EXTERNAL_STORAGE"}, 193);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean T() {
        if (Build.VERSION.SDK_INT < 23 || com.sobot.chat.utils.d.l(Z().getApplicationContext()) < 23) {
            return true;
        }
        return ContextCompat.checkSelfPermission(Z(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(Z(), Manifest.permission.CAMERA) == 0;
    }

    public void U() {
        if (!com.sobot.chat.utils.d.a()) {
            ae.a(Z().getApplicationContext(), i("sobot_sdcard_does_not_exist"), 0);
            return;
        }
        this.W = new AnonymousClass2();
        if (!b(i("sobot_camera"), i("sobot_camera_yongtu"), 3) && Q()) {
            startActivityForResult(SobotCameraActivity.a(getContext()), 108);
        }
    }

    /* renamed from: com.sobot.chat.fragment.SobotBaseFragment$2  reason: invalid class name */
    class AnonymousClass2 extends e {
        AnonymousClass2() {
        }

        @Override // com.sobot.chat.listener.e, com.sobot.chat.listener.d
        public void a() {
            SobotBaseFragment sobotBaseFragment = SobotBaseFragment.this;
            sobotBaseFragment.startActivityForResult(SobotCameraActivity.a(sobotBaseFragment.L().getContext()), 108);
        }
    }

    public void V() {
        if (!com.sobot.chat.utils.d.a()) {
            ae.a(Z(), i("sobot_sdcard_does_not_exist"), 0);
            return;
        }
        this.W = new AnonymousClass3();
        if (!b(i("sobot_camera"), i("sobot_camera_yongtu"), 4) && S()) {
            this.V = com.sobot.chat.utils.c.c(Z(), this);
        }
    }

    /* renamed from: com.sobot.chat.fragment.SobotBaseFragment$3  reason: invalid class name */
    class AnonymousClass3 extends e {
        AnonymousClass3() {
        }

        @Override // com.sobot.chat.listener.e, com.sobot.chat.listener.d
        public void a() {
            if (SobotBaseFragment.Y()) {
                SobotBaseFragment sobotBaseFragment = SobotBaseFragment.this;
                sobotBaseFragment.V = com.sobot.chat.utils.c.c(sobotBaseFragment.Z(), SobotBaseFragment.this.L());
            }
        }
    }

    /* renamed from: com.sobot.chat.fragment.SobotBaseFragment$4  reason: invalid class name */
    class AnonymousClass4 extends e {
        AnonymousClass4() {
        }

        @Override // com.sobot.chat.listener.e, com.sobot.chat.listener.d
        public void a() {
            com.sobot.chat.utils.c.a(SobotBaseFragment.this.Z(), SobotBaseFragment.this.L());
        }
    }

    public void W() {
        this.W = new AnonymousClass4();
        if (!b(i("sobot_memory_card"), i("sobot_memory_card_yongtu"), 1) && M()) {
            com.sobot.chat.utils.c.a(Z(), this);
        }
    }

    /* access modifiers changed from: protected */
    public boolean d(int i) {
        if (i == 1) {
            return N();
        }
        if (i == 2) {
            return P();
        }
        if (i == 3) {
            return R();
        }
        if (i == 4) {
            return T();
        }
        return true;
    }

    public boolean b(String str, String str2, int i) {
        if (!com.sobot.chat.d.a(16) || d(i)) {
            return false;
        }
        new SobotPermissionTipDialog(this.a, str, str2, new AnonymousClass5(i)).show();
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotBaseFragment$5  reason: invalid class name */
    public class AnonymousClass5 implements SobotPermissionTipDialog.a {
        final /* synthetic */ int a;

        AnonymousClass5(int i) {
            this.a = i;
        }

        @Override // com.sobot.chat.widget.dialog.SobotPermissionTipDialog.a
        public void a(Context context, SobotPermissionTipDialog sobotPermissionTipDialog) {
            sobotPermissionTipDialog.dismiss();
            int i = this.a;
            if (i == 1) {
                if (SobotBaseFragment.this.M()) {
                }
            } else if (i == 2) {
                if (SobotBaseFragment.this.O()) {
                }
            } else if (i != 3) {
                if (i != 4 || SobotBaseFragment.this.S()) {
                }
            } else if (SobotBaseFragment.this.Q()) {
            }
        }

        @Override // com.sobot.chat.widget.dialog.SobotPermissionTipDialog.a
        public void b(Context context, SobotPermissionTipDialog sobotPermissionTipDialog) {
            sobotPermissionTipDialog.dismiss();
        }
    }

    /* renamed from: com.sobot.chat.fragment.SobotBaseFragment$6  reason: invalid class name */
    class AnonymousClass6 extends e {
        AnonymousClass6() {
        }

        @Override // com.sobot.chat.listener.e, com.sobot.chat.listener.d
        public void a() {
            com.sobot.chat.utils.c.b(SobotBaseFragment.this.Z(), SobotBaseFragment.this.L());
        }
    }

    public void X() {
        this.W = new AnonymousClass6();
        if (!b(i("sobot_memory_card"), i("sobot_memory_card_yongtu"), 1) && M()) {
            com.sobot.chat.utils.c.b(Z(), this);
        }
    }

    /* access modifiers changed from: protected */
    public void a(TextView textView) {
        if (textView != null && -1 != c.b) {
            textView.setTextColor(getResources().getColor(c.b));
        }
    }

    public static boolean Y() {
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

    public Activity Z() {
        FragmentActivity activity = getActivity();
        return activity == null ? this.a : activity;
    }
}
