package it.sephiroth.android.library.easing;

import android.os.Handler;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public final class EasingManager {
    static final Handler a = new Handler();

    public enum EaseType {
        EaseIn,
        EaseOut,
        EaseInOut,
        EaseNone;

        public static EaseType valueOf(String str) {
            AppMethodBeat.i(12682, false);
            EaseType easeType = (EaseType) Enum.valueOf(EaseType.class, str);
            AppMethodBeat.o(12682);
            return easeType;
        }

        static {
            AppMethodBeat.i(12687, false);
            AppMethodBeat.o(12687);
        }
    }

    static {
        AppMethodBeat.i(12725, false);
        AppMethodBeat.o(12725);
    }
}
