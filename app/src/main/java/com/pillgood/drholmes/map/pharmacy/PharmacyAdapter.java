package com.pillgood.drholmes.map.pharmacy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillgood.drholmes.R;

import java.util.ArrayList;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.ViewHolder> {

    private ArrayList<Pharmacy> items = new ArrayList<Pharmacy>();
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
        View itemView = inflater.inflate(R.layout.pharmacy_list_recycle_item, parent, false);

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
        Pharmacy item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Pharmacy item) {
        items.add(item);
    }

    public void setItems(ArrayList<Pharmacy> items) {
        this.items = items;
    }

    public Pharmacy getItem(int position) {
        return  items.get(position);
    }

    public void setItem(int position, Pharmacy item) {
        items.set(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPharmacyName;
        private TextView tvPharmacyAddress;
        private TextView tvPharmacyTel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPharmacyName = itemView.findViewById(R.id.pharmacy_name);
            tvPharmacyAddress = itemView.findViewById(R.id.pharmacy_address);
            tvPharmacyTel = itemView.findViewById(R.id.pharmacy_tel);
        }

        public void setItem(Pharmacy item) {
            tvPharmacyName.setText(item.getName());
            tvPharmacyAddress.setText(item.getAddress());
            tvPharmacyTel.setText(item.getTel());
        }
    }
}