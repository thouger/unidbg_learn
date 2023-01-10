package com.sobot.chat.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.TtmlUtils;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.sobot.chat.activity.SobotChooseCityActivity;
import com.sobot.chat.activity.SobotCusFieldActivity;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.model.SobotCusFieldConfig;
import com.sobot.chat.api.model.SobotFieldModel;
import com.sobot.chat.api.model.SobotProvinInfo;
import com.sobot.chat.b;
import com.sobot.chat.b.a;
import com.sobot.chat.utils.ac;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.f;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.widget.kpswitch.util.c;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;

/* compiled from: StCusFieldPresenter */
public class a {
    public static String a(ArrayList<SobotFieldModel> arrayList) {
        ArrayList arrayList2;
        if (arrayList == null || arrayList.size() <= 0) {
            arrayList2 = null;
        } else {
            arrayList2 = new ArrayList();
            for (int i = 0; i < arrayList.size(); i++) {
                HashMap hashMap = new HashMap();
                SobotCusFieldConfig cusFieldConfig = arrayList.get(i).getCusFieldConfig();
                if (cusFieldConfig != null && !ac.a((Object) cusFieldConfig.getFieldId()) && !ac.a((Object) cusFieldConfig.getValue())) {
                    hashMap.put("id", arrayList.get(i).getCusFieldConfig().getFieldId());
                    hashMap.put("value", arrayList.get(i).getCusFieldConfig().getValue());
                    arrayList2.add(hashMap);
                }
            }
        }
        if (arrayList2 == null || arrayList2.size() <= 0) {
            return null;
        }
        return new JSONArray((Collection) arrayList2).toString();
    }

    public static void a(Activity activity, View view, int i) {
        Date date;
        TextView textView = (TextView) view.findViewById(q.a(view.getContext(), "id", "work_order_customer_date_text_click"));
        String charSequence = textView.getText().toString();
        if (!ac.a((Object) charSequence)) {
            date = f.a(charSequence, i == 3 ? f.c : f.a);
        } else {
            date = null;
        }
        c.b(textView);
        f.a(activity, view, textView, date, i == 3 ? 0 : 1);
    }

    public static String a(ArrayList<SobotFieldModel> arrayList, SobotProvinInfo.SobotProvinceModel sobotProvinceModel) {
        HashMap hashMap = new HashMap();
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                SobotCusFieldConfig cusFieldConfig = arrayList.get(i).getCusFieldConfig();
                if (cusFieldConfig != null && !ac.a((Object) cusFieldConfig.getFieldId()) && !ac.a((Object) cusFieldConfig.getValue())) {
                    hashMap.put(arrayList.get(i).getCusFieldConfig().getFieldId(), arrayList.get(i).getCusFieldConfig().getValue());
                }
            }
        }
        if (sobotProvinceModel != null) {
            hashMap.put("proviceId", sobotProvinceModel.provinceId);
            hashMap.put("proviceName", sobotProvinceModel.provinceName);
            hashMap.put("cityId", sobotProvinceModel.cityId);
            hashMap.put("cityName", sobotProvinceModel.cityName);
            hashMap.put("areaId", sobotProvinceModel.areaId);
            hashMap.put("areaName", sobotProvinceModel.areaName);
        }
        if (hashMap.size() > 0) {
            return GsonUtil.map2Json(hashMap);
        }
        return null;
    }

    public static void a(Activity activity, SobotFieldModel sobotFieldModel) {
        a(activity, (Fragment) null, sobotFieldModel);
    }

    public static void a(Activity activity, Fragment fragment, SobotFieldModel sobotFieldModel) {
        SobotCusFieldConfig cusFieldConfig = sobotFieldModel.getCusFieldConfig();
        Intent intent = new Intent(activity, SobotCusFieldActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("fieldType", cusFieldConfig.getFieldType());
        bundle.putSerializable("cusFieldConfig", cusFieldConfig);
        bundle.putSerializable("cusFieldList", sobotFieldModel);
        intent.putExtra("bundle", bundle);
        if (fragment != null) {
            fragment.startActivityForResult(intent, cusFieldConfig.getFieldType());
        } else {
            activity.startActivityForResult(intent, cusFieldConfig.getFieldType());
        }
    }

    public static void a(Activity activity, SobotProvinInfo sobotProvinInfo, SobotFieldModel sobotFieldModel) {
        Intent intent = new Intent(activity, SobotChooseCityActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cusFieldConfig", sobotFieldModel.getCusFieldConfig());
        bundle.putSerializable("sobot_intent_bundle_data_provininfo", sobotProvinInfo);
        SobotCusFieldConfig cusFieldConfig = sobotFieldModel.getCusFieldConfig();
        if (cusFieldConfig != null) {
            bundle.putSerializable("sobot_intent_bundle_data_field_id", cusFieldConfig.getFieldId());
        }
        intent.putExtra("sobot_intent_bundle_data", bundle);
        activity.startActivityForResult(intent, 106);
    }

    public static void a(Context context, Intent intent, ArrayList<SobotFieldModel> arrayList, ViewGroup viewGroup) {
        String str;
        String str2;
        if (!(intent == null || !"CATEGORYSMALL".equals(intent.getStringExtra("CATEGORYSMALL")) || -1 == intent.getIntExtra("fieldType", -1))) {
            String stringExtra = intent.getStringExtra("category_typeName");
            String stringExtra2 = intent.getStringExtra("category_fieldId");
            if (!("null".equals(stringExtra2) || TextUtils.isEmpty(stringExtra2))) {
                String stringExtra3 = intent.getStringExtra("category_typeValue");
                String str3 = Constants.ACCEPT_TIME_SEPARATOR_SP;
                if (arrayList == null || ac.a((Object) stringExtra) || ac.a((Object) stringExtra3)) {
                    if (ac.a((Object) stringExtra3)) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            SobotCusFieldConfig cusFieldConfig = arrayList.get(i).getCusFieldConfig();
                            if (!(cusFieldConfig == null || cusFieldConfig.getFieldId() == null || !cusFieldConfig.getFieldId().equals(stringExtra2))) {
                                cusFieldConfig.setChecked(false);
                                cusFieldConfig.setValue(stringExtra3);
                                cusFieldConfig.setId(stringExtra2);
                            }
                        }
                    }
                    View findViewWithTag = viewGroup.findViewWithTag(stringExtra2);
                    if (findViewWithTag != null) {
                        TextView textView = (TextView) findViewWithTag.findViewById(q.a(context, "id", "work_order_customer_date_text_click"));
                        if (stringExtra.endsWith(str3)) {
                            stringExtra = stringExtra.substring(0, stringExtra.length() - 1);
                        }
                        textView.setText(stringExtra);
                        TextView textView2 = (TextView) findViewWithTag.findViewById(q.a(context, "id", "work_order_customer_field_text_lable"));
                        ((LinearLayout) findViewWithTag.findViewById(q.a(context, "id", "work_order_customer_field_ll"))).setVisibility(8);
                        textView2.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray1")));
                        textView2.setTextSize(14.0f);
                        return;
                    }
                    return;
                }
                int i2 = 0;
                while (i2 < arrayList.size()) {
                    SobotCusFieldConfig cusFieldConfig2 = arrayList.get(i2).getCusFieldConfig();
                    if (cusFieldConfig2 == null || cusFieldConfig2.getFieldId() == null || !cusFieldConfig2.getFieldId().equals(stringExtra2)) {
                        str = str3;
                    } else {
                        cusFieldConfig2.setChecked(true);
                        cusFieldConfig2.setValue(stringExtra3);
                        cusFieldConfig2.setId(stringExtra2);
                        View findViewWithTag2 = viewGroup.findViewWithTag(cusFieldConfig2.getFieldId());
                        TextView textView3 = (TextView) findViewWithTag2.findViewById(q.a(context, "id", "work_order_customer_date_text_click"));
                        if (stringExtra.endsWith(str3)) {
                            str = str3;
                            str2 = stringExtra.substring(0, stringExtra.length() - 1);
                        } else {
                            str = str3;
                            str2 = stringExtra;
                        }
                        textView3.setText(str2);
                        TextView textView4 = (TextView) findViewWithTag2.findViewById(q.a(context, "id", "work_order_customer_field_text_lable"));
                        ((LinearLayout) findViewWithTag2.findViewById(q.a(context, "id", "work_order_customer_field_ll"))).setVisibility(0);
                        textView4.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray2")));
                        textView4.setTextSize(12.0f);
                    }
                    i2++;
                    str3 = str;
                }
            }
        }
    }

    public static String a(Context context, ViewGroup viewGroup, List<SobotFieldModel> list) {
        View findViewWithTag;
        if (!(list == null || list.size() == 0)) {
            for (int i = 0; i < list.size(); i++) {
                if (!(list.get(i).getCusFieldConfig() == null || (findViewWithTag = viewGroup.findViewWithTag(list.get(i).getCusFieldConfig().getFieldId())) == null)) {
                    if (1 == list.get(i).getCusFieldConfig().getFieldType()) {
                        EditText editText = (EditText) findViewWithTag.findViewById(q.a(context, "id", "work_order_customer_field_text_single"));
                        SobotCusFieldConfig cusFieldConfig = list.get(i).getCusFieldConfig();
                        cusFieldConfig.setValue(((Object) editText.getText()) + "");
                        if (ac.c(list.get(i).getCusFieldConfig().getLimitOptions()) && list.get(i).getCusFieldConfig().getLimitOptions().contains("7") && !r.b(editText.getText().toString().trim())) {
                            return list.get(i).getCusFieldConfig().getFieldName() + q.f(context, "sobot_input_type_err_email");
                        } else if (ac.c(list.get(i).getCusFieldConfig().getLimitOptions()) && list.get(i).getCusFieldConfig().getLimitOptions().contains("8") && !r.a(editText.getText().toString().trim())) {
                            return list.get(i).getCusFieldConfig().getFieldName() + q.f(context, "sobot_input_type_err_phone");
                        }
                    } else if (2 == list.get(i).getCusFieldConfig().getFieldType()) {
                        SobotCusFieldConfig cusFieldConfig2 = list.get(i).getCusFieldConfig();
                        cusFieldConfig2.setValue(((Object) ((EditText) findViewWithTag.findViewById(q.a(context, "id", "work_order_customer_field_text_more_content"))).getText()) + "");
                    } else if (4 == list.get(i).getCusFieldConfig().getFieldType() || 3 == list.get(i).getCusFieldConfig().getFieldType()) {
                        SobotCusFieldConfig cusFieldConfig3 = list.get(i).getCusFieldConfig();
                        cusFieldConfig3.setValue(((Object) ((TextView) findViewWithTag.findViewById(q.a(context, "id", "work_order_customer_date_text_click"))).getText()) + "");
                    } else if (5 == list.get(i).getCusFieldConfig().getFieldType()) {
                        EditText editText2 = (EditText) findViewWithTag.findViewById(q.a(context, "id", "work_order_customer_field_text_number"));
                        SobotCusFieldConfig cusFieldConfig4 = list.get(i).getCusFieldConfig();
                        cusFieldConfig4.setValue(((Object) editText2.getText()) + "");
                        if (ac.c(list.get(i).getCusFieldConfig().getLimitOptions()) && list.get(i).getCusFieldConfig().getLimitOptions().contains("3") && !ac.c(editText2.getText().toString().trim())) {
                            return list.get(i).getCusFieldConfig().getFieldName() + q.f(context, "sobot_input_type_err");
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return "";
    }

    public static void a(Activity activity, View view) {
        if (b.a(1) && b.a(4) && view != null) {
            com.sobot.chat.b.b.a().a(activity, new AnonymousClass1(view));
        }
    }

    /* compiled from: StCusFieldPresenter */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.presenter.a$1  reason: invalid class name */
    public static class AnonymousClass1 implements a.AbstractC0139a {
        final /* synthetic */ View a;

        AnonymousClass1(View view) {
            this.a = view;
        }

        @Override // com.sobot.chat.b.a.AbstractC0139a
        public void a(a.b bVar) {
            if (bVar.a) {
                for (Rect rect : bVar.b) {
                    this.a.setPadding(rect.right, this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
                }
            }
        }
    }

    public static void a(Activity activity, Context context, ArrayList<SobotFieldModel> arrayList, ViewGroup viewGroup, com.sobot.chat.listener.b bVar) {
        int i;
        int i2;
        ViewGroup viewGroup2;
        EditText editText;
        Activity activity2 = activity;
        Context context2 = context;
        ArrayList<SobotFieldModel> arrayList2 = arrayList;
        ViewGroup viewGroup3 = viewGroup;
        if (viewGroup3 != null) {
            int i3 = 0;
            viewGroup3.setVisibility(0);
            viewGroup.removeAllViews();
            if (arrayList2 != null && arrayList.size() != 0) {
                arrayList.size();
                int i4 = 0;
                while (i4 < arrayList.size()) {
                    SobotFieldModel sobotFieldModel = arrayList2.get(i4);
                    SobotCusFieldConfig cusFieldConfig = sobotFieldModel.getCusFieldConfig();
                    if (cusFieldConfig == null) {
                        viewGroup2 = viewGroup3;
                        i2 = i3;
                        i = i4;
                    } else {
                        View inflate = View.inflate(context2, q.a(context2, TtmlUtils.TAG_LAYOUT, "sobot_post_msg_cusfield_list_item"), null);
                        inflate.setTag(cusFieldConfig.getFieldId());
                        inflate.findViewById(q.a(context2, "id", "work_order_customer_field_text_bootom_line")).setVisibility(i3);
                        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_more_relativelayout"));
                        TextView textView = (TextView) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_more_text_lable"));
                        TextView textView2 = (TextView) inflate.findViewById(q.a(context2, "id", "work_order_customer_edit_hint_text_label_2"));
                        textView2.setText(q.f(context2, "sobot_please_input"));
                        textView2.setVisibility(8);
                        a(activity2, textView);
                        a(activity2, textView2);
                        EditText editText2 = (EditText) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_text_more_content"));
                        a(activity2, editText2);
                        editText2.setOnFocusChangeListener(new AnonymousClass2(editText2, textView, context2, textView2));
                        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_text"));
                        TextView textView3 = (TextView) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_text_lable"));
                        TextView textView4 = (TextView) inflate.findViewById(q.a(context2, "id", "work_order_customer_edit_hint_text_label"));
                        textView4.setText(q.f(context2, "sobot_please_input"));
                        textView4.setVisibility(8);
                        a(activity2, textView3);
                        a(activity2, textView4);
                        TextView textView5 = (TextView) inflate.findViewById(q.a(context2, "id", "work_order_customer_date_text_click"));
                        i = i4;
                        EditText editText3 = (EditText) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_text_content"));
                        EditText editText4 = (EditText) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_text_number"));
                        EditText editText5 = (EditText) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_text_single"));
                        ImageView imageView = (ImageView) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_text_img"));
                        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(q.a(context2, "id", "work_order_customer_field_ll"));
                        a(activity2, editText4);
                        a(activity2, editText5);
                        a(activity2, editText3);
                        a(activity2, textView5);
                        if (1 == cusFieldConfig.getFieldType()) {
                            linearLayout.setVisibility(8);
                            textView5.setVisibility(8);
                            imageView.setVisibility(8);
                            linearLayout2.setVisibility(0);
                            textView4.setVisibility(0);
                            editText4.setVisibility(8);
                            editText3.setVisibility(8);
                            editText5.setVisibility(0);
                            if (1 == cusFieldConfig.getFillFlag()) {
                                textView3.setText(Html.fromHtml(cusFieldConfig.getFieldName() + "<font color='#f9676f'>&nbsp;*</font>"));
                            } else {
                                textView3.setText(cusFieldConfig.getFieldName());
                            }
                            if (!ac.a((Object) cusFieldConfig.getLimitChar())) {
                                editText5.setMaxLines(Integer.parseInt(cusFieldConfig.getLimitChar()));
                            }
                            editText5.setSingleLine(true);
                            editText5.setMaxEms(11);
                            editText5.setInputType(1);
                            if (!ac.a((Object) cusFieldConfig.getLimitOptions())) {
                                if (cusFieldConfig.getLimitOptions().contains(com.tencent.connect.common.Constants.VIA_SHARE_TYPE_INFO) && !ac.a((Object) cusFieldConfig.getLimitChar())) {
                                    editText5.setMaxLines(Integer.parseInt(cusFieldConfig.getLimitChar()));
                                }
                                if (cusFieldConfig.getLimitOptions().contains("5")) {
                                    editText5.setInputType(2);
                                }
                                if (cusFieldConfig.getLimitOptions().contains("7")) {
                                    editText5.setInputType(32);
                                }
                                if (cusFieldConfig.getLimitOptions().contains("8")) {
                                    editText5.setInputType(3);
                                }
                                editText5.addTextChangedListener(new AnonymousClass3(cusFieldConfig, context2));
                            }
                            editText = editText2;
                        } else {
                            if (2 == cusFieldConfig.getFieldType()) {
                                linearLayout.setVisibility(0);
                                textView2.setVisibility(0);
                                editText = editText2;
                                editText.setVisibility(8);
                                linearLayout2.setVisibility(8);
                                imageView.setVisibility(8);
                                if (1 == cusFieldConfig.getFillFlag()) {
                                    textView.setText(Html.fromHtml(cusFieldConfig.getFieldName() + "<font color='#f9676f'>&nbsp;*</font>"));
                                } else {
                                    textView.setText(cusFieldConfig.getFieldName());
                                }
                                editText.setInputType(1);
                                editText.setInputType(131072);
                                editText.setGravity(48);
                                editText.setSingleLine(false);
                                editText.setHorizontallyScrolling(false);
                                i2 = 0;
                            } else {
                                editText = editText2;
                                if (3 == cusFieldConfig.getFieldType()) {
                                    linearLayout.setVisibility(8);
                                    textView5.setVisibility(0);
                                    linearLayout2.setVisibility(0);
                                    imageView.setVisibility(0);
                                    editText5.setVisibility(8);
                                    editText3.setVisibility(8);
                                    editText4.setVisibility(8);
                                    textView3.setText(cusFieldConfig.getFieldName());
                                    if (1 == cusFieldConfig.getFillFlag()) {
                                        textView3.setText(Html.fromHtml(cusFieldConfig.getFieldName() + "<font color='#f9676f'>&nbsp;*</font>"));
                                    } else {
                                        textView3.setText(cusFieldConfig.getFieldName());
                                    }
                                } else if (4 == cusFieldConfig.getFieldType()) {
                                    linearLayout.setVisibility(8);
                                    textView5.setVisibility(0);
                                    linearLayout2.setVisibility(0);
                                    imageView.setVisibility(0);
                                    editText3.setVisibility(8);
                                    editText4.setVisibility(8);
                                    editText5.setVisibility(8);
                                    if (1 == cusFieldConfig.getFillFlag()) {
                                        textView3.setText(Html.fromHtml(cusFieldConfig.getFieldName() + "<font color='#f9676f'>&nbsp;*</font>"));
                                    } else {
                                        textView3.setText(cusFieldConfig.getFieldName());
                                    }
                                } else if (5 == cusFieldConfig.getFieldType()) {
                                    linearLayout.setVisibility(8);
                                    textView5.setVisibility(8);
                                    linearLayout2.setVisibility(0);
                                    textView4.setVisibility(0);
                                    editText5.setVisibility(8);
                                    imageView.setVisibility(8);
                                    editText3.setVisibility(8);
                                    editText4.setVisibility(0);
                                    editText4.setSingleLine(true);
                                    if (1 == cusFieldConfig.getFillFlag()) {
                                        textView3.setText(Html.fromHtml(cusFieldConfig.getFieldName() + "<font color='#f9676f'>&nbsp;*</font>"));
                                    } else {
                                        textView3.setText(cusFieldConfig.getFieldName());
                                    }
                                    editText4.setInputType(2);
                                    if (ac.a((Object) cusFieldConfig.getLimitOptions()) || !"[3]".equals(cusFieldConfig.getLimitOptions())) {
                                        editText4.setInputType(2);
                                    } else {
                                        editText4.setInputType(8194);
                                        editText4.addTextChangedListener(new AnonymousClass4(editText4));
                                    }
                                } else if (8 == cusFieldConfig.getFieldType()) {
                                    linearLayout.setVisibility(8);
                                    textView5.setVisibility(0);
                                    linearLayout2.setVisibility(0);
                                    editText4.setVisibility(8);
                                    editText5.setVisibility(8);
                                    imageView.setVisibility(0);
                                    editText3.setVisibility(8);
                                    if (1 == cusFieldConfig.getFillFlag()) {
                                        textView3.setText(Html.fromHtml(cusFieldConfig.getFieldName() + "<font color='#f9676f'>&nbsp;*</font>"));
                                    } else {
                                        textView3.setText(cusFieldConfig.getFieldName());
                                    }
                                } else if (6 == cusFieldConfig.getFieldType()) {
                                    linearLayout.setVisibility(8);
                                    textView5.setVisibility(0);
                                    linearLayout2.setVisibility(0);
                                    imageView.setVisibility(0);
                                    editText4.setVisibility(8);
                                    editText3.setVisibility(8);
                                    editText5.setVisibility(8);
                                    if (1 == cusFieldConfig.getFillFlag()) {
                                        textView3.setText(Html.fromHtml(cusFieldConfig.getFieldName() + "<font color='#f9676f'>&nbsp;*</font>"));
                                    } else {
                                        textView3.setText(cusFieldConfig.getFieldName());
                                    }
                                } else if (7 == cusFieldConfig.getFieldType()) {
                                    linearLayout.setVisibility(8);
                                    textView5.setVisibility(0);
                                    linearLayout2.setVisibility(0);
                                    imageView.setVisibility(0);
                                    editText3.setVisibility(8);
                                    editText5.setVisibility(8);
                                    editText4.setVisibility(8);
                                    if (1 == cusFieldConfig.getFillFlag()) {
                                        textView3.setText(Html.fromHtml(cusFieldConfig.getFieldName() + "<font color='#f9676f'>&nbsp;*</font>"));
                                    } else {
                                        textView3.setText(cusFieldConfig.getFieldName());
                                    }
                                } else if (9 == cusFieldConfig.getFieldType()) {
                                    linearLayout.setVisibility(8);
                                    i2 = 0;
                                    textView5.setVisibility(0);
                                    linearLayout2.setVisibility(0);
                                    editText4.setVisibility(8);
                                    editText5.setVisibility(8);
                                    imageView.setVisibility(0);
                                    editText3.setVisibility(8);
                                    if (1 == cusFieldConfig.getFillFlag()) {
                                        textView3.setText(Html.fromHtml(cusFieldConfig.getFieldName() + "<font color='#f9676f'>&nbsp;*</font>"));
                                    } else {
                                        textView3.setText(cusFieldConfig.getFieldName());
                                    }
                                }
                            }
                            inflate.setOnClickListener(new StCusFieldPresenter$5(cusFieldConfig, textView2, editText, linearLayout3, textView3, context, textView4, bVar, sobotFieldModel));
                            viewGroup2 = viewGroup;
                            viewGroup2.addView(inflate);
                        }
                        i2 = 0;
                        inflate.setOnClickListener(new StCusFieldPresenter$5(cusFieldConfig, textView2, editText, linearLayout3, textView3, context, textView4, bVar, sobotFieldModel));
                        viewGroup2 = viewGroup;
                        viewGroup2.addView(inflate);
                    }
                    i4 = i + 1;
                    arrayList2 = arrayList;
                    viewGroup3 = viewGroup2;
                    i3 = i2;
                    activity2 = activity;
                    context2 = context;
                }
            }
        }
    }

    /* compiled from: StCusFieldPresenter */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.presenter.a$2  reason: invalid class name */
    public static class AnonymousClass2 implements View.OnFocusChangeListener {
        final /* synthetic */ EditText a;
        final /* synthetic */ TextView b;
        final /* synthetic */ Context c;
        final /* synthetic */ TextView d;

        AnonymousClass2(EditText editText, TextView textView, Context context, TextView textView2) {
            this.a = editText;
            this.b = textView;
            this.c = context;
            this.d = textView2;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                TextView textView = this.b;
                Context context = this.c;
                textView.setTextColor(ContextCompat.getColor(context, q.c(context, "sobot_common_gray2")));
                this.b.setTextSize(12.0f);
                this.d.setVisibility(8);
                this.a.setVisibility(0);
            } else if (ac.a((Object) this.a.getText().toString().trim())) {
                this.b.setTextSize(14.0f);
                TextView textView2 = this.b;
                Context context2 = this.c;
                textView2.setTextColor(ContextCompat.getColor(context2, q.c(context2, "sobot_common_gray1")));
                this.a.setVisibility(8);
                this.d.setVisibility(0);
            }
        }
    }

    /* compiled from: StCusFieldPresenter */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.presenter.a$3  reason: invalid class name */
    public static class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ SobotCusFieldConfig a;
        final /* synthetic */ Context b;
        private CharSequence c;

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        AnonymousClass3(SobotCusFieldConfig sobotCusFieldConfig, Context context) {
            this.a = sobotCusFieldConfig;
            this.b = context;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.c = charSequence;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (editable.length() != 0) {
                if (this.a.getLimitOptions().contains(com.tencent.connect.common.Constants.VIA_SHARE_TYPE_INFO) && !ac.a((Object) this.a.getLimitChar()) && this.c.length() > Integer.parseInt(this.a.getLimitChar())) {
                    Context context = this.b;
                    ae.c(context, this.a.getFieldName() + q.f(this.b, "sobot_only_can_write") + Integer.parseInt(this.a.getLimitChar()) + q.f(this.b, "sobot_char_length"));
                    editable.delete(this.c.length() + -1, this.c.length());
                }
                if (this.a.getLimitOptions().contains("4") && !Pattern.compile("^[a-zA-Z0-9\u4e00-\u9fa5]+$").matcher(editable).matches()) {
                    Context context2 = this.b;
                    ae.c(context2, this.a.getFieldName() + q.f(this.b, "sobot_only_can_write") + q.f(this.b, "sobot_number_english_china"));
                    this.c.length();
                    editable.delete(this.c.length() + -1, this.c.length());
                }
            }
        }
    }

    /* compiled from: StCusFieldPresenter */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.presenter.a$4  reason: invalid class name */
    public static class AnonymousClass4 implements TextWatcher {
        final /* synthetic */ EditText a;

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        AnonymousClass4(EditText editText) {
            this.a = editText;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.toString().contains(".") && (charSequence.length() - 1) - charSequence.toString().indexOf(".") > 2) {
                charSequence = charSequence.toString().subSequence(0, charSequence.toString().indexOf(".") + 3);
                this.a.setText(charSequence);
                this.a.setSelection(charSequence.length());
            }
            if (charSequence.toString().trim().substring(0).equals(".")) {
                charSequence = "0" + ((Object) charSequence);
                this.a.setText(charSequence);
                this.a.setSelection(2);
            }
            if (charSequence.toString().startsWith("0") && charSequence.toString().trim().length() > 1 && !charSequence.toString().substring(1, 2).equals(".")) {
                this.a.setText(charSequence.subSequence(0, 1));
                this.a.setSelection(1);
            }
        }
    }
}
