package com.dhcp.message.options;

import java.nio.ByteBuffer;

import com.dhcp.message.InvalidDhcpMessageException;
import com.dhcp.message.Option;
import com.dhcp.message.common.DhcpOption;

/**
 * 
 * @author Adrien
 *
 */
public class EmptyOption extends DhcpOption {

	public EmptyOption() {
		super(Option.EMPTY);
		
		name = "Pad Option";
	}

	@Override
	public byte[] getBytes() {
		byte[] result = { (byte) 0 }; 
		return result;
	}
	
	@Override
	public int getTotalLength(){
		return 1;
	}

	@Override
	public void parseDhcpOption(ByteBuffer buffer) throws InvalidDhcpMessageException {
		
	}
	
}
