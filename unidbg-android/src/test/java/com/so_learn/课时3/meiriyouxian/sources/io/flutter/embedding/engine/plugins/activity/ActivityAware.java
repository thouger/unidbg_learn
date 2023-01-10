package io.flutter.embedding.engine.plugins.activity;

public interface ActivityAware {
    void onAttachedToActivity(ActivityPluginBinding activityPluginBinding);

    void onDetachedFromActivity();

    void onDetachedFromActivityForConfigChanges();

    void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding);
}
