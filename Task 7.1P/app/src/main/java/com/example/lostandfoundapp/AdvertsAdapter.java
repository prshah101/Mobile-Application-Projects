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

public class AdvertsAdapter extends RecyclerView.Adapter<AdvertsAdapter.AdvertViewHolder> {

    private List<Advert> adverts;
    private Context context;

    public AdvertsAdapter(List<Advert> adverts, Context context) {
        this.adverts = adverts;
        this.context = context;
    }

    @NonNull
    @Override
    public AdvertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_adverts_item, parent, false);
        return new AdvertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertViewHolder holder, int position) {
        Advert advert = adverts.get(position);
        holder.bind(advert);


        // Set click listener for the card
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RemoveAdvertActivity.class);
                // Retrieve the text from nameTextView
                String name = holder.nameTextView.getText().toString();
                intent.putExtra("Name", name);
                // Retrieve the text from nameTextView
                String lost = holder.isLostTextView.getText().toString();
                intent.putExtra("Lost", lost);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adverts.size();
    }

    public class AdvertViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView isLostTextView;

        private MaterialCardView card;

        public AdvertViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.postTypeItemTv);
            isLostTextView = itemView.findViewById(R.id.postItemTv);
            card = itemView.findViewById(R.id.card);
        }

        public void bind(Advert advert) {
            nameTextView.setText(advert.getName());
            isLostTextView.setText(advert.isLost() ? "Lost" : "Found");
        }
    }
}
