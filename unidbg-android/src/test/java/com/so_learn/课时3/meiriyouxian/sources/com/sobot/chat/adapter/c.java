package com.sobot.chat.adapter;

import android.content.Context;
import android.media.TtmlUtils;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sobot.chat.utils.f;
import com.sobot.chat.utils.q;
import java.io.File;
import java.util.List;

/* compiled from: SobotFilesAdapter */
public class c extends com.sobot.chat.adapter.base.a<File> {
    private static final String[] e = {"sobot_choose_file_item", "sobot_choose_dir_item"};
    private Context a;
    private File d;

    public c(Context context, List list) {
        super(context, list);
        this.a = context;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        File file = (File) this.b.get(i);
        if (file == null) {
            return view;
        }
        View a2 = a(view, getItemViewType(i), i, file);
        ((a) a2.getTag()).a(file);
        return a2;
    }

    private View a(View view, int i, int i2, File file) {
        Object obj;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, e[i]), (ViewGroup) null);
            if (i == 0) {
                obj = new C0138c(this.c, view);
            } else if (i != 1) {
                obj = new C0138c(this.c, view);
            } else {
                obj = new b(this.c, view);
            }
            view.setTag(obj);
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        String[] strArr = e;
        if (strArr.length > 0) {
            return strArr.length;
        }
        return super.getViewTypeCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return ((File) getItem(i)).isDirectory() ? 1 : 0;
    }

    public boolean a(File file) {
        File file2 = this.d;
        return file2 != null && file2.equals(file);
    }

    public void b(File file) {
        this.d = file;
    }

    public File a() {
        return this.d;
    }

    /* compiled from: SobotFilesAdapter */
    static abstract class a {
        Context a;

        /* access modifiers changed from: package-private */
        public abstract void a(File file);

        a(Context context, View view) {
            this.a = context;
        }
    }

    /* compiled from: SobotFilesAdapter */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.adapter.c$c  reason: collision with other inner class name */
    public class C0138c extends a {
        private TextView c;
        private TextView d;
        private TextView e;

        C0138c(Context context, View view) {
            super(context, view);
            this.d = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_descripe"));
            this.e = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_name"));
            this.c = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_radioBtn"));
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.adapter.c.a
        public void a(File file) {
            this.c.setSelected(c.this.d != null && c.this.d.equals(file));
            this.d.setText(f.a(file.lastModified(), f.b) + "  " + Formatter.formatFileSize(this.a, file.length()));
            this.e.setText(file.getName());
        }
    }

    /* compiled from: SobotFilesAdapter */
    /* access modifiers changed from: package-private */
    public static class b extends a {
        private TextView b;

        b(Context context, View view) {
            super(context, view);
            this.b = (TextView) view.findViewById(q.a(context, "id", "sobot_tv_name"));
        }

        /* access modifiers changed from: package-private */
        @Override // com.sobot.chat.adapter.c.a
        public void a(File file) {
            this.b.setText(file.getName());
        }
    }
}
