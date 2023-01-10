package com.sobot.chat.adapter;

import android.content.Context;
import android.media.TtmlUtils;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.sobot.chat.adapter.base.a;
import com.sobot.chat.api.model.ZhiChiGroupBase;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.umeng.message.common.inter.ITagManager;
import java.util.List;

/* compiled from: SobotSikllAdapter */
public class l extends a {
    private LayoutInflater a;
    private TextView d;
    private TextView e;
    private LinearLayout f;
    private int g;
    private Context h;

    public l(Context context, List<ZhiChiGroupBase> list, int i) {
        super(context, list);
        this.a = LayoutInflater.from(context);
        this.h = context;
        this.g = i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        String str;
        if (view == null) {
            view = this.a.inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, "sobot_list_item_skill"), (ViewGroup) null);
        }
        this.f = (LinearLayout) view.findViewById(q.a(this.c, "id", "sobot_ll_content"));
        this.d = (TextView) view.findViewById(q.a(this.c, "id", "sobot_tv_content"));
        this.e = (TextView) view.findViewById(q.a(this.c, "id", "sobot_tv_desc"));
        this.f.setLayoutParams(new AbsListView.LayoutParams(-1, r.a(this.h, 36.0f)));
        ZhiChiGroupBase zhiChiGroupBase = (ZhiChiGroupBase) this.b.get(i);
        if (zhiChiGroupBase != null) {
            this.f.setVisibility(0);
            this.d.setText(zhiChiGroupBase.getGroupName());
            if (ITagManager.STATUS_TRUE.equals(zhiChiGroupBase.isOnline())) {
                this.e.setVisibility(8);
                this.d.setTextSize(14.0f);
            } else {
                this.d.setTextSize(12.0f);
                this.d.setTextColor(ContextCompat.getColor(this.c, q.c(this.c, "sobot_common_gray2")));
                if (this.g == 0) {
                    str = q.f(this.c, "sobot_no_access") + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + q.f(this.c, "sobot_can") + "<font color='#0DAEAF'>" + q.f(this.c, "sobot_str_bottom_message") + "</a>";
                } else {
                    str = q.f(this.c, "sobot_no_access");
                }
                this.e.setText(Html.fromHtml(str));
                this.e.setVisibility(0);
            }
        } else {
            this.f.setVisibility(4);
            this.e.setVisibility(8);
            this.d.setText("");
        }
        return view;
    }
}
