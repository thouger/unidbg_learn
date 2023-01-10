package com.sobot.chat.application;

import android.app.Activity;
import android.app.Application;
import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {
    private static MyApplication instance;
    private List<Activity> activityList = new LinkedList();

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        this.activityList.add(activity);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
    }

    public void exit() {
        for (Activity activity : this.activityList) {
            activity.finish();
        }
    }

    public Activity getLastActivity() {
        List<Activity> list = this.activityList;
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<Activity> list2 = this.activityList;
        return list2.get(list2.size() - 1);
    }

    public void deleteActivity(Activity activity) {
        this.activityList.remove(activity);
    }
}
