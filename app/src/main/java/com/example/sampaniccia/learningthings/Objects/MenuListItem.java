package com.example.sampaniccia.learningthings.Objects;

/**
 * This class represents each item in the hamburger menu.
 */

public class MenuListItem {
    private String mTitle, mSubtitle;
    int mIcon;

    public MenuListItem(String title, String sub, int icon){
        mTitle = title;
        mSubtitle = sub;
        mIcon = icon;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getSubtitle(){
        return mSubtitle;
    }

    public int getIcon(){
        return mIcon;
    }

}
