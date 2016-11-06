package com.example.zhangning.cookbook.category;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.zhangning.cookbook.category.beans.Category;
import com.example.zhangning.cookbook.networking.MyVolley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by zhangning on 16/10/4.
 */

public class CategoryDaoImpl implements CategoryDao ,MyVolley.CallBack{
    CategoryDaoListner listener;

    @Override
    public void getCategory(Context ctx, CategoryDaoListner listener) {
        this.listener=listener;
        String url = "http://apis.juhe.cn/cook/category?parentid=&dtype=&key=3ab5a0b03be1c3254bf6cf9ec2914b5f";
        MyVolley myVolley = MyVolley.newMyVolley();
        myVolley.stringRequestGet(ctx, url, this);
    }

    @Override
    public void onStringSuccess(String response) {
        Gson gson=new Gson();
        Category c=gson.fromJson(response,new TypeToken<Category>(){
        }.getType());
        listener.onSuccess(c);

    }

    @Override
    public void onFailure(VolleyError error) {
        listener.onFail();
    }
}
