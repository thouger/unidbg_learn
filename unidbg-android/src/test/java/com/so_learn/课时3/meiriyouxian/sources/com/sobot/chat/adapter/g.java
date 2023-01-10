package com.sobot.chat.adapter;

import android.content.Context;
import android.media.TtmlUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sobot.chat.api.model.ZhiChiUploadAppFileModelResult;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.t;
import com.sobot.chat.widget.image.SobotRCImageView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SobotPicListAdapter */
public class g extends com.sobot.chat.adapter.base.a<ZhiChiUploadAppFileModelResult> {
    b a;

    /* compiled from: SobotPicListAdapter */
    public interface b {
        void a(View view, int i, int i2);
    }

    public g(Context context, List<ZhiChiUploadAppFileModelResult> list) {
        super(context, list);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult = (ZhiChiUploadAppFileModelResult) this.b.get(i);
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, "sobot_piclist_item"), (ViewGroup) null);
            aVar = new a(this.c, view);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(i);
        aVar.a(this.a);
        aVar.a(zhiChiUploadAppFileModelResult);
        return view;
    }

    /* renamed from: a */
    public ZhiChiUploadAppFileModelResult getItem(int i) {
        if (i < 0 || i >= this.b.size()) {
            return null;
        }
        return (ZhiChiUploadAppFileModelResult) this.b.get(i);
    }

    public void a(ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult) {
        ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult2;
        if (this.b != null) {
            int size = this.b.size() + -1 < 0 ? 0 : this.b.size() - 1;
            this.b.add(size, zhiChiUploadAppFileModelResult);
            if (this.b.size() >= 10 && (zhiChiUploadAppFileModelResult2 = (ZhiChiUploadAppFileModelResult) this.b.get(size)) != null && zhiChiUploadAppFileModelResult2.getViewState() == 0) {
                this.b.remove(size);
            }
            a();
        }
    }

    public void a(List<ZhiChiUploadAppFileModelResult> list) {
        this.b.clear();
        this.b.addAll(list);
        a();
    }

    public void a() {
        if (this.b.size() == 0) {
            ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult = new ZhiChiUploadAppFileModelResult();
            zhiChiUploadAppFileModelResult.setViewState(0);
            this.b.add(zhiChiUploadAppFileModelResult);
        } else {
            ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult2 = (ZhiChiUploadAppFileModelResult) this.b.get(this.b.size() + -1 < 0 ? 0 : this.b.size() - 1);
            if (this.b.size() < 10 && zhiChiUploadAppFileModelResult2.getViewState() != 0) {
                ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult3 = new ZhiChiUploadAppFileModelResult();
                zhiChiUploadAppFileModelResult3.setViewState(0);
                this.b.add(zhiChiUploadAppFileModelResult3);
            }
        }
        notifyDataSetChanged();
    }

    public ArrayList<ZhiChiUploadAppFileModelResult> b() {
        ArrayList<ZhiChiUploadAppFileModelResult> arrayList = new ArrayList<>();
        for (int i = 0; i < this.b.size(); i++) {
            ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult = (ZhiChiUploadAppFileModelResult) this.b.get(i);
            if (zhiChiUploadAppFileModelResult.getViewState() != 0) {
                arrayList.add(zhiChiUploadAppFileModelResult);
            }
        }
        return arrayList;
    }

    @Override // com.sobot.chat.adapter.base.a, android.widget.Adapter
    public int getCount() {
        if (this.b.size() <= 10) {
            return this.b.size();
        }
        return 10;
    }

    /* compiled from: SobotPicListAdapter */
    /* access modifiers changed from: private */
    public static class a {
        SobotRCImageView a;
        LinearLayout b;
        ImageView c;
        private Context d;
        private ImageView e;
        private b f;
        private int g;

        a(Context context, View view) {
            this.d = context;
            this.a = (SobotRCImageView) view.findViewById(q.a(context, "id", "sobot_iv_pic"));
            this.c = (ImageView) view.findViewById(q.a(context, "id", "sobot_iv_pic_add"));
            this.b = (LinearLayout) view.findViewById(q.a(context, "id", "sobot_iv_pic_add_ll"));
            this.e = (ImageView) view.findViewById(q.a(context, "id", "sobot_iv_pic_delete"));
        }

        public void a(b bVar) {
            this.f = bVar;
        }

        public void a(int i) {
            this.g = i;
        }

        /* access modifiers changed from: package-private */
        public void a(ZhiChiUploadAppFileModelResult zhiChiUploadAppFileModelResult) {
            if (zhiChiUploadAppFileModelResult.getViewState() == 0) {
                this.a.setVisibility(8);
                this.b.setVisibility(0);
                this.e.setVisibility(8);
            } else {
                this.a.setVisibility(0);
                this.b.setVisibility(8);
                this.e.setVisibility(0);
                t.a(this.d, zhiChiUploadAppFileModelResult.getFileLocalPath(), this.a, q.a(this.d, "drawable", "sobot_default_pic"), q.a(this.d, "drawable", "sobot_default_pic_err"));
            }
            this.a.setOnClickListener(new SobotPicListAdapter$SobotFileHolder$1(this));
            this.b.setOnClickListener(new SobotPicListAdapter$SobotFileHolder$2(this));
            this.e.setOnClickListener(new SobotPicListAdapter$SobotFileHolder$3(this));
        }
    }

    public void a(b bVar) {
        this.a = bVar;
    }
}
