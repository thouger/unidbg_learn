package cn.missfresh.basiclib.utils.permission;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.basiclib.ui.permission.PermissionExplainDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tbruyelle.rxpermissions2.a;
import com.tbruyelle.rxpermissions2.b;
import com.tencent.smtt.sdk.TbsReaderView;
import io.reactivex.c.g;
import io.reactivex.c.h;
import io.reactivex.c.k;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PermissionUtil */
public class c {
    private b a;
    private boolean b;
    private PermissionExplainDialog c;

    static /* synthetic */ void a(c cVar, FragmentManager fragmentManager) {
        AppMethodBeat.i(5044, false);
        cVar.a(fragmentManager);
        AppMethodBeat.o(5044);
    }

    static /* synthetic */ void a(c cVar, FragmentManager fragmentManager, cn.missfresh.basiclib.ui.permission.b bVar, a aVar, String str) {
        AppMethodBeat.i(5054, false);
        cVar.a(fragmentManager, bVar, aVar, str);
        AppMethodBeat.o(5054);
    }

    static /* synthetic */ void a(c cVar, FragmentManager fragmentManager, cn.missfresh.basiclib.ui.permission.c cVar2, String str) {
        AppMethodBeat.i(5047, false);
        cVar.a(fragmentManager, cVar2, str);
        AppMethodBeat.o(5047);
    }

    static /* synthetic */ void a(c cVar, FragmentManager fragmentManager, cn.missfresh.basiclib.ui.permission.c cVar2, String str, boolean z) {
        AppMethodBeat.i(5045, false);
        cVar.a(fragmentManager, cVar2, str, z);
        AppMethodBeat.o(5045);
    }

    static /* synthetic */ void a(c cVar, FragmentManager fragmentManager, b bVar, cn.missfresh.basiclib.ui.permission.a aVar, List list) {
        AppMethodBeat.i(TbsReaderView.ReaderCallback.READER_PLUGIN_TEXT_FIND_RESULT, false);
        cVar.a(fragmentManager, bVar, aVar, list);
        AppMethodBeat.o(TbsReaderView.ReaderCallback.READER_PLUGIN_TEXT_FIND_RESULT);
    }

    static /* synthetic */ void a(c cVar, FragmentManager fragmentManager, b bVar, cn.missfresh.basiclib.ui.permission.b bVar2, String str) {
        AppMethodBeat.i(5057, false);
        cVar.a(fragmentManager, bVar, bVar2, str);
        AppMethodBeat.o(5057);
    }

    static /* synthetic */ void a(c cVar, cn.missfresh.basiclib.ui.permission.b bVar, a aVar) {
        AppMethodBeat.i(5051, false);
        cVar.a(bVar, aVar);
        AppMethodBeat.o(5051);
    }

    static /* synthetic */ void a(c cVar, a aVar, cn.missfresh.basiclib.ui.permission.a aVar2) {
        AppMethodBeat.i(5048, false);
        cVar.a(aVar, aVar2);
        AppMethodBeat.o(5048);
    }

    static /* synthetic */ boolean a(c cVar, String str) {
        AppMethodBeat.i(5049, false);
        boolean d = cVar.d(str);
        AppMethodBeat.o(5049);
        return d;
    }

    public boolean a(String... strArr) {
        AppMethodBeat.i(4963, false);
        if (strArr == null || strArr.length <= 0) {
            AppMethodBeat.o(4963);
            return true;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (Build.VERSION.SDK_INT >= 29 && strArr[i].equals("android.permission.READ_PHONE_STATE")) {
                AppMethodBeat.o(4963);
                return false;
            } else if (!c(strArr[i])) {
                AppMethodBeat.o(4963);
                return false;
            }
        }
        AppMethodBeat.o(4963);
        return true;
    }

    private boolean c(String str) {
        AppMethodBeat.i(4965, false);
        if (b.a() == null || b.a().b() == null) {
            AppMethodBeat.o(4965);
            return false;
        } else if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(4965);
            return true;
        } else {
            try {
                if (ActivityCompat.checkSelfPermission(b.a().b(), str) == 0) {
                    if (d(str)) {
                        AppMethodBeat.o(4965);
                        return true;
                    }
                    AppMethodBeat.o(4965);
                    return false;
                }
            } catch (Exception unused) {
            }
            AppMethodBeat.o(4965);
            return false;
        }
    }

    private static void a(Context context) {
        AppMethodBeat.i(4969, false);
        if (context == null) {
            AppMethodBeat.o(4969);
            return;
        }
        if (b.a() == null || b.a().b() == null) {
            b.a(context);
        }
        AppMethodBeat.o(4969);
    }

    public void a(FragmentActivity fragmentActivity, cn.missfresh.basiclib.ui.permission.a aVar, String... strArr) {
        AppMethodBeat.i(4971, false);
        if (fragmentActivity == null || fragmentActivity.isFinishing()) {
            if (aVar != null) {
                aVar.l_("");
            }
            AppMethodBeat.o(4971);
            return;
        }
        a(fragmentActivity.getApplicationContext());
        ArrayList<String> c = c(strArr);
        if (c == null || c.size() <= 0) {
            a(fragmentActivity.getSupportFragmentManager());
            if (aVar != null) {
                aVar.t_();
            }
            AppMethodBeat.o(4971);
            return;
        }
        if (this.a == null) {
            this.a = new b(fragmentActivity);
        }
        a(fragmentActivity.getSupportFragmentManager(), this.a, aVar, c);
        AppMethodBeat.o(4971);
    }

    /* compiled from: PermissionUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.utils.permission.c$4  reason: invalid class name */
    public class AnonymousClass4 implements k<a> {
        final /* synthetic */ cn.missfresh.basiclib.ui.permission.a a;
        final /* synthetic */ List b;

        AnonymousClass4(cn.missfresh.basiclib.ui.permission.a aVar, List list) {
            this.a = aVar;
            this.b = list;
        }

        @Override // io.reactivex.c.k
        public /* synthetic */ boolean test(Object obj) throws Exception {
            AppMethodBeat.i(4930, false);
            boolean a = a((a) obj);
            AppMethodBeat.o(4930);
            return a;
        }

        public boolean a(a aVar) throws Exception {
            AppMethodBeat.i(4929, false);
            c.a(c.this, aVar, this.a);
            if (!aVar.b) {
                AppMethodBeat.o(4929);
                return true;
            } else if (c.a(c.this, aVar.a)) {
                this.b.remove(aVar.a);
                AppMethodBeat.o(4929);
                return false;
            } else {
                AppMethodBeat.o(4929);
                return true;
            }
        }
    }

    private void a(FragmentManager fragmentManager, b bVar, cn.missfresh.basiclib.ui.permission.a aVar, List<String> list) {
        AppMethodBeat.i(4976, false);
        bVar.b((String[]) list.toArray(new String[list.size()])).a(new AnonymousClass4(aVar, list)).b(new AnonymousClass3()).a((io.reactivex.c.c) new AnonymousClass2()).b((io.reactivex.k) 2).a((g) new AnonymousClass1(fragmentManager, aVar, bVar, list));
        AppMethodBeat.o(4976);
    }

    /* compiled from: PermissionUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.utils.permission.c$3  reason: invalid class name */
    public class AnonymousClass3 implements h<a, Integer> {
        AnonymousClass3() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(4927, false);
            Integer a = a((a) obj);
            AppMethodBeat.o(4927);
            return a;
        }

        public Integer a(a aVar) throws Exception {
            AppMethodBeat.i(4923, false);
            if (aVar.c) {
                AppMethodBeat.o(4923);
                return 0;
            }
            AppMethodBeat.o(4923);
            return 1;
        }
    }

    /* compiled from: PermissionUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.utils.permission.c$2  reason: invalid class name */
    public class AnonymousClass2 implements io.reactivex.c.c<Integer, Integer, Integer> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.c
        public /* synthetic */ Object apply(Object obj, Object obj2) throws Exception {
            AppMethodBeat.i(4918, false);
            Integer a = a((Integer) obj, (Integer) obj2);
            AppMethodBeat.o(4918);
            return a;
        }

        public Integer a(Integer num, Integer num2) throws Exception {
            AppMethodBeat.i(4916, false);
            Integer valueOf = Integer.valueOf(num.intValue() & num2.intValue());
            AppMethodBeat.o(4916);
            return valueOf;
        }
    }

    /* compiled from: PermissionUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.utils.permission.c$1  reason: invalid class name */
    public class AnonymousClass1 implements g<Integer> {
        final /* synthetic */ FragmentManager a;
        final /* synthetic */ cn.missfresh.basiclib.ui.permission.a b;
        final /* synthetic */ b c;
        final /* synthetic */ List d;

        AnonymousClass1(FragmentManager fragmentManager, cn.missfresh.basiclib.ui.permission.a aVar, b bVar, List list) {
            this.a = fragmentManager;
            this.b = aVar;
            this.c = bVar;
            this.d = list;
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(4909, false);
            a((Integer) obj);
            AppMethodBeat.o(4909);
        }

        public void a(Integer num) throws Exception {
            AppMethodBeat.i(4907, false);
            int intValue = num.intValue();
            if (intValue == 0) {
                c.a(c.this, this.a, (cn.missfresh.basiclib.ui.permission.c) new AnonymousClass1(), "", true);
            } else if (intValue != 2) {
                c.a(c.this, this.a, new AnonymousClass2(), "");
            } else {
                c.a(c.this, this.a);
                cn.missfresh.basiclib.ui.permission.a aVar = this.b;
                if (aVar != null) {
                    aVar.t_();
                }
            }
            AppMethodBeat.o(4907);
        }

        /* compiled from: PermissionUtil */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.basiclib.utils.permission.c$1$1  reason: invalid class name */
        public class AnonymousClass1 implements cn.missfresh.basiclib.ui.permission.c {
            AnonymousClass1() {
            }

            @Override // cn.missfresh.basiclib.ui.permission.c
            public void a(String str) {
                AppMethodBeat.i(4883, false);
                if (AnonymousClass1.this.b != null) {
                    AnonymousClass1.this.b.l_(str);
                }
                AppMethodBeat.o(4883);
            }

            @Override // cn.missfresh.basiclib.ui.permission.c
            public void b(String str) {
                AppMethodBeat.i(4885, false);
                c.a(c.this, AnonymousClass1.this.a);
                if (AnonymousClass1.this.b != null) {
                    AnonymousClass1.this.b.t_();
                }
                AppMethodBeat.o(4885);
            }

            @Override // cn.missfresh.basiclib.ui.permission.c
            public void c(String str) {
                AppMethodBeat.i(4887, false);
                c.a(c.this, AnonymousClass1.this.a, AnonymousClass1.this.c, AnonymousClass1.this.b, AnonymousClass1.this.d);
                AppMethodBeat.o(4887);
            }

            @Override // cn.missfresh.basiclib.ui.permission.c
            public void a() {
                AppMethodBeat.i(4890, false);
                c.a(c.this, AnonymousClass1.this.a);
                c.a(c.this, AnonymousClass1.this.a, AnonymousClass1.this.c, AnonymousClass1.this.b, AnonymousClass1.this.d);
                AppMethodBeat.o(4890);
            }
        }

        /* compiled from: PermissionUtil */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.basiclib.utils.permission.c$1$2  reason: invalid class name */
        public class AnonymousClass2 implements cn.missfresh.basiclib.ui.permission.c {
            @Override // cn.missfresh.basiclib.ui.permission.c
            public void a() {
            }

            @Override // cn.missfresh.basiclib.ui.permission.c
            public void b(String str) {
            }

            AnonymousClass2() {
            }

            @Override // cn.missfresh.basiclib.ui.permission.c
            public void a(String str) {
                AppMethodBeat.i(4893, false);
                if (AnonymousClass1.this.b != null) {
                    AnonymousClass1.this.b.l_(str);
                }
                AppMethodBeat.o(4893);
            }

            @Override // cn.missfresh.basiclib.ui.permission.c
            public void c(String str) {
                AppMethodBeat.i(4899, false);
                c.a(c.this, AnonymousClass1.this.a, AnonymousClass1.this.c, AnonymousClass1.this.b, AnonymousClass1.this.d);
                AppMethodBeat.o(4899);
            }
        }
    }

    private void a(a aVar, cn.missfresh.basiclib.ui.permission.a aVar2) {
        AppMethodBeat.i(4978, false);
        if (aVar.b) {
            if (d(aVar.a)) {
                aVar2.a(aVar.a, 0);
            } else {
                aVar2.a(aVar.a, 1);
            }
        } else if (aVar.c) {
            aVar2.a(aVar.a, 1);
        } else {
            aVar2.a(aVar.a, 2);
        }
        AppMethodBeat.o(4978);
    }

    private ArrayList<String> c(String... strArr) {
        AppMethodBeat.i(4981, false);
        if (strArr == null || strArr.length <= 0) {
            AppMethodBeat.o(4981);
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (int length = strArr.length - 1; length >= 0; length--) {
            if (!c(strArr[length])) {
                arrayList.add(strArr[length]);
            }
        }
        if (arrayList.size() > 0) {
            AppMethodBeat.o(4981);
            return arrayList;
        }
        AppMethodBeat.o(4981);
        return null;
    }

    public String[] b(String... strArr) {
        AppMethodBeat.i(4982, false);
        ArrayList<String> c = c(strArr);
        if (c == null || c.size() <= 0) {
            AppMethodBeat.o(4982);
            return null;
        }
        String[] strArr2 = (String[]) c.toArray(new String[c.size()]);
        AppMethodBeat.o(4982);
        return strArr2;
    }

    public void a(Fragment fragment, cn.missfresh.basiclib.ui.permission.b bVar, String str) {
        AppMethodBeat.i(4983, false);
        if (fragment == null || fragment.getContext() == null || fragment.getActivity() == null) {
            if (bVar != null) {
                bVar.l_("");
            }
            AppMethodBeat.o(4983);
        } else if (c(str)) {
            a(fragment.getChildFragmentManager());
            if (bVar != null) {
                bVar.t_();
            }
            AppMethodBeat.o(4983);
        } else {
            if (this.a == null) {
                this.a = new b(fragment);
            }
            a(fragment.getChildFragmentManager(), this.a, bVar, str);
            AppMethodBeat.o(4983);
        }
    }

    public void a(FragmentActivity fragmentActivity, cn.missfresh.basiclib.ui.permission.b bVar, String str) {
        AppMethodBeat.i(4986, false);
        if (fragmentActivity == null) {
            if (bVar != null) {
                bVar.l_(str);
            }
            AppMethodBeat.o(4986);
        } else if (c(str)) {
            a(fragmentActivity.getSupportFragmentManager());
            if (bVar != null) {
                bVar.t_();
            }
            AppMethodBeat.o(4986);
        } else {
            if (this.a == null) {
                this.a = new b(fragmentActivity);
            }
            a(fragmentActivity.getSupportFragmentManager(), this.a, bVar, str);
            AppMethodBeat.o(4986);
        }
    }

    private void a(FragmentManager fragmentManager, b bVar, cn.missfresh.basiclib.ui.permission.b bVar2, String str) {
        AppMethodBeat.i(4987, false);
        a(fragmentManager, bVar, bVar2, str, false);
        AppMethodBeat.o(4987);
    }

    /* compiled from: PermissionUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.utils.permission.c$5  reason: invalid class name */
    public class AnonymousClass5 implements g<a> {
        final /* synthetic */ boolean a;
        final /* synthetic */ cn.missfresh.basiclib.ui.permission.b b;
        final /* synthetic */ FragmentManager c;
        final /* synthetic */ String d;

        AnonymousClass5(boolean z, cn.missfresh.basiclib.ui.permission.b bVar, FragmentManager fragmentManager, String str) {
            this.a = z;
            this.b = bVar;
            this.c = fragmentManager;
            this.d = str;
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(4937, false);
            a((a) obj);
            AppMethodBeat.o(4937);
        }

        public void a(a aVar) throws Exception {
            AppMethodBeat.i(4934, false);
            if (this.a) {
                c.a(c.this, this.b, aVar);
            } else {
                c.a(c.this, this.c, this.b, aVar, this.d);
            }
            AppMethodBeat.o(4934);
        }
    }

    private void a(FragmentManager fragmentManager, b bVar, cn.missfresh.basiclib.ui.permission.b bVar2, String str, boolean z) {
        AppMethodBeat.i(4992, false);
        if (bVar == null || fragmentManager == null) {
            if (bVar2 != null) {
                bVar2.l_("");
            }
            AppMethodBeat.o(4992);
            return;
        }
        bVar.b(str).e(new AnonymousClass5(z, bVar2, fragmentManager, str));
        AppMethodBeat.o(4992);
    }

    private void a(FragmentManager fragmentManager, cn.missfresh.basiclib.ui.permission.b bVar, a aVar, String str) {
        AppMethodBeat.i(4993, false);
        if (aVar.b) {
            if (d(aVar.a)) {
                a(fragmentManager);
                if (bVar != null) {
                    bVar.t_();
                }
            } else {
                a(fragmentManager, new AnonymousClass6(bVar, fragmentManager), str);
            }
        } else if (!aVar.c) {
            a(fragmentManager, new AnonymousClass7(bVar, fragmentManager), str);
        }
        AppMethodBeat.o(4993);
    }

    /* compiled from: PermissionUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.utils.permission.c$6  reason: invalid class name */
    public class AnonymousClass6 implements cn.missfresh.basiclib.ui.permission.c {
        final /* synthetic */ cn.missfresh.basiclib.ui.permission.b a;
        final /* synthetic */ FragmentManager b;

        @Override // cn.missfresh.basiclib.ui.permission.c
        public void a() {
        }

        AnonymousClass6(cn.missfresh.basiclib.ui.permission.b bVar, FragmentManager fragmentManager) {
            this.a = bVar;
            this.b = fragmentManager;
        }

        @Override // cn.missfresh.basiclib.ui.permission.c
        public void a(String str) {
            AppMethodBeat.i(4943, false);
            cn.missfresh.basiclib.ui.permission.b bVar = this.a;
            if (bVar != null) {
                bVar.l_(str);
            }
            AppMethodBeat.o(4943);
        }

        @Override // cn.missfresh.basiclib.ui.permission.c
        public void b(String str) {
            AppMethodBeat.i(4945, false);
            c.a(c.this, this.b);
            cn.missfresh.basiclib.ui.permission.b bVar = this.a;
            if (bVar != null) {
                bVar.t_();
            }
            AppMethodBeat.o(4945);
        }

        @Override // cn.missfresh.basiclib.ui.permission.c
        public void c(String str) {
            AppMethodBeat.i(4947, false);
            c cVar = c.this;
            c.a(cVar, this.b, cVar.a, this.a, str);
            AppMethodBeat.o(4947);
        }
    }

    /* compiled from: PermissionUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.utils.permission.c$7  reason: invalid class name */
    public class AnonymousClass7 implements cn.missfresh.basiclib.ui.permission.c {
        final /* synthetic */ cn.missfresh.basiclib.ui.permission.b a;
        final /* synthetic */ FragmentManager b;

        @Override // cn.missfresh.basiclib.ui.permission.c
        public void a() {
        }

        AnonymousClass7(cn.missfresh.basiclib.ui.permission.b bVar, FragmentManager fragmentManager) {
            this.a = bVar;
            this.b = fragmentManager;
        }

        @Override // cn.missfresh.basiclib.ui.permission.c
        public void a(String str) {
            AppMethodBeat.i(4953, false);
            cn.missfresh.basiclib.ui.permission.b bVar = this.a;
            if (bVar != null) {
                bVar.l_(str);
            }
            AppMethodBeat.o(4953);
        }

        @Override // cn.missfresh.basiclib.ui.permission.c
        public void b(String str) {
            AppMethodBeat.i(4955, false);
            c.a(c.this, this.b);
            cn.missfresh.basiclib.ui.permission.b bVar = this.a;
            if (bVar != null) {
                bVar.t_();
            }
            AppMethodBeat.o(4955);
        }

        @Override // cn.missfresh.basiclib.ui.permission.c
        public void c(String str) {
            AppMethodBeat.i(4957, false);
            c cVar = c.this;
            c.a(cVar, this.b, cVar.a, this.a, str);
            AppMethodBeat.o(4957);
        }
    }

    private void a(cn.missfresh.basiclib.ui.permission.b bVar, a aVar) {
        AppMethodBeat.i(4994, false);
        if (!aVar.b || !d(aVar.a)) {
            if (bVar != null) {
                bVar.l_(aVar.a);
            }
        } else if (bVar != null) {
            bVar.t_();
        }
        AppMethodBeat.o(4994);
    }

    private boolean d(String str) {
        AppMethodBeat.i(4996, false);
        boolean a = a.a(str);
        AppMethodBeat.o(4996);
        return a;
    }

    private void a(FragmentManager fragmentManager) {
        AppMethodBeat.i(4997, false);
        if (!this.b) {
            AppMethodBeat.o(4997);
            return;
        }
        this.c = (PermissionExplainDialog) fragmentManager.findFragmentByTag("fragment_explain");
        PermissionExplainDialog permissionExplainDialog = this.c;
        if (permissionExplainDialog != null && permissionExplainDialog.isAdded()) {
            this.c.dismissAllowingStateLoss();
        }
        AppMethodBeat.o(4997);
    }

    private void a(FragmentManager fragmentManager, cn.missfresh.basiclib.ui.permission.c cVar, String str) {
        AppMethodBeat.i(4999, false);
        a(fragmentManager, cVar, str, false);
        AppMethodBeat.o(4999);
    }

    private void a(FragmentManager fragmentManager, cn.missfresh.basiclib.ui.permission.c cVar, String str, boolean z) {
        AppMethodBeat.i(5001, false);
        if (!this.b) {
            cVar.a(str);
            AppMethodBeat.o(5001);
        } else if (fragmentManager == null) {
            AppMethodBeat.o(5001);
        } else {
            this.c = (PermissionExplainDialog) fragmentManager.findFragmentByTag("fragment_explain");
            PermissionExplainDialog permissionExplainDialog = this.c;
            if (permissionExplainDialog == null) {
                this.c = PermissionExplainDialog.a(str);
                this.c.a(cVar);
                this.c.a(z);
                fragmentManager.beginTransaction().add(this.c, "fragment_explain").commitAllowingStateLoss();
            } else {
                permissionExplainDialog.b(str);
                this.c.a(z);
                fragmentManager.beginTransaction().show(this.c).commitAllowingStateLoss();
            }
            AppMethodBeat.o(5001);
        }
    }

    public static String a(String str) {
        AppMethodBeat.i(TbsReaderView.ReaderCallback.READER_PLUGIN_SO_VERSION, false);
        String a = a(b.a().b(), str);
        AppMethodBeat.o(TbsReaderView.ReaderCallback.READER_PLUGIN_SO_VERSION);
        return a;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002b, code lost:
        if (r6.equals(android.Manifest.permission.READ_CONTACTS) != false) goto L_0x00a1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r5, java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 342
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.basiclib.utils.permission.c.a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0016, code lost:
        if (r3.equals(android.Manifest.permission.READ_CONTACTS) != false) goto L_0x008c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(java.lang.String r3) {
        /*
            r0 = 0
            r1 = 5035(0x13ab, float:7.056E-42)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            int r2 = r3.hashCode()
            switch(r2) {
                case -2062386608: goto L_0x007f;
                case -1888586689: goto L_0x0074;
                case -406040016: goto L_0x0069;
                case -63024214: goto L_0x005e;
                case -5573545: goto L_0x0053;
                case 52602690: goto L_0x0047;
                case 112197485: goto L_0x003c;
                case 214526995: goto L_0x0031;
                case 463403621: goto L_0x0025;
                case 1365911975: goto L_0x001a;
                case 1977429404: goto L_0x000f;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x008b
        L_0x000f:
            java.lang.String r2 = "android.permission.READ_CONTACTS"
            boolean r3 = r3.equals(r2)
            if (r3 == 0) goto L_0x008b
            goto L_0x008c
        L_0x001a:
            java.lang.String r0 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 6
            goto L_0x008c
        L_0x0025:
            java.lang.String r0 = "android.permission.CAMERA"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 10
            goto L_0x008c
        L_0x0031:
            java.lang.String r0 = "android.permission.WRITE_CONTACTS"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 1
            goto L_0x008c
        L_0x003c:
            java.lang.String r0 = "android.permission.CALL_PHONE"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 5
            goto L_0x008c
        L_0x0047:
            java.lang.String r0 = "android.permission.SEND_SMS"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 8
            goto L_0x008c
        L_0x0053:
            java.lang.String r0 = "android.permission.READ_PHONE_STATE"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 4
            goto L_0x008c
        L_0x005e:
            java.lang.String r0 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 3
            goto L_0x008c
        L_0x0069:
            java.lang.String r0 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 7
            goto L_0x008c
        L_0x0074:
            java.lang.String r0 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 2
            goto L_0x008c
        L_0x007f:
            java.lang.String r0 = "android.permission.READ_SMS"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x008b
            r0 = 9
            goto L_0x008c
        L_0x008b:
            r0 = -1
        L_0x008c:
            switch(r0) {
                case 0: goto L_0x00b9;
                case 1: goto L_0x00b9;
                case 2: goto L_0x00b3;
                case 3: goto L_0x00b3;
                case 4: goto L_0x00ad;
                case 5: goto L_0x00a7;
                case 6: goto L_0x00a1;
                case 7: goto L_0x00a1;
                case 8: goto L_0x009b;
                case 9: goto L_0x009b;
                case 10: goto L_0x0095;
                default: goto L_0x008f;
            }
        L_0x008f:
            int r3 = cn.missfresh.basiclib.R.drawable.icon_permission_hint_basic
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r3
        L_0x0095:
            int r3 = cn.missfresh.basiclib.R.drawable.icon_permission_storage
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r3
        L_0x009b:
            int r3 = cn.missfresh.basiclib.R.drawable.icon_permission_storage
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r3
        L_0x00a1:
            int r3 = cn.missfresh.basiclib.R.drawable.icon_permission_storage
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r3
        L_0x00a7:
            int r3 = cn.missfresh.basiclib.R.drawable.icon_permission_storage
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r3
        L_0x00ad:
            int r3 = cn.missfresh.basiclib.R.drawable.icon_permission_phone
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r3
        L_0x00b3:
            int r3 = cn.missfresh.basiclib.R.drawable.icon_permission_location
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r3
        L_0x00b9:
            int r3 = cn.missfresh.basiclib.R.drawable.icon_permission_storage
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r3
            switch-data {-2062386608->0x007f, -1888586689->0x0074, -406040016->0x0069, -63024214->0x005e, -5573545->0x0053, 52602690->0x0047, 112197485->0x003c, 214526995->0x0031, 463403621->0x0025, 1365911975->0x001a, 1977429404->0x000f, }
            switch-data {0->0x00b9, 1->0x00b9, 2->0x00b3, 3->0x00b3, 4->0x00ad, 5->0x00a7, 6->0x00a1, 7->0x00a1, 8->0x009b, 9->0x009b, 10->0x0095, }
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.basiclib.utils.permission.c.b(java.lang.String):int");
    }

    public void a() {
        AppMethodBeat.i(TbsReaderView.ReaderCallback.READER_PLUGIN_COMMAND_TEXT_FIND, false);
        a.a(b.a().b());
        AppMethodBeat.o(TbsReaderView.ReaderCallback.READER_PLUGIN_COMMAND_TEXT_FIND);
    }
}
