package cn.missfresh.module.base.network;

import cn.missfresh.module.base.network.l;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import de.greenrobot.event.Subscribe;
import java.util.List;

public class MissFreshNetDeliveryManager {
    private static final MissFreshNetDeliveryManager b = new MissFreshNetDeliveryManager();
    private List<l.a> a;

    private MissFreshNetDeliveryManager() {
        JniLib.cV(this, 12);
    }

    static {
        AppMethodBeat.i(15574, false);
        AppMethodBeat.o(15574);
    }

    @Subscribe
    public void onHandlerEvent(Event event) {
        AppMethodBeat.i(15571, false);
        if (event == Event.ON_REQUEST) {
            for (l.a aVar : this.a) {
                aVar.a(event.getWhichUrl());
            }
        } else if (event == Event.ON_RESULT) {
            for (l.a aVar2 : this.a) {
                aVar2.b(event.getWhichUrl());
            }
        }
        AppMethodBeat.o(15571);
    }

    public enum Event {
        ON_REQUEST,
        ON_RESULT;
        
        String whichUrl;

        public static Event valueOf(String str) {
            AppMethodBeat.i(15549, false);
            Event event = (Event) Enum.valueOf(Event.class, str);
            AppMethodBeat.o(15549);
            return event;
        }

        static {
            AppMethodBeat.i(15556, false);
            AppMethodBeat.o(15556);
        }

        public String getWhichUrl() {
            return this.whichUrl;
        }

        public void setWhichUrl(String str) {
            this.whichUrl = str;
        }
    }
}
