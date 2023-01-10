package cn.missfresh.module.base.network;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.bangcle.andjni.JniLib;
import com.tencent.msdk.dns.DnsService;
import com.tencent.msdk.dns.core.IpSet;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Dns;
import okhttp3.OkHttpClient;

/* compiled from: MFDnsManager */
public class e {
    public static void a(OkHttpClient.Builder builder) {
        JniLib.cV(builder, 157);
    }

    /* compiled from: MFDnsManager */
    static class a implements Dns {
        a() {
            JniLib.cV(this, 156);
        }

        public List<InetAddress> lookup(String str) throws UnknownHostException {
            IpSet addrsByName;
            InetAddress byName;
            AppMethodBeat.i(15484, false);
            String property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            if (property == null || property2 == null) {
                if (("https://as-vip.missfresh.cn".endsWith(str) || "https://pay.missfresh.cn".endsWith(str) || "https://abtest.missfresh.net".endsWith(str)) && (addrsByName = DnsService.getAddrsByName(str)) != null) {
                    ArrayList arrayList = new ArrayList();
                    if (!(addrsByName.v6Ips == null || addrsByName.v6Ips.length <= 0 || (byName = InetAddress.getByName(addrsByName.v6Ips[0])) == null)) {
                        arrayList.add(byName);
                    }
                    if (addrsByName.v4Ips != null && addrsByName.v4Ips.length > 0) {
                        int length = addrsByName.v4Ips.length;
                        for (int i = 0; i < length; i++) {
                            InetAddress byName2 = InetAddress.getByName(addrsByName.v4Ips[i]);
                            if (byName2 != null) {
                                arrayList.add(byName2);
                            }
                        }
                    }
                    if (!b.a(arrayList)) {
                        AppMethodBeat.o(15484);
                        return arrayList;
                    }
                }
                List<InetAddress> lookup = Dns.SYSTEM.lookup(str);
                AppMethodBeat.o(15484);
                return lookup;
            }
            List<InetAddress> lookup2 = Dns.SYSTEM.lookup(str);
            AppMethodBeat.o(15484);
            return lookup2;
        }
    }
}
