package com.sobot.chat.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.a.a;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.adapter.f;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SobotMsgCenterModel;
import com.sobot.chat.b;
import com.sobot.chat.receiver.SobotMsgCenterReceiver;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.v;
import com.sobot.chat.utils.y;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SobotConsultationListActivity extends SobotBaseActivity implements a.AbstractC0135a {
    private ListView a;
    private f d;
    private List<SobotMsgCenterModel> e = new ArrayList();
    private LocalBroadcastManager f;
    private SobotMessageReceiver g;
    private String h;
    private a i = new a(this);
    private v j;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, 1004);
    }

    /* access modifiers changed from: package-private */
    public static class a extends Handler {
        WeakReference<Activity> a;

        a(Activity activity) {
            this.a = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SobotConsultationListActivity sobotConsultationListActivity = (SobotConsultationListActivity) this.a.get();
            if (sobotConsultationListActivity != null && message.what == 1) {
                List list = sobotConsultationListActivity.e;
                f fVar = sobotConsultationListActivity.d;
                ListView listView = sobotConsultationListActivity.a;
                List list2 = (List) message.obj;
                if (list2 != null) {
                    list.clear();
                    list.addAll(list2);
                    if (fVar == null) {
                        f fVar2 = new f(sobotConsultationListActivity, list);
                        sobotConsultationListActivity.d = fVar2;
                        listView.setAdapter((ListAdapter) fVar2);
                        return;
                    }
                    fVar.notifyDataSetChanged();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_consultation_list");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        if (bundle == null) {
            this.h = getIntent().getStringExtra("sobot_current_im_partnerid");
        } else {
            this.h = bundle.getString("sobot_current_im_partnerid");
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("sobot_current_im_partnerid", this.h);
        super.onSaveInstanceState(bundle);
    }

    private void d() {
        if (this.g == null) {
            this.g = new SobotMessageReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ZhiChiConstants.receiveMessageBrocast);
        intentFilter.addAction("SOBOT_ACTION_UPDATE_LAST_MSG");
        this.f = LocalBroadcastManager.getInstance(this);
        this.f.registerReceiver(this.g, intentFilter);
    }

    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        b(b("sobot_btn_back_selector"), "", true);
        setTitle(e("sobot_consultation_list"));
        this.a = (ListView) findViewById(a("sobot_ll_msg_center"));
        this.a.setOnItemClickListener(new AnonymousClass1(this));
        this.a.setOnItemLongClickListener(new AnonymousClass2(this));
    }

    /* renamed from: com.sobot.chat.activity.SobotConsultationListActivity$1  reason: invalid class name */
    class AnonymousClass1 implements AdapterView.OnItemClickListener {
        final /* synthetic */ SobotConsultationListActivity a;

        AnonymousClass1(SobotConsultationListActivity sobotConsultationListActivity) {
            JniLib.cV(this, sobotConsultationListActivity, 1000);
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SobotMsgCenterModel sobotMsgCenterModel = (SobotMsgCenterModel) this.a.e.get(i);
            Information info = sobotMsgCenterModel.getInfo();
            if (info != null) {
                info.setPartnerid(this.a.h);
                if (y.e == null || TextUtils.isEmpty(sobotMsgCenterModel.getApp_key())) {
                    b.a(this.a.getApplicationContext(), info);
                } else {
                    y.e.a(this.a.getApplicationContext(), info);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.SobotConsultationListActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements AdapterView.OnItemLongClickListener {
        final /* synthetic */ SobotConsultationListActivity a;

        AnonymousClass2(SobotConsultationListActivity sobotConsultationListActivity) {
            JniLib.cV(this, sobotConsultationListActivity, 1003);
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            new AlertDialog.Builder(this.a).setPositiveButton(q.f(this.a, "sobot_delete_dialogue"), new AnonymousClass1(this, i)).create().show();
            return true;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.activity.SobotConsultationListActivity$2$1  reason: invalid class name */
        public class AnonymousClass1 implements DialogInterface.OnClickListener {
            final /* synthetic */ int a;
            final /* synthetic */ AnonymousClass2 b;

            AnonymousClass1(AnonymousClass2 r5, int i) {
                JniLib.cV(this, r5, Integer.valueOf(i), 1002);
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ZhiChiApi a = com.sobot.chat.core.channel.a.a(this.b.a.getApplicationContext()).a();
                String b = s.b(this.b.a.getApplicationContext(), "sobot_platform_unioncode", "");
                a.removeMerchant(this.b.a, b, this.b.a.h, (SobotMsgCenterModel) this.b.a.d.getItem(this.a), new AnonymousClass1(this));
            }

            /* renamed from: com.sobot.chat.activity.SobotConsultationListActivity$2$1$1  reason: invalid class name */
            class AnonymousClass1 implements com.sobot.chat.core.http.c.a<SobotMsgCenterModel> {
                final /* synthetic */ AnonymousClass1 a;

                AnonymousClass1(AnonymousClass1 r5) {
                    JniLib.cV(this, r5, 1001);
                }

                @Override // com.sobot.chat.core.http.c.a
                public void a(Exception exc, String str) {
                }

                public void a(SobotMsgCenterModel sobotMsgCenterModel) {
                    if (sobotMsgCenterModel != null && sobotMsgCenterModel.getInfo() != null && this.a.b.a.e != null) {
                        this.a.b.a.e.remove(sobotMsgCenterModel);
                        Collections.sort(this.a.b.a.e, this.a.b.a.j);
                        this.a.b.a.c(this.a.b.a.e);
                    }
                }
            }
        }
    }

    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        com.sobot.chat.a.a.a(this, this, this.h, this);
    }

    @Override // com.sobot.chat.a.a.AbstractC0135a
    public void a(List<SobotMsgCenterModel> list) {
        c(list);
    }

    @Override // com.sobot.chat.a.a.AbstractC0135a
    public void b(List<SobotMsgCenterModel> list) {
        c(list);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(List<SobotMsgCenterModel> list) {
        Message obtainMessage = this.i.obtainMessage();
        obtainMessage.what = 1;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        obtainMessage.obj = arrayList;
        this.i.sendMessage(obtainMessage);
    }

    public class SobotMessageReceiver extends SobotMsgCenterReceiver {
        public SobotMessageReceiver() {
        }

        @Override // com.sobot.chat.receiver.SobotMsgCenterReceiver
        public void a(SobotMsgCenterModel sobotMsgCenterModel) {
            SobotConsultationListActivity.this.a(sobotMsgCenterModel);
        }

        @Override // com.sobot.chat.receiver.SobotMsgCenterReceiver
        public List<SobotMsgCenterModel> a() {
            return SobotConsultationListActivity.this.e;
        }
    }

    public void a(SobotMsgCenterModel sobotMsgCenterModel) {
        List<SobotMsgCenterModel> list;
        if (sobotMsgCenterModel != null && sobotMsgCenterModel.getInfo() != null && !TextUtils.isEmpty(sobotMsgCenterModel.getLastMsg()) && (list = this.e) != null) {
            list.remove(sobotMsgCenterModel);
            this.e.add(sobotMsgCenterModel);
            Collections.sort(this.e, this.j);
            c(this.e);
        }
    }

    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        com.sobot.chat.core.http.a.a().a(this);
        LocalBroadcastManager localBroadcastManager = this.f;
        if (localBroadcastManager != null) {
            localBroadcastManager.unregisterReceiver(this.g);
        }
        super.onDestroy();
    }
}
