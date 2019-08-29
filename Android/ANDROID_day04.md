### 04 여러 화면 간 전환하기

#### 04-2 여러 화면 만들고 화면 간 전환하기

**P246**

액티비티 간 매개체 역할 : Intent

bundle : 잠시 앱이 살아있을때 저장.

sharedinte.. : 앱이 꺼져도 계속 저장.

##### 여러 화면 만들기

project - app - java - com.example.p246 우클릭 - new - **Activity** - Empty Activity 생성

project - app - manifests - **AndroidManirests.xml** 에 activity 추가 된것 확인 할 수 있음.

```xml
<activity android:name=".Main3Activity"></activity>
        <activity android:name=".Main2Activity" />
        <activity android:name=".MainActivity">
```

