package cn.missfresh.basiclib.net.b.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/* compiled from: SimpleFastJsonConfig */
public final class b extends a {
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.net.b.c.a
    public SerializerFeature[] a() {
        return new SerializerFeature[0];
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.net.b.c.a
    public ParseProcess c() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.net.b.c.a
    public Feature[] d() {
        return new Feature[0];
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.net.b.c.a
    public SerializeConfig b() {
        AppMethodBeat.i(3676, false);
        SerializeConfig globalInstance = SerializeConfig.getGlobalInstance();
        AppMethodBeat.o(3676);
        return globalInstance;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.net.b.c.a
    public int e() {
        return JSON.DEFAULT_PARSER_FEATURE;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.basiclib.net.b.c.a
    public ParserConfig f() {
        AppMethodBeat.i(3681, false);
        ParserConfig globalInstance = ParserConfig.getGlobalInstance();
        AppMethodBeat.o(3681);
        return globalInstance;
    }
}
