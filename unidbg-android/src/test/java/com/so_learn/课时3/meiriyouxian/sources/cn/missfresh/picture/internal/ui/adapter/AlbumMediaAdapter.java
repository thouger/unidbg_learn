package cn.missfresh.picture.internal.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.picture.R;
import cn.missfresh.picture.internal.entity.Album;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.model.SelectedItemCollection;
import cn.missfresh.picture.internal.ui.widget.CheckView;
import cn.missfresh.picture.internal.ui.widget.MediaGrid;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class AlbumMediaAdapter extends RecyclerViewCursorAdapter<RecyclerView.ViewHolder> implements MediaGrid.a {
    private final SelectedItemCollection a;
    private final Drawable b;
    private cn.missfresh.picture.internal.entity.c c = cn.missfresh.picture.internal.entity.c.a();
    private b d;
    private d e;
    private RecyclerView f;
    private int g;

    public interface b {
        void c();
    }

    public interface d {
        void a(Album album, LocalMedia localMedia, int i);
    }

    public interface e {
        void d();
    }

    public AlbumMediaAdapter(Context context, SelectedItemCollection selectedItemCollection, RecyclerView recyclerView) {
        super(null);
        AppMethodBeat.i(19150, false);
        this.a = selectedItemCollection;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.item_placeholder});
        this.b = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        this.f = recyclerView;
        AppMethodBeat.o(19150);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(19155, false);
        if (i == 1) {
            a aVar = new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_capture_item, viewGroup, false));
            aVar.itemView.setOnClickListener(new AnonymousClass1());
            AppMethodBeat.o(19155);
            return aVar;
        } else if (i == 2) {
            c cVar = new c(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.media_grid_item, viewGroup, false));
            AppMethodBeat.o(19155);
            return cVar;
        } else {
            AppMethodBeat.o(19155);
            return null;
        }
    }

    /* renamed from: cn.missfresh.picture.internal.ui.adapter.AlbumMediaAdapter$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(18633, false);
            if (view.getContext() instanceof e) {
                ((e) view.getContext()).d();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(18633);
        }
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.picture.internal.ui.adapter.RecyclerViewCursorAdapter
    public void a(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        AppMethodBeat.i(19158, false);
        if (viewHolder instanceof c) {
            c cVar = (c) viewHolder;
            LocalMedia a2 = LocalMedia.a(cursor);
            cVar.a.a(new MediaGrid.b(a(cVar.a.getContext()), this.b, this.c.f, viewHolder));
            cVar.a.a(a2);
            cVar.a.setOnMediaGridClickListener(this);
            a(a2, cVar.a);
        }
        AppMethodBeat.o(19158);
    }

    private void a(LocalMedia localMedia, MediaGrid mediaGrid) {
        AppMethodBeat.i(19162, false);
        if (this.c.f) {
            int f = this.a.f(localMedia);
            if (f > 0) {
                mediaGrid.setCheckEnabled(true);
                mediaGrid.setCheckedNum(f);
            } else if (this.a.e()) {
                mediaGrid.setCheckEnabled(false);
                mediaGrid.setCheckedNum(Integer.MIN_VALUE);
            } else {
                mediaGrid.setCheckEnabled(true);
                mediaGrid.setCheckedNum(f);
            }
        } else if (this.a.c(localMedia)) {
            mediaGrid.setCheckEnabled(true);
            mediaGrid.setChecked(true);
        } else if (this.a.e()) {
            mediaGrid.setCheckEnabled(false);
            mediaGrid.setChecked(false);
        } else {
            mediaGrid.setCheckEnabled(true);
            mediaGrid.setChecked(false);
        }
        AppMethodBeat.o(19162);
    }

    @Override // cn.missfresh.picture.internal.ui.widget.MediaGrid.a
    public void a(ImageView imageView, LocalMedia localMedia, RecyclerView.ViewHolder viewHolder) {
        AppMethodBeat.i(19166, false);
        if (this.c.w) {
            d dVar = this.e;
            if (dVar != null) {
                dVar.a(null, localMedia, viewHolder.getAdapterPosition());
            }
        } else {
            a(localMedia, viewHolder);
        }
        AppMethodBeat.o(19166);
    }

    @Override // cn.missfresh.picture.internal.ui.widget.MediaGrid.a
    public void a(CheckView checkView, LocalMedia localMedia, RecyclerView.ViewHolder viewHolder) {
        AppMethodBeat.i(19169, false);
        a(localMedia, viewHolder);
        AppMethodBeat.o(19169);
    }

    private void a(LocalMedia localMedia, RecyclerView.ViewHolder viewHolder) {
        AppMethodBeat.i(19172, false);
        if (this.c.f) {
            if (this.a.f(localMedia) != Integer.MIN_VALUE) {
                this.a.b(localMedia);
                a();
            } else if (a(viewHolder.itemView.getContext(), localMedia)) {
                this.a.a(localMedia);
                a();
            }
        } else if (this.a.c(localMedia)) {
            this.a.b(localMedia);
            a();
        } else if (a(viewHolder.itemView.getContext(), localMedia)) {
            this.a.a(localMedia);
            a();
        }
        AppMethodBeat.o(19172);
    }

    private void a() {
        AppMethodBeat.i(19175, false);
        notifyDataSetChanged();
        b bVar = this.d;
        if (bVar != null) {
            bVar.c();
        }
        AppMethodBeat.o(19175);
    }

    @Override // cn.missfresh.picture.internal.ui.adapter.RecyclerViewCursorAdapter
    public int a(int i, Cursor cursor) {
        AppMethodBeat.i(19178, false);
        int i2 = LocalMedia.a(cursor).b() ? 1 : 2;
        AppMethodBeat.o(19178);
        return i2;
    }

    private boolean a(Context context, LocalMedia localMedia) {
        boolean z = false;
        AppMethodBeat.i(19183, false);
        cn.missfresh.picture.internal.entity.b d2 = this.a.d(localMedia);
        cn.missfresh.picture.internal.entity.b.a(context, d2);
        if (d2 == null) {
            z = true;
        }
        AppMethodBeat.o(19183);
        return z;
    }

    public void a(b bVar) {
        this.d = bVar;
    }

    public void a(d dVar) {
        this.e = dVar;
    }

    private int a(Context context) {
        AppMethodBeat.i(19196, false);
        if (this.g == 0) {
            int spanCount = ((GridLayoutManager) this.f.getLayoutManager()).getSpanCount();
            this.g = (context.getResources().getDisplayMetrics().widthPixels - (context.getResources().getDimensionPixelSize(R.dimen.media_grid_spacing) * (spanCount - 1))) / spanCount;
            this.g = (int) (((float) this.g) * this.c.o);
        }
        int i = this.g;
        AppMethodBeat.o(19196);
        return i;
    }

    private static class c extends RecyclerView.ViewHolder {
        private MediaGrid a;

        c(View view) {
            super(view);
            AppMethodBeat.i(18725, false);
            this.a = (MediaGrid) view;
            AppMethodBeat.o(18725);
        }
    }

    private static class a extends RecyclerView.ViewHolder {
        a(View view) {
            super(view);
        }
    }
}
