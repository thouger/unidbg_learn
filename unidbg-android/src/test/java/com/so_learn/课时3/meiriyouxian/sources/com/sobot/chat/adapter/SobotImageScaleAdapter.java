package com.sobot.chat.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.sobot.chat.adapter.base.SobotBasePagerAdapter;
import com.sobot.chat.api.model.ZhiChiUploadAppFileModelResult;
import com.sobot.chat.utils.t;
import com.sobot.chat.widget.photoview.PhotoView;
import java.util.ArrayList;

public class SobotImageScaleAdapter extends SobotBasePagerAdapter<ZhiChiUploadAppFileModelResult> {
    public SobotImageScaleAdapter(Context context, ArrayList<ZhiChiUploadAppFileModelResult> arrayList) {
        super(context, arrayList);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        PhotoView photoView = new PhotoView(this.b);
        if (!TextUtils.isEmpty(((ZhiChiUploadAppFileModelResult) this.a.get(i)).getFileLocalPath())) {
            t.a(this.b, ((ZhiChiUploadAppFileModelResult) this.a.get(i)).getFileLocalPath(), photoView);
        } else {
            t.a(this.b, ((ZhiChiUploadAppFileModelResult) this.a.get(i)).getFileUrl(), photoView);
        }
        viewGroup.addView(photoView);
        return photoView;
    }
}
