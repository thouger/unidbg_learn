package com.sobot.chat.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sobot.chat.api.model.StDocModel;
import com.sobot.chat.b;
import com.sobot.chat.b.a;
import com.sobot.chat.utils.q;
import java.util.List;

/* compiled from: SobotCategoryAdapter */
public class a extends com.sobot.chat.adapter.base.a<StDocModel> {
    private LayoutInflater a;
    private Activity d;

    public a(Context context, Activity activity, List<StDocModel> list) {
        super(context, list);
        this.d = activity;
        this.a = LayoutInflater.from(context);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        C0136a aVar;
        if (view == null) {
            view = this.a.inflate(q.a(this.c, "sobot_list_item_help_category"), (ViewGroup) null);
            aVar = new C0136a(this.c, this.d, view);
            view.setTag(aVar);
        } else {
            aVar = (C0136a) view.getTag();
        }
        aVar.a(i, (StDocModel) this.b.get(i));
        return view;
    }

    /* compiled from: SobotCategoryAdapter */
    /* renamed from: com.sobot.chat.adapter.a$a  reason: collision with other inner class name */
    private static class C0136a {
        private TextView a;
        private Activity b;

        public C0136a(Context context, Activity activity, View view) {
            this.b = activity;
            this.a = (TextView) view.findViewById(q.g(context, "sobot_tv_title"));
        }

        public void a(int i, StDocModel stDocModel) {
            this.a.setText(stDocModel.getQuestionTitle());
            a(this.a);
        }

        public void a(View view) {
            if (b.a(1) && b.a(4) && view != null) {
                com.sobot.chat.b.b.a().a(this.b);
                this.b.getWindow().setFlags(1024, 1024);
                com.sobot.chat.b.b.a().a(this.b, new AnonymousClass1(view));
            }
        }

        /* compiled from: SobotCategoryAdapter */
        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.adapter.a$a$1  reason: invalid class name */
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
