**Tomcat 환경변수 설정**

- /etc/profile 에 추가

	```
	TOMCAT_HOME=/root/file/apache-tomcat-9.0.22
	export TOMCAT_HOME
	```

- . /etc/profile 로 설정 저장 후 확인

	```
	[root@server2 shell]# echo $TOMCAT_HOME
	/root/file/apache-tomcat-9.0.22
	```



***for1.sh***

```
#!/bin/sh
for i in 1 2 3 4
do
    echo " $i "
done
exit 0
------------------------
>for1.sh
 1
 2
 3
 4
```

***for2.sh***

```
#!/bin/sh                                                       ctn=0                                                           
for i in $(ls *.sh)                                             
do                                                               
    echo " $i "                                                
    ctn=`expr $ctn + 1`                                         
done                                                             
echo "COUNT: $ctn "                                             
exit 0  
-------------------------
>for2.sh
 case1.sh                                                      
 for1.sh                                                         
 if2.sh 
```

***for3.sh***

```
#!/bin/sh
ctn=0
for i in $(ls *.sh)
do
    if [ $i = "for1.sh" ]
    then
       echo "OK: $i"
    fi
    ctn=`expr $ctn + 1`
done 
echo "COUNT: $ctn "
exit 0
```



firewall

```
IDADDR=70.12.144.203
NETMASK=255.255.255.0
GATEWAY=70.12.114.1
DNS1=168.126.63.1


```



```
JAVA_HOME=/etc/jdk1.8
CLASSPATH=/etc/jdk1.8/lib
TOMCAT_HOME=/etc/tomcat
export JAVA_HOME CLASSPATH TOMCAT_HOME
PATH=.:$JAVA_HOME/bin:$TOMCAT_HOME/bin:$PATH

export PATH USER LOGNAME MAIL HOSTNAME HISTSIZE HISTCONTROL
```











ip setting 

70.12.114.215 : server1

70.12.114.216 : server2

mac 주소 세팅

hostname

/etc/hosts