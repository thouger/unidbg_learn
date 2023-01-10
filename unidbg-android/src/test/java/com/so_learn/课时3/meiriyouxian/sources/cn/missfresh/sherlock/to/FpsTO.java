package cn.missfresh.sherlock.to;

import cn.missfresh.sherlock.bo.DropBO;
import java.io.Serializable;
import java.util.List;

public class FpsTO extends CommonTO implements Serializable {
    private static final long serialVersionUID = 9094230483229137582L;
    private String activity;
    private float cpu;
    private List<DropBO> drops;
    private float fps;

    public String getActivity() {
        return this.activity;
    }

    public float getCpu() {
        return this.cpu;
    }

    public List<DropBO> getDrops() {
        return this.drops;
    }

    public double getFps() {
        return (double) this.fps;
    }

    public void setActivity(String str) {
        this.activity = str;
    }

    public void setCpu(float f) {
        this.cpu = f;
    }

    public void setDrops(List<DropBO> list) {
        this.drops = list;
    }

    public void setFps(float f) {
        this.fps = f;
    }

    @Override // java.lang.Object
    public String toString() {
        return " \n# SherlockFps\n* activity:\t" + this.activity + "\n* fps:\t" + this.fps + "\n* cpu:\t" + this.cpu + "\n";
    }
}
