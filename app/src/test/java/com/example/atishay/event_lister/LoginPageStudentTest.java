package com.example.atishay.event_lister;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static com.google.firebase.FirebaseApp.getApps;
import static java.security.AccessController.getContext;
import static org.junit.Assert.*;
import android.content.Context;

public class LoginPageStudentTest {
    FirebaseAuth firebaseauth;
    String email="500055748@gmail.com";
    String password="123456";

 /*   @Rule
    public TestRule rule = new InstantTaskExecutorRule();*/

    @Test
    public void mylogin() {
        String emailPattern = "[a-zA-Z0-9._-]+@stu.upes.ac.in";
        Pattern p=Pattern.compile(emailPattern);
        Matcher m=p.matcher(email);
        boolean b=m.matches();
        assertFalse(b);     //produces false as an output which passes the test case
    }

    /*@Test
    public void firebase_auth() {
        FirebaseApp.initializeApp(getApps());
        firebaseauth= FirebaseAuth.getInstance();
        firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                assertFalse(task.isSuccessful());
            }
        });

    }*/

}