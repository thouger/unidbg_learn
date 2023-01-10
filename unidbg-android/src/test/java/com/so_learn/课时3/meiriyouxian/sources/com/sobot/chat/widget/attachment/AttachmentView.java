package com.sobot.chat.widget.attachment;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotFileModel;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;

public class AttachmentView extends FrameLayout {
    private int a;
    private SobotFileModel b;
    private Context c;
    private View d;
    private String e;
    private RelativeLayout f;
    private TextView g;
    private String h;
    private ImageView i;
    private TextView j;
    private ImageView k;
    private a l;
    private int m;

    public interface a {
        void a(SobotFileModel sobotFileModel, int i);

        void a(String str, String str2, int i);

        void b(SobotFileModel sobotFileModel, int i);
    }

    public AttachmentView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.c = context;
        this.d = inflate(context, q.a(context, "layout_attachment_view"), this);
        this.f = (RelativeLayout) findViewById(q.g(context, "sobot_attachment_root_view"));
        this.g = (TextView) findViewById(q.g(context, "sobot_file_name"));
        this.i = (ImageView) findViewById(q.g(context, "sobot_file_type_icon"));
        this.j = (TextView) findViewById(q.g(context, "sobot_file_download"));
        this.j.setText(q.f(context, "sobot_preview_see"));
        this.d.setOnClickListener(new AnonymousClass1());
        this.k = (ImageView) findViewById(q.g(context, "sobot_file_image_view"));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.attachment.AttachmentView$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (AttachmentView.this.l != null) {
                if (AttachmentView.this.m == 18) {
                    AttachmentView.this.l.b(AttachmentView.this.b, AttachmentView.this.a);
                } else if (AttachmentView.this.m == 1) {
                    AttachmentView.this.l.a(AttachmentView.this.e, AttachmentView.this.h, AttachmentView.this.a);
                } else {
                    AttachmentView.this.l.a(AttachmentView.this.b, AttachmentView.this.a);
                }
            }
        }
    }

    public void setFileName(CharSequence charSequence) {
        this.h = charSequence.toString();
        TextView textView = this.g;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setFileUrl(String str) {
        this.e = str;
    }

    public void setFileTypeIcon(int i) {
        this.m = i;
        if (this.i != null) {
            if (i == 1) {
                this.k.setVisibility(0);
                this.f.setVisibility(8);
                t.a(this.c, this.e, this.k);
                return;
            }
            this.k.setVisibility(8);
            this.f.setVisibility(0);
            this.i.setImageResource(a.a(this.c, i));
        }
    }

    public void setFileModel(SobotFileModel sobotFileModel) {
        this.b = sobotFileModel;
    }

    public void setListener(a aVar) {
        this.l = aVar;
    }

    public void setFileNameColor(int i) {
        TextView textView = this.g;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setPosition(int i) {
        this.a = i;
    }
}
