> 하둡을 설치하고 실행할 계정을 생성하고 그 환경에서 실습해보자.

### 2.4 하둡 실행 계정 생성

- 4개의 hadoop1,2,3,4 서버를 생성할 것.



- namenode : 모든 요청사항 수행, 외부에서 빅데이터 환경 들어올 때 namenode 통해서 들어온다,.
- secondary namenode : namenode가 죽고 새로운 namenode가 recovery 할때 백업된 data를 보내주는 역할 
- datanode : 정보가 저장되는 곳

### 2.10 하둡 환경설정 파일 수정

- 내가 생성한 pub_key를 다른 서버에 복사해서 놓아두면, 다른서버에 자유롭게 왔다갔다 할수 있음
- 환경설정 파일 수정
  - hadoop-env.sh
  - masters : 보조네임노드 정보
  - slavers : 데이터노드 정보
  - core-site.xml : 네임노드에 들어오는 포트(9000:리눅스서버가 보통 사용하지 않는 포트) 설정 
  - hdfs-site.xml
  - mapred-site.xml

네임노드에서 하둡 세팅이 끝났으면 다른서버로 보내줌

다른서버에서 세팅된것 받아서 압축 풀면 하둡 설치됨.



HADOOP1 

displayname hadoop1으로 수정

vmware에 HADOOP1 올림

네트워크 NAT으로 바꾸고 MAC주소 새로 generate 

MAC : 00:50:56:27:64:2C

```
hostnamectl set-hostname hadoop1
hostname으로 확인

/etc/sysconfig/network-scripts/ifcfg-ens33  수정
- MAC 주소
- IPADDR=192.168.111.201
- GATEWAY=192.168.111.2
- DNS1=192.168.111.2
systemctl restart network

/etc/hosts 에 추가
192.168.111.201 hadoop1
192.168.111.202 hadoop2
192.168.111.203 hadoop3
192.168.111.204 hadoop4


```



HADOOP1 복사붙여넣기해서 2,3,4 만들기

HADOOP2 : 00:50:56:2A:16:84

HADOOP3 : 00:50:56:20:08:9A

HADOOP4 : 00:50:56:39:49:FC





HADOOP1

> 하둡1에서 자유롭게 2,3,4로 들어가기 위해 하는 작업

```
ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa

cd .ssh

cat id_dsa.pub >> authorized_keys
// 생성한 퍼블릭 키를 각각의 서버에 전송해야함.

.ssh > ssh root@hadoop2 cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
// hadoop1에서 hadoop2로 자유롭게 들어가기 위해 authorized key 보내기
.ssh > scp authorized_keys root@hadoop2:~/.ssh/authorized_keys

ssh root@hadoop4 ls ~/.ssh
// 비밀번호 안물어보고 되는지 확인해보기.

ssh hadoop2
// hadoop2로 들어가지는지 확인. 비밀번호 안물어봄

[root@hadoop1 .ssh]# ssh root@hadoop4 ls ~/.ssh
authorized_keys
id_dsa
id_dsa.pub
[root@hadoop1 .ssh]# ssh hadoop2
Last login: Tue Aug  6 11:03:39 2019
[root@hadoop2 ~]# exit
logout
Connection to hadoop2 closed.
[root@hadoop1 .ssh]# ssh hadoop3
Last login: Tue Aug  6 10:52:22 2019
[root@hadoop3 ~]# exit
logout
Connection to hadoop3 closed.
[root@hadoop1 .ssh]# ssh hadoop4
Last login: Tue Aug  6 10:57:05 2019
[root@hadoop4 ~]# exit
logout
Connection to hadoop4 closed.
[root@hadoop1 .ssh]# 
```



HADOOP1에 hadoop 설치

- hadoop.tar 복사 붙여넣기

- hadoop 압출풀고 /etc 로 복사

- /etc/profile 환경변수 설정

- /etc/hadoop-1.2.1/conf/hadoop-env.sh 수정

- /etc/hadoop-1.2.1/conf/masters 수정

  ```
  hadoop2
  ```

- /etc/hadoop-1.2.1/conf/slaves 수정

  ```
  hadoop2
  hadoop3
  hadoop4
  ```

- /etc/hadoop-1.2.1/conf/core-site.xml

  ```
  <configuration>
  <property>
      <name>fs.default.name</name>
      <value>hdfs://192.168.111.201:9000</value>
    </property>
    <property>
      <name>hadoop.tmp.dir</name>
      <value>/etc/hadoop-1.2.1/tmp</value>
    </property>
  </configuration>
  ```

- /etc/hadoop-1.2.1/conf/hdfs.xml

  ```
  <configuration>
  <property>
      <name>dfs.permissions</name>
      <value>false</value>
    </property>
    <property>
      <name>dfs.replication</name>
      <value>2</value>
    </property>
    <property>
      <name>dfs.http.address</name>
      <value>192.168.111.201:50070</value>
    </property>
    <property>
      <name>dfs.secondary.http.address</name>
      <value>192.168.111.202:50090</value>
    </property>
    <property>
      <name>dfs.name.dir</name>
      <value>/etc/hadoop-1.2.1/name</value>
    </property>
    <property>
      <name>dfs.data.dir</name>
      <value>/etc/hadoop-1.2.1/data</value>
    </property>
  </configuration>
  ```

- /etc/hadoop-1.2.1/conf/mapred-site.xml

  ```
  <configuration>
    <property>
     <name>mapred.job.tracker</name>
     <value>192.168.111.201:9001</value>
    </property> 
  </configuration>
  ```

- /etc/bashrc

  ```
  . /etc/hadoop-1.2.1/conf/hadoop-env.sh
  ```

  

```
hadoop1의 요청받은것 2,3,4에 뿌리기
설정 바꾼 hadoop을 tar로 묶어서 2,3,4한테 보내기
tar cvfz hadoop.tar.gz hadoop-1.2.1
ls -la hadoop.tar.gz

scp hadoop.tar.gz root@hadoop2:/etc

ssh root@hadoop2 tar xvf /etc/hadoop.tar.gz ==> hadoop2의 home에 hadoop-1.2.1로 풀림.

//rm -rf hadoop.tar.gz

home > mv hadoop-1.2.1 /etc

scp /etc/bashrc root@hadoop2:/etc


reboot

hadoop namenode -format
>> successfully formattted 나오면 정상적으로 된것.

start-all.sh // namenode, datanode, secondarynamenode 다 구동시킴
jps로 확인
```

:star: 다시다시 

```
systemctl stop firewalld
systemctl disable firewalld

tar cvfz hadoop.tar.gz hadoop-1.2.1

scp hadoop.tar.gz root@hadoop2,3,4:/etc

ssh root@hadoop2,3,4 "cd /etc;tar xvfz hadoop.tar.gz;rm -rf hadoop.tar.gz"

hadoop namenode -format

start-all.sh

<hadoop1> jps
5173 Jps
5077 JobTracker
4890 NameNode

<hadoop2> jps
12081 TaskTracker
11921 DataNode
12217 Jps
11982 SecondaryNameNode

<hadoop3> jps
13372 Jps
13231 TaskTracker
13151 DataNode

<hadoop4> jps
13249 TaskTracker
13169 DataNode
13383 Jps



```



프로그램에 의한 분석(jobtracker)을 요청하면 tasktracker한테 요청이 감. 

데이터들 분석한 것들 취합해서 다시 jobtracker한테 줌

```
hadoop dfs -get /data/input README.txt RD.

hadoop jar hadoop-examples-1.2.1.jar wordcount /data/input /data/output
```



### workshop : 각각의 컴퓨터를 활용하여 분산 모드 환경 구축하기

![../img/HADOOP_분산환경_브릿지활용_ws]()

