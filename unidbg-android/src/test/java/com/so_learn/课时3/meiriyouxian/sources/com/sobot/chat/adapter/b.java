package com.sobot.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.TtmlUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotCusFieldDataInfo;
import com.sobot.chat.b.a;
import com.sobot.chat.utils.q;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SobotCusFieldAdapter */
public class b extends com.sobot.chat.adapter.base.a<SobotCusFieldDataInfo> implements Filterable {
    private C0137b a;
    private Context d;
    private Activity e;
    private int f;
    private a g;
    private List<SobotCusFieldDataInfo> h;
    private List<SobotCusFieldDataInfo> i;

    public b(Activity activity, Context context, List<SobotCusFieldDataInfo> list, int i) {
        super(context, list);
        this.d = context;
        this.e = activity;
        this.f = i;
        this.i = list;
        this.h = list;
    }

    @Override // com.sobot.chat.adapter.base.a, android.widget.Adapter
    public int getCount() {
        return this.h.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            Context context = this.d;
            view = View.inflate(context, q.a(context, TtmlUtils.TAG_LAYOUT, "sobot_activity_cusfield_listview_items"), null);
            this.a = new C0137b(this.e, view);
            view.setTag(this.a);
        } else {
            this.a = (C0137b) view.getTag();
        }
        this.a.b.setText(this.h.get(i).getDataName());
        if (7 == this.f) {
            this.a.c.setVisibility(8);
            this.a.d.setVisibility(0);
            if (this.h.get(i).isChecked()) {
                this.a.d.setBackgroundResource(q.a(this.d, "drawable", "sobot_post_category_checkbox_pressed"));
            } else {
                this.a.d.setBackgroundResource(q.a(this.d, "drawable", "sobot_post_category_checkbox_normal"));
            }
        } else {
            this.a.d.setVisibility(8);
            if (this.h.get(i).isChecked()) {
                this.a.c.setVisibility(0);
                this.a.c.setBackgroundResource(q.a(this.d, "drawable", "sobot_work_order_selected_mark"));
            } else {
                this.a.c.setVisibility(8);
            }
        }
        if (this.h.size() < 2) {
            this.a.e.setVisibility(8);
        } else if (i == this.h.size() - 1) {
            this.a.e.setVisibility(8);
        } else {
            this.a.e.setVisibility(0);
        }
        return view;
    }

    /* compiled from: SobotCusFieldAdapter */
    /* renamed from: com.sobot.chat.adapter.b$b  reason: collision with other inner class name */
    class C0137b {
        private TextView b;
        private ImageView c;
        private ImageView d;
        private View e;
        private Activity f;

        C0137b(Activity activity, View view) {
            this.f = activity;
            this.b = (TextView) view.findViewById(q.a(b.this.c, "id", "sobot_activity_cusfield_listview_items_title"));
            this.c = (ImageView) view.findViewById(q.a(b.this.c, "id", "sobot_activity_cusfield_listview_items_ishave"));
            this.d = (ImageView) view.findViewById(q.a(b.this.c, "id", "sobot_activity_cusfield_listview_items_checkbox"));
            this.e = view.findViewById(q.a(b.this.c, "id", "sobot_activity_cusfield_listview_items_line"));
            a(this.b);
        }

        public void a(View view) {
            if (com.sobot.chat.b.a(1) && com.sobot.chat.b.a(4) && view != null) {
                com.sobot.chat.b.b.a().a(this.f);
                this.f.getWindow().setFlags(1024, 1024);
                com.sobot.chat.b.b.a().a(this.f, new AnonymousClass1(view));
            }
        }

        /* compiled from: SobotCusFieldAdapter */
        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.adapter.b$b$1  reason: invalid class name */
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

    /* renamed from: a */
    public a getFilter() {
        if (this.g == null) {
            this.g = new a();
        }
        return this.g;
    }

    /* compiled from: SobotCusFieldAdapter */
    public class a extends Filter {
        public a() {
        }

        /* access modifiers changed from: protected */
        @Override // android.widget.Filter
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (charSequence == null || charSequence.length() == 0) {
                filterResults.values = b.this.i;
                filterResults.count = b.this.i.size();
            } else {
                String charSequence2 = charSequence.toString();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < b.this.i.size(); i++) {
                    if (((SobotCusFieldDataInfo) b.this.i.get(i)).getDataName().contains(charSequence2)) {
                        arrayList.add(b.this.i.get(i));
                    }
                }
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
            }
            return filterResults;
        }

        /* access modifiers changed from: protected */
        @Override // android.widget.Filter
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            b.this.h = (List) filterResults.values;
            if (b.this.h.size() > 0) {
                b.this.notifyDataSetChanged();
            } else {
                b.this.notifyDataSetInvalidated();
            }
        }
    }
}
