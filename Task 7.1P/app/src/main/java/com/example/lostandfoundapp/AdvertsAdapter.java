package com.example.lostandfoundapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostandfoundapp.Advert;
import com.example.lostandfoundapp.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

// Adapter for handling Advert items in a RecyclerView of AllAdvertsActivity.
public class AdvertsAdapter extends RecyclerView.Adapter<AdvertsAdapter.AdvertViewHolder> {

    private List<Advert> adverts;
    private Context context;

    // Constructor for AdvertsAdapter
    public AdvertsAdapter(List<Advert> adverts, Context context) {
        this.adverts = adverts;
        this.context = context;
    }

    //Called when RecyclerView needs a new ViewHolder of the given (all_adverts_item) type to represent an item
    @NonNull
    @Override
    public AdvertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for each item
        View view = LayoutInflater.from(context).inflate(R.layout.all_adverts_item, parent, false);
        return new AdvertViewHolder(view);
    }

    //Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(@NonNull AdvertViewHolder holder, int position) {
        Advert advert = adverts.get(position);
        holder.bind(advert);


        // Set click listener for the card, change the activity to Remove Advert, and put Extra values in string
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RemoveAdvertActivity.class);
                // Retrieve the text from nameTextView
                String name = holder.nameTextView.getText().toString();
                intent.putExtra("Name", name);
                // Retrieve the text from nameTextView
                String lost = holder.isLostTextView.getText().toString();
                intent.putExtra("Lost", lost);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adverts.size();
    }

    // ViewHolder for the Advert item
    public class AdvertViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView isLostTextView;

        private MaterialCardView card;

        public AdvertViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize UI elements
            isLostTextView = itemView.findViewById(R.id.postTypeItemTv);
            nameTextView = itemView.findViewById(R.id.postItemTv);
            card = itemView.findViewById(R.id.card);
        }

        // Bind data to views
        public void bind(Advert advert) {
            nameTextView.setText(advert.getName());
            //If boolean is true, it's a Lost advert. If boolean is false, it's a found advert.
            isLostTextView.setText(advert.isLost() ? "Lost" : "Found");
        }
    }
}
