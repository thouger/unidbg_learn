package cn.missfresh.module.base.im;

import android.content.Context;
import android.text.TextUtils;
import cn.missfresh.module.base.bean.EventFlutterImLoginEvent;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupBaseInfo;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IMManager {
    private static IMManager b;
    List<String> a = new ArrayList();
    private String c = "";
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private ArrayList<String> g = new ArrayList<>();

    static /* synthetic */ void a(IMManager iMManager, String str) {
        AppMethodBeat.i(13440, false);
        iMManager.f(str);
        AppMethodBeat.o(13440);
    }

    static /* synthetic */ void b(IMManager iMManager) {
        AppMethodBeat.i(13442, false);
        iMManager.g();
        AppMethodBeat.o(13442);
    }

    private IMManager() {
        AppMethodBeat.i(13403, false);
        AppMethodBeat.o(13403);
    }

    public static synchronized IMManager a() {
        IMManager iMManager;
        synchronized (IMManager.class) {
            AppMethodBeat.i(13406, false);
            if (b == null) {
                b = new IMManager();
            }
            iMManager = b;
            AppMethodBeat.o(13406);
        }
        return iMManager;
    }

    public void a(Context context) {
        AppMethodBeat.i(13409, false);
        d.d("tencentIM", "IM ABtest \u5f00\u5173 " + f.f());
        if (f.f()) {
            d.d("tencentIM", "IM \u521d\u59cb\u5316 begin");
            TUIKitConfigs configs = TUIKit.getConfigs();
            configs.setSdkConfig(new TIMSdkConfig(1400293526));
            configs.setCustomFaceConfig(new CustomFaceConfig());
            configs.setGeneralConfig(new GeneralConfig());
            TUIKit.init(context, 1400293526, configs);
            this.d = true;
            d.d("tencentIM", "IM \u521d\u59cb\u5316 end");
        }
        AppMethodBeat.o(13409);
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(13410, false);
        if (a().e()) {
            a().setKickedOut(false);
            this.f = false;
            EventBus.getDefault().post(new EventFlutterImLoginEvent(false));
            AppMethodBeat.o(13410);
            return;
        }
        d.d("tencentIM", "IM \u662f\u5426\u767b\u5f55\u8fc7 " + TIMManager.getInstance().getLoginUser() + " \u662f\u5426\u521d\u59cb\u5316 " + this.d + "\u7528\u6237\u540d " + str + " \u7528\u6237\u5bc6\u7801 " + str2);
        if (!this.d || TIMManager.getInstance().getLoginUser() != null) {
            EventBus.getDefault().post(new EventFlutterImLoginEvent(this.f));
        } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            EventBus.getDefault().post(new EventFlutterImLoginEvent(false));
        } else {
            TUIKit.login(str, str2, new AnonymousClass1());
        }
        AppMethodBeat.o(13410);
    }

    /* renamed from: cn.missfresh.module.base.im.IMManager$1  reason: invalid class name */
    class AnonymousClass1 implements IUIKitCallBack {
        AnonymousClass1() {
        }

        @Override // com.tencent.qcloud.tim.uikit.base.IUIKitCallBack
        public void onSuccess(Object obj) {
            AppMethodBeat.i(13342, false);
            d.d("tencentIM", "IM \u767b\u5f55\u6210\u529f");
            IMManager.this.f = true;
            IMManager.this.getAllNewMsgToShow();
            EventBus.getDefault().post(new EventFlutterImLoginEvent(true));
            AppMethodBeat.o(13342);
        }

        @Override // com.tencent.qcloud.tim.uikit.base.IUIKitCallBack
        public void onError(String str, int i, String str2) {
            AppMethodBeat.i(13345, false);
            d.d("tencentIM", "IM \u767b\u5f55\u5931\u8d25");
            IMManager.this.f = false;
            EventBus.getDefault().post(new EventFlutterImLoginEvent(false));
            AppMethodBeat.o(13345);
        }
    }

    public void b() {
        AppMethodBeat.i(13411, false);
        if (this.d && TIMManager.getInstance().getLoginUser() != null) {
            TIMManager.getInstance().logout(null);
        }
        AppMethodBeat.o(13411);
    }

    public void a(String str) {
        AppMethodBeat.i(13412, false);
        if (c() && !TextUtils.isEmpty(str)) {
            a.a().a("/base/rider_chat").withString("ChatId", str).navigation();
        }
        AppMethodBeat.o(13412);
    }

    public void b(String str, String str2) {
        AppMethodBeat.i(13413, false);
        if (c()) {
            ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).showNewMsgView(str, str2);
        }
        AppMethodBeat.o(13413);
    }

    public long b(String str) {
        AppMethodBeat.i(13414, false);
        long j = 0;
        if (c()) {
            if (TIMManager.getInstance().getConversation(TIMConversationType.Group, str) != null) {
                j = TIMManager.getInstance().getConversation(TIMConversationType.Group, str).getUnreadMessageNum();
            }
            AppMethodBeat.o(13414);
            return j;
        }
        AppMethodBeat.o(13414);
        return 0;
    }

    public void getAllNewMsgToShow() {
        AppMethodBeat.i(13415, false);
        d.d("tencentIM", "IM \u51c6\u5907\u904d\u5386\u6240\u6709\u4f1a\u8bdd\u4e2d\u7684\u672a\u8bfb\u6d88\u606f\u8fdb\u884c\u5f39\u7a97 \u767b\u5f55\u72b6\u6001" + c());
        this.a.clear();
        if (c()) {
            TIMGroupManager.getInstance().getGroupList(new AnonymousClass2());
        }
        AppMethodBeat.o(13415);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.im.IMManager$2  reason: invalid class name */
    public class AnonymousClass2 implements TIMValueCallBack<List<TIMGroupBaseInfo>> {
        @Override // com.tencent.imsdk.TIMValueCallBack
        public void onError(int i, String str) {
        }

        AnonymousClass2() {
        }

        @Override // com.tencent.imsdk.TIMValueCallBack
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(13357, false);
            a((List) obj);
            AppMethodBeat.o(13357);
        }

        public void a(List<TIMGroupBaseInfo> list) {
            AppMethodBeat.i(13354, false);
            if (b.a(list)) {
                AppMethodBeat.o(13354);
                return;
            }
            for (TIMGroupBaseInfo tIMGroupBaseInfo : list) {
                IMManager.this.a.add(tIMGroupBaseInfo.getGroupId());
            }
            List<TIMConversation> conversationList = TIMManager.getInstance().getConversationList();
            if (b.a(conversationList)) {
                d.d("tencentIM", "IM \u6ca1\u6709\u4efb\u4f55\u4f1a\u8bdd");
                AppMethodBeat.o(13354);
                return;
            }
            d.d("tencentIM", "IM \u904d\u5386\u6240\u6709\u4f1a\u8bdd\u7684\u6570\u91cf" + conversationList.size());
            IMManager.this.g.clear();
            for (TIMConversation tIMConversation : conversationList) {
                if (tIMConversation != null && !b.a(IMManager.this.a) && IMManager.this.a.contains(tIMConversation.getPeer())) {
                    IMManager.a(IMManager.this, tIMConversation.getPeer());
                    if (!(tIMConversation.getUnreadMessageNum() <= 0 || tIMConversation.getLastMsg() == null || tIMConversation.getLastMsg().getMessageLocator() == null || tIMConversation.getLastMsg().getMessageLocator().getConversationType() == TIMConversationType.System)) {
                        d.d("tencentIM", "IM \u6709\u65b0\u7684\u6d88\u606f");
                        IMManager.this.b(tIMConversation.getPeer(), tIMConversation.getGroupName());
                    }
                }
            }
            AppMethodBeat.o(13354);
        }
    }

    public String c(String str) {
        AppMethodBeat.i(13416, false);
        String str2 = "";
        if (c()) {
            if (TIMManager.getInstance().getConversation(TIMConversationType.Group, str) != null) {
                str2 = TIMManager.getInstance().getConversation(TIMConversationType.Group, str).getGroupName();
            }
            AppMethodBeat.o(13416);
            return str2;
        }
        AppMethodBeat.o(13416);
        return str2;
    }

    public String d(String str) {
        AppMethodBeat.i(13417, false);
        String str2 = "";
        if (c()) {
            if (TIMManager.getInstance().getConversation(TIMConversationType.Group, str) != null) {
                str2 = TIMManager.getInstance().getConversation(TIMConversationType.Group, str).getPeer();
            }
            AppMethodBeat.o(13417);
            return str2;
        }
        AppMethodBeat.o(13417);
        return str2;
    }

    public boolean c() {
        boolean z = false;
        AppMethodBeat.i(13421, false);
        if (this.d) {
            if (TIMManager.getInstance().getLoginUser() != null) {
                z = true;
            }
            AppMethodBeat.o(13421);
            return z;
        }
        AppMethodBeat.o(13421);
        return false;
    }

    public String d() {
        return this.c;
    }

    public void setCurrentChatId(String str) {
        this.c = str;
    }

    public boolean e(String str) {
        AppMethodBeat.i(13426, false);
        if (this.a.contains(TIMManager.getInstance().getConversation(TIMConversationType.Group, str).getPeer())) {
            boolean z = this.d;
            AppMethodBeat.o(13426);
            return z;
        }
        AppMethodBeat.o(13426);
        return false;
    }

    public boolean e() {
        return this.e;
    }

    public void setKickedOut(boolean z) {
        this.e = z;
    }

    public boolean f() {
        return this.f;
    }

    public void setLoginSuccess(boolean z) {
        this.f = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.im.IMManager$3  reason: invalid class name */
    public class AnonymousClass3 implements TIMValueCallBack<List<TIMGroupMemberInfo>> {
        AnonymousClass3() {
        }

        @Override // com.tencent.imsdk.TIMValueCallBack
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(13383, false);
            a((List) obj);
            AppMethodBeat.o(13383);
        }

        @Override // com.tencent.imsdk.TIMValueCallBack
        public void onError(int i, String str) {
            AppMethodBeat.i(13377, false);
            d.d("tencentIM", "IM \u83b7\u53d6\u7fa4\u7ec4\u6210\u5458\u4fe1\u606f\u5931\u8d25");
            AppMethodBeat.o(13377);
        }

        public void a(List<TIMGroupMemberInfo> list) {
            AppMethodBeat.i(13380, false);
            for (TIMGroupMemberInfo tIMGroupMemberInfo : list) {
                IMManager.this.g.add(tIMGroupMemberInfo.getUser());
            }
            IMManager.b(IMManager.this);
            AppMethodBeat.o(13380);
        }
    }

    private void f(String str) {
        AppMethodBeat.i(13433, false);
        TIMGroupManager.getInstance().getGroupMembers(str, new AnonymousClass3());
        AppMethodBeat.o(13433);
    }

    private void g() {
        AppMethodBeat.i(13436, false);
        this.g.add("administrator");
        Iterator<String> it2 = this.g.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (TIMFriendshipManager.getInstance().queryUserProfile(next) != null) {
                d.d("tencentIM", "IM \u7528\u6237\u8d44\u6599\u5df2\u7ecf\u5b58\u5728" + next);
                it2.remove();
            }
        }
        TIMFriendshipManager.getInstance().getUsersProfile(this.g, true, new AnonymousClass4());
        AppMethodBeat.o(13436);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.im.IMManager$4  reason: invalid class name */
    public class AnonymousClass4 implements TIMValueCallBack<List<TIMUserProfile>> {
        AnonymousClass4() {
        }

        @Override // com.tencent.imsdk.TIMValueCallBack
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(13396, false);
            a((List) obj);
            AppMethodBeat.o(13396);
        }

        @Override // com.tencent.imsdk.TIMValueCallBack
        public void onError(int i, String str) {
            AppMethodBeat.i(13390, false);
            d.d("tencentIM", "IM \u83b7\u53d6\u7528\u6237\u8d44\u6599\u5931\u8d25");
            AppMethodBeat.o(13390);
        }

        public void a(List<TIMUserProfile> list) {
            AppMethodBeat.i(13393, false);
            d.d("tencentIM", "IM \u83b7\u53d6\u7528\u6237\u8d44\u6599\u6210\u529f");
            AppMethodBeat.o(13393);
        }
    }
}
