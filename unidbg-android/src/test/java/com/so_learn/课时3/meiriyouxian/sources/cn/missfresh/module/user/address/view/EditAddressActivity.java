package cn.missfresh.module.user.address.view;

import android.content.Intent;
import android.os.Bundle;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.c.a;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.utils.k;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.view.EditAddressModuleFragment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.umeng.analytics.pro.ai;
import java.util.HashMap;

public class EditAddressActivity extends BaseFragmentActivity implements EditAddressModuleFragment.a {
    private EditAddressModuleFragment a;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.EditAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        EditAddressActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(4933, false);
        super.onCreate(bundle);
        setContentView(R.layout.user_activity_edit_address);
        a();
        r();
        AppMethodBeat.o(4933);
    }

    private void a() {
        AppMethodBeat.i(4935, false);
        this.e.setVisibility(0);
        this.e.setCenterVisibility(0);
        this.e.setLeftButtonVisibility(0);
        AppMethodBeat.o(4935);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [cn.missfresh.module.user.address.view.EditAddressModuleFragment, androidx.fragment.app.Fragment] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void r() {
        /*
            r12 = this;
            r0 = 4938(0x134a, float:6.92E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            android.content.Intent r2 = r12.getIntent()
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            if (r2 == 0) goto L_0x0092
            int r4 = cn.missfresh.module.base.common.c.a.h
            java.lang.String r5 = "is_edit_mode"
            int r4 = r2.getIntExtra(r5, r4)
            java.lang.String r6 = "have_address"
            boolean r7 = r2.getBooleanExtra(r6, r1)
            java.lang.String r8 = "order_id"
            java.lang.String r9 = r2.getStringExtra(r8)
            java.lang.String r10 = "user_address"
            android.os.Parcelable r11 = r2.getParcelableExtra(r10)
            cn.missfresh.module.base.bean.UserAddress r11 = (cn.missfresh.module.base.bean.UserAddress) r11
            r3.putInt(r5, r4)
            r3.putString(r8, r9)
            r3.putParcelable(r10, r11)
            r3.putBoolean(r6, r7)
            java.lang.String r5 = "lat_lng"
            java.lang.String r6 = r2.getStringExtra(r5)
            r3.putString(r5, r6)
            java.lang.String r5 = "match_scope"
            int r2 = r2.getIntExtra(r5, r1)
            r3.putInt(r5, r2)
            int r2 = cn.missfresh.module.base.common.c.a.h
            r5 = 1
            java.lang.String r6 = "status"
            r7 = 2
            java.lang.String r8 = "page_view"
            if (r4 == r2) goto L_0x0078
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r2[r1] = r6
            java.lang.String r1 = "2"
            r2[r5] = r1
            cn.missfresh.module.base.datastatistics.StatisticsManager.t(r8, r2)
            cn.missfresh.module.base.support.view.TitleBar r1 = r12.e
            java.lang.String r2 = "\u7f16\u8f91\u6536\u8d27\u5730\u5740"
            r1.setCenterTxt(r2)
            java.lang.String r1 = "edit"
            r12.c(r1)
            goto L_0x0092
        L_0x0078:
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r2[r1] = r6
            java.lang.String r1 = "1"
            r2[r5] = r1
            cn.missfresh.module.base.datastatistics.StatisticsManager.t(r8, r2)
            cn.missfresh.module.base.support.view.TitleBar r1 = r12.e
            java.lang.String r2 = "\u65b0\u589e\u5730\u5740"
            r1.setCenterTxt(r2)
            java.lang.String r1 = "add"
            r12.c(r1)
        L_0x0092:
            cn.missfresh.module.user.address.view.EditAddressModuleFragment r1 = r12.a
            if (r1 != 0) goto L_0x00a2
            cn.missfresh.module.user.address.view.EditAddressModuleFragment r1 = new cn.missfresh.module.user.address.view.EditAddressModuleFragment
            r1.<init>()
            r12.a = r1
            cn.missfresh.module.user.address.view.EditAddressModuleFragment r1 = r12.a
            r1.setArguments(r3)
        L_0x00a2:
            androidx.fragment.app.FragmentManager r1 = r12.getSupportFragmentManager()
            androidx.fragment.app.FragmentTransaction r1 = r1.beginTransaction()
            int r2 = cn.missfresh.module.user.R.id.fl_edit_address_container
            cn.missfresh.module.user.address.view.EditAddressModuleFragment r3 = r12.a
            r1.replace(r2, r3)
            r1.commit()
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.user.address.view.EditAddressActivity.r():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.address.view.EditAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void c(String str) {
        AppMethodBeat.i(4940, false);
        HashMap hashMap = new HashMap();
        hashMap.put("page_type", str);
        StatisticsManager.f(this, "page_view", hashMap);
        AppMethodBeat.o(4940);
    }

    public void a(UserAddress userAddress) {
        AppMethodBeat.i(4942, false);
        Intent intent = new Intent();
        intent.putExtra("add_address", userAddress);
        setResult(a.e, intent);
        finish();
        AppMethodBeat.o(4942);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        AppMethodBeat.i(4944, false);
        super.onRequestPermissionsResult(i, strArr, iArr);
        AppMethodBeat.o(4944);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void J_() {
        AppMethodBeat.i(4946, false);
        super.J_();
        EditAddressModuleFragment editAddressModuleFragment = this.a;
        if (editAddressModuleFragment != null) {
            b(editAddressModuleFragment.g());
        }
        AppMethodBeat.o(4946);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x006c: APUT  (r2v1 java.lang.Object[]), (9 ??[int, float, short, byte, char]), (r3v13 java.lang.String) */
    private void b(UserAddress userAddress) {
        AppMethodBeat.i(4949, false);
        if (userAddress == null) {
            AppMethodBeat.o(4949);
            return;
        }
        Object[] objArr = new Object[20];
        objArr[0] = ai.e;
        objArr[1] = "exit_save_address";
        objArr[2] = "poi_id";
        objArr[3] = userAddress.poi_id;
        objArr[4] = "user_address_id";
        objArr[5] = Integer.valueOf(userAddress.id);
        objArr[6] = "poi_name";
        objArr[7] = userAddress.address_1;
        objArr[8] = "user_lat";
        String str = "";
        objArr[9] = !b.a(c.q().u()) ? k.a(c.q().u().getBytes()) : str;
        objArr[10] = "user_lan";
        objArr[11] = !b.a(c.q().v()) ? k.a(c.q().v().getBytes()) : str;
        objArr[12] = "pos_lat";
        objArr[13] = (userAddress.getLatAndLng() == null || userAddress.getLatAndLng().lat == null) ? str : k.a(userAddress.getLatAndLng().lat.getBytes());
        objArr[14] = "pos_lan";
        if (!(userAddress.getLatAndLng() == null || userAddress.getLatAndLng().lng == null)) {
            str = k.a(userAddress.getLatAndLng().lng.getBytes());
        }
        objArr[15] = str;
        objArr[16] = "street_number";
        objArr[17] = userAddress.address_2;
        objArr[18] = "tag";
        objArr[19] = userAddress.tag;
        StatisticsManager.t("click_exit_save_address", objArr);
        AppMethodBeat.o(4949);
    }
}
