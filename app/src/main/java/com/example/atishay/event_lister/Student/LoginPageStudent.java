package com.example.atishay.event_lister.Student;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atishay.event_lister.Config;
import com.example.atishay.event_lister.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageStudent extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@stu.upes.ac.in";
    FirebaseAuth firebaseauth;
    String email;
    String password;

    Task<AuthResult> task;

    public void Signuppage(View view)
    {
        Intent i=new Intent(this,SignUpActivity.class);
        startActivity(i);;

    }
    public void Mylogin(View view)
    {
        EditText emailtext=(EditText) findViewById(R.id.emailText);
        EditText passwordtext=(EditText) findViewById(R.id.passwordText);
        Boolean valid=false;
        String email=emailtext.getText().toString();
        String password=passwordtext.getText().toString();
        firebase_auth(email,password);
    }

    public void firebase_auth(String email,String password){
        firebaseauth=FirebaseAuth.getInstance();


        if(email.equals("")||password.equals("")||!(email.matches(emailPattern)))
        {
            Toast.makeText(this,"Enter valid Username and Password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Config.putSharedPreferences(getApplicationContext(),"pref_detail","user_name",email);
                        Config.putSharedPreferences(getApplicationContext(),"pref_detail","user_password",password);
                        Toast.makeText(getApplicationContext(),"Shared Prefresnces set as "+email+" "+password,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), student_login_success.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Valid Username and Password",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Wrong Username or Password",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_student);
    }
}
