package com.example.atishay.event_lister.Events_list_student.tabbed_layout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atishay.event_lister.Events_list_student.Event_List_Adapter;
import com.example.atishay.event_lister.Events;
import com.example.atishay.event_lister.R;

import java.util.ArrayList;
import java.util.List;

public class TabFragment2 extends Fragment {

    RecyclerView recyclerView;
    List<Events> FirstEvent;
    public Event_List_Adapter mAdapter;
    public static TabFragment2 newInstance() {
        TabFragment2 fragment = new TabFragment2();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_fragment_2, container, false);
        recyclerView=view.findViewById(R.id.events_list_nontech);
        setadapter(getContext());
        return view;
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
