package com.dhcp.message.options;

import java.net.UnknownHostException;

import com.dhcp.message.Option;
import com.dhcp.message.common.AddressOptionBase;

public class RouterOption extends AddressOptionBase {
	
	public RouterOption() throws UnknownHostException {
		super(Option.ROUTER, false);
		
		name = "Router Option";
	}
	
}
