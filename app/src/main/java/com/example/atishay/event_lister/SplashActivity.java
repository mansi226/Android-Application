package com.example.atishay.event_lister;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.atishay.event_lister.Chapter.chapter_entry_in_db;
import com.example.atishay.event_lister.Chapter.chapter_login_success;
import com.example.atishay.event_lister.Student.student_login_success;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME = 4000; //This is 4 seconds
    Context context=this;
    String id="",pass="";
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Config.isOnline(getApplicationContext())){
                    id = Config.getSharedPreferences(context, "pref_detail", "user_name", "");
                    pass = Config.getSharedPreferences(context, "pref_detail", "user_password", "");
                }
                if (id.equals("")||pass.equals("")){
                    Config.openActivity(context,new Login());
                    finish();
                }
                else {

                    String logintype=Config.getSharedPreferences(context,"login_type","l_type","");
                    if (logintype.equals("student")){
                        Intent i = new Intent(getApplicationContext(), student_login_success.class);
                        startActivity(i);
                    }
                    else {
                        Intent i = new Intent(getApplicationContext(), chapter_login_success.class);
                        startActivity(i);
                    }
                }
                //Do any action here. Now we are moving to next page
               /* Intent mySuperIntent = new Intent(SplashActivity.this, Login.class);
                startActivity(mySuperIntent);*/
                /* This 'finish()' is for exiting the app when back button pressed
                 *  from Home page which is ActivityHome
                 */
                finish();
            }
        }, SPLASH_TIME);

        //to add student chapter database only remove from comment when student chapter to be added else dont
        chapter_entry_in_db chapter_entry_in_db=new chapter_entry_in_db();
        chapter_entry_in_db.add_chapter("CSI","CSI_UPES","123456");
        chapter_entry_in_db.add_chapter("ACM","ACM_UPES","123456");
        chapter_entry_in_db.add_chapter("MTC","MTC_UPES","123456");
        chapter_entry_in_db.add_chapter("IC","IC_UPES","123456");
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

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (shouldShowRequestPermissionRationale(permissions[i])) {
                        new AlertDialog.Builder(this)
                                .setMessage("Unable to get your Location")
                                .setPositiveButton("Allow", (dialog, which) -> requestMultiplePermissions())

                                .create()
                                .show();
                    }

                    return;
                }
            }

        }
    }
}
