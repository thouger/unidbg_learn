package cn.missfresh.module.base.im;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.mvp.mvp.interfaces.IPresenter;
import cn.missfresh.module.mvp.mvp.module.BaseMVPActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import com.bangcle.andjni.JniLib;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.component.AudioPlayer;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.input.InputLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;

public class IMChatActivity extends BaseMVPActivity {
    String a;
    private ChatLayout b;
    private TitleBarLayout c;
    private MessageLayout d;
    private InputLayout e;
    private Context f;

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public IPresenter f() {
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.im.IMChatActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void onWindowFocusChanged(boolean z) {
        IMChatActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    public void g() {
        AppMethodBeat.i(13319, false);
        this.c.setOnLeftClickListener(new AnonymousClass1(this));
        this.c.setOnRightClickListener(new AnonymousClass2(this));
        AppMethodBeat.o(13319);
    }

    /* renamed from: cn.missfresh.module.base.im.IMChatActivity$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ IMChatActivity a;

        AnonymousClass1(IMChatActivity iMChatActivity) {
            JniLib.cV(this, iMChatActivity, 6);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(13304, true);
            this.a.finish();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(13304);
        }
    }

    /* renamed from: cn.missfresh.module.base.im.IMChatActivity$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ IMChatActivity a;

        AnonymousClass2(IMChatActivity iMChatActivity) {
            JniLib.cV(this, iMChatActivity, 8);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(13310, true);
            String d = IMManager.a().d(this.a.a);
            if (TextUtils.isEmpty(d)) {
                a.a("\u65e0\u6cd5\u4e0e\u7b2c\u4e09\u65b9\u9a91\u624b\u8054\u7cfb\uff0c\u8bf7\u8010\u5fc3\u7b49\u9a91\u624b\u914d\u9001");
            } else {
                c.a(d, null, null, new AnonymousClass1(this));
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(13310);
        }

        /* renamed from: cn.missfresh.module.base.im.IMChatActivity$2$1  reason: invalid class name */
        class AnonymousClass1 implements a {
            final /* synthetic */ AnonymousClass2 a;

            AnonymousClass1(AnonymousClass2 r5) {
                JniLib.cV(this, r5, 7);
            }

            @Override // cn.missfresh.module.base.im.a
            public void a(String str) {
                AppMethodBeat.i(13305, false);
                this.a.a.b(str);
                AppMethodBeat.o(13305);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: cn.missfresh.module.base.im.IMChatActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void k() {
        AppMethodBeat.i(13323, false);
        this.f = this;
        com.alibaba.android.arouter.b.a.a().a(this);
        this.y.a(8);
        as.a((Activity) this);
        this.b = (ChatLayout) findViewById(R.id.chat_layout);
        this.b.initDefault();
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setId(this.a);
        chatInfo.setChatName(IMManager.a().c(this.a));
        chatInfo.setType(TIMConversationType.Group);
        this.b.setChatInfo(chatInfo);
        this.c = this.b.getTitleBar();
        this.c.setRightIcon(R.drawable.order_icon_im_call);
        this.e = this.b.getInputLayout();
        this.e.disableSendFileAction(true);
        this.e.disableVideoRecordAction(true);
        this.d = this.b.getMessageLayout();
        this.d.setOnCustomMessageDrawListener(new CustomMessageDraw(this.f.getApplicationContext()));
        this.d.setLeftNameVisibility(8);
        AppMethodBeat.o(13323);
    }

    /* access modifiers changed from: protected */
    public int M_() {
        return R.layout.order_activity_im_chat;
    }

    public void onPause() {
        AppMethodBeat.i(13327, false);
        IMChatActivity.super.onPause();
        AudioPlayer.getInstance().stopPlay();
        IMManager.a().setCurrentChatId("");
        AppMethodBeat.o(13327);
    }

    public void onDestroy() {
        AppMethodBeat.i(13328, false);
        IMChatActivity.super.onDestroy();
        ChatLayout chatLayout = this.b;
        if (chatLayout != null) {
            chatLayout.exitChat();
        }
        AppMethodBeat.o(13328);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.base.im.IMChatActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void b(String str) {
        AppMethodBeat.i(13334, false);
        if (!TextUtils.isEmpty(str)) {
            q.a((Context) this, str);
        } else {
            a.a("\u65e0\u6cd5\u4e0e\u7b2c\u4e09\u65b9\u9a91\u624b\u8054\u7cfb\uff0c\u8bf7\u8010\u5fc3\u7b49\u9a91\u624b\u914d\u9001");
        }
        AppMethodBeat.o(13334);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        AppMethodBeat.i(13336, false);
        IMChatActivity.super.onResume();
        IMManager.a().setCurrentChatId(this.a);
        if (!IMManager.a().c()) {
            finish();
        }
        AppMethodBeat.o(13336);
    }
}
