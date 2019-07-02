# SPRING

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



