package com.sobot.chat.widget.timePicker.view;

import android.net.wifi.WifiEnterpriseConfig;
import android.provider.MediaStore;
import android.telephony.PreciseDisconnectCause;
import android.view.View;
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.timePicker.b.c;
import com.sobot.chat.widget.timePicker.lib.SobotWheelView;
import com.tencent.connect.common.Constants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/* compiled from: SobotWheelTime */
public class a {
    public static DateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int b;
    int c;
    int d;
    float e = 1.6f;
    private View f;
    private SobotWheelView g;
    private SobotWheelView h;
    private SobotWheelView i;
    private SobotWheelView j;
    private SobotWheelView k;
    private SobotWheelView l;
    private int m;
    private boolean[] n;
    private int o = PreciseDisconnectCause.ECBM_NOT_SUPPORTED;
    private int p = PreciseDisconnectCause.ANSWERED_ELSEWHERE;
    private int q = 1;
    private int r = 12;
    private int s = 1;
    private int t = 31;
    private int u;
    private int v = 18;
    private SobotWheelView.DividerType w;

    public a(View view, boolean[] zArr, int i, int i2) {
        this.f = view;
        this.n = zArr;
        this.m = i;
        this.v = i2;
        a(view);
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        String[] strArr = {"1", "3", "5", "7", "8", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ, Constants.VIA_REPORT_TYPE_SET_AVATAR};
        String[] strArr2 = {"4", Constants.VIA_SHARE_TYPE_INFO, "9", Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE};
        List asList = Arrays.asList(strArr);
        List asList2 = Arrays.asList(strArr2);
        this.u = i;
        View view = this.f;
        this.g = (SobotWheelView) view.findViewById(q.a(view.getContext(), "id", MediaStore.Audio.AudioColumns.YEAR));
        this.g.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.o, this.p));
        this.g.setCurrentItem(i - this.o);
        this.g.setGravity(this.m);
        View view2 = this.f;
        this.h = (SobotWheelView) view2.findViewById(q.a(view2.getContext(), "id", "month"));
        int i9 = this.o;
        int i10 = this.p;
        if (i9 == i10) {
            this.h.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.q, this.r));
            this.h.setCurrentItem((i2 + 1) - this.q);
        } else if (i == i9) {
            this.h.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.q, 12));
            this.h.setCurrentItem((i2 + 1) - this.q);
        } else if (i == i10) {
            this.h.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, this.r));
            this.h.setCurrentItem(i2);
        } else {
            this.h.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, 12));
            this.h.setCurrentItem(i2);
        }
        this.h.setGravity(this.m);
        View view3 = this.f;
        this.i = (SobotWheelView) view3.findViewById(q.a(view3.getContext(), "id", "day"));
        if (this.o == this.p && this.q == this.r) {
            int i11 = i2 + 1;
            if (asList.contains(String.valueOf(i11))) {
                if (this.t > 31) {
                    this.t = 31;
                }
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.s, this.t));
            } else if (asList2.contains(String.valueOf(i11))) {
                if (this.t > 30) {
                    this.t = 30;
                }
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.s, this.t));
            } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                if (this.t > 28) {
                    this.t = 28;
                }
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.s, this.t));
            } else {
                if (this.t > 29) {
                    this.t = 29;
                }
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.s, this.t));
            }
            this.i.setCurrentItem(i3 - this.s);
        } else if (i == this.o && (i8 = i2 + 1) == this.q) {
            if (asList.contains(String.valueOf(i8))) {
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.s, 31));
            } else if (asList2.contains(String.valueOf(i8))) {
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.s, 30));
            } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.s, 28));
            } else {
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(this.s, 29));
            }
            this.i.setCurrentItem(i3 - this.s);
        } else if (i == this.p && (i7 = i2 + 1) == this.r) {
            if (asList.contains(String.valueOf(i7))) {
                if (this.t > 31) {
                    this.t = 31;
                }
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, this.t));
            } else if (asList2.contains(String.valueOf(i7))) {
                if (this.t > 30) {
                    this.t = 30;
                }
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, this.t));
            } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                if (this.t > 28) {
                    this.t = 28;
                }
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, this.t));
            } else {
                if (this.t > 29) {
                    this.t = 29;
                }
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, this.t));
            }
            this.i.setCurrentItem(i3 - 1);
        } else {
            int i12 = i2 + 1;
            if (asList.contains(String.valueOf(i12))) {
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, 31));
            } else if (asList2.contains(String.valueOf(i12))) {
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, 30));
            } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, 28));
            } else {
                this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, 29));
            }
            this.i.setCurrentItem(i3 - 1);
        }
        this.i.setGravity(this.m);
        View view4 = this.f;
        this.j = (SobotWheelView) view4.findViewById(q.a(view4.getContext(), "id", "hour"));
        this.j.setAdapter(new com.sobot.chat.widget.timePicker.a.a(0, 23));
        this.j.setCurrentItem(i4);
        this.j.setGravity(this.m);
        View view5 = this.f;
        this.k = (SobotWheelView) view5.findViewById(q.a(view5.getContext(), "id", "min"));
        this.k.setAdapter(new com.sobot.chat.widget.timePicker.a.a(0, 59));
        this.k.setCurrentItem(i5);
        this.k.setGravity(this.m);
        View view6 = this.f;
        this.l = (SobotWheelView) view6.findViewById(q.a(view6.getContext(), "id", "second"));
        this.l.setAdapter(new com.sobot.chat.widget.timePicker.a.a(0, 59));
        this.l.setCurrentItem(i6);
        this.l.setGravity(this.m);
        AnonymousClass1 r1 = new AnonymousClass1(asList, asList2);
        AnonymousClass2 r2 = new AnonymousClass2(asList, asList2);
        this.g.setOnItemSelectedListener(r1);
        this.h.setOnItemSelectedListener(r2);
        boolean[] zArr = this.n;
        if (zArr.length == 6) {
            int i13 = 8;
            this.g.setVisibility(zArr[0] ? 0 : 8);
            this.h.setVisibility(this.n[1] ? 0 : 8);
            this.i.setVisibility(this.n[2] ? 0 : 8);
            this.j.setVisibility(this.n[3] ? 0 : 8);
            this.k.setVisibility(this.n[4] ? 0 : 8);
            SobotWheelView sobotWheelView = this.l;
            if (this.n[5]) {
                i13 = 0;
            }
            sobotWheelView.setVisibility(i13);
            b();
            return;
        }
        throw new IllegalArgumentException("type[] length is not 6");
    }

    /* compiled from: SobotWheelTime */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.timePicker.view.a$1  reason: invalid class name */
    public class AnonymousClass1 implements c {
        final /* synthetic */ List a;
        final /* synthetic */ List b;

        AnonymousClass1(List list, List list2) {
            this.a = list;
            this.b = list2;
        }

        @Override // com.sobot.chat.widget.timePicker.b.c
        public void a(int i) {
            int i2 = i + a.this.o;
            a.this.u = i2;
            int currentItem = a.this.h.getCurrentItem();
            if (a.this.o == a.this.p) {
                a.this.h.setAdapter(new com.sobot.chat.widget.timePicker.a.a(a.this.q, a.this.r));
                if (currentItem > a.this.h.getAdapter().a() - 1) {
                    currentItem = a.this.h.getAdapter().a() - 1;
                    a.this.h.setCurrentItem(currentItem);
                }
                int i3 = currentItem + a.this.q;
                if (a.this.q == a.this.r) {
                    a aVar = a.this;
                    aVar.a(i2, i3, aVar.s, a.this.t, this.a, this.b);
                } else if (i3 == a.this.q) {
                    a aVar2 = a.this;
                    aVar2.a(i2, i3, aVar2.s, 31, this.a, this.b);
                } else {
                    a.this.a(i2, i3, 1, 31, this.a, this.b);
                }
            } else if (i2 == a.this.o) {
                a.this.h.setAdapter(new com.sobot.chat.widget.timePicker.a.a(a.this.q, 12));
                if (currentItem > a.this.h.getAdapter().a() - 1) {
                    currentItem = a.this.h.getAdapter().a() - 1;
                    a.this.h.setCurrentItem(currentItem);
                }
                int i4 = currentItem + a.this.q;
                if (i4 == a.this.q) {
                    a aVar3 = a.this;
                    aVar3.a(i2, i4, aVar3.s, 31, this.a, this.b);
                    return;
                }
                a.this.a(i2, i4, 1, 31, this.a, this.b);
            } else if (i2 == a.this.p) {
                a.this.h.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, a.this.r));
                if (currentItem > a.this.h.getAdapter().a() - 1) {
                    currentItem = a.this.h.getAdapter().a() - 1;
                    a.this.h.setCurrentItem(currentItem);
                }
                int i5 = 1 + currentItem;
                if (i5 == a.this.r) {
                    a aVar4 = a.this;
                    aVar4.a(i2, i5, 1, aVar4.t, this.a, this.b);
                    return;
                }
                a.this.a(i2, i5, 1, 31, this.a, this.b);
            } else {
                a.this.h.setAdapter(new com.sobot.chat.widget.timePicker.a.a(1, 12));
                a aVar5 = a.this;
                aVar5.a(i2, 1 + aVar5.h.getCurrentItem(), 1, 31, this.a, this.b);
            }
        }
    }

    /* compiled from: SobotWheelTime */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.timePicker.view.a$2  reason: invalid class name */
    public class AnonymousClass2 implements c {
        final /* synthetic */ List a;
        final /* synthetic */ List b;

        AnonymousClass2(List list, List list2) {
            this.a = list;
            this.b = list2;
        }

        @Override // com.sobot.chat.widget.timePicker.b.c
        public void a(int i) {
            int i2 = i + 1;
            if (a.this.o == a.this.p) {
                int i3 = (i2 + a.this.q) - 1;
                if (a.this.q == a.this.r) {
                    a aVar = a.this;
                    aVar.a(aVar.u, i3, a.this.s, a.this.t, this.a, this.b);
                } else if (a.this.q == i3) {
                    a aVar2 = a.this;
                    aVar2.a(aVar2.u, i3, a.this.s, 31, this.a, this.b);
                } else if (a.this.r == i3) {
                    a aVar3 = a.this;
                    aVar3.a(aVar3.u, i3, 1, a.this.t, this.a, this.b);
                } else {
                    a aVar4 = a.this;
                    aVar4.a(aVar4.u, i3, 1, 31, this.a, this.b);
                }
            } else if (a.this.u == a.this.o) {
                int i4 = (i2 + a.this.q) - 1;
                if (i4 == a.this.q) {
                    a aVar5 = a.this;
                    aVar5.a(aVar5.u, i4, a.this.s, 31, this.a, this.b);
                    return;
                }
                a aVar6 = a.this;
                aVar6.a(aVar6.u, i4, 1, 31, this.a, this.b);
            } else if (a.this.u != a.this.p) {
                a aVar7 = a.this;
                aVar7.a(aVar7.u, i2, 1, 31, this.a, this.b);
            } else if (i2 == a.this.r) {
                a aVar8 = a.this;
                aVar8.a(aVar8.u, a.this.h.getCurrentItem() + 1, 1, a.this.t, this.a, this.b);
            } else {
                a aVar9 = a.this;
                aVar9.a(aVar9.u, a.this.h.getCurrentItem() + 1, 1, 31, this.a, this.b);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i, int i2, int i3, int i4, List<String> list, List<String> list2) {
        int currentItem = this.i.getCurrentItem();
        if (list.contains(String.valueOf(i2))) {
            int i5 = 31;
            if (i4 <= 31) {
                i5 = i4;
            }
            this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(i3, i5));
        } else if (list2.contains(String.valueOf(i2))) {
            int i6 = 30;
            if (i4 <= 30) {
                i6 = i4;
            }
            this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(i3, i6));
        } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
            int i7 = 28;
            if (i4 <= 28) {
                i7 = i4;
            }
            this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(i3, i7));
        } else {
            int i8 = 29;
            if (i4 <= 29) {
                i8 = i4;
            }
            this.i.setAdapter(new com.sobot.chat.widget.timePicker.a.a(i3, i8));
        }
        if (currentItem > this.i.getAdapter().a() - 1) {
            this.i.setCurrentItem(this.i.getAdapter().a() - 1);
        }
    }

    private void b() {
        this.i.setTextSize((float) this.v);
        this.h.setTextSize((float) this.v);
        this.g.setTextSize((float) this.v);
        this.j.setTextSize((float) this.v);
        this.k.setTextSize((float) this.v);
        this.l.setTextSize((float) this.v);
    }

    private void c() {
        this.i.setTextColorOut(this.b);
        this.h.setTextColorOut(this.b);
        this.g.setTextColorOut(this.b);
        this.j.setTextColorOut(this.b);
        this.k.setTextColorOut(this.b);
        this.l.setTextColorOut(this.b);
    }

    private void d() {
        this.i.setTextColorCenter(this.c);
        this.h.setTextColorCenter(this.c);
        this.g.setTextColorCenter(this.c);
        this.j.setTextColorCenter(this.c);
        this.k.setTextColorCenter(this.c);
        this.l.setTextColorCenter(this.c);
    }

    private void e() {
        this.i.setDividerColor(this.d);
        this.h.setDividerColor(this.d);
        this.g.setDividerColor(this.d);
        this.j.setDividerColor(this.d);
        this.k.setDividerColor(this.d);
        this.l.setDividerColor(this.d);
    }

    private void f() {
        this.i.setDividerType(this.w);
        this.h.setDividerType(this.w);
        this.g.setDividerType(this.w);
        this.j.setDividerType(this.w);
        this.k.setDividerType(this.w);
        this.l.setDividerType(this.w);
    }

    private void g() {
        this.i.setLineSpacingMultiplier(this.e);
        this.h.setLineSpacingMultiplier(this.e);
        this.g.setLineSpacingMultiplier(this.e);
        this.j.setLineSpacingMultiplier(this.e);
        this.k.setLineSpacingMultiplier(this.e);
        this.l.setLineSpacingMultiplier(this.e);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        if (str != null) {
            this.g.setLabel(str);
        }
        if (str2 != null) {
            this.h.setLabel(str2);
        }
        if (str3 != null) {
            this.i.setLabel(str3);
        }
        if (str4 != null) {
            this.j.setLabel(str4);
        }
        if (str5 != null) {
            this.k.setLabel(str5);
        }
        if (str6 != null) {
            this.l.setLabel(str6);
        }
    }

    public void a(boolean z) {
        this.g.setCyclic(z);
        this.h.setCyclic(z);
        this.i.setCyclic(z);
        this.j.setCyclic(z);
        this.k.setCyclic(z);
        this.l.setCyclic(z);
    }

    public String a() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.u == this.o) {
            int currentItem = this.h.getCurrentItem();
            int i = this.q;
            if (currentItem + i == i) {
                stringBuffer.append(this.g.getCurrentItem() + this.o);
                stringBuffer.append("-");
                stringBuffer.append(this.h.getCurrentItem() + this.q);
                stringBuffer.append("-");
                stringBuffer.append(this.i.getCurrentItem() + this.s);
                stringBuffer.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                stringBuffer.append(this.j.getCurrentItem());
                stringBuffer.append(":");
                stringBuffer.append(this.k.getCurrentItem());
                stringBuffer.append(":");
                stringBuffer.append(this.l.getCurrentItem());
            } else {
                stringBuffer.append(this.g.getCurrentItem() + this.o);
                stringBuffer.append("-");
                stringBuffer.append(this.h.getCurrentItem() + this.q);
                stringBuffer.append("-");
                stringBuffer.append(this.i.getCurrentItem() + 1);
                stringBuffer.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                stringBuffer.append(this.j.getCurrentItem());
                stringBuffer.append(":");
                stringBuffer.append(this.k.getCurrentItem());
                stringBuffer.append(":");
                stringBuffer.append(this.l.getCurrentItem());
            }
        } else {
            stringBuffer.append(this.g.getCurrentItem() + this.o);
            stringBuffer.append("-");
            stringBuffer.append(this.h.getCurrentItem() + 1);
            stringBuffer.append("-");
            stringBuffer.append(this.i.getCurrentItem() + 1);
            stringBuffer.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            stringBuffer.append(this.j.getCurrentItem());
            stringBuffer.append(":");
            stringBuffer.append(this.k.getCurrentItem());
            stringBuffer.append(":");
            stringBuffer.append(this.l.getCurrentItem());
        }
        return stringBuffer.toString();
    }

    public void a(View view) {
        this.f = view;
    }

    public void a(int i) {
        this.o = i;
    }

    public void b(int i) {
        this.p = i;
    }

    public void a(Calendar calendar, Calendar calendar2) {
        if (calendar == null && calendar2 != null) {
            int i = calendar2.get(1);
            int i2 = calendar2.get(2) + 1;
            int i3 = calendar2.get(5);
            int i4 = this.o;
            if (i > i4) {
                this.p = i;
                this.r = i2;
                this.t = i3;
            } else if (i == i4) {
                int i5 = this.q;
                if (i2 > i5) {
                    this.p = i;
                    this.r = i2;
                    this.t = i3;
                } else if (i2 == i5 && i3 > this.s) {
                    this.p = i;
                    this.r = i2;
                    this.t = i3;
                }
            }
        } else if (calendar != null && calendar2 == null) {
            int i6 = calendar.get(1);
            int i7 = calendar.get(2) + 1;
            int i8 = calendar.get(5);
            int i9 = this.p;
            if (i6 < i9) {
                this.q = i7;
                this.s = i8;
                this.o = i6;
            } else if (i6 == i9) {
                int i10 = this.r;
                if (i7 < i10) {
                    this.q = i7;
                    this.s = i8;
                    this.o = i6;
                } else if (i7 == i10 && i8 < this.t) {
                    this.q = i7;
                    this.s = i8;
                    this.o = i6;
                }
            }
        } else if (calendar != null && calendar2 != null) {
            this.o = calendar.get(1);
            this.p = calendar2.get(1);
            this.q = calendar.get(2) + 1;
            this.r = calendar2.get(2) + 1;
            this.s = calendar.get(5);
            this.t = calendar2.get(5);
        }
    }

    public void a(float f) {
        this.e = f;
        g();
    }

    public void c(int i) {
        this.d = i;
        e();
    }

    public void a(SobotWheelView.DividerType dividerType) {
        this.w = dividerType;
        f();
    }

    public void d(int i) {
        this.c = i;
        d();
    }

    public void e(int i) {
        this.b = i;
        c();
    }

    public void a(Boolean bool) {
        this.i.a(bool);
        this.h.a(bool);
        this.g.a(bool);
        this.j.a(bool);
        this.k.a(bool);
        this.l.a(bool);
    }
}
