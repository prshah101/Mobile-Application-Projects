//package com.example.personalizedlearningexperienceapp;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class MyAdapter extends RecyclerView.Adapter {
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // Inflate layout for each item view when View Holder is created
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
//        return new MyHolder(view);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        holder.questionTv.setText(newsAgency.get(position));
//        holder.option1.setText(newsDescription.get(position));
//        holder.option2.setImageResource(imageResourceIds.get(position));
//        holder.option3.setImageResource(imageResourceIds.get(position));
//        holder.option4.setImageResource(imageResourceIds.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//
//    // ViewHolder class to hold item views
//    class MyHolder extends RecyclerView.ViewHolder {
//        TextView questionTv;
//
//        RadioButton option1;
//        RadioButton option2;
//        RadioButton option3;
//        RadioButton option4;
//
//        public MyHolder(@NonNull View itemView) {
//            super(itemView);
//            questionTv = itemView.findViewById(R.id.questionTv);
//            option1 = itemView.findViewById(R.id.option1);
//            option2 = itemView.findViewById(R.id.option2);
//            option3 = itemView.findViewById(R.id.option3);
//            option4 = itemView.findViewById(R.id.option4);
//        }
//    }
//}
