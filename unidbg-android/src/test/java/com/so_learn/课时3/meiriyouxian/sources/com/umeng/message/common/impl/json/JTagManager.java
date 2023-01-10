package com.umeng.message.common.impl.json;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.inter.ITagManager;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class JTagManager implements ITagManager {
    private static final String a = JTagManager.class.getSimpleName();
    private Context b;

    public JTagManager(Context context) {
        this.b = context;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result addTags(JSONObject jSONObject, String... strArr) throws Exception {
        JSONObject jSONObject2;
        String str = MsgConstant.TAG_ENDPOINT + "/add";
        try {
            jSONObject2 = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            UMLog.mutlInfo(a, 2, "\u6dfb\u52a0tag UnknownHostException");
            jSONObject2 = JUtrack.sendRequest(this.b, jSONObject, str);
        }
        ITagManager.Result result = new ITagManager.Result(jSONObject2, false);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).addTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
            MessageSharedPrefs.getInstance(this.b).add_addTagsInterval(result.toString());
        }
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result addWeightedTags(JSONObject jSONObject, Hashtable<String, Integer> hashtable) throws Exception {
        JSONObject jSONObject2;
        String str = MsgConstant.WEIGHTED_TAG_ENDPOINT + "/incr";
        try {
            jSONObject2 = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            UMLog.mutlInfo(a, 2, "\u6dfb\u52a0\u52a0\u6743\u6807\u7b7e UnknownHostException");
            jSONObject2 = JUtrack.sendRequest(this.b, jSONObject, str);
        }
        UMLog.mutlInfo(a, 2, jSONObject2.toString());
        ITagManager.Result result = new ITagManager.Result(jSONObject2, true);
        MessageSharedPrefs.getInstance(this.b).setAddWeightedTagsInterval(result.toString());
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result update(JSONObject jSONObject, String... strArr) throws Exception {
        JSONObject jSONObject2;
        String str = MsgConstant.TAG_ENDPOINT + "/update";
        try {
            jSONObject2 = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = JUtrack.sendRequest(this.b, jSONObject, str);
        }
        ITagManager.Result result = new ITagManager.Result(jSONObject2, false);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).resetTags();
            MessageSharedPrefs.getInstance(this.b).addTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
        }
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result deleteTags(JSONObject jSONObject, String... strArr) throws Exception {
        JSONObject jSONObject2;
        String str = MsgConstant.TAG_ENDPOINT + "/delete";
        try {
            jSONObject2 = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = JUtrack.sendRequest(this.b, jSONObject, str);
        }
        ITagManager.Result result = new ITagManager.Result(jSONObject2, false);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).removeTags(strArr);
            MessageSharedPrefs.getInstance(this.b).setTagRemain(result.remain);
            MessageSharedPrefs.getInstance(this.b).add_deleteTagsInterval(result.toString());
        }
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result deleteWeightedTags(JSONObject jSONObject, String... strArr) throws Exception {
        JSONObject jSONObject2;
        String str = MsgConstant.WEIGHTED_TAG_ENDPOINT + "/delete";
        try {
            jSONObject2 = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = JUtrack.sendRequest(this.b, jSONObject, str);
        }
        ITagManager.Result result = new ITagManager.Result(jSONObject2, true);
        MessageSharedPrefs.getInstance(this.b).setDeleteWeightedTagsInterval(result.toString());
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public ITagManager.Result reset(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2;
        String str = MsgConstant.TAG_ENDPOINT + "/reset";
        try {
            jSONObject2 = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = JUtrack.sendRequest(this.b, jSONObject, str);
        }
        ITagManager.Result result = new ITagManager.Result(jSONObject2, false);
        if (TextUtils.equals(result.status, ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).resetTags();
        }
        return result;
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public List<String> getTags(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2;
        String str = MsgConstant.TAG_ENDPOINT + "/get";
        try {
            jSONObject2 = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = JUtrack.sendRequest(this.b, jSONObject, str);
        }
        ITagManager.Result result = new ITagManager.Result(jSONObject2, false);
        if (!TextUtils.equals(result.status, ITagManager.SUCCESS) || jSONObject2.getString("tags") == null) {
            return null;
        }
        UMLog.mutlInfo(a, 2, jSONObject2.getString("tags"));
        MessageSharedPrefs.getInstance(this.b).add_getTagsInteral(result.toString());
        return Arrays.asList(jSONObject2.getString("tags").split(Constants.ACCEPT_TIME_SEPARATOR_SP));
    }

    @Override // com.umeng.message.common.inter.ITagManager
    public Hashtable<String, Integer> getWeightedTags(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2;
        String str = MsgConstant.WEIGHTED_TAG_ENDPOINT + "/list";
        try {
            jSONObject2 = JUtrack.sendRequest(jSONObject, str);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = JUtrack.sendRequest(this.b, jSONObject, str);
        }
        MessageSharedPrefs.getInstance(this.b).setListWeightedTagsInterval(new ITagManager.Result(jSONObject2, true).toString());
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        JSONObject optJSONObject = jSONObject2.optJSONObject("data").optJSONObject("tags");
        if (optJSONObject != null) {
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashtable.put(next, Integer.valueOf(optJSONObject.getInt(next)));
            }
        }
        return hashtable;
    }
}
