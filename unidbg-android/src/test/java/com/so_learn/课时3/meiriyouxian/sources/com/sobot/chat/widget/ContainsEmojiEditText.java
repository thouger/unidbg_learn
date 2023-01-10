package com.sobot.chat.widget;

import android.content.Context;
import android.media.TtmlUtils;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.sobot.chat.api.model.SobotRobotGuess;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.utils.s;
import com.sobot.chat.widget.SobotCustomPopWindow;
import java.util.ArrayList;
import java.util.List;

public class ContainsEmojiEditText extends EditText implements View.OnFocusChangeListener {
    Handler a = new Handler();
    SobotCustomPopWindow b;
    View c;
    c d;
    b e;
    a f;
    String g;
    String h;
    boolean i;
    d j;

    public interface d {
        void c(String str);
    }

    public ContainsEmojiEditText(Context context) {
        super(context);
        b();
    }

    public ContainsEmojiEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public ContainsEmojiEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        this.f = new a(this, null);
        addTextChangedListener(this.f);
        if (s.b(getContext(), "sobot_config_support", false)) {
            setOnFocusChangeListener(this);
            this.e = new b(this, null);
            addTextChangedListener(this.e);
            if (com.sobot.chat.b.a(1)) {
                setOnEditorActionListener(new AnonymousClass1());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.ContainsEmojiEditText$1  reason: invalid class name */
    public class AnonymousClass1 implements TextView.OnEditorActionListener {
        AnonymousClass1() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 6) {
                com.sobot.chat.widget.kpswitch.util.c.b(ContainsEmojiEditText.this);
                ContainsEmojiEditText.this.a(textView.getText().toString());
                return true;
            } else if (i != 0) {
                return false;
            } else {
                com.sobot.chat.widget.kpswitch.util.c.b(ContainsEmojiEditText.this);
                ContainsEmojiEditText.this.a(textView.getText().toString());
                return true;
            }
        }
    }

    public void a(String str) {
        if (this.i) {
            if (TextUtils.isEmpty(str)) {
                a();
                return;
            }
            com.sobot.chat.core.http.a.a().a("SOBOT_AUTO_COMPLETE_REQUEST_CANCEL_TAG");
            com.sobot.chat.core.channel.a.a(getContext()).a().robotGuess("SOBOT_AUTO_COMPLETE_REQUEST_CANCEL_TAG", this.g, this.h, str, new AnonymousClass2());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.ContainsEmojiEditText$2  reason: invalid class name */
    public class AnonymousClass2 implements com.sobot.chat.core.http.c.a<SobotRobotGuess> {
        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        AnonymousClass2() {
        }

        public void a(SobotRobotGuess sobotRobotGuess) {
            try {
                if (ContainsEmojiEditText.this.getText().toString().equals(sobotRobotGuess.getOriginQuestion())) {
                    ContainsEmojiEditText.this.a(ContainsEmojiEditText.this, sobotRobotGuess.getRespInfoList());
                }
            } catch (Exception unused) {
            }
        }
    }

    public void a(String str, String str2) {
        this.g = str;
        this.h = str2;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        if (!z) {
            a();
        }
    }

    /* access modifiers changed from: private */
    public class b implements TextWatcher {
        private b() {
        }

        /* synthetic */ b(ContainsEmojiEditText containsEmojiEditText, AnonymousClass1 r2) {
            this();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            m.c("beforeTextChanged: " + editable.toString());
            if (!com.sobot.chat.b.a(1)) {
                ContainsEmojiEditText.this.a(editable.toString());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            m.c("beforeTextChanged: " + charSequence.toString());
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            m.c("onTextChanged: " + charSequence.toString());
        }
    }

    /* access modifiers changed from: private */
    public class a implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        private a() {
        }

        /* synthetic */ a(ContainsEmojiEditText containsEmojiEditText, AnonymousClass1 r2) {
            this();
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            com.sobot.chat.widget.emoji.c.a(ContainsEmojiEditText.this.getContext(), charSequence);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(View view, List<SobotRobotGuess.RespInfoListBean> list) {
        if (getWindowVisibility() != 8) {
            if (list == null || list.size() == 0) {
                a();
                return;
            }
            View contentView = getContentView();
            ListView b2 = b(contentView, list);
            if (this.b == null) {
                this.b = new SobotCustomPopWindow.PopupWindowBuilder(getContext()).a(contentView).a(false).c(false).b(true).a();
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) b2.getLayoutParams();
            this.b.a(view, 0, -(view.getHeight() + layoutParams.height));
            this.a.post(new AnonymousClass3(view, layoutParams));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.ContainsEmojiEditText$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ LinearLayout.LayoutParams b;

        AnonymousClass3(View view, LinearLayout.LayoutParams layoutParams) {
            this.a = view;
            this.b = layoutParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            PopupWindow b = ContainsEmojiEditText.this.b.b();
            View view = this.a;
            b.update(view, 0, -(view.getHeight() + this.b.height), ContainsEmojiEditText.this.b.b().getWidth(), this.b.height);
        }
    }

    public int b(String str) {
        return q.a(getContext(), "id", str);
    }

    private ListView b(View view, List<SobotRobotGuess.RespInfoListBean> list) {
        ListView listView = (ListView) view.findViewById(b("sobot_lv_menu"));
        a(listView, list);
        listView.setOnItemClickListener(new AnonymousClass4(listView));
        return listView;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.ContainsEmojiEditText$4  reason: invalid class name */
    public class AnonymousClass4 implements AdapterView.OnItemClickListener {
        final /* synthetic */ ListView a;

        AnonymousClass4(ListView listView) {
            this.a = listView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            List<SobotRobotGuess.RespInfoListBean> d;
            ContainsEmojiEditText.this.a();
            if (ContainsEmojiEditText.this.j != null && (d = ((c) this.a.getAdapter()).d()) != null && i < d.size()) {
                ContainsEmojiEditText.this.j.c(d.get(i).getQuestion());
            }
        }
    }

    private void a(ListView listView, List<SobotRobotGuess.RespInfoListBean> list) {
        c cVar = this.d;
        if (cVar == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.clear();
            arrayList.addAll(list);
            this.d = new c(getContext(), arrayList, null);
            listView.setAdapter((ListAdapter) this.d);
        } else {
            List<SobotRobotGuess.RespInfoListBean> d2 = cVar.d();
            if (d2 != null) {
                d2.clear();
                d2.addAll(list);
            }
            this.d.notifyDataSetChanged();
        }
        listView.setSelection(0);
        a(listView);
    }

    private void a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < Math.min(adapter.getCount(), 3); i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            int dividerHeight = i + ((listView.getDividerHeight() * adapter.getCount()) - 1);
            if (adapter.getCount() > 3) {
                dividerHeight += r.a(getContext(), 10.0f);
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) listView.getLayoutParams();
            layoutParams.height = dividerHeight;
            listView.setLayoutParams(layoutParams);
        }
    }

    public void a() {
        SobotCustomPopWindow sobotCustomPopWindow = this.b;
        if (sobotCustomPopWindow != null) {
            try {
                sobotCustomPopWindow.a();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private View getContentView() {
        if (this.c == null) {
            synchronized (ContainsEmojiEditText.class) {
                if (this.c == null) {
                    this.c = LayoutInflater.from(getContext()).inflate(q.a(getContext(), TtmlUtils.TAG_LAYOUT, "sobot_layout_auto_complete"), (ViewGroup) null);
                }
            }
        }
        return this.c;
    }

    /* access modifiers changed from: private */
    public static class c extends com.sobot.chat.adapter.base.a<SobotRobotGuess.RespInfoListBean> {
        /* synthetic */ c(Context context, List list, AnonymousClass1 r3) {
            this(context, list);
        }

        private c(Context context, List<SobotRobotGuess.RespInfoListBean> list) {
            super(context, list);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = View.inflate(this.c, q.a(this.c, TtmlUtils.TAG_LAYOUT, "sobot_item_auto_complete_menu"), null);
                aVar = new a(this.c, view, null);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            SobotRobotGuess.RespInfoListBean respInfoListBean = (SobotRobotGuess.RespInfoListBean) this.b.get(i);
            if (respInfoListBean == null || TextUtils.isEmpty(respInfoListBean.getHighlight())) {
                aVar.a.setText("");
            } else {
                aVar.a.setText(Html.fromHtml(respInfoListBean.getHighlight()));
            }
            return view;
        }

        private static class a {
            private TextView a;

            /* synthetic */ a(Context context, View view, AnonymousClass1 r3) {
                this(context, view);
            }

            private a(Context context, View view) {
                this.a = (TextView) view.findViewById(q.a(context, "id", "sobot_child_menu"));
            }
        }
    }

    public void setAutoCompleteEnable(boolean z) {
        this.i = z;
        if (!this.i) {
            com.sobot.chat.core.http.a.a().a("SOBOT_AUTO_COMPLETE_REQUEST_CANCEL_TAG");
            a();
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDetachedFromWindow() {
        removeTextChangedListener(this.e);
        com.sobot.chat.core.http.a.a().a("SOBOT_AUTO_COMPLETE_REQUEST_CANCEL_TAG");
        a();
        this.j = null;
        this.c = null;
        super.onDetachedFromWindow();
    }

    public void setSobotAutoCompleteListener(d dVar) {
        this.j = dVar;
    }
}
