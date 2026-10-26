
package com.ari.com.guia5crossplatform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClimaActivity extends Activity {

    private EditText etLugar, etTemperatura;
    private EditText etHumedad, etObservaciones;
    private BaseDatosHelper baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima);

        etLugar = findViewById(R.id.etLugar);
        etTemperatura = findViewById(R.id.etTemperatura);
        etHumedad = findViewById(R.id.etHumedad);
        etObservaciones = findViewById(R.id.etObservaciones);

        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnLimpiar = findViewById(R.id.btnLimpiar);
        Button btnVer = findViewById(R.id.btnVerRegistros);
        Button btnVolver = findViewById(R.id.btnVolverClima);

        baseDatos = new BaseDatosHelper(this);

        btnGuardar.setOnClickListener(v -> guardarRegistro());
        btnLimpiar.setOnClickListener(v -> limpiarCampos());

        btnVer.setOnClickListener(v ->
                startActivity(new Intent(this, HistorialActivity.class)));

        btnVolver.setOnClickListener(v -> finish());
    }

    private void guardarRegistro() {
        String lugar = etLugar.getText().toString().trim();
        String tempTexto = etTemperatura.getText().toString().trim();
        String humTexto = etHumedad.getText().toString().trim();
        String observaciones =
                etObservaciones.getText().toString().trim();

        if (lugar.isEmpty() || tempTexto.isEmpty()
                || humTexto.isEmpty()) {
            Toast.makeText(this,
                    "Completa lugar, temperatura y humedad",
                    Toast.LENGTH_LONG).show();
            return;
        }

        try {
            double temperatura = Double.parseDouble(
                    tempTexto.replace(',', '.'));
            double humedad = Double.parseDouble(
                    humTexto.replace(',', '.'));

            if (!Double.isFinite(temperatura)
                    || !Double.isFinite(humedad)
                    || humedad < 0 || humedad > 100) {
                Toast.makeText(this,
                        "Verifica temperatura y humedad (0 a 100%)",
                        Toast.LENGTH_LONG).show();
                return;
            }

            long resultado = baseDatos.insertarRegistro(
                    lugar, temperatura, humedad, observaciones);

            if (resultado != -1) {
                Toast.makeText(this,
                        "Registro guardado correctamente",
                        Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this,
                        "No se pudo guardar el registro",
                        Toast.LENGTH_LONG).show();
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Ingresa valores numéricos válidos",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this,
                    "Error al guardar: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void limpiarCampos() {
        etLugar.setText("");
        etTemperatura.setText("");
        etHumedad.setText("");
        etObservaciones.setText("");
        etLugar.requestFocus();
    }

    @Override
    protected void onDestroy() {
        baseDatos.close();
        super.onDestroy();
    }
}
