
package com.ari.com.guia5crossplatform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatosHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "registro_ambiental.db";
    private static final int VERSION_BD = 1;
    public static final String TABLA = "registros";

    public BaseDatosHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "lugar TEXT NOT NULL, " +
                "temperatura REAL NOT NULL, " +
                "humedad REAL NOT NULL, " +
                "observaciones TEXT, " +
                "fecha TEXT DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // Las migraciones futuras se implementarán aquí.
    }

    public long insertarRegistro(
            String lugar,
            double temperatura,
            double humedad,
            String observaciones) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("lugar", lugar);
        valores.put("temperatura", temperatura);
        valores.put("humedad", humedad);
        valores.put("observaciones", observaciones);

        return db.insert(TABLA, null, valores);
    }

    public Cursor obtenerRegistros() {
        return getReadableDatabase().rawQuery(
                "SELECT id, lugar, temperatura, humedad, " +
                        "observaciones, fecha FROM " + TABLA +
                        " ORDER BY id DESC", null);
    }

    public int eliminarRegistro(int id) {
        return getWritableDatabase().delete(
                TABLA,
                "id = ?",
                new String[]{String.valueOf(id)}
        );
    }

    public int eliminarTodos() {
        return getWritableDatabase().delete(
                TABLA, null, null
        );
    }
}
