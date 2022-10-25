package com.if5b4.kamus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if5b4.kamus.R;
import com.if5b4.kamus.models.Kamus;

import java.util.ArrayList;

public class KamusViewAdapter extends RecyclerView.Adapter<KamusViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Kamus> data = new ArrayList<Kamus>();

    public KamusViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Kamus> kamus) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KamusViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamus, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KamusViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        holder.tvDescription.setText(data.get(pos).getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}
