package com.example.zhangning.cookbook.books;

import android.content.Context;

/**
 * Created by zhangning on 16/10/4.
 */

public interface CookBookPresenter {
    public void getCookBookListById(Context context,String id);

    public void getCookBookListByKey(Context context,String key);
}
