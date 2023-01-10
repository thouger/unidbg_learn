package com.google.android.flexbox;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.widget.CompoundButtonCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: FlexboxHelper */
/* access modifiers changed from: package-private */
public class c {
    static final /* synthetic */ boolean c = (!c.class.desiredAssertionStatus());
    int[] a;
    long[] b;
    private final a d;
    private boolean[] e;
    private long[] f;

    /* access modifiers changed from: package-private */
    public int a(long j) {
        return (int) j;
    }

    /* access modifiers changed from: package-private */
    public int b(long j) {
        return (int) (j >> 32);
    }

    /* access modifiers changed from: package-private */
    public long b(int i, int i2) {
        return (((long) i) & 4294967295L) | (((long) i2) << 32);
    }

    c(a aVar) {
        this.d = aVar;
    }

    /* access modifiers changed from: package-private */
    public int[] a(View view, int i, ViewGroup.LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.d.getFlexItemCount();
        List<b> e = e(flexItemCount);
        b bVar = new b();
        if (view == null || !(layoutParams instanceof FlexItem)) {
            bVar.b = 1;
        } else {
            bVar.b = ((FlexItem) layoutParams).c();
        }
        if (i == -1 || i == flexItemCount) {
            bVar.a = flexItemCount;
        } else if (i < this.d.getFlexItemCount()) {
            bVar.a = i;
            while (i < flexItemCount) {
                e.get(i).a++;
                i++;
            }
        } else {
            bVar.a = flexItemCount;
        }
        e.add(bVar);
        return a(flexItemCount + 1, e, sparseIntArray);
    }

    /* access modifiers changed from: package-private */
    public int[] a(SparseIntArray sparseIntArray) {
        int flexItemCount = this.d.getFlexItemCount();
        return a(flexItemCount, e(flexItemCount), sparseIntArray);
    }

    private List<b> e(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            b bVar = new b();
            bVar.b = ((FlexItem) this.d.a(i2).getLayoutParams()).c();
            bVar.a = i2;
            arrayList.add(bVar);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public boolean b(SparseIntArray sparseIntArray) {
        int flexItemCount = this.d.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        for (int i = 0; i < flexItemCount; i++) {
            View a2 = this.d.a(i);
            if (!(a2 == null || ((FlexItem) a2.getLayoutParams()).c() == sparseIntArray.get(i))) {
                return true;
            }
        }
        return false;
    }

    private int[] a(int i, List<b> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (b bVar : list) {
            iArr[i2] = bVar.a;
            sparseIntArray.append(bVar.a, bVar.b);
            i2++;
        }
        return iArr;
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar, int i, int i2) {
        a(aVar, i, i2, Integer.MAX_VALUE, 0, -1, (List<b>) null);
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar, int i, int i2, int i3, int i4, List<b> list) {
        a(aVar, i, i2, i3, i4, -1, list);
    }

    /* access modifiers changed from: package-private */
    public void b(a aVar, int i, int i2, int i3, int i4, List<b> list) {
        a(aVar, i, i2, i3, 0, i4, list);
    }

    /* access modifiers changed from: package-private */
    public void b(a aVar, int i, int i2) {
        a(aVar, i2, i, Integer.MAX_VALUE, 0, -1, (List<b>) null);
    }

    /* access modifiers changed from: package-private */
    public void c(a aVar, int i, int i2, int i3, int i4, List<b> list) {
        a(aVar, i2, i, i3, i4, -1, list);
    }

    /* access modifiers changed from: package-private */
    public void d(a aVar, int i, int i2, int i3, int i4, List<b> list) {
        a(aVar, i2, i, i3, 0, i4, list);
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar, int i, int i2, int i3, int i4, int i5, List<b> list) {
        int i6;
        a aVar2;
        int i7;
        int i8;
        List<b> list2;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        View view;
        int i14;
        int i15;
        int i16;
        int i17 = i;
        int i18 = i2;
        int i19 = i5;
        boolean a2 = this.d.a();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        ArrayList arrayList = list == null ? new ArrayList() : list;
        aVar.a = arrayList;
        boolean z = i19 == -1;
        int a3 = a(a2);
        int b2 = b(a2);
        int c2 = c(a2);
        int d = d(a2);
        b bVar = new b();
        int i20 = i4;
        bVar.o = i20;
        int i21 = b2 + a3;
        bVar.e = i21;
        int flexItemCount = this.d.getFlexItemCount();
        boolean z2 = z;
        int i22 = Integer.MIN_VALUE;
        int i23 = 0;
        int i24 = 0;
        int i25 = 0;
        while (true) {
            if (i20 >= flexItemCount) {
                i6 = i24;
                aVar2 = aVar;
                break;
            }
            View b3 = this.d.b(i20);
            if (b3 != null) {
                if (b3.getVisibility() != 8) {
                    if (b3 instanceof CompoundButton) {
                        a((CompoundButton) b3);
                    }
                    FlexItem flexItem = (FlexItem) b3.getLayoutParams();
                    if (flexItem.f() == 4) {
                        bVar.n.add(Integer.valueOf(i20));
                    }
                    int a4 = a(flexItem, a2);
                    if (flexItem.l() != -1.0f && mode == 1073741824) {
                        a4 = Math.round(((float) size) * flexItem.l());
                    }
                    if (a2) {
                        int a5 = this.d.a(i17, i21 + c(flexItem, true) + d(flexItem, true), a4);
                        i8 = size;
                        i7 = mode;
                        int b4 = this.d.b(i18, c2 + d + e(flexItem, true) + f(flexItem, true) + i23, b(flexItem, true));
                        b3.measure(a5, b4);
                        a(i20, a5, b4, b3);
                        i13 = a5;
                    } else {
                        i8 = size;
                        i7 = mode;
                        int a6 = this.d.a(i18, c2 + d + e(flexItem, false) + f(flexItem, false) + i23, b(flexItem, false));
                        int b5 = this.d.b(i17, c(flexItem, false) + i21 + d(flexItem, false), a4);
                        b3.measure(a6, b5);
                        a(i20, a6, b5, b3);
                        i13 = b5;
                    }
                    this.d.a(i20, b3);
                    a(b3, i20);
                    i24 = View.combineMeasuredStates(i24, b3.getMeasuredState());
                    int i26 = i23;
                    list2 = arrayList;
                    if (a(b3, i7, i8, bVar.e, d(flexItem, a2) + a(b3, a2) + c(flexItem, a2), flexItem, i20, i25, arrayList.size())) {
                        if (bVar.c() > 0) {
                            a(list2, bVar, i20 > 0 ? i20 - 1 : 0, i26);
                            i16 = bVar.g + i26;
                        } else {
                            i16 = i26;
                        }
                        if (!a2) {
                            i11 = i2;
                            view = b3;
                            i20 = i20;
                            if (flexItem.a() == -1) {
                                a aVar3 = this.d;
                                view.measure(aVar3.a(i11, aVar3.getPaddingLeft() + this.d.getPaddingRight() + flexItem.m() + flexItem.o() + i16, flexItem.a()), i13);
                                a(view, i20);
                            }
                        } else if (flexItem.b() == -1) {
                            a aVar4 = this.d;
                            i11 = i2;
                            i20 = i20;
                            view = b3;
                            view.measure(i13, aVar4.b(i11, aVar4.getPaddingTop() + this.d.getPaddingBottom() + flexItem.n() + flexItem.p() + i16, flexItem.b()));
                            a(view, i20);
                        } else {
                            i11 = i2;
                            view = b3;
                            i20 = i20;
                        }
                        bVar = new b();
                        bVar.h = 1;
                        i10 = i21;
                        bVar.e = i10;
                        bVar.o = i20;
                        i26 = i16;
                        i14 = Integer.MIN_VALUE;
                        i15 = 0;
                    } else {
                        i11 = i2;
                        view = b3;
                        i20 = i20;
                        bVar = bVar;
                        i10 = i21;
                        bVar.h++;
                        i15 = i25 + 1;
                        i14 = i22;
                    }
                    bVar.q |= flexItem.d() != 0.0f;
                    bVar.r |= flexItem.e() != 0.0f;
                    int[] iArr = this.a;
                    if (iArr != null) {
                        iArr[i20] = list2.size();
                    }
                    bVar.e += a(view, a2) + c(flexItem, a2) + d(flexItem, a2);
                    bVar.j += flexItem.d();
                    bVar.k += flexItem.e();
                    this.d.a(view, i20, i15, bVar);
                    int max = Math.max(i14, b(view, a2) + e(flexItem, a2) + f(flexItem, a2) + this.d.a(view));
                    bVar.g = Math.max(bVar.g, max);
                    if (a2) {
                        if (this.d.getFlexWrap() != 2) {
                            bVar.l = Math.max(bVar.l, view.getBaseline() + flexItem.n());
                        } else {
                            bVar.l = Math.max(bVar.l, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.p());
                        }
                    }
                    i12 = flexItemCount;
                    if (a(i20, i12, bVar)) {
                        a(list2, bVar, i20, i26);
                        i26 += bVar.g;
                    }
                    i9 = i5;
                    if (i9 != -1 && list2.size() > 0) {
                        if (list2.get(list2.size() - 1).p >= i9 && i20 >= i9 && !z2) {
                            i26 = -bVar.a();
                            z2 = true;
                        }
                    }
                    if (i26 > i3 && z2) {
                        aVar2 = aVar;
                        i6 = i24;
                        break;
                    }
                    i25 = i15;
                    i22 = max;
                    i23 = i26;
                    i20++;
                    i17 = i;
                    flexItemCount = i12;
                    i18 = i11;
                    i21 = i10;
                    arrayList = list2;
                    mode = i7;
                    i19 = i9;
                    size = i8;
                } else {
                    bVar.i++;
                    bVar.h++;
                    if (a(i20, flexItemCount, bVar)) {
                        a(arrayList, bVar, i20, i23);
                    }
                }
            } else if (a(i20, flexItemCount, bVar)) {
                a(arrayList, bVar, i20, i23);
            }
            i8 = size;
            i7 = mode;
            i11 = i18;
            i9 = i19;
            list2 = arrayList;
            i10 = i21;
            i12 = flexItemCount;
            i20++;
            i17 = i;
            flexItemCount = i12;
            i18 = i11;
            i21 = i10;
            arrayList = list2;
            mode = i7;
            i19 = i9;
            size = i8;
        }
        aVar2.b = i6;
    }

    private void a(CompoundButton compoundButton) {
        int i;
        int i2;
        FlexItem flexItem = (FlexItem) compoundButton.getLayoutParams();
        int g = flexItem.g();
        int h = flexItem.h();
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(compoundButton);
        if (buttonDrawable == null) {
            i = 0;
        } else {
            i = buttonDrawable.getMinimumWidth();
        }
        if (buttonDrawable == null) {
            i2 = 0;
        } else {
            i2 = buttonDrawable.getMinimumHeight();
        }
        if (g == -1) {
            g = i;
        }
        flexItem.a(g);
        if (h != -1) {
            i2 = h;
        }
        flexItem.b(i2);
    }

    private int a(boolean z) {
        if (z) {
            return this.d.getPaddingStart();
        }
        return this.d.getPaddingTop();
    }

    private int b(boolean z) {
        if (z) {
            return this.d.getPaddingEnd();
        }
        return this.d.getPaddingBottom();
    }

    private int c(boolean z) {
        if (z) {
            return this.d.getPaddingTop();
        }
        return this.d.getPaddingStart();
    }

    private int d(boolean z) {
        if (z) {
            return this.d.getPaddingBottom();
        }
        return this.d.getPaddingEnd();
    }

    private int a(View view, boolean z) {
        if (z) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    private int b(View view, boolean z) {
        if (z) {
            return view.getMeasuredHeight();
        }
        return view.getMeasuredWidth();
    }

    private int a(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.a();
        }
        return flexItem.b();
    }

    private int b(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.b();
        }
        return flexItem.a();
    }

    private int c(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.m();
        }
        return flexItem.n();
    }

    private int d(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.o();
        }
        return flexItem.p();
    }

    private int e(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.n();
        }
        return flexItem.m();
    }

    private int f(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.p();
        }
        return flexItem.o();
    }

    private boolean a(View view, int i, int i2, int i3, int i4, FlexItem flexItem, int i5, int i6, int i7) {
        if (this.d.getFlexWrap() == 0) {
            return false;
        }
        if (flexItem.k()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int maxLine = this.d.getMaxLine();
        if (maxLine != -1 && maxLine <= i7 + 1) {
            return false;
        }
        int a2 = this.d.a(view, i5, i6);
        if (a2 > 0) {
            i4 += a2;
        }
        if (i2 < i3 + i4) {
            return true;
        }
        return false;
    }

    private boolean a(int i, int i2, b bVar) {
        return i == i2 - 1 && bVar.c() != 0;
    }

    private void a(List<b> list, b bVar, int i, int i2) {
        bVar.m = i2;
        this.d.a(bVar);
        bVar.p = i;
        list.add(bVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.g()
            r4 = 1
            if (r1 >= r3) goto L_0x001b
            int r1 = r0.g()
        L_0x0019:
            r3 = r4
            goto L_0x0027
        L_0x001b:
            int r3 = r0.i()
            if (r1 <= r3) goto L_0x0026
            int r1 = r0.i()
            goto L_0x0019
        L_0x0026:
            r3 = 0
        L_0x0027:
            int r5 = r0.h()
            if (r2 >= r5) goto L_0x0032
            int r2 = r0.h()
            goto L_0x003e
        L_0x0032:
            int r5 = r0.j()
            if (r2 <= r5) goto L_0x003d
            int r2 = r0.j()
            goto L_0x003e
        L_0x003d:
            r4 = r3
        L_0x003e:
            if (r4 == 0) goto L_0x0055
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.a(r8, r1, r0, r7)
            com.google.android.flexbox.a r0 = r6.d
            r0.a(r8, r7)
        L_0x0055:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.c.a(android.view.View, int):void");
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2) {
        a(i, i2, 0);
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        f(this.d.getFlexItemCount());
        if (i3 < this.d.getFlexItemCount()) {
            int flexDirection = this.d.getFlexDirection();
            int flexDirection2 = this.d.getFlexDirection();
            if (flexDirection2 == 0 || flexDirection2 == 1) {
                int mode = View.MeasureSpec.getMode(i);
                i5 = View.MeasureSpec.getSize(i);
                int largestMainSize = this.d.getLargestMainSize();
                if (mode != 1073741824 && largestMainSize <= i5) {
                    i5 = largestMainSize;
                }
                i6 = this.d.getPaddingLeft();
                i4 = this.d.getPaddingRight();
            } else if (flexDirection2 == 2 || flexDirection2 == 3) {
                int mode2 = View.MeasureSpec.getMode(i2);
                i5 = View.MeasureSpec.getSize(i2);
                if (mode2 != 1073741824) {
                    i5 = this.d.getLargestMainSize();
                }
                i6 = this.d.getPaddingTop();
                i4 = this.d.getPaddingBottom();
            } else {
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
            }
            int i7 = i6 + i4;
            int i8 = 0;
            int[] iArr = this.a;
            if (iArr != null) {
                i8 = iArr[i3];
            }
            List<b> flexLinesInternal = this.d.getFlexLinesInternal();
            int size = flexLinesInternal.size();
            for (int i9 = i8; i9 < size; i9++) {
                b bVar = flexLinesInternal.get(i9);
                if (bVar.e < i5 && bVar.q) {
                    a(i, i2, bVar, i5, i7, false);
                } else if (bVar.e > i5 && bVar.r) {
                    b(i, i2, bVar, i5, i7, false);
                }
            }
        }
    }

    private void f(int i) {
        boolean[] zArr = this.e;
        if (zArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.e = new boolean[i];
        } else if (zArr.length < i) {
            int length = zArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.e = new boolean[i];
        } else {
            Arrays.fill(zArr, false);
        }
    }

    private void a(int i, int i2, b bVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8;
        double d;
        int i9;
        double d2;
        float f = 0.0f;
        if (bVar.j > 0.0f && i3 >= bVar.e) {
            int i10 = bVar.e;
            float f2 = ((float) (i3 - bVar.e)) / bVar.j;
            bVar.e = i4 + bVar.f;
            if (!z) {
                bVar.g = Integer.MIN_VALUE;
            }
            int i11 = 0;
            float f3 = 0.0f;
            boolean z2 = false;
            int i12 = 0;
            while (i11 < bVar.h) {
                int i13 = bVar.o + i11;
                View b2 = this.d.b(i13);
                if (b2 == null || b2.getVisibility() == 8) {
                    i5 = i10;
                } else {
                    FlexItem flexItem = (FlexItem) b2.getLayoutParams();
                    int flexDirection = this.d.getFlexDirection();
                    if (flexDirection == 0 || flexDirection == 1) {
                        int measuredWidth = b2.getMeasuredWidth();
                        long[] jArr = this.f;
                        if (jArr != null) {
                            measuredWidth = a(jArr[i13]);
                        }
                        int measuredHeight = b2.getMeasuredHeight();
                        long[] jArr2 = this.f;
                        i5 = i10;
                        if (jArr2 != null) {
                            measuredHeight = b(jArr2[i13]);
                        }
                        if (this.e[i13] || flexItem.d() <= 0.0f) {
                            i8 = measuredWidth;
                            i7 = measuredHeight;
                        } else {
                            float d3 = ((float) measuredWidth) + (flexItem.d() * f2);
                            if (i11 == bVar.h - 1) {
                                d3 += f3;
                                f3 = 0.0f;
                            }
                            int round = Math.round(d3);
                            if (round > flexItem.i()) {
                                round = flexItem.i();
                                this.e[i13] = true;
                                bVar.j -= flexItem.d();
                                z2 = true;
                            } else {
                                f3 += d3 - ((float) round);
                                double d4 = (double) f3;
                                if (d4 > 1.0d) {
                                    round++;
                                    d = d4 - 1.0d;
                                } else if (d4 < -1.0d) {
                                    round--;
                                    d = d4 + 1.0d;
                                }
                                f3 = (float) d;
                            }
                            int b3 = b(i2, flexItem, bVar.m);
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                            b2.measure(makeMeasureSpec, b3);
                            i8 = b2.getMeasuredWidth();
                            i7 = b2.getMeasuredHeight();
                            a(i13, makeMeasureSpec, b3, b2);
                            this.d.a(i13, b2);
                        }
                        i6 = Math.max(i12, i7 + flexItem.n() + flexItem.p() + this.d.a(b2));
                        bVar.e += i8 + flexItem.m() + flexItem.o();
                    } else {
                        int measuredHeight2 = b2.getMeasuredHeight();
                        long[] jArr3 = this.f;
                        if (jArr3 != null) {
                            measuredHeight2 = b(jArr3[i13]);
                        }
                        int measuredWidth2 = b2.getMeasuredWidth();
                        long[] jArr4 = this.f;
                        if (jArr4 != null) {
                            measuredWidth2 = a(jArr4[i13]);
                        }
                        if (this.e[i13] || flexItem.d() <= f) {
                            i9 = i10;
                        } else {
                            float d5 = ((float) measuredHeight2) + (flexItem.d() * f2);
                            if (i11 == bVar.h - 1) {
                                d5 += f3;
                                f3 = f;
                            }
                            int round2 = Math.round(d5);
                            if (round2 > flexItem.j()) {
                                round2 = flexItem.j();
                                this.e[i13] = true;
                                bVar.j -= flexItem.d();
                                i9 = i10;
                                z2 = true;
                            } else {
                                f3 += d5 - ((float) round2);
                                i9 = i10;
                                double d6 = (double) f3;
                                if (d6 > 1.0d) {
                                    round2++;
                                    d2 = d6 - 1.0d;
                                } else if (d6 < -1.0d) {
                                    round2--;
                                    d2 = d6 + 1.0d;
                                }
                                f3 = (float) d2;
                            }
                            int a2 = a(i, flexItem, bVar.m);
                            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                            b2.measure(a2, makeMeasureSpec2);
                            measuredWidth2 = b2.getMeasuredWidth();
                            int measuredHeight3 = b2.getMeasuredHeight();
                            a(i13, a2, makeMeasureSpec2, b2);
                            this.d.a(i13, b2);
                            measuredHeight2 = measuredHeight3;
                        }
                        i6 = Math.max(i12, measuredWidth2 + flexItem.m() + flexItem.o() + this.d.a(b2));
                        bVar.e += measuredHeight2 + flexItem.n() + flexItem.p();
                        i5 = i9;
                    }
                    bVar.g = Math.max(bVar.g, i6);
                    i12 = i6;
                }
                i11++;
                i10 = i5;
                f = 0.0f;
            }
            if (z2 && i10 != bVar.e) {
                a(i, i2, bVar, i3, i4, true);
            }
        }
    }

    private void b(int i, int i2, b bVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8 = bVar.e;
        float f = 0.0f;
        if (bVar.k > 0.0f && i3 <= bVar.e) {
            float f2 = ((float) (bVar.e - i3)) / bVar.k;
            bVar.e = i4 + bVar.f;
            if (!z) {
                bVar.g = Integer.MIN_VALUE;
            }
            int i9 = 0;
            float f3 = 0.0f;
            boolean z2 = false;
            int i10 = 0;
            while (i9 < bVar.h) {
                int i11 = bVar.o + i9;
                View b2 = this.d.b(i11);
                if (b2 == null || b2.getVisibility() == 8) {
                    i5 = i9;
                } else {
                    FlexItem flexItem = (FlexItem) b2.getLayoutParams();
                    int flexDirection = this.d.getFlexDirection();
                    if (flexDirection == 0 || flexDirection == 1) {
                        i5 = i9;
                        int measuredWidth = b2.getMeasuredWidth();
                        long[] jArr = this.f;
                        if (jArr != null) {
                            measuredWidth = a(jArr[i11]);
                        }
                        int measuredHeight = b2.getMeasuredHeight();
                        long[] jArr2 = this.f;
                        if (jArr2 != null) {
                            measuredHeight = b(jArr2[i11]);
                        }
                        if (this.e[i11] || flexItem.e() <= 0.0f) {
                            i7 = measuredWidth;
                        } else {
                            float e = ((float) measuredWidth) - (flexItem.e() * f2);
                            if (i5 == bVar.h - 1) {
                                e += f3;
                                f3 = 0.0f;
                            }
                            int round = Math.round(e);
                            if (round < flexItem.g()) {
                                round = flexItem.g();
                                this.e[i11] = true;
                                bVar.k -= flexItem.e();
                                z2 = true;
                            } else {
                                f3 += e - ((float) round);
                                double d = (double) f3;
                                if (d > 1.0d) {
                                    round++;
                                    f3 -= 1.0f;
                                } else if (d < -1.0d) {
                                    round--;
                                    f3 += 1.0f;
                                }
                            }
                            int b3 = b(i2, flexItem, bVar.m);
                            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                            b2.measure(makeMeasureSpec, b3);
                            i7 = b2.getMeasuredWidth();
                            int measuredHeight2 = b2.getMeasuredHeight();
                            a(i11, makeMeasureSpec, b3, b2);
                            this.d.a(i11, b2);
                            measuredHeight = measuredHeight2;
                        }
                        i6 = Math.max(i10, measuredHeight + flexItem.n() + flexItem.p() + this.d.a(b2));
                        bVar.e += i7 + flexItem.m() + flexItem.o();
                    } else {
                        int measuredHeight3 = b2.getMeasuredHeight();
                        long[] jArr3 = this.f;
                        if (jArr3 != null) {
                            measuredHeight3 = b(jArr3[i11]);
                        }
                        int measuredWidth2 = b2.getMeasuredWidth();
                        long[] jArr4 = this.f;
                        if (jArr4 != null) {
                            measuredWidth2 = a(jArr4[i11]);
                        }
                        if (this.e[i11] || flexItem.e() <= f) {
                            i5 = i9;
                        } else {
                            float e2 = ((float) measuredHeight3) - (flexItem.e() * f2);
                            if (i9 == bVar.h - 1) {
                                e2 += f3;
                                f3 = f;
                            }
                            int round2 = Math.round(e2);
                            if (round2 < flexItem.h()) {
                                round2 = flexItem.h();
                                this.e[i11] = true;
                                bVar.k -= flexItem.e();
                                i5 = i9;
                                z2 = true;
                            } else {
                                f3 += e2 - ((float) round2);
                                i5 = i9;
                                double d2 = (double) f3;
                                if (d2 > 1.0d) {
                                    round2++;
                                    f3 -= 1.0f;
                                } else if (d2 < -1.0d) {
                                    round2--;
                                    f3 += 1.0f;
                                }
                            }
                            int a2 = a(i, flexItem, bVar.m);
                            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                            b2.measure(a2, makeMeasureSpec2);
                            measuredWidth2 = b2.getMeasuredWidth();
                            int measuredHeight4 = b2.getMeasuredHeight();
                            a(i11, a2, makeMeasureSpec2, b2);
                            this.d.a(i11, b2);
                            measuredHeight3 = measuredHeight4;
                        }
                        i6 = Math.max(i10, measuredWidth2 + flexItem.m() + flexItem.o() + this.d.a(b2));
                        bVar.e += measuredHeight3 + flexItem.n() + flexItem.p();
                    }
                    bVar.g = Math.max(bVar.g, i6);
                    i10 = i6;
                }
                i9 = i5 + 1;
                f = 0.0f;
            }
            if (z2 && i8 != bVar.e) {
                b(i, i2, bVar, i3, i4, true);
            }
        }
    }

    private int a(int i, FlexItem flexItem, int i2) {
        a aVar = this.d;
        int a2 = aVar.a(i, aVar.getPaddingLeft() + this.d.getPaddingRight() + flexItem.m() + flexItem.o() + i2, flexItem.a());
        int size = View.MeasureSpec.getSize(a2);
        if (size > flexItem.i()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.i(), View.MeasureSpec.getMode(a2));
        }
        return size < flexItem.g() ? View.MeasureSpec.makeMeasureSpec(flexItem.g(), View.MeasureSpec.getMode(a2)) : a2;
    }

    private int b(int i, FlexItem flexItem, int i2) {
        a aVar = this.d;
        int b2 = aVar.b(i, aVar.getPaddingTop() + this.d.getPaddingBottom() + flexItem.n() + flexItem.p() + i2, flexItem.b());
        int size = View.MeasureSpec.getSize(b2);
        if (size > flexItem.j()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.j(), View.MeasureSpec.getMode(b2));
        }
        return size < flexItem.h() ? View.MeasureSpec.makeMeasureSpec(flexItem.h(), View.MeasureSpec.getMode(b2)) : b2;
    }

    /* access modifiers changed from: package-private */
    public void b(int i, int i2, int i3) {
        int i4;
        int i5;
        int flexDirection = this.d.getFlexDirection();
        if (flexDirection == 0 || flexDirection == 1) {
            i5 = View.MeasureSpec.getMode(i2);
            i4 = View.MeasureSpec.getSize(i2);
        } else if (flexDirection == 2 || flexDirection == 3) {
            int mode = View.MeasureSpec.getMode(i);
            i4 = View.MeasureSpec.getSize(i);
            i5 = mode;
        } else {
            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        List<b> flexLinesInternal = this.d.getFlexLinesInternal();
        if (i5 == 1073741824) {
            int sumOfCrossSize = this.d.getSumOfCrossSize() + i3;
            int i6 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).g = i4 - i3;
            } else if (flexLinesInternal.size() >= 2) {
                int alignContent = this.d.getAlignContent();
                if (alignContent == 1) {
                    int i7 = i4 - sumOfCrossSize;
                    b bVar = new b();
                    bVar.g = i7;
                    flexLinesInternal.add(0, bVar);
                } else if (alignContent == 2) {
                    this.d.setFlexLines(a(flexLinesInternal, i4, sumOfCrossSize));
                } else if (alignContent != 3) {
                    if (alignContent != 4) {
                        if (alignContent == 5 && sumOfCrossSize < i4) {
                            float size = ((float) (i4 - sumOfCrossSize)) / ((float) flexLinesInternal.size());
                            int size2 = flexLinesInternal.size();
                            float f = 0.0f;
                            while (i6 < size2) {
                                b bVar2 = flexLinesInternal.get(i6);
                                float f2 = ((float) bVar2.g) + size;
                                if (i6 == flexLinesInternal.size() - 1) {
                                    f2 += f;
                                    f = 0.0f;
                                }
                                int round = Math.round(f2);
                                f += f2 - ((float) round);
                                if (f > 1.0f) {
                                    round++;
                                    f -= 1.0f;
                                } else if (f < -1.0f) {
                                    round--;
                                    f += 1.0f;
                                }
                                bVar2.g = round;
                                i6++;
                            }
                        }
                    } else if (sumOfCrossSize >= i4) {
                        this.d.setFlexLines(a(flexLinesInternal, i4, sumOfCrossSize));
                    } else {
                        int size3 = (i4 - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                        ArrayList arrayList = new ArrayList();
                        b bVar3 = new b();
                        bVar3.g = size3;
                        for (b bVar4 : flexLinesInternal) {
                            arrayList.add(bVar3);
                            arrayList.add(bVar4);
                            arrayList.add(bVar3);
                        }
                        this.d.setFlexLines(arrayList);
                    }
                } else if (sumOfCrossSize < i4) {
                    float size4 = ((float) (i4 - sumOfCrossSize)) / ((float) (flexLinesInternal.size() - 1));
                    ArrayList arrayList2 = new ArrayList();
                    int size5 = flexLinesInternal.size();
                    float f3 = 0.0f;
                    while (i6 < size5) {
                        arrayList2.add(flexLinesInternal.get(i6));
                        if (i6 != flexLinesInternal.size() - 1) {
                            b bVar5 = new b();
                            if (i6 == flexLinesInternal.size() - 2) {
                                bVar5.g = Math.round(f3 + size4);
                                f3 = 0.0f;
                            } else {
                                bVar5.g = Math.round(size4);
                            }
                            f3 += size4 - ((float) bVar5.g);
                            if (f3 > 1.0f) {
                                bVar5.g++;
                                f3 -= 1.0f;
                            } else if (f3 < -1.0f) {
                                bVar5.g--;
                                f3 += 1.0f;
                            }
                            arrayList2.add(bVar5);
                        }
                        i6++;
                    }
                    this.d.setFlexLines(arrayList2);
                }
            }
        }
    }

    private List<b> a(List<b> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        b bVar = new b();
        bVar.g = (i - i2) / 2;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 == 0) {
                arrayList.add(bVar);
            }
            arrayList.add(list.get(i3));
            if (i3 == list.size() - 1) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        a(0);
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        View b2;
        if (i < this.d.getFlexItemCount()) {
            int flexDirection = this.d.getFlexDirection();
            if (this.d.getAlignItems() == 4) {
                int[] iArr = this.a;
                List<b> flexLinesInternal = this.d.getFlexLinesInternal();
                int size = flexLinesInternal.size();
                for (int i2 = iArr != null ? iArr[i] : 0; i2 < size; i2++) {
                    b bVar = flexLinesInternal.get(i2);
                    int i3 = bVar.h;
                    for (int i4 = 0; i4 < i3; i4++) {
                        int i5 = bVar.o + i4;
                        if (!(i4 >= this.d.getFlexItemCount() || (b2 = this.d.b(i5)) == null || b2.getVisibility() == 8)) {
                            FlexItem flexItem = (FlexItem) b2.getLayoutParams();
                            if (flexItem.f() == -1 || flexItem.f() == 4) {
                                if (flexDirection == 0 || flexDirection == 1) {
                                    a(b2, bVar.g, i5);
                                } else if (flexDirection == 2 || flexDirection == 3) {
                                    b(b2, bVar.g, i5);
                                } else {
                                    throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                                }
                            }
                        }
                    }
                }
                return;
            }
            for (b bVar2 : this.d.getFlexLinesInternal()) {
                Iterator<Integer> it2 = bVar2.n.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Integer next = it2.next();
                        View b3 = this.d.b(next.intValue());
                        if (flexDirection == 0 || flexDirection == 1) {
                            a(b3, bVar2.g, next.intValue());
                        } else if (flexDirection == 2 || flexDirection == 3) {
                            b(b3, bVar2.g, next.intValue());
                        } else {
                            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                        }
                    }
                }
            }
        }
    }

    private void a(View view, int i, int i2) {
        int i3;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.n()) - flexItem.p()) - this.d.a(view), flexItem.h()), flexItem.j());
        long[] jArr = this.f;
        if (jArr != null) {
            i3 = a(jArr[i2]);
        } else {
            i3 = view.getMeasuredWidth();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        a(i2, makeMeasureSpec, makeMeasureSpec2, view);
        this.d.a(i2, view);
    }

    private void b(View view, int i, int i2) {
        int i3;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.m()) - flexItem.o()) - this.d.a(view), flexItem.g()), flexItem.i());
        long[] jArr = this.f;
        if (jArr != null) {
            i3 = b(jArr[i2]);
        } else {
            i3 = view.getMeasuredHeight();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        a(i2, makeMeasureSpec2, makeMeasureSpec, view);
        this.d.a(i2, view);
    }

    /* access modifiers changed from: package-private */
    public void a(View view, b bVar, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.d.getAlignItems();
        if (flexItem.f() != -1) {
            alignItems = flexItem.f();
        }
        int i5 = bVar.g;
        if (alignItems != 0) {
            if (alignItems != 1) {
                if (alignItems == 2) {
                    int measuredHeight = (((i5 - view.getMeasuredHeight()) + flexItem.n()) - flexItem.p()) / 2;
                    if (this.d.getFlexWrap() != 2) {
                        int i6 = i2 + measuredHeight;
                        view.layout(i, i6, i3, view.getMeasuredHeight() + i6);
                        return;
                    }
                    int i7 = i2 - measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                } else if (alignItems != 3) {
                    if (alignItems != 4) {
                        return;
                    }
                } else if (this.d.getFlexWrap() != 2) {
                    int max = Math.max(bVar.l - view.getBaseline(), flexItem.n());
                    view.layout(i, i2 + max, i3, i4 + max);
                    return;
                } else {
                    int max2 = Math.max((bVar.l - view.getMeasuredHeight()) + view.getBaseline(), flexItem.p());
                    view.layout(i, i2 - max2, i3, i4 - max2);
                    return;
                }
            } else if (this.d.getFlexWrap() != 2) {
                int i8 = i2 + i5;
                view.layout(i, (i8 - view.getMeasuredHeight()) - flexItem.p(), i3, i8 - flexItem.p());
                return;
            } else {
                view.layout(i, (i2 - i5) + view.getMeasuredHeight() + flexItem.n(), i3, (i4 - i5) + view.getMeasuredHeight() + flexItem.n());
                return;
            }
        }
        if (this.d.getFlexWrap() != 2) {
            view.layout(i, i2 + flexItem.n(), i3, i4 + flexItem.n());
        } else {
            view.layout(i, i2 - flexItem.p(), i3, i4 - flexItem.p());
        }
    }

    /* access modifiers changed from: package-private */
    public void a(View view, b bVar, boolean z, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.d.getAlignItems();
        if (flexItem.f() != -1) {
            alignItems = flexItem.f();
        }
        int i5 = bVar.g;
        if (alignItems != 0) {
            if (alignItems != 1) {
                if (alignItems == 2) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int measuredWidth = (((i5 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams)) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                    if (!z) {
                        view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                        return;
                    } else {
                        view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                        return;
                    }
                } else if (!(alignItems == 3 || alignItems == 4)) {
                    return;
                }
            } else if (!z) {
                view.layout(((i + i5) - view.getMeasuredWidth()) - flexItem.o(), i2, ((i3 + i5) - view.getMeasuredWidth()) - flexItem.o(), i4);
                return;
            } else {
                view.layout((i - i5) + view.getMeasuredWidth() + flexItem.m(), i2, (i3 - i5) + view.getMeasuredWidth() + flexItem.m(), i4);
                return;
            }
        }
        if (!z) {
            view.layout(i + flexItem.m(), i2, i3 + flexItem.m(), i4);
        } else {
            view.layout(i - flexItem.o(), i2, i3 - flexItem.o(), i4);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        long[] jArr = this.f;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.f = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.f = Arrays.copyOf(this.f, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(int i) {
        long[] jArr = this.b;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.b = new long[i];
        } else if (jArr.length < i) {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.b = Arrays.copyOf(this.b, i);
        }
    }

    private void a(int i, int i2, int i3, View view) {
        long[] jArr = this.b;
        if (jArr != null) {
            jArr[i] = b(i2, i3);
        }
        long[] jArr2 = this.f;
        if (jArr2 != null) {
            jArr2[i] = b(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    /* access modifiers changed from: package-private */
    public void d(int i) {
        int[] iArr = this.a;
        if (iArr == null) {
            if (i < 10) {
                i = 10;
            }
            this.a = new int[i];
        } else if (iArr.length < i) {
            int length = iArr.length * 2;
            if (length >= i) {
                i = length;
            }
            this.a = Arrays.copyOf(this.a, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(List<b> list, int i) {
        if (!c && this.a == null) {
            throw new AssertionError();
        } else if (c || this.b != null) {
            int i2 = this.a[i];
            if (i2 == -1) {
                i2 = 0;
            }
            for (int size = list.size() - 1; size >= i2; size--) {
                list.remove(size);
            }
            int[] iArr = this.a;
            int length = iArr.length - 1;
            if (i > length) {
                Arrays.fill(iArr, -1);
            } else {
                Arrays.fill(iArr, i, length, -1);
            }
            long[] jArr = this.b;
            int length2 = jArr.length - 1;
            if (i > length2) {
                Arrays.fill(jArr, 0L);
            } else {
                Arrays.fill(jArr, i, length2, 0L);
            }
        } else {
            throw new AssertionError();
        }
    }

    /* compiled from: FlexboxHelper */
    /* access modifiers changed from: private */
    public static class b implements Comparable<b> {
        int a;
        int b;

        private b() {
        }

        /* renamed from: a */
        public int compareTo(b bVar) {
            int i = this.b;
            int i2 = bVar.b;
            if (i != i2) {
                return i - i2;
            }
            return this.a - bVar.a;
        }

        @Override // java.lang.Object
        public String toString() {
            return "Order{order=" + this.b + ", index=" + this.a + '}';
        }
    }

    /* compiled from: FlexboxHelper */
    /* access modifiers changed from: package-private */
    public static class a {
        List<b> a;
        int b;

        a() {
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.a = null;
            this.b = 0;
        }
    }
}
