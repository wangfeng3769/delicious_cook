package com.example.zhangning.cookbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.zhangning.cookbook.category.beans.Category;
import com.example.zhangning.cookbook.category.beans.Category1;
import com.example.zhangning.cookbook.networking.MyVolley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyVolley.CallBack{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStringSuccess(String response) {
        Gson gson=new Gson();
        Category c=gson.fromJson(response,new TypeToken<Category>(){
        }.getType());


        List<Category1> list1=c.getResult();
        for (Category1 c1:list1){

        }
    }

    @Override
    public void onFailure(VolleyError error) {

    }
}
