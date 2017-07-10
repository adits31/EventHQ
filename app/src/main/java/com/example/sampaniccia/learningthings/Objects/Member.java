package com.example.sampaniccia.learningthings.Objects;

import android.graphics.drawable.Drawable;

/**
 * Created by Sam Paniccia on 7/3/2017.
 */

public class Member {

    protected boolean isAdmin;
    protected String name, desc;
    protected Drawable prof;

    public Member(String n, String d, boolean a, Drawable p){
        name = n;
        desc = d;
        isAdmin = a;
        prof = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Drawable getProf() {
        return prof;
    }

    public void setProf(Drawable prof) {
        this.prof = prof;
    }
}
