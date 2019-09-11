package com.example.p577;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;

public class CalActivity extends AppCompatActivity {
    CalendarView calendarView;
    int y, m, d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                // 년, 월(0부터 시작??), 일
                y = i;
                m = i1+1;
                d = i2;
            }
        });
    }
    public void btCalOk(View view){
        Date date = new Date(calendarView.getDate());
        //String sdate = date.toLocaleString();
        //String sdate = date.getYear()+"."+date.getMonth()+"."+date.getDate();
        String sdate = y+"Y"+m+"M"+d+"D";
        Intent intent = new Intent();
        intent.putExtra("Date", sdate);
        setResult(RESULT_OK, intent);
        finish(); // 달력 화면 종료
    }
}
