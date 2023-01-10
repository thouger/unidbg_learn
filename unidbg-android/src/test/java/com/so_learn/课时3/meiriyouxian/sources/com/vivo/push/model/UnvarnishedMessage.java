package com.vivo.push.model;

import android.bluetooth.BluetoothClass;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.util.k;
import com.vivo.push.util.n;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UnvarnishedMessage {
    private static final String TAG = "UnvarnishedMessage";
    private String mMessage;
    private long mMsgId;
    private Map<String, String> mParams = new HashMap();
    private int mTargetType;
    private String mTragetContent;

    public UnvarnishedMessage() {
        AppMethodBeat.i(BluetoothClass.Device.WEARABLE_GLASSES, false);
        AppMethodBeat.o(BluetoothClass.Device.WEARABLE_GLASSES);
    }

    public UnvarnishedMessage(String str) {
        AppMethodBeat.i(1814, false);
        packToObj(str);
        AppMethodBeat.o(1814);
    }

    public long getMsgId() {
        return this.mMsgId;
    }

    public void setMsgId(long j) {
        this.mMsgId = j;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public String getTragetContent() {
        return this.mTragetContent;
    }

    public void setTragetContent(String str) {
        this.mTragetContent = str;
    }

    public int getTargetType() {
        return this.mTargetType;
    }

    public void setTargetType(int i) {
        this.mTargetType = i;
    }

    public Map<String, String> getParams() {
        return this.mParams;
    }

    public void setParams(Map<String, String> map) {
        this.mParams = map;
    }

    public String unpackToJson() {
        AppMethodBeat.i(1832, false);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.mTargetType);
        jSONArray.put(this.mTragetContent);
        jSONArray.put(this.mMessage);
        Object obj = this.mParams;
        if (obj == null) {
            obj = new HashMap();
        }
        jSONArray.put(obj);
        String jSONArray2 = jSONArray.toString();
        AppMethodBeat.o(1832);
        return jSONArray2;
    }

    private void packToObj(String str) {
        AppMethodBeat.i(1838, false);
        try {
            if (TextUtils.isEmpty(str)) {
                n.a(TAG, "unvarnishedMsg pack to obj is null");
                AppMethodBeat.o(1838);
                return;
            }
            JSONArray jSONArray = new JSONArray(str);
            this.mTargetType = jSONArray.optInt(0);
            this.mTragetContent = jSONArray.getString(1);
            this.mMessage = jSONArray.getString(2);
            this.mParams = k.a(new JSONObject(jSONArray.getString(3)));
            AppMethodBeat.o(1838);
        } catch (JSONException e) {
            e.printStackTrace();
            n.a(TAG, "unvarnishedMsg pack to obj error", e);
            AppMethodBeat.o(1838);
        }
    }
}
