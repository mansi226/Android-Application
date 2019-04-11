package com.example.atishay.event_lister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.atishay.event_lister.Events_list_student.Event_lister_Activity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button)findViewById(R.id.event_card);
        databaseReference=FirebaseDatabase.getInstance().getReference("Events");
        String id=databaseReference.push().getKey();
        databaseReference.setValue("Hackathon 3.0");

        eventspage(btn);                                    //to call events list
    }
    public void eventspage(Button btn){                     // this is for the events listing
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Event_lister_Activity.class);
                startActivity(intent);
            }
        });
    }
}
