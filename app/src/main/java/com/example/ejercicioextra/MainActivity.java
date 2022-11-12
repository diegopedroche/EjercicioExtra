package com.example.ejercicioextra;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.ejercicioextra.Adapters.AdapterPartido;
import com.example.ejercicioextra.Modelos.PartidoModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ejercicioextra.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AdapterPartido adapter;
    private ArrayList<PartidoModel> partidosList;
    private RecyclerView.LayoutManager layoutManager;
    private ActivityResultLauncher<Intent> launcherCrearPartido;
    public static ActivityResultLauncher<Intent> launcherViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        partidosList = new ArrayList<>();
        inicializaLaunchers();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearPartido.launch(new Intent(MainActivity.this,AddPartido.class));
            }
        });

        // EMPEZAMOS A PARTIR DE AQUÍ POR SI ACASO.
        adapter = new AdapterPartido(MainActivity.this, R.layout.partido_model_view, partidosList);
        binding.contentMain.contenedor.setAdapter(adapter);

        int columnas;

        columnas = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 1 : 2; // OPERADOR TERNARIO
        layoutManager = new GridLayoutManager(MainActivity.this, columnas);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);
    }

    private void inicializaLaunchers() {
        launcherCrearPartido = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK){
                    if (result.getData() != null && result.getData().getExtras() != null){
                        PartidoModel partido = (PartidoModel) result.getData().getExtras().getSerializable("GAME");
                        partidosList.add(partido);
                        adapter.notifyItemInserted(partidosList.size()-1);
                    }
                }{
                    Toast.makeText(MainActivity.this, "Actividad cancelada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        launcherViewInfo = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

            }
        });
    }
}