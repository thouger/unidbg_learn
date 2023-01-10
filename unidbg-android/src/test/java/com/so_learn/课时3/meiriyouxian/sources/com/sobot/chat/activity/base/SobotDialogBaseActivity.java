package com.sobot.chat.activity.base;

import android.Manifest;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.sobot.chat.b;
import com.sobot.chat.b.a;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.q;

public abstract class SobotDialogBaseActivity extends SobotBaseActivity {
    public Activity t() {
        return this;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.gravity = 80;
        window.setAttributes(attributes);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public boolean p() {
        if (Build.VERSION.SDK_INT < 23 || d.l(getApplicationContext()) < 23) {
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

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public boolean o() {
        if (Build.VERSION.SDK_INT < 23 || d.l(getApplicationContext()) < 23 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 193);
        return false;
    }

    public static void a(Activity activity, View view) {
        if (b.a(1) && b.a(4) && view != null && activity != null) {
            com.sobot.chat.b.b.a().a(activity, new AnonymousClass1(view));
        }
    }

    /* renamed from: com.sobot.chat.activity.base.SobotDialogBaseActivity$1  reason: invalid class name */
    static class AnonymousClass1 implements a.AbstractC0139a {
        final /* synthetic */ View a;

        AnonymousClass1(View view) {
            this.a = view;
        }

        @Override // com.sobot.chat.b.a.AbstractC0139a
        public void a(a.b bVar) {
            if (bVar.a) {
                for (Rect rect : bVar.b) {
                    this.a.setPadding(rect.right, this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
                }
            }
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || motionEvent.getY() > 0.0f) {
            return true;
        }
        finish();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        d();
    }

    private void d() {
        overridePendingTransition(q.a(getApplicationContext(), "anim", "sobot_popupwindow_in"), q.a(getApplicationContext(), "anim", "sobot_popupwindow_out"));
    }
}
