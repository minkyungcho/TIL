# 13 쓰레드 Thread

## 1. 프로세스와 쓰레드 

프로세스 : 프로그램이 동작하고 있는 것

쓰레드 : 프로세스 내에서 동작하고 있는 것

### 멀티태스킹과 멀티쓰레딩

멀티태스킹 : 여러개의 프로세스가 동시에 실행

멀티쓰레드 : 하나의 프로세스 내에서 여러 쓰레드가 동시에 작업을 수행

### 멀티쓰레딩의 장단점

장점

- CPU 사용률을 향상시킨다.
- 자원을 보다 효율적으로 사용할 수 있다.
- 사용자에 대한 응답성이 향상된다.
- 작업이 분리되어 코드가 간결해진다.

단점

- 여러쓰레드가 같은 프로세스 내에서 자원을 공유하면서 작업을 하기 때문에 동기화, 교착상태와 같은 문제점 발생.

> http : 접속해서 index.html 뿌려주고 접속 끝남.

## 2. 쓰레드의 구현과 실행

### Thread 만들기

Thread 상속받아서 만들기 **Th1.java**    [실습코드](https://github.com/minkyungcho/TIL/blob/master/Java/day01/Th1.java)

1. thread에서 상속
2. thread에서 멤버 var 갖기
3. run에서 실행됨



interface 활용해서 만들기 **Th2.java**    [실습코드](https://github.com/minkyungcho/TIL/blob/master/Java/day01/Th2.java)

자바는 싱글 inheritance이기 때문에 interface를 활용할 수 도 있다.

다른 class에서 상속을 받았더라도 thread 만들고 싶을때.



---

## workshop

scanner에서 입력 받은 숫자까지 for문을 이용하여 출력

**Th5.java**    [실습코드](https://github.com/minkyungcho/TIL/blob/master/Java/day01/Th5.java)

