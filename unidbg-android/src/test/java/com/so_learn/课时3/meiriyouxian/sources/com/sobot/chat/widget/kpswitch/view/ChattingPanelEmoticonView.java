package com.sobot.chat.widget.kpswitch.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.sobot.chat.widget.emoji.DisplayEmojiRules;
import com.sobot.chat.widget.emoji.b;
import com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView;
import com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonPageView;
import com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsFuncView;
import com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsIndicatorView;
import com.sobot.chat.widget.kpswitch.widget.a.c;
import com.sobot.chat.widget.kpswitch.widget.adpater.PageSetAdapter;
import com.sobot.chat.widget.kpswitch.widget.adpater.a;
import com.sobot.chat.widget.kpswitch.widget.data.EmoticonPageEntity;
import com.sobot.chat.widget.kpswitch.widget.data.EmoticonPageSetEntity;
import com.sobot.chat.widget.kpswitch.widget.data.PageSetEntity;

public class ChattingPanelEmoticonView extends BaseChattingPanelView implements EmoticonsFuncView.a {
    protected EmoticonsFuncView b;
    protected EmoticonsIndicatorView c;
    a d;
    com.sobot.chat.widget.kpswitch.widget.a.a e = new AnonymousClass3();

    public interface a extends BaseChattingPanelView.a {
        void a(b bVar);

        void z();
    }

    @Override // com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsFuncView.a
    public void a(PageSetEntity pageSetEntity) {
    }

    @Override // com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView
    public String d() {
        return "ChattingPanelEmoticonView";
    }

    public ChattingPanelEmoticonView(Context context) {
        super(context);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView
    public View a() {
        return View.inflate(this.a, b("sobot_emoticon_layout"), null);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView
    public void b() {
        this.b = (EmoticonsFuncView) c().findViewById(a("view_epv"));
        this.c = (EmoticonsIndicatorView) c().findViewById(a("view_eiv"));
        this.b.setOnIndicatorListener(this);
        e();
    }

    @Override // com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView
    public void a(BaseChattingPanelView.a aVar) {
        if (aVar != null && (aVar instanceof a)) {
            this.d = (a) aVar;
        }
    }

    public void e() {
        PageSetAdapter pageSetAdapter = new PageSetAdapter();
        pageSetAdapter.b(new EmoticonPageSetEntity.a().a(e("sobot_emotiocon_line")).b(e("sobot_emotiocon_row")).a(DisplayEmojiRules.getListAll(this.a)).a(new AnonymousClass1()).a(EmoticonPageEntity.DelBtnStatus.LAST).a());
        this.b.setAdapter(pageSetAdapter);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.kpswitch.view.ChattingPanelEmoticonView$1  reason: invalid class name */
    public class AnonymousClass1 implements c<EmoticonPageEntity> {
        AnonymousClass1() {
        }

        public View a(ViewGroup viewGroup, int i, EmoticonPageEntity emoticonPageEntity) {
            if (emoticonPageEntity.e() == null) {
                EmoticonPageView emoticonPageView = new EmoticonPageView(viewGroup.getContext());
                emoticonPageView.setNumColumns(emoticonPageEntity.c());
                emoticonPageEntity.a(emoticonPageView);
                try {
                    com.sobot.chat.widget.kpswitch.widget.adpater.a aVar = new com.sobot.chat.widget.kpswitch.widget.adpater.a(viewGroup.getContext(), emoticonPageEntity, ChattingPanelEmoticonView.this.e);
                    aVar.a(1.8d);
                    aVar.a(ChattingPanelEmoticonView.this.a(ChattingPanelEmoticonView.this.e));
                    emoticonPageView.getEmoticonsGridView().setAdapter((ListAdapter) aVar);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return emoticonPageEntity.e();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.kpswitch.view.ChattingPanelEmoticonView$2  reason: invalid class name */
    public class AnonymousClass2 implements com.sobot.chat.widget.kpswitch.widget.a.b<Object> {
        final /* synthetic */ com.sobot.chat.widget.kpswitch.widget.a.a a;

        AnonymousClass2(com.sobot.chat.widget.kpswitch.widget.a.a aVar) {
            this.a = aVar;
        }

        @Override // com.sobot.chat.widget.kpswitch.widget.a.b
        public void a(int i, ViewGroup viewGroup, a.C0147a aVar, Object obj, boolean z) {
            b bVar = (b) obj;
            if (bVar != null || z) {
                aVar.b.setBackgroundResource(ChattingPanelEmoticonView.this.c("sobot_bg_emoticon"));
                if (z) {
                    aVar.c.setVisibility(0);
                    aVar.d.setVisibility(8);
                    aVar.c.setImageResource(ChattingPanelEmoticonView.this.c("sobot_emoticon_del_selector"));
                } else {
                    aVar.c.setVisibility(8);
                    aVar.d.setVisibility(0);
                    aVar.d.setText(bVar.a());
                }
                aVar.a.setOnClickListener(new AnonymousClass1(bVar, z));
            }
        }

        /* renamed from: com.sobot.chat.widget.kpswitch.view.ChattingPanelEmoticonView$2$1  reason: invalid class name */
        class AnonymousClass1 implements View.OnClickListener {
            final /* synthetic */ b a;
            final /* synthetic */ boolean b;

            AnonymousClass1(b bVar, boolean z) {
                this.a = bVar;
                this.b = z;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AnonymousClass2.this.a != null) {
                    AnonymousClass2.this.a.a(this.a, this.b);
                }
            }
        }
    }

    public com.sobot.chat.widget.kpswitch.widget.a.b<Object> a(com.sobot.chat.widget.kpswitch.widget.a.a aVar) {
        return new AnonymousClass2(aVar);
    }

    /* renamed from: com.sobot.chat.widget.kpswitch.view.ChattingPanelEmoticonView$3  reason: invalid class name */
    class AnonymousClass3 implements com.sobot.chat.widget.kpswitch.widget.a.a {
        AnonymousClass3() {
        }

        @Override // com.sobot.chat.widget.kpswitch.widget.a.a
        public void a(Object obj, boolean z) {
            if (ChattingPanelEmoticonView.this.d == null) {
                return;
            }
            if (z) {
                ChattingPanelEmoticonView.this.d.z();
            } else {
                ChattingPanelEmoticonView.this.d.a((b) obj);
            }
        }
    }

    @Override // com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsFuncView.a
    public void a(int i, PageSetEntity pageSetEntity) {
        this.c.a(i, pageSetEntity);
    }

    @Override // com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsFuncView.a
    public void a(int i, int i2, PageSetEntity pageSetEntity) {
        this.c.a(i, i2, pageSetEntity);
    }
}
