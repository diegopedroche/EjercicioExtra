package com.example.ejercicioextra.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicioextra.InfoPartido;
import com.example.ejercicioextra.MainActivity;
import com.example.ejercicioextra.Modelos.PartidoModel;
import com.example.ejercicioextra.R;

import java.util.ArrayList;

public class AdapterPartido extends RecyclerView.Adapter<AdapterPartido.PartidoVH> {

    // Las tres cosas esenciales que tiene un adapter
    private Context context;
    private int resources;
    private ArrayList<PartidoModel> objects;

    public AdapterPartido(Context context, int resources, ArrayList<PartidoModel> objects) {
        this.context = context;
        this.resources = resources;
        this.objects = objects;
    }

    @NonNull
    @Override
    public PartidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resources, null);
        ViewGroup.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PartidoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidoVH holder, int position) {
        PartidoModel p = objects.get(position);

        holder.lbNombreEquipoUno.setText(p.getEquipo1());
        holder.lbNombreEquipoDos.setText(p.getEquipo2());
        holder.lbGolesEquipoUno.setText(String.valueOf(p.getGoles1()));
        holder.lbGolesEquipoDos.setText(String.valueOf(p.getGoles2()));
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objects.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarResultados(p).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InfoPartido.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("INFO",p);
                intent.putExtras(bundle);
                MainActivity.launcherViewInfo.launch(intent);
            }
        });
    }

    private AlertDialog mostrarResultados(PartidoModel p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Resultado");
        builder.setCancelable(true);
        if (p.getGoles1() > p.getGoles2()){
            builder.setMessage(R.string.Ganado + " " + p.getEquipo1());
        }else if (p.getGoles1() == p.getGoles2()) {
            builder.setMessage(R.string.Empate);
        }else {
            builder.setMessage(R.string.Ganado + " " + p.getEquipo2());
        }
        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PartidoVH extends RecyclerView.ViewHolder {

        private TextView lbNombreEquipoUno;
        private TextView lbNombreEquipoDos;
        private TextView lbGolesEquipoUno;
        private TextView lbGolesEquipoDos;
        private ImageButton btnEliminar;
        private ImageButton btnVer;
        public PartidoVH(@NonNull View itemView) {
            super(itemView);
            lbNombreEquipoUno = itemView.findViewById(R.id.lbNombreEquipoUno);
            lbNombreEquipoDos = itemView.findViewById(R.id.lbNombreEquipoDos);
            lbGolesEquipoUno = itemView.findViewById(R.id.lbGolesEquipoUno);
            lbGolesEquipoDos = itemView.findViewById(R.id.lbGolesEquipoDos);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
            btnVer = itemView.findViewById(R.id.btnVerResultado);
        }
    }
}
