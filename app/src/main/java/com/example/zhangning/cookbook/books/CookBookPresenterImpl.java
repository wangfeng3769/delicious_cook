package com.example.zhangning.cookbook.books;

import android.content.Context;

import com.example.zhangning.cookbook.cookbook.beans.CookBook;

import java.util.List;

/**
 * Created by zhangning on 16/10/4.
 */

public class CookBookPresenterImpl implements CookBookPresenter,CookBookDao.CookBookListener {

    CookBookDao dao;

    CookBookView cookBookView;

    public CookBookPresenterImpl(CookBookView cookBookView){
        this.cookBookView=cookBookView;
        dao=new CookBookDaoImpl();
    }
    @Override
    public void onSuccess(List<CookBook> list) {
        cookBookView.setCookBookList(list);

    }

    @Override
    public void onFail() {
        cookBookView.setFail();
    }

    @Override
    public void getCookBookListById(Context context, String id) {
        dao.getCookBookListById(context,id,this);
    }

    @Override
    public void getCookBookListByKey(Context context, String key) {
        dao.getCookBookListByKey(context,key,this);
    }
}
