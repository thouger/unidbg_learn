package cn.missfresh.picture.internal.model;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import cn.missfresh.picture.R;
import cn.missfresh.picture.internal.a.c;
import cn.missfresh.picture.internal.a.d;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.entity.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SelectedItemCollection {
    private final Context a;
    private Set<LocalMedia> b;
    private int c = 0;

    public SelectedItemCollection(Context context) {
        this.a = context;
    }

    public void a(Bundle bundle) {
        AppMethodBeat.i(17960, false);
        if (bundle == null) {
            this.b = new LinkedHashSet();
        } else {
            this.b = new LinkedHashSet(bundle.getParcelableArrayList("state_selection"));
            this.c = bundle.getInt("state_collection_type", 0);
        }
        AppMethodBeat.o(17960);
    }

    public void b(Bundle bundle) {
        AppMethodBeat.i(17966, false);
        bundle.putParcelableArrayList("state_selection", new ArrayList<>(this.b));
        bundle.putInt("state_collection_type", this.c);
        AppMethodBeat.o(17966);
    }

    public Bundle a() {
        AppMethodBeat.i(17969, false);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("state_selection", new ArrayList<>(this.b));
        bundle.putInt("state_collection_type", this.c);
        AppMethodBeat.o(17969);
        return bundle;
    }

    public boolean a(LocalMedia localMedia) {
        AppMethodBeat.i(17973, false);
        if (!e(localMedia)) {
            boolean add = this.b.add(localMedia);
            if (add) {
                int i = this.c;
                if (i == 0) {
                    if (localMedia.c()) {
                        this.c = 1;
                    } else if (localMedia.e()) {
                        this.c = 2;
                    }
                } else if (i == 1) {
                    if (localMedia.e()) {
                        this.c = 3;
                    }
                } else if (i == 2 && localMedia.c()) {
                    this.c = 3;
                }
            }
            AppMethodBeat.o(17973);
            return add;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Can't select images and videos at the same time.");
        AppMethodBeat.o(17973);
        throw illegalArgumentException;
    }

    public boolean b(LocalMedia localMedia) {
        AppMethodBeat.i(17976, false);
        boolean remove = this.b.remove(localMedia);
        if (remove) {
            if (this.b.size() == 0) {
                this.c = 0;
            } else if (this.c == 3) {
                h();
            }
        }
        AppMethodBeat.o(17976);
        return remove;
    }

    public void a(ArrayList<LocalMedia> arrayList, int i) {
        AppMethodBeat.i(17980, false);
        if (arrayList.size() == 0) {
            this.c = 0;
        } else {
            this.c = i;
        }
        this.b.clear();
        this.b.addAll(arrayList);
        AppMethodBeat.o(17980);
    }

    public List<LocalMedia> b() {
        AppMethodBeat.i(17984, false);
        ArrayList arrayList = new ArrayList(this.b);
        AppMethodBeat.o(17984);
        return arrayList;
    }

    public List<Uri> c() {
        AppMethodBeat.i(17987, false);
        ArrayList arrayList = new ArrayList();
        for (LocalMedia localMedia : this.b) {
            arrayList.add(localMedia.a());
        }
        AppMethodBeat.o(17987);
        return arrayList;
    }

    public List<String> d() {
        AppMethodBeat.i(17989, false);
        ArrayList arrayList = new ArrayList();
        for (LocalMedia localMedia : this.b) {
            arrayList.add(c.a(this.a, localMedia.a()));
        }
        AppMethodBeat.o(17989);
        return arrayList;
    }

    public boolean c(LocalMedia localMedia) {
        AppMethodBeat.i(17996, false);
        boolean contains = this.b.contains(localMedia);
        AppMethodBeat.o(17996);
        return contains;
    }

    public b d(LocalMedia localMedia) {
        String str;
        AppMethodBeat.i(17999, false);
        if (e()) {
            int g = g();
            try {
                str = this.a.getResources().getQuantityString(R.plurals.error_over_count, g, Integer.valueOf(g));
            } catch (Resources.NotFoundException unused) {
                str = this.a.getString(R.string.error_over_count, Integer.valueOf(g));
            } catch (NoClassDefFoundError unused2) {
                str = this.a.getString(R.string.error_over_count, Integer.valueOf(g));
            }
            b bVar = new b(str);
            AppMethodBeat.o(17999);
            return bVar;
        } else if (e(localMedia)) {
            b bVar2 = new b(this.a.getString(R.string.error_type_conflict));
            AppMethodBeat.o(17999);
            return bVar2;
        } else {
            b a = d.a(this.a, localMedia);
            AppMethodBeat.o(17999);
            return a;
        }
    }

    public boolean e() {
        boolean z = false;
        AppMethodBeat.i(18002, false);
        if (this.b.size() == g()) {
            z = true;
        }
        AppMethodBeat.o(18002);
        return z;
    }

    private int g() {
        AppMethodBeat.i(18005, false);
        cn.missfresh.picture.internal.entity.c a = cn.missfresh.picture.internal.entity.c.a();
        if (a.g > 0) {
            int i = a.g;
            AppMethodBeat.o(18005);
            return i;
        }
        int i2 = this.c;
        if (i2 == 1) {
            int i3 = a.h;
            AppMethodBeat.o(18005);
            return i3;
        } else if (i2 == 2) {
            int i4 = a.i;
            AppMethodBeat.o(18005);
            return i4;
        } else {
            int i5 = a.g;
            AppMethodBeat.o(18005);
            return i5;
        }
    }

    private void h() {
        boolean z = false;
        AppMethodBeat.i(18008, false);
        boolean z2 = false;
        for (LocalMedia localMedia : this.b) {
            if (localMedia.c() && !z) {
                z = true;
            }
            if (localMedia.e() && !z2) {
                z2 = true;
            }
        }
        if (z && z2) {
            this.c = 3;
        } else if (z) {
            this.c = 1;
        } else if (z2) {
            this.c = 2;
        }
        AppMethodBeat.o(18008);
    }

    public boolean e(LocalMedia localMedia) {
        int i;
        int i2;
        boolean z = false;
        AppMethodBeat.i(18011, false);
        if (cn.missfresh.picture.internal.entity.c.a().b && ((localMedia.c() && ((i2 = this.c) == 2 || i2 == 3)) || (localMedia.e() && ((i = this.c) == 1 || i == 3)))) {
            z = true;
        }
        AppMethodBeat.o(18011);
        return z;
    }

    public int f() {
        AppMethodBeat.i(18013, false);
        int size = this.b.size();
        AppMethodBeat.o(18013);
        return size;
    }

    public int f(LocalMedia localMedia) {
        AppMethodBeat.i(18016, false);
        int indexOf = new ArrayList(this.b).indexOf(localMedia);
        int i = indexOf == -1 ? Integer.MIN_VALUE : indexOf + 1;
        AppMethodBeat.o(18016);
        return i;
    }
}
