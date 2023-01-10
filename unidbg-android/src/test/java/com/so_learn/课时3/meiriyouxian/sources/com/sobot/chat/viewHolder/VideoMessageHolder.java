package com.sobot.chat.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.sobot.chat.activity.SobotVideoActivity;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.CommonModelBase;
import com.sobot.chat.api.model.SobotCacheFile;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.core.http.i.b;
import com.sobot.chat.core.http.i.c;
import com.sobot.chat.core.http.i.d;
import com.sobot.chat.core.http.model.SobotProgress;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.widget.RoundProgressBar;
import com.sobot.chat.widget.image.SobotRCImageView;

public class VideoMessageHolder extends MessageHolderBase implements View.OnClickListener {
    private RoundProgressBar a;
    private ImageView b;
    private ImageView c;
    private SobotRCImageView d;
    private int e;
    private ZhiChiMessageBase f;
    private String g;

    public VideoMessageHolder(Context context, View view) {
        super(context, view);
        this.a = (RoundProgressBar) view.findViewById(q.g(context, "sobot_pic_progress_round"));
        this.b = (ImageView) view.findViewById(q.g(context, "sobot_msgStatus"));
        this.c = (ImageView) view.findViewById(q.g(context, "st_tv_play"));
        this.d = (SobotRCImageView) view.findViewById(q.g(context, "st_iv_pic"));
        this.e = q.e(context, "sobot_bg_default_pic");
        this.a.setTextDisplayable(false);
        ImageView imageView = this.b;
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
        this.c.setOnClickListener(this);
    }

    @Override // com.sobot.chat.viewHolder.base.MessageHolderBase
    public void a(Context context, ZhiChiMessageBase zhiChiMessageBase) {
        this.f = zhiChiMessageBase;
        if (zhiChiMessageBase.getAnswer() != null && zhiChiMessageBase.getAnswer().getCacheFile() != null) {
            SobotCacheFile cacheFile = zhiChiMessageBase.getAnswer().getCacheFile();
            Context context2 = this.n;
            String snapshot = cacheFile.getSnapshot();
            SobotRCImageView sobotRCImageView = this.d;
            int i = this.e;
            t.a(context2, snapshot, sobotRCImageView, i, i);
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
            if (!(this.c != view || zhiChiMessageBase.getAnswer() == null || this.f.getAnswer().getCacheFile() == null)) {
                this.n.startActivity(SobotVideoActivity.a(this.n, this.f.getAnswer().getCacheFile()));
            }
            if (this.b == view) {
                a(this.n, this.t, new AnonymousClass1());
            }
        }
    }

    /* renamed from: com.sobot.chat.viewHolder.VideoMessageHolder$1  reason: invalid class name */
    class AnonymousClass1 implements MessageHolderBase.a {
        AnonymousClass1() {
        }

        @Override // com.sobot.chat.viewHolder.base.MessageHolderBase.a
        public void a() {
            d<?> a = b.a().a(VideoMessageHolder.this.g);
            if (a != null) {
                a.b();
            } else {
                VideoMessageHolder.this.a();
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
            ImageView imageView = this.b;
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            this.a.setProgress(100);
            this.a.setVisibility(8);
            this.u.setVisibility(8);
            this.c.setVisibility(0);
        } else if (this.b != null) {
            int i = sobotProgress.status;
            if (i == 0) {
                this.b.setVisibility(8);
                this.c.setVisibility(0);
                this.a.setProgress((int) (sobotProgress.fraction * 100.0f));
            } else if (i == 1 || i == 2 || i == 3) {
                this.c.setVisibility(8);
                this.b.setVisibility(8);
                this.a.setProgress((int) (sobotProgress.fraction * 100.0f));
                this.a.setVisibility(8);
                this.u.setVisibility(0);
            } else if (i == 4) {
                this.c.setVisibility(0);
                this.b.setVisibility(0);
                this.a.setProgress(100);
                this.a.setVisibility(8);
                this.u.setVisibility(8);
            } else if (i == 5) {
                this.c.setVisibility(0);
                this.b.setVisibility(8);
                this.a.setProgress(100);
                this.a.setVisibility(8);
                this.u.setVisibility(8);
            }
        }
    }

    private static class a extends c {
        private VideoMessageHolder b;

        @Override // com.sobot.chat.core.http.i.a
        public void a(SobotProgress sobotProgress) {
        }

        @Override // com.sobot.chat.core.http.i.a
        public void d(SobotProgress sobotProgress) {
        }

        a(Object obj, VideoMessageHolder videoMessageHolder) {
            super(obj);
            this.b = videoMessageHolder;
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
