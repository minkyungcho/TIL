package tcp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	String ip;
	int port;
	
	Socket socket;
	InputStream in;
	InputStreamReader inr;
	BufferedReader br;
	
	
	public Client(String ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	public void connect() {
		try {
			socket = new Socket(ip, port);
		} catch (Exception e) {
			boolean flag = true;
			while(flag) {
				try {
					Thread.sleep(2000);
					socket = new Socket(ip, port);
					break;
				} catch (Exception e1) {
					System.out.println("retry");
				}
			}
		}
	}
	
	public void request() throws IOException {
		try {
			in = socket.getInputStream();
			inr = new InputStreamReader(in);
			br = new BufferedReader(inr);
			String str = br.readLine();
			System.out.println(str);
		} catch (IOException e) {
			throw e;
		} finally {
			if(in != null) {
				in.close();
			}
			if(inr != null) {
				inr.close();
			}
			if(br != null) {
				br.close();
			}
			if(socket != null) {
				socket.close();
			}
		}
	}
	
	public static void main(String[] args) {
		Client client = null;
		client = new Client("70.12.60.111",8);
		client.connect();
		try {
			client.request();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
