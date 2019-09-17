package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText textId;
    EditText textPwd;
    String id;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textId = findViewById(R.id.textId);
        textPwd = findViewById(R.id.textPwd);
    }

    public void clickLoginBt(View view){
        id = textId.getText().toString();
        pwd = textPwd.getText().toString();
        check();
    }

    private void check() {
        String url =  "http://70.12.60.111/webview/login.jsp?id="+id+"&pwd="+pwd;
        HttpTask httpTask = new HttpTask(url);
        httpTask.execute();
    }

    class HttpTask extends AsyncTask<String, Void, String>{
        String url;

        public HttpTask(String url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected String doInBackground(String... strings) {
            String str = HttpHandler.getString(url);
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("0")){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
