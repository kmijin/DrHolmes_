package com.pillgood.drholmes.info.disease;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillgood.drholmes.R;

import java.util.ArrayList;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.ViewHolder>{

    private ArrayList<Disease> items = new ArrayList<>();
    private DiseaseAdapter.OnPharmacyItemClickListener itemClickListener;

    public interface OnPharmacyItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(DiseaseAdapter.OnPharmacyItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public DiseaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.disease_list_item, parent, false);
        DiseaseAdapter.ViewHolder viewHolder = new DiseaseAdapter.ViewHolder(itemView);

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
    public void onBindViewHolder(@NonNull DiseaseAdapter.ViewHolder holder, int position) {
        Disease item = items.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Disease item) {
        items.add(item);
    }

    public void setItems(ArrayList<Disease> items) {
        this.items = items;
    }

    public Disease getItem(int position) {
        return  items.get(position);
    }

    public void setItem(int position, Disease item) {
        items.set(position, item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDiseaseName;
        private TextView tvDiseaseFilterName;
        private TextView tvDiseaseDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDiseaseName = itemView.findViewById(R.id.disease_name);
            tvDiseaseFilterName = itemView.findViewById(R.id.disease_filter_name);
            tvDiseaseDesc = itemView.findViewById(R.id.disease_desc);
        }

        public void setItem(Disease item) {
            tvDiseaseName.setText(item.getName());
            tvDiseaseFilterName.setText(item.getFilterName());
            tvDiseaseDesc.setText(item.getDesc());
        }
    }
}

