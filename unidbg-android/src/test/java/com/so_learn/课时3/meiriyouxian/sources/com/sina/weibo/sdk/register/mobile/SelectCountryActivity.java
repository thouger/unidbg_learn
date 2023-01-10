package com.sina.weibo.sdk.register.mobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.sina.weibo.sdk.a.g;
import com.sina.weibo.sdk.component.view.TitleBar;
import com.sina.weibo.sdk.register.mobile.LetterIndexBar;
import com.taobao.accs.common.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectCountryActivity extends Activity implements LetterIndexBar.a {
    String a = "";
    private LetterIndexBar b;
    private List<Country>[] c;
    private List<Country> d;
    private CountryList e;
    private List<b> f = new ArrayList();
    private ListView g;
    private a h;
    private RelativeLayout i;
    private FrameLayout j;

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
    }

    private void a() {
        this.i = new RelativeLayout(this);
        this.i.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        TitleBar titleBar = new TitleBar(this);
        titleBar.setId(1);
        titleBar.setLeftBtnBg(g.a(this, "weibosdk_navigationbar_back.png", "weibosdk_navigationbar_back_highlighted.png"));
        titleBar.setTitleBarText(g.a(this, "Region", "\u9009\u62e9\u56fd\u5bb6", "\u9078\u64c7\u570b\u5bb6"));
        titleBar.setTitleBarClickListener(new AnonymousClass1());
        this.i.addView(titleBar);
        this.j = new FrameLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, titleBar.getId());
        this.j.setLayoutParams(layoutParams);
        this.i.addView(this.j);
        this.g = new ListView(this);
        this.g.setLayoutParams(new AbsListView.LayoutParams(-1, -1));
        this.g.setFadingEdgeLength(0);
        this.g.setSelector(new ColorDrawable(0));
        this.g.setDividerHeight(g.a(this, 1));
        this.g.setCacheColorHint(0);
        this.g.setDrawSelectorOnTop(false);
        this.g.setScrollingCacheEnabled(false);
        this.g.setScrollbarFadingEnabled(false);
        this.g.setVerticalScrollBarEnabled(false);
        this.g.setOnItemClickListener(new AnonymousClass2());
        this.j.addView(this.g);
        this.h = new a(this, null);
        this.g.setAdapter((ListAdapter) this.h);
        this.b = new LetterIndexBar(this);
        this.b.setIndexChangeListener(this);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -1);
        layoutParams2.gravity = 5;
        this.b.setLayoutParams(layoutParams2);
        this.j.addView(this.b);
        a.a(this);
        Locale a2 = g.a();
        if (Locale.SIMPLIFIED_CHINESE.equals(a2)) {
            this.a = g.c(this, "countryCode.txt");
        } else if (Locale.TRADITIONAL_CHINESE.equals(a2)) {
            this.a = g.c(this, "countryCodeTw.txt");
        } else {
            this.a = g.c(this, "countryCodeEn.txt");
        }
        this.e = new CountryList(this.a);
        this.d = this.e.countries;
        this.c = a(this.d);
        this.f = a(this.c);
        this.h.notifyDataSetChanged();
        setContentView(this.i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.register.mobile.SelectCountryActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements TitleBar.a {
        AnonymousClass1() {
        }

        @Override // com.sina.weibo.sdk.component.view.TitleBar.a
        public void a() {
            SelectCountryActivity.this.setResult(0);
            SelectCountryActivity.this.finish();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.register.mobile.SelectCountryActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements AdapterView.OnItemClickListener {
        AnonymousClass2() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Country country = (Country) SelectCountryActivity.this.h.getItem(i);
            if (country != null) {
                Intent intent = new Intent();
                intent.putExtra(Constants.KEY_HTTP_CODE, country.getCode());
                intent.putExtra("name", country.getName());
                SelectCountryActivity.this.setResult(-1, intent);
                SelectCountryActivity.this.finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.sina.weibo.sdk.register.mobile.LetterIndexBar.a
    public void a(int i) {
        List<Country>[] listArr = this.c;
        if (listArr != null && i < listArr.length && listArr[i] != null) {
            this.g.setSelection(this.f.indexOf(new b(i, -1)));
        }
    }

    /* access modifiers changed from: private */
    public class b {
        int a;
        int b;

        b(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public boolean equals(Object obj) {
            int i;
            if (!(obj instanceof b) || (i = this.b) != -1) {
                return false;
            }
            b bVar = (b) obj;
            if (this.a == bVar.a && i == bVar.b) {
                return true;
            }
            return false;
        }
    }

    private List<Country>[] a(List<Country> list) {
        ArrayList[] arrayListArr = new ArrayList[27];
        Country country = new Country();
        country.setCode(Country.CHINA_CODE);
        country.setName(g.a(this, "China", "\u4e2d\u56fd", "\u4e2d\u570b"));
        arrayListArr[0] = new ArrayList();
        arrayListArr[0].add(country);
        for (int i = 0; i < list.size(); i++) {
            Country country2 = list.get(i);
            if (country2.getCode().equals("00852") || country2.getCode().equals("00853") || country2.getCode().equals("00886")) {
                arrayListArr[0].add(country2);
            } else {
                int charAt = (country2.getPinyin().charAt(0) - 'a') + 1;
                if (arrayListArr[charAt] == null) {
                    arrayListArr[charAt] = new ArrayList();
                }
                arrayListArr[charAt].add(country2);
            }
        }
        return arrayListArr;
    }

    private List<b> a(List<Country>[] listArr) {
        ArrayList arrayList = new ArrayList();
        if (listArr != null) {
            for (int i = 0; i < listArr.length; i++) {
                List<Country> list = listArr[i];
                if (list != null && list.size() > 0) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        if (i2 == 0) {
                            arrayList.add(new b(i, -1));
                        }
                        arrayList.add(new b(i, i2));
                    }
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public class a extends BaseAdapter {
        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0;
        }

        private a() {
        }

        /* synthetic */ a(SelectCountryActivity selectCountryActivity, a aVar) {
            this();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (SelectCountryActivity.this.f != null) {
                return SelectCountryActivity.this.f.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (SelectCountryActivity.this.f == null || SelectCountryActivity.this.f.isEmpty() || i == SelectCountryActivity.this.f.size()) {
                return null;
            }
            b bVar = (b) SelectCountryActivity.this.f.get(i);
            if (bVar.b == -1) {
                return null;
            }
            return SelectCountryActivity.this.c[bVar.a].get(bVar.b);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar = (b) SelectCountryActivity.this.f.get(i);
            if (view == null) {
                if (bVar.b == -1) {
                    return a(bVar.a);
                }
                Country country = (Country) SelectCountryActivity.this.c[bVar.a].get(bVar.b);
                view = new SelectCountryItemView(SelectCountryActivity.this, country.getName(), country.getCode());
            } else if (bVar.b != -1) {
                Country country2 = (Country) SelectCountryActivity.this.c[bVar.a].get(bVar.b);
                if (view instanceof SelectCountryTitleView) {
                    view = new SelectCountryItemView(SelectCountryActivity.this, country2.getName(), country2.getCode());
                } else {
                    ((SelectCountryItemView) view).a(country2.getName(), country2.getCode());
                }
            } else if (!(view instanceof SelectCountryTitleView)) {
                view = a(bVar.a);
            } else if (bVar.a == 0) {
                ((SelectCountryTitleView) view).a(g.a(SelectCountryActivity.this, "Common", "\u5e38\u7528", "\u5e38\u7528"));
            } else {
                view = a(bVar.a);
            }
            return view;
        }

        private SelectCountryTitleView a(int i) {
            SelectCountryTitleView selectCountryTitleView = new SelectCountryTitleView(SelectCountryActivity.this.getApplicationContext());
            if (i == 0) {
                selectCountryTitleView.setTitle(g.a(SelectCountryActivity.this, "Common", "\u5e38\u7528", "\u5e38\u7528"));
            } else {
                selectCountryTitleView.setTitle(String.valueOf((char) ((i + 65) - 1)));
            }
            return selectCountryTitleView;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
