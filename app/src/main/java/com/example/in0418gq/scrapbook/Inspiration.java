package com.example.in0418gq.scrapbook;



import java.util.Date;
import java.util.UUID;

/**
 * Created by in0418gq on 10/18/16.
 */
public class Inspiration {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    //generates random number
    public Inspiration(){
        this (UUID.randomUUID());
    }

    public Inspiration (UUID id){
        mId=id;
        mDate = new Date();
    }
    //getters
    public UUID getId(){return mId;}
    public String getTitle(){return mTitle;}
    public Date getDate(){return mDate;}
    //setters
    public void setTitle(String title){mTitle=title;}
    public void setDate(Date date){mDate=date;}
}
