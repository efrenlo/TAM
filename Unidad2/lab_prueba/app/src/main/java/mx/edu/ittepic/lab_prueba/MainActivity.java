package mx.edu.ittepic.lab_prueba;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import mx.edu.ittepic.lab_prueba.utilidades.Utilidades;
import mx.edu.ittepic.lab_prueba.entidades.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnnew;
    private ImageButton btndel;
    private ImageButton btnupdate;
    private ImageButton btnlist;

    private EditText nombre;
    private EditText rfc;
    private EditText celular;
    private EditText email;
    private EditText fecha;
    private EditText resultado;
    Button consultar;
    mx.edu.ittepic.lab_prueba.ConexionSQLiteHelper base;

    SQLiteDatabase db;


    ArrayList<Usuario> listaUsuarios;
    RecyclerView listaObjetos;
    private RecyclerView.LayoutManager mLayoutManager;
    mx.edu.ittepic.lab_prueba.Adaptador adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        base = new mx.edu.ittepic.lab_prueba.ConexionSQLiteHelper(this, "usuariosLAB", null, 2);

        consultar = findViewById(R.id.button);
        init();
        //listardatos();

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"LIST ALL",Toast.LENGTH_SHORT).show();
                listardatos();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        base.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        base.closeDB();
    }

    private void init() {
        btnnew = findViewById(R.id.imageButton);
        btndel = findViewById(R.id.imageButton2);
        btnupdate = findViewById(R.id.imageButton3);
        btnlist = findViewById(R.id.imageButton4);

        nombre=findViewById(R.id.edt1);
        rfc=findViewById(R.id.edt2);
        celular=findViewById(R.id.edt3);
        email=findViewById(R.id.edit4);
        fecha=findViewById(R.id.edit5);

        resultado=findViewById(R.id.editText4);

        btnnew.setOnClickListener(btnListener);
        btndel.setOnClickListener(btnListener);
        btnupdate.setOnClickListener(btnListener);
        btnlist.setOnClickListener(btnListener);

        listaObjetos= findViewById(R.id.carview);
        listaObjetos.setLayoutManager(new LinearLayoutManager(this));
        listaUsuarios=new ArrayList<>();
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.imageButton:
                    Long registros;
                    registros = registrarUsuario();

                    if (registros==-1){
                        Toast.makeText(MainActivity.this,"NOT ADD ROW ",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(MainActivity.this,"ADD ROW "+ registros,Toast.LENGTH_SHORT).show();
                    }
                    //buscar por id
                    break;
                case R.id.imageButton2:
                    Toast.makeText(MainActivity.this,"Buscar usuario",Toast.LENGTH_SHORT).show();
                    pedirID(1);
                    break;
                //Actualizar
                case R.id.imageButton3:
                    Toast.makeText(MainActivity.this,"UPDATE ROW",Toast.LENGTH_SHORT).show();
                    pedirID(2);
                    break;

                case R.id.imageButton4:


                    break;
            }
        }
    };

    private void pedirID(final int origen){
        final EditText pidoID = new EditText(this);
        pidoID.setInputType(InputType.TYPE_CLASS_NUMBER);
        pidoID.setHint("Valor entero mayor de 0");
        String mensaje ="Escriba el id a buscar";

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        if(origen ==2){
            mensaje ="Ecriba el id a modificar";
        }


        alerta.setTitle("atencion").setMessage(mensaje)
                .setView(pidoID)
                .setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(pidoID.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,"DEbes escribir un numero",Toast.LENGTH_LONG).show();
                            return;
                        }
                        buscarDato(pidoID.getText().toString(), origen);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancelar",null).show();
    }

    private void listardatos() {
        try {

            listaUsuarios=new ArrayList<>();
            StringBuffer datos = new StringBuffer();
            Cursor cursor = base.getAll();

            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                datos.append(cursor.getString(cursor.getColumnIndex(Utilidades.CLAVE)));
                datos.append(" - ");
                datos.append(cursor.getString(cursor.getColumnIndex(Utilidades.NOMBRE)));
                datos.append(" - ");
                datos.append(cursor.getDouble(cursor.getColumnIndex(Utilidades.SUELDO)));
                datos.append("\n");
                listaUsuarios.add(new Usuario(cursor.getString(1), cursor.getString(2), cursor.getDouble(3)));
            }
            adapter = new mx.edu.ittepic.lab_prueba.Adaptador(listaUsuarios, getApplicationContext());
            listaObjetos.setAdapter(adapter);
            resultado.setText(datos);
        }catch (SQLException e) {

        }

    }

    private void buscarDato(String idReceta, int numero) {

        try{

            SQLiteDatabase tabla = base.getReadableDatabase();

            String SQL = "SELECT *FROM USUARIO WHERE _id="+idReceta;

            Cursor resultado = tabla.rawQuery(SQL,null);
            if(resultado.moveToFirst()){//mover le primer resultado obtenido de la consulta
                //si hay resulta´do

                if(numero==1){
                    nombre.setText(resultado.getString(1));
                    rfc.setText(resultado.getString(2));
                    celular.setText(resultado.getString(3));
                    email.setText(resultado.getString(4));
                    email.setText(resultado.getString(5));
                }



                if(numero==2){
                    //modificar
                    aplicarActualizar(idReceta);

                }
            }else {
                //no hay resultado!
                Toast.makeText(this,"No se ENCONTRO EL RESULTADO",Toast.LENGTH_LONG).show();
            }
            tabla.close();

        }catch (SQLiteException e){
            Toast.makeText(this,"No se pudo buscar",Toast.LENGTH_LONG).show();
        }

    }

    public Long registrarUsuario() {

        return base.insert(nombre.getText().toString(), rfc.getText().toString(), celular.getText().toString(),email.getText().toString(),fecha.getText().toString());

    }

    private void aplicarActualizar(String identificacion) {
        try{
            SQLiteDatabase tabla = base.getWritableDatabase();

            String SQL= "UPDATE USUARIO SET clave='"+nombre.getText().toString()+"', nombre='"
                    +rfc.getText().toString()+"', sueldo='"+celular.getText().toString()+"', email='"+email.getText().toString()+"', fecha='"+fecha.getText().toString()+
                    "' WHERE _id="+identificacion;
            tabla.execSQL(SQL);
            tabla.close();
            nombre.setText("");
            rfc.setText("");
            celular.setText("");
            email.setText("");
            fecha.setText("");
            Toast.makeText(this,"SE actualizo",Toast.LENGTH_LONG).show();

        }catch (SQLiteException e){
            Toast.makeText(this,"No se pudo actualizar",Toast.LENGTH_LONG).show();
        }

    }

}
