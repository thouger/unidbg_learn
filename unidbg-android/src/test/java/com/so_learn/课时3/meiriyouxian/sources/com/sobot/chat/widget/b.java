package com.sobot.chat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.TtmlUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.zxing.a.a;
import com.sobot.chat.widget.zxing.e;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: SelectPicPopupWindow */
public class b extends PopupWindow {
    private Button a;
    private Button b;
    private Button c;
    private View d;
    private String e;
    private Context f;
    private String g;
    private LayoutInflater h;
    private e[] i;
    private View.OnClickListener j = new SelectPicPopupWindow$3(this);

    public b(Activity activity, String str, String str2) {
        super(activity);
        this.e = str;
        this.g = str2;
        this.f = activity.getApplicationContext();
        b();
    }

    public b(Activity activity, String str, String str2, boolean z) {
        super(activity);
        this.e = str;
        this.g = str2;
        this.f = activity.getApplicationContext();
        b();
        if (z) {
            new Thread(new AnonymousClass1()).start();
        }
    }

    /* compiled from: SelectPicPopupWindow */
    /* renamed from: com.sobot.chat.widget.b$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = b.this;
            bVar.i = a.a(bVar.e);
            if (b.this.i != null) {
                com.sobot.chat.core.http.a.a((Runnable) new AnonymousClass1());
            }
        }

        /* compiled from: SelectPicPopupWindow */
        /* renamed from: com.sobot.chat.widget.b$1$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.i.length == 1) {
                    m.d("\u56fe\u7247\u4e2d\u4e8c\u7ef4\u7801:" + b.this.i[0].a());
                    b.this.c.setVisibility(0);
                    return;
                }
                m.d("\u56fe\u7247\u4e2d\u6709 " + b.this.i.length + " \u4e2a\u4e8c\u7ef4\u7801");
                b.this.c.setVisibility(8);
            }
        }
    }

    private void b() {
        this.h = (LayoutInflater) this.f.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.d = this.h.inflate(q.a(this.f, TtmlUtils.TAG_LAYOUT, "sobot_clear_history_dialog"), (ViewGroup) null);
        this.a = (Button) this.d.findViewById(q.a(this.f, "id", "sobot_btn_take_photo"));
        this.a.setText(q.f(this.f, "sobot_save_pic"));
        this.b = (Button) this.d.findViewById(q.a(this.f, "id", "sobot_btn_cancel"));
        this.b.setText(q.f(this.f, "sobot_btn_cancle"));
        this.c = (Button) this.d.findViewById(q.a(this.f, "id", "sobot_btn_scan_qr_code"));
        this.c.setText(q.f(this.f, "sobot_scan_qr_code"));
        setContentView(this.d);
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setAnimationStyle(q.a(this.f, "style", "AnimBottom"));
        setBackgroundDrawable(new ColorDrawable(-1342177280));
        this.d.setOnTouchListener(new AnonymousClass2());
        if (!TextUtils.isEmpty(this.e)) {
            this.a.setTextColor(this.f.getResources().getColor(q.a(this.f, "color", "sobot_common_black")));
            this.b.setTextColor(this.f.getResources().getColor(q.a(this.f, "color", "sobot_common_black")));
            this.c.setTextColor(this.f.getResources().getColor(q.a(this.f, "color", "sobot_common_black")));
            this.b.setOnClickListener(this.j);
            this.a.setOnClickListener(this.j);
            this.c.setOnClickListener(this.j);
        }
    }

    /* compiled from: SelectPicPopupWindow */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.b$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnTouchListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int top = b.this.d.findViewById(q.a(b.this.f, "id", "sobot_pop_layout")).getTop();
            int y = (int) motionEvent.getY();
            if (motionEvent.getAction() == 1 && y < top) {
                b.this.dismiss();
            }
            return true;
        }
    }

    private void a(String str) {
        Context context = this.f;
        com.sobot.chat.utils.e.a(context, str, 1000, q.e(context, "sobot_iv_login_right")).show();
    }

    public void a(Context context, Bitmap bitmap) {
        if (!a()) {
            ae.a(context, q.f(context, "sobot_save_err_sd_card"));
        } else if (bitmap == null) {
            ae.a(context, q.f(context, "sobot_save_err_pic"));
        } else {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + "Sobot", "sobot_pic");
            if (!file.exists()) {
                file.mkdirs();
            }
            String str = System.currentTimeMillis() + ".jpg";
            File file2 = new File(file, str);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                ae.a(context, q.f(context, "sobot_save_error_file"));
                e.printStackTrace();
            } catch (IOException e2) {
                ae.a(context, q.f(context, "sobot_save_err"));
                e2.printStackTrace();
            } catch (Exception e3) {
                ae.a(context, q.f(context, "sobot_save_err"));
                e3.printStackTrace();
            }
            a(file2, str);
        }
    }

    public boolean a() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public void a(Context context, String str) {
        if (!a()) {
            ae.a(context, q.f(context, "sobot_save_err_sd_card"));
        } else if (TextUtils.isEmpty(str)) {
            ae.a(context, q.f(context, "sobot_save_err_pic"));
        } else {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + "Sobot", "sobot_pic");
            if (!file.exists()) {
                file.mkdirs();
            }
            String str2 = System.currentTimeMillis() + ".gif";
            File file2 = new File(file, str2);
            if (a(new File(str), file2)) {
                a(file2, str2);
            }
        }
    }

    public void a(File file, String str) {
        if (file != null) {
            try {
                if (file.exists() && !TextUtils.isEmpty(str)) {
                    MediaStore.Images.Media.insertImage(this.f.getContentResolver(), file.getAbsolutePath(), str, (String) null);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        this.f.sendBroadcast(intent);
        a(q.f(this.f, "sobot_already_save_to_picture") + "\n" + file.getAbsolutePath());
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0085 A[SYNTHETIC, Splitter:B:50:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x008d A[Catch:{ IOException -> 0x0089 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0092 A[Catch:{ IOException -> 0x0089 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0097 A[Catch:{ IOException -> 0x0089 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.io.File r10, java.io.File r11) {
        /*
            r9 = this;
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0050, all -> 0x004b }
            r1.<init>(r10)     // Catch:{ IOException -> 0x0050, all -> 0x004b }
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0046, all -> 0x0041 }
            r10.<init>(r11)     // Catch:{ IOException -> 0x0046, all -> 0x0041 }
            java.nio.channels.FileChannel r11 = r1.getChannel()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.nio.channels.FileChannel r0 = r10.getChannel()     // Catch:{ IOException -> 0x0036, all -> 0x0030 }
            r3 = 0
            long r5 = r11.size()     // Catch:{ IOException -> 0x0036, all -> 0x0030 }
            r2 = r11
            r7 = r0
            r2.transferTo(r3, r5, r7)     // Catch:{ IOException -> 0x0036, all -> 0x0030 }
            r1.close()     // Catch:{ IOException -> 0x006b }
            if (r11 == 0) goto L_0x0026
            r11.close()     // Catch:{ IOException -> 0x006b }
        L_0x0026:
            r10.close()     // Catch:{ IOException -> 0x006b }
            if (r0 == 0) goto L_0x0080
            r0.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x0080
        L_0x0030:
            r2 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x0083
        L_0x0036:
            r2 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x0054
        L_0x003b:
            r2 = move-exception
            r11 = r0
            goto L_0x0044
        L_0x003e:
            r2 = move-exception
            r11 = r0
            goto L_0x0049
        L_0x0041:
            r2 = move-exception
            r10 = r0
            r11 = r10
        L_0x0044:
            r0 = r1
            goto L_0x004e
        L_0x0046:
            r2 = move-exception
            r10 = r0
            r11 = r10
        L_0x0049:
            r0 = r1
            goto L_0x0053
        L_0x004b:
            r2 = move-exception
            r10 = r0
            r11 = r10
        L_0x004e:
            r1 = r11
            goto L_0x0083
        L_0x0050:
            r2 = move-exception
            r10 = r0
            r11 = r10
        L_0x0053:
            r1 = r11
        L_0x0054:
            android.content.Context r3 = r9.f     // Catch:{ all -> 0x0082 }
            android.content.Context r4 = r9.f     // Catch:{ all -> 0x0082 }
            java.lang.String r5 = "sobot_save_err"
            java.lang.String r4 = com.sobot.chat.utils.q.f(r4, r5)     // Catch:{ all -> 0x0082 }
            com.sobot.chat.utils.ae.a(r3, r4)     // Catch:{ all -> 0x0082 }
            r2.printStackTrace()     // Catch:{ all -> 0x0082 }
            if (r0 == 0) goto L_0x006d
            r0.close()
            goto L_0x006d
        L_0x006b:
            r10 = move-exception
            goto L_0x007d
        L_0x006d:
            if (r11 == 0) goto L_0x0072
            r11.close()
        L_0x0072:
            if (r10 == 0) goto L_0x0077
            r10.close()
        L_0x0077:
            if (r1 == 0) goto L_0x0080
            r1.close()
            goto L_0x0080
        L_0x007d:
            r10.printStackTrace()
        L_0x0080:
            r10 = 1
            return r10
        L_0x0082:
            r2 = move-exception
        L_0x0083:
            if (r0 == 0) goto L_0x008b
            r0.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x008b
        L_0x0089:
            r10 = move-exception
            goto L_0x009b
        L_0x008b:
            if (r11 == 0) goto L_0x0090
            r11.close()     // Catch:{ IOException -> 0x0089 }
        L_0x0090:
            if (r10 == 0) goto L_0x0095
            r10.close()     // Catch:{ IOException -> 0x0089 }
        L_0x0095:
            if (r1 == 0) goto L_0x009e
            r1.close()     // Catch:{ IOException -> 0x0089 }
            goto L_0x009e
        L_0x009b:
            r10.printStackTrace()
        L_0x009e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.widget.b.a(java.io.File, java.io.File):boolean");
    }
}
