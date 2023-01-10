package com.sobot.chat.widget.kpswitch.view.emoticon;

import android.content.Context;
import android.util.AttributeSet;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.sobot.chat.widget.kpswitch.widget.adpater.PageSetAdapter;
import com.sobot.chat.widget.kpswitch.widget.data.PageSetEntity;
import java.util.Iterator;

public class EmoticonsFuncView extends ViewPager {
    protected PageSetAdapter a;
    protected int b;
    private a c;

    public interface a {
        void a(int i, int i2, PageSetEntity pageSetEntity);

        void a(int i, PageSetEntity pageSetEntity);

        void a(PageSetEntity pageSetEntity);
    }

    public EmoticonsFuncView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setAdapter(PageSetAdapter pageSetAdapter) {
        super.setAdapter((PagerAdapter) pageSetAdapter);
        this.a = pageSetAdapter;
        setOnPageChangeListener(new AnonymousClass1());
        if (this.c != null && !this.a.a().isEmpty()) {
            PageSetEntity pageSetEntity = this.a.a().get(0);
            this.c.a(0, pageSetEntity);
            this.c.a(pageSetEntity);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonsFuncView$1  reason: invalid class name */
    public class AnonymousClass1 implements ViewPager.OnPageChangeListener {
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        AnonymousClass1() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            EmoticonsFuncView.this.a(i);
            EmoticonsFuncView.this.b = i;
        }
    }

    public void setCurrentPageSet(PageSetEntity pageSetEntity) {
        PageSetAdapter pageSetAdapter = this.a;
        if (pageSetAdapter != null && pageSetAdapter.getCount() > 0) {
            setCurrentItem(this.a.a(pageSetEntity));
        }
    }

    public void a(int i) {
        a aVar;
        PageSetAdapter pageSetAdapter = this.a;
        if (pageSetAdapter != null) {
            Iterator<PageSetEntity> it2 = pageSetAdapter.a().iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                PageSetEntity next = it2.next();
                int pageCount = next.getPageCount();
                int i3 = i2 + pageCount;
                if (i3 > i) {
                    boolean z = true;
                    int i4 = this.b;
                    if (i4 - i2 >= pageCount) {
                        a aVar2 = this.c;
                        if (aVar2 != null) {
                            aVar2.a(i - i2, next);
                        }
                    } else if (i4 - i2 < 0) {
                        a aVar3 = this.c;
                        if (aVar3 != null) {
                            aVar3.a(0, next);
                        }
                    } else {
                        a aVar4 = this.c;
                        if (aVar4 != null) {
                            aVar4.a(i4 - i2, i - i2, next);
                        }
                        z = false;
                    }
                    if (z && (aVar = this.c) != null) {
                        aVar.a(next);
                        return;
                    }
                    return;
                }
                i2 = i3;
            }
        }
    }

    public void setOnIndicatorListener(a aVar) {
        this.c = aVar;
    }
}
