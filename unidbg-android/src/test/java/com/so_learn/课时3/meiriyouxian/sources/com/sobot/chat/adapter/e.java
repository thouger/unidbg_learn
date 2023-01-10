package com.sobot.chat.adapter;

import android.content.Context;
import android.media.TtmlUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.model.SobotMultiDiaRespInfo;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.api.model.ZhiChiReplyAnswer;
import com.sobot.chat.utils.ag;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.s;
import com.sobot.chat.viewHolder.CardMessageHolder;
import com.sobot.chat.viewHolder.ConsultMessageHolder;
import com.sobot.chat.viewHolder.FileMessageHolder;
import com.sobot.chat.viewHolder.ImageMessageHolder;
import com.sobot.chat.viewHolder.LocationMessageHolder;
import com.sobot.chat.viewHolder.OrderCardMessageHolder;
import com.sobot.chat.viewHolder.RichTextMessageHolder;
import com.sobot.chat.viewHolder.RobotAnswerItemsMsgHolder;
import com.sobot.chat.viewHolder.RobotQRMessageHolder;
import com.sobot.chat.viewHolder.TextMessageHolder;
import com.sobot.chat.viewHolder.VideoMessageHolder;
import com.sobot.chat.viewHolder.VoiceMessageHolder;
import com.sobot.chat.viewHolder.b;
import com.sobot.chat.viewHolder.base.MessageHolderBase;
import com.sobot.chat.viewHolder.c;
import com.sobot.chat.viewHolder.d;
import com.sobot.chat.viewHolder.f;
import com.sobot.chat.viewHolder.g;
import com.sobot.chat.viewHolder.h;
import com.sobot.chat.viewHolder.i;
import com.sobot.chat.viewHolder.j;
import com.sobot.chat.viewHolder.k;
import com.sobot.chat.viewHolder.l;
import com.sobot.chat.viewHolder.m;
import com.tencent.connect.common.Constants;
import java.util.List;

/* compiled from: SobotMsgAdapter */
public class e extends com.sobot.chat.adapter.base.a<ZhiChiMessageBase> {
    private static final String[] a = {"sobot_chat_msg_item_txt_l", "sobot_chat_msg_item_txt_r", "sobot_chat_msg_item_tip", "sobot_chat_msg_item_rich", "sobot_chat_msg_item_imgt_l", "sobot_chat_msg_item_imgt_r", "sobot_chat_msg_item_audiot_r", "sobot_chat_msg_item_consult", "sobot_chat_msg_item_evaluate", "sobot_chat_msg_item_template1_l", "sobot_chat_msg_item_template2_l", "sobot_chat_msg_item_template3_l", "sobot_chat_msg_item_sdk_history_r", "sobot_chat_msg_item_template4_l", "sobot_chat_msg_item_template5_l", "sobot_chat_msg_item_question_recommend", "sobot_chat_msg_item_retracted_msg", "sobot_chat_msg_item_robot_answer_items_l", "sobot_chat_msg_item_robot_keyword_items_l", "sobot_chat_msg_item_file_l", "sobot_chat_msg_item_file_r", "sobot_chat_msg_item_video_r", "sobot_chat_msg_item_location_r", "sobot_chat_msg_item_notice", "sobot_chat_msg_item_card_r", "sobot_chat_msg_item_order_card_r", "sobot_chat_msg_item_order_card_l", "sobot_chat_msg_item_card_l", "sobot_chat_msg_item_template6_l", "sobot_chat_msg_item_system_tip", "sobot_chat_msg_item_video_l"};
    private String d;
    private String e;
    private a f;

    /* compiled from: SobotMsgAdapter */
    public interface a {
        void a();

        void a(ZhiChiMessageBase zhiChiMessageBase);

        void a(ZhiChiMessageBase zhiChiMessageBase, int i, int i2, String str);

        void a(ZhiChiMessageBase zhiChiMessageBase, int i, int i2, String str, String str2);

        void a(boolean z, ZhiChiMessageBase zhiChiMessageBase);

        void b();

        void b(boolean z, ZhiChiMessageBase zhiChiMessageBase);

        void c();
    }

    public e(Context context, List<ZhiChiMessageBase> list, a aVar) {
        super(context, list);
        this.f = aVar;
        this.d = s.b(context, "sobot_current_sender_face", "");
        this.e = s.b(context, "sobot_current_sender_name", "");
    }

    public void a(List<ZhiChiMessageBase> list) {
        b(list);
        this.b.addAll(0, list);
    }

    public void a(ZhiChiMessageBase zhiChiMessageBase) {
        if (zhiChiMessageBase != null) {
            if (zhiChiMessageBase.getAction() != null && "action_remind_connt_success".equals(zhiChiMessageBase.getAction())) {
                for (int i = 0; i < this.b.size(); i++) {
                    if (((ZhiChiMessageBase) this.b.get(i)).getSugguestionsFontColor() != 1) {
                        ((ZhiChiMessageBase) this.b.get(i)).setSugguestionsFontColor(1);
                    }
                }
            }
            a(zhiChiMessageBase, "action_remind_no_service", "action_remind_no_service", true);
            a(zhiChiMessageBase, "action_remind_info_paidui", "action_remind_info_paidui", true);
            a(zhiChiMessageBase, "action_remind_info_paidui", "action_remind_info_post_msg", true);
            a(zhiChiMessageBase, "action_remind_connt_success", "action_remind_info_paidui", false);
            a(zhiChiMessageBase, "action_remind_info_post_msg", "action_remind_info_post_msg", true);
            a(zhiChiMessageBase, "action_remind_connt_success", "action_remind_info_post_msg", false);
            a(zhiChiMessageBase, "action_consultingContent_info", "action_consultingContent_info", false);
            a(zhiChiMessageBase, "sobot_outline_leverByManager", "sobot_outline_leverByManager", true);
            a(zhiChiMessageBase, "action_custom_evaluate", "action_custom_evaluate", true);
            if (zhiChiMessageBase.getAction() != null && zhiChiMessageBase.getAction().equals("action_remind_past_time") && zhiChiMessageBase.getAnswer() != null && 5 == zhiChiMessageBase.getAnswer().getRemindType()) {
                for (int i2 = 0; i2 < this.b.size(); i2++) {
                    if (((ZhiChiMessageBase) this.b.get(i2)).getAction() != null && ((ZhiChiMessageBase) this.b.get(i2)).getAction().equals("action_remind_past_time") && zhiChiMessageBase.getAnswer() != null && 5 == zhiChiMessageBase.getAnswer().getRemindType()) {
                        this.b.remove(i2);
                        zhiChiMessageBase.setShake(true);
                    }
                }
            }
            b(zhiChiMessageBase);
        }
    }

    public void b(ZhiChiMessageBase zhiChiMessageBase) {
        if (zhiChiMessageBase != null) {
            c(s.b(this.c, "lastCid", ""), zhiChiMessageBase);
            this.b.add(zhiChiMessageBase);
        }
    }

    private void a(ZhiChiMessageBase zhiChiMessageBase, String str, String str2, boolean z) {
        if (zhiChiMessageBase.getAction() != null && zhiChiMessageBase.getAction().equals(str)) {
            for (int i = 0; i < this.b.size(); i++) {
                if (((ZhiChiMessageBase) this.b.get(i)).getAction() != null && ((ZhiChiMessageBase) this.b.get(i)).getAction().equals(str2)) {
                    this.b.remove(i);
                    zhiChiMessageBase.setShake(z);
                }
            }
        }
    }

    public void c(ZhiChiMessageBase zhiChiMessageBase) {
        c(s.b(this.c, "lastCid", ""), zhiChiMessageBase);
        this.b.add(0, zhiChiMessageBase);
    }

    private void c(String str, ZhiChiMessageBase zhiChiMessageBase) {
        ZhiChiReplyAnswer answer = zhiChiMessageBase.getAnswer();
        if ((answer == null || answer.getRemindType() != 6) && zhiChiMessageBase.getCid() == null) {
            zhiChiMessageBase.setCid(str);
        }
    }

    private void b(List<ZhiChiMessageBase> list) {
        String b = s.b(this.c, "lastCid", "");
        for (int i = 0; i < list.size(); i++) {
            c(b, list.get(i));
        }
    }

    public void a(String str, int i, int i2) {
        ZhiChiMessageBase c = c(str);
        if (c != null && c.getSendSuccessState() != 1) {
            c.setSendSuccessState(i);
            c.setProgressBar(i2);
        }
    }

    public void a(String str, int i, String str2) {
        ZhiChiMessageBase c = c(str);
        if (c != null) {
            c.setSendSuccessState(i);
            if (!TextUtils.isEmpty(str2) && c.getAnswer() != null) {
                c.getAnswer().setDuration(str2);
            }
        }
    }

    public void a(String str, ZhiChiMessageBase zhiChiMessageBase) {
        ZhiChiMessageBase c = c(str);
        if (c != null) {
            c.setSendSuccessState(zhiChiMessageBase.getSendSuccessState());
        }
    }

    public void b(String str, ZhiChiMessageBase zhiChiMessageBase) {
        ZhiChiMessageBase c = c(str);
        if (c != null) {
            c.setAnswer(zhiChiMessageBase.getAnswer());
            c.setSenderType(zhiChiMessageBase.getSenderType());
            c.setSendSuccessState(zhiChiMessageBase.getSendSuccessState());
        }
    }

    public void a(String str) {
        ZhiChiMessageBase c = c(str);
        if (c != null && c.getSendSuccessState() == 4) {
            this.b.remove(c);
        }
    }

    public void a(String str, int i) {
        ZhiChiMessageBase c = c(str);
        if (c != null) {
            c.setSendSuccessState(i);
        }
    }

    private ZhiChiMessageBase c(String str) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            ZhiChiMessageBase zhiChiMessageBase = (ZhiChiMessageBase) this.b.get(size);
            if (!(zhiChiMessageBase == null || zhiChiMessageBase.getId() == null || !zhiChiMessageBase.getId().equals(str))) {
                return zhiChiMessageBase;
            }
        }
        return null;
    }

    public int b(String str) {
        int i = 0;
        for (Object obj : this.b) {
            i++;
            if (obj instanceof ZhiChiMessageBase) {
                ZhiChiMessageBase zhiChiMessageBase = (ZhiChiMessageBase) obj;
                if (zhiChiMessageBase.getId() != null && zhiChiMessageBase.getId().equals(str)) {
                    return i;
                }
            }
        }
        return this.b.size() - 1;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ZhiChiMessageBase zhiChiMessageBase = (ZhiChiMessageBase) this.b.get(i);
        if (zhiChiMessageBase == null) {
            return view;
        }
        int itemViewType = getItemViewType(i);
        View a2 = a(view, itemViewType, i, zhiChiMessageBase);
        MessageHolderBase messageHolderBase = (MessageHolderBase) a2.getTag();
        messageHolderBase.a(this.f);
        a(messageHolderBase, i);
        messageHolderBase.a(itemViewType, this.c, zhiChiMessageBase, this.d, this.e);
        messageHolderBase.h();
        messageHolderBase.a(zhiChiMessageBase);
        messageHolderBase.a(this.c, zhiChiMessageBase);
        return a2;
    }

    private View a(View view, int i, int i2, ZhiChiMessageBase zhiChiMessageBase) {
        MessageHolderBase messageHolderBase;
        MessageHolderBase messageHolderBase2;
        if (view == null) {
            View inflate = LayoutInflater.from(this.c).inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, a[i]), (ViewGroup) null);
            switch (i) {
                case 0:
                case 1:
                    messageHolderBase2 = new TextMessageHolder(this.c, inflate);
                    if (i == 0) {
                        messageHolderBase2.a(false);
                    } else {
                        messageHolderBase2.a(true);
                    }
                    messageHolderBase = messageHolderBase2;
                    break;
                case 2:
                    messageHolderBase = new c(this.c, inflate);
                    break;
                case 3:
                    messageHolderBase = new RichTextMessageHolder(this.c, inflate);
                    break;
                case 4:
                case 5:
                    messageHolderBase2 = new ImageMessageHolder(this.c, inflate);
                    if (i == 4) {
                        messageHolderBase2.a(false);
                    } else {
                        messageHolderBase2.a(true);
                    }
                    messageHolderBase = messageHolderBase2;
                    break;
                case 6:
                    messageHolderBase = new VoiceMessageHolder(this.c, inflate);
                    messageHolderBase.a(true);
                    break;
                case 7:
                    messageHolderBase = new ConsultMessageHolder(this.c, inflate);
                    break;
                case 8:
                    messageHolderBase = new com.sobot.chat.viewHolder.a(this.c, inflate);
                    break;
                case 9:
                    messageHolderBase = new f(this.c, inflate);
                    break;
                case 10:
                    messageHolderBase = new g(this.c, inflate);
                    break;
                case 11:
                    messageHolderBase = new h(this.c, inflate);
                    break;
                case 12:
                    messageHolderBase = new l(this.c, inflate);
                    break;
                case 13:
                    messageHolderBase = new i(this.c, inflate);
                    break;
                case 14:
                    messageHolderBase = new j(this.c, inflate);
                    break;
                case 15:
                    messageHolderBase = new RobotQRMessageHolder(this.c, inflate);
                    break;
                case 16:
                    messageHolderBase = new d(this.c, inflate);
                    break;
                case 17:
                    messageHolderBase = new RobotAnswerItemsMsgHolder(this.c, inflate);
                    break;
                case 18:
                    messageHolderBase = new com.sobot.chat.viewHolder.e(this.c, inflate);
                    break;
                case 19:
                case 20:
                    messageHolderBase2 = new FileMessageHolder(this.c, inflate);
                    if (i == 19) {
                        messageHolderBase2.a(false);
                    } else {
                        messageHolderBase2.a(true);
                    }
                    messageHolderBase = messageHolderBase2;
                    break;
                case 21:
                case 30:
                    messageHolderBase2 = new VideoMessageHolder(this.c, inflate);
                    if (i == 30) {
                        messageHolderBase2.a(false);
                    } else {
                        messageHolderBase2.a(true);
                    }
                    messageHolderBase = messageHolderBase2;
                    break;
                case 22:
                    messageHolderBase = new LocationMessageHolder(this.c, inflate);
                    messageHolderBase.a(true);
                    break;
                case 23:
                    messageHolderBase = new b(this.c, inflate);
                    break;
                case 24:
                case 27:
                    messageHolderBase2 = new CardMessageHolder(this.c, inflate);
                    if (i == 27) {
                        messageHolderBase2.a(false);
                    } else {
                        messageHolderBase2.a(true);
                    }
                    messageHolderBase = messageHolderBase2;
                    break;
                case 25:
                case 26:
                    messageHolderBase2 = new OrderCardMessageHolder(this.c, inflate);
                    if (i == 26) {
                        messageHolderBase2.a(false);
                    } else {
                        messageHolderBase2.a(true);
                    }
                    messageHolderBase = messageHolderBase2;
                    break;
                case 28:
                    messageHolderBase = new k(this.c, inflate);
                    break;
                case 29:
                    messageHolderBase = new m(this.c, inflate);
                    break;
                default:
                    messageHolderBase = new TextMessageHolder(this.c, inflate);
                    break;
            }
            inflate.setTag(messageHolderBase);
            return inflate;
        }
        switch (i) {
            case 9:
                View inflate2 = LayoutInflater.from(this.c).inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, a[i]), (ViewGroup) null);
                inflate2.setTag(new f(this.c, inflate2));
                return inflate2;
            case 10:
                View inflate3 = LayoutInflater.from(this.c).inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, a[i]), (ViewGroup) null);
                inflate3.setTag(new g(this.c, inflate3));
                return inflate3;
            case 11:
                View inflate4 = LayoutInflater.from(this.c).inflate(q.a(this.c, TtmlUtils.TAG_LAYOUT, a[i]), (ViewGroup) null);
                inflate4.setTag(new h(this.c, inflate4));
                return inflate4;
            default:
                return view;
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        String[] strArr = a;
        if (strArr.length > 0) {
            return strArr.length;
        }
        return super.getViewTypeCount();
    }

    /* renamed from: a */
    public ZhiChiMessageBase getItem(int i) {
        if (i < 0 || i >= this.b.size()) {
            return null;
        }
        return (ZhiChiMessageBase) this.b.get(i);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        try {
            ZhiChiMessageBase a2 = getItem(i);
            if (a2 == null) {
                return 0;
            }
            if (a2.isRetractedMsg()) {
                return 16;
            }
            int i2 = -1;
            if (!TextUtils.isEmpty(a2.getSenderType())) {
                i2 = Integer.parseInt(a2.getSenderType());
            } else if (29 == Integer.parseInt(a2.getAction())) {
                return 29;
            }
            if (!(i2 == 0 || 1 == i2)) {
                if (2 != i2) {
                    if (24 == Integer.parseInt(a2.getSenderType())) {
                        return 2;
                    }
                    if (23 == Integer.parseInt(a2.getSenderType())) {
                        return 5;
                    }
                    if (25 == Integer.parseInt(a2.getSenderType())) {
                        return 6;
                    }
                    if (26 == Integer.parseInt(a2.getSenderType())) {
                        return 7;
                    }
                    if (27 == Integer.parseInt(a2.getSenderType())) {
                        return 3;
                    }
                    if (28 == Integer.parseInt(a2.getSenderType())) {
                        return 8;
                    }
                    if (29 == Integer.parseInt(a2.getSenderType())) {
                        return 15;
                    }
                    if (30 == Integer.parseInt(a2.getSenderType())) {
                        return 3;
                    }
                    if (31 == Integer.parseInt(a2.getSenderType())) {
                        return 18;
                    }
                    if (32 == Integer.parseInt(a2.getSenderType())) {
                        return 23;
                    }
                    if (29 == Integer.parseInt(a2.getAction())) {
                        return 29;
                    }
                    return 0;
                }
            }
            if (a2.getAnswer() != null) {
                if (Integer.parseInt(a2.getAnswer().getMsgType()) == 0) {
                    if (1 == Integer.parseInt(a2.getSenderType())) {
                        return 3;
                    }
                    if (2 != Integer.parseInt(a2.getSenderType()) && Integer.parseInt(a2.getSenderType()) == 0) {
                        return 1;
                    }
                } else if (1 == Integer.parseInt(a2.getAnswer().getMsgType())) {
                    if (1 != Integer.parseInt(a2.getSenderType())) {
                        if (2 != Integer.parseInt(a2.getSenderType())) {
                            if (Integer.parseInt(a2.getSenderType()) == 0) {
                                return 5;
                            }
                        }
                    }
                    return 4;
                } else if (2 == Integer.parseInt(a2.getAnswer().getMsgType())) {
                    if (1 != Integer.parseInt(a2.getSenderType())) {
                        if (2 != Integer.parseInt(a2.getSenderType())) {
                            if (Integer.parseInt(a2.getSenderType()) == 0) {
                                return (a2.getAnswer() == null || TextUtils.isEmpty(a2.getAnswer().getMsgTransfer())) ? 6 : 1;
                            }
                        }
                    }
                    return 0;
                } else if (3 == Integer.parseInt(a2.getAnswer().getMsgType())) {
                    if (1 == Integer.parseInt(a2.getSenderType()) || 2 == Integer.parseInt(a2.getSenderType())) {
                        return 3;
                    }
                } else if (4 == Integer.parseInt(a2.getAnswer().getMsgType())) {
                    if (1 == Integer.parseInt(a2.getSenderType()) || 2 == Integer.parseInt(a2.getSenderType())) {
                        return 3;
                    }
                } else if (5 == Integer.parseInt(a2.getAnswer().getMsgType())) {
                    if (1 == Integer.parseInt(a2.getSenderType()) || 2 == Integer.parseInt(a2.getSenderType())) {
                        return 3;
                    }
                } else if (Integer.parseInt(a2.getAnswer().getMsgType()) == 7 || Integer.parseInt(a2.getAnswer().getMsgType()) == 11) {
                    return 3;
                } else {
                    if (Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(a2.getAnswer().getMsgType())) {
                        return 12;
                    }
                    if ("9".equals(a2.getAnswer().getMsgType())) {
                        if (GsonUtil.isMultiRoundSession(a2) && a2.getAnswer().getMultiDiaRespInfo() != null) {
                            SobotMultiDiaRespInfo multiDiaRespInfo = a2.getAnswer().getMultiDiaRespInfo();
                            if ("1511".equals(a2.getAnswerType())) {
                                return 17;
                            }
                            if ("1522".equals(a2.getAnswerType())) {
                                return 3;
                            }
                            if (multiDiaRespInfo.getInputContentList() != null && multiDiaRespInfo.getInputContentList().length > 0) {
                                return 10;
                            }
                            if (TextUtils.isEmpty(multiDiaRespInfo.getTemplate())) {
                                if (multiDiaRespInfo.getInterfaceRetList() == null || multiDiaRespInfo.getInterfaceRetList().size() <= 0) {
                                    if (multiDiaRespInfo.getInputContentList() == null) {
                                        return 14;
                                    }
                                    if (multiDiaRespInfo.getInputContentList().length <= 0) {
                                        return 14;
                                    }
                                }
                                return 10;
                            } else if ("0".equals(multiDiaRespInfo.getTemplate())) {
                                return 9;
                            } else {
                                if ("1".equals(multiDiaRespInfo.getTemplate())) {
                                    return 10;
                                }
                                if ("2".equals(multiDiaRespInfo.getTemplate())) {
                                    return 11;
                                }
                                if ("3".equals(multiDiaRespInfo.getTemplate())) {
                                    return 13;
                                }
                                if ("4".equals(multiDiaRespInfo.getTemplate())) {
                                    return 14;
                                }
                                if ("99".equals(multiDiaRespInfo.getTemplate())) {
                                    return 28;
                                }
                            }
                        }
                    } else if (Constants.VIA_REPORT_TYPE_SET_AVATAR.equals(a2.getAnswer().getMsgType())) {
                        if (2 == Integer.parseInt(a2.getSenderType())) {
                            return 19;
                        }
                        if (Integer.parseInt(a2.getSenderType()) == 0) {
                            return 20;
                        }
                    } else if (Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR.equals(a2.getAnswer().getMsgType())) {
                        if (2 == Integer.parseInt(a2.getSenderType())) {
                            if (a2.getAnswer().getCacheFile() != null) {
                                return 30;
                            }
                        } else if (Integer.parseInt(a2.getSenderType()) == 0 && a2.getAnswer().getCacheFile() != null) {
                            return 21;
                        }
                    } else if (Constants.VIA_REPORT_TYPE_DATALINE.equals(a2.getAnswer().getMsgType())) {
                        if (Integer.parseInt(a2.getSenderType()) == 0 && a2.getAnswer().getLocationData() != null) {
                            return 22;
                        }
                    } else if (24 == Integer.parseInt(a2.getAnswer().getMsgType())) {
                        if (a2.getConsultingContent() != null) {
                            if (2 == Integer.parseInt(a2.getSenderType())) {
                                return 27;
                            }
                            if (Integer.parseInt(a2.getSenderType()) == 0) {
                                return 24;
                            }
                        }
                    } else if (25 == Integer.parseInt(a2.getAnswer().getMsgType()) && a2.getOrderCardContent() != null) {
                        if (2 == Integer.parseInt(a2.getSenderType())) {
                            return 26;
                        }
                        if (Integer.parseInt(a2.getSenderType()) == 0) {
                            return 25;
                        }
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void a(MessageHolderBase messageHolderBase, int i) {
        if (!s.b(this.c, "sobot_use_language", false)) {
            ZhiChiMessageBase zhiChiMessageBase = (ZhiChiMessageBase) this.b.get(i);
            if (messageHolderBase.r != null) {
                ag.a(null, messageHolderBase.r);
                messageHolderBase.r.setTextColor(this.c.getResources().getColor(q.a(this.c, "color", "sobot_color_remind_time_color")));
                if (i == 0) {
                    ZhiChiReplyAnswer answer = zhiChiMessageBase.getAnswer();
                    if (answer == null || answer.getRemindType() != 6) {
                        messageHolderBase.r.setText(a(zhiChiMessageBase, i));
                        messageHolderBase.r.setVisibility(0);
                        return;
                    }
                    messageHolderBase.r.setVisibility(8);
                } else if (zhiChiMessageBase.getCid() == null || zhiChiMessageBase.getCid().equals(((ZhiChiMessageBase) this.b.get(i - 1)).getCid())) {
                    messageHolderBase.r.setVisibility(8);
                } else {
                    String a2 = a(zhiChiMessageBase, i);
                    messageHolderBase.r.setVisibility(0);
                    messageHolderBase.r.setText(a2);
                }
            }
        }
    }

    private String a(ZhiChiMessageBase zhiChiMessageBase, int i) {
        String b = s.b(this.c, "lastCid", "");
        zhiChiMessageBase.setTs(TextUtils.isEmpty(zhiChiMessageBase.getTs()) ? com.sobot.chat.utils.f.b() : zhiChiMessageBase.getTs());
        String a2 = com.sobot.chat.utils.f.a(zhiChiMessageBase.getTs() + "", "yyyy-MM-dd", Boolean.valueOf(com.sobot.chat.d.a(8)));
        String a3 = com.sobot.chat.utils.f.a();
        if (zhiChiMessageBase.getCid() != null && zhiChiMessageBase.getCid().equals(b) && a3.equals(a2)) {
            return com.sobot.chat.utils.f.a(zhiChiMessageBase.getTs(), true, "", Boolean.valueOf(com.sobot.chat.d.a(8)));
        }
        return com.sobot.chat.utils.f.a(((ZhiChiMessageBase) this.b.get(i)).getTs() + "", "MM-dd HH:mm", Boolean.valueOf(com.sobot.chat.d.a(8)));
    }

    public void a() {
        for (int i = 0; i < this.b.size(); i++) {
            if (((ZhiChiMessageBase) this.b.get(i)).getAction() != null && ((ZhiChiMessageBase) this.b.get(i)).getAction().equals("action_consultingContent_info")) {
                this.b.remove(i);
                return;
            }
        }
    }

    public void b() {
        try {
            List<ZhiChiMessageBase> d = d();
            for (int size = d.size() - 1; size >= 0; size--) {
                if (31 == Integer.parseInt(d.get(size).getSenderType())) {
                    d.remove(size);
                    return;
                }
            }
        } catch (Exception unused) {
            com.sobot.chat.utils.m.d("error : removeKeyWordTranferItem()");
        }
    }

    public void c() {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            ZhiChiMessageBase zhiChiMessageBase = (ZhiChiMessageBase) this.b.get(size);
            if (Constants.VIA_ACT_TYPE_TWENTY_EIGHT.equals(zhiChiMessageBase.getSenderType()) && zhiChiMessageBase.getSobotEvaluateModel() != null) {
                this.b.remove(zhiChiMessageBase);
                return;
            }
        }
    }
}
