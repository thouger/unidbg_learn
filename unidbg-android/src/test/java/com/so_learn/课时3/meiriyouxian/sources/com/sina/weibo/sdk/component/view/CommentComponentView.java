package com.sina.weibo.sdk.component.view;

import android.widget.FrameLayout;

public class CommentComponentView extends FrameLayout {
    private a a;

    public static class a {
    }

    public void setCommentParam(a aVar) {
        this.a = aVar;
    }

    public enum Category {
        MOVIE("1001"),
        TRAVEL("1002");
        
        private String mVal;

        private Category(String str) {
            this.mVal = str;
        }

        public String getValue() {
            return this.mVal;
        }
    }
}
