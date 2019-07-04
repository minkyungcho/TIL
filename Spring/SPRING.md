# SPRING

# 01 

## I. FRAME WORK 개요

프레임워크 : 뼈대, 틀. sw 관점에서 접근하면 아키텍쳐에 해당하는 골격코드

장점

- 빠른 구현시간 : 프레임워크가 골격코드를 제공하면서 개발자는 비즈니스 로직만 구현하면 된다.

- 쉬운 관리 : 같은 프레임워크가 적용된 app들은 아키텍처가 같으므로 관리하기 쉽다.

- 개발자들의 역량 획일화 : 숙련된 개발자와 초급 개발자가 생성한 코드가 비슷하다.

- 검증된 아키텍처의 재사용과 일관성 유지


> container - component 모델
>
> servlet(component) 규칙 : httpservlet + constructor, service, doget, dopost
>
> 단점 : 규칙에 의해 만들어야함



POJO - Plain Old Java Object

: 옛날 자바 클래스들을 component 모델로 사용할 수 있음



스프링 프레임워크의 특징

- 경량
- 제어의 역행 (Inversion of Cintrol, IoC)
  - 유지 보수 향상

- 관점지향 프로그래밍(Aspect Oriented Programing, AOP)
  - 유지 보수 향상
- 컨테이너



## II. 스프링 컨테이너 및 설정 파일

- spring은 공식적이지 않아서 JDK에 없음

- spring 쓰기 위한 외부 lib 추가

- spring container : IoC Inversion of Control 역주행 제어

- 모든 객체는 spring에서 관리



메이븐 : 외부 lib를 관리하기 위해서 사용하는 기법

- spring은 eclipse에도 없어서 따로 설치해야함
  - marketplace에서 sts 검색 후 Spring Tools 3.0 다운
  - 프로젝트 우클릭 - Spring - Add spring project nature 선택
  - 프로젝트 우클릭 - configure - convert to Maven project 선택
    - C/사용자/student/.m2/repository 에 다운받아짐
    - pom.xml : 강사님이 올려주신 pom.xml로 복붙 후 groupId, artifactId 프로젝트명(day0111)으로 수정
  - 프로젝트 우클릭 - Maven - update project
  - project - java resource - src 우클 - new - others - spring - spring bean configuration file

 ### Srping Project Setting - 스프링 개발환경 세팅

#### 1. Make Project

#### 2. Spring Nature

#### 3. Maven(Add Spring Library)

- pom.xml (List up Library) : 필요한 것들을 기록해놓음.
- Download Library : 기록해놓은 것을 보고 이클립스가 해당 자료를 다운받아 저장함.

## III. 의존성 주입

의존성 주입은 코드에서 하지 않고 xml에서!



## IV. 어노테이션 기반 설정

### 어노테이션 설정 기초

- context 네임스페이스 추가
- 컴포넌트 스캔 설정
- <context:component-scan base-package="com.*"/>



### 의존성 주입 설정

1. 첫번째 방법 

- UserBiz 
  - 고유 이름 정해주기 @Component("ubiz")
  - @Autowired // 어떤게 들어와도 상관 없다
- myspring에 가서 bean 하나만 생성
  - <bean id="uod" class="com.user.UserOracleDao"/>
- App 실행

> Componenet == Repository

2. 두번째 방법

- myspring에 bean 안만들고
- UserOracleDao -  @Repository("uod")
- UserMariaDao -  @Repository("umd")
- UserBiz 
  - @Service("ubiz")
  - @Resource(name="uod") // 여기서 uod, umd 중 선택
- App 실행



# 02

## I. 스프링 AOP

### AOP 

- Aspect Oriented Programming

- 응집도와 관련된 기능

- 기존 객체지향언어에서는 횡단관심에 해당하는 공통 코드를 환벽하게 분리시키기 어렵다

  => 관심분리

  -  횡단관심 : 메소드마다 공통으로 등장하는 로깅이나 예외, 트랜잭션 처리 같은 코드
  - 핵심관심 : 사용자의 요청에 따라 실제로 수행되는 핵심 비즈니스 로직



#### LOG 찍기 

> day022

- LogAdvice class 생성

  ```java
  public class LogAdvice {
  	public void printlog() {
  		Date d = new Date();
  		System.out.println(d+"[공통로그]비즈니스 로직 수행...");
  	}
  }
  ```

- UserBiz에 추가

  ```java
  LogAdvice log; // 클래스 상단에 선언
  log = new LogAdvice(); // UserBiz constructor에 생성
  log.printlog(); // 모든 함수에 추가
  ```

  

### AOP 시작하기

> day022

- AOP lib 추가
- aop 네임스페이스 추가



### 관심분리

#### XML 적용 방식

>  java 코드에는 변화 없음. xml에만 추가.



#### Annotation 적용 방식

> java 코드 수정



## II.AOP 용어 및 기본 설정

#### AOP 용어 정리

- jointpoint 조인포인트
- pointcut 포인트컷
- advice 어드바이스 : 횡단관심에 해당하는 공통기능의 코드 (LogAdvice)

#### AOP 엘리먼트

```java
<aop:config> // 루트. 하위에 <aop:pointcut>, <aop:aspect>가 위치함.
<aop:pointcut> // 포인트컷 지정. 유일한 id 할당하여 aspect 설정할때 포인트컷을 참조하는 용도로 사용. 적용할 위치
<aop:aspect> // 포인스컷 메소드(핵심관심)와 어드바이스 메소드(횡단관심) 결합. 무엇을 어떻게 적용할 것인지
<aop:advisor> // 특수한 경우(트랜잭션 설정)의 포인트컷과 어드바이스 결합 aspect와 같은 기능.
```

## III. 어드바이스 동작 시점

#### before

포인트컷으로 지정된 메소드 호출 시, 메소드가 실행되기 전에 처리될 내용들을 기술.

``` java
<aop:before pointcut-ref="allPointcut" method="beforeLog"/>
```



#### after-returning

포인트컷으로 지정된 메소드가 정상적으로 실행되고 나서, 메소드 수행 결과로 생성된 데이터를 리턴하는 시점에 동작. 따라서 비즈니스 메소드 수행 결과로 얻은 결과 데이터를이용하여 사후 처리 로직을 추가할때 사용.

``` java
<aop:after-returning pointcut-ref="getPointcut" method="afterLog"/>
```



#### after-throwing

포인트컷으로 지정한 메소드가 실행되다가 예외가 발생하는 시점에 동작. 따라서 예외 처리 어드바이스를 설정할 때 사용.

``` java
<aop:after-throwing pointcut-ref="" method=""/>
```



#### after

try-catch-finally 구문에서 finally 블록처름 예외 발생 여부에 상관없이 무조건 수행되는 어드바이스를 등록할 때 사용.

``` java
<aop:after pointcut-ref=""method=""/>
```



#### around

하나의 어드바이스가 비즈니스 메소드 실행 전과 후에 모두 동작하여 로직을 처리하는 경우에 사용.

``` java
<aop:around pointcut-ref=""method=""/>
```



## IV. JoinPoint와 바인드 변수

#### JoinPoint 메소드

- Signature getSignature() 
  - 클라이언트가 호출한 메소드의 시그니처(리턴타입, 이름, 매개변수) 정보가 저장된 Signature 객체 리턴

- Object [] getArgs() 
  - 클라이언트가 메소드를 호출할 때 넘겨준 인자 목록을 Object 배열로 리턴
- String getName() 
  - 클라이언트가 호출한 메소드 이름 리턴













4. Spring JDBC(x), ORM(Mybatis) : JDBC를 쉽게 개발하기 위한 프레임워크
   - 패키지 구조 com.
     - frame - Biz, Dao
     - vo : value object - Product, User
     - user
     - product
     - app
     - mybatis : mybatis에서 사용하는 xml
   - myspring.xml
     - aop, bean, p, context, tx 네임스페이스 추가
     - <context:component-scan base-package="com.*"/>
     - 트랜잭션 어노테이션 이용 <tx:annotation-driven transaction-manager="txManager"/>
     - DB 세팅
     - 트랜잭션 세팅 - DATAsOURCE 사용
     - mybatis 세팅 - dataSource 필요, 환경설정 파일 위치
       - mybatis.xml 수정
       - 통로역할하는 mapper 생성
         - UserMapper, ProductMapper

5. Sprin MVC



# 03

## I. MyBatis 프레임워크 시작하기

- MyBatis : DB와 편하게 커뮤니케이션을 할 수 있도록 도와주는 프로그래밍을 짜는 프레임워크
- 상당부분을 xml로 관리. sql 문장 포함

### 프로젝트 생성





## II. 



@transactional: 이 함수를 트랜잭셔널하게 동작시킨다. 

- 아이디가 중복되어 두번 연속으로 insert 될때 1번째는 들어가고 2번째는 error로 인해 안들어가게 되는 현상을 둘다 안들어 가게끔 해결해준다. 



## III. Spring MVC

- web.xml에 환경설정
  - dispatcher 등록
  - 한글 깨짐 현상 해결 filter
- spring.xml 세팅
  - ViewResolver
- 