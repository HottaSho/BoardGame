package game.characters;

import java.net.InetAddress;

public class PlayerMP extends Player {

	public InetAddress ipAddress;
	public int port;
	public String username;
	
	public PlayerMP(String username, InetAddress ipAddress, int port){
		this.ipAddress = ipAddress;
		this.port = port;
		this.username = username;
	}
	
	public String getUsername(){
		return username;
	}
	
}
