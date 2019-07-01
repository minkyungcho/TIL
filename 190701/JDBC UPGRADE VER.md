## JDBC UPGRADE VER

### I. index 파일 수정

- req?type=user에서 user.do로 변경

- web.xml에 내용 추가(servlet 등록)
  - .do 가 있으면 DispatcherServlet으로 가게끔
  - dispatcher 역할 : req와 같은 역할(모든 암호를 복호화함). url 짧게 만들기
- src에 web.dispatcher 패키지 생성 후 DispatcherAervlet.java, Navi.java 붙여넣기
- index.jsp -> main.jsp로 파일명 수정
- index.html 생성
- index.html->main.do->dispatcher->main.jsp

