### 1.5 URLConnection

**Http2.java**

- url 활용해서 웹에 올라가있는 파일 내용 출력하기.
- 파일 내용 읽어온거 활용해서 파일 생성.

```java
public class Http2 {

	public static void main(String[] args) throws Exception{
		String urlstr = "http://70.12.60.90/test/oracle.zip";
		URL url = new URL(urlstr);
		InputStream is = url.openStream();
		BufferedInputStream bis = new BufferedInputStream(is,1024);
		
		int data = 0;
		// oracle.zip
		
		String fileName = "oracle.zip";
		FileOutputStream fos = new FileOutputStream(fileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos) ;		
		
		System.out.println("Start ...");
		while((data=bis.read()) != -1) {
			bos.write(data);
//			System.out.println("*"+data);
		}
		System.out.println("Finish ...");
		is.close();
		bis.close();
	}

}
```



**Http3.java**

- id와 pwd를 전송해서 결과값 받기.
- 결과 : id, pwd가 일치하는지 불일치하는지.

```java
public class Http3 {

	public static void main(String[] args) throws Exception {
		// id와 pwd를 전송해서 결과값 받기.
		// 결과 : id, pwd가 일치하는지 불일치하는지.
		String urlstr = "http://70.12.60.111/webview/login.jsp";
//		String urlstr = "http://70.12.60.90/test/login.jsp";
		String id = "ming";
		String pwd = "11";
		urlstr += "?id="+id+"&pwd="+pwd;
		
		URL url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setRequestMethod("GET"); // 해당 url로 connection을 GET방식으로 진행
		conn.setRequestMethod("POST"); // 보안
		conn.setReadTimeout(5000);
		
		InputStream is = conn.getInputStream(); // server로 전송. server와 app 사이에 input stream 생김.
		
		InputStreamReader isr = new InputStreamReader(is); // 결과를 2byte로 읽음. 다시 string으로 바꿔줘야함.
		BufferedReader br = new BufferedReader(isr); // Line 단위
		
		// 로그인 정상적으로 됐을 때
		if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			String result = null;
			while((result=br.readLine()) != null) {
				System.out.println(result);
			}
		}else { // 로그인 비정상적으로 됐을 때
			System.out.println("Server Down ...");
		}
		
//		conn.disconnect();
	}

}

```



**Http4.java**

- thread 만들고
- thread invocation해서
- main thread에서 id,pwd scanner로 입력 받기
- thread를 통해 서버로 전송하기.

```java
class SendThread extends Thread {
	String id;
	String pwd;
	
	public void setId(String id) {
		this.id = id;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void run() {
		String urlstr = "http://70.12.60.90/test/login.jsp";
		String id = "aa";
		String pwd = "11";
		urlstr += "?id=" + id + "&pwd=" + pwd;

		URL url = null;

		try {
			url = new URL(urlstr);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HttpURLConnection conn = null;
		InputStream is = null;
		try {
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(5000);
			
			is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String result = null;
				while((result=br.readLine()) != null) {
					System.out.println(result);
				}
			}else { // 로그인 비정상적으로 됐을 때
				System.out.println("Server Down ...");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
}

public class Http4 {

	public static void main(String[] args) {
		// thread 만들고
		// thread invocation해서
		// main thread에서 id,pwd scanner로 입력 받기
		// thread를 통해 서버로 전송하기.
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Input .. ");
			String id = sc.next();
			String pwd = sc.next();

			SendThread st = new SendThread();
			st.setId(id);
			st.setPwd(pwd);
			st.start();
		}
	}

}

```



## 2.  소켓 프로그래밍

TCP/IP : 컴퓨터끼리의 통신규약

http : 서버와 클라이언트 간 통신 규약 

### 2.1 TCP와 UDP 

**package : tcp1**

- 클라이언트가 서버로 데이터 전송하는 구조

<img src="../img/Networking_Http.png" weigth="500px">

**Client.java**

```java
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
			e.printStackTrace();
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
	client = new Client("70.12.60.111",8888);
	client.connect();
	try {
		client.request();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
```

**Server.java**

```java
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
		server = new Server(8888);
		server.startServer();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
```



------

## workshop

- 보내는 쪽 서버
-  쓰레드로 변경시킨다.
- 동시에 여러 client가 서버에 접속할 경우
- 각 client가 요구한 작업을 수행할 수 있도록.

