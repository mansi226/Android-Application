package com.example.atishay.event_lister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.atishay.event_lister.Events_list_student.Event_lister_Activity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class chapter_login_success extends AppCompatActivity {
    ImageButton imageButton;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentchapter_postlogin);
        btn=findViewById(R.id.event_add);
        imageButton=findViewById(R.id.logout);

        // this is for the events listing
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), EventRegister.class);
                startActivity(intent);
            }
        });

        //to logout
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config.clearSharedPreferences(getApplicationContext());
                Config.finishAllOpenNewActivity(getApplicationContext(), new Login());
            }
        });
    }
}
