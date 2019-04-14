package com.example.atishay.event_lister.Chapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.atishay.event_lister.Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class check_db_chapter extends AppCompatActivity {
    DatabaseReference databaseReference;
    String email;
    String password;
    Context context;

    public void verify(String email, String password, Context context) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Student Chapter");
        this.email=email;
        this.password=password;
        this.context=context;
        db_connect();
    }
    public void db_connect(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    chapter_entry_in_db chapter_entry_in_db = postSnapshot.getValue(chapter_entry_in_db.class);
                    if (chapter_entry_in_db.getId().equals(email) && chapter_entry_in_db.getPassword().equals(password)) {
                        Config.putSharedPreferences(context, "pref_detail", "user_name", email);
                        Config.putSharedPreferences(context, "pref_detail", "user_password", password);
                        Toast.makeText(context, "Shared Prefresnces set as " + email + " " + password, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(context, chapter_login_success.class);
                        context.startActivity(i);
                    }
                }
                Toast.makeText(context, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
