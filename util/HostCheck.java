package util;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HostCheck {

	private InetAddress ip;
	
	public HostCheck(String ip) {
		try {
			this.ip = InetAddress.getByName(ip);	
		} catch (Exception e) {
			
		}
	}
	
	public boolean checkOpen(int port) {
		boolean isOpen = false;
		Socket socket = new Socket();
		InetSocketAddress address = new InetSocketAddress(this.ip, port);
		
		try {
			socket.connect(address, 1000);
			
			if(socket.isConnected())
				isOpen = true;
		} catch (Exception e) {
			
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				
			}
		}
		
		return isOpen;
	}
	
}
