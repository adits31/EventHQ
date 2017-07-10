package com.example.sampaniccia.learningthings.Objects;

/**
 * An Announcement object represents the text shown for each Announement in the announcement view.
 * The title is the bigger text at the top (the poster) and the text is the actual announcement.
 */
public class Announcement {

    private boolean drawNormalDivider = false, isPinned;

    private String title, text;

    public Announcement(String T, String t, boolean pin, boolean divide) {
        title = T;
        text = t;
        isPinned = pin;
        drawNormalDivider = divide;
    }

    public void setDrawNormalDivider(boolean draw) {
        drawNormalDivider = draw;
    }

    public boolean getDrawNormalDivider() {
        return drawNormalDivider;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public boolean getPinned() { return isPinned; }


    public void setTitle(String t) {
        title = t;
    }

    public void setText(String t) {
        text = t;
    }

    public void setPinned(boolean b){ isPinned = b; }

}