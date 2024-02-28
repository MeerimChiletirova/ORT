package com.example.online_ort;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private final Context context;
    Activity activity;
    private ArrayList question_id, question_name, question_correct_option, question_option_1, question_option_2, question_option_3, question_option_4, question_option_5;
    Adapter(Activity activity, Context context, ArrayList question_id, ArrayList question_name, ArrayList question_correct_option,
                  ArrayList question_option_1, ArrayList question_option_2, ArrayList question_option_3, ArrayList question_option_4,
                  ArrayList question_option_5) {
        this.activity = activity;
        this.context = context;
        this.question_id = question_id;
        this.question_name = question_name;
        this.question_correct_option = question_correct_option;
        this.question_option_1 = question_option_1;
        this.question_option_2 = question_option_2;
        this.question_option_3 = question_option_3;
        this.question_option_4 = question_option_4;
        this.question_option_5 = question_option_5;
    }
    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.question_row, parent, false);
        return new Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.question_id_txt.setText(String.valueOf(question_id.get(position)));
        holder.question_name_txt.setText(String.valueOf(question_name.get(position)));
        holder.question_correct_option_txt.setText(String.valueOf(question_correct_option.get(position)));
        holder.question_option_1_txt.setText(String.valueOf(question_option_1.get(position)));
        holder.question_option_2_txt.setText(String.valueOf(question_option_2.get(position)));
        holder.question_option_3_txt.setText(String.valueOf(question_option_3.get(position)));
        holder.question_option_4_txt.setText(String.valueOf(question_option_4.get(position)));
        holder.question_option_5_txt.setText(String.valueOf(question_option_5.get(position)));
        holder.questionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateJournalActivity.class);
                intent.putExtra("id", String.valueOf(question_id.get(position)));
                intent.putExtra("name", String.valueOf(question_name.get(position)));
                intent.putExtra("correct_option", String.valueOf(question_correct_option.get(position)));
                intent.putExtra("option_1", String.valueOf(question_option_1.get(position)));
                intent.putExtra("option_2", String.valueOf(question_option_2.get(position)));
                intent.putExtra("option_3", String.valueOf(question_option_3.get(position)));
                intent.putExtra("option_4", String.valueOf(question_option_4.get(position)));
                intent.putExtra("option_5", String.valueOf(question_option_5.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return question_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView question_id_txt, question_name_txt, question_correct_option_txt, question_option_1_txt, question_option_2_txt, question_option_3_txt, question_option_4_txt, question_option_5_txt;
        LinearLayout questionLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_id_txt = itemView.findViewById(R.id.question_id_txt);
            question_name_txt = itemView.findViewById(R.id.question_name_txt);
            question_correct_option_txt = itemView.findViewById(R.id.question_correct_option_txt);
            question_option_1_txt = itemView.findViewById(R.id.question_option_1_txt);
            question_option_2_txt = itemView.findViewById(R.id.question_option_2_txt);
            question_option_3_txt = itemView.findViewById(R.id.question_option_3_txt);
            question_option_4_txt = itemView.findViewById(R.id.question_option_4_txt);
            question_option_5_txt = itemView.findViewById(R.id.question_option_5_txt);
            questionLayout = itemView.findViewById(R.id.questionLayout);
        }
    }
}
