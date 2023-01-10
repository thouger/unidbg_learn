package cn.missfresh.module.search.d;

import cn.missfresh.module.search.adapter.bean.SearchHotListBean;
import cn.missfresh.module.search.adapter.bean.SearchResultBean;
import cn.missfresh.module.search.adapter.bean.SearchTagsBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Type;

/* compiled from: SearchResultDeserializer */
public class a implements ObjectDeserializer {
    public int getFastMatchToken() {
        return 0;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        AppMethodBeat.i(10551, false);
        JSONObject parseObject = defaultJSONParser.parseObject();
        int intValue = parseObject.getIntValue("cellType");
        if (intValue == 30) {
            T t = (T) JSON.toJavaObject(parseObject, SearchHotListBean.class);
            AppMethodBeat.o(10551);
            return t;
        } else if (intValue != 40) {
            T t2 = (T) JSON.toJavaObject(parseObject, SearchResultBean.class);
            AppMethodBeat.o(10551);
            return t2;
        } else {
            T t3 = (T) JSON.toJavaObject(parseObject, SearchTagsBean.class);
            AppMethodBeat.o(10551);
            return t3;
        }
    }
}
