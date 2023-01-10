package cn.missfresh.module.base.common.config;

import androidx.exifinterface.media.ExifInterface;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* compiled from: MFExifInterfaceImageHeaderParser */
public final class g implements ImageHeaderParser {
    @Override // com.bumptech.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType getType(InputStream inputStream) throws IOException {
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType getType(ByteBuffer byteBuffer) throws IOException {
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public int getOrientation(InputStream inputStream, ArrayPool arrayPool) throws IOException {
        AppMethodBeat.i(11171, false);
        int attributeInt = new ExifInterface(inputStream).getAttributeInt("Orientation", 1);
        if (attributeInt == 0) {
            AppMethodBeat.o(11171);
            return -1;
        }
        AppMethodBeat.o(11171);
        return attributeInt;
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public int getOrientation(ByteBuffer byteBuffer, ArrayPool arrayPool) throws IOException {
        AppMethodBeat.i(11172, false);
        int orientation = getOrientation(ByteBufferUtil.toStream(byteBuffer), arrayPool);
        AppMethodBeat.o(11172);
        return orientation;
    }
}
