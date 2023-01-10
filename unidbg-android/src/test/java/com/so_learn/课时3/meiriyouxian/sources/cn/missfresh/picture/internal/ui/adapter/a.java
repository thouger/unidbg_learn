package cn.missfresh.picture.internal.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.picture.R;
import cn.missfresh.picture.internal.entity.Album;
import cn.missfresh.picture.internal.entity.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: AlbumsAdapter */
public class a extends CursorAdapter {
    private final Drawable a;

    public a(Context context, Cursor cursor, boolean z) {
        super(context, cursor, z);
        AppMethodBeat.i(17839, false);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{R.attr.album_thumbnail_placeholder});
        this.a = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(17839);
    }

    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        AppMethodBeat.i(17845, false);
        View inflate = LayoutInflater.from(context).inflate(R.layout.album_list_item, viewGroup, false);
        AppMethodBeat.o(17845);
        return inflate;
    }

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        AppMethodBeat.i(17850, false);
        Album a = Album.a(cursor);
        ((TextView) view.findViewById(R.id.album_name)).setText(a.a(context));
        ((TextView) view.findViewById(R.id.album_media_count)).setText(String.valueOf(a.c()));
        c.a().p.a(context, context.getResources().getDimensionPixelSize(R.dimen.media_grid_size), this.a, (ImageView) view.findViewById(R.id.album_cover), a.b());
        AppMethodBeat.o(17850);
    }
}
