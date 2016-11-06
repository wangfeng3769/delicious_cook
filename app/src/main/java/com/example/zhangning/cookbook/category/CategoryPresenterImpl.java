package com.example.zhangning.cookbook.category;

import android.content.Context;

import com.example.zhangning.cookbook.category.beans.Category;

/**
 * Created by zhangning on 16/10/4.
 */

public class CategoryPresenterImpl implements CategoryPresenter ,CategoryDao.CategoryDaoListner{


        CategoryView categoryView;
        CategoryDao dao;
        Context ctx;

        public CategoryPresenterImpl(Context ctx,CategoryView categoryView) {
            this.ctx = ctx;
            this.categoryView = categoryView;
            dao = new CategoryDaoImpl();
        }

        @Override
        public void getCategory() {
            dao.getCategory(ctx,this);
        }

        @Override
        public void onSuccess(Category category) {
            categoryView.setCategory(category);
        }

        @Override
        public void onFail() {
            categoryView.setFail();
        }
    }