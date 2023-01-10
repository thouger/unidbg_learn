package com.google.common.base;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class JdkPattern extends e implements Serializable {
    private static final long serialVersionUID = 0;
    private final Pattern pattern;

    JdkPattern(Pattern pattern) {
        this.pattern = (Pattern) m.a(pattern);
    }

    @Override // com.google.common.base.e
    public d matcher(CharSequence charSequence) {
        return new a(this.pattern.matcher(charSequence));
    }

    @Override // com.google.common.base.e
    public String pattern() {
        return this.pattern.pattern();
    }

    @Override // com.google.common.base.e
    public int flags() {
        return this.pattern.flags();
    }

    @Override // com.google.common.base.e, java.lang.Object
    public String toString() {
        return this.pattern.toString();
    }

    private static final class a extends d {
        final Matcher a;

        a(Matcher matcher) {
            this.a = (Matcher) m.a(matcher);
        }

        @Override // com.google.common.base.d
        public boolean a() {
            return this.a.find();
        }
    }
}
