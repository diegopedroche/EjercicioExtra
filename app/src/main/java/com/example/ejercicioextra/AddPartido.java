package com.example.ejercicioextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ejercicioextra.Modelos.PartidoModel;

public class AddPartido extends AppCompatActivity {

    private Spinner equipoUno;
    private Spinner equipoDos;
    private EditText golesUno;
    private EditText golesDos;
    private EditText resumenPartido;
    private Button btnCrear;
    private Button btnCancelar;
    PartidoModel partido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_partido);

        inicializaVistas();
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!equipoUno.getSelectedItem().toString().equalsIgnoreCase(equipoDos.getSelectedItem().toString())){
                    if (!golesUno.getText().toString().isEmpty() && !golesDos.getText().toString().isEmpty()){
                        partido = new PartidoModel(equipoUno.getSelectedItem().toString(),equipoDos.getSelectedItem().toString(),Integer.parseInt(golesUno.getText().toString()),Integer.parseInt(golesDos.getText().toString()),resumenPartido.getText().toString());
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("GAME", partido);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    }else{
                        Toast.makeText(AddPartido.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(AddPartido.this, "No pueden ser los mismos equipos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializaVistas() {
        equipoUno = findViewById(R.id.spnEquipoUnoAdd);
        equipoDos = findViewById(R.id.spnEquipoDosAdd);
        golesUno = findViewById(R.id.txtGolesEquipoUnoAdd);
        golesDos = findViewById(R.id.txtGolesEquipoDosAdd);
        resumenPartido = findViewById(R.id.txtResumenAdd);
        btnCancelar = findViewById(R.id.btnCancelarAdd);
        btnCrear = findViewById(R.id.btnCrearAdd);
    }
}