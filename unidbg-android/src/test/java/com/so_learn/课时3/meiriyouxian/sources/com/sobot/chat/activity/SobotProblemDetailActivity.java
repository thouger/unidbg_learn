package com.sobot.chat.activity;

import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotBaseHelpCenterActivity;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.StDocModel;
import com.sobot.chat.api.model.StHelpDocModel;
import com.sobot.chat.b;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.y;

public class SobotProblemDetailActivity extends SobotBaseHelpCenterActivity implements View.OnClickListener {
    private StDocModel e;
    private WebView f;
    private View g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_problem_detail");
    }

    public static Intent a(Context context, Information information, StDocModel stDocModel) {
        Intent intent = new Intent(context, SobotProblemDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("sobot_bundle_info", information);
        intent.putExtra("sobot_bundle_information", bundle);
        intent.putExtra("extra_key_doc", stDocModel);
        return intent;
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseHelpCenterActivity, com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        super.a(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.e = (StDocModel) intent.getSerializableExtra("extra_key_doc");
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        b(b("sobot_btn_back_grey_selector"), "", true);
        setTitle(e("sobot_problem_detail_title"));
        this.g = findViewById(a("ll_bottom"));
        this.h = (TextView) findViewById(a("tv_sobot_layout_online_service"));
        this.i = (TextView) findViewById(a("tv_sobot_layout_online_tel"));
        this.j = (TextView) findViewById(a("sobot_text_problem_title"));
        this.f = (WebView) findViewById(a("sobot_webView"));
        this.k = (TextView) findViewById(a("tv_sobot_layout_online_service"));
        this.k.setText(q.f(this, "sobot_help_center_online_service"));
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        if (this.d == null || TextUtils.isEmpty(this.d.getHelpCenterTelTitle()) || TextUtils.isEmpty(this.d.getHelpCenterTel())) {
            this.i.setVisibility(8);
        } else {
            this.i.setVisibility(0);
            this.i.setText(this.d.getHelpCenterTelTitle());
        }
        d();
        displayInNotch(this.f);
        displayInNotch(this.j);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        a.a(getApplicationContext()).a().getHelpDocByDocId(this, this.d.getApp_key(), this.e.getDocId(), new AnonymousClass1(this));
    }

    /* renamed from: com.sobot.chat.activity.SobotProblemDetailActivity$1  reason: invalid class name */
    class AnonymousClass1 implements com.sobot.chat.core.http.c.a<StHelpDocModel> {
        final /* synthetic */ SobotProblemDetailActivity a;

        AnonymousClass1(SobotProblemDetailActivity sobotProblemDetailActivity) {
            JniLib.cV(this, sobotProblemDetailActivity, 1027);
        }

        public void a(StHelpDocModel stHelpDocModel) {
            this.a.j.setText(stHelpDocModel.getQuestionTitle());
            String answerDesc = stHelpDocModel.getAnswerDesc();
            if (!TextUtils.isEmpty(answerDesc)) {
                this.a.f.loadDataWithBaseURL("about:blank", "<style>*,body,html,div,p,img{border:0;margin:0;padding:0;} </style>" + ("<!DOCTYPE html>\n<html>\n    <head>\n        <meta charset=\"utf-8\">\n        <title></title>\n        <style>\n body{color:" + q.h(this.a, "sobot_common_wenzi_black") + ";}\n            img{\n                width: auto;\n                height:auto;\n                max-height: 100%;\n                max-width: 100%;\n            }\n        </style>\n    </head>\n    <body>" + answerDesc + "  </body>\n</html>"), ClipDescription.MIMETYPE_TEXT_HTML, FileOpt.ENCODE_STR, null);
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            ae.a(this.a.getApplicationContext(), str);
        }
    }

    private void d() {
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                this.f.removeJavascriptInterface("searchBoxJavaBridge_");
            } catch (Exception unused) {
            }
        }
        this.f.setDownloadListener(new AnonymousClass2(this));
        this.f.removeJavascriptInterface("searchBoxJavaBridge_");
        this.f.getSettings().setDefaultFontSize(14);
        this.f.getSettings().setTextZoom(100);
        this.f.getSettings().setJavaScriptEnabled(true);
        this.f.getSettings().setAllowFileAccess(false);
        this.f.getSettings().setCacheMode(-1);
        this.f.setBackgroundColor(0);
        this.f.getSettings().setDomStorageEnabled(true);
        this.f.getSettings().setLoadsImagesAutomatically(true);
        this.f.getSettings().setBlockNetworkImage(false);
        this.f.getSettings().setSavePassword(false);
        if (Build.VERSION.SDK_INT >= 21) {
            this.f.getSettings().setMixedContentMode(0);
        }
        this.f.getSettings().setDatabaseEnabled(true);
        this.f.getSettings().setAppCacheEnabled(true);
        this.f.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.f.setWebViewClient(new AnonymousClass3());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotProblemDetailActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements DownloadListener {
        final /* synthetic */ SobotProblemDetailActivity a;

        AnonymousClass2(SobotProblemDetailActivity sobotProblemDetailActivity) {
            JniLib.cV(this, sobotProblemDetailActivity, 1028);
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(Uri.parse(str));
            this.a.startActivity(intent);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotProblemDetailActivity$3  reason: invalid class name */
    public class AnonymousClass3 extends WebViewClient {
        AnonymousClass3() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            SobotProblemDetailActivity.this.t();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (y.a != null) {
                y.a.a(str);
                return true;
            } else if (y.b == null || !y.b.a(str)) {
                return false;
            } else {
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void t() {
        this.f.loadUrl("javascript:(function(){var objs = document.getElementsByTagName('img'); for(var i=0;i<objs.length;i++)  {var img = objs[i];       img.style.maxWidth = '100%'; img.style.height = 'auto';  }})()");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        WebView webView = this.f;
        if (webView != null) {
            webView.onResume();
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        WebView webView = this.f;
        if (webView != null) {
            webView.onPause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        WebView webView = this.f;
        if (webView != null) {
            webView.removeAllViews();
            ViewGroup viewGroup = (ViewGroup) this.f.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.f);
            }
            this.f.destroy();
        }
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.h) {
            b.a(getApplicationContext(), this.d);
        }
        if (view == this.i && !TextUtils.isEmpty(this.d.getHelpCenterTel())) {
            d.a(this.d.getHelpCenterTel(), q());
        }
    }
}
