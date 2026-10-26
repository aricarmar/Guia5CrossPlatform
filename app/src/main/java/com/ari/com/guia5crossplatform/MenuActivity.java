
package com.ari.com.guia5crossplatform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends Activity {

    private Button btnClicks;
    private Button btnClima;
    private Button btnHistorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        btnClicks = findViewById(R.id.btnClicks);
        btnClima = findViewById(R.id.btnClima);
        btnHistorial = findViewById(R.id.btnHistorial);

        btnClicks.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MenuActivity.this,
                    ClicksActivity.class
            );
            startActivity(intent);
        });

        btnClima.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MenuActivity.this,
                    ClimaActivity.class
            );
            startActivity(intent);
        });

        btnHistorial.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MenuActivity.this,
                    HistorialActivity.class
            );
            startActivity(intent);
        });
    }
}
