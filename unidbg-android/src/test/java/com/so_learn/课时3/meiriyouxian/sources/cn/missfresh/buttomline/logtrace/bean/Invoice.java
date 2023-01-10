package cn.missfresh.buttomline.logtrace.bean;

import java.io.File;
import java.util.List;
import java.util.Map;
import okhttp3.Response;

public class Invoice {
    public String content;
    public File file;
    public List<List<Map<String, String>>> lists;
    public Response response;
    public String tag;

    public Invoice(File file, String str) {
        this.file = file;
        this.tag = str;
    }
}
