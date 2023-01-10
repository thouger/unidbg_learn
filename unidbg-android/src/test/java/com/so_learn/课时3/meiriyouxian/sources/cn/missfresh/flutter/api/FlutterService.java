package cn.missfresh.flutter.api;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.flutter.a.a;
import cn.missfresh.module.base.common.providers.IFlutterService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.embedding.android.FlutterFragment;
import java.util.HashMap;

public class FlutterService implements IFlutterService {
    public void init(Context context) {
    }

    public Fragment getFlutterFragment(FragmentActivity fragmentActivity, String str, String str2, HashMap<String, Object> hashMap) {
        AppMethodBeat.i(20793, false);
        FlutterFragment a = a.a(fragmentActivity, str, str2, hashMap);
        AppMethodBeat.o(20793);
        return a;
    }
}
