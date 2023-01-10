package com.sobot.chat.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.adapter.c;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.h;
import com.sobot.chat.utils.q;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SobotChooseFileActivity extends SobotBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView a;
    private TextView d;
    private TextView e;
    private File f = Environment.getExternalStorageDirectory();
    private File g;
    private c h;
    private List<File> i = new ArrayList();

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_choose_file");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        if (Build.VERSION.SDK_INT >= 29 && !Environment.isExternalStorageLegacy()) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 42);
        }
        setTitle(e("sobot_internal_memory"));
        b(b("sobot_btn_back_selector"), "", true);
        this.a = (ListView) findViewById(a("sobot_lv_files"));
        this.d = (TextView) findViewById(a("sobot_tv_send"));
        this.d.setText(q.f(this, "sobot_button_send"));
        this.e = (TextView) findViewById(a("sobot_tv_total"));
        this.d.setOnClickListener(this);
        displayInNotch(this.a);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        if (o() && d.a()) {
            this.g = this.f;
            a(this.g);
            this.a.setOnItemClickListener(this);
        }
    }

    private void a(File file) {
        if (file.isDirectory()) {
            a(b(file));
        }
    }

    private void a(File[] fileArr) {
        this.i.clear();
        if (fileArr != null) {
            this.i.addAll(Arrays.asList(fileArr));
        }
        Collections.sort(this.i, new AnonymousClass1(this));
        c cVar = this.h;
        if (cVar == null) {
            this.h = new c(this, this.i);
            this.a.setAdapter((ListAdapter) this.h);
            return;
        }
        cVar.notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotChooseFileActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements Comparator<File> {
        final /* synthetic */ SobotChooseFileActivity a;

        AnonymousClass1(SobotChooseFileActivity sobotChooseFileActivity) {
            JniLib.cV(this, sobotChooseFileActivity, 999);
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            if (file.isDirectory() && file2.isFile()) {
                return -1;
            }
            if (!file.isFile() || !file2.isDirectory()) {
                return file2.getName().compareTo(file.getName());
            }
            return 1;
        }
    }

    private File[] b(File file) {
        if (file.isDirectory()) {
            return file.listFiles();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(View view) {
        d();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        d();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str;
        try {
            File file = this.i.get(i);
            if (file == null) {
                return;
            }
            if (file.isDirectory()) {
                this.g = file;
                a(file);
            } else if (file.length() > 52428800) {
                ae.a(this, e("sobot_file_upload_failed"));
            } else if (!h.a(file.getName().toLowerCase(), this, "sobot_fileEndingAll") && this.h != null) {
                if (this.h.a(file)) {
                    this.h.b(null);
                    str = "0B";
                    this.d.setEnabled(false);
                } else {
                    this.h.b(file);
                    str = Formatter.formatFileSize(this, file.length());
                    this.d.setEnabled(true);
                }
                this.h.notifyDataSetChanged();
                TextView textView = this.e;
                textView.setText(e("sobot_files_selected") + "\uff1a" + str);
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        File a;
        if (view == this.d && (a = this.h.a()) != null) {
            Intent intent = new Intent();
            intent.putExtra("sobot_intent_data_selected_file", a);
            setResult(107, intent);
            finish();
        }
    }

    private void d() {
        if (!this.f.equals(this.g)) {
            this.g = this.g.getParentFile();
            a(this.g);
            return;
        }
        super.onBackPressed();
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri data;
        if (i != 42 || i2 != -1 || intent == null || (data = intent.getData()) == null) {
            finish();
            return;
        }
        Intent intent2 = new Intent();
        intent2.setData(data);
        setResult(107, intent2);
        finish();
    }
}
