package com.example.p200;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ServiceConfigurationError;

public class MainActivity extends AppCompatActivity {
    View view, view2;
    TextView textView;

    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }

    private void setUI(){
        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
        textView = findViewById(R.id.textView2);
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

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    // 어디서든 메세지를 출력하는 함수
    private void printMSG(String str){
        textView.append(str+"\n");
    }

    // 핸드폰에 있는 버튼에 이벤트 발생했을 경우. volume 버튼, back 버튼,home 버튼..
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "Back...", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showToast("ORIENTATION_PORTRAIT");
        }else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            showToast("ORIENTATION_LANDSCAPE");
        }
    }

    public void showToast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
