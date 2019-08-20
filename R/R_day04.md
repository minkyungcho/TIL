#### png img파일로 그래프 그리기

**r3/r08.R**

```R
# class별 hwy연비의 평균을 그래프로 그리시오
library(ggplot2)
mpg2 <- aggregate(data=mpg,hwy~class,mean)
png("gg.png",width=600,height=500)
ggplot(data=economics,aes(x=date,y=psavert)) + geom_line()
dev.off() # 화면으로 출력하지 않고 파일에 출력.
```

- 웹에서 요청이 오면 바로 img로 만든다. 바로 웹에 img 게시

---

## 텍스트마이닝

> java가 설치되어 있어야 KoNLP 설치 가능

*step01 패키지 설치*

```
install.packages("rJava")
install.packages("memoise")
install.packages("KoNLP")
install.packages("stringr")
install.packages("wordcloud")
install.packages("RColorBrewer")
```

*step02 사전 설정하기*

```
>useNIADic()
```

*step03*

```R
# 패키지 로드
library(rJava)
library(KoNLP)
library(dplyr)
library(stringr)
library(RColorBrewer)
library(wordcloud)
txt <- readLines("hiphop.txt") # 데이터 불러오기
txt <- str_replace_all(txt,"\\W", " ") # 특수문자 제거
nouns <- extractNoun(txt) # 가사에서 명사 추출
cnt <- table(unlist(nouns)) # 추출한 명사 list를 문자열 벡터로 변환, 단어별 빈도표 생성
df_cnt <- as.data.frame(cnt,stringsAsFactors = F) # 데이터 프레임으로 변환
colnames(df_cnt) <- c("word","freq") # 열 이름 변경
df_word <- filter(df_cnt,nchar(word) >= 2) # 두 글자 이상 단어 추출
df_word <- df_word[order(df_word$freq,decreasing=T),] # 정렬
df_word <- head(df_word,20) # top20만 뽑기
pal <- brewer.pal(8,"Dark2") # Dark2 색상 목록에서 8개 색상 추출
set.seed(1234)
wordcloud(word=df_word$word,
          freq=df_word$freq,
          min.freq=2,
          max.words=200,
          random.order=F,
          rot.per=.1,
          scale=c(4,0.3),
          colors=pal)
```

#### 11-2

```
install.packages("stringi")
install.packages("devtools")
devtools::install_github("cardiomoon/kormaps2014")
```

---

## R - ORACLE 연동

> C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib 의 **ojdbc6_g**  활용
>
> http://127.0.0.1:8080/apex/

*step01*

**RJDBC, rJava 두가지 패키지 인스톨**

```
>install.packages("RJDBC")
>install.packages("DBI")
>install.packages("rJava")
>library(RJDBC)
>library(DBI)
>library(rJava)
```

*step02*

**JDBC 드라이버 로딩 및 Connection 만들기**

```
> drv=JDBC(driverClass="oracle.jdbc.driver.OracleDriver",classPath="c:\\ojdbc6.jar")
> conn=dbConnect(drv,"jdbc:oracle:thin:@127.0.0.1:1521:EX","db","db")
```

*step03*

**데이터 가지고 오기**

```
> emp=dbGetQuery(conn,"select * from emp") 
```

*step04*

**Connection Close**

```
> dbDisconnect(conn)
```

**r4/r07.R**

```
db <- function(){
  library(RJDBC)
  library(DBI)
  library(rJava)
  drv=JDBC(driverClass="oracle.jdbc.driver.OracleDriver",
           classPath="c:\\ojdbc6_g.jar")
  conn=dbConnect(drv,"jdbc:oracle:thin:@70.12.114.50:1521:xe",
                 "db","db")
  tuser=dbGetQuery(conn,"select * from t_user") 
  dbDisconnect(conn)
  return(tuser)
}
```

---

## R - JAVA 연동

> C:\lib 에 .jar파일 복붙
>
> REngine, RserveEngine, json-simple-1.1.1

*step01*

```
>install.packages("Rserve")
```

*step02*

```
>Rserve::run.Rserve()
```

- portnum:6060이 떠있는 상태

*step03*

**r4/r07.R**

```R
dd <- function(x,y){
  z <- x + y;
  return(z);
}
```

*step04*

- **rtset** java project 생성
- build path에 jar 파일 추가.
  - REngine
  - RserveEngine
  - json-simple-1.1.1

- java eclipse에서 코드 실행

**Test1.java**

```java
package rtest;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
public class Test1 {
	public static void main(String[] args) throws Exception{
		int x = 10;
		int y = 20;
		RConnection rc =new RConnection();
		System.out.println("Connection OK");
		rc.eval("source('C:/rstudio/r4/r07.R')"); 
        // 현재 rserver가 동작도;ㅣ고 있기 때문에 source는 올라가지 않아서 이런식으로 올려줘야함.
		// REXP rx = rc.eval("dd(3,4)");
		REXP rx = rc.eval("dd("+x+","+y+")");
		double result = rx.asDouble();
		System.out.println(result);
		rc.close();
	}
}
```

```
Connection OK
// 7.0
30.0
```

**리모트 접속**

- Rstudio

```
>Rserve(args="--RS-enable-remote")
```

- eclipse

```
new RConnection("ip")
```

---

**r4/r07.R**

```R
df <- function(){
  year <- c(2010,2011,2012,2013,2014);
  qt <- c(980,890,789,765,879);
  result <- data.frame(YEAR=year,QT=qt);
  return (result);
}
```

**Test2.java**

```java
package rtest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
public class Test2 {
	public static void main(String[] args) throws Exception{
		int x = 10;
		int y = 20;
		RConnection rc =new RConnection("70.12.114.62");
		System.out.println("Connection OK");
		rc.eval("source('C:/rstudio/r4/r07.R')"); 
		REXP rx = rc.eval("df()"); // datafram이 return
		RList rlist = rx.asList(); // datafram -> list
		double years [] = rlist.at("YEAR").asDoubles();
		double qts [] = rlist.at("QT").asDoubles();
		System.out.println(years.length+" "+qts.length); // data frame의 1번째 column과 2번째 column
		// [{"YEAR":2010,"QT":980}]
		JSONArray ja = new JSONArray();
		for(int i=0; i<years.length; i++) {
			JSONObject jo = new JSONObject();
			jo.put("YEAR", years[i]);
			jo.put("QT", qts[i]);
			ja.add(jo);
		}
		System.out.println(ja.toJSONString());
		rc.close();
	}
}
```

```
Connection OK
5 5
[{"QT":980.0,"YEAR":2010.0},{"QT":890.0,"YEAR":2011.0},{"QT":789.0,"YEAR":2012.0},{"QT":765.0,"YEAR":2013.0},{"QT":879.0,"YEAR":2014.0}]
```

#### 한글 깨짐 문제

```
RConnection rconn = null;
rconn = new RConnection();
rconn.setStringEncoding("utf8")
rconn.eval("source('C:/r/d1/jdbc.R',encoding='UTF-8')");
```

---

**r4/r07.R**

```R
db <- function(){
  library(RJDBC)
  library(DBI)
  library(rJava)
  drv=JDBC(driverClass="oracle.jdbc.driver.OracleDriver",
           classPath="c:\\ojdbc6_g.jar")
  conn=dbConnect(drv,"jdbc:oracle:thin:@70.12.114.50:1521:xe",
                 "db","db")
  tuser=dbGetQuery(conn,"select * from t_user") 
  dbDisconnect(conn)
  return (tuser)
}
```

**Test3.java**

```java
package rtest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
public class Test3 {
	public static void main(String[] args) throws Exception{
		RConnection rc = null;
		rc = new RConnection("70.12.114.62");
		rc.setStringEncoding("utf8");
		// rc.eval("source('C:/rstudio/r4/r07.R',encoding='UTF-8')");
         rc.eval("source('C:/rstudio/r4/r07.R')");
		System.out.println("Connection OK");
		rc.eval("source('C:/rstudio/r4/r07.R')");
		REXP rx = rc.eval("db()");
		RList rlist = rx.asList();
		String ids [] = rlist.at("ID").asStrings();
		String pwds [] = rlist.at("PWD").asStrings();
		String names [] = rlist.at("NAME").asStrings();
		System.out.println(ids.length+" "+pwds.length+" "+names.length);
		// [{"ID":id05,"PWD":0705,"NAME":뎡대}]
		JSONArray ja = new JSONArray();
		for(int i=0; i<ids.length; i++) {
			JSONObject jo = new JSONObject();
			jo.put("ID", ids[i]);
			jo.put("PWD", pwds[i]);
			jo.put("NAME", names[i]);
			ja.add(jo);
		}
		System.out.println(ja.toJSONString());
		rc.close();
	}
}
```

```
Connection OK
14 14 14
[{"ID":"id05","PWD":"0705","NAME":"뎡대"},{"ID":"id66","PWD":"pwd66","NAME":"일말숙"},{"ID":"id50","PWD":"pwd502","NAME":"이영자2"},{"ID":"id51","PWD":"pwd51","NAME":"김영철"},{"ID":"id52","PWD":"pwd52","NAME":"최보근"},{"ID":"id53","PWD":"pwd53","NAME":"김재영"},{"ID":"id54","PWD":"pwd54","NAME":"김도형"},{"ID":"id55","PWD":"pwd55","NAME":"임지훈"},{"ID":"id44","PWD":"pwd44","NAME":"임라영"},{"ID":"id01","PWD":"pwd01","NAME":"임말숙"},{"ID":"id02","PWD":"pwd02","NAME":"이말숙"},{"ID":"id03","PWD":"pwd03","NAME":"장말숙"},{"ID":"sdf","PWD":"dsf","NAME":"fds"},{"ID":"id04","PWD":"asdf","NAME":"뎡대"}]
```

---

## R  -  HIVE 연동

> C:\java_hive_lib

**r4/r07.R**

```R
rhive <- function(){
  library(RJDBC);
  library(DBI);
  library(rJava);
  hive_lib <- 'c:\\java_hive_lib'; # library 위치
  # jar들 메모리에 올리는 과정
  .jinit();
  .jaddClassPath(dir(hive_lib,full.names = T));
  .jclassPath(); 
    
  drv=JDBC(driverClass='org.apache.hive.jdbc.HiveDriver',
           'hive-jdbc-1.0.1.jar');
  conn=dbConnect(drv,"jdbc:hive2://70.12.114.222:10000/default",
                 "root","111111");
  
  user=dbGetQuery(conn,"select hdi.country, hdi.hdi from hdi limit 10");
  dbDisconnect(conn);
  return (user);
}
```

**Test4.java**

```R
package rtest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
public class Test4 {
	public static void main(String[] args) throws Exception{
		RConnection rc = null;
		rc = new RConnection("70.12.114.62");
		rc.setStringEncoding("utf8");
		rc.eval("source('C:/rstudio/r4/r07.R')");
		System.out.println("Connection OK");
		REXP rx = rc.eval("rhive()");
		RList rlist = rx.asList();
		String countrys [] = rlist.at("hdi.country").asStrings();
		String hdis [] = rlist.at("hdi.hdi").asStrings();
		System.out.println(countrys.length+" "+hdis.length);
		// [{"COUNTRY":Norway,"HDI":0.9430000185966492}]
		JSONArray ja = new JSONArray();
		for(int i=0; i<countrys.length; i++) {
			JSONObject jo = new JSONObject();
			jo.put("COUNTRY", countrys[i]);
			jo.put("HDI", hdis[i]);
			ja.add(jo);
		}
		System.out.println(ja.toJSONString());
		rc.close();
	}
}
```

```
Connection OK
10 10
[{"COUNTRY":"Norway","HDI":"0.9430000185966492"},{"COUNTRY":"Australia","HDI":"0.9290000200271606"},{"COUNTRY":"Netherlands","HDI":"0.9100000262260437"},{"COUNTRY":"United States","HDI":"0.9100000262260437"},{"COUNTRY":"New Zealand","HDI":"0.9079999923706055"},{"COUNTRY":"Canada","HDI":"0.9079999923706055"},{"COUNTRY":"Ireland","HDI":"0.9079999923706055"},{"COUNTRY":"Liechtenstein","HDI":"0.9049999713897705"},{"COUNTRY":"Germany","HDI":"0.9049999713897705"},{"COUNTRY":"Sweden","HDI":"0.9039999842643738"}]
```

