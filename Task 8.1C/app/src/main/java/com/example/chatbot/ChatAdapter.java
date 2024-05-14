//package com.example.chatbot;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//
//class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyHolder> {
//    public ArrayList<ChatResponse.ChatHistory> data;
//
//    // Constructor to initialize adapter with data
//    public ChatAdapter(List<ChatResponse.ChatHistory> data) {
//        this.data = (ArrayList<ChatResponse.ChatHistory>) data;
//    }
//
//    @NonNull
//    @Override
//    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // Inflate layout for each item view when View Holder is created
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
//        return new MyHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
//        // Bind data to views
//        ChatResponse.ChatHistory item = data.get(position);
//        holder.chat2tv.setText(item.getLlama());
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return 1;
//    }
//
//    // ViewHolder class to hold item views
//    class MyHolder extends RecyclerView.ViewHolder {
//        TextView chat2tv;
//
//
//        public MyHolder(@NonNull View itemView) {
//            super(itemView);
//            chat2tv = itemView.findViewById(R.id.chat2tv);
//        }
//    }
//
//}