package com.sina.weibo.sdk.api.share;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.sina.weibo.sdk.a.k;

public class WeiboDownloader {
    private static final String CANCEL_CHINESS = "\u4ee5\u540e\u518d\u8bf4";
    private static final String CANCEL_ENGLISH = "Download Later";
    private static final String OK_CHINESS = "\u73b0\u5728\u4e0b\u8f7d";
    private static final String OK_ENGLISH = "Download Now";
    private static final String PROMPT_CHINESS = "\u672a\u5b89\u88c5\u5fae\u535a\u5ba2\u6237\u7aef\uff0c\u662f\u5426\u73b0\u5728\u53bb\u4e0b\u8f7d\uff1f";
    private static final String PROMPT_ENGLISH = "Sina Weibo client is not installed, download now?";
    private static final String TITLE_CHINESS = "\u63d0\u793a";
    private static final String TITLE_ENGLISH = "Notice";

    public static Dialog createDownloadConfirmDialog(Context context, IWeiboDownloadListener iWeiboDownloadListener) {
        String str;
        String str2;
        String str3;
        String str4;
        if (!k.a(context.getApplicationContext())) {
            str4 = TITLE_ENGLISH;
            str3 = PROMPT_ENGLISH;
            str2 = OK_ENGLISH;
            str = CANCEL_ENGLISH;
        } else {
            str4 = TITLE_CHINESS;
            str3 = PROMPT_CHINESS;
            str2 = OK_CHINESS;
            str = CANCEL_CHINESS;
        }
        return new AlertDialog.Builder(context).setMessage(str3).setTitle(str4).setPositiveButton(str2, new AnonymousClass1(context)).setNegativeButton(str, new AnonymousClass2(iWeiboDownloadListener)).create();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.api.share.WeiboDownloader$1  reason: invalid class name */
    public class AnonymousClass1 implements DialogInterface.OnClickListener {
        private final /* synthetic */ Context val$context;

        AnonymousClass1(Context context) {
            this.val$context = context;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            WeiboDownloader.downloadWeibo(this.val$context);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.api.share.WeiboDownloader$2  reason: invalid class name */
    public class AnonymousClass2 implements DialogInterface.OnClickListener {
        private final /* synthetic */ IWeiboDownloadListener val$listener;

        AnonymousClass2(IWeiboDownloadListener iWeiboDownloadListener) {
            this.val$listener = iWeiboDownloadListener;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            IWeiboDownloadListener iWeiboDownloadListener = this.val$listener;
            if (iWeiboDownloadListener != null) {
                iWeiboDownloadListener.onCancel();
            }
        }
    }

    /* access modifiers changed from: private */
    public static void downloadWeibo(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(Uri.parse("http://app.sina.cn/appdetail.php?appID=84560"));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
