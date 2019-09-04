package com.example.p535;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> list;
    ListView listView;
    LinearLayout container;
    ItemAdapter itemAdapter;
    ProgressDialog progressDialog;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();


        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        progressDialog = new ProgressDialog(this);
//        listView.setOnItemClickListener(this);
    }
    class ItemAdapter extends BaseAdapter {
        ArrayList<Item> alist;
        public ItemAdapter(){
        }
        public ItemAdapter(ArrayList<Item> alist) {
            this.alist = alist;
        }
        public void addItem(Item item){
            alist.add(item);
            list = alist;
        }
        @Override
        public int getCount() {
            return alist.size();
        }
        @Override
        public Object getItem(int i) {
            return alist.get(i);
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myview = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = inflater.inflate(R.layout.layout, container,true);
            final ImageView iv = myview.findViewById(R.id.imageView);
            TextView tv1 = myview.findViewById(R.id.textView);
            TextView tv2 = myview.findViewById(R.id.textView2);
            ratingBar = myview.findViewById(R.id.ratingBar);
            ratingBar.setRating(0);
            ratingBar.setNumStars(5);
            ratingBar.setMax(5);
//            ratingBar.setStepSize(1);
            String img = alist.get(i).getImg();
            img = "http://70.12.60.111/webview/"+img;
            final String temp = img;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url = null;
                    InputStream is = null;
                    try {
                        url = new URL(temp);
                        is = url.openStream();
                        final Bitmap bm = BitmapFactory.decodeStream(is);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageBitmap(bm);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            tv1.setText(alist.get(i).getName()); //
            tv2.setText(alist.get(i).getPhone());

            int num = Integer.parseInt(alist.get(i).getArea());
            if(num < 500000 ){
                ratingBar.setRating(1);
            }else if(num < 1000000){
                ratingBar.setRating(2);
            }else if(num < 1500000){
                ratingBar.setRating(3);
            }else if(num < 2000000){
                ratingBar.setRating(4);
            }else {
                ratingBar.setRating(5);
            }

            return myview;
        }
    }

    public void clickBt(View view){
        getData();
    }
    private void getData(){
        String url = "http://70.12.60.111/webview/nature2.jsp";
        HttpTask httpTask = new HttpTask(url);
        httpTask.execute();
    }
    class HttpTask extends AsyncTask<String, Void, String> {
        String url;
        public HttpTask(String url) {
            this.url = url;
        }
        @Override
        protected void onPreExecute() {
            progressDialog.setTitle("Http Connecting ..");
            progressDialog.setMessage("Please Wait ..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            String str = HttpHandler.getString(url);
            return str;
        }
        @Override
        protected void onPostExecute(String str) {
            progressDialog.dismiss();
//            Log.d("[JSON]",str);
            JSONArray ja = null;
            try {
                ja = new JSONArray(str);
                for(int i=0; i<ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String name = jo.getString("휴양림명");
                    String phone = jo.getString("휴양림전화번호");
                    String area = jo.getString("휴양림면적");
                    String img = "park.png";
                    list.add(new Item(name,phone,area,img));
                    Log.d("[JO]",""+name+phone+area+img);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            itemAdapter = new ItemAdapter(list);
            listView.setAdapter(itemAdapter);
        }
    }
}
