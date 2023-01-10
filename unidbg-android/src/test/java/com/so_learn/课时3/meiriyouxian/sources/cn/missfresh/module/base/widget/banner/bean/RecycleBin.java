package cn.missfresh.module.base.widget.banner.bean;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class RecycleBin {
    private int[] activeViewTypes = new int[0];
    private View[] activeViews = new View[0];
    private SparseArray<View> currentScrapViews;
    private SparseArray<View>[] scrapViews;
    private int viewTypeCount;

    /* access modifiers changed from: protected */
    public boolean shouldRecycleViewType(int i) {
        return i >= 0;
    }

    public RecycleBin() {
        AppMethodBeat.i(23929, false);
        AppMethodBeat.o(23929);
    }

    public void setViewTypeCount(int i) {
        AppMethodBeat.i(23930, false);
        if (i >= 1) {
            SparseArray<View>[] sparseArrayArr = new SparseArray[i];
            for (int i2 = 0; i2 < i; i2++) {
                sparseArrayArr[i2] = new SparseArray<>();
            }
            this.viewTypeCount = i;
            this.currentScrapViews = sparseArrayArr[0];
            this.scrapViews = sparseArrayArr;
            AppMethodBeat.o(23930);
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Can't have a viewTypeCount < 1");
        AppMethodBeat.o(23930);
        throw illegalArgumentException;
    }

    public View getScrapView(int i, int i2) {
        AppMethodBeat.i(23931, false);
        if (this.viewTypeCount == 1) {
            View retrieveFromScrap = retrieveFromScrap(this.currentScrapViews, i);
            AppMethodBeat.o(23931);
            return retrieveFromScrap;
        }
        if (i2 >= 0) {
            SparseArray<View>[] sparseArrayArr = this.scrapViews;
            if (i2 < sparseArrayArr.length) {
                View retrieveFromScrap2 = retrieveFromScrap(sparseArrayArr[i2], i);
                AppMethodBeat.o(23931);
                return retrieveFromScrap2;
            }
        }
        AppMethodBeat.o(23931);
        return null;
    }

    public void addScrapView(View view, int i, int i2) {
        AppMethodBeat.i(23932, false);
        if (this.viewTypeCount == 1) {
            this.currentScrapViews.put(i, view);
        } else {
            this.scrapViews[i2].put(i, view);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            view.setAccessibilityDelegate(null);
        }
        AppMethodBeat.o(23932);
    }

    public void scrapActiveViews() {
        boolean z = false;
        AppMethodBeat.i(23933, false);
        View[] viewArr = this.activeViews;
        int[] iArr = this.activeViewTypes;
        if (this.viewTypeCount > 1) {
            z = true;
        }
        SparseArray<View> sparseArray = this.currentScrapViews;
        for (int length = viewArr.length - 1; length >= 0; length--) {
            View view = viewArr[length];
            if (view != null) {
                int i = iArr[length];
                viewArr[length] = null;
                iArr[length] = -1;
                if (shouldRecycleViewType(i)) {
                    if (z) {
                        sparseArray = this.scrapViews[i];
                    }
                    sparseArray.put(length, view);
                    if (Build.VERSION.SDK_INT >= 14) {
                        view.setAccessibilityDelegate(null);
                    }
                }
            }
        }
        pruneScrapViews();
        AppMethodBeat.o(23933);
    }

    private void pruneScrapViews() {
        AppMethodBeat.i(23934, false);
        int length = this.activeViews.length;
        int i = this.viewTypeCount;
        SparseArray<View>[] sparseArrayArr = this.scrapViews;
        for (int i2 = 0; i2 < i; i2++) {
            SparseArray<View> sparseArray = sparseArrayArr[i2];
            int size = sparseArray.size();
            int i3 = size - length;
            int i4 = size - 1;
            int i5 = 0;
            while (i5 < i3) {
                sparseArray.remove(sparseArray.keyAt(i4));
                i5++;
                i4--;
            }
        }
        AppMethodBeat.o(23934);
    }

    static View retrieveFromScrap(SparseArray<View> sparseArray, int i) {
        AppMethodBeat.i(23935, false);
        int size = sparseArray.size();
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                int keyAt = sparseArray.keyAt(i2);
                View view = (View) sparseArray.get(keyAt);
                if (keyAt == i) {
                    sparseArray.remove(keyAt);
                    AppMethodBeat.o(23935);
                    return view;
                }
            }
            int i3 = size - 1;
            View view2 = (View) sparseArray.valueAt(i3);
            sparseArray.remove(sparseArray.keyAt(i3));
            AppMethodBeat.o(23935);
            return view2;
        }
        AppMethodBeat.o(23935);
        return null;
    }
}
