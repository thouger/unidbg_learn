package cn.missfresh.risk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import cn.missfresh.risk.bean.BatteryBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.taobao.accs.common.Constants;

/* compiled from: BatteryUtil */
public class a {
    private static String a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "unknow" : "full" : "notCharging" : "disCharging" : "charging" : "unknown";
    }

    public static BatteryBean a(Context context) {
        AppMethodBeat.i(Constants.PORT, false);
        BatteryBean batteryBean = new BatteryBean();
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            if (registerReceiver == null) {
                AppMethodBeat.o(Constants.PORT);
                return batteryBean;
            }
            int intExtra = registerReceiver.getIntExtra("status", -1);
            int intExtra2 = registerReceiver.getIntExtra("level", -1);
            int intExtra3 = registerReceiver.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
            batteryBean.setBatteryLevel(intExtra2);
            batteryBean.setBatteryVoltage(intExtra3);
            batteryBean.setBatteryTemperature(((float) registerReceiver.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1)) * 0.1f);
            batteryBean.setState(a(intExtra));
            AppMethodBeat.o(Constants.PORT);
            return batteryBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
