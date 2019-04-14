package com.example.atishay.event_lister.Chapter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.atishay.event_lister.Config;
import com.example.atishay.event_lister.Events;
import com.example.atishay.event_lister.Events_db;
import com.example.atishay.event_lister.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class EventRegister extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView imageView;
    private int mYear,mMonth,mDay,mHour,mMinute;
    EditText Event_Dat,Event_Tim,Event_nam;
    EditText End_Dat,End_Tim;
    Button Upload;
    String picturePath;
    DatabaseReference databaseReference;
    ArrayList<Events_db> events_list;
    String event_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_info_chapter);
        databaseReference= FirebaseDatabase.getInstance().getReference("Events");
        imageView = (ImageView) findViewById(R.id.event_img);
        Upload=(Button)findViewById(R.id.event_img_button);
        selectImage(imageView,Upload);
        Button btn=(Button)findViewById(R.id.myeventdescription);
        EventDetailsChapter(btn);
        Event_Dat=(EditText)findViewById(R.id.event_date);
        Event_Tim=(EditText)findViewById(R.id.event_time);
        End_Dat=(EditText)findViewById(R.id.end_date);
        End_Tim=(EditText)findViewById(R.id.end_time);
        Event_nam=findViewById(R.id.event_name);
        Button EventDateSelect=(Button) findViewById(R.id.date_selector);
        Button EventTimeSelect=(Button) findViewById(R.id.time_selector);
        Button EndDateSelect=(Button)findViewById(R.id.end_date_selector);
        Button EndTimeSelect=(Button)findViewById(R.id.end_time_selector);
        selectEventDate(EventDateSelect);
        selectEventTime(EventTimeSelect);
        selectEventEndDate(EndDateSelect);
        selectEventEndTime(EndTimeSelect);
        events_list=new ArrayList<Events_db>();
    }

    public void EventDetailsChapter(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Event_Descriptio=(EditText)findViewById(R.id.event_description);
                EditText Event_Dat=(EditText)findViewById(R.id.event_date);
                EditText Event_Tim=(EditText)findViewById(R.id.event_time);
                EditText Max_registratio=(EditText)findViewById(R.id.registration_number);
                EditText End_Dat=(EditText)findViewById(R.id.end_date);
                EditText End_Tim=(EditText)findViewById(R.id.end_time);
                Spinner CategorySelect = (Spinner) findViewById(R.id.category_dropdown);
                String Event_name=Event_nam.getText().toString();
                String Event_Category = CategorySelect.getSelectedItem().toString();
                String Event_Description=Event_Descriptio.getText().toString();
                String Event_Date=Event_Dat.getText().toString();
                String Event_Time=Event_Tim.getText().toString();
                int Max_Registration=Integer.parseInt(Max_registratio.getText().toString());
                String End_Date=End_Dat.getText().toString();
                String End_Time=End_Tim.getText().toString();
                String chapter_name = Config.getSharedPreferences(getApplicationContext(), "pref_detail", "chapter_name", "");


                event_id=databaseReference.push().getKey();
                Events_db events_db=new Events_db(chapter_name,Event_name,Event_Category,Event_Description,Event_Date, Event_Time,Max_Registration,End_Date,End_Time,picturePath,event_id);
                databaseReference.child(event_id).setValue(events_db);
            }
        });
    }

    public void selectEventEndTime(Button endTimeSelect) {
        endTimeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(EventRegister.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                End_Tim.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
    }

    public void selectEventEndDate(Button endDateSelect) {
        endDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EventRegister.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                End_Dat.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
    }
    public void selectEventTime(Button EventTimeSelect) {
        EventTimeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(EventRegister.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                Event_Tim.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
    }
    public void selectEventDate(Button EventDateSelect) {
        EventDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EventRegister.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Event_Dat.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }


        });
        //Get Current Date
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            // String picturePath contains the path of selected Image
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    private void selectImage(ImageView imageView,Button Upload) {
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

}




