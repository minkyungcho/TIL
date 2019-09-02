package com.example.p427;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Item> list;
    ListView listView;
    LinearLayout container;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        listView.setOnItemClickListener(this);
        String[] Permissions = {
                Manifest.permission.CALL_PHONE,
        };
        ActivityCompat.requestPermissions(this, Permissions, 101);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Item item = list.get(list.size()-i-1); // 데이터 거꾸로 출력중
        Toast.makeText(this, ""+item.getPhone(), Toast.LENGTH_SHORT).show();
        int permission = PermissionChecker.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        Intent intent = new Intent();
        if(permission == PackageManager.PERMISSION_GRANTED){
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"010-6737-0122"));
            startActivity(intent);
        }else{
            Toast.makeText(this, "권한부여가 안되었습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
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
            ImageView iv = myview.findViewById(R.id.imageView);
            TextView tv1 = myview.findViewById(R.id.textView);
            TextView tv2 = myview.findViewById(R.id.textView2);
//            iv .setImageResource(alist.get(i).getImgId());
//            tv1.setText(alist.get(i).getName()); //
//            tv2.setText(alist.get(i).getPhone());
            iv.setImageResource(alist.get(alist.size()-i-1).getImgId());
            tv1.setText(alist.get(alist.size()-i-1).getName()); //
            tv2.setText(alist.get(alist.size()-i-1).getPhone());
            return myview;
        }
    }

    public void clickBt2(View view){
        itemAdapter.addItem(new Item("김말자", "010-9876-5432", R.mipmap.ic_launcher));
        itemAdapter.notifyDataSetChanged(); // 11번 돈다. 새로 refresh 해워야함.
    }

    public void clickBt(View view){
        getData();
        itemAdapter = new ItemAdapter(list);
        listView.setAdapter(itemAdapter);
    }
    private void getData(){
        list = new ArrayList<>();
        list.add(new Item("강말숙", "010-1234-5671",R.drawable.detective));
        list.add(new Item("김말숙", "010-1234-5672",R.drawable.doctor));
        list.add(new Item("박말숙", "010-1234-5673",R.drawable.employee));
        list.add(new Item("신말숙", "010-1234-5674",R.drawable.mechanic));
        list.add(new Item("이말숙", "010-1234-5675",R.drawable.meditation));
        list.add(new Item("임말숙", "010-1234-5676",R.drawable.police));
        list.add(new Item("서말숙", "010-1234-5677",R.drawable.policeman));
        list.add(new Item("안말숙", "010-1234-5678",R.drawable.support));
        list.add(new Item("정말숙", "010-1234-5679",R.drawable.woman));
        list.add(new Item("최말숙", "010-1234-5670",R.drawable.detective));
    }
}
