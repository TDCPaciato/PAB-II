package com.mnag.tokobuku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private Context ctx;
    private ArrayList<Book> arrayBook;

    public BookAdapter(Context ctx, ArrayList<Book> arrayBook) {
        this.ctx = ctx;
        this.arrayBook = arrayBook;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View view = layoutInflater.inflate(R.layout.cardview, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.tvId.setText(arrayBook.get(position).getId());
        holder.tvIsbn.setText(arrayBook.get(position).getIsbn());
        holder.tvTitle.setText(arrayBook.get(position).getJudul());
        holder.tvcategory.setText(arrayBook.get(position).getKategori());
        holder.tvDesc.setText(arrayBook.get(position).getDeskripsi());
        holder.tvPrice.setText(arrayBook.get(position).getHarga());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ctx, "ID anda =" + arrayBook.get(holder.getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder messageWindow = new AlertDialog.Builder(ctx);
                messageWindow.setMessage("Yakin ingin Menghapus ?");
                messageWindow.setTitle("Attention ! ");
                messageWindow.setCancelable(true);
                messageWindow.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                messageWindow.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(ctx);
                        long result = databaseHelper.deleteBookById(arrayBook.get(holder.getAdapterPosition()));

                        if(result == -1) {
                            Toast.makeText(ctx, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ctx, "Data Berhasil di hapus", Toast.LENGTH_SHORT).show();
                            if(holder.getAdapterPosition() == 0) {
                                MainActivity.dataPosition = 0;
                            }
                            else {
                                MainActivity.dataPosition = holder.getAdapterPosition() - 1;
                            }
                            // Resume ke MainActivity
                            ((MainActivity) ctx).onResume();
                        }
                    }
                });
                messageWindow.show();
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, EditActivity.class);
                intent.putExtra("keyId", arrayBook.get(holder.getAdapterPosition()).getId());
                intent.putExtra("keyIsbn", arrayBook.get(holder.getAdapterPosition()).getIsbn());
                intent.putExtra("keyJudul", arrayBook.get(holder.getAdapterPosition()).getJudul());
                intent.putExtra("keyKategori", arrayBook.get(holder.getAdapterPosition()).getKategori());
                intent.putExtra("keyDeskripsi", arrayBook.get(holder.getAdapterPosition()).getDeskripsi());
                intent.putExtra("keyHarga", arrayBook.get(holder.getAdapterPosition()).getHarga());
                intent.putExtra("keyPosition", holder.getAdapterPosition());
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayBook.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvIsbn, tvTitle, tvcategory, tvDesc, tvPrice;
        ImageView ivDelete, ivEdit;
        CardView cvBuku;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvIsbn = itemView.findViewById(R.id.tvIsbn);
            tvTitle = itemView.findViewById(R.id.tvJudul);
            tvcategory = itemView.findViewById(R.id.tvKategori);
            tvDesc = itemView.findViewById(R.id.tvDesk);
            tvPrice = itemView.findViewById(R.id.tvHarga);
            cvBuku = itemView.findViewById(R.id.cvBuku);
            ivDelete = itemView.findViewById(R.id.btnDelete);
            ivEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
