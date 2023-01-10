package com.sobot.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sobot.chat.api.model.StCategoryModel;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.widget.SobotImageView;
import java.util.List;

/* compiled from: SobotHelpCenterAdapter */
public class d extends com.sobot.chat.adapter.base.a<StCategoryModel> {
    private LayoutInflater a;

    public d(Context context, List<StCategoryModel> list) {
        super(context, list);
        this.a = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.a.inflate(q.a(this.c, "sobot_list_item_help_center"), (ViewGroup) null);
            aVar = new a(this.c, view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(i, (StCategoryModel) this.b.get(i));
        return view;
    }

    /* compiled from: SobotHelpCenterAdapter */
    private static class a {
        private Context a;
        private LinearLayout b;
        private RelativeLayout c;
        private SobotImageView d;
        private TextView e;
        private TextView f;
        private int g;

        public a(Context context, View view) {
            this.a = context;
            this.b = (LinearLayout) view.findViewById(q.g(context, "sobot_container"));
            this.c = (RelativeLayout) view.findViewById(q.g(context, "sobot_rl"));
            this.d = (SobotImageView) view.findViewById(q.g(context, "sobot_tv_icon"));
            this.e = (TextView) view.findViewById(q.g(context, "sobot_tv_title"));
            this.f = (TextView) view.findViewById(q.g(context, "sobot_tv_descripe"));
            this.g = q.e(context, "sobot_bg_default_pic_img");
        }

        public void a(int i, StCategoryModel stCategoryModel) {
            this.c.setSelected(i % 2 == 0);
            Context context = this.a;
            String categoryUrl = stCategoryModel.getCategoryUrl();
            SobotImageView sobotImageView = this.d;
            int i2 = this.g;
            t.a(context, categoryUrl, sobotImageView, i2, i2);
            this.e.setText(stCategoryModel.getCategoryName());
            this.f.setText(stCategoryModel.getCategoryDetail());
        }
    }
}
