package com.if5a.tulisaja.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if5a.tulisaja.R;
import com.if5a.tulisaja.models.post;

import java.util.ArrayList;
import java.util.List;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder> {
    private List<post> data = new ArrayList<>();

    public void setData(List<post> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PostViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewAdapter.ViewHolder holder, int position) {
        post post = data.get(position);
        holder.tvUsername.setText(post.getUsername());
        holder.tvContent.setText(post.getContent());
        holder.tvDate.setText(post.getCreateDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent, tvUsername, tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
