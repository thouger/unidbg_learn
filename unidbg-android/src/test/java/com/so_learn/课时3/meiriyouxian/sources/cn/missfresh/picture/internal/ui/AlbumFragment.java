package cn.missfresh.picture.internal.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import cn.missfresh.picture.R;
import cn.missfresh.picture.internal.ui.adapter.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class AlbumFragment extends Fragment {
    private ListView a;
    private a b;
    private Cursor c;
    private AdapterView.OnItemClickListener d;

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

    public static AlbumFragment a() {
        AppMethodBeat.i(18365, false);
        AlbumFragment albumFragment = new AlbumFragment();
        AppMethodBeat.o(18365);
        return albumFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(18368, false);
        View inflate = layoutInflater.inflate(R.layout.fragment_album, viewGroup, false);
        AppMethodBeat.o(18368);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(18370, false);
        super.onViewCreated(view, bundle);
        this.a = (ListView) view.findViewById(R.id.lv_album);
        AppMethodBeat.o(18370);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        AppMethodBeat.i(18372, false);
        super.onActivityCreated(bundle);
        this.b = new a(getActivity(), null, false);
        this.a.setAdapter((ListAdapter) this.b);
        this.a.setOnItemClickListener(new AnonymousClass1());
        this.b.swapCursor(this.c);
        AppMethodBeat.o(18372);
    }

    /* renamed from: cn.missfresh.picture.internal.ui.AlbumFragment$1  reason: invalid class name */
    class AnonymousClass1 implements AdapterView.OnItemClickListener {
        AnonymousClass1() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            AppMethodBeat.i(18873, false);
            if (AlbumFragment.this.d != null) {
                AlbumFragment.this.d.onItemClick(adapterView, view, i, j);
            }
            AppMethodBeat.o(18873);
        }
    }

    public void a(Cursor cursor) {
        this.c = cursor;
    }

    public void a(int i) {
        AppMethodBeat.i(18377, false);
        this.b.getCursor().moveToPosition(i);
        AppMethodBeat.o(18377);
    }

    public Cursor b() {
        AppMethodBeat.i(18379, false);
        Cursor cursor = this.b.getCursor();
        AppMethodBeat.o(18379);
        return cursor;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        AppMethodBeat.i(18382, false);
        super.onDestroyView();
        AppMethodBeat.o(18382);
    }

    public void a(AdapterView.OnItemClickListener onItemClickListener) {
        this.d = onItemClickListener;
    }
}
