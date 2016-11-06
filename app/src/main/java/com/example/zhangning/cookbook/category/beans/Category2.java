package com.example.zhangning.cookbook.category.beans;

/**
 * Created by zhangning on 16/9/13.
 */
public class Category2 {
    String parentId;
    String id;
    String name;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category2(String parentId, String id, String name) {
        this.parentId = parentId;
        this.id = id;
        this.name = name;
    }
    public Category2() {

    }
}
