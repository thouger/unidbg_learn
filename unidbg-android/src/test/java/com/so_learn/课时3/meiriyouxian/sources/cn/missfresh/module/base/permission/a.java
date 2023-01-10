package cn.missfresh.module.base.permission;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.basiclib.ui.permission.b;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: AppPermissionDialogUtil */
public class a {
    public static void a(Fragment fragment, String str, b bVar, c cVar) {
        AppMethodBeat.i(18667, false);
        if (TextUtils.isEmpty(str) || bVar == null || cVar == null || fragment == null) {
            AppMethodBeat.o(18667);
        } else if (cVar.a(str)) {
            bVar.t_();
            AppMethodBeat.o(18667);
        } else {
            View inflate = View.inflate(fragment.getContext(), R.layout.layout_app_permission_notice, null);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_allow);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tv_not_allow);
            ((TextView) inflate.findViewById(R.id.tv_title)).setText(b(str));
            ((TextView) inflate.findViewById(R.id.tv_subtitle)).setText(a(str));
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity(), R.style.DialogConner6);
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            textView.setOnClickListener(new AppPermissionDialogUtil$1(create, cVar, fragment, bVar, str));
            textView2.setOnClickListener(new AppPermissionDialogUtil$2(bVar, str, create));
            create.show();
            AppMethodBeat.o(18667);
        }
    }

    public static void a(FragmentActivity fragmentActivity, String str, b bVar, c cVar) {
        AppMethodBeat.i(18671, false);
        a(fragmentActivity, str, b(str), a(str), bVar, cVar);
        AppMethodBeat.o(18671);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001a, code lost:
        if (r8.equals(android.Manifest.permission.READ_CONTACTS) != false) goto L_0x0055;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r8) {
        /*
            r0 = 0
            r1 = 18676(0x48f4, float:2.617E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            int r2 = r8.hashCode()
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            switch(r2) {
                case -1888586689: goto L_0x0049;
                case -63024214: goto L_0x003e;
                case 112197485: goto L_0x0033;
                case 214526995: goto L_0x0028;
                case 463403621: goto L_0x001d;
                case 1977429404: goto L_0x0013;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x0054
        L_0x0013:
            java.lang.String r2 = "android.permission.READ_CONTACTS"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x0054
            goto L_0x0055
        L_0x001d:
            java.lang.String r0 = "android.permission.CAMERA"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r3
            goto L_0x0055
        L_0x0028:
            java.lang.String r0 = "android.permission.WRITE_CONTACTS"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r7
            goto L_0x0055
        L_0x0033:
            java.lang.String r0 = "android.permission.CALL_PHONE"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r4
            goto L_0x0055
        L_0x003e:
            java.lang.String r0 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r5
            goto L_0x0055
        L_0x0049:
            java.lang.String r0 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r6
            goto L_0x0055
        L_0x0054:
            r0 = -1
        L_0x0055:
            if (r0 == 0) goto L_0x0071
            if (r0 == r7) goto L_0x0071
            if (r0 == r6) goto L_0x006d
            if (r0 == r5) goto L_0x006d
            if (r0 == r4) goto L_0x0069
            if (r0 == r3) goto L_0x0065
            java.lang.String r8 = ""
            goto L_0x0074
        L_0x0065:
            java.lang.String r8 = "App\u9700\u8981\u8bbf\u95ee\u60a8\u7684\u76f8\u518c\uff0c\u8fd9\u6837\u60a8\u624d\u80fd\u4f7f\u7528\u4e8c\u7ef4\u7801\u62cd\u7167\u7b49\u529f\u80fd"
            goto L_0x0074
        L_0x0069:
            java.lang.String r8 = "APP\u9700\u8981\u8bbf\u95ee\u60a8\u7684\u901a\u8bdd\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u62e8\u6253\u5ba2\u670d\u70ed\u7ebf\u53cd\u9988\u95ee\u9898"
            goto L_0x0074
        L_0x006d:
            java.lang.String r8 = "\u60a8\u7684\u4f4d\u7f6e\u5c06\u88ab\u7528\u6765\u83b7\u53d6\u60a8\u5468\u56f4\u53ef\u4f9b\u5e94\u7684\u6c34\u679c\u751f\u9c9c\u4fe1\u606f"
            goto L_0x0074
        L_0x0071:
            java.lang.String r8 = "App\u9700\u8981\u8bbf\u95ee\u60a8\u7684\u901a\u8baf\u5f55\uff0c\u4ee5\u4fbf\u5728\u60a8\u8d2d\u7269\u65f6\u6b63\u5e38\u67e5\u9605\uff0c\u53d6\u7528\u60a8\u7684\u8054\u7cfb\u4eba\u4fe1\u606f"
        L_0x0074:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r8
            switch-data {-1888586689->0x0049, -63024214->0x003e, 112197485->0x0033, 214526995->0x0028, 463403621->0x001d, 1977429404->0x0013, }
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.permission.a.a(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x001a, code lost:
        if (r8.equals(android.Manifest.permission.READ_CONTACTS) != false) goto L_0x0055;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String b(java.lang.String r8) {
        /*
            r0 = 0
            r1 = 18678(0x48f6, float:2.6173E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            int r2 = r8.hashCode()
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            switch(r2) {
                case -1888586689: goto L_0x0049;
                case -63024214: goto L_0x003e;
                case 112197485: goto L_0x0033;
                case 214526995: goto L_0x0028;
                case 463403621: goto L_0x001d;
                case 1977429404: goto L_0x0013;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x0054
        L_0x0013:
            java.lang.String r2 = "android.permission.READ_CONTACTS"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x0054
            goto L_0x0055
        L_0x001d:
            java.lang.String r0 = "android.permission.CAMERA"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r3
            goto L_0x0055
        L_0x0028:
            java.lang.String r0 = "android.permission.WRITE_CONTACTS"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r7
            goto L_0x0055
        L_0x0033:
            java.lang.String r0 = "android.permission.CALL_PHONE"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r4
            goto L_0x0055
        L_0x003e:
            java.lang.String r0 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r5
            goto L_0x0055
        L_0x0049:
            java.lang.String r0 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x0054
            r0 = r6
            goto L_0x0055
        L_0x0054:
            r0 = -1
        L_0x0055:
            if (r0 == 0) goto L_0x0071
            if (r0 == r7) goto L_0x0071
            if (r0 == r6) goto L_0x006d
            if (r0 == r5) goto L_0x006d
            if (r0 == r4) goto L_0x0069
            if (r0 == r3) goto L_0x0065
            java.lang.String r8 = ""
            goto L_0x0074
        L_0x0065:
            java.lang.String r8 = "\u201c\u6bcf\u65e5\u4f18\u9c9c\u201d\u60f3\u8bbf\u95ee\u60a8\u7684\u76f8\u673a"
            goto L_0x0074
        L_0x0069:
            java.lang.String r8 = "\u201c\u6bcf\u65e5\u4f18\u9c9c\u201d\u60f3\u8bbf\u95ee\u60a8\u7684\u7535\u8bdd"
            goto L_0x0074
        L_0x006d:
            java.lang.String r8 = "\u201c\u6bcf\u65e5\u4f18\u9c9c\u201d\u60f3\u8bbf\u95ee\u60a8\u7684\u5b9a\u4f4d\u4fe1\u606f"
            goto L_0x0074
        L_0x0071:
            java.lang.String r8 = "\u201c\u6bcf\u65e5\u4f18\u9c9c\u201d\u60f3\u8bbf\u95ee\u60a8\u7684\u901a\u8baf\u5f55"
        L_0x0074:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r8
            switch-data {-1888586689->0x0049, -63024214->0x003e, 112197485->0x0033, 214526995->0x0028, 463403621->0x001d, 1977429404->0x0013, }
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.permission.a.b(java.lang.String):java.lang.String");
    }

    public static void a(FragmentActivity fragmentActivity, String str, String str2, String str3, b bVar, c cVar) {
        AppMethodBeat.i(18682, false);
        if (TextUtils.isEmpty(str) || bVar == null || cVar == null || fragmentActivity == null) {
            AppMethodBeat.o(18682);
        } else if (cVar.a(str)) {
            bVar.t_();
            AppMethodBeat.o(18682);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity, R.style.DialogConner6);
            builder.setCancelable(false);
            View inflate = View.inflate(fragmentActivity, R.layout.layout_app_permission_notice, null);
            builder.setView(inflate);
            ((TextView) inflate.findViewById(R.id.tv_title)).setText(str2);
            ((TextView) inflate.findViewById(R.id.tv_subtitle)).setText(str3);
            AlertDialog create = builder.create();
            ((TextView) inflate.findViewById(R.id.tv_allow)).setOnClickListener(new AppPermissionDialogUtil$3(create, cVar, fragmentActivity, bVar, str));
            ((TextView) inflate.findViewById(R.id.tv_not_allow)).setOnClickListener(new AppPermissionDialogUtil$4(bVar, str, create));
            create.show();
            AppMethodBeat.o(18682);
        }
    }
}
