package com.example.p234;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ProgressBar progressBar;
    Button btClick;
    RadioGroup radioGroup;
    RadioButton rbtDog, rbtCat, rbtHedgehog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }
    private void setUI() {
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
//        btOk = findViewById(R.id.btOk);
        btClick = findViewById(R.id.btClick);
        rbtDog = findViewById(R.id.rbtDog);
        rbtCat = findViewById(R.id.rbtCat);
        rbtHedgehog = findViewById(R.id.rbtHedgehog);
    }
    public void btclick(View view){
        imageView = findViewById(R.id.imageView);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("선호도 조사");
        LayoutInflater inflater = getLayoutInflater();
        final View dview = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.dLayout));
        builder.setView(dview);
        final AlertDialog dialog = builder.create();
        dialog.setButton(dialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                radioGroup = dview.findViewById(R.id.rg);
                int id = radioGroup.getCheckedRadioButtonId();
                if(radioGroup.getCheckedRadioButtonId()== R.id.rbtCat){
//                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.drawable.cat2);
                }else if(id == R.id.rbtDog){
//                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.drawable.dog1);
                }else if(id == R.id.rbtHedgehog){
//                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.drawable.hedgehog1);
                }
                TextView at = dview.findViewById(R.id.editText);
                int l = Integer.parseInt(at.getText().toString());
                progressBar.setProgress(l);
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }
}
