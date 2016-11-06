package com.example.zhangning.cookbook.cookbook.beans;

import java.io.Serializable;

/**
 * Created by geek99.com on 2016/8/4.
 */
public class Step implements Serializable{
    String img;
    String step;

    public Step() {
    }


    public Step(String img, String step) {
        this.img = img;
        this.step = step;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
