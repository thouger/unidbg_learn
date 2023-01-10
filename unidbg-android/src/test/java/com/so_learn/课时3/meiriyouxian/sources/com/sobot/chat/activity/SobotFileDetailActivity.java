package com.sobot.chat.activity;

import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.api.model.SobotCacheFile;
import com.sobot.chat.core.http.g.a;
import com.sobot.chat.core.http.g.b;
import com.sobot.chat.core.http.g.c;
import com.sobot.chat.core.http.model.SobotProgress;
import com.sobot.chat.utils.h;
import com.sobot.chat.utils.i;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.z;
import com.umeng.message.proguard.l;
import java.io.File;
import java.util.Map;

public class SobotFileDetailActivity extends SobotBaseActivity implements View.OnClickListener {
    private TextView a;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private LinearLayout i;
    private ProgressBar j;
    private TextView k;
    private String l;
    private SobotCacheFile m;
    private c n;
    private b o;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_file_detail");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        setTitle(e("sobot_file_preview"));
        b(b("sobot_btn_back_selector"), "", true);
        this.a = (TextView) findViewById(a("sobot_file_icon"));
        this.d = (TextView) findViewById(a("sobot_file_name"));
        this.e = (TextView) findViewById(a("sobot_tv_file_size"));
        this.f = (TextView) findViewById(a("sobot_tv_progress"));
        this.g = (TextView) findViewById(a("sobot_btn_start"));
        this.g.setText(q.f(this, "sobot_file_download"));
        this.i = (LinearLayout) findViewById(a("sobot_ll_progress"));
        this.j = (ProgressBar) findViewById(a("sobot_pb_progress"));
        this.k = (TextView) findViewById(a("sobot_btn_cancel"));
        this.h = (TextView) findViewById(a("sobot_tv_decribe"));
        this.l = e("sobot_file_downloading");
        this.g.setOnClickListener(this);
        this.k.setOnClickListener(this);
        if (o()) {
            this.o = new AnonymousClass1("tag_download_act");
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotFileDetailActivity$1  reason: invalid class name */
    class AnonymousClass1 extends b {
        @Override // com.sobot.chat.core.http.i.a
        public void d(SobotProgress sobotProgress) {
        }

        AnonymousClass1(Object obj) {
            super(obj);
        }

        @Override // com.sobot.chat.core.http.i.a
        public void a(SobotProgress sobotProgress) {
            SobotFileDetailActivity.this.a(sobotProgress);
        }

        @Override // com.sobot.chat.core.http.i.a
        public void b(SobotProgress sobotProgress) {
            SobotFileDetailActivity.this.a(sobotProgress);
        }

        @Override // com.sobot.chat.core.http.i.a
        public void c(SobotProgress sobotProgress) {
            SobotFileDetailActivity.this.a(sobotProgress);
        }

        public void a(File file, SobotProgress sobotProgress) {
            SobotFileDetailActivity.this.a(sobotProgress);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        try {
            this.m = (SobotCacheFile) getIntent().getSerializableExtra("sobot_intent_data_selected_file");
            if (this.m == null) {
                return;
            }
            if (!TextUtils.isEmpty(this.m.getMsgId())) {
                this.a.setBackgroundResource(com.sobot.chat.utils.c.a(getApplicationContext(), this.m.getFileType()));
                this.d.setText(this.m.getFileName());
                if (TextUtils.isEmpty(this.m.getFileSize())) {
                    i.a(this.m.getUrl(), new AnonymousClass2(this));
                } else {
                    TextView textView = this.e;
                    textView.setText(e("sobot_file_size") + "\uff1a" + this.m.getFileSize());
                }
                a.a().a(z.a().f());
                if (!TextUtils.isEmpty(this.m.getFilePath())) {
                    u();
                } else {
                    d();
                }
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotFileDetailActivity$2  reason: invalid class name */
    class AnonymousClass2 implements i.a<String> {
        final /* synthetic */ SobotFileDetailActivity a;

        AnonymousClass2(SobotFileDetailActivity sobotFileDetailActivity) {
            JniLib.cV(this, sobotFileDetailActivity, 1010);
        }

        public void a(String str) {
            this.a.runOnUiThread(new AnonymousClass1(this, str));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.activity.SobotFileDetailActivity$2$1  reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ String a;
            final /* synthetic */ AnonymousClass2 b;

            AnonymousClass1(AnonymousClass2 r5, String str) {
                JniLib.cV(this, r5, str, 1009);
            }

            @Override // java.lang.Runnable
            public void run() {
                this.b.a.m.setFileSize(this.a);
                TextView textView = this.b.a.e;
                textView.setText(this.b.a.e("sobot_file_size") + "\uff1a" + this.b.a.m.getFileSize());
            }
        }
    }

    private void d() {
        SobotProgress a = com.sobot.chat.core.http.f.a.a().a(this.m.getMsgId());
        if (a != null) {
            this.n = a.a(a).a(this.o);
            a(this.n.a);
            return;
        }
        t();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(SobotProgress sobotProgress) {
        int i = sobotProgress.status;
        if (i != 0) {
            if (i == 1) {
                t();
                return;
            } else if (i == 2) {
                a(sobotProgress.fraction, sobotProgress.currentSize, sobotProgress.totalSize);
                return;
            } else if (!(i == 3 || i == 4)) {
                if (i == 5) {
                    u();
                    this.m.setFilePath(sobotProgress.filePath);
                    return;
                }
                return;
            }
        }
        t();
    }

    private void a(float f, long j, long j2) {
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        this.e.setVisibility(8);
        this.f.setVisibility(0);
        this.i.setVisibility(0);
        String formatFileSize = Formatter.formatFileSize(this, j);
        String formatFileSize2 = Formatter.formatFileSize(this, j2);
        TextView textView = this.f;
        textView.setText(this.l + "\u2026(" + formatFileSize + NotificationIconUtil.SPLIT_CHAR + formatFileSize2 + l.t);
        this.j.setProgress((int) (f * 100.0f));
    }

    private void t() {
        this.g.setSelected(false);
        this.g.setText(e("sobot_file_download"));
        this.e.setVisibility(0);
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.h.setVisibility(8);
        this.i.setVisibility(8);
    }

    private void u() {
        this.e.setVisibility(0);
        this.f.setVisibility(8);
        this.g.setText(e("sobot_file_open"));
        this.g.setVisibility(0);
        this.h.setVisibility(0);
        this.i.setVisibility(8);
        this.g.setSelected(true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.k) {
            t();
            c cVar = this.n;
            if (cVar != null) {
                cVar.a(true);
            }
        }
        TextView textView = this.g;
        if (view != textView) {
            return;
        }
        if (textView.isSelected()) {
            SobotCacheFile sobotCacheFile = this.m;
            if (sobotCacheFile != null) {
                File file = new File(sobotCacheFile.getFilePath());
                if (!file.exists()) {
                    t();
                    this.m.setFilePath(null);
                    c cVar2 = this.n;
                    if (cVar2 != null) {
                        cVar2.a(true);
                        return;
                    }
                    return;
                }
                h.k(getApplicationContext(), file);
                return;
            }
            return;
        }
        c cVar3 = this.n;
        if (cVar3 != null) {
            if (cVar3.a.isUpload) {
                this.n.a(true);
            } else {
                this.n.a.request = com.sobot.chat.core.a.a().a(this.m.getUrl(), null);
            }
        }
        this.n = com.sobot.chat.core.a.a().a(this.m.getMsgId(), this.m.getUrl(), this.m.getFileName(), (Map<String, String>) null);
        c cVar4 = this.n;
        if (cVar4 != null) {
            cVar4.a(this.o).b();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        a.a().d("tag_download_act");
        c cVar = this.n;
        if (cVar != null && (cVar.a.status == 5 || this.n.a.status == 0 || this.n.a.status == 4)) {
            a.a().c(this.n.a.tag);
        }
        super.onDestroy();
    }
}
