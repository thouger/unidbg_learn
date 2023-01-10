package io.flutter.embedding.engine.plugins.lifecycle;

import androidx.lifecycle.Lifecycle;

public class HiddenLifecycleReference {
    private final Lifecycle lifecycle;

    public HiddenLifecycleReference(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public Lifecycle getLifecycle() {
        return this.lifecycle;
    }
}
