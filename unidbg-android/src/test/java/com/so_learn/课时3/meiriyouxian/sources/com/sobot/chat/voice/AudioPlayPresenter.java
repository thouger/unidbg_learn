package com.sobot.chat.voice;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.core.a;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.b;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.z;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class AudioPlayPresenter {
    private Context a;
    private ZhiChiMessageBase b;
    private a c;

    public AudioPlayPresenter(Context context) {
        this.a = context;
    }

    public synchronized void a(ZhiChiMessageBase zhiChiMessageBase, a aVar) {
        if (b.a().isPlaying()) {
            b.b();
        }
        this.c = aVar;
        if (this.b != zhiChiMessageBase) {
            if (this.b != null) {
                this.b.setVoideIsPlaying(false);
                if (this.c != null) {
                    this.c.b(this.b);
                    this.b = null;
                }
            }
            a(zhiChiMessageBase);
        } else {
            b.b();
            zhiChiMessageBase.setVoideIsPlaying(false);
            if (this.c != null) {
                this.c.b(zhiChiMessageBase);
                this.b = null;
            }
        }
    }

    private void a(ZhiChiMessageBase zhiChiMessageBase) {
        String str;
        String msg = zhiChiMessageBase.getAnswer().getMsg();
        if (!TextUtils.isEmpty(msg)) {
            if (zhiChiMessageBase.getSugguestionsFontColor() == 1) {
                str = z.a().d() + msg.substring(msg.lastIndexOf(NotificationIconUtil.SPLIT_CHAR) + 1, msg.length());
                File parentFile = new File(str).getParentFile();
                if (!parentFile.exists() && !parentFile.mkdirs()) {
                    try {
                        parentFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                str = msg;
            }
            m.d("contentPath\uff1a" + str);
            File file = new File(str);
            if (file.exists()) {
                a(zhiChiMessageBase, file);
            } else if (TextUtils.isEmpty(msg) || !msg.startsWith("http")) {
                Context context = this.a;
                ae.a(context, q.f(context, "sobot_voice_file_error"));
            } else {
                a.a().a(msg, file, (Map<String, String>) null, new AnonymousClass1(zhiChiMessageBase));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.voice.AudioPlayPresenter$1  reason: invalid class name */
    public class AnonymousClass1 implements a.AbstractC0142a {
        final /* synthetic */ ZhiChiMessageBase a;

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(int i) {
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(Exception exc, String str, int i) {
        }

        AnonymousClass1(ZhiChiMessageBase zhiChiMessageBase) {
            this.a = zhiChiMessageBase;
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(File file) {
            AudioPlayPresenter.this.a(this.a, file);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(ZhiChiMessageBase zhiChiMessageBase, File file) {
        try {
            b.a();
            if (b.d()) {
                b.b();
            }
            b.a().setAudioStreamType(3);
            b.a().reset();
            b.a().setDataSource(file.toString());
            b.a().prepareAsync();
            b.a().setOnPreparedListener(new AnonymousClass2(zhiChiMessageBase));
            b.a().setOnCompletionListener(new AnonymousClass3(zhiChiMessageBase));
        } catch (Exception e) {
            e.printStackTrace();
            m.d("\u97f3\u9891\u64ad\u653e\u5931\u8d25");
            zhiChiMessageBase.setVoideIsPlaying(false);
            b.a().stop();
            a aVar = this.c;
            if (aVar != null) {
                aVar.b(zhiChiMessageBase);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.voice.AudioPlayPresenter$2  reason: invalid class name */
    public class AnonymousClass2 implements MediaPlayer.OnPreparedListener {
        final /* synthetic */ ZhiChiMessageBase a;

        AnonymousClass2(ZhiChiMessageBase zhiChiMessageBase) {
            this.a = zhiChiMessageBase;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
            this.a.setVoideIsPlaying(true);
            if (AudioPlayPresenter.this.c != null) {
                AudioPlayPresenter.this.b = this.a;
                AudioPlayPresenter.this.c.a(this.a);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.voice.AudioPlayPresenter$3  reason: invalid class name */
    public class AnonymousClass3 implements MediaPlayer.OnCompletionListener {
        final /* synthetic */ ZhiChiMessageBase a;

        AnonymousClass3(ZhiChiMessageBase zhiChiMessageBase) {
            this.a = zhiChiMessageBase;
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            this.a.setVoideIsPlaying(false);
            b.a().stop();
            m.d("----\u8bed\u97f3\u64ad\u653e\u5b8c\u6bd5----");
            if (AudioPlayPresenter.this.c != null) {
                AudioPlayPresenter.this.c.b(this.a);
            }
        }
    }
}
