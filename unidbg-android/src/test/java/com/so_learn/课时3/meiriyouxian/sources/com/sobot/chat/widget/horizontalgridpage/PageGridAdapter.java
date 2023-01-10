package com.sobot.chat.widget.horizontalgridpage;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import java.util.ArrayList;

public class PageGridAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<T> a;
    private b b;
    private int c;
    private int d;
    private int e;
    private int f;
    private ZhiChiMessageBase g;

    public PageGridAdapter(b bVar) {
        this(null, bVar);
    }

    public PageGridAdapter(ArrayList<T> arrayList, b bVar) {
        this.a = b(arrayList);
        this.b = bVar;
    }

    public void a(a aVar) {
        this.d = aVar.d()[0];
        this.e = aVar.d()[1];
        this.f = aVar.c();
    }

    private ArrayList<T> b(ArrayList<T> arrayList) {
        if (arrayList == null) {
            return new ArrayList<>();
        }
        ArrayList<T> arrayList2 = new ArrayList<>();
        int i = this.d * this.e;
        int ceil = (int) Math.ceil(((double) arrayList.size()) / ((double) i));
        for (int i2 = 0; i2 < ceil; i2++) {
            for (int i3 = 0; i3 < this.e; i3++) {
                for (int i4 = 0; i4 < this.d; i4++) {
                    int i5 = (this.e * i4) + i3 + (i2 * i);
                    if (i5 < arrayList.size()) {
                        arrayList2.add(arrayList.get(i5));
                    } else {
                        arrayList2.add(null);
                    }
                }
            }
        }
        return arrayList2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.c <= 0) {
            this.c = (viewGroup.getMeasuredWidth() - (this.f * 2)) / this.e;
        }
        RecyclerView.ViewHolder a = this.b.a(viewGroup, i);
        a.itemView.measure(0, 0);
        a.itemView.getLayoutParams().width = this.c;
        a.itemView.getLayoutParams().height = a.itemView.getMeasuredHeight();
        return a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = this.e;
        if (i2 == 1) {
            viewHolder.itemView.getLayoutParams().width = this.c + (this.f * 2);
            View view = viewHolder.itemView;
            int i3 = this.f;
            view.setPadding(i3, 0, i3, 0);
        } else {
            int i4 = this.d;
            int i5 = i % (i4 * i2);
            if (i5 < i4) {
                viewHolder.itemView.getLayoutParams().width = this.c + this.f;
                viewHolder.itemView.setPadding(this.f, 0, 0, 0);
            } else if (i5 >= (i2 * i4) - i4) {
                viewHolder.itemView.getLayoutParams().width = this.c + this.f;
                viewHolder.itemView.setPadding(0, 0, this.f, 0);
            } else {
                viewHolder.itemView.getLayoutParams().width = this.c;
                viewHolder.itemView.setPadding(0, 0, 0, 0);
            }
        }
        viewHolder.itemView.setTag(Integer.valueOf(i));
        a(viewHolder);
        if (i >= this.a.size() || this.a.get(i) == null) {
            viewHolder.itemView.setVisibility(8);
            return;
        }
        viewHolder.itemView.setVisibility(0);
        this.b.a(viewHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.size();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.horizontalgridpage.PageGridAdapter$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PageGridAdapter.this.b.a(view, ((Integer) view.getTag()).intValue());
        }
    }

    private void a(RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setOnClickListener(new AnonymousClass1());
        viewHolder.itemView.setOnLongClickListener(new AnonymousClass2());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.horizontalgridpage.PageGridAdapter$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnLongClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            PageGridAdapter.this.b.b(view, ((Integer) view.getTag()).intValue());
            return true;
        }
    }

    public ArrayList<T> a() {
        return this.a;
    }

    public void a(ArrayList<T> arrayList) {
        this.a.clear();
        this.a.addAll(b(arrayList));
        notifyDataSetChanged();
    }

    public ZhiChiMessageBase b() {
        return this.g;
    }

    public void a(ZhiChiMessageBase zhiChiMessageBase) {
        this.g = zhiChiMessageBase;
    }
}
