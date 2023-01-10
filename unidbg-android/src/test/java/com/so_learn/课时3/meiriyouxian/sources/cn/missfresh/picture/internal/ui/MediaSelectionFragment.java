package cn.missfresh.picture.internal.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.picture.R;
import cn.missfresh.picture.internal.a.g;
import cn.missfresh.picture.internal.entity.Album;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.entity.c;
import cn.missfresh.picture.internal.model.AlbumMediaCollection;
import cn.missfresh.picture.internal.model.SelectedItemCollection;
import cn.missfresh.picture.internal.ui.adapter.AlbumMediaAdapter;
import cn.missfresh.picture.internal.ui.widget.MediaGridInset;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MediaSelectionFragment extends Fragment implements AlbumMediaCollection.a, AlbumMediaAdapter.b, AlbumMediaAdapter.d {
    private final AlbumMediaCollection a = new AlbumMediaCollection();
    private RecyclerView b;
    private AlbumMediaAdapter c;
    private a d;
    private AlbumMediaAdapter.b e;
    private AlbumMediaAdapter.d f;

    public interface a {
        SelectedItemCollection b();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AppMethodBeat.onResume(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public MediaSelectionFragment() {
        AppMethodBeat.i(18555, false);
        AppMethodBeat.o(18555);
    }

    public static MediaSelectionFragment a(Album album) {
        AppMethodBeat.i(18558, false);
        MediaSelectionFragment mediaSelectionFragment = new MediaSelectionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_album", album);
        mediaSelectionFragment.setArguments(bundle);
        AppMethodBeat.o(18558);
        return mediaSelectionFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        AppMethodBeat.i(18560, false);
        super.onAttach(context);
        if (context instanceof a) {
            this.d = (a) context;
            if (context instanceof AlbumMediaAdapter.b) {
                this.e = (AlbumMediaAdapter.b) context;
            }
            if (context instanceof AlbumMediaAdapter.d) {
                this.f = (AlbumMediaAdapter.d) context;
            }
            AppMethodBeat.o(18560);
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Context must implement SelectionProvider.");
        AppMethodBeat.o(18560);
        throw illegalStateException;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(18562, false);
        View inflate = layoutInflater.inflate(R.layout.fragment_media_selection, viewGroup, false);
        AppMethodBeat.o(18562);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(18564, false);
        super.onViewCreated(view, bundle);
        this.b = (RecyclerView) view.findViewById(R.id.recyclerview);
        AppMethodBeat.o(18564);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        int i;
        AppMethodBeat.i(18565, false);
        super.onActivityCreated(bundle);
        Album parcelable = getArguments().getParcelable("extra_album");
        this.c = new AlbumMediaAdapter(getContext(), this.d.b(), this.b);
        this.c.a((AlbumMediaAdapter.b) this);
        this.c.a((AlbumMediaAdapter.d) this);
        this.b.setHasFixedSize(true);
        c a2 = c.a();
        if (a2.n > 0) {
            i = g.a(getContext(), a2.n);
        } else {
            i = a2.m;
        }
        this.b.setLayoutManager(new GridLayoutManager(getContext(), i));
        this.b.addItemDecoration(new MediaGridInset(i, getResources().getDimensionPixelSize(R.dimen.media_grid_spacing), false));
        this.b.setAdapter(this.c);
        this.a.a(getActivity(), this);
        this.a.a(parcelable, a2.k);
        AppMethodBeat.o(18565);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        AppMethodBeat.i(18566, false);
        super.onDestroyView();
        this.a.a();
        AppMethodBeat.o(18566);
    }

    public void b() {
        AppMethodBeat.i(18568, false);
        this.c.notifyDataSetChanged();
        AppMethodBeat.o(18568);
    }

    @Override // cn.missfresh.picture.internal.model.AlbumMediaCollection.a
    public void a(Cursor cursor) {
        AppMethodBeat.i(18573, false);
        this.c.a(cursor);
        AppMethodBeat.o(18573);
    }

    @Override // cn.missfresh.picture.internal.model.AlbumMediaCollection.a
    public void a() {
        AppMethodBeat.i(18576, false);
        this.c.a((Cursor) null);
        AppMethodBeat.o(18576);
    }

    @Override // cn.missfresh.picture.internal.ui.adapter.AlbumMediaAdapter.b
    public void c() {
        AppMethodBeat.i(18580, false);
        AlbumMediaAdapter.b bVar = this.e;
        if (bVar != null) {
            bVar.c();
        }
        AppMethodBeat.o(18580);
    }

    @Override // cn.missfresh.picture.internal.ui.adapter.AlbumMediaAdapter.d
    public void a(Album album, LocalMedia localMedia, int i) {
        AppMethodBeat.i(18585, false);
        AlbumMediaAdapter.d dVar = this.f;
        if (dVar != null) {
            dVar.a((Album) getArguments().getParcelable("extra_album"), localMedia, i);
        }
        AppMethodBeat.o(18585);
    }
}
