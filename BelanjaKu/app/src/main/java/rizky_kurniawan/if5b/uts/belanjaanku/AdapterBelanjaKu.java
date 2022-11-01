package rizky_kurniawan.if5b.uts.belanjaanku;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBelanjaKu extends RecyclerView.Adapter<AdapterBelanjaKu.ViewHolder> {
    private Context context;
    private ArrayList arrid, arrnama, arrkategori, arrharga;

    public AdapterBelanjaKu(Context context, ArrayList arrid, ArrayList arrnama, ArrayList arrkategori, ArrayList arrharga) {
        this.context = context;
        this.arrid = arrid;
        this.arrnama = arrnama;
        this.arrkategori = arrkategori;
        this.arrharga = arrharga;
    }

    @NonNull
    @Override
    public AdapterBelanjaKu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBelanjaKu.ViewHolder holder, int position) {
        holder.tv_id.setText(arrid.get(position).toString());
        holder.tv_nama.setText(arrnama.get(position).toString());
        holder.tv_kategori.setText(arrkategori.get(position).toString());
        holder.tv_harga.setText(arrharga.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrid.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id, tv_nama, tv_kategori, tv_harga;
        Button btn_add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_kategori = itemView.findViewById(R.id.tv_kategori);
            tv_harga = itemView.findViewById(R.id.tv_harga);
        }
    }
}
