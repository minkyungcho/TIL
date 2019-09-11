package com.example.p577;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

//    ArrayList<Memo> memolist;
    ListView listView;
    LinearLayout memoContainer;
    ItemAdapter itemAdapter;

    ArrayList<Memo> memoArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(this);



        Intent intent = getIntent();
//        memoArrayList = new ArrayList<>();

        //
        memoArrayList = (ArrayList<Memo>) intent.getSerializableExtra("item");

        itemAdapter = new ItemAdapter(memoArrayList);

        listView.setAdapter(itemAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    class ItemAdapter extends BaseAdapter{

        ArrayList<Memo> List;

        public ItemAdapter() {
        }

        public ItemAdapter(ArrayList<Memo> list) {
            this.List = list;
        }

        @Override
        public int getCount() {
            return List.size();
        }

        @Override
        public Object getItem(int i) {
            return List.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View myview = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            myview = inflater.inflate(R.layout.memolayout, memoContainer, true);
            final TextView textDate = myview.findViewById(R.id.textView);
            final TextView textTitle = myview.findViewById(R.id.textView2);

            textDate.setText(List.get(i).getDate());
            textTitle.setText(List.get(i).getTitle());
            final String contents = List.get(i).getCont();
            myview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("td",textDate.getText().toString());
                    intent.putExtra("tt", textTitle.getText().toString());
                    intent.putExtra("tc", contents);
                    startActivity(intent);
                }
            });
            return myview;
        }
    }
}
