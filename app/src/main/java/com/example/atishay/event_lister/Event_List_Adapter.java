package com.example.atishay.event_lister;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Event_List_Adapter extends RecyclerView.Adapter<Event_List_Adapter.MyViewHolder> {

    private Context mContext ;
    private List<Events> mData ;
    // Provide a suitable constructor (depends on the kind of dataset)

    public Event_List_Adapter(Context mContext, List<Events> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }



    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //creating new view
        //LayoutInflater mInflater = LayoutInflater.from(mContext);
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_list_rec_view_layout,viewGroup,false);
        return new MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,Event_Description.class);

                // passing data to the book activity
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                // start the activity
                mContext.startActivity(intent);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //Log.d("Event_list_adapter", "getItemCount: size if "+meventsList.size());
        return mData.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView ;

        public MyViewHolder(View v){        // each data item is just a string in this case
            super(v);
            tv_book_title = (TextView) v.findViewById(R.id.event_title_id) ;
            img_book_thumbnail = (ImageView) v.findViewById(R.id.event_img_id);
            cardView = (CardView) v.findViewById(R.id.cardview_id);


        }
    }
}
