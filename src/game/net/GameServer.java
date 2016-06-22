package game.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import game.Game;

public class GameServer {
	
	private Game game;
	
	private String ip = "localhost";
	private int port = 22222;
	
	private Scanner scanner = new Scanner(System.in);
	
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	private ServerSocket serverSocket;
	
	public GameServer(Game game){
		this.game = game;
		start();
	}
	
	public void start() {
		System.out.println("Please input the IP: ");
		ip = scanner.nextLine();
		System.out.println("Please input the port: ");
		port = scanner.nextInt();
		while (port < 1 || port > 65535) {
			System.out.println("The port you entered was invalid, please input another port: ");
			port = scanner.nextInt();
		}
		
		if (!connect()) initializeServer();
	}
	
	public void listenForServerRequest() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			game.accepted = true;
			game.board.startGame();
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean connect() {
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			game.accepted = true;
			game.board.startGame();
		} catch (IOException e) {
			System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
			return false;
		}
		System.out.println("Successfully connected to the server.");
		return true;
	}

	private void initializeServer() {
		try {
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		try {
			String data = dis.readUTF();
			game.board.decode(data);
			System.out.println("DATA RECEIVED");
		} catch (IOException e) {
			e.printStackTrace();
			game.errors++;
		}
	}
	
	public void writeData(String data) {
		try {
			dos.writeUTF(data);
			dos.flush();
		} catch (IOException e1) {
			game.errors++;
			e1.printStackTrace();
		}
	}
	
}