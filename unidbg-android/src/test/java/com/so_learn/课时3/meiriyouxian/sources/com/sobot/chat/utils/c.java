package com.sobot.chat.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.internal.logging.nano.MetricsProto;
import com.sobot.chat.R;
import com.sobot.chat.adapter.e;
import com.sobot.chat.api.ResultCallBack;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.enumtype.SobotChatAvatarDisplayMode;
import com.sobot.chat.api.enumtype.SobotChatTitleDisplayMode;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SobotCacheFile;
import com.sobot.chat.api.model.SobotEvaluateModel;
import com.sobot.chat.api.model.SobotLocationModel;
import com.sobot.chat.api.model.SobotMsgCenterModel;
import com.sobot.chat.api.model.SobotMultiDiaRespInfo;
import com.sobot.chat.api.model.SobotQuestionRecommend;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.api.model.ZhiChiMessage;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.api.model.ZhiChiPushMessage;
import com.sobot.chat.api.model.ZhiChiReplyAnswer;
import com.sobot.chat.application.MyApplication;
import com.sobot.chat.b;
import com.sobot.chat.camera.c.f;
import com.sobot.chat.d;
import com.sobot.chat.viewHolder.ImageMessageHolder;
import com.sobot.chat.widget.dialog.SobotRobotListDialog;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ChatUtils */
public class c {

    /* compiled from: ChatUtils */
    public interface a {
        void a();

        void a(String str);
    }

    public static boolean a(int i) {
        return i == 0 || i == -1;
    }

    public static void a(Activity activity, Handler handler, boolean z) {
        e.a(activity.getApplicationContext(), q.f(activity, "sobot_thank_dialog_hint"), 1000, q.e(activity.getApplicationContext(), "sobot_iv_login_right")).show();
        handler.postDelayed(new AnonymousClass1(activity, z), 2000);
    }

    /* compiled from: ChatUtils */
    /* renamed from: com.sobot.chat.utils.c$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Activity a;
        final /* synthetic */ boolean b;

        AnonymousClass1(Activity activity, boolean z) {
            this.a = activity;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.a.isFinishing() && this.b) {
                this.a.finish();
            }
        }
    }

    public static void a(Activity activity) {
        a(activity, (Fragment) null);
    }

    public static void a(Activity activity, Fragment fragment) {
        Intent intent;
        if (activity != null) {
            if (Build.VERSION.SDK_INT < 19) {
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
            } else {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            }
            if (fragment != null) {
                try {
                    fragment.startActivityForResult(intent, 701);
                } catch (Exception unused) {
                    ae.a(activity.getApplicationContext(), q.f(activity, "sobot_not_open_album"));
                }
            } else {
                activity.startActivityForResult(intent, 701);
            }
        }
    }

    public static void b(Activity activity, Fragment fragment) {
        Intent intent;
        if (activity != null) {
            if (Build.VERSION.SDK_INT < 19) {
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
            } else {
                intent = new Intent(Intent.ACTION_PICK);
                intent.setType("video/*");
            }
            if (fragment != null) {
                try {
                    fragment.startActivityForResult(intent, 701);
                } catch (Exception e) {
                    e.printStackTrace();
                    ae.a(activity.getApplicationContext(), q.f(activity, "sobot_not_open_album"));
                }
            } else {
                activity.startActivityForResult(intent, 701);
            }
        }
    }

    public static File b(Activity activity) {
        return c(activity, null);
    }

    public static File c(Activity activity, Fragment fragment) {
        Uri uri;
        File file = new File(z.a().e() + System.currentTimeMillis() + ".jpg");
        k.a(file.getParentFile());
        if (Build.VERSION.SDK_INT <= 23) {
            uri = Uri.fromFile(file);
        } else if (Build.VERSION.SDK_INT < 29 || Environment.isExternalStorageLegacy()) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("_data", file.getAbsolutePath());
            uri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            uri = h.j(activity, file);
        }
        Intent putExtra = new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, uri);
        if (fragment != null) {
            fragment.startActivityForResult(putExtra, 702);
        } else {
            activity.startActivityForResult(putExtra, 702);
        }
        return file;
    }

    public static String a(Context context, String str) {
        return q.f(context, str);
    }

    public static void a(Context context, Handler handler, Uri uri, ZhiChiInitModeBase zhiChiInitModeBase, ListView listView, e eVar, boolean z) {
        if (zhiChiInitModeBase != null) {
            String a2 = l.a(context, uri);
            m.d("picturePath:" + a2);
            if (!TextUtils.isEmpty(a2)) {
                File file = new File(a2);
                if (file.exists() && file.isFile()) {
                    a(a2, zhiChiInitModeBase.getCid(), zhiChiInitModeBase.getPartnerid(), handler, context, listView, eVar, z);
                    return;
                }
                return;
            }
            File file2 = new File(uri.getPath());
            if (!file2.exists()) {
                ae.a(context, q.f(context, "sobot_not_find_pic"));
            } else {
                a(file2.getAbsolutePath(), zhiChiInitModeBase.getCid(), zhiChiInitModeBase.getPartnerid(), handler, context, listView, eVar, z);
            }
        }
    }

    public static void a(String str, String str2, String str3, Handler handler, Context context, ListView listView, e eVar, boolean z) {
        String a2;
        if (Build.VERSION.SDK_INT < 29 || Environment.isExternalStorageLegacy()) {
            Bitmap a3 = t.a(str, context, z);
            if (a3 != null) {
                try {
                    int a4 = l.a(str);
                    if (a4 > 0) {
                        a3 = l.a(a3, a4);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (str.endsWith(".gif") || str.endsWith(".GIF")) {
                    try {
                        String a5 = n.a(str);
                        a2 = f.a(context, l.a(context, str), a5 + f.b(str), str);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                } else {
                    String e3 = z.a().e();
                    k.a(e3);
                    a2 = e3 + n.a(str) + "_tmp.jpg";
                    try {
                        a3.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(a2));
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return;
                    }
                }
                if (d.a(a2) < 20971520) {
                    String str4 = System.currentTimeMillis() + "";
                    a(a2, handler, str4);
                    a(context, str2, str3, a2, handler, str4, listView, eVar);
                    return;
                }
                ae.a(context, q.f(context, "sobot_file_lt_8M"));
                return;
            }
            ae.a(context, q.f(context, "sobot_pic_type_error"));
        } else if (TextUtils.isEmpty(str)) {
            ae.a(context, q.f(context, "sobot_pic_type_error"));
        } else if (d.a(str) < 20971520) {
            String str5 = System.currentTimeMillis() + "";
            a(str, handler, str5);
            a(context, str2, str3, str, handler, str5, listView, eVar);
        } else {
            ae.a(context, q.f(context, "sobot_file_lt_8M"));
        }
    }

    public static void a(String str, Handler handler, String str2) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsg(str);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setId(str2);
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        zhiChiMessageBase.setSendSuccessState(2);
        zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR);
        Message message = new Message();
        message.what = MetricsProto.MetricsEvent.DIALOG_USER_EDIT_PROFILE;
        message.obj = zhiChiMessageBase;
        handler.sendMessage(message);
    }

    /* compiled from: ChatUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.utils.c$2  reason: invalid class name */
    public static class AnonymousClass2 implements ResultCallBack<ZhiChiMessage> {
        final /* synthetic */ String a;
        final /* synthetic */ Handler b;
        final /* synthetic */ e c;
        final /* synthetic */ ListView d;

        AnonymousClass2(String str, Handler handler, e eVar, ListView listView) {
            this.a = str;
            this.b = handler;
            this.c = eVar;
            this.d = listView;
        }

        /* renamed from: a */
        public void onSuccess(ZhiChiMessage zhiChiMessage) {
            if (1 == Integer.parseInt(zhiChiMessage.getCode()) && this.a != null) {
                Message obtainMessage = this.b.obtainMessage();
                obtainMessage.what = 402;
                obtainMessage.obj = this.a;
                this.b.sendMessage(obtainMessage);
            }
        }

        @Override // com.sobot.chat.api.ResultCallBack
        public void onLoading(long j, long j2, boolean z) {
            m.d("\u53d1\u9001\u56fe\u7247 \u8fdb\u5ea6:" + j2);
            String str = this.a;
            if (str != null) {
                int b = this.c.b(str);
                m.d("\u53d1\u9001\u56fe\u7247 position:" + b);
                c.a((int) j2, b, this.d);
            }
        }

        @Override // com.sobot.chat.api.ResultCallBack
        public void onFailure(Exception exc, String str) {
            m.d("\u53d1\u9001\u56fe\u7247error:" + str + "exception:" + exc);
            if (this.a != null) {
                Message obtainMessage = this.b.obtainMessage();
                obtainMessage.what = 401;
                obtainMessage.obj = this.a;
                this.b.sendMessage(obtainMessage);
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3, Handler handler, String str4, ListView listView, e eVar) {
        com.sobot.chat.core.channel.a.a(context).a().sendFile(str, str2, str3, "", new AnonymousClass2(str4, handler, eVar, listView));
    }

    public static void a(int i, int i2, ListView listView) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int lastVisiblePosition = listView.getLastVisiblePosition();
        if (i2 >= firstVisiblePosition && i2 <= lastVisiblePosition) {
            View childAt = listView.getChildAt(i2 - firstVisiblePosition);
            if (childAt.getTag() instanceof ImageMessageHolder) {
                ((ImageMessageHolder) childAt.getTag()).d.setProgress(i);
            }
        }
    }

    public static String a(Context context, ZhiChiInitModeBase zhiChiInitModeBase, int i) {
        context.getResources();
        if (1 == i) {
            if (!zhiChiInitModeBase.isServiceEndPushFlag() || TextUtils.isEmpty(zhiChiInitModeBase.getServiceEndPushMsg())) {
                return "";
            }
            return zhiChiInitModeBase.getServiceEndPushMsg();
        } else if (2 == i) {
            if (!zhiChiInitModeBase.isServiceEndPushFlag() || TextUtils.isEmpty(zhiChiInitModeBase.getServiceEndPushMsg())) {
                return "";
            }
            return zhiChiInitModeBase.getServiceEndPushMsg();
        } else if (3 == i) {
            return q.f(context, "sobot_outline_leverByManager");
        } else {
            if (4 == i) {
                String b = s.b(context, "sobot_user_out_word", "");
                if (!TextUtils.isEmpty(b)) {
                    return b;
                }
                return zhiChiInitModeBase != null ? zhiChiInitModeBase.getUserOutWord() : q.f(context, "sobot_outline_leverByManager");
            } else if (5 == i) {
                return q.f(context, "sobot_outline_leverByManager");
            } else {
                if (6 == i) {
                    return q.f(context, "sobot_outline_openNewWindows");
                }
                if (99 == i) {
                    return context.getString(R.string.sobot_outline_leavemsg);
                }
                return null;
            }
        }
    }

    public static ZhiChiMessageBase a(Context context) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsg(q.f(context, "sobot_no_read"));
        zhiChiReplyAnswer.setRemindType(7);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase a(ZhiChiPushMessage zhiChiPushMessage) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        zhiChiMessageBase.setSenderName(TextUtils.isEmpty(zhiChiPushMessage.getAname()) ? q.f(MyApplication.getInstance(), "sobot_cus_service") : zhiChiPushMessage.getAname());
        SobotEvaluateModel sobotEvaluateModel = new SobotEvaluateModel();
        sobotEvaluateModel.setIsQuestionFlag(zhiChiPushMessage.getIsQuestionFlag());
        sobotEvaluateModel.setIsResolved(zhiChiPushMessage.getIsQuestionFlag() ? 0 : -1);
        zhiChiMessageBase.setSobotEvaluateModel(sobotEvaluateModel);
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiMessageBase.setSenderType(Constants.VIA_ACT_TYPE_TWENTY_EIGHT);
        zhiChiMessageBase.setAction("action_custom_evaluate");
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase a(Context context, String str, File file) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        SobotCacheFile sobotCacheFile = new SobotCacheFile();
        sobotCacheFile.setMsgId(str);
        sobotCacheFile.setFilePath(file.getAbsolutePath());
        sobotCacheFile.setFileName(file.getName());
        sobotCacheFile.setFileType(a(file));
        sobotCacheFile.setFileSize(Formatter.formatFileSize(context, file.length()));
        zhiChiReplyAnswer.setCacheFile(sobotCacheFile);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setId(str);
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        zhiChiReplyAnswer.setMsgType(Constants.VIA_REPORT_TYPE_SET_AVATAR);
        zhiChiMessageBase.setSenderType("0");
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase a(String str, SobotLocationModel sobotLocationModel) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setLocationData(sobotLocationModel);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setId(str);
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        zhiChiReplyAnswer.setMsgType(Constants.VIA_REPORT_TYPE_DATALINE);
        zhiChiMessageBase.setSenderType("0");
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase a(Context context, String str, File file, String str2) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        SobotCacheFile sobotCacheFile = new SobotCacheFile();
        sobotCacheFile.setMsgId(str);
        sobotCacheFile.setFilePath(file.getAbsolutePath());
        sobotCacheFile.setFileName(file.getName());
        sobotCacheFile.setSnapshot(str2);
        sobotCacheFile.setFileType(a(file));
        sobotCacheFile.setFileSize(Formatter.formatFileSize(context, file.length()));
        zhiChiReplyAnswer.setCacheFile(sobotCacheFile);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setId(str);
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        zhiChiReplyAnswer.setMsgType(Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR);
        zhiChiMessageBase.setSenderType("0");
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase a(Context context, ZhiChiInitModeBase zhiChiInitModeBase) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsg(q.f(context, "sobot_robot_auto_transfer_tip"));
        zhiChiReplyAnswer.setMsgType("0");
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setSenderFace(zhiChiInitModeBase.getRobotLogo());
        zhiChiMessageBase.setSender(zhiChiInitModeBase.getRobotName());
        zhiChiMessageBase.setSenderType("1");
        zhiChiMessageBase.setSenderName(zhiChiInitModeBase.getRobotName());
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase b(Context context, ZhiChiInitModeBase zhiChiInitModeBase) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        String announceMsg = zhiChiInitModeBase.getAnnounceMsg();
        if (!TextUtils.isEmpty(announceMsg)) {
            announceMsg = announceMsg.replace("<p>", "").replace("</p>", "").replace("<br/>", "").replace("\n", "");
        }
        zhiChiReplyAnswer.setMsg(announceMsg);
        zhiChiReplyAnswer.setMsgType("0");
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setSenderType("32");
        return zhiChiMessageBase;
    }

    public static void a(Context context, Information information) {
        s.a(context, "robot_current_themeImg", information.getTitleImgId());
        String str = "";
        s.a(context, "sobot_current_sender_face", TextUtils.isEmpty(information.getFace()) ? str : information.getFace());
        s.a(context, "sobot_current_sender_name", TextUtils.isEmpty(information.getUser_nick()) ? str : information.getUser_nick());
        s.a(context, "sobot_user_phone", TextUtils.isEmpty(information.getUser_tels()) ? str : information.getUser_tels());
        if (!TextUtils.isEmpty(information.getUser_emails())) {
            str = information.getUser_emails();
        }
        s.a(context, "sobot_user_email", str);
        if (TextUtils.isEmpty(information.getPartnerid())) {
            information.setEquipmentId(d.e(context));
        }
    }

    public static boolean a(Context context, String str, Information information) {
        String str2 = "";
        if (!s.b(context, "sobot_last_current_appkey", str2).equals(information.getApp_key())) {
            s.c(context, "sobot_last_login_group_id");
            b.b(context);
            return true;
        }
        String b = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_last_current_partnerId", str2);
        String b2 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_receptionistid", str2);
        String b3 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_robot_code", str2);
        String b4 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_last_current_remark", str2);
        String b5 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_last_current_groupid", str2);
        int b6 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_service_mode", -1);
        String b7 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_customer_fields", str2);
        String b8 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_isvip", str2);
        String b9 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_vip_level", str2);
        String b10 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_user_label", str2);
        String b11 = s.b(context, str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_current_robot_alias", str2);
        if (!b.equals(information.getPartnerid() == null ? str2 : information.getPartnerid())) {
            m.d("uid\u53d1\u751f\u4e86\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
            return true;
        }
        if (!b2.equals(information.getChoose_adminid() == null ? str2 : information.getChoose_adminid())) {
            m.d("\u8f6c\u5165\u7684\u6307\u5b9a\u5ba2\u670d\u53d1\u751f\u4e86\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
            return true;
        }
        if (!b3.equals(information.getRobotCode() == null ? str2 : information.getRobotCode())) {
            m.d("\u6307\u5b9a\u673a\u5668\u4eba\u53d1\u751f\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
            return true;
        }
        if (!b11.equals(information.getRobot_alias() == null ? str2 : information.getRobot_alias())) {
            m.d("\u6307\u5b9a\u673a\u5668\u4eba\u522b\u540d\u53d1\u751f\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
            return true;
        }
        if (!b4.equals(information.getRemark() == null ? str2 : information.getRemark())) {
            m.d("\u5907\u6ce8\u53d1\u751f\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
            return true;
        }
        if (!b5.equals(information.getGroupid() == null ? str2 : information.getGroupid())) {
            m.d("\u6280\u80fd\u7ec4\u53d1\u751f\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
            return true;
        } else if (b6 != information.getService_mode()) {
            m.d("\u63a5\u5165\u6a21\u5f0f\u53d1\u751f\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
            return true;
        } else {
            if (!b7.equals(information.getCustomer_fields() == null ? str2 : information.getCustomer_fields())) {
                m.d("\u81ea\u5b9a\u4e49\u5b57\u6bb5\u53d1\u751f\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
                return true;
            }
            if (!b8.equals(information.getIsVip() == null ? str2 : information.getIsVip())) {
                m.d("\u662f\u5426vip\u53d1\u751f\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
                return true;
            }
            if (!b9.equals(information.getVip_level() == null ? str2 : information.getVip_level())) {
                m.d("vip\u7ea7\u522b\u53d1\u751f\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
                return true;
            }
            if (information.getUser_label() != null) {
                str2 = information.getUser_label();
            }
            if (b10.equals(str2)) {
                return false;
            }
            m.d("\u7528\u6237\u6807\u7b7e\u53d1\u751f\u53d8\u5316\uff0c\u91cd\u65b0\u521d\u59cb\u5316..............");
            return true;
        }
    }

    public static com.sobot.chat.widget.dialog.e a(Activity activity, boolean z, boolean z2, boolean z3, ZhiChiInitModeBase zhiChiInitModeBase, int i, int i2, String str, int i3, int i4, String str2, boolean z4, boolean z5) {
        com.sobot.chat.widget.dialog.e eVar;
        if (zhiChiInitModeBase == null) {
            return null;
        }
        if (r.c(activity)) {
            eVar = new com.sobot.chat.widget.dialog.e(activity, z, z2, z3, zhiChiInitModeBase, i, i2, str, i3, i4, str2, z4, z5, q.a(activity, "style", "sobot_FullScreenDialogStyle"));
        } else {
            eVar = new com.sobot.chat.widget.dialog.e(activity, z, z2, z3, zhiChiInitModeBase, i, i2, str, i3, i4, str2, z4, z5);
        }
        eVar.setCanceledOnTouchOutside(true);
        eVar.show();
        return eVar;
    }

    public static SobotRobotListDialog a(Activity activity, ZhiChiInitModeBase zhiChiInitModeBase, SobotRobotListDialog.a aVar) {
        if (activity == null || zhiChiInitModeBase == null || aVar == null) {
            return null;
        }
        SobotRobotListDialog sobotRobotListDialog = new SobotRobotListDialog(activity, zhiChiInitModeBase.getPartnerid(), zhiChiInitModeBase.getRobotid(), aVar);
        sobotRobotListDialog.setCanceledOnTouchOutside(true);
        sobotRobotListDialog.show();
        return sobotRobotListDialog;
    }

    public static String a(ZhiChiInitModeBase zhiChiInitModeBase, List<String> list, int i) {
        if (zhiChiInitModeBase == null) {
            return "-1";
        }
        String cid = zhiChiInitModeBase.getCid();
        if (i <= 0) {
            return cid;
        }
        if (i > list.size() - 1) {
            return "-1";
        }
        return list.get(i);
    }

    public static String a(Context context, boolean z, String str, String str2) {
        int b;
        if (z || SobotChatTitleDisplayMode.Default.getValue() == (b = s.b(context, "sobot_chat_title_display_mode", SobotChatTitleDisplayMode.Default.getValue()))) {
            return str;
        }
        if (SobotChatTitleDisplayMode.ShowFixedText.getValue() != b) {
            return (SobotChatTitleDisplayMode.ShowCompanyName.getValue() != b || TextUtils.isEmpty(str2)) ? str : str2;
        }
        String b2 = s.b(context, "sobot_chat_title_display_content", "");
        return !TextUtils.isEmpty(b2) ? b2 : str;
    }

    public static String b(Context context, boolean z, String str, String str2) {
        int b;
        if (z || SobotChatAvatarDisplayMode.Default.getValue() == (b = s.b(context, "sobot_chat_avatar_display_mode", SobotChatAvatarDisplayMode.Default.getValue()))) {
            return str;
        }
        if (SobotChatAvatarDisplayMode.ShowFixedAvatar.getValue() != b) {
            return (SobotChatAvatarDisplayMode.ShowCompanyAvatar.getValue() != b || TextUtils.isEmpty(str2)) ? str : str2;
        }
        String b2 = s.b(context, "sobot_chat_avatar_display_content", "");
        return !TextUtils.isEmpty(b2) ? b2 : str;
    }

    public static ZhiChiMessageBase b(Context context, String str) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
        zhiChiMessageBase.setAction("action_remind_connt_success");
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsgType(null);
        zhiChiReplyAnswer.setMsg(a(context, "sobot_service_accept_start") + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + str + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + a(context, "sobot_service_accept_end"));
        zhiChiReplyAnswer.setRemindType(4);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return null;
        }
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        zhiChiMessageBase.setSenderName(str);
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsgType("0");
        zhiChiMessageBase.setSenderType("2");
        zhiChiReplyAnswer.setMsg(str3);
        zhiChiMessageBase.setSenderFace(str2);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase a(String str) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsg(str);
        zhiChiReplyAnswer.setMsgType("0");
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setLeaveMsgFlag(true);
        zhiChiMessageBase.setSenderType("0");
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase b(String str) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
        zhiChiMessageBase.setAction("action_remind_info_paidui");
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsg(str);
        zhiChiReplyAnswer.setRemindType(3);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        return zhiChiMessageBase;
    }

    public static boolean a(Context context, boolean z, int i) {
        return s.b(context, "sobot_chat_evaluation_completed_exit", false) && z && i == 302;
    }

    public static void b(Context context) {
        s.a(context, "sobot_is_exit", true);
        String b = s.b(context, "sobot_cid_chat", "");
        String b2 = s.b(context, "sobot_uid_chat", "");
        d.a(context);
        if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(b2)) {
            com.sobot.chat.core.channel.a.a(context).a().out(b, b2, new AnonymousClass3());
        }
    }

    /* compiled from: ChatUtils */
    /* renamed from: com.sobot.chat.utils.c$3  reason: invalid class name */
    static class AnonymousClass3 implements com.sobot.chat.core.http.c.a<CommonModel> {
        public void a(CommonModel commonModel) {
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        AnonymousClass3() {
        }
    }

    public static boolean a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                Integer valueOf = Integer.valueOf(str2);
                String[] split = str.split(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                if ((valueOf.intValue() == 1 && "1".equals(split[0])) || ((valueOf.intValue() == 9 && "1".equals(split[0])) || ((valueOf.intValue() == 11 && "1".equals(split[0])) || ((valueOf.intValue() == 12 && "1".equals(split[0])) || ((valueOf.intValue() == 14 && "1".equals(split[0])) || ((valueOf.intValue() == 2 && "1".equals(split[1])) || ((valueOf.intValue() == 4 && "1".equals(split[2])) || (valueOf.intValue() == 3 && "1".equals(split[3]))))))))) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static void a(Context context, String str, a aVar, boolean z) {
        String str2;
        if (Build.VERSION.SDK_INT < 29 || Environment.isExternalStorageLegacy()) {
            Bitmap a2 = t.a(str, context, z);
            if (a2 != null) {
                try {
                    int a3 = l.a(str);
                    if (a3 > 0) {
                        a2 = l.a(a2, a3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (str.endsWith(".gif") || str.endsWith(".GIF")) {
                    try {
                        String a4 = n.a(str);
                        str2 = f.a(context, l.a(context, str), a4 + f.b(str), str);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        ae.a(context, q.f(context, "sobot_pic_type_error"));
                        return;
                    }
                } else {
                    String e3 = z.a().e();
                    k.a(e3);
                    str2 = e3 + n.a(str) + "_tmp.jpg";
                    try {
                        a2.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(str2));
                    } catch (FileNotFoundException e4) {
                        e4.printStackTrace();
                        ae.a(context, q.f(context, "sobot_pic_type_error"));
                        return;
                    }
                }
                aVar.a(str2);
                return;
            }
            ae.a(context, q.f(context, "sobot_pic_type_error"));
            aVar.a();
        } else if (!TextUtils.isEmpty(str)) {
            aVar.a(str);
        } else {
            ae.a(context, q.f(context, "sobot_pic_type_error"));
            aVar.a();
        }
    }

    public static void a(Context context, Uri uri, a aVar, boolean z) {
        String a2 = l.a(context, uri);
        if (!TextUtils.isEmpty(a2)) {
            a(context, a2, aVar, z);
        } else if (!new File(uri.getPath()).exists()) {
            com.sobot.chat.widget.dialog.d.b(context);
            ae.a(context, q.f(context, "sobot_not_find_pic"));
        } else {
            a(context, a2, aVar, z);
        }
    }

    public static boolean a(SobotEvaluateModel sobotEvaluateModel) {
        if (sobotEvaluateModel != null) {
            return sobotEvaluateModel.getIsQuestionFlag();
        }
        return false;
    }

    public static void a(Context context, Information information, String str, ZhiChiInitModeBase zhiChiInitModeBase, List<ZhiChiMessageBase> list) {
        String str2;
        String str3;
        String str4;
        u a2 = u.a(context);
        SobotMsgCenterModel sobotMsgCenterModel = new SobotMsgCenterModel();
        sobotMsgCenterModel.setInfo(information);
        sobotMsgCenterModel.setFace(zhiChiInitModeBase.getCompanyLogo());
        sobotMsgCenterModel.setName(zhiChiInitModeBase.getCompanyName());
        sobotMsgCenterModel.setAppkey(str);
        sobotMsgCenterModel.setUnreadCount(0);
        if (list != null) {
            int size = list.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                ZhiChiMessageBase zhiChiMessageBase = list.get(size);
                if (Constants.VIA_REPORT_TYPE_CHAT_VIDEO.equals(zhiChiMessageBase.getSenderType())) {
                    size--;
                } else {
                    sobotMsgCenterModel.setSenderName(zhiChiMessageBase.getSenderName());
                    if (TextUtils.isEmpty(zhiChiMessageBase.getSenderFace())) {
                        sobotMsgCenterModel.setSenderFace("https://img.sobot.com/console/common/face/user.png");
                    } else {
                        sobotMsgCenterModel.setSenderFace("");
                    }
                    if (Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR.equals(zhiChiMessageBase.getSenderType())) {
                        str2 = q.f(context, "sobot_upload");
                    } else if (Constants.VIA_REPORT_TYPE_CHAT_AUDIO.equals(zhiChiMessageBase.getSenderType())) {
                        str2 = q.f(context, "sobot_chat_type_voice");
                    } else if (zhiChiMessageBase.getAnswer() == null) {
                        str2 = "";
                    } else if ("1".equals(zhiChiMessageBase.getAnswer().getMsgType())) {
                        str2 = q.f(context, "sobot_upload");
                    } else if (zhiChiMessageBase.getAnswer().getMsg() == null) {
                        if (Constants.VIA_REPORT_TYPE_CHAT_AIO.equals(zhiChiMessageBase.getAnswer().getMsgType())) {
                            str2 = q.f(context, "sobot_chat_type_goods");
                        } else if (Constants.VIA_REPORT_TYPE_CHAT_AUDIO.equals(zhiChiMessageBase.getAnswer().getMsgType())) {
                            str2 = q.f(context, "sobot_chat_type_card");
                        } else if (Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR.equals(zhiChiMessageBase.getAnswer().getMsgType())) {
                            str2 = q.f(context, "sobot_upload_video");
                        } else if (Constants.VIA_REPORT_TYPE_SET_AVATAR.equals(zhiChiMessageBase.getAnswer().getMsgType())) {
                            str2 = q.f(context, "sobot_choose_file");
                        } else {
                            str2 = q.f(context, "sobot_chat_type_other_msg");
                        }
                    } else if (GsonUtil.isMultiRoundSession(zhiChiMessageBase)) {
                        str2 = zhiChiMessageBase.getAnswer().getMultiDiaRespInfo().getAnswer();
                    } else if (GsonUtil.isMultiRoundSession(zhiChiMessageBase)) {
                        str2 = zhiChiMessageBase.getAnswer().getMultiDiaRespInfo().getAnswer();
                    } else {
                        str2 = zhiChiMessageBase.getAnswer().getMsg();
                    }
                    sobotMsgCenterModel.setLastMsg(str2);
                    if (!TextUtils.isEmpty(zhiChiMessageBase.getT())) {
                        str3 = zhiChiMessageBase.getT();
                    } else {
                        str3 = Calendar.getInstance().getTime().getTime() + "";
                    }
                    sobotMsgCenterModel.setLastDate(f.a(Long.parseLong(str3), f.b));
                    if (!TextUtils.isEmpty(zhiChiMessageBase.getT())) {
                        str4 = zhiChiMessageBase.getT();
                    } else {
                        str4 = Calendar.getInstance().getTime().getTime() + "";
                    }
                    sobotMsgCenterModel.setLastDateTime(str4);
                }
            }
            a2.a(com.sobot.chat.core.channel.a.a(str, information.getPartnerid()), sobotMsgCenterModel);
            ArrayList arrayList = (ArrayList) a2.b(com.sobot.chat.core.channel.a.b(information.getPartnerid()));
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            if (!arrayList.contains(str)) {
                arrayList.add(str);
                a2.a(com.sobot.chat.core.channel.a.b(information.getPartnerid()), arrayList);
            }
            s.c(context, "sobot_current_im_appid");
            Intent intent = new Intent("SOBOT_ACTION_UPDATE_LAST_MSG");
            intent.putExtra("lastMsg", sobotMsgCenterModel);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            s.a(context, "sobot_last_msg_content", sobotMsgCenterModel.getLastMsg());
        }
    }

    public static void a(Context context, SobotMultiDiaRespInfo sobotMultiDiaRespInfo, Map<String, String> map, e.a aVar) {
        if (context != null && sobotMultiDiaRespInfo != null && map != null) {
            ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
            String str = "{\"interfaceRetList\":[" + GsonUtil.map2Json(map) + "],\"template\":" + sobotMultiDiaRespInfo.getTemplate() + "}";
            zhiChiMessageBase.setContent(a(sobotMultiDiaRespInfo.getOutPutParamList(), map, sobotMultiDiaRespInfo));
            zhiChiMessageBase.setId(System.currentTimeMillis() + "");
            if (aVar != null) {
                aVar.a(zhiChiMessageBase, 4, 2, str, map.get("title"));
            }
        }
    }

    private static String a(String[] strArr, Map<String, String> map, SobotMultiDiaRespInfo sobotMultiDiaRespInfo) {
        if (sobotMultiDiaRespInfo == null || map == null || map.size() <= 0) {
            return "";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("level", sobotMultiDiaRespInfo.getLevel());
        hashMap.put("conversationId", sobotMultiDiaRespInfo.getConversationId());
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length; i++) {
                hashMap.put(strArr[i], map.get(strArr[i]));
            }
        }
        return GsonUtil.map2Str(hashMap);
    }

    public static String a(SobotMultiDiaRespInfo sobotMultiDiaRespInfo) {
        if (sobotMultiDiaRespInfo == null) {
            return "";
        }
        if (!"000000".equals(sobotMultiDiaRespInfo.getRetCode())) {
            return sobotMultiDiaRespInfo.getRetErrorMsg();
        }
        if (sobotMultiDiaRespInfo.getEndFlag()) {
            return !TextUtils.isEmpty(sobotMultiDiaRespInfo.getAnswerStrip()) ? sobotMultiDiaRespInfo.getAnswerStrip() : sobotMultiDiaRespInfo.getAnswer();
        }
        return sobotMultiDiaRespInfo.getRemindQuestion();
    }

    public static ZhiChiMessageBase a(ZhiChiInitModeBase zhiChiInitModeBase, SobotQuestionRecommend sobotQuestionRecommend) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setSenderType("29");
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        zhiChiReplyAnswer.setQuestionRecommend(sobotQuestionRecommend);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        zhiChiMessageBase.setSenderFace(zhiChiInitModeBase.getRobotLogo());
        zhiChiMessageBase.setSender(zhiChiInitModeBase.getRobotName());
        zhiChiMessageBase.setSenderName(zhiChiInitModeBase.getRobotName());
        return zhiChiMessageBase;
    }

    public static ZhiChiMessageBase c(String str) {
        ZhiChiMessageBase zhiChiMessageBase = new ZhiChiMessageBase();
        zhiChiMessageBase.setSenderType(Constants.VIA_REPORT_TYPE_CHAT_AIO);
        zhiChiMessageBase.setT(Calendar.getInstance().getTime().getTime() + "");
        ZhiChiReplyAnswer zhiChiReplyAnswer = new ZhiChiReplyAnswer();
        zhiChiReplyAnswer.setMsg(str);
        zhiChiReplyAnswer.setRemindType(8);
        zhiChiMessageBase.setAnswer(zhiChiReplyAnswer);
        return zhiChiMessageBase;
    }

    public static void a(ZhiChiInitModeBase zhiChiInitModeBase, e eVar, ZhiChiPushMessage zhiChiPushMessage) {
        if (zhiChiInitModeBase != null && a(zhiChiPushMessage.getContent(), zhiChiInitModeBase.getAccountStatus())) {
            eVar.b(c(q.f(MyApplication.getInstance(), "sobot_money_trading_tip")));
        }
    }

    private static boolean a(String str, int i) {
        if (TextUtils.isEmpty(str) || ((i != 0 && i != 1) || !str.contains(q.f(MyApplication.getInstance(), "sobot_ver_code")))) {
            return false;
        }
        return true;
    }

    public static TextView a(Context context, boolean z) {
        int i;
        TextView textView = new TextView(context);
        textView.setTextSize(14.0f);
        textView.setPadding(0, r.a(context, 7.0f), 0, r.a(context, 7.0f));
        textView.setLineSpacing(2.0f, 1.0f);
        if (z) {
            if (-1 != com.sobot.chat.c.n) {
                i = com.sobot.chat.c.n;
            } else {
                i = q.a(context, "color", "sobot_color_suggestion_history");
            }
        } else if (-1 != com.sobot.chat.c.o) {
            i = com.sobot.chat.c.o;
        } else {
            i = q.a(context, "color", "sobot_color_link");
        }
        textView.setTextColor(context.getResources().getColor(i));
        return textView;
    }

    public static int a(Context context, int i) {
        if (context == null) {
            return 0;
        }
        switch (i) {
            case 13:
                return q.a(context, "drawable", "sobot_icon_file_doc");
            case 14:
                return q.a(context, "drawable", "sobot_icon_file_ppt");
            case 15:
                return q.a(context, "drawable", "sobot_icon_file_xls");
            case 16:
                return q.a(context, "drawable", "sobot_icon_file_pdf");
            case 17:
                return q.a(context, "drawable", "sobot_icon_file_mp3");
            case 18:
                return q.a(context, "drawable", "sobot_icon_file_mp4");
            case 19:
                return q.a(context, "drawable", "sobot_icon_file_rar");
            case 20:
                return q.a(context, "drawable", "sobot_icon_file_txt");
            case 21:
                return q.a(context, "drawable", "sobot_icon_file_unknow");
            default:
                return q.a(context, "drawable", "sobot_icon_file_unknow");
        }
    }

    public static int a(File file) {
        if (file == null) {
            return 21;
        }
        try {
            String lowerCase = file.getName().toLowerCase();
            if (lowerCase.endsWith("doc")) {
                return 13;
            }
            if (lowerCase.endsWith("docx")) {
                return 13;
            }
            if (lowerCase.endsWith("ppt")) {
                return 14;
            }
            if (lowerCase.endsWith("pptx")) {
                return 14;
            }
            if (lowerCase.endsWith("xls")) {
                return 15;
            }
            if (lowerCase.endsWith("xlsx")) {
                return 15;
            }
            if (lowerCase.endsWith("pdf")) {
                return 16;
            }
            if (lowerCase.endsWith("mp3")) {
                return 17;
            }
            if (lowerCase.endsWith("mp4")) {
                return 18;
            }
            if (lowerCase.endsWith("rar")) {
                return 19;
            }
            if (lowerCase.endsWith("zip")) {
                return 19;
            }
            if (lowerCase.endsWith("txt")) {
                return 20;
            }
            return 21;
        } catch (Exception unused) {
        }
    }
}
