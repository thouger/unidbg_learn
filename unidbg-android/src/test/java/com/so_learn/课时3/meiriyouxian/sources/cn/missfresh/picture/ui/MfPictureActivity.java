package cn.missfresh.picture.ui;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import cn.missfresh.picture.R;
import cn.missfresh.picture.internal.a.b;
import cn.missfresh.picture.internal.a.d;
import cn.missfresh.picture.internal.a.f;
import cn.missfresh.picture.internal.entity.Album;
import cn.missfresh.picture.internal.entity.LocalMedia;
import cn.missfresh.picture.internal.entity.c;
import cn.missfresh.picture.internal.model.AlbumCollection;
import cn.missfresh.picture.internal.model.SelectedItemCollection;
import cn.missfresh.picture.internal.ui.AlbumFragment;
import cn.missfresh.picture.internal.ui.AlbumPreviewActivity;
import cn.missfresh.picture.internal.ui.MediaSelectionFragment;
import cn.missfresh.picture.internal.ui.SelectedPreviewActivity;
import cn.missfresh.picture.internal.ui.adapter.AlbumMediaAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import java.util.ArrayList;
import java.util.Iterator;

public class MfPictureActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AlbumCollection.a, MediaSelectionFragment.a, AlbumMediaAdapter.b, AlbumMediaAdapter.d, AlbumMediaAdapter.e {
    private String[] a = {Manifest.permission.CAMERA};
    private final AlbumCollection b = new AlbumCollection();
    private b c;
    private SelectedItemCollection d = new SelectedItemCollection(this);
    private c e;
    private boolean f = false;
    private AlbumFragment g;
    private View h;
    private View i;
    private ImageView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private ImageView n;

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public MfPictureActivity() {
        AppMethodBeat.i(18273, false);
        AppMethodBeat.o(18273);
    }

    static /* synthetic */ void a(MfPictureActivity mfPictureActivity, Album album) {
        AppMethodBeat.i(18333, false);
        mfPictureActivity.a(album);
        AppMethodBeat.o(18333);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(18275, false);
        this.e = c.a();
        setTheme(this.e.d);
        super.onCreate(bundle);
        if (!this.e.q) {
            setResult(0);
            finish();
            AppMethodBeat.o(18275);
            return;
        }
        setContentView(R.layout.activity_picture);
        if (this.e.d()) {
            setRequestedOrientation(this.e.e);
        }
        if (this.e.k) {
            this.c = new b(this);
            if (this.e.l != null) {
                this.c.a(this.e.l);
            } else {
                RuntimeException runtimeException = new RuntimeException("Don't forget to set CaptureStrategy.");
                AppMethodBeat.o(18275);
                throw runtimeException;
            }
        }
        this.j = (ImageView) findViewById(R.id.iv_picture_back);
        this.k = (TextView) findViewById(R.id.iv_picture_title);
        this.l = (TextView) findViewById(R.id.iv_picture_next);
        this.m = (TextView) findViewById(R.id.tv_show);
        this.h = findViewById(R.id.container);
        this.i = findViewById(R.id.empty_view);
        this.n = (ImageView) findViewById(R.id.bottom_bg_view);
        this.l.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.d.a(bundle);
        a(false);
        this.b.a(this, this);
        this.b.a(bundle);
        this.b.b();
        this.g = AlbumFragment.a();
        this.g.a(this);
        AppMethodBeat.o(18275);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        AppMethodBeat.i(18279, false);
        super.onSaveInstanceState(bundle);
        this.d.b(bundle);
        this.b.b(bundle);
        AppMethodBeat.o(18279);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        AppMethodBeat.i(18280, false);
        super.onDestroy();
        this.b.a();
        c cVar = this.e;
        cVar.v = null;
        cVar.r = null;
        AppMethodBeat.o(18280);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        AppMethodBeat.i(18282, false);
        setResult(0);
        super.onBackPressed();
        AppMethodBeat.o(18282);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        AppMethodBeat.i(18286, false);
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            AppMethodBeat.o(18286);
            return;
        }
        if (i == 23) {
            Bundle bundleExtra = intent.getBundleExtra("extra_result_bundle");
            ArrayList<LocalMedia> parcelableArrayList = bundleExtra.getParcelableArrayList("state_selection");
            int i3 = bundleExtra.getInt("state_collection_type", 0);
            if (intent.getBooleanExtra("extra_result_apply", false)) {
                Intent intent2 = new Intent();
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
                ArrayList<String> arrayList2 = new ArrayList<>();
                if (parcelableArrayList != null) {
                    Iterator<LocalMedia> it2 = parcelableArrayList.iterator();
                    while (it2.hasNext()) {
                        LocalMedia next = it2.next();
                        arrayList.add(next.a());
                        arrayList2.add(cn.missfresh.picture.internal.a.c.a(this, next.a()));
                    }
                }
                intent2.putParcelableArrayListExtra("extra_result_selection_url", arrayList);
                intent2.putStringArrayListExtra("extra_result_selection_path", arrayList2);
                setResult(-1, intent2);
                finish();
            } else {
                this.d.a(parcelableArrayList, i3);
                Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(MediaSelectionFragment.class.getSimpleName());
                if (findFragmentByTag instanceof MediaSelectionFragment) {
                    ((MediaSelectionFragment) findFragmentByTag).b();
                }
                e();
            }
        } else if (i == 24) {
            Uri a = this.c.a();
            String b = this.c.b();
            ArrayList<? extends Parcelable> arrayList3 = new ArrayList<>();
            arrayList3.add(a);
            ArrayList<String> arrayList4 = new ArrayList<>();
            arrayList4.add(b);
            Intent intent3 = new Intent();
            intent3.putParcelableArrayListExtra("extra_result_selection_url", arrayList3);
            intent3.putStringArrayListExtra("extra_result_selection_path", arrayList4);
            setResult(-1, intent3);
            if (Build.VERSION.SDK_INT < 21) {
                revokeUriPermission(a, 3);
            }
            new f(getApplicationContext(), b, new AnonymousClass1(this));
            finish();
        }
        AppMethodBeat.o(18286);
    }

    /* renamed from: cn.missfresh.picture.ui.MfPictureActivity$1  reason: invalid class name */
    class AnonymousClass1 implements f.a {
        final /* synthetic */ MfPictureActivity a;

        AnonymousClass1(MfPictureActivity mfPictureActivity) {
            JniLib.cV(this, mfPictureActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.FIELD_AUTOFILL_FORGED_COMPONENT_NAME));
        }

        public void a() {
            AppMethodBeat.i(18595, false);
            Log.i("SingleMediaScanner", "scan finish!");
            AppMethodBeat.o(18595);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        AppMethodBeat.i(18289, false);
        if (i == 22) {
            if (iArr[0] == 0) {
                b bVar = this.c;
                if (bVar != null) {
                    bVar.a(this, 24);
                }
            } else {
                Toast.makeText(this, "\u8bf7\u6253\u5f00\u76f8\u673a\u6743\u9650\uff0c\u5426\u5219\u65e0\u6cd5\u62cd\u7167\u5466", 1).show();
            }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
        AppMethodBeat.o(18289);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(18293, true);
        if (view.getId() == R.id.iv_picture_next) {
            if (f() > 0) {
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(18293);
                return;
            }
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("extra_result_selection_url", (ArrayList) this.d.c());
            intent.putStringArrayListExtra("extra_result_selection_path", (ArrayList) this.d.d());
            setResult(-1, intent);
            finish();
        } else if (view.getId() == R.id.iv_picture_back) {
            if (this.f) {
                onBackPressed();
            } else {
                a(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, this.g, MediaSelectionFragment.class.getSimpleName()).commitAllowingStateLoss();
            }
        } else if (view.getId() == R.id.tv_show && this.m.isSelected()) {
            Intent intent2 = new Intent(this, SelectedPreviewActivity.class);
            intent2.putExtra("extra_default_bundle", this.d.a());
            startActivityForResult(intent2, 23);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(18293);
    }

    private void a(boolean z) {
        AppMethodBeat.i(18297, false);
        this.f = z;
        if (this.f) {
            this.k.setText("\u76f8\u8584");
            this.j.setImageDrawable(getResources().getDrawable(R.drawable.ic_close));
            this.l.setVisibility(8);
        } else {
            this.l.setVisibility(0);
            e();
            this.j.setImageDrawable(getResources().getDrawable(R.drawable.ic_picture_back));
        }
        AppMethodBeat.o(18297);
    }

    private void e() {
        boolean z = false;
        AppMethodBeat.i(18301, false);
        int f = this.d.f();
        if (f == 0) {
            this.k.setText(getString(R.string.picture_camera_roll_default, Integer.valueOf(f)));
        } else if (f != 1 || !this.e.c()) {
            this.k.setText(getString(R.string.picture_camera_select_roll, Integer.valueOf(f)));
        } else {
            this.k.setText(getString(R.string.picture_camera_roll_default, Integer.valueOf(f)));
        }
        TextView textView = this.l;
        if (f > 0) {
            z = true;
        }
        textView.setSelected(z);
        this.m.setSelected(this.l.isSelected());
        AppMethodBeat.o(18301);
    }

    @Override // cn.missfresh.picture.internal.model.AlbumCollection.a
    public void a(Cursor cursor) {
        AppMethodBeat.i(18303, false);
        this.g.a(cursor);
        new Handler(Looper.getMainLooper()).post(new AnonymousClass2(this, cursor));
        AppMethodBeat.o(18303);
    }

    /* renamed from: cn.missfresh.picture.ui.MfPictureActivity$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ Cursor a;
        final /* synthetic */ MfPictureActivity b;

        AnonymousClass2(MfPictureActivity mfPictureActivity, Cursor cursor) {
            JniLib.cV(this, mfPictureActivity, cursor, 950);
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(18451, false);
            this.a.moveToPosition(this.b.b.c());
            Album a = Album.a(this.a);
            if (a.e() && c.a().k) {
                a.d();
            }
            MfPictureActivity.a(this.b, a);
            AppMethodBeat.o(18451);
        }
    }

    @Override // cn.missfresh.picture.internal.model.AlbumCollection.a
    public void a() {
        AppMethodBeat.i(18306, false);
        this.g.a((Cursor) null);
        AppMethodBeat.o(18306);
    }

    private void a(Album album) {
        AppMethodBeat.i(18310, false);
        if (!album.e() || !album.f()) {
            this.h.setVisibility(0);
            this.i.setVisibility(8);
            MediaSelectionFragment a = MediaSelectionFragment.a(album);
            a(false);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, a, MediaSelectionFragment.class.getSimpleName()).commitAllowingStateLoss();
        } else {
            this.h.setVisibility(8);
            this.i.setVisibility(0);
        }
        AppMethodBeat.o(18310);
    }

    @Override // cn.missfresh.picture.internal.ui.adapter.AlbumMediaAdapter.b
    public void c() {
        AppMethodBeat.i(18311, false);
        e();
        if (this.e.r != null) {
            this.e.r.a(this.d.c(), this.d.d());
        }
        AppMethodBeat.o(18311);
    }

    @Override // cn.missfresh.picture.internal.ui.adapter.AlbumMediaAdapter.d
    public void a(Album album, LocalMedia localMedia, int i) {
        AppMethodBeat.i(18314, false);
        Intent intent = new Intent(this, AlbumPreviewActivity.class);
        intent.putExtra("extra_album", (Parcelable) album);
        intent.putExtra("extra_item", localMedia);
        intent.putExtra("extra_default_bundle", this.d.a());
        startActivityForResult(intent, 23);
        AppMethodBeat.o(18314);
    }

    @Override // cn.missfresh.picture.internal.ui.MediaSelectionFragment.a
    public SelectedItemCollection b() {
        return this.d;
    }

    @Override // cn.missfresh.picture.internal.ui.adapter.AlbumMediaAdapter.e
    public void d() {
        AppMethodBeat.i(18319, false);
        if (Build.VERSION.SDK_INT < 23) {
            b bVar = this.c;
            if (bVar != null) {
                bVar.a(this, 24);
            }
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == 0) {
            b bVar2 = this.c;
            if (bVar2 != null) {
                bVar2.a(this, 24);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 22);
        }
        AppMethodBeat.o(18319);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AppMethodBeat.i(18322, false);
        this.b.a(i);
        this.g.a(i);
        Album a = Album.a(this.g.b());
        if (a.e() && c.a().k) {
            a.d();
        }
        a(a);
        AppMethodBeat.o(18322);
    }

    private int f() {
        AppMethodBeat.i(18326, false);
        int f = this.d.f();
        int i = 0;
        for (int i2 = 0; i2 < f; i2++) {
            LocalMedia localMedia = this.d.b().get(i2);
            if (localMedia.c() && d.a(localMedia.d) > ((float) this.e.u)) {
                i++;
            }
        }
        AppMethodBeat.o(18326);
        return i;
    }
}
