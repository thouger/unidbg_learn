package io.flutter.view;

import io.flutter.util.Predicate;
import io.flutter.view.AccessibilityBridge;

/* compiled from: lambda */
/* renamed from: io.flutter.view.-$$Lambda$AccessibilityBridge$JBDpWWlF54eN0gel8Jn1hY7yOmE  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AccessibilityBridge$JBDpWWlF54eN0gel8Jn1hY7yOmE implements Predicate {
    private final /* synthetic */ AccessibilityBridge.SemanticsNode f$0;

    public /* synthetic */ $$Lambda$AccessibilityBridge$JBDpWWlF54eN0gel8Jn1hY7yOmE(AccessibilityBridge.SemanticsNode semanticsNode) {
        this.f$0 = semanticsNode;
    }

    @Override // io.flutter.util.Predicate
    public final boolean test(Object obj) {
        return AccessibilityBridge.lambda$shouldSetCollectionInfo$0(this.f$0, (AccessibilityBridge.SemanticsNode) obj);
    }
}
