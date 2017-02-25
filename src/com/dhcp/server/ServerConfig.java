package com.dhcp.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;
import java.util.TreeMap;

import com.dhcp.lease.Lease;
import com.dhcp.util.HardwareAddress;
import com.dhcp.util.ServerLogger;

public class ServerConfig {

	private String serverName;
	private InetAddress serverAddress;
	private InetAddress ipAddressBandStart;
	private InetAddress ipAddressBandEnd;
	
	private long leaseDuration;
	private int addressAvailable;
	
	private final TreeMap<InetAddress,Lease> staticLeases = new TreeMap<>();
	
	public ServerConfig(String config, ServerLogger logger) {
		logger.systemMessage("Loading config from " + config);
		File cfg = new File (config);
	
		try {
			FileReader reader = new FileReader(cfg);
			Properties properties = new Properties();
			properties.load(reader);
			
			this.serverName = properties.getProperty("serverName");
			this.serverAddress = InetAddress.getByName(properties.getProperty("serverAddress"));
			this.ipAddressBandStart = InetAddress.getByName(properties.getProperty("iPAddressBandStart"));
			this.ipAddressBandEnd = InetAddress.getByName(properties.getProperty("iPAddressBandEnd"));
			
			this.leaseDuration = Long.parseLong(properties.getProperty("leaseDuration"));
			this.addressAvailable = Integer.parseInt(properties.getProperty("addressAvailable"));
			
			for(int i = 0; i < Integer.parseInt(properties.getProperty("staticLeaseAllocated")); i ++) {
				staticLeases.put(InetAddress.getByName(properties.getProperty("lease"+i+".ipAddress")),
									new Lease(InetAddress.getByName(properties.getProperty("lease"+i+".ipAddress"))
											,HardwareAddress.parseHardwareAddress(properties.getProperty("lease"+i+".hardwareAddress"))
											,Long.parseLong(properties.getProperty("lease"+i+".duration"))));
			}
			
		} catch (FileNotFoundException e) {
			logger.systemMessage("Could not find file \"" + config + "\"");
		} catch (IOException e ) {
			e.printStackTrace();
		}
		
	}

	public String getServerName() {
		return serverName;
	}

	public InetAddress getServerAddress() {
		return serverAddress;
	}

	public InetAddress getIpAddressBandStart() {
		return ipAddressBandStart;
	}

	public InetAddress getIpAddressBandEnd() {
		return ipAddressBandEnd;
	}

	public long getLeaseDuration() {
		return leaseDuration;
	}

	public int getAddressAvailable() {
		return addressAvailable;
	}
	
	public TreeMap<InetAddress,Lease> getStaticLeases() {
		return staticLeases;
	}

}