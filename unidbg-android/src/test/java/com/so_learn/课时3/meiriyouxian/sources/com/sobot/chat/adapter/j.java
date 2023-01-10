package com.sobot.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.TtmlUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotProvinInfo;
import com.sobot.chat.b;
import com.sobot.chat.b.a;
import com.sobot.chat.utils.q;
import java.util.List;

/* compiled from: SobotProvinAdapter */
public class j extends com.sobot.chat.adapter.base.a<SobotProvinInfo.SobotProvinceModel> {
    private Context a;
    private Activity d;
    private a e;

    public j(Activity activity, Context context, List list) {
        super(context, list);
        this.a = context;
        this.d = activity;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            Context context = this.a;
            view = View.inflate(context, q.a(context, TtmlUtils.TAG_LAYOUT, "sobot_activity_post_category_items"), null);
            this.e = new a(this.d, this.a, view);
            view.setTag(this.e);
        } else {
            this.e = (a) view.getTag();
        }
        this.e.a((SobotProvinInfo.SobotProvinceModel) this.b.get(i));
        if (this.b.size() < 2) {
            this.e.c.setVisibility(8);
        } else if (i == this.b.size() - 1) {
            this.e.c.setVisibility(8);
        } else {
            this.e.c.setVisibility(0);
        }
        return view;
    }

    /* compiled from: SobotProvinAdapter */
    static class a {
        private TextView a;
        private ImageView b;
        private View c;
        private Context d;
        private Activity e;

        a(Activity activity, Context context, View view) {
            this.e = activity;
            this.d = context;
            this.a = (TextView) view.findViewById(q.a(context, "id", "work_order_category_title"));
            this.b = (ImageView) view.findViewById(q.a(context, "id", "work_order_category_ishave"));
            this.c = view.findViewById(q.a(context, "id", "work_order_category_line"));
            a(this.a);
        }

        /* access modifiers changed from: package-private */
        public void a(SobotProvinInfo.SobotProvinceModel sobotProvinceModel) {
            if (sobotProvinceModel != null) {
                int i = sobotProvinceModel.level;
                if (i == 0) {
                    this.a.setText(sobotProvinceModel.provinceName);
                } else if (i == 1) {
                    this.a.setText(sobotProvinceModel.cityName);
                } else if (i == 2) {
                    this.a.setText(sobotProvinceModel.areaName);
                }
                if (sobotProvinceModel.nodeFlag) {
                    this.b.setVisibility(0);
                    this.b.setBackgroundResource(q.a(this.d, "drawable", "sobot_right_arrow_icon"));
                } else {
                    this.b.setVisibility(8);
                }
                if (sobotProvinceModel.isChecked) {
                    this.b.setVisibility(0);
                    this.b.setBackgroundResource(q.a(this.d, "drawable", "sobot_work_order_selected_mark"));
                }
            }
        }

        public void a(View view) {
            if (b.a(1) && b.a(4) && view != null) {
                com.sobot.chat.b.b.a().a(this.e);
                this.e.getWindow().setFlags(1024, 1024);
                com.sobot.chat.b.b.a().a(this.e, new AnonymousClass1(view));
            }
        }

        /* compiled from: SobotProvinAdapter */
        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.adapter.j$a$1  reason: invalid class name */
        public class AnonymousClass1 implements a.AbstractC0139a {
            final /* synthetic */ View a;

            AnonymousClass1(View view) {
                this.a = view;
            }

            @Override // com.sobot.chat.b.a.AbstractC0139a
            public void a(a.b bVar) {
                if (bVar.a) {
                    for (Rect rect : bVar.b) {
                        this.a.setPadding(rect.right, this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
                    }
                }
            }
        }
    }
}
