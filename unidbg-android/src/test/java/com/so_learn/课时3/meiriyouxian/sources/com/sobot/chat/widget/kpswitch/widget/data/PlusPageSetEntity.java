package com.sobot.chat.widget.kpswitch.widget.data;

import com.sobot.chat.widget.kpswitch.widget.a.c;
import com.sobot.chat.widget.kpswitch.widget.data.PageSetEntity;
import java.util.List;

public class PlusPageSetEntity<T> extends PageSetEntity<EmoticonPageEntity> {
    final List<T> mDataList;
    final int mLine;
    final int mRow;

    public PlusPageSetEntity(a aVar) {
        super(aVar);
        this.mLine = aVar.a;
        this.mRow = aVar.b;
        this.mDataList = aVar.c;
    }

    public int getLine() {
        return this.mLine;
    }

    public int getRow() {
        return this.mRow;
    }

    public List<T> getDataList() {
        return this.mDataList;
    }

    public static class a<T> extends PageSetEntity.a {
        protected int a;
        protected int b;
        protected List<T> c;
        protected c d;

        public a a(int i) {
            this.a = i;
            return this;
        }

        public a b(int i) {
            this.b = i;
            return this;
        }

        public a a(List<T> list) {
            this.c = list;
            return this;
        }

        public a a(c cVar) {
            this.d = cVar;
            return this;
        }

        public PlusPageSetEntity<T> a() {
            int size = this.c.size();
            int i = this.b * this.a;
            this.f = (int) Math.ceil(((double) this.c.size()) / ((double) i));
            int i2 = i > size ? size : i;
            if (!this.h.isEmpty()) {
                this.h.clear();
            }
            int i3 = 0;
            int i4 = i2;
            int i5 = 0;
            while (i3 < this.f) {
                b bVar = new b();
                bVar.a(this.a);
                bVar.b(this.b);
                bVar.a(this.c.subList(i5, i4));
                bVar.a(this.d);
                this.h.add(bVar);
                i5 = (i3 * i) + i;
                i3++;
                i4 = (i3 * i) + i;
                if (i4 >= size) {
                    i4 = size;
                }
            }
            return new PlusPageSetEntity<>(this);
        }
    }
}
