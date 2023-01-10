package com.sobot.chat.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.application.MyApplication;
import com.sobot.chat.camera.StCameraView;
import com.sobot.chat.camera.a.a;
import com.sobot.chat.camera.a.c;
import com.sobot.chat.camera.a.d;
import com.sobot.chat.camera.c.f;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.q;

public class SobotCameraActivity extends FragmentActivity {
    private StCameraView a;

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, 995);
    }

    public static Intent a(Context context) {
        return new Intent(context, SobotCameraActivity.class);
    }

    public static String a(Intent intent) {
        return intent.getStringExtra("EXTRA_IMAGE_FILE_PATH");
    }

    public static String b(Intent intent) {
        return intent.getStringExtra("EXTRA_VIDEO_FILE_PATH");
    }

    public static int c(Intent intent) {
        return intent.getIntExtra("EXTRA_ACTION_TYPE", 0);
    }

    /* renamed from: com.sobot.chat.activity.SobotCameraActivity$1  reason: invalid class name */
    class AnonymousClass1 implements d {
        final /* synthetic */ SobotCameraActivity a;

        AnonymousClass1(SobotCameraActivity sobotCameraActivity) {
            JniLib.cV(this, sobotCameraActivity, 992);
        }

        @Override // com.sobot.chat.camera.a.d
        public void a() {
            this.a.setResult(103, new Intent());
            this.a.finish();
        }

        @Override // com.sobot.chat.camera.a.d
        public void b() {
            SobotCameraActivity sobotCameraActivity = this.a;
            ae.a(sobotCameraActivity, q.f(sobotCameraActivity, "sobot_no_voice_permission"), 0);
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotCameraActivity$2  reason: invalid class name */
    class AnonymousClass2 implements a {
        final /* synthetic */ SobotCameraActivity a;

        AnonymousClass2(SobotCameraActivity sobotCameraActivity) {
            JniLib.cV(this, sobotCameraActivity, 993);
        }

        @Override // com.sobot.chat.camera.a.a
        public void a(Bitmap bitmap) {
            Intent intent = new Intent();
            intent.putExtra("EXTRA_ACTION_TYPE", 0);
            if (bitmap != null) {
                intent.putExtra("EXTRA_IMAGE_FILE_PATH", f.a(100, bitmap));
            }
            this.a.setResult(103, intent);
            this.a.finish();
        }

        @Override // com.sobot.chat.camera.a.a
        public void a(String str, Bitmap bitmap) {
            Intent intent = new Intent();
            intent.putExtra("EXTRA_ACTION_TYPE", 1);
            if (bitmap != null) {
                intent.putExtra("EXTRA_IMAGE_FILE_PATH", f.a(80, bitmap));
            }
            intent.putExtra("EXTRA_VIDEO_FILE_PATH", str);
            this.a.setResult(103, intent);
            this.a.finish();
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotCameraActivity$3  reason: invalid class name */
    class AnonymousClass3 implements c {
        final /* synthetic */ SobotCameraActivity a;

        AnonymousClass3(SobotCameraActivity sobotCameraActivity) {
            JniLib.cV(this, sobotCameraActivity, 994);
        }

        @Override // com.sobot.chat.camera.a.c
        public void a() {
            this.a.finish();
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        } else if (Build.VERSION.SDK_INT >= 16) {
            getWindow().getDecorView().setSystemUiVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.a.b();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.a.c();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        MyApplication.getInstance().deleteActivity(this);
        super.onDestroy();
    }
}
