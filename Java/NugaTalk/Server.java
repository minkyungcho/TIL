import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class SThread extends Thread {
	int port;
	Socket socket;
	OutputStream out;
	DataOutputStream dout;
	InputStream in;
	DataInputStream din;
	public SThread(Socket socket) throws IOException {
		this.socket = socket;
		out = socket.getOutputStream();
		dout = new DataOutputStream(out);
		in = socket.getInputStream();
		din = new DataInputStream(in);
	}
	public void run() {
		try {
			String str = din.readUTF();
			System.out.println(socket.getInetAddress() + " : " + str);
			dout.writeUTF("This Feeling is hard to find");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dout != null)
				try {
					dout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (din != null)
				try {
					din.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}

public class Server {
	boolean flag = true;
	ServerSocket serverSocket;
	Map<String, DataOutputStream> map = new HashMap<>();
	Map<String, String> nick = new HashMap<>();
	Map<String, String> nickip = new HashMap<>();
	ArrayList<String> userList = new ArrayList<String>();

	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server Start..");
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					while (flag) {
						System.out.println("Server Ready");
						Socket socket = serverSocket.accept();
						new ReCThread(socket).start();
						System.out.println(socket.getInetAddress() + " Connected");
						sendMsg(socket.getInetAddress() + " ���� �����߽��ϴ�");
					}
					System.out.println("Server Dead...");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(r).start();
	}

	public void start() throws IOException {
		Scanner sc = new Scanner(System.in);
		try {
			boolean inflag = true;
			System.out.println("Input Msg");
			while (inflag) {
				String instr = sc.next();
				sendMsg(instr);
				if (instr.equals("q"))
					break;

			}
		} catch (Exception e) {
			throw e;
		} finally {
			sc.close();
		}
	}

	public void sendMsg(String msg) {
		SendThread s = new SendThread();
		s.setMsg(msg);
		s.start();
	}

	public void sendWhisper(String msg, String ip, String me) {
		SendThread s = new SendThread();
		s.setMsg(msg);
		s.setTarget(ip);
		s.setMe(me);
		s.setCmd(1);
		s.start();
	}

	class SendThread extends Thread {
		String msg;
		int cmd = 0;
		String target;
		String me;

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public void setMe(String me) {
			this.me = me;
		}

		public void setCmd(int cmd) {
			this.cmd = cmd;
		}

		public void setTarget(String t) {
			this.target = t;
		}

		public void run() {
			if (cmd == 0) {
				Collection<DataOutputStream> col = map.values();
				Iterator<DataOutputStream> it = col.iterator();
				while (it.hasNext()) {
					try {
						it.next().writeUTF(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (cmd == 1) {
				DataOutputStream doutt;
				DataOutputStream doutt2;
				doutt = map.get(target);
				doutt2 = map.get(me);
				try {
					doutt.writeUTF(msg);
					doutt2.writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class ReCThread extends Thread {
		Socket socket;
		InputStream in;
		DataInputStream din;
		OutputStream out;
		DataOutputStream dout;
		boolean rflag = true;
		String ip;
		String name;
		public ReCThread() {
		}
		public ReCThread(Socket socket) throws IOException {
			this.socket = socket;
			in = socket.getInputStream();
			din = new DataInputStream(in);
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			ip = socket.getInetAddress().toString();
			if (nick.get(ip) != null)
				name = nick.get(ip);
			map.put(ip, dout);
			System.out.println("������ ��: " + map.size());
		}
		public void run() {
			try {
				while (rflag) {
					String str = din.readUTF();
					if (str == null || str.equals(""))
						continue;
					System.out.println(str);
					String sendId;
					boolean flag = true;
					if (str.charAt(0) == '!') {
						if (str.equals("!q")) {
							str = "���� �������ϴ�!";
							String snd;
							if (!nick.containsKey(ip))
								snd = ip;
							else
								snd = nick.get(ip);
							sendMsg(snd + ": " + str);
							map.remove(ip);
							nickip.remove(nick.get(ip));
							nick.remove(ip);
							System.out.println("�����������ڼ�:" + map.size());
							break;
						} else if (str.equals("!l")) {
							sendMsg(getClients());
						} else if (str.length() > 6) {
							if (str.substring(0, 6).equals("!nick:")) {
								System.out.println("�г��� ����");
								String unick = str.substring(6);
								nick.put(ip, unick);
								nickip.put(unick, ip);
								sendMsg(ip + "�г��� ���� -> " + unick);
							} else if (str.substring(0, 2).equals("!w")) {
								String[] spil = str.split(":");
								if (nickip.containsKey(spil[1])) {
									String keys = nickip.get(spil[1]);
									sendWhisper("[�Ӹ�]" + nick.get(ip) + ": " + spil[2], keys, ip);
								}
							}
						} else if (str.equals("!rule")) {
							sendMsg("ä�ñ�Ģ:�弳����,������,��������,ä�ýǸ���,�Ҹ�����,�������,ī�����");
						} else if (str.equals("!cmd")) {
							sendMsg("## !q   !l   !nick:  !w:nickname:���� !rule ##");
						}
					} else {
						if (!nick.containsKey(ip))
							sendId = ip;
						else {
							sendId = nick.get(ip);
							System.out.println(sendId + ": " + str);
						}
						sendMsg(sendId + ": " + str);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("������ �ƿ�:" + ip);
				map.remove(ip);
				nickip.remove(nick.get(ip));
				nick.remove(ip);
				System.out.println("�����������ڼ�:" + map.size());
				if (din != null) {
					try {
						din.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		public String getClients() {
			String a = "[";
			Set s = map.keySet();
			Iterator it = s.iterator();
			boolean f = true;
			while (it.hasNext()) {
				String k = (String) it.next();
				if (nick.containsKey(k))
					if (f) {
						a += " " + nick.get(k);
						f = false;
					} else
						a += " , " + nick.get(k);
				else
					if (f) {
						a += " " + k;
						f = false;
					}
					else
						a += " , " + k;
			}
			return a + " ]";
		}
	}
	public static void main(String[] args) throws IOException {
		Server s = new Server(1234);
		s.start();
	}
	public static int getNicks(String c) {
		int l = c.length();
		String re = "";
		int i = 0;
		for (i = 0; i < l; i++) {
			if (c.charAt(i) != ' ') {
				re += c.charAt(i);
			} else
				break;
		}
		return i;
	}
}
