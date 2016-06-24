package wyl.activemq.myactivemq;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class LocalIPTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getLocalIP());
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	private static String getLocalIP() {
        try {
            String localip = null;// 本地IP，如果没有配置外网IP则返回它
            String netip = null;// 外网IP
           
            Enumeration<NetworkInterface> netInterfaces;
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                try {
                    if (!ni.isLoopback()) {
                        Enumeration<InetAddress> address = ni.getInetAddresses();
                        while (address.hasMoreElements()) {
                            ip = address.nextElement();
                            if (!ip.isSiteLocalAddress()
                                    && !ip.isLoopbackAddress()
                                    && !ip.getHostAddress().contains(":")) {// 外网IP
                                netip = ip.getHostAddress();
                                finded = true;
                                break;
                            } else if (ip.isSiteLocalAddress()
                                    && !ip.isLoopbackAddress()
                                    && !ip.getHostAddress().contains(":")) {// 内网IP
                                localip = ip.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                	e.printStackTrace();
                }
            }

            if (localip != null && !"".equals(localip)) {
                return localip;
            } else {
                return netip;
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return "local-ip";
        }
    }

}
