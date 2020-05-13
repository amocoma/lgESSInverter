package org.openhab.binding.lginverter.internal.discovery;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import org.eclipse.jdt.annotation.NonNull;


/**
 * Sample Code for Service Discovery using JmDNS and a ServiceListener.
 * <p>
 * Run the main method of this class. It listens for HTTP services and lists all changes on System.out.
 *
 * @author Werner Randelshofer
 */
public class DiscoverServices {

    public static String getESSIP(@NonNull String serial) throws Exception {
        String hostName = "LGE_ESS-" + serial;
        String ip = null;
        try {

            final JmDNS jmdns = JmDNS.create();
            List<ServiceInfo> _sinfos = Arrays.stream(jmdns.list("_pmsctrl._tcp.local.")).filter(_serviceInfo -> hostName.equals(_serviceInfo.getName())).collect(Collectors.toList());
            if (_sinfos.size() == 1) ip = _sinfos.get(0).getInet4Addresses()[0].getHostAddress();
            jmdns.close();
        } catch (IOException e) {
            throw new Exception();
        }
        if (ip == null) {
            throw new Exception();
        }
        return ip;
    }
}