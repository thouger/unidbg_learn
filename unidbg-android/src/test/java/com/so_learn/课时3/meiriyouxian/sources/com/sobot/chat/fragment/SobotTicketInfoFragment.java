package com.sobot.chat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sobot.chat.activity.SobotTicketDetailActivity;
import com.sobot.chat.adapter.n;
import com.sobot.chat.api.model.SobotUserTicketInfo;
import com.sobot.chat.core.http.c.a;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.q;
import java.util.ArrayList;
import java.util.List;

public class SobotTicketInfoFragment extends SobotBaseFragment {
    private View a;
    private ListView b;
    private TextView c;
    private n d;
    private String e = "";
    private String f = "";
    private String g = "";
    private List<SobotUserTicketInfo> h = new ArrayList();

    public static SobotTicketInfoFragment a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("sobot_bundle_information", bundle);
        SobotTicketInfoFragment sobotTicketInfoFragment = new SobotTicketInfoFragment();
        sobotTicketInfoFragment.setArguments(bundle2);
        return sobotTicketInfoFragment;
    }

    @Override // com.sobot.chat.fragment.SobotBaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        Bundle bundle2;
        super.onCreate(bundle);
        if (getArguments() != null && (bundle2 = getArguments().getBundle("sobot_bundle_information")) != null) {
            this.e = bundle2.getString("intent_key_uid");
            this.f = bundle2.getString("intent_key_customerid");
            this.g = bundle2.getString("intent_key_companyid");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = layoutInflater.inflate(g("sobot_fragment_ticket_info"), viewGroup, false);
        b(this.a);
        return this.a;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        a();
        super.onActivityCreated(bundle);
    }

    /* access modifiers changed from: protected */
    public void b(View view) {
        this.b = (ListView) view.findViewById(e("sobot_listview"));
        this.c = (TextView) view.findViewById(e("sobot_empty"));
        this.c.setText(q.f(Z(), "sobot_empty_data"));
        this.b.setOnItemClickListener(new AnonymousClass1());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotTicketInfoFragment$1  reason: invalid class name */
    public class AnonymousClass1 implements AdapterView.OnItemClickListener {
        AnonymousClass1() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SobotUserTicketInfo sobotUserTicketInfo = (SobotUserTicketInfo) SobotTicketInfoFragment.this.d.getItem(i);
            SobotTicketInfoFragment.this.startActivityForResult(SobotTicketDetailActivity.a(SobotTicketInfoFragment.this.getContext(), SobotTicketInfoFragment.this.g, SobotTicketInfoFragment.this.e, sobotUserTicketInfo), 1);
            sobotUserTicketInfo.setNewFlag(false);
            SobotTicketInfoFragment.this.d.notifyDataSetChanged();
        }
    }

    public void a() {
        if ("null".equals(this.f)) {
            this.f = "";
        }
        if (isAdded() && !TextUtils.isEmpty(this.g) && !TextUtils.isEmpty(this.e)) {
            this.U.getUserTicketInfoList(this, this.e, this.g, this.f, new AnonymousClass2());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.fragment.SobotTicketInfoFragment$2  reason: invalid class name */
    public class AnonymousClass2 implements a<List<SobotUserTicketInfo>> {
        AnonymousClass2() {
        }

        public void a(List<SobotUserTicketInfo> list) {
            if (list == null || list.size() <= 0) {
                SobotTicketInfoFragment.this.c.setVisibility(0);
                SobotTicketInfoFragment.this.b.setVisibility(8);
                return;
            }
            SobotTicketInfoFragment.this.b.setVisibility(0);
            SobotTicketInfoFragment.this.c.setVisibility(8);
            SobotTicketInfoFragment.this.h.clear();
            SobotTicketInfoFragment.this.h.addAll(list);
            SobotTicketInfoFragment sobotTicketInfoFragment = SobotTicketInfoFragment.this;
            sobotTicketInfoFragment.d = new n(sobotTicketInfoFragment.getActivity(), SobotTicketInfoFragment.this.getContext(), SobotTicketInfoFragment.this.h);
            SobotTicketInfoFragment.this.b.setAdapter((ListAdapter) SobotTicketInfoFragment.this.d);
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            ae.a(SobotTicketInfoFragment.this.getContext(), str);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            a();
        }
        super.onActivityResult(i, i2, intent);
    }
}
