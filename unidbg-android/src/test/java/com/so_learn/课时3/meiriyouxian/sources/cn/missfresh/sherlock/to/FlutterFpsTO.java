package cn.missfresh.sherlock.to;

public class FlutterFpsTO {
    private int count;
    private double fps;

    public int getCount() {
        return this.count;
    }

    public double getFps() {
        return this.fps;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setFps(double d) {
        this.fps = d;
    }

    public String toString() {
        return " \n# SherlockFps\n* fps:\t" + this.fps + "\n* count:\t" + this.count + "\n";
    }
}
