package com.example.p548;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

    }

    public void create(View view){
        String name = "emp"; // emp라는 테이블 이름
        // 데이터베이스 객체 생성
        println("createDatabase 호출됨.");
        databaseHelper = new DatabaseHelper(this); // helper 생성. 이때 데이터베이스 생성
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        println("데이터베이스 생성함 : ");

        // 테이블 생성
        println("createTable 호출됨.");
        if (sqLiteDatabase == null) {
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }
        sqLiteDatabase.execSQL("create table if not exists " + name + "("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " mobile text)");
        println("테이블 생성함 : " + name);
    }
    public void insert(View view){
        println("insertRecord 호출됨.");
        if (sqLiteDatabase == null) {
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }
        if (sqLiteDatabase == null) {
            println("테이블을 먼저 생성하세요.");
            return;
        }
        sqLiteDatabase.execSQL("insert into emp"
                + "(name, age, mobile) "
                + " values "
                + "('John', 20, '010-1000-1000')");

        println("레코드 추가함.");
    }
    public void select(View view){
        println("executeQuery 호출됨.");
        Cursor cursor = sqLiteDatabase.rawQuery("select _id, name, age, mobile from emp", null);
        int recordCount = cursor.getCount();
        println("레코드 개수 : " + recordCount);
        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext(); // 계속 돈다.
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);
            println("레코드 #" + i + " : " + id + ", " + name + ", " + age + ", " + mobile);
        }
        cursor.close();
    }
    public void println(String data) {
        textView.append(data + "\n");
    }
}
