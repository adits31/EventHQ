package com.example.sampaniccia.learningthings.Objects;



public class Comment {

    public Comment(String n, String t){
        name = n;
        text = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String name, text;





}
