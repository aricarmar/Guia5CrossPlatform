
package com.ari.com.guia5crossplatform;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HistorialActivity extends Activity {

    private BaseDatosHelper baseDatos;
    private LinearLayout contenedor;
    private TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        baseDatos = new BaseDatosHelper(this);
        contenedor = findViewById(R.id.contenedorRegistros);
        txtTotal = findViewById(R.id.txtTotal);

        Button btnBorrar = findViewById(R.id.btnBorrarTodos);
        Button btnVolver = findViewById(R.id.btnVolverHistorial);

        btnBorrar.setOnClickListener(v -> confirmarBorradoTotal());
        btnVolver.setOnClickListener(v -> finish());

        mostrarRegistros();
    }

    private int dp(int valor) {
        return (int) (valor * getResources()
                .getDisplayMetrics().density + 0.5f);
    }

    private void mostrarRegistros() {
        contenedor.removeAllViews();
        int total = 0;

        try (Cursor cursor = baseDatos.obtenerRegistros()) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String lugar = cursor.getString(1);
                double temperatura = cursor.getDouble(2);
                double humedad = cursor.getDouble(3);
                String observaciones = cursor.getString(4);
                String fecha = cursor.getString(5);

                LinearLayout tarjeta = new LinearLayout(this);
                tarjeta.setOrientation(LinearLayout.VERTICAL);
                tarjeta.setPadding(dp(16), dp(16), dp(16), dp(16));
                tarjeta.setBackgroundColor(Color.rgb(38, 30, 65));

                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                params.bottomMargin = dp(14);
                tarjeta.setLayoutParams(params);

                TextView detalle = new TextView(this);
                detalle.setText(
                        "Registro #" + id +
                                "\nLugar: " + lugar +
                                "\nTemperatura: " + temperatura + " °C" +
                                "\nHumedad: " + humedad + " %" +
                                "\nObservaciones: " +
                                (observaciones == null ? "" : observaciones) +
                                "\nFecha: " + fecha);
                detalle.setTextColor(Color.WHITE);
                detalle.setTextSize(16);
                tarjeta.addView(detalle);

                Button eliminar = new Button(this);
                eliminar.setText("Eliminar este registro");
                eliminar.setTextColor(Color.WHITE);
                eliminar.setBackgroundTintList(
                        android.content.res.ColorStateList.valueOf(
                                Color.rgb(155, 45, 70)));
                eliminar.setOnClickListener(v ->
                        confirmarBorradoIndividual(id));

                tarjeta.addView(eliminar);
                contenedor.addView(tarjeta);
                total++;
            }
        } catch (Exception e) {
            Toast.makeText(this,
                    "Error al consultar registros",
                    Toast.LENGTH_LONG).show();
        }

        txtTotal.setText("Registros guardados: " + total);

        if (total == 0) {
            TextView vacio = new TextView(this);
            vacio.setText("Todavía no hay registros ambientales.");
            vacio.setTextColor(Color.WHITE);
            vacio.setTextSize(17);
            contenedor.addView(vacio);
        }
    }

    private void confirmarBorradoIndividual(int id) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar registro")
                .setMessage("¿Deseas eliminar el registro #" + id + "?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    baseDatos.eliminarRegistro(id);
                    mostrarRegistros();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void confirmarBorradoTotal() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar todos los registros")
                .setMessage("Esta acción no se puede deshacer. " +
                        "¿Deseas continuar?")
                .setPositiveButton("Sí, eliminar", (dialog, which) -> {
                    baseDatos.eliminarTodos();
                    mostrarRegistros();
                    Toast.makeText(this,
                            "Todos los registros fueron eliminados",
                            Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseDatos != null) {
            mostrarRegistros();
        }
    }

    @Override
    protected void onDestroy() {
        baseDatos.close();
        super.onDestroy();
    }
}
