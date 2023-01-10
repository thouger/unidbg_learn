package com.sobot.chat.a;

import android.content.Context;
import android.text.TextUtils;
import com.sobot.chat.api.model.SobotMsgCenterModel;
import com.sobot.chat.b;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.v;
import com.sobot.chat.utils.w;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: SobotMsgCenterHandler */
public class a {

    /* compiled from: SobotMsgCenterHandler */
    /* renamed from: com.sobot.chat.a.a$a  reason: collision with other inner class name */
    public interface AbstractC0135a {
        void a(List<SobotMsgCenterModel> list);

        void b(List<SobotMsgCenterModel> list);
    }

    /* compiled from: SobotMsgCenterHandler */
    /* renamed from: com.sobot.chat.a.a$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ AbstractC0135a c;
        final /* synthetic */ Object d;

        AnonymousClass1(Context context, String str, AbstractC0135a aVar, Object obj) {
            this.a = context;
            this.b = str;
            this.c = aVar;
            this.d = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<SobotMsgCenterModel> a = b.a(this.a.getApplicationContext(), this.b);
            if (a == null) {
                a = new ArrayList<>();
            }
            v vVar = new v();
            Collections.sort(a, vVar);
            AbstractC0135a aVar = this.c;
            if (aVar != null) {
                aVar.a(a);
            }
            List b = a.b(this.d, this.a, this.b);
            if (b != null && b.size() > 0) {
                for (int i = 0; i < b.size(); i++) {
                    SobotMsgCenterModel sobotMsgCenterModel = (SobotMsgCenterModel) b.get(i);
                    int indexOf = a.indexOf(sobotMsgCenterModel);
                    if (indexOf == -1) {
                        a.add(sobotMsgCenterModel);
                    } else {
                        try {
                            a.get(indexOf).setId(sobotMsgCenterModel.getId());
                        } catch (Exception unused) {
                        }
                    }
                }
                Collections.sort(a, vVar);
                AbstractC0135a aVar2 = this.c;
                if (aVar2 != null) {
                    aVar2.b(a);
                }
            }
        }
    }

    public static void a(Object obj, Context context, String str, AbstractC0135a aVar) {
        w.a().execute(new AnonymousClass1(context, str, aVar, obj));
    }

    /* access modifiers changed from: private */
    public static List<SobotMsgCenterModel> b(Object obj, Context context, String str) {
        String b = s.b(context.getApplicationContext(), "sobot_platform_unioncode", "");
        if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(str)) {
            try {
                return com.sobot.chat.core.channel.a.a(context.getApplicationContext()).a().getPlatformList(obj, b, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
