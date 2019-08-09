## *part17 하이브*

하이브가 하둡에 들어갈 스트럭쳐 구조를 마리아디비에 저장



```
schematool -initSchema -dbType mysql
```

메타정보를 파일시스템(덩비)에 저장한다

대신에

mysql이라는 데베에 저장한다.

```
# hive
hive > select * from HDI;
```

/etc/hive/conf/hive-site.xml 수정

```
--- 수정 ---
--- <value>true</value> => <value>false</value> ---
<name>hive.metastore.local</name>
<value>false</value>
---추가---
<property>
<name>hive.cli.print.header</name>
<value>true</value>
</property>
```

false => mariaㅇdb에 메타데이터 저장

true => 덜비?에 저장



```
# mysql -u hive -p hive_db
[hive_db] > show tables; ==> 없음
[hive_db] > select * from TBLS; ==> hdi 있는 것 확인
```

<img src="../img/show_hdi.PNG">

```
mkdir data
data > wget http://stat-computing.org/dataexpo/2009/2006.csv.bz2
data > wget http://stat-computing.org/dataexpo/2009/2007.csv.bz2
data > wget http://stat-computing.org/dataexpo/2009/2008.csv.bz2
data > bzip2 -kd 2006.csv.bz2
data > bzip2 -kd 2007.csv.bz2
data > bzip2 -kd 2008.csv.bz2
data > rm -rf *bz2
```

```
hive > DayofMonth INT,DayofWeek INT,DepTime INT,CRSDepTime INT,ArrTime INT,CRSArrTime INT,UniqueCarrier STRING,FlightNum INT,TailNum STRING,ActualElapsedTime INT,CRSElapsedTime INT,AirTime INT,ArrDelay INT,DepDelay INT,Origin STRING,Dest STRING,Distance INT,TaxiIn INT,TaxiOut INT,Cancelled INT,CancellationCode STRINGCOMMENT 'A = carrier, B = weather, C = NAS, D = security',Diverted INT COMMENT '1 = yes, 0 = no',CarrierDelay STRING,WeatherDelay STRING,NASDelay STRING,SecurityDelay STRING,LateAircraftDelay STRING) COMMENT 'TEST DATA' PARTITIONED BY (delayYear INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' STORED AS TEXTFILE;

hive > LOAD DATA LOCAL INPATH '/root/data/2006.csv' OVERWRITE INTO TABLE airline_delay PARTITION (delayYear='2006');

hive > LOAD DATA LOCAL INPATH '/root/data/2006.csv' OVERWRITE INTO TABLE airline_delay PARTITION (delayYear='2007');

hive > LOAD DATA LOCAL INPATH '/root/data/2006.csv' OVERWRITE INTO TABLE airline_delay PARTITION (delayYear='2008');

hive > select * from airline_delay where delayYear='2008' LIMIT 2;

> select count(*) from airline_delay where delayYear='2008';

> select Month, avg(ArrDelay), avg(DepDelay) from airline_delay where delayYear='2007' group by Month;
> select Year, Month, avg(ArrDelay), avg(DepDelay) from airline_delay where delayYear='2007' group by Year, Month;
```

- partition으로 나눠서 데이터를 더 편하게 볼 수 있다.
- sql문으로 데이터 뽑아낼때에도 partition마다의 데이터를 볼수 있다.

### 17.3 하이브QL

- 하이브에서 사용하는 데이터는 하둡 파일시스템에 저장되어 있음. 파일시스템에 한번 저장된 파일은 수정할 수 없어 UPDATE와 DELETE를 사용할 수 없음. 
- SQL에서의 서브쿼리를 하이브QL에서는 FROM절에서만 사용할 수 있음
- 뷰는 읽기 전용.
- SELECT문에서 HAVING 절 사용할 수 없음
- **PARTITIONED BY**

### 17.4 파티션 테이블

**파티션 테이블에 데이터를 입력하기**

```
INSERT OVERWRITE TABLE airline_delay PARTITION (delayyear='2007')
SELECT * FROM airline_delay_raw WHERE year = 2007;
```

### 17.5 데이터 정렬

- order by

```
select Month, avg(ArrDelay), avg(DepDelay) from airline_delay where delayYear='2007' group by Month order by Month;
```

- sort by

```
select Month, avg(ArrDelay), avg(DepDelay) from airline_delay where delayYear='2007' group by Month sort by Month;
```



## JAVA - HIVE 연동

- hive listener 띄워놓기 

```
hive --service hiveserver2
```

- eclipse 로 hive에 sql 쿼리 작성하기

```java
package hive;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Hive {
	public static void main(String[] args) throws Exception{
		 Class.forName("org.apache.hive.jdbc.HiveDriver");
         Connection conn =DriverManager.getConnection("jdbc:hive2://70.12.114.216:10000/default","root","111111");
         Statement stmt =conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM hdi");
            while(rs.next()) {
              System.out.println(rs.getString(1));
            }
         conn.close();
         System.out.println("Success....");
	}
}
```

:heavy_check_mark: **hadoop safe mode error 대처 방법**

```
hadoop dfsadmin -safemode leave
```





```
load data local inpath '/root/worklog.txt' overwrite into table worklog;

load data local inpath '/root/worklog.txt' into table worklog;

alter table worklog set serdeproperties ('serialization.encoding'='UTF-8');

CREATE TABLE WORKLOG(type STRING, typenum INT, logdate STRING, userid STRING, userloc STRING, funcname STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;

select * from worklog;

alter database hive_db character set latin1;

```

