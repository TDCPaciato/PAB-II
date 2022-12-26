package muhammadnaufalanugrah.if5b.uts.belanjaanku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BelanjaAdapter extends RecyclerView.Adapter<BelanjaAdapter.BelanjaViewHolder> {
    private Context ctx;
    private ArrayList<Belanja> arrayBelanja;

    public BelanjaAdapter(Context ctx, ArrayList<Belanja> arrayBelanja) {
        this.ctx = ctx;
        this.arrayBelanja = arrayBelanja;
    }


    @NonNull
    @Override
    public BelanjaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View view = layoutInflater.inflate(R.layout.cardview, parent, false);
        return new BelanjaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BelanjaViewHolder holder, int position) {
        holder.tvId.setText(arrayBelanja.get(position).getId());
        holder.tvNama.setText(arrayBelanja.get(position).getNama());
        holder.tvKategori.setText(arrayBelanja.get(position).getKategori());
        holder.tvHarga.setText(arrayBelanja.get(position).getHarga());
    }

    @Override
    public int getItemCount() {
        return arrayBelanja.size();
    }

    public class BelanjaViewHolder extends RecyclerView.ViewHolder{

        TextView tvId, tvNama, tvKategori, tvHarga;
        CardView cvBelanja;

        public BelanjaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvid);
            tvNama = itemView.findViewById(R.id.tvnama);
            tvKategori = itemView.findViewById(R.id.tvkategori);
            tvHarga = itemView.findViewById(R.id.tvharga);
            cvBelanja = itemView.findViewById(R.id.cvBelanja);
        }
    }
}
