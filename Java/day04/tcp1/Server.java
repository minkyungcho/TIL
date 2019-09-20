package tcp1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	boolean flag = true;
	int port; // server에 구멍이 있어야한다.
	ServerSocket serverSocket; // 서버소켓과 소켓 필요.
	Socket socket;

	OutputStream out;
	OutputStreamWriter osw;
	BufferedWriter bw;

	public Server(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
	}

	public void startServer() throws IOException {
		System.out.println("Server Start ...");
		while(flag) {
				
			try {
				System.out.println("Server Ready ...");
				socket = serverSocket.accept(); 
				System.out.println("Accepted.."+socket.getInetAddress());
				
				out = socket.getOutputStream();
				osw = new OutputStreamWriter(out);
				bw = new BufferedWriter(osw);
				bw.write("안녕");
				
			} catch(IOException e) {
				throw e;
			}finally {
				if(bw != null) {
					bw.close();
				}
				
				if(socket != null) {
					socket.close();
				}
			}
		} // end while 
		
		System.out.println("Server End ...");
	}

	public static void main(String[] args) {

		// server 구동. inputStream, OutputStream 만들기
		Server server = null;
		try {
			server = new Server(8);
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
