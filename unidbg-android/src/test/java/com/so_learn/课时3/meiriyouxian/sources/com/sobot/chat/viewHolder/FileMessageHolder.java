package com.sobot.chat.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sobot.chat.activity.SobotFileDetailActivity;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.CommonModelBase;
import com.sobot.chat.api.model.SobotCacheFile;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.core.http.i.b;
import com.sobot.chat.core.http.i.d;
import com.sobot.chat.core.http.model.SobotProgress;
import com.sobot.chat.utils.c;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.widget.SobotSectorProgressView;

public class FileMessageHolder extends MessageHolderBase implements View.OnClickListener {
    private SobotSectorProgressView a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private RelativeLayout e;
    private ZhiChiMessageBase f;
    private String g;
    private int h;
    private int i;

    public FileMessageHolder(Context context, View view) {
        super(context, view);
        this.a = (SobotSectorProgressView) view.findViewById(q.a(context, "id", "sobot_progress"));
        this.b = (TextView) view.findViewById(q.a(context, "id", "sobot_file_name"));
        this.c = (TextView) view.findViewById(q.a(context, "id", "sobot_file_size"));
        this.d = (ImageView) view.findViewById(q.a(context, "id", "sobot_msgStatus"));
        this.e = (RelativeLayout) view.findViewById(q.a(context, "id", "sobot_ll_file_container"));
        this.h = q.a(context, "drawable", "sobot_re_send_selector");
        this.i = q.a(context, "drawable", "sobot_icon_remove");
        ImageView imageView = this.d;
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        this.e.setOnClickListener(this);
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.f = zhiChiMessageBase;
        if (zhiChiMessageBase.getAnswer() != null && zhiChiMessageBase.getAnswer().getCacheFile() != null) {
            SobotCacheFile cacheFile = zhiChiMessageBase.getAnswer().getCacheFile();
            this.b.setText(cacheFile.getFileName());
            this.c.setText(cacheFile.getFileSize());
            t.a(this.n, c.a(this.n, cacheFile.getFileType()), this.a);
            this.g = cacheFile.getMsgId();
            if (!this.o) {
                a((SobotProgress) null);
            } else if (b.a().b(this.g)) {
                d<?> a2 = b.a().a(this.g);
                a2.a(new a(this.g, this));
                a(a2.a);
            } else {
                a((SobotProgress) null);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ZhiChiMessageBase zhiChiMessageBase = this.f;
        if (zhiChiMessageBase != null) {
            if (!(this.e != view || zhiChiMessageBase.getAnswer() == null || this.f.getAnswer().getCacheFile() == null)) {
                Intent intent = new Intent(this.n, SobotFileDetailActivity.class);
                intent.putExtra("sobot_intent_data_selected_file", this.f.getAnswer().getCacheFile());
                intent.setFlags(268435456);
                this.n.startActivity(intent);
            }
            ImageView imageView = this.d;
            if (imageView != view) {
                return;
            }
            if (imageView.isSelected()) {
                a(this.n, this.t, new AnonymousClass1());
                return;
            }
            if (b.a().b(this.g)) {
                b.a().a(this.g).d();
            }
            a();
        }
    }

    /* renamed from: com.sobot.chat.viewHolder.FileMessageHolder$1  reason: invalid class name */
    class AnonymousClass1 implements MessageHolderBase.a {
        AnonymousClass1() {
        }

        @Override // com.sobot.chat.viewHolder.base.MessageHolderBase.a
        public void a() {
            d<?> a = b.a().a(FileMessageHolder.this.g);
            if (a != null) {
                a.b();
            } else {
                FileMessageHolder.this.a();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a() {
        Intent intent = new Intent(ZhiChiConstants.SOBOT_BROCAST_REMOVE_FILE_TASK);
        intent.putExtra("sobot_msgId", this.g);
        com.sobot.chat.utils.d.a(this.n, intent);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String b() {
        return this.g;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(SobotProgress sobotProgress) {
        if (sobotProgress == null) {
            ImageView imageView = this.d;
            if (imageView != null) {
                imageView.setVisibility(8);
                this.u.setVisibility(8);
            }
        } else if (this.d != null) {
            int i = sobotProgress.status;
            if (i == 0) {
                this.d.setVisibility(8);
                this.u.setVisibility(8);
            } else if (i == 1 || i == 2 || i == 3) {
                this.u.setVisibility(0);
                this.d.setVisibility(8);
                this.d.setBackgroundResource(this.i);
                this.d.setSelected(false);
            } else if (i == 4) {
                this.d.setVisibility(0);
                this.d.setBackgroundResource(this.h);
                this.d.setSelected(true);
                this.u.setVisibility(8);
            } else if (i == 5) {
                this.d.setVisibility(8);
                this.u.setVisibility(8);
            }
        }
    }

    private static class a extends com.sobot.chat.core.http.i.c {
        private FileMessageHolder b;

        @Override // com.sobot.chat.core.http.i.a
        public void a(SobotProgress sobotProgress) {
        }

        @Override // com.sobot.chat.core.http.i.a
        public void d(SobotProgress sobotProgress) {
        }

        a(Object obj, FileMessageHolder fileMessageHolder) {
            super(obj);
            this.b = fileMessageHolder;
        }

        @Override // com.sobot.chat.core.http.i.a
        public void b(SobotProgress sobotProgress) {
            if (this.a == this.b.b()) {
                this.b.a(sobotProgress);
            }
        }

        @Override // com.sobot.chat.core.http.i.a
        public void c(SobotProgress sobotProgress) {
            if (this.a == this.b.b()) {
                this.b.a(sobotProgress);
            }
        }

        public void a(CommonModelBase commonModelBase, SobotProgress sobotProgress) {
            if (this.a == this.b.b()) {
                this.b.a(sobotProgress);
            }
        }
    }
}
