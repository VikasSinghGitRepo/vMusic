package com.vikas.elmusico;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    public static final String EXTRA_URL ="com.vikas.elmusico.extra.URL";
    public static final String EXTRA_SONG ="com.vikas.elmusico.extra.SONG";
    public static final String EXTRA_POS = "com.vikas.elmusico.extra.Pos";
    public static final String EXTRA_URI = "com.vikas.elmusico.EXTRA.URI";
    public static final String EXTRA_SONG_OFF = "com.vikas.elmusico.EXTRA.SONGOFF";
    Context c;
    List<Music> m;
    public Adapter(Context c,List<Music> m) {
        this.c =c;
        this.m = m;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylayout,parent,false);
        ViewHolder t= new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m.get(t.getAdapterPosition()).uri != null){
                    Intent intent1 = new Intent(c,offlineplayer.class);
                    intent1.putExtra(EXTRA_URI,String.valueOf(m.get(t.getAdapterPosition()).uri));
                    intent1.putExtra(EXTRA_SONG_OFF,m.get(t.getAdapterPosition()).m_name);
                    intent1.putExtra(EXTRA_POS,t.getAdapterPosition());
                    c.startActivity(intent1);
                }else{
                Intent intent =new Intent(c,Music_Activity.class);
                intent.putExtra(EXTRA_URL,m.get(t.getAdapterPosition()).url);
                intent.putExtra(EXTRA_SONG,m.get(t.getAdapterPosition()).m_name);
                intent.putExtra(EXTRA_POS,t.getAdapterPosition());
                c.startActivity(intent);
                }
            }
        });
        return t;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.getM_name().setText(m.get(position).m_name);
        holder.getA_name().setText(m.get(position).a_name);
    }
    @Override
    public int getItemCount() {
        return m.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView m_name;
        private final TextView a_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            m_name = itemView.findViewById(R.id.m_name);
            a_name = itemView.findViewById(R.id.a_name);
        }

        public TextView getM_name() {
            return m_name;
        }

        public TextView getA_name() {
            return a_name;
        }
    }
}
