package com.example.atishay.event_lister.Chapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atishay.event_lister.Config;
import com.example.atishay.event_lister.Events_db;
import com.example.atishay.event_lister.Events_list_student.Event_lister_Activity;
import com.example.atishay.event_lister.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPageChapter extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@stu.upes.ac.in";
    DatabaseReference databaseReference;
    String email;
    String password;
    EditText emailtext;
    EditText passwordtext;
    Button button;
    int x=-1;
    check_db_chapter check_db_chapter=new check_db_chapter();
    /*public void Signuppage(View view)
    {
        Intent i=new Intent(this,SignUpActivity.class);
        startActivity(i);;

    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_chapter);
        emailtext=findViewById(R.id.emailText);
        passwordtext= findViewById(R.id.passwordText);
        button=findViewById(R.id.registerbutton);
        databaseReference= FirebaseDatabase.getInstance().getReference("Student Chapter");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailtext.getText().toString().equals("") || passwordtext.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter valid Username and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    email=emailtext.getText().toString();
                    password=passwordtext.getText().toString();
                    Log.i("Email and password",email+password);
                    check_db();
                }
            }
        });

    }
   public void check_db(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        chapter_entry_in_db chapter_entry_in_db = postSnapshot.getValue(chapter_entry_in_db.class);
                        Log.i("Values are", chapter_entry_in_db.getId()+chapter_entry_in_db.getPassword());
                        if (chapter_entry_in_db.getId().equals(email) && chapter_entry_in_db.getPassword().equals(password)) {
                            x=1;
                            Config.putSharedPreferences(getApplicationContext(), "pref_detail", "user_name", email);
                            Config.putSharedPreferences(getApplicationContext(), "pref_detail", "user_password", password);
                            Toast.makeText(getApplicationContext(), "Shared Prefresnces set as " + email + " " + password, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), chapter_login_success.class);
                            startActivity(i);
                        }
                    }
                    if(x==-1){
                    Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
