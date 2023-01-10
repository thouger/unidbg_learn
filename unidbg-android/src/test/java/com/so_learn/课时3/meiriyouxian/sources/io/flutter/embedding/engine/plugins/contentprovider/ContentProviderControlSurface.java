package io.flutter.embedding.engine.plugins.contentprovider;

import android.content.ContentProvider;
import androidx.lifecycle.Lifecycle;

public interface ContentProviderControlSurface {
    void attachToContentProvider(ContentProvider contentProvider, Lifecycle lifecycle);

    void detachFromContentProvider();
}
