package com.if5b.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if5b.myapplication.R;
import com.if5b.myapplication.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> data = new ArrayList<>();
    private OnClickListener listener;

    public PostAdapter() {

    }

    public void setData(List<Post> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        int posisi = holder.getAdapterPosition();
        Post post = data.get(posisi);
        holder.tvContent.setText(post.getContent());
        holder.tvDate.setText(post.getCreatedDate());
        holder.tvUsername.setText(post.getUsername());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onEditClicked(post);
                }
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    int id = Integer.parseInt(post.getId());
                    listener.onDeleteClicked(id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent, tvDate, tvUsername;
        ImageView ivEdit, ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onEditClicked(Post post);
        void onDeleteClicked(int id);
    }
}
