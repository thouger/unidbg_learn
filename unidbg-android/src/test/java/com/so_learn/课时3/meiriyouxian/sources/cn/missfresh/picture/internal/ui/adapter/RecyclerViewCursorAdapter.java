package cn.missfresh.picture.internal.ui.adapter;

import android.database.Cursor;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public abstract class RecyclerViewCursorAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private Cursor a;
    private int b;

    /* access modifiers changed from: protected */
    public abstract int a(int i, Cursor cursor);

    /* access modifiers changed from: protected */
    public abstract void a(VH vh, Cursor cursor);

    RecyclerViewCursorAdapter(Cursor cursor) {
        setHasStableIds(true);
        a(cursor);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VH vh, int i) {
        if (!b(this.a)) {
            throw new IllegalStateException("Cannot bind view holder when cursor is in invalid state.");
        } else if (this.a.moveToPosition(i)) {
            a((RecyclerViewCursorAdapter<VH>) vh, this.a);
        } else {
            throw new IllegalStateException("Could not move cursor to position " + i + " when trying to bind view holder");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.a.moveToPosition(i)) {
            return a(i, this.a);
        }
        throw new IllegalStateException("Could not move cursor to position " + i + " when trying to get item view type.");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (b(this.a)) {
            return this.a.getCount();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        if (!b(this.a)) {
            throw new IllegalStateException("Cannot lookup item id when cursor is in invalid state.");
        } else if (this.a.moveToPosition(i)) {
            return this.a.getLong(this.b);
        } else {
            throw new IllegalStateException("Could not move cursor to position " + i + " when trying to get an item id");
        }
    }

    public void a(Cursor cursor) {
        if (cursor != this.a) {
            if (cursor != null) {
                this.a = cursor;
                this.b = this.a.getColumnIndexOrThrow("_id");
                notifyDataSetChanged();
                return;
            }
            notifyItemRangeRemoved(0, getItemCount());
            this.a = null;
            this.b = -1;
        }
    }

    private boolean b(Cursor cursor) {
        return cursor != null && !cursor.isClosed();
    }
}
