package com.example.atishay.event_lister.Events_list_student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.support.design.widget.TabLayout;

import com.example.atishay.event_lister.Events;
import com.example.atishay.event_lister.Events_list_student.tabbed_layout.PageAdapter;
import com.example.atishay.event_lister.R;


public class Event_lister_Activity extends AppCompatActivity {

    List<Events> FirstEvent;
    public Event_List_Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_lister);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Technical"));
        tabLayout.addTab(tabLayout.newTab().setText("Non-Technical"));
        tabLayout.addTab(tabLayout.newTab().setText("Fun"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.event_pager);
        final PageAdapter adapter = new PageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
