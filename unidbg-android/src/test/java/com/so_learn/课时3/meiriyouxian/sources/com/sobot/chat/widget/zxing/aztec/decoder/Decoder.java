package com.sobot.chat.widget.zxing.aztec.decoder;

import android.app.backup.FullBackup;
import android.net.wifi.WifiEnterpriseConfig;
import android.telecom.Logging.Session;
import androidx.exifinterface.media.ExifInterface;
import com.android.internal.telephony.PhoneConstants;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.umeng.analytics.pro.ai;
import com.umeng.message.proguard.l;
import com.xiaomi.mipush.sdk.Constants;

public final class Decoder {
    private static final String[] a = {"CTRL_PS", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] b = {"CTRL_PS", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER, "a", "b", "c", "d", "e", FullBackup.FILES_TREE_TOKEN, "g", "h", "i", "j", FullBackup.KEY_VALUE_DATA_TOKEN, "l", "m", "n", "o", "p", "q", FullBackup.ROOT_TREE_TOKEN, ai.az, "t", ai.aE, "v", "w", "x", "y", ai.aB, "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] c = {"CTRL_PS", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER, "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", Session.SESSION_SEPARATION_CHAR_CHILD, "`", HiAnalyticsConstant.REPORT_VAL_SEPARATOR, Constants.WAVE_SEPARATOR, "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] d = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", l.s, l.t, PhoneConstants.APN_TYPE_ALL, "+", Constants.ACCEPT_TIME_SEPARATOR_SP, "-", ".", NotificationIconUtil.SPLIT_CHAR, ":", ";", "<", ContainerUtils.KEY_VALUE_DELIMITER, ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] e = {"CTRL_PS", WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER, "0", "1", "2", "3", "4", "5", com.tencent.connect.common.Constants.VIA_SHARE_TYPE_INFO, "7", "8", "9", Constants.ACCEPT_TIME_SEPARATOR_SP, ".", "CTRL_UL", "CTRL_US"};

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }
}
