package com.dhcp.lease;

import java.net.InetAddress;
import java.util.TreeMap;

public class LeaseManager {

	private static TreeMap<InetAddress, Lease> leases = new TreeMap<>();
	
	public static synchronized InetAddress getRandIPAddress(InetAddress hardwareAddress) {
			//TODO non termin�
			
			//Lease lease = new Lease(/* addresse ip*/,hardwareAddress,/* duration */);
			//leases.put(lease.getIPAddress(), lease);
			//return lease.getIPAddress();
			
			return null;
		}
	
	public static synchronized InetAddress tryOldIPAddressElseRand(InetAddress ipAddress, InetAddress hardwareAddress) {
		if(leases.get(ipAddress).isAvailable()) {
			leases.get(ipAddress).bind(hardwareAddress);
			return ipAddress;
		} else {
			return getRandIPAddress(hardwareAddress);
		}
	}
	
	public static synchronized void release(InetAddress address) {
		leases.get(address).release(false);
	}
}
