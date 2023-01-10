package com.umeng.message;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.connect.common.Constants;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.common.e;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.l;
import com.umeng.message.proguard.m;
import com.umeng.message.provider.a;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.Calendar;

public class MessageSharedPrefs {
    private static final String a = MessageSharedPrefs.class.getName();
    private static MessageSharedPrefs c = null;
    private static final String d = "tempkey";
    private static final String e = "tempvalue";
    private Context b;
    private SharedPreferences f;
    private int g = 0;

    private MessageSharedPrefs(Context context) {
        this.b = context;
        if (Build.VERSION.SDK_INT > 11) {
            this.g |= 4;
        }
        this.f = context.getSharedPreferences(MsgConstant.PUSH_SHARED_PREFERENCES_FILE_NAME, this.g);
    }

    public static synchronized MessageSharedPrefs getInstance(Context context) {
        MessageSharedPrefs messageSharedPrefs;
        synchronized (MessageSharedPrefs.class) {
            if (c == null) {
                c = new MessageSharedPrefs(context);
            }
            messageSharedPrefs = c;
        }
        return messageSharedPrefs;
    }

    public boolean hasAppLaunchLogSentToday() {
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTimeInMillis(m.a(this.b).f());
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.d(a, e2.toString());
        }
        Calendar instance2 = Calendar.getInstance();
        if (instance.get(6) == instance2.get(6) && instance.get(1) == instance2.get(1)) {
            return true;
        }
        return false;
    }

    public void setDisplayNotificationNumber(int i) {
        c(MsgConstant.KEY_NOTIFICATION_NUMBER, i + "");
    }

    public int getDisplayNotificationNumber() {
        return Integer.valueOf(b(MsgConstant.KEY_NOTIFICATION_NUMBER, "1")).intValue();
    }

    public void setMessageAppKey(String str) {
        if (!h.d(this.b)) {
            return;
        }
        if (str == null || str.equals("")) {
            UMLog.mutlInfo(a, 0, "appkey\u4e0d\u80fd\u4e3anull");
        } else {
            this.f.edit().putString(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY, str).commit();
        }
    }

    public void removeMessageAppKey() {
        this.f.edit().remove(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY).commit();
    }

    public String getMessageAppKey() {
        return this.f.getString(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY, "");
    }

    public void setMessageAppSecret(String str) {
        if (!h.d(this.b)) {
            return;
        }
        if (str == null || str.equals("")) {
            UMLog.mutlInfo(a, 0, "appSecret\u4e0d\u80fd\u4e3anull");
        } else {
            this.f.edit().putString(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET, str).commit();
        }
    }

    public void removeMessageAppSecret() {
        this.f.edit().remove(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET).commit();
    }

    public String getMessageAppSecret() {
        return this.f.getString(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET, "");
    }

    public void setMessageChannel(String str) {
        if (h.d(this.b)) {
            this.f.edit().putString(MsgConstant.KEY_UMENG_MESSAGE_APP_CHANNEL, str).commit();
            e.a(new AnonymousClass1());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.MessageSharedPrefs$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            UTrack.getInstance(MessageSharedPrefs.this.b).updateHeader();
        }
    }

    public String getMessageChannel() {
        return this.f.getString(MsgConstant.KEY_UMENG_MESSAGE_APP_CHANNEL, "");
    }

    public void setAppLaunchLogSendPolicy(int i) {
        c(MsgConstant.KEY_APP_LAUNCH_LOG_SEND_POLICY, i + "");
    }

    public void setDaRegisterSendPolicy(int i) {
        c(MsgConstant.KEY_APP_DAREGISTER_LOG_SEND_POLICY, i + "");
    }

    public int getDaRegisterSendPolicy() {
        return Integer.valueOf(b(MsgConstant.KEY_APP_DAREGISTER_LOG_SEND_POLICY, "-1")).intValue();
    }

    public void setTagSendPolicy(int i) {
        c(MsgConstant.KEY_TAG_SEND_POLICY, i + "");
    }

    public int getAppLaunchLogSendPolicy() {
        return Integer.valueOf(b(MsgConstant.KEY_APP_LAUNCH_LOG_SEND_POLICY, "-1")).intValue();
    }

    public int getTagSendPolicy() {
        return Integer.valueOf(b(MsgConstant.KEY_TAG_SEND_POLICY, "-1")).intValue();
    }

    public void addAlias(String str, String str2, int i, int i2, String str3) {
        try {
            a(str2, str3);
            String[] strArr = {str, str2, i + ""};
            Cursor query = this.b.getContentResolver().query(a.a(this.b).d, new String[]{"error"}, "alias=? and type=? and exclusive=?", strArr, "time desc");
            if (query == null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
                contentValues.put("type", str2);
                contentValues.put("alias", str);
                contentValues.put(l.A, Integer.valueOf(i));
                contentValues.put("error", Integer.valueOf(i2));
                contentValues.put("message", str3);
                this.b.getContentResolver().insert(a.a(this.b).d, contentValues);
            } else if (query.getCount() > 0) {
                query.moveToFirst();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("time", Long.valueOf(System.currentTimeMillis()));
                contentValues2.put("type", str2);
                contentValues2.put("alias", str);
                contentValues2.put(l.A, Integer.valueOf(i));
                contentValues2.put("error", Integer.valueOf(i2));
                contentValues2.put("message", str3);
                this.b.getContentResolver().update(a.a(this.b).d, contentValues2, "alias=? and type=? and exclusive=?", strArr);
            } else {
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("time", Long.valueOf(System.currentTimeMillis()));
                contentValues3.put("type", str2);
                contentValues3.put("alias", str);
                contentValues3.put(l.A, Integer.valueOf(i));
                contentValues3.put("error", Integer.valueOf(i2));
                contentValues3.put("message", str3);
                this.b.getContentResolver().insert(a.a(this.b).d, contentValues3);
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(String str, String str2) {
        try {
            this.b.getContentResolver().delete(a.a(this.b).d, "type=? and message=? ", new String[]{str, str2});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void removeAlias(int i, String str, String str2) {
        try {
            this.b.getContentResolver().delete(a.a(this.b).d, "type=? and alias=? and exclusive=? ", new String[]{str2, str, i + ""});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String getLastAlias(int i, String str) {
        String str2 = "";
        try {
            Cursor query = this.b.getContentResolver().query(a.a(this.b).d, new String[]{"alias"}, "type=? and exclusive=? and (error=? or error = ?)", new String[]{str, i + str2, "0", "2"}, "time desc");
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                str2 = query.getString(query.getColumnIndex("alias"));
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x00f1, code lost:
        if (r3 != null) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00f3, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00fe, code lost:
        if (0 == 0) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0101, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isAliasSet(int r13, java.lang.String r14, java.lang.String r15) {
        /*
            r12 = this;
            java.lang.String r0 = "alias"
            java.lang.String r1 = "type"
            r2 = 0
            r3 = 0
            java.lang.String r7 = "type=? and alias=? and exclusive=? and (error=? or error = ?)"
            r4 = 5
            java.lang.String[] r8 = new java.lang.String[r4]     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r8[r2] = r15     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r10 = 1
            r8[r10] = r14     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r4.<init>()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r4.append(r13)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r5 = ""
            r4.append(r5)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r11 = 2
            r8[r11] = r4     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r4 = 3
            java.lang.String r5 = "0"
            r8[r4] = r5     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r4 = 4
            java.lang.String r5 = "2"
            r8[r4] = r5     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r4 = com.umeng.message.MessageSharedPrefs.a     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String[] r5 = new java.lang.String[r10]     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r6.<init>()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r9 = "type:"
            r6.append(r9)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r6.append(r15)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r9 = ",alias:"
            r6.append(r9)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r6.append(r14)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r9 = ",exclusive:"
            r6.append(r9)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r6.append(r13)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r13 = r6.toString()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r5[r2] = r13     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            com.umeng.commonsdk.debug.UMLog.mutlInfo(r4, r11, r5)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            android.content.Context r13 = r12.b     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            android.content.ContentResolver r4 = r13.getContentResolver()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            android.content.Context r13 = r12.b     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            com.umeng.message.provider.a r13 = com.umeng.message.provider.a.a(r13)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            android.net.Uri r5 = r13.d     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String[] r6 = new java.lang.String[]{r1, r0}     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r9 = 0
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            if (r3 == 0) goto L_0x00f1
            int r13 = r3.getCount()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r4 = com.umeng.message.MessageSharedPrefs.a     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String[] r5 = new java.lang.String[r10]     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r6.<init>()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r7 = "count:"
            r6.append(r7)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r6.append(r13)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r5[r2] = r6     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            com.umeng.commonsdk.debug.UMLog.mutlInfo(r4, r11, r5)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            if (r13 <= 0) goto L_0x00f1
            r3.moveToFirst()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            int r13 = r3.getColumnIndex(r1)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r13 = r3.getString(r13)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            int r0 = r3.getColumnIndex(r0)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r0 = r3.getString(r0)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r1 = com.umeng.message.MessageSharedPrefs.a     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r5.<init>()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r6 = "typeTmp:"
            r5.append(r6)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r5.append(r13)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r6 = ",aliasTmp:"
            r5.append(r6)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r5.append(r0)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r6 = ",type:"
            r5.append(r6)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r5.append(r15)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r6 = ",alis:"
            r5.append(r6)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r5.append(r14)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            r4[r2] = r5     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            com.umeng.commonsdk.debug.UMLog.mutlInfo(r1, r11, r4)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            boolean r13 = r13.equalsIgnoreCase(r15)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            if (r13 == 0) goto L_0x00f1
            boolean r13 = r0.equalsIgnoreCase(r14)     // Catch:{ Exception -> 0x00fe, all -> 0x00f7 }
            if (r13 == 0) goto L_0x00f1
            r2 = r10
        L_0x00f1:
            if (r3 == 0) goto L_0x0101
        L_0x00f3:
            r3.close()
            goto L_0x0101
        L_0x00f7:
            r13 = move-exception
            if (r3 == 0) goto L_0x00fd
            r3.close()
        L_0x00fd:
            throw r13
        L_0x00fe:
            if (r3 == 0) goto L_0x0101
            goto L_0x00f3
        L_0x0101:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.MessageSharedPrefs.isAliasSet(int, java.lang.String, java.lang.String):boolean");
    }

    public void addTags(String... strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String format = String.format("UMENG_TAG_%s", strArr[i]);
            if (!Boolean.valueOf(b(format, ITagManager.STATUS_FALSE)).booleanValue()) {
                c(format, ITagManager.STATUS_TRUE);
                c("UMENG_TAG_COUNT", (getTagCount() + 1) + "");
            }
        }
    }

    public void removeTags(String... strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String format = String.format("UMENG_TAG_%s", strArr[i]);
            if (Boolean.valueOf(b(format, ITagManager.STATUS_FALSE)).booleanValue()) {
                removeKeyAndValue(format);
                StringBuilder sb = new StringBuilder();
                sb.append(getTagCount() - 1);
                sb.append("");
                c("UMENG_TAG_COUNT", sb.toString());
            }
        }
    }

    public boolean isTagSet(String str) {
        return Boolean.valueOf(b(String.format("UMENG_TAG_%s", str), ITagManager.STATUS_FALSE)).booleanValue();
    }

    public int getTagCount() {
        return Integer.valueOf(b("UMENG_TAG_COUNT", "0")).intValue();
    }

    public void add_addTagsInterval(String str) {
        if (str != null) {
            c(MsgConstant.KEY_ADDTAGS, str);
        }
    }

    public String get_addTagsInterval() {
        return b(MsgConstant.KEY_ADDTAGS, null);
    }

    public void add_deleteTagsInterval(String str) {
        if (str != null) {
            c(MsgConstant.KEY_DELETETAGS, str);
        }
    }

    public String get_deleteTagsInterval() {
        return b(MsgConstant.KEY_DELETETAGS, null);
    }

    public void add_getTagsInteral(String str) {
        if (str != null) {
            c(MsgConstant.KEY_GETTAGS, str);
        }
    }

    public String get_getTagsInterval() {
        return b(MsgConstant.KEY_GETTAGS, null);
    }

    public void setAddWeightedTagsInterval(String str) {
        if (!TextUtils.isEmpty(str)) {
            c(MsgConstant.KEY_ADD_WEIGHTED_TAGS, str);
        }
    }

    public String getAddWeightedTagsInterval() {
        return b(MsgConstant.KEY_ADD_WEIGHTED_TAGS, null);
    }

    public void setDeleteWeightedTagsInterval(String str) {
        if (!TextUtils.isEmpty(str)) {
            c(MsgConstant.KEY_DELETE_WEIGHTED_TAGS, str);
        }
    }

    public String getDeleteWeightedTagsInterval() {
        return b(MsgConstant.KEY_DELETE_WEIGHTED_TAGS, null);
    }

    public void setListWeightedTagsInterval(String str) {
        if (!TextUtils.isEmpty(str)) {
            c(MsgConstant.KEY_LIST_WEIGHTED_TAGS, str);
        }
    }

    public String getListWeightedTagsInterval() {
        return b(MsgConstant.KEY_LIST_WEIGHTED_TAGS, null);
    }

    public void add_addAliasInterval(String str) {
        if (str != null) {
            c(MsgConstant.KEY_ADDALIAS, str);
        }
    }

    public String get_addAliasInterval() {
        return b(MsgConstant.KEY_ADDALIAS, null);
    }

    public void add_setAliasInterval(String str) {
        if (str != null) {
            c("setAlias", str);
        }
    }

    public String get_setAliasInterval() {
        return b("setAlias", null);
    }

    public void add_deleteAliasInterval(String str) {
        if (str != null) {
            c(MsgConstant.KEY_DELETEALIAS, str);
        }
    }

    public String get_deleteALiasInterval() {
        return b(MsgConstant.KEY_DELETEALIAS, null);
    }

    public void setTagRemain(int i) {
        c(MsgConstant.KET_UMENG_TAG_REMAIN, i + "");
    }

    public int getTagRemain() {
        return Integer.valueOf(b(MsgConstant.KET_UMENG_TAG_REMAIN, "64")).intValue();
    }

    public void resetTags() {
        Cursor cursor = null;
        try {
            ArrayList arrayList = new ArrayList();
            cursor = this.b.getContentResolver().query(a.a(this.b).c, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String string = cursor.getString(cursor.getColumnIndex(d));
                    if (string.contains("UMENG_TAG")) {
                        arrayList.add(string);
                    }
                    cursor.moveToNext();
                }
            }
            for (int i = 0; i < arrayList.size(); i++) {
                this.b.getContentResolver().delete(a.a(this.b).c, "tempkey=?", new String[]{(String) arrayList.get(i)});
            }
            if (cursor == null) {
                return;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (0 == 0) {
                return;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2, int i3, int i4) {
        c(MsgConstant.KEY_NO_DISTURB_START_HOUR, i + "");
        c(MsgConstant.KEY_NO_DISTURB_START_HOUR, i2 + "");
        c(MsgConstant.KEY_NO_DISTURB_END_HOUR, i3 + "");
        c(MsgConstant.KEY_NO_DISTURB_END_MINUTE, i4 + "");
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_START_HOUR, Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR)).intValue();
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_START_MINUTE, "0")).intValue();
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_END_HOUR, "7")).intValue();
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return Integer.valueOf(b(MsgConstant.KEY_NO_DISTURB_END_MINUTE, "0")).intValue();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        c(MsgConstant.KEY_ENEABLED, ITagManager.STATUS_TRUE);
    }

    /* access modifiers changed from: package-private */
    public void f() {
        c(MsgConstant.KEY_ENEABLED, ITagManager.STATUS_FALSE);
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return b(MsgConstant.KEY_ENEABLED, ITagManager.STATUS_FALSE).equalsIgnoreCase(ITagManager.STATUS_TRUE);
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str) {
        return b("device_token", "").equalsIgnoreCase(str);
    }

    public void setIsEnabled(boolean z) {
        c(MsgConstant.KEY_ISENABLED, String.valueOf(z));
    }

    public boolean isEnabled() {
        String b = b(MsgConstant.KEY_ISENABLED, "");
        return b.equalsIgnoreCase(ITagManager.STATUS_TRUE) || b.equalsIgnoreCase("");
    }

    public boolean hasTransferedCacheFileDataToSQL() {
        return b(MsgConstant.KEY_CACHE_FILE_TRANSFER_TO_SQL, ITagManager.STATUS_FALSE).equalsIgnoreCase(ITagManager.STATUS_TRUE);
    }

    public boolean finishTransferedCacheFileDataToSQL() {
        return b(MsgConstant.KEY_CACHE_FILE_TRANSFER_TO_SQL, ITagManager.STATUS_TRUE).equalsIgnoreCase(ITagManager.STATUS_TRUE);
    }

    public <U extends UmengMessageService> void setPushIntentServiceClass(Class<U> cls) {
        if (cls == null) {
            removeKeyAndValue(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME);
        } else {
            c(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME, cls.getName());
        }
    }

    public String getPushIntentServiceClass() {
        String b = b(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME, "");
        if (b.equalsIgnoreCase("")) {
            return "";
        }
        try {
            Class.forName(b);
            return b;
        } catch (ClassNotFoundException unused) {
            return "";
        }
    }

    public boolean hasMessageResourceDownloaded(String str) {
        return b(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX + str, ITagManager.STATUS_FALSE).equals(ITagManager.STATUS_TRUE);
    }

    public void setMessageResourceDownloaded(String str) {
        c(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX + str, ITagManager.STATUS_TRUE);
    }

    public void removeMessageResouceRecord(String str) {
        removeKeyAndValue(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX + str);
    }

    public void setLastMessageMsgID(String str) {
        c(MsgConstant.KEY_LAST_MSG_ID, str);
    }

    public String getLastMessageMsgID() {
        return b(MsgConstant.KEY_LAST_MSG_ID, "");
    }

    public void setMuteDuration(int i) {
        c(MsgConstant.KEY_MUTE_DURATION, i + "");
    }

    public int getMuteDuration() {
        return Integer.valueOf(b(MsgConstant.KEY_MUTE_DURATION, Constant.TRANS_TYPE_LOAD)).intValue();
    }

    public void setSerialNo(int i) {
        c(MsgConstant.KEY_SERIA_NO, i + "");
    }

    public int getSerialNo() {
        return Integer.valueOf(b(MsgConstant.KEY_SERIA_NO, "1")).intValue();
    }

    public boolean getNotificaitonOnForeground() {
        return b(MsgConstant.KEY_SET_NOTIFICATION_ON_FOREGROUND, ITagManager.STATUS_TRUE).equals(ITagManager.STATUS_TRUE);
    }

    public void setNotificaitonOnForeground(boolean z) {
        c(MsgConstant.KEY_SET_NOTIFICATION_ON_FOREGROUND, String.valueOf(z));
    }

    public String getResourcePackageName() {
        return b(MsgConstant.KEY_SET_RESOURCE_PACKAGENAME, "");
    }

    public void setResourcePackageName(String str) {
        c(MsgConstant.KEY_SET_RESOURCE_PACKAGENAME, str);
    }

    public int getNotificationPlayVibrate() {
        return Integer.valueOf(b(MsgConstant.KEY_SET_NOTIFICATION_PLAY_VIBRATE, "0")).intValue();
    }

    public void setNotificationPlayVibrate(int i) {
        c(MsgConstant.KEY_SET_NOTIFICATION_PLAY_VIBRATE, i + "");
    }

    public int getNotificationPlayLights() {
        return Integer.valueOf(b(MsgConstant.KEY_SET_NOTIFICATION_PLAY_LIGHTS, "0")).intValue();
    }

    public void setNotificationPlayLights(int i) {
        c(MsgConstant.KEY_SET_NOTIFICATION_PLAY_LIGHTS, i + "");
    }

    public int getNotificationPlaySound() {
        return Integer.valueOf(b(MsgConstant.KEY_SET_NOTIFICATION_PLAY_SOUND, "0")).intValue();
    }

    public void setNotificationPlaySound(int i) {
        c(MsgConstant.KEY_SET_NOTIFICATION_PLAY_SOUND, i + "");
    }

    public void setAppVersion(String str) {
        if (str == null) {
            removeKeyAndValue("app_version");
        } else {
            c("app_version", str);
        }
    }

    public String getAppVersion() {
        return b("app_version", "");
    }

    public void setDeviceToken(String str) {
        if (str == null) {
            removeKeyAndValue("device_token");
        } else {
            c("device_token", str);
        }
    }

    public void setDeviceTokenSync(String str) {
        if (str == null) {
            removeKeyAndValueSync("device_token");
        } else {
            d("device_token", str);
        }
    }

    public String getDeviceToken() {
        return b("device_token", "");
    }

    public void setUmid(String str) {
        this.f.edit().putString("KEY_SET_UMID", str).apply();
    }

    public String getUmid() {
        return this.f.getString("KEY_SET_UMID", "");
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        this.f.edit().putString(MsgConstant.KEY_NOTIFICATION_CHANNEL, str).apply();
    }

    /* access modifiers changed from: package-private */
    public String h() {
        return this.f.getString(MsgConstant.KEY_NOTIFICATION_CHANNEL, "");
    }

    public void setLocationInterval(int i) {
        if (i < 2 || i > 1800) {
            UMLog.mutlInfo("LBS", 2, "LBS\u7684\u8bf7\u6c42\u95f4\u9694\u5e94\u8be5\u57282\u81f31800\u79d2\u4e4b\u95f4");
            return;
        }
        c("interval", i + "");
    }

    public int getLocationInterval() {
        return Integer.valueOf(b("interval", "600")).intValue();
    }

    public void setHasResgister(boolean z) {
        c(MsgConstant.KEY_HASREGISTER, String.valueOf(z));
    }

    public void setHasRegisterSync(boolean z) {
        d(MsgConstant.KEY_HASREGISTER, String.valueOf(z));
    }

    public boolean getHasRegister() {
        return b(MsgConstant.KEY_HASREGISTER, ITagManager.STATUS_FALSE).equalsIgnoreCase(ITagManager.STATUS_TRUE);
    }

    public int getRegisterTimes() {
        return Integer.valueOf(getInstance(this.b).b(MsgConstant.KEY_REGISTER_TIMES, "0")).intValue();
    }

    public void setRegisterTimes(int i) {
        c(MsgConstant.KEY_REGISTER_TIMES, i + "");
    }

    public String getUcode() {
        return getInstance(this.b).b(MsgConstant.KEY_UCODE, "");
    }

    public void setUcode(String str) {
        c(MsgConstant.KEY_UCODE, str);
    }

    private String b(String str, String str2) {
        try {
            new ContentValues().put(d, str);
            Cursor query = this.b.getContentResolver().query(a.a(this.b).c, new String[]{e}, "tempkey=?", new String[]{str}, null);
            if (query == null) {
                return str2;
            }
            if (query.moveToFirst()) {
                str2 = query.getString(query.getColumnIndex(e));
            }
            query.close();
            return str2;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.MessageSharedPrefs$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass2(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MessageSharedPrefs.this.d(this.a, this.b);
        }
    }

    private void c(String str, String str2) {
        e.a(new AnonymousClass2(str, str2));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(String str, String str2) {
        try {
            String[] strArr = {str};
            Cursor query = this.b.getContentResolver().query(a.a(this.b).c, new String[]{e}, "tempkey=?", strArr, null);
            if (query == null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(d, str);
                contentValues.put(e, str2);
                this.b.getContentResolver().insert(a.a(this.b).c, contentValues);
                return;
            }
            if (query.moveToFirst()) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(e, str2);
                this.b.getContentResolver().update(a.a(this.b).c, contentValues2, "tempkey=?", strArr);
            } else {
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put(d, str);
                contentValues3.put(e, str2);
                this.b.getContentResolver().insert(a.a(this.b).c, contentValues3);
            }
            query.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.MessageSharedPrefs$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass3(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            MessageSharedPrefs.this.removeKeyAndValueSync(this.a);
        }
    }

    public void removeKeyAndValue(String str) {
        e.a(new AnonymousClass3(str));
    }

    public void removeKeyAndValueSync(String str) {
        try {
            new ContentValues().put(d, str);
            Cursor query = this.b.getContentResolver().query(a.a(this.b).c, new String[]{e}, null, null, null);
            if (query != null) {
                this.b.getContentResolver().delete(a.a(this.b).c, "tempkey=?", new String[]{str});
                query.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
