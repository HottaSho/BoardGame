package game.characters;

import java.net.InetAddress;

public class PlayerMP extends Player {

	public InetAddress ipAddress;
	public int port;
	public String username;
	
	public PlayerMP(String username){
		super(username);
	}
	
	public PlayerMP(String username, InetAddress ipAddress, int port){
		super(username);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
}
