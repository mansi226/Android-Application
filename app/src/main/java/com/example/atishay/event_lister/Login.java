package com.example.atishay.event_lister;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    RadioButton student, chapter;
    Button submit_type;
    String login_type;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);     //main used just for no error state in actual login oage to be refrenced

        chooseDialogue();

    }

    public void chooseDialogue() {
        final AlertDialog.Builder alertdialoguebuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        //alertdialoguebuilder.setTitle("Choose Your Profile");
        alertdialoguebuilder.setCancelable(false);
        View dialogview = inflater.inflate(R.layout.login_type, null);
        alertdialoguebuilder.setView(dialogview);
        student = dialogview.findViewById(R.id.student);
        chapter = dialogview.findViewById(R.id.chapter);
        submit_type = dialogview.findViewById(R.id.submit_type);
        final AlertDialog alertDialog = alertdialoguebuilder.create();
        alertDialog.show();

        submit_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (student.isChecked() || chapter.isChecked()) {
                    if (student.isChecked()) {
                        login_type = "student";
                        Config.putSharedPreferences(getApplicationContext(), "login_type", "l_type", login_type);
                        alertDialog.dismiss();
                        String logis = Config.getSharedPreferences(getApplicationContext(), "login_type", "l_type", "");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (arePermissionsEnabled()) {
                                //getContacts();
//                    permissions granted, continue flow normally

                            } else {
                                requestMultiplePermissions();
                            }
                        }
                        Intent i = new Intent(getApplicationContext(), LoginPageStudent.class);
                        startActivity(i);

                    } else if (chapter.isChecked()) {
                        login_type = "chapter";
                        Config.putSharedPreferences(getApplicationContext(), "login_type", "l_type", login_type);
                        alertDialog.dismiss();
                        String logis = Config.getSharedPreferences(getApplicationContext(), "login_type", "l_type", "");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (arePermissionsEnabled()) {
                                //getContacts();
//                    permissions granted, continue flow normally
                            } else {
                                requestMultiplePermissions();
                            }
                        }
                        Intent i = new Intent(getApplicationContext(), LoginPageChapter.class);
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(Login.this, "Select Login Type", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean arePermissionsEnabled() {
        for (String permission : permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    private void requestMultiplePermissions() {
        List<String> remainingPermissions = new ArrayList<>();

        for (String permission : permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                remainingPermissions.add(permission);
            }
        }
        requestPermissions(remainingPermissions.toArray
                (new String[remainingPermissions.size()]), 101);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (shouldShowRequestPermissionRationale(permissions[i])) {
                        new AlertDialog.Builder(this)
                                .setMessage("Enable Permissions")
                                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Login.this.requestMultiplePermissions();
                                    }
                                })
                                .create()
                                .show();
                    }

                    return;
                }
            }

        }
    }
}