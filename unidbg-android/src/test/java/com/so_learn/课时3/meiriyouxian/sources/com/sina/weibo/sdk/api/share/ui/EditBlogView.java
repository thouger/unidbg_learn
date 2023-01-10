package com.sina.weibo.sdk.api.share.ui;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class EditBlogView extends EditText {
    private boolean canSelectionChanged = true;
    private int count;
    private Context ctx;
    private List<OnSelectionListener> listeners;
    private OnEnterListener mOnEnterListener;

    public interface OnEnterListener {
        void onEnterKey();
    }

    public interface OnSelectionListener {
        void onSelectionChanged(int i, int i2);
    }

    public EditBlogView(Context context) {
        super(context);
        init();
    }

    public EditBlogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public EditBlogView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.ctx = getContext();
        this.listeners = new ArrayList();
    }

    public void setOnSelectionListener(OnSelectionListener onSelectionListener) {
        this.listeners.add(onSelectionListener);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.TextView
    public void onSelectionChanged(int i, int i2) {
        List<OnSelectionListener> list;
        super.onSelectionChanged(i, i2);
        if (!(!this.canSelectionChanged || (list = this.listeners) == null || list.isEmpty())) {
            for (OnSelectionListener onSelectionListener : this.listeners) {
                onSelectionListener.onSelectionChanged(i, i2);
            }
        }
    }

    public void enableSelectionChanged(boolean z) {
        this.canSelectionChanged = z;
    }

    public void setOnEnterListener(OnEnterListener onEnterListener) {
        this.mOnEnterListener = onEnterListener;
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        OnEnterListener onEnterListener;
        if (i == 66 && (onEnterListener = this.mOnEnterListener) != null) {
            onEnterListener.onEnterKey();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public int correctPosition(int i) {
        Object[] spans;
        if (i == -1) {
            return i;
        }
        Editable text = getText();
        return (i >= text.length() || (spans = text.getSpans(i, i, ImageSpan.class)) == null || spans.length == 0 || i == text.getSpanStart(spans[0])) ? i : text.getSpanEnd(spans[0]);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return new AnonymousClass1(super.onCreateInputConnection(editorInfo), false);
    }

    /* renamed from: com.sina.weibo.sdk.api.share.ui.EditBlogView$1  reason: invalid class name */
    class AnonymousClass1 extends InputConnectionWrapper {
        AnonymousClass1(InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean commitText(CharSequence charSequence, int i) {
            Editable editableText = EditBlogView.this.getEditableText();
            new String(editableText.toString());
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            if (!(selectionStart == -1 || selectionEnd == -1)) {
                int correctPosition = EditBlogView.this.correctPosition(selectionStart);
                int correctPosition2 = EditBlogView.this.correctPosition(selectionEnd);
                if (correctPosition > correctPosition2) {
                    correctPosition2 = correctPosition;
                    correctPosition = correctPosition2;
                }
                if (!(correctPosition == selectionStart && correctPosition2 == selectionEnd)) {
                    Selection.setSelection(editableText, correctPosition, correctPosition2);
                }
                if (correctPosition != correctPosition2) {
                    EditBlogView.this.getText().delete(correctPosition, correctPosition2);
                }
            }
            return super.commitText(charSequence, i);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean setComposingText(CharSequence charSequence, int i) {
            Editable editableText = EditBlogView.this.getEditableText();
            new String(editableText.toString());
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            if (!(selectionStart == -1 || selectionEnd == -1)) {
                int correctPosition = EditBlogView.this.correctPosition(selectionStart);
                int correctPosition2 = EditBlogView.this.correctPosition(selectionEnd);
                if (correctPosition > correctPosition2) {
                    correctPosition2 = correctPosition;
                    correctPosition = correctPosition2;
                }
                if (!(correctPosition == selectionStart && correctPosition2 == selectionEnd)) {
                    Selection.setSelection(editableText, correctPosition, correctPosition2);
                }
                if (correctPosition != correctPosition2) {
                    EditBlogView.this.getText().delete(correctPosition, correctPosition2);
                }
            }
            return super.setComposingText(charSequence, i);
        }
    }
}
