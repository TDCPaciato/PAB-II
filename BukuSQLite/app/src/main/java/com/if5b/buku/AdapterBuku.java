package com.if5b.buku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBuku extends RecyclerView.Adapter<AdapterBuku.ViewHolderBuku> {
    private Context ctx;
    private ArrayList arrID, arrJudul, arrPenulis, arrTahun;

    public AdapterBuku(Context ctx, ArrayList arrID, ArrayList arrJudul, ArrayList arrPenulis, ArrayList arrTahun) {
        this.ctx = ctx;
        this.arrID = arrID;
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
    public void onBindViewHolder(@NonNull ViewHolderBuku holder,  int position) {
        holder.tvId.setText(arrID.get(position).toString());
        holder.tvJudul.setText(arrJudul.get(position).toString());
        holder.tvPenulis.setText(arrPenulis.get(position).toString());
        holder.tvTahun.setText(arrTahun.get(position).toString());

        holder.cvBuku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder jendelaPesan = new AlertDialog.Builder(ctx);
                jendelaPesan.setMessage("Pilih Perintah yang Diinginkan!");
                jendelaPesan.setTitle("Perhatian");
                jendelaPesan.setCancelable(true);

                jendelaPesan.setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                        long eksekusi = myDB.HapusBuku(arrID.get(position).toString());
                        
                        if (eksekusi == -1) {
                            Toast.makeText(ctx, "Gagal Menghapus Data!", Toast.LENGTH_SHORT).show();
                        }
                        
                        else {
                            Toast.makeText(ctx, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                            if (position == 0) {
                                MainActivity.posisiData = 0;
                            }

                            else {
                                MainActivity.posisiData = position -1;
                            }

                            dialogInterface.dismiss();
                            ((MainActivity) ctx).onResume();
                        }
                    }
                });

                jendelaPesan.setPositiveButton("UBAH", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent pindah = new Intent(ctx, UbahActivity.class);
                        pindah.putExtra("varID", arrID.get(position).toString());
                        pindah.putExtra("varJudul", arrJudul.get(position).toString());
                        pindah.putExtra("varPenulis", arrPenulis.get(position).toString());
                        pindah.putExtra("varTahun", arrTahun.get(position).toString());
                        pindah.putExtra("varPosisi",position);
                        ctx.startActivity(pindah);

                    }
                });

                jendelaPesan.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrJudul.size();
    }

    public class ViewHolderBuku extends RecyclerView.ViewHolder {
        TextView tvId, tvJudul, tvPenulis, tvTahun;
        CardView cvBuku;

        public ViewHolderBuku(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvPenulis = itemView.findViewById(R.id.tv_penulis);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            cvBuku = itemView.findViewById(R.id.cv_buku);
        }
    }
}
