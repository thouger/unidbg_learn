package com.hjq.toast;

import android.app.Application;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BaseToast extends Toast {
    private TextView mMessageView;

    public BaseToast(Application application) {
        super(application);
    }

    @Override // android.widget.Toast
    public void setView(View view) {
        super.setView(view);
        this.mMessageView = getMessageView(view);
    }

    @Override // android.widget.Toast
    public void setText(CharSequence charSequence) {
        this.mMessageView.setText(charSequence);
    }

    private static TextView getMessageView(View view) {
        TextView findTextView;
        if (view instanceof TextView) {
            return (TextView) view;
        }
        if (view.findViewById(16908299) instanceof TextView) {
            return (TextView) view.findViewById(16908299);
        }
        if ((view instanceof ViewGroup) && (findTextView = findTextView((ViewGroup) view)) != null) {
            return findTextView;
        }
        throw new IllegalArgumentException("The layout must contain a TextView");
    }

    private static TextView findTextView(ViewGroup viewGroup) {
        TextView findTextView;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof TextView) {
                return (TextView) childAt;
            }
            if ((childAt instanceof ViewGroup) && (findTextView = findTextView((ViewGroup) childAt)) != null) {
                return findTextView;
            }
        }
        return null;
    }
}
