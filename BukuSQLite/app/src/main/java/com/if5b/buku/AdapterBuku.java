package com.if5b.buku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBuku extends RecyclerView.Adapter<AdapterBuku.ViewHolderBuku> {
    private Context ctx;
    private ArrayList arrJudul, arrPenulis, arrTahun;

    public AdapterBuku(Context ctx, ArrayList arrJudul, ArrayList arrPenulis, ArrayList arrTahun) {
        this.ctx = ctx;
        this.arrJudul = arrJudul;
        this.arrPenulis = arrPenulis;
        this.arrTahun = arrTahun;
    }

    @NonNull
    @Override
    public ViewHolderBuku onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater pompa = LayoutInflater.from(ctx);
        View view = pompa.inflate(R.layout.card_item, parent, false);
        return new ViewHolderBuku(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBuku holder, int position) {
        holder.tvJudul.setText(arrJudul.get(position).toString());
        holder.tvPenulis.setText(arrPenulis.get(position).toString());
        holder.tvTahun.setText(arrTahun.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrJudul.size();
    }

    public class ViewHolderBuku extends RecyclerView.ViewHolder {
        TextView tvJudul, tvPenulis, tvTahun;

        public ViewHolderBuku(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvPenulis = itemView.findViewById(R.id.tv_penulis);
            tvTahun = itemView.findViewById(R.id.tv_tahun);

            // video ke-3 menit ke 13:40
        }
    }
}
