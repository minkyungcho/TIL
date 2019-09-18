## 8. 쓰레드의 실행제어

### 쓰레드 스케쥴링과 관련된 메서드

| 메서드                         | 설명                                                         |
| :----------------------------- | ------------------------------------------------------------ |
| static void sleep(long millis) | 지정된 시간동안 쓰레드를 일시정지시킨다. 지정한 시간이 지나고나면, 자동적으로 다시 실행대기상태가 된다. |
| void join()                    | 지정된 시간동안 쓰레드가 실행되도록 한다.                    |
| void interrupt()               | 일시정지상태인 쓰레드를 깨워서 실행대기상태로 만든다.        |
| void stop()                    | 즉시 종료. (되도록 쓰지 않는게 좋다)                         |
| void suspend()                 | 쓰레드를 일시정지시킨다. resume()을 호출하면서 다시 실행대기상태가 된다. |
| void resume()                  | suspend()에 의해 일시정지상태에 있는 쓰레드를 실행대기상태로 만든다. |
| static void yield              | 실행 중에 자신에게 주어진 실행시간을 다른 쓰레드에게 양보(yield)하고 자신은 실행대기상태가 된다. |



### 쓰레드의 상태

| 상태                   | 설명                                                         |
| ---------------------- | ------------------------------------------------------------ |
| NEW                    | 쓰레드가 생성되고 아직 start()가 호출되지 않은 상태          |
| RUNNABLE               | 실행 중 또는 실행 가능한 상태                                |
| BLOCKED                | 동기화블럭에 의해서 일시정지된 상태(lock이 풀릴 때까지 기다리는 상태) |
| WAITING, TIMED_WAITING | 쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은(runnable) 일시정지상태. TIMED_WAITING은 일시정지시간이 지정된 경우. |
| TERMINATED             | 쓰레드의 작업이 종료된 상태                                  |



**그림 P.749**



- 5초 이후에 thread 끝남.
- interrupt 활용

```java
class Th1 extends Thread{
	public void run() {
		while(!isInterrupted()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			System.out.println("Th1");
		}
		System.out.println("Th1 END ...");
	}
}

public class Inter1 {
	
	public static void main(String[] args) {
		Th1 th1 = new Th1();
		th1.start(); // 멈춰있지 않는다.
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th1.interrupt();
	}
}	
```

> sleep 상태에서 interrupt를 만났을 경우 exception 발생.



- cmd(input)에 따라서 쓰레드 제어하기  **Suspend.java**

```java
package day02;

import java.util.Scanner;

class Sus implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println("-");
			// thread의 name과 id 출력
//			System.out.println(
//					Thread.currentThread().getName()
//					+" "+
//					Thread.currentThread().getId()
//					);
		}
	}
}

public class Suspend {
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Sus(), "s1"); // 쓰레드에 이름 "s1" 지정
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("input cmd ? ");
			int cmd = sc.nextInt();
			if(cmd == 1) {
				t1.start();
			}else if(cmd == 2) {
				t1.suspend(); // duplicated
				System.out.println("Suspended");
			}else if(cmd == 3) {
				t1.resume();
			}else if(cmd == 4) {
				t1.stop();
			}else if(cmd == 9) {
				return;
			}
		}
	}
}
```

- start 한 후 stop하고 다시 start하면 오류.

- 해결방법

```java
public static void main(String[] args) {
		Thread t1 = null; // null로 객체만 만들어 두기.
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("input cmd ? ");
			int cmd = sc.nextInt();
			if(cmd == 1) {
				t1 = new Thread(new Sus(), "s1"); // start할때마다 새로운 Thread 생성
				t1.start();
			}else if(cmd == 2) {
				t1.suspend(); // duplicated
				System.out.println("Suspended");
			}else if(cmd == 3) {
				t1.resume();
			}else if(cmd == 4) {
				t1.stop();
			}else if(cmd == 9) {
				return;
			}
		}
	}
```



- suspend & resume 구현 **Suspend2.java**

```java
package day02;

import java.util.Scanner;

class Sus2 implements Runnable{
	
	boolean spd = false; // suspend resume
	boolean stp = false; // suspend stop
	
	public void setStop() {
		stp = true;
	}
	public void setSus() {
		stp = true;
	}
	public void setRes() {
		stp = false;
	}
	
	
	@Override
	public void run() {
		while(!stp) {
			if(!spd) {
				// suspend가 아니면 실행 되어야함.
				System.out.println("-");
			}
			
		}
	}
}

public class Suspend2 {
	
	public static void main(String[] args) {
		Thread t1 = null; // 쓰레드에 이름 "s1" 지정
		Sus2 sus2 = null;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("input cmd ? ");
			int cmd = sc.nextInt();
			if(cmd == 1) {
				sus2 = new Sus2();
				t1 = new Thread(sus2, "s2");
				t1.start();
			}else if(cmd == 2) {
				sus2.setSus();
				System.out.println("Suspended");
			}else if(cmd == 3) {
				sus2.setRes();
			}else if(cmd == 4) {
				sus2.setStop();
			}else if(cmd == 9) {
				return;
			}
		}
	}
}

```



### join()

- 1부터 10까지 더함

```java
package day02;

class Th2 extends Thread{
	
	int sum;
	
	public int getSum() {
		return sum;
	}
	
	public void run() {
		int i = 1;
		while(!isInterrupted()) {
			sum += i; // 1~10까지 덧셈
			if(i == 10) {
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
//			System.out.println("Th2:"+i);
			i++;
		}
		System.out.println("Th2 END ...");
	}
}

public class Join {
	
	public static void main(String[] args) {
		Th2 th2 = new Th2();
		th2.start(); 
		System.out.println("sum:"+th2.getSum());
	}
}
----------------------------------
sum:0
```

- th2 시작하고 바로 다음줄 출력되어서 sum:0이 출력됨
- 해결방법 : **join** 활용

```java
public class Join {
	public static void main(String[] args) {
		Th2 th2 = new Th2();
		System.out.println("Start ...");
		th2.start(); 
		try {
			th2.join(); // th2 끝날때까지 기다림
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("sum:"+th2.getSum());
	}
}
---------------------------------------------------
Start ...
sum:55
```



---

## workshop

Main Thread 에서 

2개의 Thread를 동작하여 결과의 합을 구한다.

첫번째 Thread는 1~100까지의 합,

두번째 Thread는 101~150까지의 합

두 Thread의 합을 다시 더해서 결과를 출력한다.

```java
package day02;

class Th3 extends Thread {
	int sum;
	int s, e;

	public Th3(int s, int e) {
		this.s = s;
		this.e = e;
	}

	public int getSum() {
		return sum;
	}

	@Override
	public void run() {

		if (s == 1) {
			while (!isInterrupted()) {
				sum += s;
				if (s == 100) {
					return;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
				s++;
			}
		} else if (s == 101) {
			while (!isInterrupted()) {
				sum += s;
				if (s == 150) {
					return;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}
				s++;
			}
		}
	}
}

public class Join2 {

	public static void main(String[] args) {
		Th3 t1 = new Th3(1, 100);
		Th3 t2 = new Th3(101, 150);

		System.out.println("Start ...");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int tsum = t1.getSum() + t2.getSum();
		System.out.println("Total Sum : " + tsum);
	}

}
--------------------------------------
Start ...
Total Sum : 11325
```



## 9. 쓰레드의 동기화

한 쓰레드가 진행 중인 작업을 다른 쓰레드가 간섭하지 못하도록 막는 것을 '쓰레드의 동기화'라고 한다.



