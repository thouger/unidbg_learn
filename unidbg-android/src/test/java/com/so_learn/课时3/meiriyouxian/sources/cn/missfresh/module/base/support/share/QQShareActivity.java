package cn.missfresh.module.base.support.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.support.share.b;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class QQShareActivity extends AppCompatActivity implements IUiListener {
    private String a = getClass().getSimpleName();
    private Tencent b;
    private b.a c;
    private String d;

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public QQShareActivity() {
        AppMethodBeat.i(22436, false);
        AppMethodBeat.o(22436);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(22437, false);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT < 26) {
            setRequestedOrientation(1);
        }
        setContentView(R.layout.activity_qqshare);
        this.b = Tencent.createInstance("101438073", getApplicationContext());
        this.d = getIntent().getStringExtra("picPath");
        this.c = new b.a();
        this.c.a = getIntent().getIntExtra("shareType", 0);
        this.c.b = getIntent().getStringExtra("title");
        this.c.c = getIntent().getStringExtra("summary");
        this.c.d = getIntent().getStringExtra("targetUrl");
        this.c.e = getIntent().getStringExtra("imageUrl");
        this.c.f = g.a().d();
        if (this.c.a != 1 || cn.missfresh.utils.b.a(this.d)) {
            b();
        } else {
            a();
        }
        AppMethodBeat.o(22437);
    }

    private void a() {
        AppMethodBeat.i(22438, false);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 5);
        bundle.putString("imageLocalUrl", this.d);
        this.b.shareToQQ(this, bundle, this);
        AppMethodBeat.o(22438);
    }

    private void b() {
        AppMethodBeat.i(22439, false);
        Bundle bundle = new Bundle();
        bundle.putInt("req_type", 1);
        bundle.putString("title", this.c.b);
        bundle.putString("summary", this.c.c);
        bundle.putString("targetUrl", this.c.d);
        bundle.putString("imageUrl", this.c.e);
        this.b.shareToQQ(this, bundle, this);
        AppMethodBeat.o(22439);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        AppMethodBeat.i(22441, false);
        super.onActivityResult(i, i2, intent);
        if (this.b != null) {
            Tencent.onActivityResultData(i, i2, intent, this);
        }
        AppMethodBeat.o(22441);
    }

    @Override // com.tencent.tauth.IUiListener
    public void onComplete(Object obj) {
        AppMethodBeat.i(22442, false);
        if (q.a((Activity) this)) {
            a.a("\u5206\u4eab\u6210\u529f");
        }
        finish();
        AppMethodBeat.o(22442);
    }

    @Override // com.tencent.tauth.IUiListener
    public void onError(UiError uiError) {
        AppMethodBeat.i(22443, false);
        if (q.a((Activity) this)) {
            a.a("\u5206\u4eab\u5931\u8d25");
        }
        finish();
        AppMethodBeat.o(22443);
    }

    @Override // com.tencent.tauth.IUiListener
    public void onCancel() {
        AppMethodBeat.i(22445, false);
        if (q.a((Activity) this)) {
            a.a("\u53d6\u6d88\u5206\u4eab");
        }
        finish();
        AppMethodBeat.o(22445);
    }
}
