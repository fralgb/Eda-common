package com.omniselling.microservice.service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * @author xslong
 * @version 创建时间：Nov 23, 2016 2:12:08 PM
 * 
 */

public class LocalIpAddressUtil {
	/**
	 * 获取本地ip地址，有可能会有多个地址, 若有多个网卡则会搜集多个网卡的ip地址
	 * 
	 * @return
	 */
	public static List<InetAddress> resolveLocalAddresses() {
		List<InetAddress> addrs = new ArrayList<InetAddress>();
		Enumeration<NetworkInterface> ns = null;
		try {
			ns = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
		}
		while (ns != null && ns.hasMoreElements()) {
			NetworkInterface n = ns.nextElement();
			Enumeration<InetAddress> is = n.getInetAddresses();
			while (is.hasMoreElements()) {
				InetAddress i = is.nextElement();
				if (!i.isLoopbackAddress() && !i.isLinkLocalAddress() && !i.isMulticastAddress()
						&& !isSpecialIp(i.getHostAddress()))
					addrs.add(i);
			}
		}
		return addrs;
	}

	public static String[] resolveLocalIps() {
		List<InetAddress> addrs = resolveLocalAddresses();
		int len = addrs.size();
		String[] ret = new String[len];
		for (int i = 0; i < len; i++)
			ret[i] = addrs.get(i).getHostAddress();
		return ret;
	}

	private static boolean isSpecialIp(String ip) {
		if (ip.contains(":"))
			return true;
		if (ip.startsWith("127."))
			return true;
		if (ip.startsWith("169.254."))
			return true;
		if (ip.equals("255.255.255.255"))
			return true;
		return false;
	}

	public static void main(String[] args) throws UnknownHostException {
		System.out.println("本机IP：" + Arrays.toString(resolveLocalIps()));
	}
}
