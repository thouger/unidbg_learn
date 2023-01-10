package com.vivo.push.sdk.service;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;

public class CommandClientService extends CommandService {
    /* access modifiers changed from: protected */
    @Override // com.vivo.push.sdk.service.CommandService
    public final boolean a(String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_NOTIFICATION_GROUP_SUMMARY, false);
        boolean equals = "com.vivo.pushclient.action.RECEIVE".equals(str);
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_NOTIFICATION_GROUP_SUMMARY);
        return equals;
    }
}
