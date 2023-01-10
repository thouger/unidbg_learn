package cn.missfresh.module.base.widget.tag;

import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.base.widget.FitWidthImageView;
import cn.missfresh.module.base.widget.tag.bean.ProductTagInfoBean;
import cn.missfresh.module.base.widget.tag.bean.TagInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import cn.missfresh.utils.f;

/* compiled from: TagInfoViewUtil */
public class a {
    private static void a(TextView textView, ImageView imageView) {
        AppMethodBeat.i(24338, false);
        textView.setVisibility(8);
        imageView.setVisibility(8);
        AppMethodBeat.o(24338);
    }

    public static void a(TextView textView, ImageView imageView, TagInfo tagInfo) {
        AppMethodBeat.i(24339, false);
        a(textView, imageView, tagInfo, false);
        AppMethodBeat.o(24339);
    }

    public static void a(TextView textView, ImageView imageView, TagInfo tagInfo, boolean z) {
        AppMethodBeat.i(24340, false);
        if (tagInfo == null) {
            a(textView, imageView);
            AppMethodBeat.o(24340);
            return;
        }
        if (tagInfo.getUseType() == 1) {
            imageView.setVisibility(8);
            a(tagInfo, textView, z);
        } else if (tagInfo.getUseType() == 2) {
            textView.setVisibility(8);
            if (b.a(tagInfo.getTagIcon())) {
                imageView.setVisibility(8);
                AppMethodBeat.o(24340);
                return;
            }
            imageView.setVisibility(0);
            if (imageView instanceof FitWidthImageView) {
                ((FitWidthImageView) imageView).a(tagInfo.getTagIcon());
            } else {
                c.a(imageView).a(tagInfo.getTagIcon()).a(imageView);
            }
        } else {
            textView.setVisibility(8);
            imageView.setVisibility(8);
        }
        AppMethodBeat.o(24340);
    }

    public static void a(TagInfo tagInfo, TextView textView) {
        AppMethodBeat.i(24341, false);
        a(tagInfo, textView, false);
        AppMethodBeat.o(24341);
    }

    public static void a(TagInfo tagInfo, TextView textView, boolean z) {
        AppMethodBeat.i(24342, false);
        if (textView == null) {
            AppMethodBeat.o(24342);
        } else if (tagInfo == null || b.a(tagInfo.getTagName())) {
            textView.setVisibility(8);
            AppMethodBeat.o(24342);
        } else {
            textView.setVisibility(0);
            textView.setText(tagInfo.getTagName());
            textView.setTextColor(q.a(tagInfo.getTextColor()));
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius((float) f.c(textView.getContext(), 6));
            gradientDrawable.setColor(q.a(tagInfo.getBgColor()));
            gradientDrawable.setStroke(2, q.a(tagInfo.getBorderColor()));
            textView.setBackground(gradientDrawable);
            textView.post(new AnonymousClass1(textView, z));
            AppMethodBeat.o(24342);
        }
    }

    /* compiled from: TagInfoViewUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.tag.a$1  reason: invalid class name */
    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ TextView a;
        final /* synthetic */ boolean b;

        AnonymousClass1(TextView textView, boolean z) {
            this.a = textView;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(24336, false);
            TextView textView = this.a;
            if (!(textView == null || textView.getLayout() == null)) {
                if (this.a.getLayout().getEllipsisCount(this.a.getLineCount() - 1) <= 0 || !this.b) {
                    this.a.setVisibility(0);
                } else {
                    this.a.setVisibility(8);
                }
            }
            AppMethodBeat.o(24336);
        }
    }

    public static void a(TagInfo tagInfo, View view, ImageView imageView, TextView textView, boolean z) {
        AppMethodBeat.i(24343, false);
        if (textView == null) {
            AppMethodBeat.o(24343);
        } else if (tagInfo == null || b.a(tagInfo.getTagName())) {
            view.setVisibility(8);
            AppMethodBeat.o(24343);
        } else {
            view.setVisibility(0);
            textView.setText(tagInfo.getTagName());
            textView.setTextColor(q.a(tagInfo.getTextColor()));
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius((float) f.c(textView.getContext(), 6));
            gradientDrawable.setColor(q.a(tagInfo.getBgColor()));
            gradientDrawable.setStroke(2, q.a(tagInfo.getBorderColor()));
            view.setBackground(gradientDrawable);
            textView.post(new AnonymousClass2(textView, z));
            if (b.a(tagInfo.getTagIcon())) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                c.a(view).a(tagInfo.getTagIcon()).a(imageView);
            }
            AppMethodBeat.o(24343);
        }
    }

    /* compiled from: TagInfoViewUtil */
    /* renamed from: cn.missfresh.module.base.widget.tag.a$2  reason: invalid class name */
    static class AnonymousClass2 implements Runnable {
        final /* synthetic */ TextView a;
        final /* synthetic */ boolean b;

        AnonymousClass2(TextView textView, boolean z) {
            this.a = textView;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(24337, false);
            TextView textView = this.a;
            if (!(textView == null || textView.getLayout() == null)) {
                if (this.a.getLayout().getEllipsisCount(this.a.getLineCount() - 1) <= 0 || !this.b) {
                    this.a.setVisibility(0);
                } else {
                    this.a.setVisibility(8);
                }
            }
            AppMethodBeat.o(24337);
        }
    }

    public static void a(String str, TagInfo tagInfo, TextView textView) {
        AppMethodBeat.i(24344, false);
        if (textView == null) {
            AppMethodBeat.o(24344);
        } else if (tagInfo == null || b.a(tagInfo.getTagName())) {
            textView.setText(str);
            AppMethodBeat.o(24344);
        } else {
            Rect rect = new Rect();
            textView.getPaint().getTextBounds(str, 0, str.length(), rect);
            textView.setText(tagInfo.getTagName());
            SpannableString spannableString = new SpannableString(tagInfo.getTagName() + str);
            spannableString.setSpan(new cn.missfresh.module.base.span.b(q.a(tagInfo.getBgColor()), rect.height(), q.a(tagInfo.getTextColor()), q.a(tagInfo.getBorderColor()), f.b(textView.getContext(), 10), f.c(textView.getContext(), 4), 0, f.c(textView.getContext(), 2)), 0, tagInfo.getTagName().length(), 33);
            textView.setText(spannableString, TextView.BufferType.SPANNABLE);
            AppMethodBeat.o(24344);
        }
    }

    public static boolean a(ProductTagInfoBean productTagInfoBean, MissfreshTagView missfreshTagView, ClassifyPriceLineTag classifyPriceLineTag, TextView textView, String str, boolean z) {
        AppMethodBeat.i(24345, false);
        if (productTagInfoBean != null) {
            missfreshTagView.setVisibility(0);
            missfreshTagView.setData(productTagInfoBean);
            classifyPriceLineTag.a(productTagInfoBean.getPriceTop(), f.c(missfreshTagView.getContext(), 14), f.b(missfreshTagView.getContext(), 10), z);
            a(str, productTagInfoBean.getTitleLeft(), textView);
            AppMethodBeat.o(24345);
            return true;
        }
        missfreshTagView.setVisibility(8);
        classifyPriceLineTag.setVisibility(8);
        AppMethodBeat.o(24345);
        return false;
    }

    public static boolean a(ProductTagInfoBean productTagInfoBean, MissfreshTagView missfreshTagView, MissfreshPriceLineTag missfreshPriceLineTag, TextView textView, String str, boolean z) {
        AppMethodBeat.i(24346, false);
        if (productTagInfoBean != null) {
            missfreshTagView.setVisibility(0);
            missfreshTagView.setData(productTagInfoBean);
            missfreshPriceLineTag.a(productTagInfoBean.getPriceTop(), f.c(missfreshTagView.getContext(), 14), f.b(missfreshTagView.getContext(), 10), z);
            a(str, productTagInfoBean.getTitleLeft(), textView);
            AppMethodBeat.o(24346);
            return true;
        }
        missfreshTagView.setVisibility(8);
        missfreshPriceLineTag.setVisibility(8);
        AppMethodBeat.o(24346);
        return false;
    }

    public static void a(int i, View... viewArr) {
        AppMethodBeat.i(24347, false);
        if (viewArr == null || viewArr.length == 0) {
            AppMethodBeat.o(24347);
            return;
        }
        for (View view : viewArr) {
            view.setVisibility(i);
        }
        AppMethodBeat.o(24347);
    }
}
