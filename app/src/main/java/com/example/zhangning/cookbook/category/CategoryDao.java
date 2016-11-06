package com.example.zhangning.cookbook.category;

import android.content.Context;

import com.example.zhangning.cookbook.category.beans.Category;

/**
 * Created by zhangning on 16/10/4.
 */

public interface CategoryDao {
    public void getCategory(Context ctx,CategoryDaoListner listener);

    interface CategoryDaoListner{
        public void onSuccess(Category category);

        public void onFail();
    }




}
