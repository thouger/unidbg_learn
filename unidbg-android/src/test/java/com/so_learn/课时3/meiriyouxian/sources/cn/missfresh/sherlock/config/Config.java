package cn.missfresh.sherlock.config;

import cn.missfresh.sherlock.bo.ConfigBO;
import cn.missfresh.sherlock.to.DeviceLevel;
import cn.missfresh.sherlock.tool.e;
import cn.missfresh.sherlock.tool.j;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config implements Serializable {
    private static final long serialVersionUID = -9037891876258772303L;
    public int anrTime;
    public int apiLevel;
    public int crashSwitch;
    public int diskCacheCount;
    public int fileSize;
    public int flutterSwitch;
    @SerializedName("errorSwitch")
    public int httpSwitch;
    public List<DeviceLevel> mDeviceLevels;
    public boolean mLogSwitch;
    public List<ReportStrategy> mReportStrategies;
    public int maxReportCount;
    public int memoryCacheCount;
    public int methodLevel;
    public int resouceSwitch;
    public int screenshotSwitch;
    @SerializedName("apmSwitch")
    public int sherlockSwitch;
    public int shotNetworkCount;
    public int shotNetworkSwitch;
    public int slowFunctionSwitch;
    public int slowFunctionTime;
    public int timeStep;
    public int traceSwitch;
    public List<String> whiteList;

    public static class b {
        private static final Config a = new Config();
    }

    public static Config getInstance() {
        return b.a;
    }

    public boolean enableCrashSwitch() {
        return enableSherlock() && this.crashSwitch == 1;
    }

    public boolean enableFpsSwitch() {
        return this.traceSwitch == 1;
    }

    public boolean enableHttpSwitch() {
        return enableSherlock() && this.httpSwitch == 1;
    }

    public boolean enableNetowrkSwitch() {
        return this.shotNetworkSwitch == 1;
    }

    public boolean enableScreenSwitch() {
        return enableSherlock() && this.screenshotSwitch == 1;
    }

    public boolean enableSherlock() {
        return this.sherlockSwitch == 1;
    }

    public boolean enableSlowFunctionSwitch() {
        return this.traceSwitch == 1 && this.slowFunctionSwitch == 1;
    }

    public boolean enableTraceSwitch() {
        return enableSherlock() && this.traceSwitch == 1;
    }

    public int getShotNetowrkCount() {
        return this.shotNetworkCount;
    }

    public void update(List<ConfigBO.ConfigKeyValue> list) {
        if (!(list == null || list.isEmpty())) {
            for (ConfigBO.ConfigKeyValue configKeyValue : list) {
                if (configKeyValue.getName().equals("apmSwitch")) {
                    this.sherlockSwitch = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("errorSwitch")) {
                    this.httpSwitch = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("crashSwitch")) {
                    this.crashSwitch = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("screenshotSwitch")) {
                    this.screenshotSwitch = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("shotNetworkSwitch")) {
                    this.shotNetworkSwitch = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("memoryCacheCount")) {
                    this.memoryCacheCount = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("diskCacheCount")) {
                    this.diskCacheCount = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("maxReportCount")) {
                    this.maxReportCount = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("timeStep")) {
                    this.timeStep = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("fileSize")) {
                    this.fileSize = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("shotNetworkCount")) {
                    this.shotNetworkCount = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("whiteList")) {
                    this.whiteList = Arrays.asList((Object[]) e.a.fromJson(configKeyValue.getValue(), (Class<Object>) String[].class));
                } else if (configKeyValue.getName().equals("mLogSwitch")) {
                    this.mLogSwitch = Boolean.parseBoolean(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("mReportStrategies")) {
                    this.mReportStrategies = Arrays.asList((Object[]) e.a.fromJson(configKeyValue.getValue(), (Class<Object>) ReportStrategy[].class));
                } else if (configKeyValue.getName().equals("traceSwitch")) {
                    this.traceSwitch = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("slowFunctionSwitch")) {
                    this.slowFunctionSwitch = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("anrTime")) {
                    this.anrTime = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("slowFunctionTime")) {
                    this.slowFunctionTime = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("resouceSwitch")) {
                    this.resouceSwitch = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("methodLevel")) {
                    this.methodLevel = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("apiLevel")) {
                    this.apiLevel = Integer.parseInt(configKeyValue.getValue());
                } else if (configKeyValue.getName().equals("deviceLevel")) {
                    this.mDeviceLevels = Arrays.asList((Object[]) e.a.fromJson(configKeyValue.getValue(), (Class<Object>) DeviceLevel[].class));
                } else if (configKeyValue.getName().equals("flutterSwith")) {
                    this.flutterSwitch = Integer.parseInt(configKeyValue.getValue());
                }
            }
            j.b("SherlockHelper", "update config success");
            cn.missfresh.sherlock.e.a();
        }
    }

    private Config() {
        this.sherlockSwitch = 0;
        this.httpSwitch = 0;
        this.crashSwitch = 0;
        this.screenshotSwitch = 0;
        this.shotNetworkSwitch = 1;
        this.memoryCacheCount = 10;
        this.diskCacheCount = 10;
        this.maxReportCount = 10;
        this.timeStep = 60;
        this.fileSize = 30;
        this.shotNetworkCount = 5;
        this.whiteList = new ArrayList();
        this.mLogSwitch = false;
        this.traceSwitch = 0;
        this.slowFunctionSwitch = 0;
        this.anrTime = 5000;
        this.slowFunctionTime = 700;
        this.resouceSwitch = 1;
        this.methodLevel = 500;
        this.apiLevel = 100;
        this.flutterSwitch = 0;
        this.mReportStrategies = new ArrayList();
        this.mReportStrategies.add(ReportStrategy.START);
        this.mReportStrategies.add(ReportStrategy.BACKGROUD);
        this.mReportStrategies.add(ReportStrategy.CACHE);
    }

    public void update(Config config) {
        if (config != null) {
            this.sherlockSwitch = config.sherlockSwitch;
            this.httpSwitch = config.httpSwitch;
            this.crashSwitch = config.crashSwitch;
            this.screenshotSwitch = config.screenshotSwitch;
            this.mLogSwitch = config.mLogSwitch;
            this.memoryCacheCount = config.memoryCacheCount;
            this.diskCacheCount = config.diskCacheCount;
            this.maxReportCount = config.maxReportCount;
            this.timeStep = config.timeStep;
            this.fileSize = config.fileSize;
            this.mReportStrategies.clear();
            this.mReportStrategies.addAll(config.mReportStrategies);
            this.whiteList.clear();
            this.whiteList.addAll(config.whiteList);
            this.shotNetworkCount = config.shotNetworkCount;
            this.shotNetworkSwitch = config.shotNetworkSwitch;
            this.traceSwitch = config.traceSwitch;
            this.slowFunctionSwitch = config.slowFunctionSwitch;
            this.anrTime = config.anrTime;
            this.slowFunctionTime = config.slowFunctionTime;
            this.resouceSwitch = config.resouceSwitch;
            this.methodLevel = config.methodLevel;
            this.apiLevel = config.apiLevel;
            this.mDeviceLevels.clear();
            this.mDeviceLevels.addAll(config.mDeviceLevels);
        }
    }
}
