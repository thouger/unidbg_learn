package cn.missfresh.sherlock.trace.tracer;

import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: TraceDataUtils */
public class g {

    /* compiled from: TraceDataUtils */
    /* access modifiers changed from: package-private */
    public static class a implements Comparator<cn.missfresh.sherlock.trace.c.a> {
        a() {
        }

        /* renamed from: a */
        public int compare(cn.missfresh.sherlock.trace.c.a aVar, cn.missfresh.sherlock.trace.c.a aVar2) {
            return Integer.compare((aVar2.c + 1) * aVar2.b, (aVar.c + 1) * aVar.b);
        }
    }

    /* compiled from: TraceDataUtils */
    public interface b {
        int a();

        void a(List<cn.missfresh.sherlock.trace.c.a> list, int i);

        boolean a(long j, int i);
    }

    public static void a(long[] jArr, LinkedList<cn.missfresh.sherlock.trace.c.a> linkedList, boolean z, long j) {
        int c2;
        LinkedList linkedList2 = new LinkedList();
        boolean z2 = !z;
        int i = 0;
        for (long j2 : jArr) {
            if (0 != j2) {
                if (z) {
                    if (a(j2) && 1048574 == c(j2)) {
                        z2 = true;
                    }
                    if (!z2) {
                        j.b("TraceDataUtils", "never begin! pass this method " + c(j2));
                    }
                }
                if (a(j2)) {
                    if (((long) c(j2)) == 1048574) {
                        i = 0;
                    }
                    i++;
                    linkedList2.push(Long.valueOf(j2));
                } else {
                    int c3 = c(j2);
                    if (!linkedList2.isEmpty()) {
                        long longValue = ((Long) linkedList2.pop()).longValue();
                        i--;
                        LinkedList linkedList3 = new LinkedList();
                        linkedList3.add(Long.valueOf(longValue));
                        while (true) {
                            c2 = c(longValue);
                            if (c2 == c3 || linkedList2.isEmpty()) {
                                break;
                            }
                            j.c("TraceDataUtils", "pop inMethodId[%s] to continue match ouMethodId[%s]", Integer.valueOf(c2), Integer.valueOf(c3));
                            longValue = ((Long) linkedList2.pop()).longValue();
                            i--;
                            linkedList3.add(Long.valueOf(longValue));
                        }
                        if (c2 == c3 || c2 != 1048574) {
                            long b2 = b(j2) - b(longValue);
                            if (b2 < 0) {
                                j.d("TraceDataUtils", "[structuredDataToStack] trace during invalid:%d", Long.valueOf(b2));
                                linkedList2.clear();
                                linkedList.clear();
                                return;
                            }
                            a(linkedList, new cn.missfresh.sherlock.trace.c.a(c3, (int) b2, i));
                        } else {
                            j.d("TraceDataUtils", "inMethodId[%s] != outMethodId[%s] throw this outMethodId!", Integer.valueOf(c2), Integer.valueOf(c3));
                            linkedList2.addAll(linkedList3);
                            i += linkedList2.size();
                        }
                    } else {
                        j.c("TraceDataUtils", "[structuredDataToStack] method[%s] not found in! ", Integer.valueOf(c3));
                    }
                }
            }
        }
        while (!linkedList2.isEmpty() && z) {
            long longValue2 = ((Long) linkedList2.pop()).longValue();
            int c4 = c(longValue2);
            boolean a2 = a(longValue2);
            long b3 = b(longValue2) + AppMethodBeat.getDiffTime();
            if (!a2) {
                j.d("TraceDataUtils", "[structuredDataToStack] why has out Method[%s]? is wrong! ", Integer.valueOf(c4));
            } else {
                a(linkedList, new cn.missfresh.sherlock.trace.c.a(c4, (int) (j - b3), linkedList2.size()));
            }
        }
        c cVar = new c(null, null);
        a(linkedList, cVar);
        linkedList.clear();
        a(cVar, linkedList);
    }

    private static boolean a(long j) {
        return ((j >> 63) & 1) == 1;
    }

    private static long b(long j) {
        return j & 8796093022207L;
    }

    private static int c(long j) {
        return (int) ((j >> 43) & 1048575);
    }

    /* compiled from: TraceDataUtils */
    public static final class c {
        cn.missfresh.sherlock.trace.c.a a;
        c b;
        LinkedList<c> c = new LinkedList<>();

        c(cn.missfresh.sherlock.trace.c.a aVar, c cVar) {
            this.a = aVar;
            this.b = cVar;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void b(c cVar) {
            this.c.addFirst(cVar);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private int a() {
            cn.missfresh.sherlock.trace.c.a aVar = this.a;
            if (aVar == null) {
                return 0;
            }
            return aVar.c;
        }
    }

    private static int a(LinkedList<cn.missfresh.sherlock.trace.c.a> linkedList, cn.missfresh.sherlock.trace.c.a aVar) {
        int i;
        cn.missfresh.sherlock.trace.c.a peek = !linkedList.isEmpty() ? linkedList.peek() : null;
        if (peek == null || peek.a != aVar.a || peek.c != (i = aVar.c) || i == 0) {
            linkedList.push(aVar);
            return aVar.b;
        }
        int i2 = aVar.b;
        if (i2 == 5000) {
            i2 = peek.b;
        }
        aVar.b = i2;
        peek.a((long) aVar.b);
        return peek.b;
    }

    private static void a(c cVar, LinkedList<cn.missfresh.sherlock.trace.c.a> linkedList) {
        for (int i = 0; i < cVar.c.size(); i++) {
            c cVar2 = cVar.c.get(i);
            linkedList.add(cVar2.a);
            if (!cVar2.c.isEmpty()) {
                a(cVar2, linkedList);
            }
        }
    }

    public static int a(LinkedList<cn.missfresh.sherlock.trace.c.a> linkedList, c cVar) {
        ListIterator<cn.missfresh.sherlock.trace.c.a> listIterator = linkedList.listIterator(0);
        c cVar2 = null;
        int i = 0;
        while (listIterator.hasNext()) {
            c cVar3 = new c(listIterator.next(), cVar2);
            i++;
            if (cVar2 != null || cVar3.a() == 0) {
                int a2 = cVar3.a();
                if (cVar2 == null || a2 == 0) {
                    cVar.b(cVar3);
                } else if (cVar2.a() >= a2) {
                    while (cVar2.a() > a2) {
                        cVar2 = cVar2.b;
                    }
                    c cVar4 = cVar2.b;
                    if (cVar4 != null) {
                        cVar3.b = cVar4;
                        cVar2.b.b(cVar3);
                    }
                } else if (cVar2.a() < a2) {
                    cVar2.b(cVar3);
                }
                cVar2 = cVar3;
            } else {
                j.e("TraceDataUtils", "[stackToTree] begin error! why the first node'depth is not 0!");
                return 0;
            }
        }
        return i;
    }

    public static long a(LinkedList<cn.missfresh.sherlock.trace.c.a> linkedList, StringBuilder sb) {
        Iterator<cn.missfresh.sherlock.trace.c.a> it2 = linkedList.iterator();
        long j = 0;
        while (it2.hasNext()) {
            cn.missfresh.sherlock.trace.c.a next = it2.next();
            sb.append(next.toString());
            sb.append('\n');
            long j2 = (long) next.b;
            if (j < j2) {
                j = j2;
            }
        }
        return j;
    }

    public static void a(List<cn.missfresh.sherlock.trace.c.a> list, int i, b bVar) {
        if (i < 0) {
            list.clear();
            return;
        }
        int size = list.size();
        int i2 = 1;
        while (size > i) {
            ListIterator<cn.missfresh.sherlock.trace.c.a> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                if (bVar.a((long) listIterator.previous().b, i2)) {
                    listIterator.remove();
                    size--;
                    if (size <= i) {
                        return;
                    }
                }
            }
            size = list.size();
            i2++;
            if (bVar.a() < i2) {
                break;
            }
        }
        int size2 = list.size();
        if (size2 > i) {
            bVar.a(list, size2);
        }
    }

    public static String a(List<cn.missfresh.sherlock.trace.c.a> list, long j) {
        StringBuilder sb = new StringBuilder();
        long j2 = (long) (((float) j) * 0.3f);
        LinkedList linkedList = new LinkedList();
        for (cn.missfresh.sherlock.trace.c.a aVar : list) {
            if (((long) aVar.b) >= j2) {
                linkedList.add(aVar);
            }
        }
        Collections.sort(linkedList, new a());
        if (linkedList.isEmpty() && !list.isEmpty()) {
            linkedList.add(list.get(0));
        } else if (linkedList.size() > 1 && ((cn.missfresh.sherlock.trace.c.a) linkedList.peek()).a == 1048574) {
            linkedList.removeFirst();
        }
        Iterator it2 = linkedList.iterator();
        if (it2.hasNext()) {
            sb.append(((cn.missfresh.sherlock.trace.c.a) it2.next()).a + HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
        }
        return sb.toString();
    }
}
