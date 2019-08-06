# I. 하둡기초

## *part01 하둡살펴보기*

## 빅데이터

- 데이터를 수집, 저장, 관리, 분석하는 역량을 넘어서서 데이터(정형, 비정형) 집합으로부터 가치를 추출하고 결과를 분석하는 기술

- 3대 요소 (3V) : 크기(Volume), 속도(Velocity), 다양성(Variety)

  +) 정확성(Veracity), 가치(Value)

- 하둡?

  : 대용량 데이터를 분산 처리할 수 있는 자바 기반의 오픈소스 프레임워크

  : 저렴한 구축비용, 비용대비 빠른 데이터 처리, 



## *part02 하둡개발 준비*

## HADOOP

- 독립 실행 모드 : 기본 실행 모드
- 가상 분산 모드 : 하나의 서버에 완벽한 분산 환경 구축
- 완전 분산 모드 : 여러 대의 서버에 하둡이 설치된 경우



<img src="C:\Users\student\Desktop\가상분산모드.png" width="250px">



## 하둡 설치

:white_check_mark: HADOOPSERVER1 복붙해서 HADOOP1 만들어두기

#### *step00 필요한 파일 확인*

- HADOOPSERVER1 복사해서 백업해놓기

- HADOOPSERVER1을 가상 분산 모드로 사용할 예정

- 필요 파일 다운로드

  - java

  - tomcat

  - eclipse

  - mysql (MariaDB 설치)

  - hadoop 

#### *step01 hadoop 설치*

- firewall 설정 변경

  ``` 
  systemctl disable firewalld
  ```

> https://archive.apache.org/dist/hadoop/common/hadoop-1.2.1/[hadoop-1.2.1.tar.gz](http://apache.mirror.cdnetworks.com/hadoop/core/hadoop-1.2.1/hadoop-1.2.1.tar.gz)
>
> hadoop-1.2.1.tar.gz

- filetemp에 복사 붙여넣기 후 압축 풀고 /etc로 이동

  ```
  tar xvf hadoop-1.2.1.tar.gz
  cp -r hadoop-1.2.1 /etc
  ```

- /etc/profile 수정

  ```
  HADOOP_HOME=/etc/hadoop-1.2.1
  export JAVA_HOME CLASSPATH TOMCAT_HOME HADOOP_HOME
  PATH=.:$JAVA_HOME/bin:$TOMCAT_HOME/bin:$HADOOP_HOME/bin:$PATH
  ```

- hostname 바꾸기

  ```
  hastnamectl set-hostname hadoopserver1
  ```

- /etc/hosts : hadoopserver1만 있도록 수정
- /etc/sysconfig/network-scripts/ifcfg-ens33 에서 IP 확인

#### *step02 ssh 설정*

- ssh : 서버와 서버끼리 왕래할때 비밀번호 물어보는과정 생략하기 위해. 더 자유롭게 왕래할 수 있도록하려고


```
[root@hadoopserver1 ~]# ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa  //private, public key 만들기
Generating public/private dsa key pair.
Your identification has been saved in /root/.ssh/id_dsa.
Your public key has been saved in /root/.ssh/id_dsa.pub.
The key fingerprint is:
f0:a2:59:59:9b:4a:bc:3f:ae:54:f7:fd:af:b3:e0:5f root@hadoopserver1
The key's randomart image is:
+--[ DSA 1024]----+
|                 |
|                 |
|      . .        |
|     . = o       |
|      * S .      |
|     = = . . .   |
|    o +     o . E|
|     . ..  . ..o |
|      .oo.  ..+++|
+-----------------+
[root@hadoopserver1 ~]# ls -a
.              .config         .tcshrc               문서
..             .cshrc          .viminfo              바탕화면
.ICEauthority  .dbus           anaconda-ks.cfg       비디오
.bash_history  .esd_auth       filetemp              사진
.bash_logout   .local          initial-setup-ks.cfg  서식
.bash_profile  .mozilla        temp                  음악
.bashrc        .mysql_history  공개
.cache         .ssh            다운로드
[root@hadoopserver1 ~]# cd .ssh/
[root@hadoopserver1 .ssh]# ls
id_dsa  id_dsa.pub  known_hosts
[root@hadoopserver1 .ssh]# cat id_dsa.pub >> authorized_keys
[root@hadoopserver1 .ssh]# ls
authorized_keys  id_dsa  id_dsa.pub  known_hosts
[root@hadoopserver1 .ssh]# cd
[root@hadoopserver1 ~]# ssh hadoopserver1 
Last login: Mon Aug  5 14:05:07 2019 from hadoopserver1
[root@hadoopserver1 ~]# exit
logout
Connection to hadoopserver1 closed.
```

- private, public key 만들기

  ```
  ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa
  ```

- .ssh 에서 key 생성된거 확인

  ```
  .ssh > ls
  id_dsa  id_dsa.pub
  ```

  - id_dsa(private key)  id_dsa.pub(public key)

- id_dsa.pub(public key) 등록시킴

  ```
  cat id_dsa.pub >> authorized_keys
  ```

- authorized_keys 생성 확인

  ```
  .ssh > ls
  authorized_keys  id_dsa  id_dsa.pub  known_hosts
  ```

- 비밀번호 없이 연결 확인

  ```
  > ssh hadoopserver1 
  Login
  ```

#### *step03 hadoop conf 설정*

- /etc/hadoop-1.2.1/conf/core-site.xml

  ```
  <configuration>
  <property>
    <name>fs.default.name</name>
    <value>hdfs://localhost:9000</value>
  </property>
  <property>
    <name>hadoop.tmp.dir</name>
    <value>/etc/hadoop-1.2.1/tmp</value>
  </property>
  </configuration>
  ```

- /etc/hadoop-1.2.1/conf/hdfs-site.xml

  ```
  <configuration>
  <property>
    <name>dfs.replication</name>
    <value>1</value>
  </property>
  <property>
    <name>dfs.name.dir</name>
    <value>/etc/hadoop-1.2.1/name</value>
  </property>
  <property>
    <name>dfs.data.dir</name>
    <value>/etc/hadoop-1.2.1/data</value>
  </property>
  <property>
    <name>dfs.webhdfs.enabled</name>
    <value>true</value>
  </property>
  </configuration>
  ```

- /etc/hadoop-1.2.1/conf/mapred-site.xml

  ```
  <configuration>
  <property>
    <name>mapred.job.tracker</name>
    <value>localhost:9001</value>
  </property>
  </configuration>
  ```

- /etc/hadoop-1.2.1/conf/hadoop-env.sh 9번째줄 수정

  ```
  export JAVA_HOME=/etc/jdk1.8
  export HADOOP_HOME_WARN_SUPPRESS="TRUE" // 오류메세지 발생 방지
  ```

- /etc/bashrs 맨 아래에 추가 

  ```
  . /etc/hadoop-1.2.1/conf/hadoop-env.sh
  ```

  - 서버 켤때마다 env 실행되도록하는 문장

- hadoop 포맷

  ```
  hadoop namenode -format
  ```

  - /etc/hadoop-1.2.1 아래에 name, data, temp 디렉토리 생겼으면 제대로 포맷 된 것이다

- reboot

#### *step04 hadoop 실행*

- hadoop 시작

  ```
  start-all.sh
  ```

- 제대로 시작됐는지 확인

  ```
  > jps
  5506 Jps
  5427 TaskTracker
  5300 JobTracker
  4950 NameNode
  5080 DataNode
  5210 SecondaryNameNode
  ```

- hadoop 종료

  ```
  stop-all.sh
  ```

  

## 하둡으로 빅데이터 분석하기

#### *step00 모니터링 환경*

- firefox에서 http://localhost:50070 열기
- 모니터링용으로 사용
- Browse the filesystem --> etc 디렉토리 있는 것 확인

#### *step01 hadoop 명령어 활용*

> http://localhost:50070 에서 확인해보면서 진행

- 디렉토리 내용 출력 ls

  ```
  > hadoop dfs -ls /
  Found 1 items
  drwxr-xr-x   - root supergroup          0 2019-08-05 15:44 /etc
  ```

- 디렉토리 생성 후 확인

  ```
  > hadoop dfs -mkdir /test
  > hadoop dfs -ls /
  Found 2 items
  drwxr-xr-x   - root supergroup          0 2019-08-05 15:44 /etc
  drwxr-xr-x   - root supergroup          0 2019-08-05 16:17 /test
  ```

- 파일 복사 붙여넣기

  ```
  > hadoop dfs -put README.txt /test
  ```

#### *step02 WordCount*

- /data , /data/input1 디렉토리 만들기

  ```
  > hadoop dfs -mkdir /data
  > hadoop dfs -mkdir /data/input1
  ```

- 파일 복사 붙여넣기

  ```
  > hadoop dfs -put README.txt /data/input1
  ```

- 해당 파일 wordcount 해서 결과 /data/output1에 생성되게

  ```
  hadoop jar hadoop-examples-1.2.1.jar wordcount /data/input1 /data/output1
  ```

  - /data/output1 디렉토리 자동 생성되면서 분석됨

    ![](../Desktop/output.PNG)

  - part-r-00000 file에서 분석값 확인

    ![](../Desktop/분석.PNG)

    - 단어가 몇번 쓰였는지 옆에 숫자로 나타나있는걸 확인할 수 있다.

- /etc/hadoop-1.2.1/src/examples/org/apache/hadoop/examples에서 WordCount.java 코드에 어떤방식으로 단어 카운팅했는지 확인할 수 있다.



