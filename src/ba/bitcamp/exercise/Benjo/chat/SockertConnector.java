package ba.bitcamp.exercise.Benjo.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SockertConnector {

	public static final String serverAdress = "127.0.0.1"; // mogli smo napisati
															// i "local host" to
															// znaci da se
															// pokusavamo
															// spojiti na isti
															// racunar

	// port mora biti isti kao kod servere, ako nije isti onda se ne mogu
	// spojiti
	public static final int port = 1728;

	private static void connectToServer() throws UnknownHostException,
			IOException {

		// klijentu Socket moramo predati i adresu i port
		Socket client = new Socket(serverAdress, port);

		SocketReadWrite sc = new SocketReadWrite(client.getInputStream(),
				client.getOutputStream());
		
		Scanner line = new Scanner(System.in);
		while ( true){
			String message = sc.getMessage();
			System.out.println("Odgovori: ");
			
			String lineClient = line.nextLine();
			
			sc.sendMessage(lineClient);
			System.out.println(sc.getMessage());
			client.close();
		}
		
		

	}

	public static void main(String[] args) {
		try {
			connectToServer();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
