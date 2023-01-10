package cn.missfresh.picture.internal.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import cn.missfresh.picture.internal.entity.Album;
import cn.missfresh.picture.internal.loader.AlbumMediaLoader;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.ref.WeakReference;

public class AlbumMediaCollection implements LoaderManager.LoaderCallbacks<Cursor> {
    private WeakReference<Context> a;
    private LoaderManager b;
    private a c;

    public interface a {
        void a();

        void a(Cursor cursor);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        AppMethodBeat.i(18163, false);
        a(loader, (Cursor) obj);
        AppMethodBeat.o(18163);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        boolean z = false;
        AppMethodBeat.i(18151, false);
        Context context = this.a.get();
        if (context == null) {
            AppMethodBeat.o(18151);
            return null;
        }
        Album parcelable = bundle.getParcelable("args_album");
        if (parcelable == null) {
            AppMethodBeat.o(18151);
            return null;
        }
        if (parcelable.e() && bundle.getBoolean("args_enable_capture", false)) {
            z = true;
        }
        CursorLoader a2 = AlbumMediaLoader.a(context, parcelable, z);
        AppMethodBeat.o(18151);
        return a2;
    }

    public void a(Loader<Cursor> loader, Cursor cursor) {
        AppMethodBeat.i(18153, false);
        if (this.a.get() == null) {
            AppMethodBeat.o(18153);
            return;
        }
        this.c.a(cursor);
        AppMethodBeat.o(18153);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<Cursor> loader) {
        AppMethodBeat.i(18154, false);
        if (this.a.get() == null) {
            AppMethodBeat.o(18154);
            return;
        }
        this.c.a();
        AppMethodBeat.o(18154);
    }

    public void a(FragmentActivity fragmentActivity, a aVar) {
        AppMethodBeat.i(18156, false);
        this.a = new WeakReference<>(fragmentActivity);
        this.b = fragmentActivity.getSupportLoaderManager();
        this.c = aVar;
        AppMethodBeat.o(18156);
    }

    public void a() {
        AppMethodBeat.i(18157, false);
        LoaderManager loaderManager = this.b;
        if (loaderManager != null) {
            loaderManager.destroyLoader(2);
        }
        this.c = null;
        AppMethodBeat.o(18157);
    }

    public void a(Album album) {
        AppMethodBeat.i(18159, false);
        a(album, false);
        AppMethodBeat.o(18159);
    }

    public void a(Album album, boolean z) {
        AppMethodBeat.i(18161, false);
        Bundle bundle = new Bundle();
        bundle.putParcelable("args_album", album);
        bundle.putBoolean("args_enable_capture", z);
        this.b.initLoader(2, bundle, this);
        AppMethodBeat.o(18161);
    }
}
