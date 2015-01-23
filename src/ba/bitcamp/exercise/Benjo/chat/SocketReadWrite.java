package ba.bitcamp.exercise.Benjo.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketReadWrite {

	private InputStream is;
	private OutputStream os;

	public SocketReadWrite(InputStream is, OutputStream os) {
		this.is = is;
		this.os= os;
	}

	public  String getMessage() {
		String bufferedStringClient = "";
		try {

			int msgClientLenght = is.read();
			byte[] byteBuffer = new byte[msgClientLenght];
			int byteRead = 0;

			StringBuilder sb = new StringBuilder();

			while ((byteRead += is.read(byteBuffer)) >= 0) {
				sb.append(new String(byteBuffer).replaceAll("\\s+", " "));

				if (byteRead >= msgClientLenght) {
					break;
				}
			}

			bufferedStringClient = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedStringClient;

	}

	public void sendMessage(String msg) {
		
		try {

			byte[] msgBytes = msg.getBytes();
			os.write(msgBytes.length);

			os.write(msgBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
