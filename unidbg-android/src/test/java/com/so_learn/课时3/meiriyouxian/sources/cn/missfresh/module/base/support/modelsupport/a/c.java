package cn.missfresh.module.base.support.modelsupport.a;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.a;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.support.modelsupport.event.EventSocialReqData;
import cn.missfresh.module.base.support.modelsupport.event.EventSocialResData;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.module.base.support.modelsupport.event.bean.SocialBeans;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import java.util.Map;
import okhttp3.Request;

/* compiled from: SocialSDataDealer */
public class c {
    public void a(BaseReqEvent baseReqEvent) {
        AppMethodBeat.i(22019, false);
        d.d("SocialSDataDealer", "doAction req:" + baseReqEvent);
        if (baseReqEvent == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("reqEvent is null");
            AppMethodBeat.o(22019);
            throw illegalArgumentException;
        } else if (baseReqEvent instanceof EventSocialReqData) {
            EventSocialReqData eventSocialReqData = (EventSocialReqData) baseReqEvent;
            int reqDetailType = eventSocialReqData.getReqDetailType();
            if (reqDetailType == 1) {
                a(eventSocialReqData);
            } else if (reqDetailType == 2) {
                b(eventSocialReqData);
            } else if (reqDetailType == 3) {
                c(eventSocialReqData);
            }
            AppMethodBeat.o(22019);
        } else {
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("reqEvent is" + baseReqEvent.getClass().getName() + ",but " + EventSocialReqData.class.getName() + " wanted");
            AppMethodBeat.o(22019);
            throw illegalArgumentException2;
        }
    }

    private void a(EventSocialReqData eventSocialReqData) {
        AppMethodBeat.i(22020, false);
        cn.missfresh.module.base.network.c.a("SocialSDataDealer", i.aG, (Map<String, String>) null, new AnonymousClass1(new EventSocialResData(eventSocialReqData)));
        AppMethodBeat.o(22020);
    }

    /* compiled from: SocialSDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.c$1  reason: invalid class name */
    public class AnonymousClass1 extends m {
        final /* synthetic */ EventSocialResData a;

        AnonymousClass1(EventSocialResData eventSocialResData) {
            this.a = eventSocialResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(22002, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(22002);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(22003, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(22003);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(22005, false);
            if (b.a(str)) {
                d.c("SocialSDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(22005);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setInviteFriendsInfo((SocialBeans.InviteFriendsInfo) JSON.parseObject(str, SocialBeans.InviteFriendsInfo.class));
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                d.a("SocialSDataDealer", e);
            }
            AppMethodBeat.o(22005);
        }
    }

    private void b(EventSocialReqData eventSocialReqData) {
        AppMethodBeat.i(22021, false);
        EventSocialResData eventSocialResData = new EventSocialResData(eventSocialReqData);
        String adConfigUrl = eventSocialReqData.getAdConfigUrl();
        Map<String, String> a = cn.missfresh.module.base.network.c.a("shareUrl", eventSocialReqData.getOriginUrl());
        if (eventSocialReqData.getAdImgType() != -1) {
            a.put("type", String.valueOf(eventSocialReqData.getAdImgType()));
        }
        cn.missfresh.module.base.network.c.a("SocialSDataDealer", adConfigUrl, a, new AnonymousClass2(eventSocialResData));
        AppMethodBeat.o(22021);
    }

    /* compiled from: SocialSDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.c$2  reason: invalid class name */
    public class AnonymousClass2 extends m {
        final /* synthetic */ EventSocialResData a;

        AnonymousClass2(EventSocialResData eventSocialResData) {
            this.a = eventSocialResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(22007, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(22007);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(22009, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(22009);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a() {
            AppMethodBeat.i(22011, false);
            super.a();
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(22011);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void b() {
            AppMethodBeat.i(22012, false);
            super.b();
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(22012);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(22013, false);
            if (b.a(str)) {
                d.c("SocialSDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(22013);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setImgUrlRes((SocialBeans.getShareImgUrlRes) JSON.parseObject(str, SocialBeans.getShareImgUrlRes.class));
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                d.a("SocialSDataDealer", e);
            }
            AppMethodBeat.o(22013);
        }
    }

    private void c(EventSocialReqData eventSocialReqData) {
        AppMethodBeat.i(22022, false);
        cn.missfresh.module.base.network.c.a("SocialSDataDealer", i.bD, (Map<String, String>) null, new AnonymousClass3(new EventSocialResData(eventSocialReqData)));
        AppMethodBeat.o(22022);
    }

    /* compiled from: SocialSDataDealer */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.modelsupport.a.c$3  reason: invalid class name */
    public class AnonymousClass3 extends m {
        final /* synthetic */ EventSocialResData a;

        AnonymousClass3(EventSocialResData eventSocialResData) {
            this.a = eventSocialResData;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(22016, false);
            this.a.setErrCode(i);
            this.a.post();
            AppMethodBeat.o(22016);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(22017, false);
            this.a.setNetErr();
            this.a.post();
            AppMethodBeat.o(22017);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(22018, false);
            if (b.a(str)) {
                d.c("SocialSDataDealer", "res data empty");
                this.a.setDataErr();
                this.a.post();
                AppMethodBeat.o(22018);
                return;
            }
            try {
                a c = m.c(str);
                if (c.a != 0) {
                    this.a.setErrCode(c.a);
                    this.a.post();
                } else {
                    this.a.setMainPageRes((SocialBeans.GetInviteMainPageRes) JSON.parseObject(str, SocialBeans.GetInviteMainPageRes.class));
                    this.a.post();
                }
            } catch (Exception e) {
                this.a.setDataErr();
                this.a.post();
                d.a("SocialSDataDealer", e);
            }
            AppMethodBeat.o(22018);
        }
    }
}
