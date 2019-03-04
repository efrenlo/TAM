package mx.edu.ittepic.lab_prueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.edu.ittepic.lab_prueba.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public SQLiteDatabase myDB;

    public ConexionSQLiteHelper(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Utilidades.DBNAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)  {
        sqLiteDatabase.execSQL(Utilidades.CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuarios");
        //onCreate(sqLiteDatabase);
    }

    public void openDB(){
        myDB=getWritableDatabase();
    }

    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }
    }

    public Cursor getAll(){

        String query="SELECT * FROM "+ Utilidades.TABLA;

        return myDB.rawQuery(query,null);

    }

    public long insert(String clave, String nombre, String sueldo,String email,String fecha){

        ContentValues cv = new ContentValues();
        cv.put(Utilidades.CLAVE,clave);
        cv.put(Utilidades.NOMBRE,nombre);
        cv.put(Utilidades.SUELDO,sueldo);
        cv.put(Utilidades.EMAIL,email);
        cv.put(Utilidades.FECHA,fecha);

        return myDB.insert(Utilidades.TABLA,null,cv);
    }

}
