
package com.ari.com.guia5crossplatform;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ClicksActivity extends Activity {

    private int contador = 0;
    private TextView txtContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicks);

        txtContador = findViewById(R.id.txtContador);
        Button btnSumar = findViewById(R.id.btnSumar);
        Button btnReiniciar = findViewById(R.id.btnReiniciar);
        Button btnVolver = findViewById(R.id.btnVolverClicks);

        if (savedInstanceState != null) {
            contador = savedInstanceState.getInt("contador", 0);
        } else {
            contador = getPreferences(MODE_PRIVATE)
                    .getInt("contador", 0);
        }

        actualizarContador();

        btnSumar.setOnClickListener(v -> {
            contador++;
            guardarContador();
            actualizarContador();
        });

        btnReiniciar.setOnClickListener(v -> {
            contador = 0;
            guardarContador();
            actualizarContador();
        });

        btnVolver.setOnClickListener(v -> finish());
    }

    private void actualizarContador() {
        txtContador.setText(String.valueOf(contador));
    }

    private void guardarContador() {
        getPreferences(MODE_PRIVATE)
                .edit()
                .putInt("contador", contador)
                .apply();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("contador", contador);
        super.onSaveInstanceState(outState);
    }
}
