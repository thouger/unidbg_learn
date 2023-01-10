package cn.missfresh.module.base.helper;

import android.app.Activity;
import cn.missfresh.module.base.common.config.BottomTabEnum;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.picture.MfPicture;
import cn.missfresh.picture.MimeType;
import cn.missfresh.picture.PictureFileProvider;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import com.huawei.hms.support.api.push.PushReceiver;
import java.util.ArrayList;
import java.util.List;

/* compiled from: NavigationHelper */
public class f {
    public static void a() {
        AppMethodBeat.i(13028, false);
        a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.DELICACY.getPos()).withAction("action_nothing").navigation();
        AppMethodBeat.o(13028);
    }

    public static void a(int i, int i2) {
        AppMethodBeat.i(13030, false);
        a.a().a("/food/other_profile").withInt("type", i2).withInt("authorId", i).navigation();
        AppMethodBeat.o(13030);
    }

    public static void a(long j, String str) {
        AppMethodBeat.i(13033, false);
        a(j, str, (String) null);
        AppMethodBeat.o(13033);
    }

    public static void a(long j, String str, String str2) {
        AppMethodBeat.i(13034, false);
        a(j, 2, str2);
        AppMethodBeat.o(13034);
    }

    public static void b(long j, String str) {
        AppMethodBeat.i(13036, false);
        b(j, str, null);
        AppMethodBeat.o(13036);
    }

    public static void b(long j, String str, String str2) {
        AppMethodBeat.i(13037, false);
        a(j, 1, str2);
        AppMethodBeat.o(13037);
    }

    public static void a(long j, int i, String str) {
        AppMethodBeat.i(13039, false);
        a.a().a("/flutter/router").withString("Router", "talentplanDetail").withLong("MFFoodCommunityRecipeID", j).withString(PushReceiver.PushMessageThread.MSGTYPE, str).withInt("contentType", i).navigation();
        AppMethodBeat.o(13039);
    }

    public static void a(String str, String str2) {
        AppMethodBeat.i(13045, false);
        a.a().a("/food/search").withString("hintWord", str).withString("searchKey", str2).navigation();
        AppMethodBeat.o(13045);
    }

    public static void a(List<String> list) {
        AppMethodBeat.i(13048, false);
        a.a().a("/food/food_upload_production").withLong("authorId", (long) e.p().getUser_id()).navigation();
        AppMethodBeat.o(13048);
    }

    public static void b() {
        AppMethodBeat.i(13049, false);
        a.a().a("/food/recipes_upload_production").withLong("authorId", (long) e.p().getUser_id()).navigation();
        AppMethodBeat.o(13049);
    }

    public static void a(Activity activity, List<String> list) {
        AppMethodBeat.i(13052, false);
        int i = 9;
        if (list != null && !list.isEmpty()) {
            i = 9 - list.size();
        }
        if (i < 1) {
            AppMethodBeat.o(13052);
            return;
        }
        a(activity, i);
        AppMethodBeat.o(13052);
    }

    public static void a(Activity activity, int i) {
        AppMethodBeat.i(13053, false);
        if (i < 1) {
            AppMethodBeat.o(13053);
            return;
        }
        MfPicture.from(activity).choose(MimeType.ofImageNoGif(), false).b(true).c(true).a(new cn.missfresh.picture.internal.entity.a(true, PictureFileProvider.a(activity.getApplicationContext()), "mf")).a(i).a(new cn.missfresh.picture.filter.a()).b(1).a(0.85f).a(new cn.missfresh.picture.a.a.a()).a(true).d(true).c(4097);
        AppMethodBeat.o(13053);
    }

    public static void a(long j, long j2, long j3, int i) {
        AppMethodBeat.i(13056, false);
        a.a().a("/food/vote").withLong("workId", j).withLong("activityId", j2).withLong("cookId", j3).withInt("sourceType", i).navigation();
        AppMethodBeat.o(13056);
    }

    public static void a(Activity activity, ArrayList<String> arrayList, int i, boolean z) {
        AppMethodBeat.i(13058, false);
        a.a().a("/food/relate_food").withStringArrayList("relateSku", arrayList).withInt("relateSize", i).withBoolean("relatePage", z).navigation(activity, 512);
        AppMethodBeat.o(13058);
    }

    public static void a(String str, Activity activity) {
        AppMethodBeat.i(13064, false);
        a.a().a("/talentPlan/userHeader").withString("user_info", str).navigation(activity, 3001);
        AppMethodBeat.o(13064);
    }
}
