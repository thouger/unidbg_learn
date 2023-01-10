package com.sobot.chat.widget.kpswitch.widget.data;

import com.sobot.chat.widget.kpswitch.widget.a.c;
import com.sobot.chat.widget.kpswitch.widget.data.EmoticonPageEntity;
import com.sobot.chat.widget.kpswitch.widget.data.PageSetEntity;
import java.util.ArrayList;

public class EmoticonPageSetEntity<T> extends PageSetEntity<EmoticonPageEntity> {
    final EmoticonPageEntity.DelBtnStatus mDelBtnStatus;
    final ArrayList<T> mEmoticonList;
    final int mLine;
    final int mRow;

    public EmoticonPageSetEntity(a aVar) {
        super(aVar);
        this.mLine = aVar.a;
        this.mRow = aVar.b;
        this.mDelBtnStatus = aVar.c;
        this.mEmoticonList = aVar.d;
    }

    public int getLine() {
        return this.mLine;
    }

    public int getRow() {
        return this.mRow;
    }

    public EmoticonPageEntity.DelBtnStatus getDelBtnStatus() {
        return this.mDelBtnStatus;
    }

    public ArrayList<T> getEmoticonList() {
        return this.mEmoticonList;
    }

    public static class a<T> extends PageSetEntity.a {
        protected int a;
        protected int b;
        protected EmoticonPageEntity.DelBtnStatus c = EmoticonPageEntity.DelBtnStatus.GONE;
        protected ArrayList<T> d;
        protected c e;

        public a a(int i) {
            this.a = i;
            return this;
        }

        public a b(int i) {
            this.b = i;
            return this;
        }

        public a a(EmoticonPageEntity.DelBtnStatus delBtnStatus) {
            this.c = delBtnStatus;
            return this;
        }

        public a a(ArrayList<T> arrayList) {
            this.d = arrayList;
            return this;
        }

        public a a(c cVar) {
            this.e = cVar;
            return this;
        }

        public EmoticonPageSetEntity<T> a() {
            int size = this.d.size();
            int i = (this.b * this.a) - (this.c.isShow() ? 1 : 0);
            this.f = (int) Math.ceil(((double) this.d.size()) / ((double) i));
            int i2 = i > size ? size : i;
            if (!this.h.isEmpty()) {
                this.h.clear();
            }
            int i3 = 0;
            int i4 = i2;
            int i5 = 0;
            while (i3 < this.f) {
                EmoticonPageEntity emoticonPageEntity = new EmoticonPageEntity();
                emoticonPageEntity.a(this.a);
                emoticonPageEntity.b(this.b);
                emoticonPageEntity.a(this.c);
                emoticonPageEntity.a(this.d.subList(i5, i4));
                emoticonPageEntity.a(this.e);
                this.h.add(emoticonPageEntity);
                i5 = (i3 * i) + i;
                i3++;
                i4 = (i3 * i) + i;
                if (i4 >= size) {
                    i4 = size;
                }
            }
            return new EmoticonPageSetEntity<>(this);
        }
    }
}
