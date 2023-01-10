package com.sobot.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.TtmlUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotTypeModel;
import com.sobot.chat.b;
import com.sobot.chat.b.a;
import com.sobot.chat.utils.q;
import java.util.List;

/* compiled from: SobotPostCategoryAdapter */
public class h extends com.sobot.chat.adapter.base.a<SobotTypeModel> {
    private Context a;
    private a d;
    private Activity e;

    public h(Activity activity, Context context, List list) {
        super(context, list);
        this.a = context;
        this.e = activity;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            Context context = this.a;
            view = View.inflate(context, q.a(context, TtmlUtils.TAG_LAYOUT, "sobot_activity_post_category_items"), null);
            this.d = new a(this.e, this.a, view);
            view.setTag(this.d);
        } else {
            this.d = (a) view.getTag();
        }
        this.d.a.setText(((SobotTypeModel) this.b.get(i)).getTypeName());
        if (((SobotTypeModel) this.b.get(i)).getNodeFlag() == 0) {
            this.d.b.setVisibility(8);
        } else {
            this.d.b.setVisibility(0);
            this.d.b.setBackgroundResource(q.a(this.a, "drawable", "sobot_right_arrow_icon"));
        }
        if (((SobotTypeModel) this.b.get(i)).isChecked()) {
            this.d.b.setVisibility(0);
            this.d.b.setBackgroundResource(q.a(this.a, "drawable", "sobot_work_order_selected_mark"));
        }
        if (this.b.size() < 2) {
            this.d.c.setVisibility(8);
        } else if (i == this.b.size() - 1) {
            this.d.c.setVisibility(8);
        } else {
            this.d.c.setVisibility(0);
        }
        return view;
    }

    /* compiled from: SobotPostCategoryAdapter */
    static class a {
        private TextView a;
        private ImageView b;
        private View c;
        private Activity d;

        a(Activity activity, Context context, View view) {
            this.d = activity;
            this.a = (TextView) view.findViewById(q.a(context, "id", "work_order_category_title"));
            this.b = (ImageView) view.findViewById(q.a(context, "id", "work_order_category_ishave"));
            this.c = view.findViewById(q.a(context, "id", "work_order_category_line"));
            a(this.a);
        }

        public void a(View view) {
            if (b.a(1) && b.a(4) && view != null) {
                com.sobot.chat.b.b.a().a(this.d);
                this.d.getWindow().setFlags(1024, 1024);
                com.sobot.chat.b.b.a().a(this.d, new AnonymousClass1(view));
            }
        }

        /* compiled from: SobotPostCategoryAdapter */
        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.adapter.h$a$1  reason: invalid class name */
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
