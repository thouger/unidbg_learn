package cn.missfresh.player;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MediaSource {
    public static final String URL_KEY_DEFAULT = "URL_KEY_DEFAULT";
    public int currentUrlIndex;
    public HashMap<String, String> headerMap = new HashMap<>();
    public boolean looping = false;
    public Object[] objects;
    public String title = "";
    public LinkedHashMap urlsMap = new LinkedHashMap();

    public MediaSource(String str) {
        AppMethodBeat.i(7650, false);
        this.urlsMap.put(URL_KEY_DEFAULT, str);
        this.currentUrlIndex = 0;
        AppMethodBeat.o(7650);
    }

    public MediaSource(String str, String str2) {
        AppMethodBeat.i(7652, false);
        this.urlsMap.put(URL_KEY_DEFAULT, str);
        this.title = str2;
        this.currentUrlIndex = 0;
        AppMethodBeat.o(7652);
    }

    public MediaSource(Object obj) {
        AppMethodBeat.i(7656, false);
        this.urlsMap.put(URL_KEY_DEFAULT, obj);
        this.currentUrlIndex = 0;
        AppMethodBeat.o(7656);
    }

    public MediaSource(LinkedHashMap linkedHashMap) {
        AppMethodBeat.i(7659, false);
        this.urlsMap.clear();
        this.urlsMap.putAll(linkedHashMap);
        this.currentUrlIndex = 0;
        AppMethodBeat.o(7659);
    }

    public MediaSource(LinkedHashMap linkedHashMap, String str) {
        AppMethodBeat.i(7663, false);
        this.urlsMap.clear();
        this.urlsMap.putAll(linkedHashMap);
        this.title = str;
        this.currentUrlIndex = 0;
        AppMethodBeat.o(7663);
    }

    public Object getCurrentUrl() {
        AppMethodBeat.i(7665, false);
        Object valueFromLinkedMap = getValueFromLinkedMap(this.currentUrlIndex);
        AppMethodBeat.o(7665);
        return valueFromLinkedMap;
    }

    public Object getCurrentKey() {
        AppMethodBeat.i(7669, false);
        String keyFromDataSource = getKeyFromDataSource(this.currentUrlIndex);
        AppMethodBeat.o(7669);
        return keyFromDataSource;
    }

    public String getKeyFromDataSource(int i) {
        int i2 = 0;
        AppMethodBeat.i(7672, false);
        for (Object obj : this.urlsMap.keySet()) {
            if (i2 == i) {
                String obj2 = obj.toString();
                AppMethodBeat.o(7672);
                return obj2;
            }
            i2++;
        }
        AppMethodBeat.o(7672);
        return null;
    }

    public Object getValueFromLinkedMap(int i) {
        int i2 = 0;
        AppMethodBeat.i(7676, false);
        for (Object obj : this.urlsMap.keySet()) {
            if (i2 == i) {
                Object obj2 = this.urlsMap.get(obj);
                AppMethodBeat.o(7676);
                return obj2;
            }
            i2++;
        }
        AppMethodBeat.o(7676);
        return null;
    }

    public boolean containsTheUrl(Object obj) {
        AppMethodBeat.i(7678, false);
        if (obj != null) {
            boolean containsValue = this.urlsMap.containsValue(obj);
            AppMethodBeat.o(7678);
            return containsValue;
        }
        AppMethodBeat.o(7678);
        return false;
    }

    public MediaSource cloneMe() {
        AppMethodBeat.i(7683, false);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(this.urlsMap);
        MediaSource mediaSource = new MediaSource(linkedHashMap, this.title);
        AppMethodBeat.o(7683);
        return mediaSource;
    }
}
