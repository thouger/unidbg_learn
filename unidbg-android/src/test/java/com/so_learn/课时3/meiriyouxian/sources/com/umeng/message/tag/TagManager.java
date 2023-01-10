package com.umeng.message.tag;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.UTrack;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.util.HttpRequest;
import com.umeng.message.util.e;
import com.xiaomi.mipush.sdk.Constants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class TagManager {
    private static final String a = TagManager.class.getSimpleName();
    private static final String b = "ok";
    private static final String c = "fail";
    private static TagManager d;
    private static ITagManager f;
    private Context e;

    public interface TCallBack {
        void onMessage(boolean z, ITagManager.Result result);
    }

    public interface TagListCallBack {
        void onMessage(boolean z, List<String> list);
    }

    public interface WeightedTagListCallBack {
        void onMessage(boolean z, Hashtable<String, Integer> hashtable);
    }

    private TagManager(Context context) {
        this.e = context.getApplicationContext();
    }

    public static synchronized TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (d == null) {
                d = new TagManager(context.getApplicationContext());
                try {
                    f = (ITagManager) Class.forName("com.umeng.message.common.impl.json.JTagManager").getConstructor(Context.class).newInstance(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            tagManager = d;
        }
        return tagManager;
    }

    /* renamed from: com.umeng.message.tag.TagManager$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String[] a;
        final /* synthetic */ TCallBack b;

        AnonymousClass1(String[] strArr, TCallBack tCallBack) {
            this.a = strArr;
            this.b = tCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            ITagManager.Result result = new ITagManager.Result();
            String[] strArr = this.a;
            if (strArr == null || strArr.length == 0) {
                UMLog.mutlInfo(TagManager.a, 0, "No tags");
                result.setErrors("No tags");
                this.b.onMessage(false, result);
            } else if (!TagManager.this.d()) {
                UMLog.mutlInfo(TagManager.a, 0, "No utdid or device_token");
                result.setErrors("No utdid or device_token");
                this.b.onMessage(false, result);
            } else if (TagManager.this.e()) {
                UMLog.mutlInfo(TagManager.a, 0, "Tag API is disabled by the server");
                result.setErrors("Tag API is disabled by the server");
                this.b.onMessage(false, result);
            } else {
                TagManager tagManager = TagManager.this;
                String a = tagManager.a(MessageSharedPrefs.getInstance(tagManager.e).get_addTagsInterval(), this.a);
                if (!TextUtils.isEmpty(a)) {
                    UMLog.mutlInfo(TagManager.a, 0, a);
                    result.setErrors(a);
                    this.b.onMessage(false, result);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                String[] strArr2 = this.a;
                if (strArr2.length > 0) {
                    for (String str : strArr2) {
                        if (!MessageSharedPrefs.getInstance(TagManager.this.e).isTagSet(str) && !arrayList.contains(str)) {
                            byte[] bArr = null;
                            try {
                                bArr = str.getBytes("UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            if (bArr.length <= 128 && bArr.length >= 0) {
                                arrayList.add(str);
                            }
                        }
                    }
                }
                if (arrayList.size() == 0) {
                    this.b.onMessage(true, TagManager.this.f());
                    return;
                }
                try {
                    JSONObject c = TagManager.this.c();
                    c.put("tags", e.a(arrayList));
                    this.b.onMessage(true, TagManager.f.addTags(c, this.a));
                } catch (Exception unused) {
                    UMLog.mutlInfo(TagManager.a, 0, "\u6dfb\u52a0tag\u5f02\u5e38");
                }
            }
        }
    }

    public void addTags(TCallBack tCallBack, String... strArr) {
        com.umeng.message.common.e.a(new AnonymousClass1(strArr, tCallBack));
    }

    /* renamed from: com.umeng.message.tag.TagManager$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ TCallBack a;
        final /* synthetic */ Hashtable b;

        AnonymousClass2(TCallBack tCallBack, Hashtable hashtable) {
            this.a = tCallBack;
            this.b = hashtable;
        }

        @Override // java.lang.Runnable
        public void run() {
            ITagManager.Result result = new ITagManager.Result();
            if (!TagManager.this.d()) {
                result.setErrors("No utdid or device token");
                UMLog.mutlInfo(TagManager.a, 0, "No utdid or device token");
                this.a.onMessage(false, result);
                return;
            }
            Hashtable hashtable = this.b;
            if (hashtable == null || hashtable.size() == 0) {
                UMLog.mutlInfo(TagManager.a, 0, "No weighted tags");
                result.setErrors("No weighted tags");
                this.a.onMessage(false, result);
            } else if (this.b.size() > 64) {
                UMLog.mutlInfo(TagManager.a, 0, "The maximum number of adding weighted tags per request is 64");
                result.setErrors("The maximum number of adding weighted tags per request is 64");
                this.a.onMessage(false, result);
            } else {
                TagManager tagManager = TagManager.this;
                String a = tagManager.a(MessageSharedPrefs.getInstance(tagManager.e).getAddWeightedTagsInterval(), this.b);
                if (!TextUtils.isEmpty(a)) {
                    UMLog.mutlInfo(TagManager.a, 0, a);
                    result.setErrors(a);
                    this.a.onMessage(false, result);
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject();
                    for (String str : this.b.keySet()) {
                        jSONObject.put(str, ((Integer) this.b.get(str)).intValue());
                    }
                    JSONObject c = TagManager.this.c();
                    c.put("tags", jSONObject);
                    this.a.onMessage(true, TagManager.f.addWeightedTags(c, this.b));
                } catch (Exception e) {
                    e.printStackTrace();
                    UMLog.mutlInfo(TagManager.a, 0, "\u6dfb\u52a0\u52a0\u6743\u6807\u7b7e\u5f02\u5e38");
                }
            }
        }
    }

    public void addWeightedTags(TCallBack tCallBack, Hashtable<String, Integer> hashtable) {
        com.umeng.message.common.e.a(new AnonymousClass2(tCallBack, hashtable));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String a(String str, String... strArr) {
        String str2;
        byte[] bArr;
        ArrayList arrayList = new ArrayList();
        if (strArr != null && strArr.length > 0) {
            for (String str3 : strArr) {
                if (!MessageSharedPrefs.getInstance(this.e).isTagSet(str3) && !arrayList.contains(str3)) {
                    try {
                        bArr = str3.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        bArr = null;
                    }
                    if (bArr.length > 128 || bArr.length < 0) {
                        return "\u90e8\u5206Tags\u957f\u5ea6\u4e0d\u5728\u9650\u52360\u5230128\u4e2a\u5b57\u7b26\u4e4b\u95f4";
                    }
                    arrayList.add(str3);
                }
            }
        }
        if (str == null) {
            return null;
        }
        try {
            ITagManager.Result result = new ITagManager.Result(new JSONObject(str), false);
            long currentTimeMillis = System.currentTimeMillis();
            if (arrayList.size() <= 0) {
                return null;
            }
            if (result.remain < 0 || arrayList.size() > result.remain) {
                str2 = "Tags\u6570\u91cf\u4e0d\u80fd\u8d85\u8fc71024";
            } else if (result.interval == 0) {
                return null;
            } else {
                if ((currentTimeMillis - result.last_requestTime) / 1000 > result.interval) {
                    return null;
                }
                str2 = "interval\u9650\u5236";
            }
            return str2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0011  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r8, java.util.Hashtable<java.lang.String, java.lang.Integer> r9) {
        /*
            r7 = this;
            java.lang.String r0 = "\u90e8\u5206Tags\u957f\u5ea6\u4e0d\u5728\u9650\u52360\u5230128\u4e2a\u5b57\u7b26\u4e4b\u95f4"
            java.util.Set r1 = r9.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x000b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0055
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "UTF-8"
            byte[] r3 = r2.getBytes(r3)     // Catch:{ UnsupportedEncodingException -> 0x0047 }
            int r4 = r3.length     // Catch:{ UnsupportedEncodingException -> 0x0047 }
            r5 = 128(0x80, float:1.794E-43)
            if (r4 <= r5) goto L_0x0027
            int r3 = r3.length     // Catch:{ UnsupportedEncodingException -> 0x0047 }
            if (r3 > 0) goto L_0x0027
            return r0
        L_0x0027:
            java.lang.Object r3 = r9.get(r2)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r4 = -10
            if (r3 < r4) goto L_0x0043
            java.lang.Object r2 = r9.get(r2)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r3 = 10
            if (r2 <= r3) goto L_0x000b
        L_0x0043:
            java.lang.String r8 = "\u90e8\u5206Tags\u6743\u503c\u4e0d\u5728-10\u523010\u4e4b\u95f4"
            return r8
        L_0x0047:
            java.lang.String r8 = com.umeng.message.tag.TagManager.a
            r9 = 0
            java.lang.String r1 = "UnsupportedEncodingException"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            com.umeng.commonsdk.debug.UMLog.mutlInfo(r8, r9, r1)
            return r0
        L_0x0055:
            r9 = 0
            if (r8 != 0) goto L_0x0059
            return r9
        L_0x0059:
            com.umeng.message.common.inter.ITagManager$Result r0 = new com.umeng.message.common.inter.ITagManager$Result     // Catch:{ JSONException -> 0x0082 }
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0082 }
            r1.<init>(r8)     // Catch:{ JSONException -> 0x0082 }
            r8 = 1
            r0.<init>(r1, r8)     // Catch:{ JSONException -> 0x0082 }
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x0082 }
            long r3 = r0.interval     // Catch:{ JSONException -> 0x0082 }
            r5 = 0
            int r8 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r8 == 0) goto L_0x0086
            long r3 = r0.last_requestTime     // Catch:{ JSONException -> 0x0082 }
            long r1 = r1 - r3
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r3
            long r3 = r0.interval     // Catch:{ JSONException -> 0x0082 }
            int r8 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r8 <= 0) goto L_0x007d
            goto L_0x0086
        L_0x007d:
            java.lang.String r8 = "interval\u9650\u5236"
            r9 = r8
            goto L_0x0086
        L_0x0082:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0086:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.tag.TagManager.a(java.lang.String, java.util.Hashtable):java.lang.String");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String b(String str, String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : strArr) {
            try {
                byte[] bytes = str2.getBytes("UTF-8");
                if (bytes.length > 128 || bytes.length < 0) {
                    return "\u90e8\u5206Tags\u957f\u5ea6\u4e0d\u5728\u9650\u52360\u5230128\u4e2a\u5b57\u7b26\u4e4b\u95f4";
                }
                arrayList.add(str2);
            } catch (UnsupportedEncodingException unused) {
                UMLog.mutlInfo(a, 0, "UnsupportedEncodingException");
                return "\u90e8\u5206Tags\u957f\u5ea6\u4e0d\u5728\u9650\u52360\u5230128\u4e2a\u5b57\u7b26\u4e4b\u95f4";
            }
        }
        if (str == null) {
            return null;
        }
        try {
            ITagManager.Result result = new ITagManager.Result(new JSONObject(str), true);
            long currentTimeMillis = System.currentTimeMillis();
            if (arrayList.size() <= 0 || result.interval == 0) {
                return null;
            }
            if ((currentTimeMillis - result.last_requestTime) / 1000 > result.interval) {
                return null;
            }
            return "interval\u9650\u5236";
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String a(String str, boolean z) {
        if (str == null) {
            return null;
        }
        try {
            ITagManager.Result result = new ITagManager.Result(new JSONObject(str), z);
            long currentTimeMillis = System.currentTimeMillis();
            if (result.interval == 0) {
                return null;
            }
            if ((currentTimeMillis - result.last_requestTime) / 1000 > result.interval) {
                return null;
            }
            return "interval\u9650\u5236";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: com.umeng.message.tag.TagManager$3  reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ TCallBack a;
        final /* synthetic */ String[] b;

        AnonymousClass3(TCallBack tCallBack, String[] strArr) {
            this.a = tCallBack;
            this.b = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            ITagManager.Result result = null;
            if (TagManager.this.e()) {
                try {
                    throw new Exception("Tag API is disabled by the server.");
                } catch (Exception e) {
                    e.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else if (TagManager.this.d()) {
                String[] strArr = this.b;
                if (strArr == null || strArr.length == 0) {
                    try {
                        throw new Exception("No tags");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        this.a.onMessage(false, null);
                    }
                } else {
                    ArrayList arrayList = new ArrayList();
                    for (String str : this.b) {
                        arrayList.add(str);
                    }
                    if (arrayList.size() == 0) {
                        this.a.onMessage(true, TagManager.this.f());
                        return;
                    }
                    try {
                        JSONObject c = TagManager.this.c();
                        c.put("tags", e.a(arrayList));
                        result = TagManager.f.update(c, this.b);
                        this.a.onMessage(true, result);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        this.a.onMessage(true, result);
                    }
                }
            } else {
                try {
                    throw new Exception("No utdid or device_token");
                } catch (Exception e4) {
                    e4.printStackTrace();
                    this.a.onMessage(false, null);
                }
            }
        }
    }

    private void a(TCallBack tCallBack, String... strArr) {
        com.umeng.message.common.e.a(new AnonymousClass3(tCallBack, strArr));
    }

    /* renamed from: com.umeng.message.tag.TagManager$4  reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ String[] a;
        final /* synthetic */ TCallBack b;

        AnonymousClass4(String[] strArr, TCallBack tCallBack) {
            this.a = strArr;
            this.b = tCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            TagManager tagManager = TagManager.this;
            String a = tagManager.a(MessageSharedPrefs.getInstance(tagManager.e).get_deleteTagsInterval(), this.a);
            ITagManager.Result result = null;
            if (!TextUtils.isEmpty(a)) {
                try {
                    throw new Exception(a);
                } catch (Exception e) {
                    String str = TagManager.a;
                    UMLog.mutlInfo(str, 0, "exception:" + e.getMessage());
                    this.b.onMessage(false, null);
                }
            } else if (TagManager.this.e()) {
                try {
                    throw new Exception("Tag API is disabled by the server.");
                } catch (Exception e2) {
                    String str2 = TagManager.a;
                    UMLog.mutlInfo(str2, 0, "exception:" + e2.getMessage());
                    this.b.onMessage(false, null);
                }
            } else if (TagManager.this.d()) {
                String[] strArr = this.a;
                if (strArr == null || strArr.length == 0) {
                    try {
                        throw new Exception("No tags");
                    } catch (Exception e3) {
                        String str3 = TagManager.a;
                        UMLog.mutlInfo(str3, 0, "exception:" + e3.getMessage());
                        this.b.onMessage(false, null);
                    }
                } else {
                    try {
                        JSONObject c = TagManager.this.c();
                        c.put("tags", e.a(this.a));
                        result = TagManager.f.deleteTags(c, this.a);
                        this.b.onMessage(true, result);
                    } catch (Exception e4) {
                        String str4 = TagManager.a;
                        UMLog.mutlInfo(str4, 0, "exception:" + e4.getMessage());
                        this.b.onMessage(false, result);
                    }
                }
            } else {
                try {
                    throw new Exception("No utdid or device_token");
                } catch (Exception e5) {
                    String str5 = TagManager.a;
                    UMLog.mutlInfo(str5, 0, "exception:" + e5.getMessage());
                    this.b.onMessage(false, null);
                }
            }
        }
    }

    public void deleteTags(TCallBack tCallBack, String... strArr) {
        com.umeng.message.common.e.a(new AnonymousClass4(strArr, tCallBack));
    }

    /* renamed from: com.umeng.message.tag.TagManager$5  reason: invalid class name */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ TCallBack a;
        final /* synthetic */ String[] b;

        AnonymousClass5(TCallBack tCallBack, String[] strArr) {
            this.a = tCallBack;
            this.b = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            ITagManager.Result result = new ITagManager.Result();
            if (!TagManager.this.d()) {
                result.setErrors("\u7f3a\u5c11utdid\u6216device token");
                UMLog.mutlInfo(TagManager.a, 0, "\u7f3a\u5c11utdid\u6216device token");
                this.a.onMessage(false, result);
                return;
            }
            String[] strArr = this.b;
            if (strArr == null || strArr.length == 0) {
                UMLog.mutlInfo(TagManager.a, 0, "\u6ca1\u6709\u52a0\u6743\u6807\u7b7e");
                result.setErrors("\u6ca1\u6709\u52a0\u6743\u6807\u7b7e");
                this.a.onMessage(false, result);
            } else if (strArr.length > 64) {
                UMLog.mutlInfo(TagManager.a, 0, "\u6bcf\u6b21\u8bf7\u6c42\u6700\u591a\u5220\u966464\u4e2a\u52a0\u6743\u6807\u7b7e");
                result.setErrors("\u6bcf\u6b21\u8bf7\u6c42\u6700\u591a\u5220\u966464\u4e2a\u52a0\u6743\u6807\u7b7e");
                this.a.onMessage(false, result);
            } else {
                TagManager tagManager = TagManager.this;
                String b = tagManager.b(MessageSharedPrefs.getInstance(tagManager.e).getDeleteWeightedTagsInterval(), this.b);
                if (!TextUtils.isEmpty(b)) {
                    UMLog.mutlInfo(TagManager.a, 0, b);
                    result.setErrors(b);
                    this.a.onMessage(false, result);
                    return;
                }
                try {
                    JSONObject c = TagManager.this.c();
                    c.put("tags", e.a(this.b));
                    this.a.onMessage(true, TagManager.f.deleteWeightedTags(c, this.b));
                } catch (Exception unused) {
                    UMLog.mutlInfo(TagManager.a, 0, "\u5220\u9664\u52a0\u6743\u6807\u7b7e\u5f02\u5e38");
                }
            }
        }
    }

    public void deleteWeightedTags(TCallBack tCallBack, String... strArr) {
        com.umeng.message.common.e.a(new AnonymousClass5(tCallBack, strArr));
    }

    /* renamed from: com.umeng.message.tag.TagManager$6  reason: invalid class name */
    class AnonymousClass6 implements Runnable {
        final /* synthetic */ TCallBack a;

        AnonymousClass6(TCallBack tCallBack) {
            this.a = tCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            ITagManager.Result result = null;
            if (TagManager.this.e()) {
                try {
                    throw new Exception("Tag API\u88ab\u670d\u52a1\u5668\u7981\u6b62");
                } catch (Exception e) {
                    e.printStackTrace();
                    this.a.onMessage(false, null);
                }
            } else if (TagManager.this.d()) {
                try {
                    result = TagManager.f.reset(TagManager.this.c());
                    this.a.onMessage(true, result);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    this.a.onMessage(false, result);
                }
            } else {
                try {
                    throw new Exception("\u7f3a\u5c11utdid\u6216device token");
                } catch (Exception e3) {
                    e3.printStackTrace();
                    this.a.onMessage(false, null);
                }
            }
        }
    }

    private void a(TCallBack tCallBack) {
        com.umeng.message.common.e.a(new AnonymousClass6(tCallBack));
    }

    /* renamed from: com.umeng.message.tag.TagManager$7  reason: invalid class name */
    class AnonymousClass7 implements Runnable {
        final /* synthetic */ TagListCallBack a;

        AnonymousClass7(TagListCallBack tagListCallBack) {
            this.a = tagListCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            TagManager tagManager = TagManager.this;
            String a = tagManager.a(MessageSharedPrefs.getInstance(tagManager.e).get_getTagsInterval(), false);
            List<String> list = null;
            if (!TextUtils.isEmpty(a)) {
                try {
                    throw new Exception(a);
                } catch (Exception e) {
                    String str = TagManager.a;
                    UMLog.mutlInfo(str, 0, "exception:" + e.getMessage());
                    this.a.onMessage(false, null);
                }
            } else if (TagManager.this.e()) {
                try {
                    throw new Exception("Tag API\u88ab\u670d\u52a1\u5668\u7981\u6b62.");
                } catch (Exception e2) {
                    String str2 = TagManager.a;
                    UMLog.mutlInfo(str2, 0, "exception:" + e2.getMessage());
                    this.a.onMessage(false, null);
                }
            } else if (TagManager.this.d()) {
                try {
                    list = TagManager.f.getTags(TagManager.this.c());
                    this.a.onMessage(true, list);
                } catch (Exception e3) {
                    String str3 = TagManager.a;
                    UMLog.mutlInfo(str3, 0, "exception:" + e3.getMessage());
                    this.a.onMessage(false, list);
                }
            } else {
                try {
                    throw new Exception("\u7f3a\u5c11utdid\u6216device token");
                } catch (Exception e4) {
                    String str4 = TagManager.a;
                    UMLog.mutlInfo(str4, 0, "exception:" + e4.getMessage());
                    this.a.onMessage(false, null);
                }
            }
        }
    }

    public void getTags(TagListCallBack tagListCallBack) {
        com.umeng.message.common.e.a(new AnonymousClass7(tagListCallBack));
    }

    /* renamed from: com.umeng.message.tag.TagManager$8  reason: invalid class name */
    class AnonymousClass8 implements Runnable {
        final /* synthetic */ WeightedTagListCallBack a;

        AnonymousClass8(WeightedTagListCallBack weightedTagListCallBack) {
            this.a = weightedTagListCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            Hashtable<String, Integer> hashtable = new Hashtable<>();
            if (!TagManager.this.d()) {
                UMLog.mutlInfo(TagManager.a, 0, "\u7f3a\u5c11utdid\u6216device token");
                this.a.onMessage(false, hashtable);
                return;
            }
            TagManager tagManager = TagManager.this;
            String a = tagManager.a(MessageSharedPrefs.getInstance(tagManager.e).getListWeightedTagsInterval(), true);
            if (!TextUtils.isEmpty(a)) {
                UMLog.mutlInfo(TagManager.a, 0, a);
                this.a.onMessage(false, hashtable);
                return;
            }
            try {
                hashtable = TagManager.f.getWeightedTags(TagManager.this.c());
                this.a.onMessage(true, hashtable);
            } catch (Exception unused) {
                UMLog.mutlInfo(TagManager.a, 0, "\u83b7\u53d6\u52a0\u6743\u6807\u7b7e\u5217\u8868\u5f02\u5e38");
                this.a.onMessage(false, hashtable);
            }
        }
    }

    public void getWeightedTags(WeightedTagListCallBack weightedTagListCallBack) {
        com.umeng.message.common.e.a(new AnonymousClass8(weightedTagListCallBack));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.e).getHeader());
        jSONObject.put("utdid", UmengMessageDeviceConfig.getUtdid(this.e));
        jSONObject.put("device_token", MessageSharedPrefs.getInstance(this.e).getDeviceToken());
        jSONObject.put("ts", System.currentTimeMillis());
        return jSONObject;
    }

    private static JSONObject a(JSONObject jSONObject, String str) throws JSONException {
        String body = HttpRequest.post(str).acceptJson().contentType(HttpRequest.CONTENT_TYPE_JSON).send(jSONObject.toString()).body("UTF-8");
        String str2 = a;
        UMLog.mutlInfo(str2, 2, "postJson() url=" + str + "\n request = " + jSONObject + "\n response = " + body);
        return new JSONObject(body);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean d() {
        if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(this.e))) {
            UMLog.mutlInfo(a, 0, "UTDID\u4e3a\u7a7a");
            return false;
        } else if (!TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.e).getDeviceToken())) {
            return true;
        } else {
            UMLog.mutlInfo(a, 0, "Device token\u4e3a\u7a7a");
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean e() {
        boolean z = true;
        if (MessageSharedPrefs.getInstance(this.e).getTagSendPolicy() != 1) {
            z = false;
        }
        if (z) {
            UMLog.mutlInfo(a, 2, "Tag API\u88ab\u670d\u52a1\u5668\u7981\u6b62");
        }
        return z;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ITagManager.Result f() {
        ITagManager.Result result = new ITagManager.Result(new JSONObject(), false);
        result.remain = MessageSharedPrefs.getInstance(this.e).getTagRemain();
        result.status = "ok";
        result.jsonString = "status:" + result.status + Constants.ACCEPT_TIME_SEPARATOR_SP + " remain:" + result.remain + Constants.ACCEPT_TIME_SEPARATOR_SP + "description:" + result.status;
        return result;
    }
}
