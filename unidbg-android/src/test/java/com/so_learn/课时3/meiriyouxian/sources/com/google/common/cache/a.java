package com.google.common.cache;

/* compiled from: AbstractCache */
public abstract class a<K, V> implements b<K, V> {

    /* compiled from: AbstractCache */
    public interface b {
        void a();

        void a(int i);

        void a(long j);

        c b();

        void b(int i);

        void b(long j);
    }

    /* compiled from: AbstractCache */
    /* renamed from: com.google.common.cache.a$a  reason: collision with other inner class name */
    public static final class C0103a implements b {
        private final f a = LongAddables.a();
        private final f b = LongAddables.a();
        private final f c = LongAddables.a();
        private final f d = LongAddables.a();
        private final f e = LongAddables.a();
        private final f f = LongAddables.a();

        private static long c(long j) {
            if (j >= 0) {
                return j;
            }
            return Long.MAX_VALUE;
        }

        @Override // com.google.common.cache.a.b
        public void a(int i) {
            this.a.add((long) i);
        }

        @Override // com.google.common.cache.a.b
        public void b(int i) {
            this.b.add((long) i);
        }

        @Override // com.google.common.cache.a.b
        public void a(long j) {
            this.c.increment();
            this.e.add(j);
        }

        @Override // com.google.common.cache.a.b
        public void b(long j) {
            this.d.increment();
            this.e.add(j);
        }

        @Override // com.google.common.cache.a.b
        public void a() {
            this.f.increment();
        }

        @Override // com.google.common.cache.a.b
        public c b() {
            return new c(c(this.a.sum()), c(this.b.sum()), c(this.c.sum()), c(this.d.sum()), c(this.e.sum()), c(this.f.sum()));
        }

        public void a(b bVar) {
            c b = bVar.b();
            this.a.add(b.a());
            this.b.add(b.b());
            this.c.add(b.c());
            this.d.add(b.d());
            this.e.add(b.e());
            this.f.add(b.f());
        }
    }
}
