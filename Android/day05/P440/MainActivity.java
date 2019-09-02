package com.example.p440;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ArrayList<Item> list;
    ArrayList<Integer> imgList;
    ListView listView;
    LinearLayout container;
    ItemAdapter itemAdapter;
    EditText editTextName;
    EditText editTextPhone;
    Spinner spinner;
    ImageView imageView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);

        container = findViewById(R.id.container);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        String[] Permissions = {
                Manifest.permission.CALL_PHONE,
        };
        ActivityCompat.requestPermissions(this, Permissions, 101);
        getItemData();
        getImgData();
        itemAdapter = new ItemAdapter(list);
        listView.setAdapter(itemAdapter);
        ArrayAdapter<Integer> imgAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, imgList);
        imgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(imgAdapter);

//        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    private void getImgData() {
        imgList = new ArrayList<>();
        imgList.add(R.drawable.cooker);
        imgList.add(R.drawable.detective);
        imgList.add(R.drawable.doctor);
        imgList.add(R.drawable.employee);
        imgList.add(R.drawable.mechanic);
        imgList.add(R.drawable.meditation);
        imgList.add(R.drawable.police);
        imgList.add(R.drawable.policeman);
        imgList.add(R.drawable.support);
        imgList.add(R.drawable.woman);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Item item = list.get(i);
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
        float temp = ratingBar.getRating()+1;
        ratingBar.setRating(temp);
    }

    class ItemAdapter extends BaseAdapter{

        ArrayList<Item> itemList;

        public ItemAdapter() {
        }

        public ItemAdapter(ArrayList<Item> itemList) {
            this.itemList = itemList;
        }
        public void addItem(Item item){
            itemList.add(item);
            list = itemList;

        }
        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int i) {
            return itemList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myview = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = inflater.inflate(R.layout.layout, container, true);
            ImageView iv = myview.findViewById(R.id.imageView);
            TextView textName = myview.findViewById(R.id.textName);
            TextView textPhone = myview.findViewById(R.id.textPhone);
            ratingBar = myview.findViewById(R.id.ratingBar);
            ratingBar.setRating(0);
            ratingBar.setNumStars(5);
            ratingBar.setMax(5);
            ratingBar.setStepSize(1);
            final int a=i;
            myview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemList.get(a).plus();
                    ratingBar.setRating(itemList.get(a).num);
                }
            });
            iv.setImageResource(itemList.get(i).getImgId());
            textName.setText(itemList.get(i).getName());
            textPhone.setText(itemList.get(i).getPhone());
            return myview;
        }
    }
    public void getItemData(){
        list = new ArrayList<>();
        list.add(new Item("밍경", "010-1234-0000", R.drawable.cooker, 0));
        list.add(new Item("밍지", "010-1234-1111", R.drawable.detective, 0));
    }

    public void clickBt(View view){
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        int imgCode = parseInt(spinner.getSelectedItem().toString());
        int num = 0;
        itemAdapter.addItem(new Item(name,phone,imgCode, 0));
        itemAdapter.notifyDataSetChanged();
//        int s = list.size();
//        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        editTextPhone.setText("");
        editTextName.setText("");
    }
}
