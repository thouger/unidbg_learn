package com.sobot.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.bangcle.andjni.JniLib;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.adapter.SobotImageScaleAdapter;
import com.sobot.chat.api.model.ZhiChiUploadAppFileModelResult;
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.dialog.c;
import com.sobot.chat.widget.photoview.HackyViewPager;
import java.util.ArrayList;

public class SobotPhotoListActivity extends SobotBaseActivity {
    protected c a;
    private ArrayList<ZhiChiUploadAppFileModelResult> d;
    private int e;
    private HackyViewPager f;
    private SobotImageScaleAdapter g;
    private View.OnClickListener h = new AnonymousClass2(this);

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_photo_list");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        if (bundle == null) {
            Intent intent = getIntent();
            this.d = (ArrayList) intent.getSerializableExtra("sobot_keytype_pic_list");
            this.e = intent.getIntExtra("sobot_keytype_pic_list_current_item", 0);
            return;
        }
        this.d = (ArrayList) bundle.getSerializable("sobot_keytype_pic_list");
        this.e = bundle.getInt("sobot_keytype_pic_list_current_item");
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("sobot_keytype_pic_list_current_item", this.e);
        bundle.putSerializable("sobot_keytype_pic_list", this.d);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
        this.f = (HackyViewPager) findViewById(a("sobot_viewPager"));
        this.g = new SobotImageScaleAdapter(this, this.d);
        this.f.setAdapter(this.g);
        this.f.setCurrentItem(this.e);
        this.f.addOnPageChangeListener(new AnonymousClass1(this));
    }

    /* renamed from: com.sobot.chat.activity.SobotPhotoListActivity$1  reason: invalid class name */
    class AnonymousClass1 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ SobotPhotoListActivity a;

        AnonymousClass1(SobotPhotoListActivity sobotPhotoListActivity) {
            JniLib.cV(this, sobotPhotoListActivity, 1018);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            this.a.a(i);
        }
    }

    public void a(int i) {
        setTitle((i + 1) + NotificationIconUtil.SPLIT_CHAR + this.d.size());
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        a(b("sobot_pic_delete_selector"), "", true);
        a(this.e);
        b(b("sobot_btn_back_selector"), "", true);
    }

    /* renamed from: com.sobot.chat.activity.SobotPhotoListActivity$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ SobotPhotoListActivity a;

        AnonymousClass2(SobotPhotoListActivity sobotPhotoListActivity) {
            JniLib.cV(this, sobotPhotoListActivity, 1019);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.a.dismiss();
            if (view.getId() == this.a.a("btn_pick_photo")) {
                Intent intent = new Intent();
                intent.putExtra("sobot_keytype_pic_list", this.a.d);
                this.a.setResult(302, intent);
                this.a.d.remove(this.a.f.getCurrentItem());
                if (this.a.d.size() == 0) {
                    this.a.finish();
                    return;
                }
                SobotPhotoListActivity sobotPhotoListActivity = this.a;
                sobotPhotoListActivity.g = new SobotImageScaleAdapter(sobotPhotoListActivity, sobotPhotoListActivity.d);
                this.a.f.setAdapter(this.a.g);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b(View view) {
        this.a = new c(this, q.f(this, "sobot_do_you_delete_picture"), this.h);
        this.a.show();
    }
}
