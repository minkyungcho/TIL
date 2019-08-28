## 03 기본 위젯과 드로어블 사용하기

#### 버튼 

**P178**    [실습코드]()

버튼의 클릭 이벤트 처리하기 (정규방식)

```java
package com.example.p178;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{ // MainActivity 안에서 OnClickListener 처리
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.button);
        bt.setOnClickListener(this); // bt에 이벤트가 들어오면, this(내가) 처리한다. bt에 onclick 처리한다.
    }
    @Override
    public void onClick(View view) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
    }
}
```



### 03-3 이벤트 처리 이해하기

**P200**    [실습코드]()

방햔전환을 알수 있도록 **manifests**에서 속성 설정해주기.

```xml
<activity android:name=".MainActivity"
                android:configChanges="orientation|screenSize|keyboardHidden"
            >
```

#### 터치 이벤트 처리

```java
// view 터치 이벤트 발생하면 () 에서 처리.
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // action 받아오기
                int action = motionEvent.getAction();
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();
                
                if(action == MotionEvent.ACTION_DOWN){
                    printMSG("DOWN: "+curX+"  "+curY); // 손가락이 눌렸을 때
                }else if(action == MotionEvent.ACTION_MOVE){
                    printMSG("MOVE: "+curX+"  "+curY); // 손가락이 눌린 상태로 움직일 때
                }else if(action == MotionEvent.ACTION_UP){
                    printMSG("UP: "+curX+"  "+curY); // 손가락이 떼졌을 때
                }
                return true;
            }
        });
```

#### 제스처 이벤트 처리

```java
gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                printMSG("onDown() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                printMSG("onShowPress() 호출됨.");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                printMSG("onSingleTapUp() 호출됨.");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                printMSG("onScroll() 호출됨.");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                printMSG("onLongPress() 호출됨.");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                printMSG("onFling() 호출됨.");
                return true;
            }
        });
// 
public void showToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
```



#### bundle 활용하기

**P213**    [실습코드]()

버튼 누르면 특정 값 저장한 후 어플 다시 실행했을때 그 값 보여줄 수 있도록.

```java
public void clickbt(View view){
        if(view.getId() == R.id.button){
            // save
            sp = getSharedPreferences("ma", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("key01", "id01");
            editor.commit();
        }else if(view.getId() == R.id.button2){
            // load
            sp = getSharedPreferences("ma", MODE_PRIVATE);
            String id = sp.getString("key01","default");
            Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        }
    }
```



#### 03-4 토스트, 스낵바 그리고 대화상자 사용하기

토스트, 스낵, 다이알로그 각각 클릭 이벤트 생성

##### layout xml 추가하기

*안드로이드에서 새로운 화면 만드는 방식 중 하나*

project - layout 우클릭 - new - XML - Layout XML File  

**P217**    [실습코드]()

##### toast

```java
public void toast(View view){
        LayoutInflater inflater = getLayoutInflater();
        View tview = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.tlayout)); // 만든 toast.XML과 view.
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(tview);
        toast.show();
    }
```

##### dialog

```java
public void dialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("my dialog");
        //builder.setMessage("Do you Exit App ?");
        //builder.setIcon(R.drawable.icon1);
        LayoutInflater inflater = getLayoutInflater();
        View dview = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dlayout));
        TextView tv = dview.findViewById(R.id.textView2);
        tv.setText("DO you wanna Exit App...?");
        builder.setView(dview);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
```



#### 03-5 프로그래스바 사용하기

**P228**    [실습코드]()

```java
// progressbar 제어
    public void bar(View view){
        if(view.getId() == R.id.button){
            progressBar.setProgress(progressBar.getProgress()+10);
        }else if(view.getId() == R.id.button2){
            progressBar.setProgress(progressBar.getProgress()-10);
        }
    }
    // progress dialog 제어
    public void dialog(View view){
        if(view.getId() == R.id.button3){
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Progress...");
            progressDialog.setCancelable(false);
            progressDialog.setButton(progressDialog.BUTTON_NEGATIVE, "Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progressDialog.dismiss();
                }
            });
            progressDialog.show();
        }else if(view.getId() == R.id.button4){
            progressDialog.dismiss();
        }
    }
```



---

## workshop

**#1 P233**    [실습코드]()

시크바와 프로그레스바 보여주기

시크바의 값을 바꾸면 프로그레스바의 값이 바뀌고, 그 값이 입력상자에 표시되도록 한다.



**#2 P234**    [실습코드]()

![../img/P234.png]()