package cn.missfresh.basiclib.net.b.c;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/* compiled from: AbsFastJsonConfig */
public abstract class a {
    private ParserConfig a;
    private int b;
    private Feature[] c;
    private ParseProcess d;
    private SerializeConfig e;
    private SerializerFeature[] f;

    /* access modifiers changed from: protected */
    public abstract SerializerFeature[] a();

    /* access modifiers changed from: protected */
    public abstract SerializeConfig b();

    /* access modifiers changed from: protected */
    public abstract ParseProcess c();

    /* access modifiers changed from: protected */
    public abstract Feature[] d();

    /* access modifiers changed from: protected */
    public abstract int e();

    /* access modifiers changed from: protected */
    public abstract ParserConfig f();

    public a() {
        this.a = ParserConfig.getGlobalInstance();
        this.b = JSON.DEFAULT_PARSER_FEATURE;
        this.a = f();
        this.b = e();
        this.c = d();
        this.d = c();
        this.e = b();
        this.f = a();
    }

    public final ParserConfig g() {
        ParserConfig parserConfig = this.a;
        return parserConfig == null ? ParserConfig.getGlobalInstance() : parserConfig;
    }

    public final int h() {
        int i = this.b;
        return i <= 0 ? JSON.DEFAULT_PARSER_FEATURE : i;
    }

    public final Feature[] i() {
        Feature[] featureArr = this.c;
        return featureArr == null ? new Feature[0] : featureArr;
    }

    public final ParseProcess j() {
        return this.d;
    }

    public final SerializeConfig k() {
        SerializeConfig serializeConfig = this.e;
        return serializeConfig == null ? SerializeConfig.getGlobalInstance() : serializeConfig;
    }

    public final SerializerFeature[] l() {
        return this.f;
    }
}
