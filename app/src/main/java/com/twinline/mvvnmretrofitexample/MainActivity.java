package com.twinline.mvvnmretrofitexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    PostViewModel postViewModel = new PostViewModel();
    ListView l;
    ArrayList<String> lstData;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.list);
        lstData = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, lstData);
        l.setAdapter(adapter);
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getPostData().observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                for (int i=0; i<postModels.size(); i++){
                    Log.d("title:    ",postModels.get(i).getTitle()+"\n"+postModels.get(i).getBody());
                    lstData.add(postModels.get(i).getTitle()+"\n"+postModels.get(i).getBody());
                    adapter.notifyDataSetChanged();

                }


            }
        });



    }
}