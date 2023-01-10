package com.sobot.chat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.api.model.SobotCacheFile;
import com.sobot.chat.application.MyApplication;
import com.sobot.chat.camera.StVideoView;
import com.sobot.chat.camera.a.g;
import com.sobot.chat.core.http.g.a;
import com.sobot.chat.core.http.g.b;
import com.sobot.chat.core.http.g.c;
import com.sobot.chat.core.http.model.SobotProgress;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.t;
import com.sobot.chat.utils.z;
import java.io.File;
import java.util.Map;

public class SobotVideoActivity extends FragmentActivity implements View.OnClickListener {
    private StVideoView a;
    private TextView b;
    private ImageView c;
    private ProgressBar d;
    private SobotCacheFile e;
    private c f;
    private b g;

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, 1041);
    }

    public static Intent a(Context context, SobotCacheFile sobotCacheFile) {
        if (sobotCacheFile == null) {
            return null;
        }
        Intent intent = new Intent(context, SobotVideoActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("EXTRA_VIDEO_FILE_DATA", sobotCacheFile);
        return intent;
    }

    /* renamed from: com.sobot.chat.activity.SobotVideoActivity$1  reason: invalid class name */
    class AnonymousClass1 extends b {
        @Override // com.sobot.chat.core.http.i.a
        public void d(SobotProgress sobotProgress) {
        }

        AnonymousClass1(Object obj) {
            super(obj);
        }

        @Override // com.sobot.chat.core.http.i.a
        public void a(SobotProgress sobotProgress) {
            SobotVideoActivity.this.b(sobotProgress);
        }

        @Override // com.sobot.chat.core.http.i.a
        public void b(SobotProgress sobotProgress) {
            SobotVideoActivity.this.b(sobotProgress);
        }

        @Override // com.sobot.chat.core.http.i.a
        public void c(SobotProgress sobotProgress) {
            SobotVideoActivity.this.b(sobotProgress);
        }

        public void a(File file, SobotProgress sobotProgress) {
            SobotVideoActivity.this.b(sobotProgress);
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotVideoActivity$2  reason: invalid class name */
    class AnonymousClass2 implements g {
        final /* synthetic */ SobotVideoActivity a;

        AnonymousClass2(SobotVideoActivity sobotVideoActivity) {
            JniLib.cV(this, sobotVideoActivity, 1040);
        }

        @Override // com.sobot.chat.camera.a.g
        public void a() {
            this.a.b.setVisibility(8);
        }

        @Override // com.sobot.chat.camera.a.g
        public void b() {
            m.d("progress---onEnd");
            this.a.b.setVisibility(0);
        }

        @Override // com.sobot.chat.camera.a.g
        public void c() {
            this.a.c();
        }

        @Override // com.sobot.chat.camera.a.g
        public void d() {
            this.a.finish();
        }
    }

    private void a() {
        try {
            this.e = (SobotCacheFile) getIntent().getSerializableExtra("EXTRA_VIDEO_FILE_DATA");
            if (this.e == null) {
                return;
            }
            if (!TextUtils.isEmpty(this.e.getMsgId())) {
                a.a().a(z.a().c());
                if (!TextUtils.isEmpty(this.e.getFilePath())) {
                    a(this.e.getFilePath());
                } else {
                    b();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b() {
        SobotProgress a = com.sobot.chat.core.http.f.a.a().a(this.e.getMsgId());
        if (a == null) {
            a((SobotProgress) null);
        } else if (a.status != 5) {
            a(a);
        } else if (TextUtils.isEmpty(a.filePath) || !new File(a.filePath).exists()) {
            a(a);
        } else {
            b(a);
        }
    }

    private void a(SobotProgress sobotProgress) {
        if (sobotProgress != null) {
            this.f = a.a(sobotProgress);
            c cVar = this.f;
            if (cVar != null) {
                cVar.a(true);
            }
        }
        this.f = com.sobot.chat.core.a.a().a(this.e.getMsgId(), this.e.getUrl(), this.e.getFileName(), (Map<String, String>) null);
        c cVar2 = this.f;
        if (cVar2 != null) {
            cVar2.a(this.g).b();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(SobotProgress sobotProgress) {
        int i = sobotProgress.status;
        if (i == 0 || i == 1) {
            this.b.setVisibility(8);
            this.d.setVisibility(0);
            this.c.setVisibility(0);
            t.a(this, this.e.getSnapshot(), this.c, 0, 0);
        } else if (i == 2 || i == 3) {
            a(sobotProgress.fraction, sobotProgress.currentSize, sobotProgress.totalSize);
        } else if (i == 4) {
            a.a().c(sobotProgress.tag);
            c();
        } else if (i == 5) {
            this.e.setFilePath(sobotProgress.filePath);
            a(sobotProgress.filePath);
        }
    }

    private void a(float f, long j, long j2) {
        this.b.setVisibility(8);
        this.d.setVisibility(0);
        this.c.setVisibility(0);
        t.a(this, this.e.getSnapshot(), this.c, 0, 0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c() {
        this.b.setVisibility(8);
        this.d.setVisibility(0);
        this.c.setVisibility(0);
        t.a(this, this.e.getSnapshot(), this.c, 0, 0);
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                this.b.setVisibility(8);
                this.d.setVisibility(8);
                this.c.setVisibility(8);
                this.a.setVideoPath(str);
                this.a.e();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(5380);
        } else if (Build.VERSION.SDK_INT >= 16) {
            getWindow().getDecorView().setSystemUiVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.a.a();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.a.b();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        MyApplication.getInstance().deleteActivity(this);
        a.a().d("SOBOT_TAG_DOWNLOAD_ACT_VIDEO");
        c cVar = this.f;
        if (cVar != null && (cVar.a.status == 5 || this.f.a.status == 0 || this.f.a.status == 3 || this.f.a.status == 4)) {
            a.a().c(this.f.a.tag);
        }
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        TextView textView = this.b;
        if (view == textView) {
            textView.setSelected(!textView.isSelected());
            this.a.a(this.b.isSelected());
        }
    }
}
