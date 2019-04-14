package com.example.atishay.event_lister.Chapter;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class chapter_entry_in_db {

    private String chapter_name;
    private String id;
    private String password;

    public chapter_entry_in_db(){

    }

    public chapter_entry_in_db(String chapter_name, String id, String password) {
        this.chapter_name = chapter_name;
        this.id = id;
        this.password = password;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void add_chapter(String name, String id, String password){
        DatabaseReference databaseReference;
        databaseReference= FirebaseDatabase.getInstance().getReference("Student Chapter");
        chapter_entry_in_db db_obj=new chapter_entry_in_db(name,id,password);
        databaseReference.child(name).setValue(db_obj);
    }

}
