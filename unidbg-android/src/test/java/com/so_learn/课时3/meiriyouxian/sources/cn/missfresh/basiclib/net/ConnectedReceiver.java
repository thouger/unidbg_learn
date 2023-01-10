package cn.missfresh.basiclib.net;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public abstract class ConnectedReceiver extends BroadcastReceiver {
    public static final IntentFilter a = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
}
