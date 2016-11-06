package com.example.zhangning.cookbook.books;

import com.example.zhangning.cookbook.cookbook.beans.CookBook;

import java.util.List;

/**
 * Created by zhangning on 16/10/4.
 */

public interface CookBookView {
    public void setCookBookList(List<CookBook> list);

    public void setFail();
}
