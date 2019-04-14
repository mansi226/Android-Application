package com.example.atishay.event_lister.Chapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atishay.event_lister.Login;
import com.example.atishay.event_lister.Student.LoginPageStudent;
import com.example.atishay.event_lister.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpChapterActivity extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@stu.upes.ac.in";
    FirebaseAuth firebaseauth;
    public void loginpage(View view)
    {
        Intent intent=new Intent(getApplicationContext(), LoginPageChapter.class);
        startActivity(intent);
        Toast.makeText(this,"Enter all the fields",Toast.LENGTH_SHORT).show();

    }

    public void register(View view)
    {
        EditText email=(EditText) findViewById(R.id.emailText);
        EditText name=(EditText) findViewById(R.id.nameText);
        EditText phone=(EditText) findViewById(R.id.phoneText);
        EditText password=(EditText) findViewById(R.id.passwordText);
        firebaseauth=FirebaseAuth.getInstance();
        //Toast.makeText(this,"Enter all the fields",Toast.LENGTH_SHORT).show();
        if(email.getText().toString().equals("")||password.getText().toString().equals("")||name.getText().toString().equals("")||phone.getText().toString().equals(""))
        {
            Toast.makeText(this,"Enter all the fields",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(!(email.getText().toString().matches(emailPattern)))
            {
                Toast.makeText(getApplicationContext(),"Enter Valid Email Address",Toast.LENGTH_SHORT).show();
            }
            else if(phone.getText().toString().length()!=10)
            {
                Toast.makeText(getApplicationContext(),"Enter Valid Phone Number",Toast.LENGTH_SHORT).show();
            }
            else {


                firebaseauth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Correct fields", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), LoginPageStudent.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page_chapter);
    }
    public void onBackPressed(){
        Intent a = new Intent(this, Login.class);
        a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        a.putExtra( "EXIT",true );
        startActivity(a);
    }
}
