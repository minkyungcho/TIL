  pclass    sex    생존율
1    1st female 0.9652778
2    1st   male 0.3407821
3    2nd female 0.8867925
4    2nd   male 0.1461988
5    3rd female 0.4907407
6    3rd   male 0.1521298R Variable

1. scalar
2. factor
3. vector
4. matrix
5. data frame

### R Function

### R Programming





```R
user <- read.csv("user.csv",
                 header=FALSE,
                 stringsAsFactors=FALSE,
                 na.strings = "NA",
                 encoding = "EUC-KR")
colnames(user) <- c("ID", "NAME", "AGE", "WEIGHT", "HEIGHT")
user 

write.csv(
  user,
  file = "user2.csv",
  row.names = FALSE,
  col.names = FALSE,
  fileEncoding = "EUC-KR"
)
```



> user.csv 파일을 읽어서 나이, 몸무게, 키의 평균을 구하고 3가지 평균정보를 가진 vector를 파일로 저장하시오.
>
> 함수명 : useravg
>
> 파일명은 useravg.cvs
>
> ex) 
>
> age, weight, height
>
> 27.4, 80.2, 178.3

```
savefile <- function(a){
  write.csv(
    a,
    file = "useravg.csv",
    row.names = FALSE,
    fileEncoding = "EUC-KR"
  )
}

useravg <- function(){
  user <- read.csv("user.csv",
                   stringsAsFactors = TRUE,
                   encoding = "EUC-KR"
  )
  colnames(user) <- c("ID", "NAME", "AGE", "WEIGHT", "HEIGHT")
  user2 <- c(mean(user$AGE,na.rm = TRUE),
             mean(user$WEIGHT,na.rm = TRUE),
             mean(user$HEIGHT,na.rm = TRUE))
  names(user2) <- c("AGE", "WHIGHT", "HEIGHT")
  savefile(user2)
  return(user2)
}

useravg()
```





#### 데이터 프레임의 행과 컬럼 합치기

- x라는 데이터프레임 생성하고 컬럼 추가하기.

```
x <- data.frame(id=c(1, 2), 
                name=c("a", "b"), 
                stringsAsFactors=F)
x$age <- c(20,30)
------------------------
  id name age
1  1    a  20
2  2    b  30
```

- x에 row를 추가하여 새로운 데이터프레임 y를 생성 rbind 활용

```
y <- rbind(x,c(1,"c",40))
-------------------------
  id name age
1  1    a  20
2  2    b  30
3  1    c  40
```

- cbind 활용

```
z <- cbind(y,c(1,2,3))
-------------------------
  id name age c(1, 2, 3)
1  1    a  20          1
2  2    b  30          2
3  1    c  40          3
```

- cbind 열 name 설정해주기

```
z <- cbind(y,level=c(1,2,3),add=c(3,2,1),rate=c(2,4,6))
---------------------------
  id name age level add rate
1  1    a  20     1   3    2
2  2    b  30     2   2    4
3  1    c  40     3   1    6
```

> stduent.csv
>
> 새로운 데이터프레임 만들기
>
> 사람마다의 과목들 평균 구해서 avg라는 열 추가 

```R
stu <- read.csv("student.csv",
                    header=TRUE,
                    stringsAsFactors = TRUE,
                    encoding = "EUC-KR"        
)
avg <- c(mean(as.numeric(stu[1,3:6])),mean(as.numeric(stu[2,3:6])),mean(as.numeric(stu[3,3:6])),              mean(as.numeric(stu[4,3:6])),mean(as.numeric(stu[5,3:6])),mean(as.numeric(stu[6,3:6])),
         mean(as.numeric(stu[7,3:6])),mean(as.numeric(stu[8,3:6])))
stu <- cbind(stu,avg)
----------------------------
   ID   NAME KO EN MA SI avg
1 100 김말숙 90 80 70 60  75
2 101 홍말숙 91 81 71 61  76
3 102 안말숙 92 82 72 62  77
4 103 정말숙 93 83 73 63  78
5 104 강말숙 94 84 74 64  79
6 105 조말숙 95 85 75 65  80
7 106 장말숙 96 86 76 66  81
8 107 한말숙 97 87 77 67  82

// 이름과 평균만 존재하는 데이터프레임 만들기.
newst <- stu[,c(2,7)] 
-----------------------
    NAME avg
1 김말숙  75
2 홍말숙  76
3 안말숙  77
4 정말숙  78
5 강말숙  79
6 조말숙  80
7 장말숙  81
8 한말숙  82

// 과목의 평균도 나타내기

```



#### apply 계열 함수

- 

```
stu <- read.csv("student.csv",
                header=TRUE,
                stringsAsFactors = FALSE,
                na.strings = "NA",
                encoding = "EUC-KR")

stss <- apply(stu[,3:6],2,mean)
sts <- apply(stu[,3:6],1,mean)
names(sts) <- stu[,2]

class(stss)
class(sts)
dfstss <- as.data.frame(stss)
colnames(dfstss) <- "average"
------------------------------
> stss
  KO   EN   MA   SI 
93.5 83.5 73.5 63.5 
> class(stss)
[1] "numeric"
> dfstss
   average
KO    93.5
EN    83.5
MA    73.5
SI    63.5
```

- tapply() : 그룹별로 사용. 벡터, 인덱스

```
tiris <- tapply(iris$Sepal.Length, iris$Species, mean)
---------------------------------------
> tiris
    setosa versicolor  virginica 
     5.006      5.936      6.588, 밧컵소라보허
```





#### 외부 라이브러리 활용

- install.packages("")

**sql 연동**

```
> install.packages("sqldf")
library(sqldf)
```

- r2/r08.R

```
library(sqldf)
iris
result <- sqldf('select Species, avg("Sepal.Length") as "Sepal.Length", avg("Sepal.Width") as "Sepal.Width" from iris group by Species')
------------------------------
     Species Sepal.Length Sepal.Width
1     setosa        5.006       3.428
2 versicolor        5.936       2.770
3  virginica        6.588       2.974
```

- r2/r088.R

  > pclass 별 생존율을 조사하시오
  >
  > pclass 별, 성별 생존율을 조사하시오

```
library(sqldf)
tt <- read.csv("titanic.csv",
               header=TRUE,
               stringsAsFactors = FALSE,
               na.strings = "NA"
)
result <- sqldf('select pclass, avg(survived) as "생존율" from tt group by pclass')
---------------------------
  pclass    생존율
1    1st 0.6191950
2    2nd 0.4296029
3    3rd 0.2552891
result2 <- sqldf('select pclass, sex, avg(survived) as "생존율" from tt group by pclass, sex)
-----------------------------
  pclass    sex    생존율
1    1st female 0.9652778
2    1st   male 0.3407821
3    2nd female 0.8867925
4    2nd   male 0.1461988
5    3rd female 0.4907407
6    3rd   male 0.1521298
result3 <- sqldf('select pclass, sex, avg(survived) as "생존율" from tt group by pclass, sex having avg(survived) < 0.5')
-----------------------------
  pclass    sex    생존율
1    1st   male 0.3407821
2    2nd   male 0.1461988
3    3rd female 0.4907407
4    3rd   male 0.1521298
```



## 0816 ws

r2/r09_ws

```R
library(sqldf)
bum <- read.csv("bum.csv",
               header=TRUE,
               stringsAsFactors = FALSE,
               na.strings = "NA"
)
r3 <- sqldf('select 범죄대분류, sum(계) as 합계, sum(서울) as 서울시 
            from bum group by 범죄대분류')
per <- c(as.numeric(r3$서울시/r3$합계*100))
rr3 <- cbind(r3, "%"=per)
```

```
     범죄대분류   합계 서울시        %
1      강력범죄  25765   6683 25.93829
2      교통범죄 600401  74270 12.37007
3      기타범죄 260539  44407 17.04428
4      노동범죄   2457    509 20.71632
5      마약범죄   7329   1449 19.77077
6      병역범죄  16651   4120 24.74326
7      보건범죄  14662   3875 26.42886
8      선거범죄   1018    180 17.68173
9      안보범죄     81     19 23.45679
10     절도범죄 203037  46861 23.08003
11     지능범죄 312577  72137 23.07815
12 특별경제범죄  65025  17109 26.31142
13     폭력범죄 309394  64601 20.87985
14     풍속범죄  26165   5647 21.58227
15     환경범죄   4349     58  1.33364
```





**java 연동**

