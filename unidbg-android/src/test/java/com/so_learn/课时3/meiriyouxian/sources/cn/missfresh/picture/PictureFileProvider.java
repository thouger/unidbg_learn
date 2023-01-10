package cn.missfresh.picture;

import android.content.Context;
import androidx.core.content.FileProvider;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class PictureFileProvider extends FileProvider {
    public static final String a(Context context) {
        AppMethodBeat.i(18087, false);
        String str = context.getPackageName() + ".fileprovider";
        AppMethodBeat.o(18087);
        return str;
    }
}
