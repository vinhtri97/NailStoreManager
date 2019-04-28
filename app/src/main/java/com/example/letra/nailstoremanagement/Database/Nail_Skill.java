package com.example.letra.nailstoremanagement.Database;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.letra.nailstoremanagement.R;

public class Nail_Skill{
    private String name;
    private String price;
    private String description;
    private String img_src;
    private int id;
    public Nail_Skill(int id, String name, String price, String description, String img_src) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img_src = img_src;
    }

    public Nail_Skill getNailSkillCard(){
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }
}
