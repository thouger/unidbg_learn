package com.sijla.la;

import android.content.Context;
import com.sijla.lj.L;
import java.util.HashMap;
import java.util.Map;

public interface LContext {
    void call(String str, Object... objArr);

    Object doFile(String str, Object... objArr);

    Context getContext();

    Map getGlobalData();

    String getLCpath();

    String getLDir();

    L getLState();

    HashMap<String, String> getLibrarys();

    void regGc(a aVar);

    void sendError(String str, Exception exc);

    void sendMsg(String str);

    void set(String str, Object obj);
}
