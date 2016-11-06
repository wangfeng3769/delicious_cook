package com.example.zhangning.cookbook.cookbook.beans;

import java.util.List;

/**
 * Created by geek99.com on 2016/8/4.
 */
public class TagCategoryList {
    List<CookBook> data;

    public TagCategoryList() {
    }

    public TagCategoryList(List<CookBook> data) {
        this.data = data;
    }

    public List<CookBook> getData() {
        return data;
    }

    public void setData(List<CookBook> data) {
        this.data = data;
    }
}
