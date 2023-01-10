package com.sobot.chat.conversation;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.sobot.chat.activity.base.SobotBaseActivity;

public class SobotChatActivity extends SobotBaseActivity {
    Bundle a;
    SobotChatFragment d;
    SobotChatFSFragment e;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_chat_act");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        if (bundle == null) {
            this.a = getIntent().getBundleExtra("sobot_bundle_information");
        } else {
            this.a = bundle.getBundle("sobot_bundle_information");
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBundle("sobot_bundle_information", this.a);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        if (s()) {
            this.e = (SobotChatFSFragment) getSupportFragmentManager().findFragmentById(a("sobot_contentFrame"));
            if (this.e == null) {
                this.e = SobotChatFSFragment.a(this.a);
                a(getSupportFragmentManager(), this.e, a("sobot_contentFrame"));
                return;
            }
            return;
        }
        this.d = (SobotChatFragment) getSupportFragmentManager().findFragmentById(a("sobot_contentFrame"));
        if (this.d == null) {
            this.d = SobotChatFragment.a(this.a);
            a(getSupportFragmentManager(), this.d, a("sobot_contentFrame"));
        }
    }

    public static void a(FragmentManager fragmentManager, Fragment fragment, int i) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(i, fragment);
        beginTransaction.commit();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (s()) {
            SobotChatFSFragment sobotChatFSFragment = this.e;
            if (sobotChatFSFragment != null) {
                sobotChatFSFragment.J();
            } else {
                super.onBackPressed();
            }
        } else {
            SobotChatFragment sobotChatFragment = this.d;
            if (sobotChatFragment != null) {
                sobotChatFragment.u();
            } else {
                super.onBackPressed();
            }
        }
    }
}
