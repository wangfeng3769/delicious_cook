package com.example.zhangning.cookbook.books;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.zhangning.cookbook.cookbook.beans.CookBook;
import com.example.zhangning.cookbook.cookbook.beans.TagCategoryList;
import com.example.zhangning.cookbook.networking.MyVolley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zhangning on 16/10/4.
 */

public class CookBookDaoImpl implements CookBookDao,MyVolley.CallBack {

    CookBookListener listener;
    @Override
    public void getCookBookListById(Context context, String id, CookBookListener listener) {
        this.listener=listener;
        String url="http://apis.juhe.cn/cook/index?dtype=&pn=&rn=&format=&key=3ab5a0b03be1c3254bf6cf9ec2914b5f&cid="+id;
        MyVolley myVolley=MyVolley.newMyVolley();
        myVolley.stringRequestGet(context,url,this);
    }

    @Override
    public void getCookBookListByKey(Context context, String key, CookBookListener listener) {
        this.listener=listener;
        String url="http://apis.juhe.cn/cook/query.php?dtype=&pn=&rn=&albums=&=&key=3ab5a0b03be1c3254bf6cf9ec2914b5f&menu="+key;
        MyVolley myVolley=MyVolley.newMyVolley();
        myVolley.stringRequestGet(context,url,this);
    }

    @Override
    public void onStringSuccess(String response) {
        Gson gson=new Gson();
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(response);
            JSONObject jsonObject1=jsonObject.getJSONObject("result");
            TagCategoryList tcl=gson.fromJson(jsonObject1.toString(), new TypeToken<TagCategoryList>(){}.getType());
            List<CookBook> list=tcl.getData();
            listener.onSuccess(list);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(VolleyError error) {
            listener.onFail();
    }
}
