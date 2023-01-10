package cn.missfresh.tinkerlib;

import android.app.job.JobInfo;
import android.text.format.DateUtils;
import android.util.TimedRemoteCaller;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

/* compiled from: TinkerReport */
public class e {
    private static a a;

    /* compiled from: TinkerReport */
    /* access modifiers changed from: package-private */
    public interface a {
        void a(int i);

        void a(String str);
    }

    public static void a(boolean z) {
        AppMethodBeat.i(12880, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12880);
            return;
        }
        aVar.a(2);
        a.a(70);
        if (z) {
            a.a(3);
        }
        AppMethodBeat.o(12880);
    }

    public static void a(int i) {
        AppMethodBeat.i(12883, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12883);
            return;
        }
        switch (i) {
            case -24:
                aVar.a(80);
                break;
            case -23:
                aVar.a(79);
                break;
            case -22:
                aVar.a(78);
                break;
            case -21:
                aVar.a(76);
                break;
            case -20:
                aVar.a(75);
                break;
            default:
                switch (i) {
                    case -6:
                        aVar.a(77);
                        break;
                    case -5:
                        aVar.a(81);
                        break;
                    case -4:
                        aVar.a(73);
                        break;
                    case -3:
                        aVar.a(72);
                        break;
                    case -2:
                        aVar.a(74);
                        break;
                    case -1:
                        aVar.a(71);
                        break;
                }
        }
        AppMethodBeat.o(12883);
    }

    public static void b(int i) {
        AppMethodBeat.i(12885, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12885);
            return;
        }
        switch (i) {
            case -9:
                aVar.a(MetricsProto.MetricsEvent.QS_EDIT);
                break;
            case -8:
                aVar.a(MetricsProto.MetricsEvent.OVERVIEW_DISMISS_ALL);
                break;
            case -7:
                aVar.a(MetricsProto.MetricsEvent.ACTION_SCOPED_DIRECTORY_ACCESS_DENIED_AND_PERSIST_BY_FOLDER);
                break;
            case -6:
                aVar.a(354);
                break;
            case -5:
                aVar.a(353);
                break;
            case -4:
                aVar.a(352);
                break;
            case -3:
                aVar.a(351);
                break;
            case -2:
                aVar.a(MetricsProto.MetricsEvent.ACTION_SCOPED_DIRECTORY_ACCESS_DENIED_AND_PERSIST_BY_PACKAGE);
                break;
            case -1:
                aVar.a(350);
                break;
        }
        AppMethodBeat.o(12885);
    }

    public static void a(long j) {
        AppMethodBeat.i(12886, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12886);
            return;
        }
        aVar.a(6);
        if (j < 0) {
            TinkerLog.e("Tinker.TinkerReport", "hp_report report load cost failed, invalid cost", new Object[0]);
            AppMethodBeat.o(12886);
            return;
        }
        if (j <= 500) {
            a.a(400);
        } else if (j <= 1000) {
            a.a(401);
        } else if (j <= 3000) {
            a.a(402);
        } else if (j <= TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS) {
            a.a(403);
        } else {
            a.a(404);
        }
        AppMethodBeat.o(12886);
    }

    public static void a() {
        AppMethodBeat.i(12887, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12887);
            return;
        }
        aVar.a(309);
        AppMethodBeat.o(12887);
    }

    public static void c(int i) {
        AppMethodBeat.i(12888, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12888);
            return;
        }
        switch (i) {
            case 1:
                aVar.a(305);
                break;
            case 2:
                aVar.a(306);
                break;
            case 3:
                aVar.a(303);
                break;
            case 4:
                aVar.a(307);
                break;
            case 5:
                aVar.a(304);
                break;
            case 6:
                aVar.a(308);
                break;
        }
        AppMethodBeat.o(12888);
    }

    public static void a(int i, Throwable th) {
        AppMethodBeat.i(12890, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12890);
            return;
        }
        if (i == 0) {
            aVar.a(MetricsProto.MetricsEvent.ACTION_HIDE_APP_DISAMBIG_APP_FEATURED);
        } else if (i == 1) {
            aVar.a(450);
            a aVar2 = a;
            aVar2.a("Tinker Exception:interpret occur exception " + g.b(th));
        } else if (i == 2) {
            aVar.a(MetricsProto.MetricsEvent.ACTION_SHOW_APP_DISAMBIG_APP_FEATURED);
            a aVar3 = a;
            aVar3.a("Tinker Exception:interpret occur exception " + g.b(th));
        }
        AppMethodBeat.o(12890);
    }

    public static void d(int i) {
        AppMethodBeat.i(12891, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12891);
            return;
        }
        if (i == 3) {
            aVar.a(300);
        } else if (i == 5) {
            aVar.a(301);
        } else if (i == 6) {
            aVar.a(302);
        }
        AppMethodBeat.o(12891);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.Throwable r6, int r7) {
        /*
            r0 = 12893(0x325d, float:1.8067E-41)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            cn.missfresh.tinkerlib.e$a r2 = cn.missfresh.tinkerlib.e.a
            if (r2 != 0) goto L_0x000e
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x000e:
            r3 = -4
            r4 = 1
            if (r7 == r3) goto L_0x00ce
            r3 = -3
            java.lang.String r5 = "Tinker.TinkerReport"
            if (r7 == r3) goto L_0x007b
            r3 = -2
            if (r7 == r3) goto L_0x0027
            r3 = -1
            if (r7 == r3) goto L_0x0020
            goto L_0x00d3
        L_0x0020:
            r7 = 250(0xfa, float:3.5E-43)
            r2.a(r7)
            goto L_0x00d3
        L_0x0027:
            java.lang.String r7 = r6.getMessage()
            java.lang.String r2 = "checkDexInstall failed"
            boolean r7 = r7.contains(r2)
            if (r7 == 0) goto L_0x0058
            cn.missfresh.tinkerlib.e$a r7 = cn.missfresh.tinkerlib.e.a
            r2 = 253(0xfd, float:3.55E-43)
            r7.a(r2)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r2 = "tinker dex check fail:"
            r7.append(r2)
            java.lang.String r2 = r6.getMessage()
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.tencent.tinker.lib.util.TinkerLog.e(r5, r7, r1)
            goto L_0x00d4
        L_0x0058:
            cn.missfresh.tinkerlib.e$a r7 = cn.missfresh.tinkerlib.e.a
            r2 = 252(0xfc, float:3.53E-43)
            r7.a(r2)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r2 = "tinker dex reflect fail:"
            r7.append(r2)
            java.lang.String r2 = r6.getMessage()
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            java.lang.Object[] r2 = new java.lang.Object[r1]
            com.tencent.tinker.lib.util.TinkerLog.e(r5, r7, r2)
            goto L_0x00d3
        L_0x007b:
            java.lang.String r7 = r6.getMessage()
            java.lang.String r2 = "checkResInstall failed"
            boolean r7 = r7.contains(r2)
            if (r7 == 0) goto L_0x00ab
            cn.missfresh.tinkerlib.e$a r7 = cn.missfresh.tinkerlib.e.a
            r2 = 255(0xff, float:3.57E-43)
            r7.a(r2)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r2 = "tinker res check fail:"
            r7.append(r2)
            java.lang.String r2 = r6.getMessage()
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.tencent.tinker.lib.util.TinkerLog.e(r5, r7, r1)
            goto L_0x00d4
        L_0x00ab:
            cn.missfresh.tinkerlib.e$a r7 = cn.missfresh.tinkerlib.e.a
            r2 = 254(0xfe, float:3.56E-43)
            r7.a(r2)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r2 = "tinker res reflect fail:"
            r7.append(r2)
            java.lang.String r2 = r6.getMessage()
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            java.lang.Object[] r2 = new java.lang.Object[r1]
            com.tencent.tinker.lib.util.TinkerLog.e(r5, r7, r2)
            goto L_0x00d3
        L_0x00ce:
            r7 = 251(0xfb, float:3.52E-43)
            r2.a(r7)
        L_0x00d3:
            r4 = r1
        L_0x00d4:
            if (r4 != 0) goto L_0x00f1
            cn.missfresh.tinkerlib.e$a r7 = cn.missfresh.tinkerlib.e.a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Tinker Exception:load tinker occur exception "
            r1.append(r2)
            java.lang.String r6 = cn.missfresh.tinkerlib.g.b(r6)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r7.a(r6)
        L_0x00f1:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.tinkerlib.e.a(java.lang.Throwable, int):void");
    }

    public static void b() {
        AppMethodBeat.i(12896, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12896);
            return;
        }
        aVar.a(4);
        AppMethodBeat.o(12896);
    }

    public static void a(Throwable th) {
        AppMethodBeat.i(12898, false);
        if (a == null) {
            AppMethodBeat.o(12898);
            return;
        }
        if (th.getMessage().contains(ShareConstants.CHECK_DEX_OAT_EXIST_FAIL)) {
            a.a(122);
        } else if (th.getMessage().contains(ShareConstants.CHECK_DEX_OAT_FORMAT_FAIL)) {
            a.a(123);
        } else {
            a.a(121);
            a aVar = a;
            aVar.a("Tinker Exception:apply tinker occur exception " + g.b(th));
        }
        AppMethodBeat.o(12898);
    }

    public static void c() {
        AppMethodBeat.i(12900, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12900);
            return;
        }
        aVar.a(124);
        AppMethodBeat.o(12900);
    }

    public static void d() {
        AppMethodBeat.i(12901, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12901);
            return;
        }
        aVar.a(180);
        AppMethodBeat.o(12901);
    }

    public static void e(int i) {
        AppMethodBeat.i(12904, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12904);
            return;
        }
        if (i == 1) {
            aVar.a(181);
        } else if (i == 3) {
            aVar.a(182);
        } else if (i == 5) {
            aVar.a(183);
        } else if (i == 6) {
            aVar.a(184);
        }
        AppMethodBeat.o(12904);
    }

    public static void a(long j, boolean z) {
        AppMethodBeat.i(12906, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12906);
            return;
        }
        if (z) {
            aVar.a(5);
        }
        if (z) {
            a.a(100);
        } else {
            a.a(101);
        }
        TinkerLog.i("Tinker.TinkerReport", "hp_report report apply cost = %d", Long.valueOf(j));
        if (j < 0) {
            TinkerLog.e("Tinker.TinkerReport", "hp_report report apply cost failed, invalid cost", new Object[0]);
            AppMethodBeat.o(12906);
            return;
        }
        if (j <= TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS) {
            if (z) {
                a.a(200);
            } else {
                a.a(205);
            }
        } else if (j <= JobInfo.MIN_BACKOFF_MILLIS) {
            if (z) {
                a.a(201);
            } else {
                a.a(206);
            }
        } else if (j <= 30000) {
            if (z) {
                a.a(202);
            } else {
                a.a(207);
            }
        } else if (j <= DateUtils.MINUTE_IN_MILLIS) {
            if (z) {
                a.a(203);
            } else {
                a.a(208);
            }
        } else if (z) {
            a.a(204);
        } else {
            a.a(209);
        }
        AppMethodBeat.o(12906);
    }

    public static void f(int i) {
        AppMethodBeat.i(12908, false);
        if (a == null) {
            AppMethodBeat.o(12908);
            return;
        }
        TinkerLog.i("Tinker.TinkerReport", "hp_report package check failed, error = %d", Integer.valueOf(i));
        switch (i) {
            case -9:
                a.a(158);
                break;
            case -8:
                a.a(157);
                break;
            case -7:
                a.a(156);
                break;
            case -6:
                a.a(154);
                break;
            case -5:
                a.a(153);
                break;
            case -4:
                a.a(152);
                break;
            case -3:
                a.a(151);
                break;
            case -2:
                a.a(155);
                break;
            case -1:
                a.a(150);
                break;
        }
        AppMethodBeat.o(12908);
    }

    public static void b(Throwable th) {
        AppMethodBeat.i(12910, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12910);
            return;
        }
        aVar.a(120);
        a aVar2 = a;
        aVar2.a("Tinker Exception:apply tinker occur exception " + g.b(th));
        AppMethodBeat.o(12910);
    }

    public static void e() {
        AppMethodBeat.i(12912, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12912);
            return;
        }
        aVar.a(7);
        AppMethodBeat.o(12912);
    }

    public static void f() {
        AppMethodBeat.i(12913, false);
        if (a == null) {
            AppMethodBeat.o(12913);
            return;
        }
        if (ShareTinkerInternals.isVmArt()) {
            a.a(9);
        } else {
            a.a(8);
        }
        AppMethodBeat.o(12913);
    }

    public static void g() {
        AppMethodBeat.i(12915, false);
        a aVar = a;
        if (aVar == null) {
            AppMethodBeat.o(12915);
            return;
        }
        aVar.a(10);
        AppMethodBeat.o(12915);
    }
}
