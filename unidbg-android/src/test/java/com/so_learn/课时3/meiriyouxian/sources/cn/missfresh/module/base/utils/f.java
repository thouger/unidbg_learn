package cn.missfresh.module.base.utils;

import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.inf.ABConfig;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.tencent.connect.common.Constants;

/* compiled from: ABTestUtils */
public class f implements ABConfig {
    private static String a = "";

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String getTag() {
        return "ABTestUtils";
    }

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String[] getCaseIds() {
        return new String[]{"69", "1", "18", Constants.VIA_ACT_TYPE_NINETEEN, Constants.VIA_REPORT_TYPE_CHAT_VIDEO, Constants.VIA_REPORT_TYPE_CHAT_AUDIO, "36", "35", "57", "62", "65", "68", "92", "94", "97", "95", "104", "105", "107", "118", "119", "124", "122", "126", "135", "140", "136", "137", "138", "142", "146", "153", "161", "168", "174", "176", "179", "180", "181", "184", "186", "188"};
    }

    public static boolean a() {
        boolean z = false;
        AppMethodBeat.i(23005, false);
        if (ABTest.get("92") == 0) {
            z = true;
        }
        AppMethodBeat.o(23005);
        return z;
    }

    public static boolean b() {
        AppMethodBeat.i(23006, false);
        if (ABTest.get("69") == 1) {
            AppMethodBeat.o(23006);
            return true;
        }
        AppMethodBeat.o(23006);
        return false;
    }

    public static boolean c() {
        AppMethodBeat.i(23007, false);
        if (ABTest.get("95") == 1) {
            AppMethodBeat.o(23007);
            return true;
        }
        AppMethodBeat.o(23007);
        return false;
    }

    public static boolean d() {
        AppMethodBeat.i(23008, false);
        if (ABTest.get(Constants.VIA_REPORT_TYPE_CHAT_VIDEO) == 1) {
            AppMethodBeat.o(23008);
            return true;
        }
        AppMethodBeat.o(23008);
        return false;
    }

    public static boolean e() {
        AppMethodBeat.i(23009, false);
        if (ABTest.get(Constants.VIA_REPORT_TYPE_CHAT_AUDIO) == 1) {
            AppMethodBeat.o(23009);
            return true;
        }
        AppMethodBeat.o(23009);
        return false;
    }

    public static boolean f() {
        boolean z = false;
        AppMethodBeat.i(23010, false);
        if (ABTest.get("65") == 1) {
            z = true;
        }
        AppMethodBeat.o(23010);
        return z;
    }

    public static boolean g() {
        AppMethodBeat.i(23011, false);
        d.d("abtest", "AD SDK the page switch is " + ABTest.get("36"));
        if (ABTest.get("36") == 1) {
            AppMethodBeat.o(23011);
            return true;
        }
        AppMethodBeat.o(23011);
        return false;
    }

    public static int h() {
        AppMethodBeat.i(23012, false);
        int i = ABTest.get("36");
        AppMethodBeat.o(23012);
        return i;
    }

    public static boolean i() {
        boolean z = false;
        AppMethodBeat.i(23013, false);
        if (ABTest.get("57") == 1) {
            z = true;
        }
        AppMethodBeat.o(23013);
        return z;
    }

    public static boolean j() {
        boolean z = false;
        AppMethodBeat.i(23014, false);
        if (ABTest.get("62") == 1) {
            z = true;
        }
        AppMethodBeat.o(23014);
        return z;
    }

    public static boolean k() {
        boolean z = false;
        AppMethodBeat.i(23015, false);
        if (ABTest.get("68") == 1) {
            z = true;
        }
        AppMethodBeat.o(23015);
        return z;
    }

    public static boolean l() {
        boolean z = false;
        AppMethodBeat.i(23016, false);
        if (ABTest.get("94") == 1) {
            z = true;
        }
        AppMethodBeat.o(23016);
        return z;
    }

    public static int m() {
        AppMethodBeat.i(23017, false);
        int i = ABTest.get("104");
        AppMethodBeat.o(23017);
        return i;
    }

    public static boolean n() {
        boolean z = false;
        AppMethodBeat.i(23018, false);
        if (ABTest.get("105") == 1) {
            z = true;
        }
        AppMethodBeat.o(23018);
        return z;
    }

    public static boolean o() {
        boolean z = false;
        AppMethodBeat.i(23020, false);
        if (ABTest.get("122") == 1) {
            z = true;
        }
        AppMethodBeat.o(23020);
        return z;
    }

    public static boolean p() {
        boolean z = false;
        AppMethodBeat.i(23021, false);
        if (ABTest.get("126") == 1) {
            z = true;
        }
        AppMethodBeat.o(23021);
        return z;
    }

    public static boolean q() {
        boolean z = false;
        AppMethodBeat.i(23022, false);
        if (ABTest.get("135") == 1) {
            z = true;
        }
        AppMethodBeat.o(23022);
        return z;
    }

    public static boolean r() {
        boolean z = false;
        AppMethodBeat.i(23023, false);
        if (ABTest.get("137") == 1) {
            z = true;
        }
        AppMethodBeat.o(23023);
        return z;
    }

    public static boolean s() {
        boolean z = false;
        AppMethodBeat.i(23024, false);
        if (ABTest.get("142") == 1) {
            z = true;
        }
        AppMethodBeat.o(23024);
        return z;
    }

    public static boolean t() {
        boolean z = false;
        AppMethodBeat.i(23025, false);
        if (ABTest.get("153") == 1) {
            z = true;
        }
        AppMethodBeat.o(23025);
        return z;
    }

    public static boolean u() {
        boolean z = false;
        AppMethodBeat.i(23026, false);
        if (ABTest.get("146") != 0) {
            z = true;
        }
        AppMethodBeat.o(23026);
        return z;
    }

    public static boolean v() {
        boolean z = false;
        AppMethodBeat.i(23027, false);
        if (ABTest.get("168") != 0) {
            z = true;
        }
        AppMethodBeat.o(23027);
        return z;
    }

    public static boolean w() {
        boolean z = false;
        AppMethodBeat.i(23028, false);
        if (ABTest.get("161") == 1) {
            z = true;
        }
        AppMethodBeat.o(23028);
        return z;
    }

    public static boolean x() {
        boolean z = false;
        AppMethodBeat.i(23029, false);
        if (ABTest.get("174") == 1) {
            z = true;
        }
        AppMethodBeat.o(23029);
        return z;
    }

    public static boolean y() {
        boolean z = false;
        AppMethodBeat.i(23030, false);
        if (ABTest.get("176") == 1) {
            z = true;
        }
        AppMethodBeat.o(23030);
        return z;
    }

    public static boolean z() {
        boolean z = false;
        AppMethodBeat.i(23031, false);
        if (ABTest.get("181") == 1) {
            z = true;
        }
        AppMethodBeat.o(23031);
        return z;
    }

    public static boolean A() {
        boolean z = false;
        AppMethodBeat.i(23033, false);
        if (ABTest.get("188") == 1) {
            z = true;
        }
        AppMethodBeat.o(23033);
        return z;
    }

    public static int B() {
        AppMethodBeat.i(23034, false);
        int i = 1000;
        if (ABTest.get("186") >= 1000) {
            i = ABTest.get("186");
        }
        AppMethodBeat.o(23034);
        return i;
    }
}
