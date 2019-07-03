Biz Dao 구현 (generic)



frame

- Biz, Dao에서 crud 함수 생성
- UserOracleDao, UserMariaDao 생성 - Dao interface
- UserBiz 생성 - Biz interface
- UserBiz 
  - Dao 선언 후 Dao getter 생성

- myspring.xml 생성 - new spring

  - bean 만들기 - useroracledao, usermariadao, userbiz
  - userbiz bean에서 어떤 bean이랑 연결할건지 결정 p:dao-ref="uod"

  ※ mydao.xml, mybiz.xml 생성 - new spring

  - mydao - biz bean 삭제
  - mybiz - dao bean 삭제
  - myspring - bean 다 삭제 후 import

- App 생성

  - factory 생성 - myspring.xml
  - factory의 "ubiz"라는 콩을 Biz형으로 변환 해서 biz에 저장.
  - User 생성해서 biz가 user를 insert 실행하도록.