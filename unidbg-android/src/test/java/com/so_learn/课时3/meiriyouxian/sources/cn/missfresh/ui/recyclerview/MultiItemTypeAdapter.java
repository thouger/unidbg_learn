package cn.missfresh.ui.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.recyclerview.base.ViewHolder;
import cn.missfresh.ui.recyclerview.base.b;
import com.android.internal.logging.nano.MetricsProto;
import java.util.List;

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context a;
    protected List<T> b;
    protected b c;
    protected a d;

    public interface a {
        void a(View view, RecyclerView.ViewHolder viewHolder, int i);

        boolean b(View view, RecyclerView.ViewHolder viewHolder, int i);
    }

    public void a(ViewHolder viewHolder, View view) {
    }

    /* access modifiers changed from: protected */
    public boolean a(int i) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(1029, false);
        a((ViewHolder) viewHolder, i);
        AppMethodBeat.o(1029);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(1031, false);
        ViewHolder a2 = a(viewGroup, i);
        AppMethodBeat.o(1031);
        return a2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.SETTINGS_CHOOSE_LOCK_DIALOG, false);
        if (!a()) {
            int itemViewType = super.getItemViewType(i);
            AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_CHOOSE_LOCK_DIALOG);
            return itemViewType;
        }
        int a2 = this.c.a(this.b.get(i), i);
        AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_CHOOSE_LOCK_DIALOG);
        return a2;
    }

    public ViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(994, false);
        ViewHolder a2 = ViewHolder.a(this.a, viewGroup, this.c.a(i).a());
        a(a2, a2.a());
        a(viewGroup, a2, i);
        AppMethodBeat.o(994);
        return a2;
    }

    public void a(ViewHolder viewHolder, T t) {
        AppMethodBeat.i(1000, false);
        this.c.a(viewHolder, t, viewHolder.getAdapterPosition());
        AppMethodBeat.o(1000);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.recyclerview.MultiItemTypeAdapter$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ ViewHolder a;

        AnonymousClass1(ViewHolder viewHolder) {
            this.a = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(963, false);
            if (MultiItemTypeAdapter.this.d != null) {
                MultiItemTypeAdapter.this.d.a(view, this.a, this.a.getAdapterPosition());
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(963);
        }
    }

    /* access modifiers changed from: protected */
    public void a(ViewGroup viewGroup, ViewHolder viewHolder, int i) {
        AppMethodBeat.i(1005, false);
        if (!a(i)) {
            AppMethodBeat.o(1005);
            return;
        }
        viewHolder.a().setOnClickListener(new AnonymousClass1(viewHolder));
        viewHolder.a().setOnLongClickListener(new AnonymousClass2(viewHolder));
        AppMethodBeat.o(1005);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.recyclerview.MultiItemTypeAdapter$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnLongClickListener {
        final /* synthetic */ ViewHolder a;

        AnonymousClass2(ViewHolder viewHolder) {
            this.a = viewHolder;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            AppMethodBeat.i(976, false);
            if (MultiItemTypeAdapter.this.d != null) {
                boolean b = MultiItemTypeAdapter.this.d.b(view, this.a, this.a.getAdapterPosition());
                AppMethodBeat.o(976);
                return b;
            }
            AppMethodBeat.o(976);
            return false;
        }
    }

    public void a(ViewHolder viewHolder, int i) {
        AppMethodBeat.i(1008, false);
        a(viewHolder, (ViewHolder) this.b.get(i));
        AppMethodBeat.o(1008);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        AppMethodBeat.i(1010, false);
        int size = this.b.size();
        AppMethodBeat.o(1010);
        return size;
    }

    /* access modifiers changed from: protected */
    public boolean a() {
        boolean z = false;
        AppMethodBeat.i(1023, false);
        if (this.c.a() > 0) {
            z = true;
        }
        AppMethodBeat.o(1023);
        return z;
    }
}
