package mx.edu.ittepic.u4_lab_efren;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNumero;
    TextView txtResultado;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumero = findViewById(R.id.edtNumero);
        txtResultado = findViewById(R.id.txtResultado);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNumero.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "No se ha ingresado ni un numero", Toast.LENGTH_SHORT).show();
                }
                else{
                    metodo Tarea = new metodo();
                    Tarea.execute(Integer.parseInt(edtNumero.getText().toString()));
                }
            }
        });




    }

    private class  metodo extends AsyncTask<Integer, Integer,Boolean> { //hilo principal
        String resultado="0,1,";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(Integer... params) { // es la parte que esta en segundo plano
            int num = 0;
            int n2 = 1;
            int aux;
            int limite = params[0];
            int i=3;
            while ((i) <= limite) {

                aux = num;
                num = n2;
                n2 = aux + num;
                i++;
                resultado += n2+",";
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //
            super.onProgressUpdate(values);


        }

        //meto el resultado
        @Override
        protected void onPostExecute(Boolean aVoid) {// despues de ejecutar, muestro el resultado
            txtResultado.setText(resultado);
        }


    }
}
