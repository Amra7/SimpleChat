package ba.bitcamp.exercise.Benjo.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	// port boje od 0 do 65000 ( najbolje je da koristimo preko 1500)
	public static final int port = 1728;

	public static void startServer() {
		try {
			// ovaj Socket samo prima port
			// drugi client Socket prima adresu i port
			ServerSocket server = new ServerSocket(port);

			// koristimo true da bi nam socket uvijek radio
			while (true) {
				System.out.println("waiting");
				Socket client = server.accept();
				SocketReadWrite sc = new SocketReadWrite(
						client.getInputStream(), client.getOutputStream());
				Scanner line = new Scanner(System.in);
				
				while (true){
					System.out.println("Server: ");
					
					String msgServer = line.nextLine();
					sc.sendMessage(msgServer);
					
					
					String message = sc.getMessage();
					System.out.println(message);
				
					System.out.println("Unesi poruku: ");
													
					
					sc.sendMessage(msgServer);
					 if( line.equals("quit")){
						 break;
					 }
				}
				client.close();
				
				System.out.println(sc.getMessage());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		startServer();

	}

}
