# 16 네트워킹 Networking

### 1.1 클라이언트 client / 서버 server

서버는 서비스를 제공하는 컴퓨터

클라이언트는 서비스를 사용하는 컴퓨터

**네트워크 구성**

- 서버기반모델 : 전용서버를 두는 것
- P2P모델 : 별도의 전용서버 없이 각 클라이언트가 서버역할을 동시에 수행하는 것

| 서버기반 모델                | P2P 모델                  |
| ---------------------------- | ------------------------- |
| 안정적인 서비스 제공 가능    | 서버구축 및 운용바용 절감 |
| 공유 데이터 관리와 보안 용이 | 자원 활용 극대화          |
| 서버구축비용과 관리비용 발생 | 보안 취약                 |





### 1.4 URL

**스크린 스크랩핑**

**Http1.java**

```java
public static void main(String[] args) throws Exception {
		URL url = new URL("http://70.12.60.90/test");
		URLConnection conn = url.openConnection();
		
		InputStream is = conn.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		
		int data = 0;
		StringWriter sw = new StringWriter();
		while((data=bis.read()) != -1) {
			char c = (char)data;
			sw.write(c);
		}
		System.out.println(sw.toString());
	}
--------------------------
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>¾?³???¼¼¿?</h1>
</body>
</html>
```



- 한글깨짐 현상 해결

**HttpWS.java**

```java
public static void main(String[] args) throws Exception {
		URL url = new URL("http://70.12.60.90/test");
		
		InputStream is = url.openStream();
		
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String data = null;
		StringWriter sw = new StringWriter();
		BufferedWriter bw = new BufferedWriter(sw);
		
		while((data=br.readLine()) != null) {
			System.out.println(data);
			bw.write(data);
			bw.newLine();
			
		}
	}
---------------------------------------
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>안녕하세요</h1>
</body>
</html>
```

