package com.sobot.chat.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.android.internal.telephony.PhoneConstants;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.application.MyApplication;
import com.sobot.chat.core.a;
import com.sobot.chat.utils.l;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.t;
import com.sobot.chat.widget.gif.GifView2;
import com.sobot.chat.widget.photoview.PhotoView;
import com.sobot.chat.widget.photoview.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class SobotPhotoActivity extends Activity implements View.OnLongClickListener {
    String a;
    Bitmap b;
    boolean c;
    String d;
    private PhotoView e;
    private b f;
    private GifView2 g;
    private RelativeLayout h;
    private com.sobot.chat.widget.b i;
    private View.OnLongClickListener j = new AnonymousClass4(this);

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, 1017);
    }

    /* renamed from: com.sobot.chat.activity.SobotPhotoActivity$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ SobotPhotoActivity a;

        AnonymousClass1(SobotPhotoActivity sobotPhotoActivity) {
            JniLib.cV(this, sobotPhotoActivity, 1012);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.finish();
        }
    }

    /* renamed from: com.sobot.chat.activity.SobotPhotoActivity$2  reason: invalid class name */
    class AnonymousClass2 implements GifView2.a {
        final /* synthetic */ SobotPhotoActivity a;

        AnonymousClass2(SobotPhotoActivity sobotPhotoActivity) {
            JniLib.cV(this, sobotPhotoActivity, 1013);
        }

        @Override // com.sobot.chat.widget.gif.GifView2.a
        public void a(String str) {
            this.a.a(str);
        }
    }

    private void a(Bundle bundle) {
        if (bundle == null) {
            this.a = getIntent().getStringExtra("imageUrL");
            this.c = getIntent().getBooleanExtra("isRight", false);
            return;
        }
        this.a = bundle.getString("imageUrL");
        this.c = bundle.getBoolean("isRight");
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (!TextUtils.isEmpty(this.a) && ((this.a.endsWith(".gif") || this.a.endsWith(".GIF")) && this.c)) {
            b(str);
        } else if (TextUtils.isEmpty(this.a) || (!this.a.endsWith(".gif") && !this.a.endsWith(".GIF"))) {
            this.b = t.a(str, getApplicationContext(), true);
            try {
                int a = l.a(str);
                if (a > 0) {
                    this.b = l.a(this.b, a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.e.setImageBitmap(this.b);
            this.f = new b(this.e);
            this.f.a(new AnonymousClass3(this));
            this.f.i();
            this.e.setVisibility(0);
            this.f.a(this);
        } else {
            b(str);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotPhotoActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements b.d {
        final /* synthetic */ SobotPhotoActivity a;

        AnonymousClass3(SobotPhotoActivity sobotPhotoActivity) {
            JniLib.cV(this, sobotPhotoActivity, 1014);
        }

        @Override // com.sobot.chat.widget.photoview.b.d
        public void a(View view, float f, float f2) {
            m.d("\u70b9\u51fb\u56fe\u7247\u7684\u65f6\u95f4\uff1a" + view + " x:" + f + "  y:" + f2);
            this.a.finish();
        }
    }

    private void b(String str) {
        int i;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            this.b = BitmapFactory.decodeFile(str);
            this.g.a(fileInputStream, this.a);
            int a = r.a((Activity) this);
            int b = r.b(this);
            int a2 = r.a((Context) this, this.b.getWidth());
            int a3 = r.a((Context) this, this.b.getHeight());
            if (a2 != a3) {
                if (a2 > a) {
                    a2 = a;
                    i = (int) (((float) a3) * ((((float) a) * 1.0f) / ((float) a2)));
                } else {
                    i = a3;
                }
                if (i > b) {
                    a = (int) (((float) a2) * ((((float) b) * 1.0f) / ((float) i)));
                } else {
                    b = i;
                    a = a2;
                }
            } else if (a2 > a) {
                b = a;
            } else {
                a = a2;
                b = a3;
            }
            m.d("bitmap" + a + PhoneConstants.APN_TYPE_ALL + b);
            this.g.setLayoutParams(new RelativeLayout.LayoutParams(a, b));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.h.setVisibility(0);
        this.h.setOnLongClickListener(this.j);
        this.g.setOnLongClickListener(this.j);
    }

    /* renamed from: com.sobot.chat.activity.SobotPhotoActivity$4  reason: invalid class name */
    class AnonymousClass4 implements View.OnLongClickListener {
        final /* synthetic */ SobotPhotoActivity a;

        AnonymousClass4(SobotPhotoActivity sobotPhotoActivity) {
            JniLib.cV(this, sobotPhotoActivity, 1015);
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (!TextUtils.isEmpty(this.a.d) && new File(this.a.d).exists()) {
                SobotPhotoActivity sobotPhotoActivity = this.a;
                sobotPhotoActivity.i = new com.sobot.chat.widget.b(sobotPhotoActivity, sobotPhotoActivity.d, "gif");
                try {
                    this.a.i.showAtLocation(this.a.h, 81, 0, 0);
                } catch (Exception unused) {
                    this.a.i = null;
                }
            }
            return false;
        }
    }

    public void a(String str, File file, GifView2 gifView2) {
        a.a().a(str, file, (Map<String, String>) null, new AnonymousClass5(this));
    }

    /* renamed from: com.sobot.chat.activity.SobotPhotoActivity$5  reason: invalid class name */
    class AnonymousClass5 implements a.AbstractC0142a {
        final /* synthetic */ SobotPhotoActivity a;

        AnonymousClass5(SobotPhotoActivity sobotPhotoActivity) {
            JniLib.cV(this, sobotPhotoActivity, 1016);
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(File file) {
            m.d("down load onSuccess gif" + file.getAbsolutePath());
            this.a.a(file.getAbsolutePath());
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(Exception exc, String str, int i) {
            m.b("\u56fe\u7247\u4e0b\u8f7d\u5931\u8d25:" + str, exc);
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(int i) {
            m.d("\u56fe\u7247\u4e0b\u8f7d\u8fdb\u5ea6:" + i);
        }
    }

    public File a(Context context, String str) {
        if (a()) {
            return context.getExternalFilesDir(str);
        }
        return context.getFilesDir();
    }

    public File a(Context context) {
        return a(context, "images");
    }

    public boolean a() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        this.g.a();
        Bitmap bitmap = this.b;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.b.recycle();
            System.gc();
        }
        com.sobot.chat.widget.b bVar = this.i;
        if (bVar != null && bVar.isShowing()) {
            try {
                this.i.dismiss();
            } catch (Exception unused) {
            }
            this.i = null;
        }
        MyApplication.getInstance().deleteActivity(this);
        super.onDestroy();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        if (!TextUtils.isEmpty(this.d) && new File(this.d).exists()) {
            this.i = new com.sobot.chat.widget.b(this, this.d, "jpg/png", true);
            try {
                this.i.showAtLocation(this.h, 81, 0, 0);
            } catch (Exception unused) {
                this.i = null;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("imageUrL", this.a);
        bundle.putBoolean("isRight", this.c);
        super.onSaveInstanceState(bundle);
    }
}
