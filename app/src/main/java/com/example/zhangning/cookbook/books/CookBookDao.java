package com.example.zhangning.cookbook.books;

import android.content.Context;

import com.example.zhangning.cookbook.cookbook.beans.CookBook;

import java.util.List;

/**
 * Created by zhangning on 16/10/4.
 */

public interface CookBookDao {
    interface CookBookListener{
        public void onSuccess(List<CookBook> list);

        public void onFail();
    }

    public void getCookBookListById(Context context,String id,CookBookListener listener);

    public void getCookBookListByKey(Context context,String key,CookBookListener listener);
}
