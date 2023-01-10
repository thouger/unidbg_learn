package com.umeng.message.common.inter;

import com.umeng.message.UTrack;
import org.json.JSONObject;

public interface IUtrack {
    void addAlias(String str, String str2, JSONObject jSONObject, UTrack.ICallBack iCallBack) throws Exception;

    void deleteAlias(String str, String str2, JSONObject jSONObject, UTrack.ICallBack iCallBack) throws Exception;

    void sendAliasFailLog(JSONObject jSONObject);

    void sendMsgLog(JSONObject jSONObject, String str, int i) throws Exception;

    void sendRegisterLog(JSONObject jSONObject) throws Exception;

    void setAlias(String str, String str2, JSONObject jSONObject, UTrack.ICallBack iCallBack) throws Exception;

    void trackAppLaunch(JSONObject jSONObject) throws Exception;

    void trackLocation(JSONObject jSONObject) throws Exception;

    void trackRegister(JSONObject jSONObject, String str) throws Exception;
}
