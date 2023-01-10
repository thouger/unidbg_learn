package cn.missfresh.picture.internal.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import cn.missfresh.picture.internal.loader.AlbumLoader;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.ref.WeakReference;

public class AlbumCollection implements LoaderManager.LoaderCallbacks<Cursor> {
    private WeakReference<Context> a;
    private LoaderManager b;
    private a c;
    private int d;
    private boolean e;

    public interface a {
        void a();

        void a(Cursor cursor);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        AppMethodBeat.i(19300, false);
        a(loader, (Cursor) obj);
        AppMethodBeat.o(19300);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        AppMethodBeat.i(19271, false);
        Context context = this.a.get();
        if (context == null) {
            AppMethodBeat.o(19271);
            return null;
        }
        this.e = false;
        CursorLoader a2 = AlbumLoader.a(context);
        AppMethodBeat.o(19271);
        return a2;
    }

    public void a(Loader<Cursor> loader, Cursor cursor) {
        AppMethodBeat.i(19274, false);
        if (this.a.get() == null) {
            AppMethodBeat.o(19274);
            return;
        }
        if (!this.e) {
            this.e = true;
            this.c.a(cursor);
        }
        AppMethodBeat.o(19274);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<Cursor> loader) {
        AppMethodBeat.i(19277, false);
        if (this.a.get() == null) {
            AppMethodBeat.o(19277);
            return;
        }
        this.c.a();
        AppMethodBeat.o(19277);
    }

    public void a(FragmentActivity fragmentActivity, a aVar) {
        AppMethodBeat.i(19282, false);
        this.a = new WeakReference<>(fragmentActivity);
        this.b = fragmentActivity.getSupportLoaderManager();
        this.c = aVar;
        AppMethodBeat.o(19282);
    }

    public void a(Bundle bundle) {
        AppMethodBeat.i(19285, false);
        if (bundle == null) {
            AppMethodBeat.o(19285);
            return;
        }
        this.d = bundle.getInt("state_current_selection");
        AppMethodBeat.o(19285);
    }

    public void b(Bundle bundle) {
        AppMethodBeat.i(19288, false);
        bundle.putInt("state_current_selection", this.d);
        AppMethodBeat.o(19288);
    }

    public void a() {
        AppMethodBeat.i(19291, false);
        LoaderManager loaderManager = this.b;
        if (loaderManager != null) {
            loaderManager.destroyLoader(1);
        }
        this.c = null;
        AppMethodBeat.o(19291);
    }

    public void b() {
        AppMethodBeat.i(19293, false);
        this.b.initLoader(1, null, this);
        AppMethodBeat.o(19293);
    }

    public int c() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }
}
