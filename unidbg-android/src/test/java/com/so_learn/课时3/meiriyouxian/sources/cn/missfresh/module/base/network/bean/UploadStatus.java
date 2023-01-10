package cn.missfresh.module.base.network.bean;

import com.bangcle.andjni.JniLib;
import java.util.List;

public class UploadStatus {
    public int count;
    public long countLenght;
    public int currentIndex;
    public long currentLenght;
    public List<String> urlList;

    public UploadStatus() {
        JniLib.cV(this, 96);
    }
}
