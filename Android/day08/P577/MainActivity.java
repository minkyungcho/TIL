package com.example.p577;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textMain;
    TextView textID;
    EditText textTitle;
    TextView textDate;
    EditText textContents;
    //
    ArrayList<Memo> items;

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMain = findViewById(R.id.textMain);
        textID = findViewById(R.id.textID);
        textTitle = findViewById(R.id.textTitle);
        textDate = findViewById(R.id.textDate);
        textContents = findViewById(R.id.textContents);
        //
        items = new ArrayList<>();

        Intent intent = getIntent();
        String str = intent.getStringExtra("ID");
        textMain.setText(str+"님 환영합니다.");
        textID.setText(str);
        create();
    }

    private void create() {
        String tname = "tt"; // tt라는 테이블 이름
        // 데이터베이스 객체 생성
        Log.d("####","createDatabase 호출됨.");
        databaseHelper = new DatabaseHelper(this); // helper 생성. 이때 데이터베이스 생성
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        Log.d("####","데이터베이스 생성함 : ");
        // 테이블 생성
        Log.d("####","createTable 호출됨.");
        if (sqLiteDatabase == null) {
            Log.d("####","데이터베이스를 먼저 생성하세요.");
            return;
        }
        sqLiteDatabase.execSQL("create table if not exists " + tname + "("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " ddate text,"
                + " title text, "
                + " cont text)");
        Log.d("####","테이블 생성함 : " + tname);
    }

    public void btCal(View view){
        Intent calIntent = new Intent(getApplicationContext(), CalActivity.class);
        startActivityForResult(calIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            String Date = data.getStringExtra("Date");
            textDate.setText(Date);
        }
    }

    public void btSave(View view){
        String name = textID.getText().toString();
        String dd = textDate.getText().toString();
        String title = textTitle.getText().toString();
        String cont = textContents.getText().toString();
        insert(name, dd, title, cont);
    }

    private void insert(String n, String d, String t, String c) {
        Log.d("####","insertRecord 호출됨.");
        if (sqLiteDatabase == null) {
            Log.d("####","데이터베이스를 먼저 생성하세요.");
            return;
        }
        if (sqLiteDatabase == null) {
            Log.d("####","테이블을 먼저 생성하세요.");
            return;
        }
        sqLiteDatabase.execSQL("insert into tt"
                + "(name, ddate, title, cont) "
                + " values "
                + "('"+n+"', '"+d+"', '"+t+"', '"+c+"')");
        //        + "('John', 20, '010-1000-1000')");
        Log.d("####","레코드 추가함.");
    }

    public void btList(View view){
        select();

        Intent listIntent = new Intent(getApplicationContext(), ListActivity.class);
        listIntent.putExtra("item", items);
        startActivity(listIntent);
    }

    private void select() {
        Log.d("####","executeQuery 호출됨.");
        Cursor cursor = sqLiteDatabase.rawQuery("select _id, name, ddate, title, cont from tt", null);
        int recordCount = cursor.getCount();
        println("레코드 개수 : " + recordCount);
        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext(); // 계속 돈다.
            int id = cursor.getInt(0);
            String sel_name = cursor.getString(1);
            String sel_date = cursor.getString(2);
            String sel_title = cursor.getString(3);
            String sel_cont = cursor.getString(4);
            Log.d("####","레코드 #" + i + " : " + id + ", " + sel_name + ", " + sel_date + ", " + sel_title + ", " + sel_cont);
            Toast.makeText(this, id+" "+sel_name+" "+sel_date+" "+sel_title+" "+sel_cont, Toast.LENGTH_SHORT).show();
            Memo memo = new Memo(sel_name, sel_date, sel_title, sel_cont);
            items.add(memo);
        }
        cursor.close();
    }
    public void println(String data) {
        //textView.append(data + "\n");
    }
}
