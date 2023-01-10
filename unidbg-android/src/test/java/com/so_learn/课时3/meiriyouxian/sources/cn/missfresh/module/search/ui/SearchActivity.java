package cn.missfresh.module.search.ui;

import android.app.Activity;
import androidx.core.content.ContextCompat;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.module.mvvm.BaseMVVMActivity;
import cn.missfresh.module.search.R;
import cn.missfresh.module.search.a.c;
import cn.missfresh.module.search.interfaces.ISearchInterceptor;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;

public class SearchActivity extends BaseMVVMActivity {
    private ISearchInterceptor a;

    /* access modifiers changed from: protected */
    public void k() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.search.ui.SearchActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void onWindowFocusChanged(boolean z) {
        SearchActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: cn.missfresh.module.search.ui.SearchActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void f() {
        AppMethodBeat.i(11809, false);
        C();
        this.a = (ISearchInterceptor) a.a().a("/search/interceptor").navigation();
        this.a.a(this, R.id.center_layout, getIntent().getStringExtra("hot_word"), getIntent().getIntExtra("filter_type", 0));
        AppMethodBeat.o(11809);
    }

    /* access modifiers changed from: protected */
    public void g() {
        AppMethodBeat.i(11811, false);
        this.a.b();
        this.a.d(true).g(true).c(true).f(true).e(true).h(true).a(true).b(true).c();
        AppMethodBeat.o(11811);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.search.ui.SearchActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void a() {
        AppMethodBeat.i(11813, false);
        as.a((Activity) this, ContextCompat.getColor(this, R.color.white_f5));
        AppMethodBeat.o(11813);
    }

    /* access modifiers changed from: protected */
    public int M_() {
        return R.layout.search_activity_search;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.search.ui.SearchActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void onBackPressed() {
        AppMethodBeat.i(11816, false);
        c.a();
        r.a((Activity) this);
        SearchActivity.super.onBackPressed();
        AppMethodBeat.o(11816);
    }

    public void finish() {
        AppMethodBeat.i(11817, false);
        SearchActivity.super.finish();
        this.a.a();
        AppMethodBeat.o(11817);
    }
}
