package com.example.p436;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<Integer> list;
    Spinner spinner;
    ImageView imageView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(0);
        ratingBar.setNumStars(5);
        ratingBar.setMax(5);
        ratingBar.setStepSize(1);
        getData();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 손가락으로 화살표 눌렀을때 밑으로 쭈르륵 떨어지는 화면
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void getData() {
        list = new ArrayList<>();
        list.add(R.drawable.cooker);
        list.add(R.drawable.detective);
        list.add(R.drawable.doctor);
        list.add(R.drawable.employee);
        list.add(R.drawable.mechanic);
        list.add(R.drawable.meditation);
        list.add(R.drawable.police);
        list.add(R.drawable.policeman);
        list.add(R.drawable.support);
        list.add(R.drawable.woman);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Integer imgcode = list.get(i);
        imageView.setImageResource(imgcode);
        float temp = ratingBar.getRating()+1;
        ratingBar.setRating(temp);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
