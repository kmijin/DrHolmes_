package com.pillgood.drholmes.map.hospital;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillgood.drholmes.R;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    private ArrayList<Hospital> items = new ArrayList<Hospital>();
    private OnPharmacyItemClickListener itemClickListener;

    public interface OnPharmacyItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnPharmacyItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.hospital_list_recycle_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, position);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hospital item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Hospital item) {
        items.add(item);
    }

    public void setItems(ArrayList<Hospital> items) {
        this.items = items;
    }

    public Hospital getItem(int position) {
        return  items.get(position);
    }

    public void setItem(int position, Hospital item) {
        items.set(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHospitalName;
        private TextView tvHospitalDepartment;
        private TextView tvHospitalAddress;
        private TextView tvHospitalTel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHospitalName = itemView.findViewById(R.id.hospital_name);
            tvHospitalDepartment = itemView.findViewById(R.id.hospital_department);
            tvHospitalAddress = itemView.findViewById(R.id.hospital_address);
            tvHospitalTel = itemView.findViewById(R.id.hospital_tel);
        }

        public void setItem(Hospital item) {
            tvHospitalName.setText(item.getName());
            tvHospitalDepartment.setText(item.getDepartment());
            tvHospitalAddress.setText(item.getAddress());
            tvHospitalTel.setText(item.getTel());
        }
    }
}