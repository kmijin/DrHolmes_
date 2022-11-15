package com.pillgood.drholmes.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillgood.drholmes.R;

import java.util.ArrayList;

public class PillCustomAdapter extends RecyclerView.Adapter<PillCustomAdapter.PillCustomViewHolder> {

    private ArrayList<PillModel> arrayList;
    private Context context;

    public PillCustomAdapter(ArrayList<PillModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PillCustomAdapter.PillCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_home_pill_list, parent, false);
        PillCustomViewHolder holder = new PillCustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PillCustomViewHolder holder, int position) {
        PillModel model = arrayList.get(position);
        holder.hour.setText(arrayList.get(position).getPillHour());
        holder.minute.setText(arrayList.get(position).getPillMinute());
        holder.pillName.setText(arrayList.get(position).getPillName());
        holder.cnt.setText(arrayList.get(position).getPillCount());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class PillCustomViewHolder extends RecyclerView.ViewHolder {

        TextView pillName;
        TextView hour;
        TextView minute;
        TextView cnt;

        public PillCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.pillName = itemView.findViewById(R.id.pillName);
            this.hour = itemView.findViewById(R.id.pillHour);
            this.minute = itemView.findViewById(R.id.pillMinute);
            this.cnt = itemView.findViewById(R.id.pillCnt);
        }
    }
}
