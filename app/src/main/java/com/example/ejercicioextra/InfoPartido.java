package com.example.ejercicioextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ejercicioextra.Adapters.AdapterPartido;
import com.example.ejercicioextra.Modelos.PartidoModel;

public class InfoPartido extends AppCompatActivity {

    private TextView lbInfo;
    private Button btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_partido);
        lbInfo = findViewById(R.id.lbResumen);
        btnAtras = findViewById(R.id.btnAtras);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        PartidoModel p = (PartidoModel) bundle.getSerializable("INFO");

        lbInfo.setText(p.toString());

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}