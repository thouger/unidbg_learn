package cn.missfresh.module.base.support;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoLinkTextView extends TextView {
    static String a = "(http|https|Http|Https):\\/\\/\\S*";

    public AutoLinkTextView(Context context) {
        super(context);
    }

    public AutoLinkTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AutoLinkTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setTextWithLink(CharSequence charSequence) {
        AppMethodBeat.i(19892, false);
        super.setText(charSequence, TextView.BufferType.NORMAL);
        if (a(this, 1) && getLinksClickable() && !isTextSelectable()) {
            setMovementMethod(LinkMovementMethod.getInstance());
        }
        a();
        AppMethodBeat.o(19892);
    }

    public static final boolean a(TextView textView, int i) {
        AppMethodBeat.i(19894, false);
        if (i == 0) {
            AppMethodBeat.o(19894);
            return false;
        }
        CharSequence text = textView.getText();
        if (!(text instanceof Spannable)) {
            SpannableString valueOf = SpannableString.valueOf(text);
            if (a(valueOf, i)) {
                a(textView);
                textView.setText(valueOf);
                AppMethodBeat.o(19894);
                return true;
            }
            AppMethodBeat.o(19894);
            return false;
        } else if (a((Spannable) text, i)) {
            a(textView);
            AppMethodBeat.o(19894);
            return true;
        } else {
            AppMethodBeat.o(19894);
            return false;
        }
    }

    private static final void a(TextView textView) {
        AppMethodBeat.i(19897, false);
        MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        AppMethodBeat.o(19897);
    }

    /* access modifiers changed from: package-private */
    public static class a {
        String a;
        int b;
        int c;

        a() {
        }
    }

    private static final void a(ArrayList<a> arrayList, Spannable spannable, Pattern pattern, String[] strArr, Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
        AppMethodBeat.i(19900, false);
        Matcher matcher = pattern.matcher(spannable);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (matchFilter == null || matchFilter.acceptMatch(spannable, start, end)) {
                a aVar = new a();
                aVar.a = a(matcher.group(0), strArr, matcher, transformFilter);
                aVar.b = start;
                aVar.c = end;
                arrayList.add(aVar);
            }
        }
        AppMethodBeat.o(19900);
    }

    private static final String a(String str, String[] strArr, Matcher matcher, Linkify.TransformFilter transformFilter) {
        boolean z;
        AppMethodBeat.i(19904, false);
        if (transformFilter != null) {
            str = transformFilter.transformUrl(matcher, str);
        }
        int i = 0;
        while (true) {
            z = true;
            if (i >= strArr.length) {
                z = false;
                break;
            } else if (!str.regionMatches(true, 0, strArr[i], 0, strArr[i].length())) {
                i++;
            } else if (!str.regionMatches(false, 0, strArr[i], 0, strArr[i].length())) {
                str = strArr[i] + str.substring(strArr[i].length());
            }
        }
        if (!z && strArr.length > 0) {
            str = strArr[0] + str;
        }
        AppMethodBeat.o(19904);
        return str;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.AutoLinkTextView$1  reason: invalid class name */
    public static class AnonymousClass1 implements Comparator<a> {
        AnonymousClass1() {
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // java.util.Comparator
        public /* synthetic */ int compare(a aVar, a aVar2) {
            AppMethodBeat.i(19883, false);
            int a = a(aVar, aVar2);
            AppMethodBeat.o(19883);
            return a;
        }

        public final int a(a aVar, a aVar2) {
            if (aVar.b < aVar2.b) {
                return -1;
            }
            if (aVar.b > aVar2.b || aVar.c < aVar2.c) {
                return 1;
            }
            if (aVar.c > aVar2.c) {
                return -1;
            }
            return 0;
        }
    }

    private static final void a(ArrayList<a> arrayList) {
        int i;
        int i2 = 0;
        AppMethodBeat.i(19906, false);
        Collections.sort(arrayList, new AnonymousClass1());
        int size = arrayList.size();
        while (i2 < size - 1) {
            a aVar = arrayList.get(i2);
            int i3 = i2 + 1;
            a aVar2 = arrayList.get(i3);
            if (aVar.b <= aVar2.b && aVar.c > aVar2.b) {
                if (aVar2.c > aVar.c && aVar.c - aVar.b <= aVar2.c - aVar2.b) {
                    i = aVar.c - aVar.b < aVar2.c - aVar2.b ? i2 : -1;
                } else {
                    i = i3;
                }
                if (i != -1) {
                    arrayList.remove(i);
                    size--;
                }
            }
            i2 = i3;
        }
        AppMethodBeat.o(19906);
    }

    private static final void a(String str, int i, int i2, Spannable spannable) {
        AppMethodBeat.i(19909, false);
        spannable.setSpan(new URLSpan(str), i, i2, 33);
        AppMethodBeat.o(19909);
    }

    public static final boolean a(Spannable spannable, int i) {
        AppMethodBeat.i(19914, false);
        if (i == 0) {
            AppMethodBeat.o(19914);
            return false;
        }
        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int length = uRLSpanArr.length - 1; length >= 0; length--) {
            spannable.removeSpan(uRLSpanArr[length]);
        }
        ArrayList arrayList = new ArrayList();
        if ((i & 1) != 0) {
            a(arrayList, spannable, Pattern.compile(a), new String[0], Linkify.sUrlMatchFilter, null);
        }
        a(arrayList);
        if (arrayList.size() == 0) {
            AppMethodBeat.o(19914);
            return false;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            a aVar = (a) it2.next();
            a(aVar.a, aVar.b, aVar.c, spannable);
        }
        AppMethodBeat.o(19914);
        return true;
    }

    @Override // android.widget.TextView
    public void append(CharSequence charSequence, int i, int i2) {
        AppMethodBeat.i(19915, false);
        super.append(charSequence, i, i2);
        a();
        AppMethodBeat.o(19915);
    }

    private void a() {
        AppMethodBeat.i(19919, false);
        CharSequence text = getText();
        if (text instanceof SpannableString) {
            SpannableString spannableString = (SpannableString) text;
            try {
                Field declaredField = spannableString.getClass().getSuperclass().getDeclaredField("mSpans");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(spannableString);
                if (obj.getClass().isArray()) {
                    Object[] objArr = (Object[]) obj;
                    if (objArr.length > 0) {
                        for (int i = 0; i < objArr.length; i++) {
                            Object obj2 = objArr[i];
                            if (obj2 != null && obj2.getClass().equals(URLSpan.class)) {
                                Field declaredField2 = obj2.getClass().getDeclaredField("mURL");
                                declaredField2.setAccessible(true);
                                Object obj3 = declaredField2.get(obj2);
                                Constructor constructor = ExtendUrlSpan.class.getConstructor(String.class);
                                constructor.setAccessible(true);
                                objArr[i] = constructor.newInstance(obj3.toString());
                            }
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (InstantiationException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
        }
        AppMethodBeat.o(19919);
    }
}
