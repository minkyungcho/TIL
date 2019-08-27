package com.example.p169;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText textInput;
    TextView textByte;
    int cnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUi();
    }

    private void setUi() {
        textInput = findViewById(R.id.textInput);
        textByte = findViewById(R.id.textByte);
        textInput.addTextChangedListener(watcher);
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            InputFilter[] filter = new InputFilter[1];
            filter[0] = new InputFilter.LengthFilter(80);
            textInput.setFilters(filter);

            cnt = getLength(s.toString());
            textByte.setText(String.valueOf(cnt) + " / 80 바이트");
//            if(cnt > 80){
//                s.delete(s.length()-2, s.length()-1);
//            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public static int getLength(String string){
        if(string == null || "".equals(string)){
            return 0;
        }else {
            int length = string.length();
            int charLength = 0;
            for(int i=0; i<length; i++){
                charLength += string.codePointAt(i) > 0xff ? 2:1;
            }
            return charLength;
        }
    }

    public void clickBt(View view){
        if(view.getId() == R.id.btSend){
            String message = textInput.getText().toString();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else if(view.getId() == R.id.btClose){
            finish();
        }
    }
}
