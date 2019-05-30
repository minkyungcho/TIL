## 데이터베이스와 SQL

### 데이터베이스란?

- 대량의 정보를 컴퓨터가 효율적으로 접근할 수 있도록 가공 및 저장한 것
- RDBMS : 관계형 데이터베이스 관리 시스템

### 데이터베이스 구성

- 일반적으로 RDBMS는 '클라이언트-서버' 시스템 구성을 가진다
- 데이터베이스의 게이터 처리를 위해서는 서버인 RDBMS에 클라이언트가 SQL 문을 전송한다
- 관계형 데이터베이스에서는 '테이블' 또는 '표'라고 하는 2차원 표를 사용해서 데이터를 관리한다.
- 테이블은 데이터 항목을 나타내는 '열(칼럼)'과 한 건의 데이터를 나타내는 '행(레코드)'으로 구성된다. 데이터 읽기/쓰기는 레코드 단위로 이루어진다.
- 열과 행이 교차하는 하나의 칸을 이 책에서는 '셀'이라고 부른다. 하나의 셀에는 하나의 데이터만 저장된다



### SQL 개요

- SQL은 데이터베이스를 제어하기 위해 개발된 언어다.

- SQL에는 표준규격이 존재하지만, 실제로는 RDBMS별로 차이가 있다.

- SQL에서는 처리하고 싶은 내용을 하나의 구문(SQL문)으로 기술하고 RDBMS에 전송한다.

- 원칙적으론 문장 끝에 구분자로 세미콜론(;)을 붙여야 한다.

- SQL은 목적에 따라 DDL, DML, DCL로 분류할 수 있다.

  - DDL(Data Definition Language) 데이터 정의 언어

    CREATE : 데이터베이스나 테이블 등을 작성한다.

    DROP : 데이터베이스나 테이블 등을 삭제한다.

    ALTER : 데이터베이스나 테이블 등의 구성을 변경한다.

  - DML(Data Manipulation Language) 데이터 조작 언어

    SELECT : 테이블에서 행을 검색한다.

    INSERT : 테이블에 신규 행을 등록한다.

    DELETE : 테이블에서 행을 삭제한다.

    UPDATE

  - DCL(Data Control Language) : 데이터 제어 언어

    COMMIT : 데이터베이스 변경 내용을 확정한다.

    ROLLBACK : 데이터베이스 변경 내용을 취소한다.

    GRANT : 사용자에게 처리 권한을 부여한다.

    REMOVE : 사용자 처리 권한을 제거한다.

#### SQL의 기본적인 작성 규칙

- SQL 문 마지막에 세미콜론(;)을 붙인다
- 대문자, 고문자 구분이 없다
  - 키워드는 대문자
  - 테비블명은 첫 문자만 대문자
  - 그 외의 경우(열명 등)는 소문자
  - 테이블에 등록된 데이터에 한해서는 대문자/소문자가 구분된다.
- 상수 작성법에는 규칙이 있다.
  - 문자열을 작은따옴표(')로 감싸서 그것이 문자열임을 가리킨다. 'abc'
- 단어는 공백 문자나 줄바꿈 문자로 구분한다.

### 테이블 작성

- DDL(Data Definition Language) 데이터 정의 언어

  CREATE : 데이터베이스나 테이블 등을 작성한다.

  ```sql
  CREATE TABLE T_USER(
  	ID VARCHAR2(10),
      PWD VARCHAR2(10),
      NAME VARCHAR2(20)
  );
  DESC T_USER; // TABLE 확인
  ```

  DROP : 데이터베이스나 테이블 등을 삭제한다.

  ```sql
  DROP TABLE T_USER;
  ```

  ALTER : 데이터베이스나 테이블 등의 구성을 변경한다.

  ```SQL
  ALTER TABLE T_PRODUCT ADD (REGDATE DATE);
  ALTER TABLE T_PRODUCT DROP (REGDATE);
  ALTER TABLE T_PRODUCT ADD PRIMARY KEY(ID);
  ALTER TABLE T_PRODUCT MODIFY(PWD CHAR(10)); // 컬럼의 변수타입 바꾸기
  ALTER TABLE T_PRODUCT MODIFY(NAME NULL); // 컬럼의 변수타입 바꾸기
  ALTER TABLE T_PRODUCT RENAME COLUMN NAME TO PWD; // 컬럼 명 바꾸기
  ALTER TABLE T_PRODUCT RENAME TO PRODUCT; // TABLE 명 바꾸기
  ```

- DML(Data Manipulation Language) 데이터 조작 언어

  SELECT : 테이블에서 행을 검색한다.

  INSERT : 테이블에 신규 행을 등록한다.

  ``` SQL
  INSERT INTO T_USER () VALUES ();
  ```

  DELETE : 테이블에서 행을 삭제한다.

  ``` SQL
  DELETE FROM T_USER;
  DELETE FROM T_USER WHERE ID='id01';
  ```

  UPDATE

  ``` SQL
  UPDATE T_USER SET PWD='111',NAME='공말숙';
  UPDATE T_USER SET PWD='111',NAME='공말숙' WHERE ID='id03';
  ```

  

- DCL(Data Control Language) : 데이터 제어 언어

  COMMIT : 데이터베이스 변경 내용을 확정한다.

  ROLLBACK : 데이터베이스 변경 내용을 취소한다.

  GRANT : 사용자에게 처리 권한을 부여한다.

  REMOVE : 사용자 처리 권한을 제거한다.



> oracle library 이클립스에 추가하기
>
> 프로젝트-properties-java build path-libraries-add external jars-
>
> C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib

