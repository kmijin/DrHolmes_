package com.pillgood.drholmes.info;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pillgood.drholmes.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.ViewHolder> {

    private ArrayList<Pill> items = new ArrayList<Pill>();
    private PillAdapter.OnPillItemClickListener itemClickListener;

    public interface OnPillItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(PillAdapter.OnPillItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public PillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.pill_list_recycle_item, parent, false);

        PillAdapter.ViewHolder viewHolder = new PillAdapter.ViewHolder(itemView);

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
    public void onBindViewHolder(@NonNull PillAdapter.ViewHolder holder, int position) {
        Pill item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Pill item) {
        items.add(item);
    }

    public void setItems(ArrayList<Pill> items) {
        this.items = items;
    }

    public void clearItems() {
        items.clear();
    };

    public Pill getItem(int position) {
        return  items.get(position);
    }

    public void setItem(int position, Pill item) {
        items.set(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPillIcon;
        private TextView tvPillName;
        private TextView tvPillEffect;
        private TextView tvPillDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPillIcon = itemView.findViewById(R.id.pill_icon);
            tvPillName = itemView.findViewById(R.id.pill_name);
            tvPillEffect = itemView.findViewById(R.id.pill_effect);
            tvPillDesc = itemView.findViewById(R.id.pill_desc);
        }

        public void setItem(Pill item) {
            new DrawUrlImageTask(ivPillIcon).execute(item.getIcon());

            tvPillName.setText(item.getName());
            tvPillEffect.setText(item.getEffect());
            tvPillDesc.setText(item.getDesc());
        }
    }

    private static class DrawUrlImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView iv;

        public DrawUrlImageTask(ImageView ivSample) {
            this.iv = ivSample;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            InputStream in = null;

            try {
                in = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            iv.setImageBitmap(bitmap);
        }
    }
}
