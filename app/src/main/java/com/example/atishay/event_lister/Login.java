package com.example.atishay.event_lister;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    RadioButton student,chapter;
    Button submit_type;
    String login_type;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);     //main used just for no error state in actual login oage to be refrenced

        chooseDialogue();

    }

    public void chooseDialogue(){
        final AlertDialog.Builder alertdialoguebuilder=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        //alertdialoguebuilder.setTitle("Choose Your Profile");
        alertdialoguebuilder.setCancelable(false);
        View dialogview=inflater.inflate(R.layout.login_type,null);
        alertdialoguebuilder.setView(dialogview);
        student=dialogview.findViewById(R.id.student);
        chapter=dialogview.findViewById(R.id.chapter);
        submit_type=dialogview.findViewById(R.id.submit_type);
        final AlertDialog alertDialog=alertdialoguebuilder.create();
        alertDialog.show();

        submit_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (student.isChecked()||chapter.isChecked()){
                    if (student.isChecked()){
                        login_type="student";
                        alertDialog.dismiss();
                        Intent i=new Intent(getApplicationContext(),LoginPageStudent.class);
                        startActivity(i);

                    }
                    else if (chapter.isChecked()){
                        login_type="chapter";
                        alertDialog.dismiss();
                        Intent i=new Intent(getApplicationContext(),LoginPageChapter.class);
                        startActivity(i);
                    }
                }
                else {
                    Toast.makeText(Login.this, "Select Login Type", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
