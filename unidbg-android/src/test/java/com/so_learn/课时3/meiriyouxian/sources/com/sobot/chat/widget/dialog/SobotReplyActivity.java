package com.sobot.chat.widget.dialog;

import android.app.Activity;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.SobotPhotoActivity;
import com.sobot.chat.activity.SobotVideoActivity;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.activity.base.SobotDialogBaseActivity;
import com.sobot.chat.adapter.g;
import com.sobot.chat.api.ResultCallBack;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.model.SobotCacheFile;
import com.sobot.chat.api.model.SobotUserTicketInfo;
import com.sobot.chat.api.model.ZhiChiMessage;
import com.sobot.chat.api.model.ZhiChiUploadAppFileModelResult;
import com.sobot.chat.application.MyApplication;
import com.sobot.chat.b;
import com.sobot.chat.b.a;
import com.sobot.chat.camera.c.f;
import com.sobot.chat.listener.d;
import com.sobot.chat.utils.ac;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.e;
import com.sobot.chat.utils.l;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.n;
import com.sobot.chat.utils.o;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SobotReplyActivity extends SobotDialogBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    protected c a;
    public d d;
    protected File e;
    private TextView f;
    private LinearLayout g;
    private EditText h;
    private GridView i;
    private Button j;
    private ArrayList<ZhiChiUploadAppFileModelResult> k = new ArrayList<>();
    private g l;
    private h m;
    private String n = "";
    private String o = "";
    private SobotUserTicketInfo p;
    private View.OnClickListener q = new AnonymousClass4(this);
    private c.a r = new AnonymousClass5(this);

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return q.a(this, "sobot_layout_dialog_reply");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        this.f = (TextView) findViewById(a("sobot_tv_title"));
        this.f.setText(e("sobot_reply"));
        this.g = (LinearLayout) findViewById(a("sobot_negativeButton"));
        this.h = (EditText) findViewById(a("sobot_reply_edit"));
        this.h.setHint(q.f(this, "sobot_please_input_reply_hint"));
        this.i = (GridView) findViewById(a("sobot_reply_msg_pic"));
        this.j = (Button) findViewById(a("sobot_btn_submit"));
        this.j.setText(q.f(this, "sobot_btn_submit_text"));
        List list = (List) getIntent().getSerializableExtra("picTempList");
        String stringExtra = getIntent().getStringExtra("replyTempContent");
        if (!ac.a((Object) stringExtra)) {
            this.h.setText(stringExtra);
        }
        if (list != null && list.size() > 0) {
            this.k.addAll(list);
        }
        this.g.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.l = new g(this, this.k);
        this.i.setAdapter((ListAdapter) this.l);
        u();
        this.n = getIntent().getStringExtra("uid");
        this.o = getIntent().getStringExtra("companyId");
        this.p = (SobotUserTicketInfo) getIntent().getSerializableExtra("ticketInfo");
        if (b.a(1) && b.a(4)) {
            com.sobot.chat.b.b.a().a(this, new AnonymousClass1(this));
        }
        displayInNotch(this.i);
    }

    /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$1  reason: invalid class name */
    class AnonymousClass1 implements a.AbstractC0139a {
        final /* synthetic */ SobotReplyActivity a;

        AnonymousClass1(SobotReplyActivity sobotReplyActivity) {
            JniLib.cV(this, sobotReplyActivity, Integer.valueOf((int) Process.WEBVIEW_ZYGOTE_UID));
        }

        @Override // com.sobot.chat.b.a.AbstractC0139a
        public void a(a.b bVar) {
            if (bVar.a) {
                for (Rect rect : bVar.b) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, r.a((Context) this.a, 104.0f));
                    layoutParams.setMargins(rect.right + r.a((Context) this.a, 20.0f), r.a((Context) this.a, 20.0f), r.a((Context) this.a, 20.0f), r.a((Context) this.a, 20.0f));
                    this.a.h.setLayoutParams(layoutParams);
                }
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        InputMethodManager inputMethodManager;
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (a(currentFocus, motionEvent) && (inputMethodManager = (InputMethodManager) t().getSystemService(Context.INPUT_METHOD_SERVICE)) != null) {
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
            return super.dispatchTouchEvent(motionEvent);
        } else if (getWindow().superDispatchTouchEvent(motionEvent)) {
            return true;
        } else {
            return onTouchEvent(motionEvent);
        }
    }

    @Override // com.sobot.chat.activity.base.SobotDialogBaseActivity, android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && motionEvent.getY() <= 0.0f) {
            Intent intent = new Intent();
            intent.putExtra("replyTempContent", this.h.getText().toString());
            intent.putExtra("picTempList", this.k);
            intent.putExtra("isTemp", true);
            setResult(-1, intent);
            finish();
        }
        return true;
    }

    public boolean a(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int height = view.getHeight() + i2;
        int width = view.getWidth() + i;
        if (motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) width) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) height)) {
            return true;
        }
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LinearLayout linearLayout = this.g;
        if (view == linearLayout) {
            com.sobot.chat.widget.kpswitch.util.c.b(linearLayout);
            Intent intent = new Intent();
            intent.putExtra("replyTempContent", this.h.getText().toString());
            intent.putExtra("picTempList", this.k);
            intent.putExtra("isTemp", true);
            setResult(-1, intent);
            finish();
        }
        Button button = this.j;
        if (view == button) {
            com.sobot.chat.widget.kpswitch.util.c.b(button);
            if (ac.a((Object) this.h.getText().toString().trim())) {
                Toast.makeText(t(), q.f(t(), "sobot_please_input_reply_no_empty"), 0).show();
            } else if (com.sobot.chat.utils.g.a()) {
                d.a(this);
                this.b.replyTicketContent(this, this.n, this.p.getTicketId(), this.h.getText().toString(), d(), this.o, new AnonymousClass2(this));
            }
        }
    }

    /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$2  reason: invalid class name */
    class AnonymousClass2 implements com.sobot.chat.core.http.c.a<String> {
        final /* synthetic */ SobotReplyActivity a;

        AnonymousClass2(SobotReplyActivity sobotReplyActivity) {
            JniLib.cV(this, sobotReplyActivity, 1052);
        }

        public void a(String str) {
            m.c(str);
            SobotReplyActivity sobotReplyActivity = this.a;
            e.a(sobotReplyActivity, q.f(sobotReplyActivity, "sobot_leavemsg_success_tip"), 1000, q.e(this.a, "sobot_iv_login_right")).show();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.a.k.clear();
            Intent intent = new Intent();
            intent.putExtra("replyTempContent", "");
            intent.putExtra("picTempList", this.a.k);
            intent.putExtra("isTemp", false);
            this.a.setResult(-1, intent);
            d.b(this.a);
            this.a.finish();
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            SobotReplyActivity sobotReplyActivity = this.a;
            ae.c(sobotReplyActivity, q.f(sobotReplyActivity, "sobot_leavemsg_error_tip"));
            exc.printStackTrace();
            d.b(this.a);
        }
    }

    private void u() {
        this.l.a(new AnonymousClass3(this));
        this.l.a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements g.b {
        final /* synthetic */ SobotReplyActivity a;

        AnonymousClass3(SobotReplyActivity sobotReplyActivity) {
            JniLib.cV(this, sobotReplyActivity, 1054);
        }

        @Override // com.sobot.chat.adapter.g.b
        public void a(View view, int i, int i2) {
            ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult;
            com.sobot.chat.widget.kpswitch.util.c.b(view);
            if (i2 == 0) {
                SobotReplyActivity sobotReplyActivity = this.a;
                sobotReplyActivity.m = new h(sobotReplyActivity, sobotReplyActivity.q);
                this.a.m.show();
            } else if (i2 == 1) {
                m.d("\u5f53\u524d\u9009\u62e9\u56fe\u7247\u4f4d\u7f6e\uff1a" + i);
                if (this.a.l != null && this.a.l.b() != null && (zhiChiUploadAppFileModelResult = this.a.l.b().get(i)) != null) {
                    if (TextUtils.isEmpty(zhiChiUploadAppFileModelResult.getFileLocalPath()) || !o.b(zhiChiUploadAppFileModelResult.getFileLocalPath())) {
                        Intent intent = new Intent(this.a, SobotPhotoActivity.class);
                        intent.putExtra("imageUrL", TextUtils.isEmpty(zhiChiUploadAppFileModelResult.getFileLocalPath()) ? zhiChiUploadAppFileModelResult.getFileUrl() : zhiChiUploadAppFileModelResult.getFileLocalPath());
                        this.a.startActivity(intent);
                        return;
                    }
                    File file = new File(zhiChiUploadAppFileModelResult.getFileLocalPath());
                    SobotCacheFile sobotCacheFile = new SobotCacheFile();
                    sobotCacheFile.setFileName(file.getName());
                    sobotCacheFile.setUrl(zhiChiUploadAppFileModelResult.getFileUrl());
                    sobotCacheFile.setFilePath(zhiChiUploadAppFileModelResult.getFileLocalPath());
                    sobotCacheFile.setFileType(com.sobot.chat.widget.attachment.a.a(f.b(zhiChiUploadAppFileModelResult.getFileLocalPath())));
                    sobotCacheFile.setMsgId("" + System.currentTimeMillis());
                    this.a.startActivity(SobotVideoActivity.a(this.a, sobotCacheFile));
                }
            } else if (i2 == 2) {
                String f = q.f(this.a, "sobot_do_you_delete_picture");
                if (this.a.l != null && this.a.l.b() != null) {
                    ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult2 = this.a.l.b().get(i);
                    if (zhiChiUploadAppFileModelResult2 != null && !TextUtils.isEmpty(zhiChiUploadAppFileModelResult2.getFileLocalPath()) && o.b(zhiChiUploadAppFileModelResult2.getFileLocalPath())) {
                        f = q.f(this.a, "sobot_do_you_delete_video");
                    }
                    if (this.a.a != null) {
                        this.a.a.dismiss();
                        this.a.a = null;
                    }
                    if (this.a.a == null) {
                        SobotReplyActivity sobotReplyActivity2 = this.a;
                        sobotReplyActivity2.a = new c(sobotReplyActivity2, f, new AnonymousClass1(this));
                    }
                    this.a.a.a(i);
                    this.a.a.show();
                }
            }
        }

        /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$3$1  reason: invalid class name */
        class AnonymousClass1 implements View.OnClickListener {
            final /* synthetic */ AnonymousClass3 a;

            AnonymousClass1(AnonymousClass3 r5) {
                JniLib.cV(this, r5, 1053);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.a.a.a.dismiss();
                if (view.getId() == this.a.a.a("btn_pick_photo")) {
                    Log.e("onClick: ", this.a.a.a.a() + "");
                    this.a.a.k.remove(this.a.a.a.a());
                    this.a.a.l.a();
                }
            }
        }
    }

    /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$4  reason: invalid class name */
    class AnonymousClass4 implements View.OnClickListener {
        final /* synthetic */ SobotReplyActivity a;

        AnonymousClass4(SobotReplyActivity sobotReplyActivity) {
            JniLib.cV(this, sobotReplyActivity, 1055);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.m.dismiss();
            if (view.getId() == this.a.a("btn_take_photo")) {
                m.d("\u62cd\u7167");
                if (!com.sobot.chat.utils.d.a()) {
                    SobotReplyActivity sobotReplyActivity = this.a;
                    ae.a(sobotReplyActivity, sobotReplyActivity.e("sobot_sdcard_does_not_exist"), 0);
                    return;
                }
                this.a.d = new AnonymousClass1();
                if (this.a.p()) {
                    SobotReplyActivity sobotReplyActivity2 = this.a;
                    sobotReplyActivity2.e = c.b((Activity) sobotReplyActivity2);
                } else {
                    return;
                }
            }
            if (view.getId() == this.a.a("btn_pick_photo")) {
                m.d("\u9009\u62e9\u7167\u7247");
                this.a.d = new AnonymousClass2();
                if (this.a.o()) {
                    c.a((Activity) this.a);
                } else {
                    return;
                }
            }
            if (view.getId() == this.a.a("btn_pick_vedio")) {
                m.d("\u9009\u62e9\u89c6\u9891");
                this.a.d = new AnonymousClass3();
                if (this.a.o()) {
                    c.b(this.a, (Fragment) null);
                }
            }
        }

        /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$4$1  reason: invalid class name */
        class AnonymousClass1 extends com.sobot.chat.listener.e {
            AnonymousClass1() {
            }

            @Override // com.sobot.chat.listener.e, com.sobot.chat.listener.d
            public void a() {
                if (SobotBaseActivity.r()) {
                    AnonymousClass4.this.a.e = c.b((Activity) AnonymousClass4.this.a);
                }
            }
        }

        /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$4$2  reason: invalid class name */
        class AnonymousClass2 extends com.sobot.chat.listener.e {
            AnonymousClass2() {
            }

            @Override // com.sobot.chat.listener.e, com.sobot.chat.listener.d
            public void a() {
                c.a((Activity) AnonymousClass4.this.a);
            }
        }

        /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$4$3  reason: invalid class name */
        class AnonymousClass3 extends com.sobot.chat.listener.e {
            AnonymousClass3() {
            }

            @Override // com.sobot.chat.listener.e, com.sobot.chat.listener.d
            public void a() {
                c.b(AnonymousClass4.this.a, (Fragment) null);
            }
        }
    }

    public void a(ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult) {
        this.l.a(zhiChiUploadAppFileModelResult);
    }

    public String d() {
        ArrayList<ZhiChiUploadAppFileModelResult> b = this.l.b();
        String str = "";
        for (int i = 0; i < b.size(); i++) {
            str = str + b.get(i).getFileUrl() + ";";
        }
        return str;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        com.sobot.chat.core.http.a.a().a(this);
        MyApplication.getInstance().deleteActivity(this);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 701) {
            if (intent == null || intent.getData() == null) {
                g(e("sobot_did_not_get_picture_path"));
                return;
            }
            Uri data = intent.getData();
            if (data == null) {
                data = l.a(intent, this);
            }
            String a = l.a(this, data);
            if (o.b(a)) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(this, data);
                    mediaPlayer.prepare();
                    if (mediaPlayer.getDuration() / 1000 > 15) {
                        ae.a(this, e("sobot_upload_vodie_length"));
                        return;
                    }
                    d.a(this);
                    String a2 = n.a(a);
                    try {
                        this.r.a(f.a(this, data, a2 + f.b(a), a));
                    } catch (Exception e) {
                        e.printStackTrace();
                        ae.a(this, q.f(this, "sobot_pic_type_error"));
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } else {
                d.a(this);
                c.a((Context) this, data, this.r, false);
            }
        } else if (i == 702) {
            File file = this.e;
            if (file == null || !file.exists()) {
                g(e("sobot_pic_select_again"));
                return;
            }
            d.a(this);
            c.a((Context) this, this.e.getAbsolutePath(), this.r, true);
        }
    }

    public void g(String str) {
        e.a(this, str, 1000).show();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$5  reason: invalid class name */
    public class AnonymousClass5 implements c.a {
        final /* synthetic */ SobotReplyActivity a;

        AnonymousClass5(SobotReplyActivity sobotReplyActivity) {
            JniLib.cV(this, sobotReplyActivity, 1057);
        }

        @Override // com.sobot.chat.utils.c.a
        public void a(String str) {
            ZhiChiApi zhiChiApi = this.a.b;
            SobotReplyActivity sobotReplyActivity = this.a;
            zhiChiApi.fileUploadForPostMsg(sobotReplyActivity, sobotReplyActivity.o, this.a.n, str, new AnonymousClass1(this, str));
        }

        /* renamed from: com.sobot.chat.widget.dialog.SobotReplyActivity$5$1  reason: invalid class name */
        class AnonymousClass1 implements ResultCallBack<ZhiChiMessage> {
            final /* synthetic */ String a;
            final /* synthetic */ AnonymousClass5 b;

            AnonymousClass1(AnonymousClass5 r5, String str) {
                JniLib.cV(this, r5, str, Integer.valueOf((int) BluetoothClass.Device.AUDIO_VIDEO_CAR_AUDIO));
            }

            @Override // com.sobot.chat.api.ResultCallBack
            public void onLoading(long j, long j2, boolean z) {
            }

            /* renamed from: a */
            public void onSuccess(ZhiChiMessage zhiChiMessage) {
                d.b(this.b.a);
                if (zhiChiMessage.getData() != null) {
                    ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult = new ZhiChiUploadAppFileModelResult();
                    zhiChiUploadAppFileModelResult.setFileUrl(zhiChiMessage.getData().getUrl());
                    zhiChiUploadAppFileModelResult.setFileLocalPath(this.a);
                    zhiChiUploadAppFileModelResult.setViewState(1);
                    this.b.a.a(zhiChiUploadAppFileModelResult);
                }
            }

            @Override // com.sobot.chat.api.ResultCallBack
            public void onFailure(Exception exc, String str) {
                d.b(this.b.a);
                this.b.a.g(q.f(this.b.a, "sobot_net_work_err"));
            }
        }

        @Override // com.sobot.chat.utils.c.a
        public void a() {
            d.b(this.a);
        }
    }
}
