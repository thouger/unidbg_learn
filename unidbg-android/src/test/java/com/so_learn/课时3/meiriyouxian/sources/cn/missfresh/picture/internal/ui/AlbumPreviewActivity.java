package cn.missfresh.picture.internal.ui;

import android.database.Cursor;
import android.os.Bundle;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.model.AlbumMediaCollection;
import cn.missfresh.picture.internal.ui.adapter.PreviewPagerAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import java.util.ArrayList;

public class AlbumPreviewActivity extends BasePreviewActivity implements AlbumMediaCollection.a {
    private AlbumMediaCollection l = new AlbumMediaCollection();
    private boolean m;

    @Override // cn.missfresh.picture.internal.model.AlbumMediaCollection.a
    public void a() {
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.picture.internal.ui.BasePreviewActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, Integer.valueOf((int) MetricsProto.MetricsEvent.FIELD_NOTIFICATION_GROUP_SUMMARY));
    }

    @Override // cn.missfresh.picture.internal.ui.BasePreviewActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public AlbumPreviewActivity() {
        AppMethodBeat.i(18879, false);
        AppMethodBeat.o(18879);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        AppMethodBeat.i(18887, false);
        super.onDestroy();
        this.l.a();
        AppMethodBeat.o(18887);
    }

    @Override // cn.missfresh.picture.internal.model.AlbumMediaCollection.a
    public void a(Cursor cursor) {
        AppMethodBeat.i(18889, false);
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(LocalMedia.a(cursor));
        }
        if (arrayList.isEmpty()) {
            AppMethodBeat.o(18889);
            return;
        }
        PreviewPagerAdapter previewPagerAdapter = (PreviewPagerAdapter) this.c.getAdapter();
        previewPagerAdapter.a(arrayList);
        previewPagerAdapter.notifyDataSetChanged();
        if (!this.m) {
            this.m = true;
            int indexOf = arrayList.indexOf((LocalMedia) getIntent().getParcelableExtra("extra_item"));
            this.c.setCurrentItem(indexOf, false);
            this.j = indexOf;
        }
        AppMethodBeat.o(18889);
    }
}
