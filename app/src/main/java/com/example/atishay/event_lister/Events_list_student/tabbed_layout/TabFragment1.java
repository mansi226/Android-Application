package com.example.atishay.event_lister.Events_list_student.tabbed_layout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.atishay.event_lister.Events_db;
import com.example.atishay.event_lister.Events_list_student.Event_List_Adapter;
import com.example.atishay.event_lister.Events;
import com.example.atishay.event_lister.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TabFragment1 extends Fragment{

    ArrayList<Events_db> events_dbArrayList= new ArrayList<>();
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    List<Events> FirstEvent;
    public Event_List_Adapter mAdapter;
    public static TabFragment1 newInstance() {
        TabFragment1 fragment = new TabFragment1();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_fragment_1, container, false);
        databaseReference= FirebaseDatabase.getInstance().getReference("Events");
        recyclerView=view.findViewById(R.id.events_list_tech);
        setadapter(getContext());
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        Toast.makeText(getContext(),"Inside onstart",Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            //events_dbArrayList.clear();
            for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                Events_db events_db=postSnapshot.getValue(Events_db.class);
                if(events_db.getEvent_Category()=="Technical") {
                    events_dbArrayList.add(events_db);
                }
            }
                Log.i("array list fetched is ",events_dbArrayList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setadapter(Context context){

        FirstEvent = new ArrayList<>();
        FirstEvent.add(new Events("The Vegitarian","Categorie Book","Description book",R.drawable.tech1));
        FirstEvent.add(new Events("The Wild Robot","Categorie Book","Description book",R.drawable.tech2));
        FirstEvent.add(new Events("Maria Semples","Categorie Book","Description book",R.drawable.fun1));
        FirstEvent.add(new Events("The Martian","Categorie Book","Description book",R.drawable.fun2));
        FirstEvent.add(new Events("He Died with...","Categorie Book","Description book",R.drawable.fun4));
        FirstEvent.add(new Events("The Vegitarian","Categorie Book","Description book",R.drawable.tech2));
        FirstEvent.add(new Events("The Wild Robot","Categorie Book","Description book",R.drawable.tech3));
        FirstEvent.add(new Events("Maria Semples","Categorie Book","Description book",R.drawable.tech2));
        FirstEvent.add(new Events("The Martian","Categorie Book","Description book",R.drawable.tech4));
        FirstEvent.add(new Events("He Died with...","Categorie Book","Description book",R.drawable.tech1));
        FirstEvent.add(new Events("The Vegitarian","Categorie Book","Description book",R.drawable.tech4));
        FirstEvent.add(new Events("The Wild Robot","Categorie Book","Description book",R.drawable.fun4));
        FirstEvent.add(new Events("Maria Semples","Categorie Book","Description book",R.drawable.fun1));
        FirstEvent.add(new Events("The Martian","Categorie Book","Description book",R.drawable.fun2));
        FirstEvent.add(new Events("He Died with...","Categorie Book","Description book",R.drawable.fun1));

        mAdapter = new Event_List_Adapter(context,FirstEvent );
        recyclerView.setLayoutManager(new LinearLayoutManager( context ) );
        recyclerView.setAdapter(mAdapter);
    }
}