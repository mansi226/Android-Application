package com.example.atishay.event_lister;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Event_lister_Activity extends AppCompatActivity {

    List<Events> FirstEvent;
    public Event_List_Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_lister);

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

        RecyclerView myrv = (RecyclerView) findViewById(R.id.events_list);
        Event_List_Adapter myAdapter = new Event_List_Adapter(this,FirstEvent );
        myrv.setLayoutManager(new LinearLayoutManager( this ) );
        myrv.setAdapter(myAdapter);
    }


}
