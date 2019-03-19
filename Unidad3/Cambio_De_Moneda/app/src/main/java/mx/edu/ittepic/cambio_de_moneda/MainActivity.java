package mx.edu.ittepic.cambio_de_moneda;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // Crear variable con el tipo de moneda por Pais
    Currency[] currency = {
            new Currency("Euro", "EUR"),
            new Currency("Hong Kong Dollar", "HKD"),
            new Currency("Honduran Lempira", "HNL"),
            new Currency("United States Dollar", "USD"),
            new Currency("Venezuelan Bolívar Fuerte", "VEF"),
            new Currency("Mexican Peso", "MXN"),
            new Currency("Canadian Dollar", "CAD")
    };

    // Declaración de variables
    private String jsonResult;
    String divisa, divisa2;
    Spinner spinner1, spinner2;
    Button btnConvert;
    EditText edtCantidad;
    TextView txtResultado;
    private static String urls = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Obtener la referencia del objeto spinner
        spinner1 = (Spinner) findViewById(R.id.spinner);
// Construir los datos en el spinner
        MySpinnerAdapter adapter = new MySpinnerAdapter(MainActivity.this, android.R.layout.simple_spinner_item, currency);
        spinner1.setAdapter(adapter);
// Evento selección del spinner
        spinner1.setOnItemSelectedListener(onItemSelectedListener1);
// Obtener la referencia del objeto spinner
        spinner2 = (Spinner) findViewById(R.id.spinner2);
// Construir los datos en el spinner
        MySpinnerAdapter adapter2 = new MySpinnerAdapter(MainActivity.this, android.R.layout.simple_spinner_item, currency);
        spinner2.setAdapter(adapter2);
// Evento selección del spinner
        spinner2.setOnItemSelectedListener(onItemSelectedListener2);
// Obtener la refencia del Button
        btnConvert = (Button) findViewById(R.id.button);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Obtener referencia del objeto editText
                edtCantidad = (EditText) findViewById(R.id.editText2);
// Asignar el valor a la variable string
                String cantidad = edtCantidad.getText().toString();
                // urls = "https://api.fixer.io/latest?base=" + divisa + "&symbols=" + divisa2 + "&amount=" + cantidad + "";
                urls ="http://api.valuta.money/v1/quotes/"+divisa+"/"+divisa2+"/json?quantity="+cantidad+"&key=1855|wWMDmS0cK91^yzafafcdHoweK7JHqCtb";
                // urls="http:data.fixer.io/api/latest?access_key=43f744926f1e6fdeda54af8f92e6852f&base=" + divisa + "&symbols=" + divisa2 + "&amount=" + cantidad  +"";
                //Toast.makeText(getApplicationContext(), urls, Toast.LENGTH_LONG).show();
// Ejecutar la clase asincrona
                webService task = new webService();
                task.execute();
            }
        });
    }

    // Clase asincrona del servicio
    private class webService extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String forecastJsonStr = null;
            try {
// Asignar la URL de la conexión
                URL url = new URL(urls);
// Establece el tipo de conexiòn
                urlConnection = (HttpURLConnection) url.openConnection();
// El tipo de conexiòn Get
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }

                forecastJsonStr = buffer.toString();
                jsonResult = buffer.toString();
                return forecastJsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            getResultado();
        }
    }

    // Obtener el resultado
    public void getResultado() {
        try {
            divisa2="amount";
            String resDivisa;
// Obtener el valor del arreglo de la devoluciòn
            JSONObject reader = new JSONObject(jsonResult);
// Especificar el valor que se necesita
            JSONObject result = reader.getJSONObject("result");
// Obtener el valor pero en nodo
            resDivisa = result.getString(divisa2);
// Asignar el valor al TextView
            txtResultado = (TextView) findViewById(R.id.textView6);
            txtResultado.setText(resDivisa);
        } catch (Exception e) {
        }
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener1 =
            new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    Currency obj = (Currency) (parent.getItemAtPosition(position));
                    divisa = String.valueOf(obj.getValue());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }

            };
    AdapterView.OnItemSelectedListener onItemSelectedListener2 =
            new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    Currency obj = (Currency) (parent.getItemAtPosition(position));
                    divisa2 = String.valueOf(obj.getValue());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }

            };

    // Definicion de la clase Currency
    public class Currency {

        private String text;
        private String value;

        public Currency(String text, String value) {
            this.text = text;
            this.value = value;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    //custom adapter
    public class MySpinnerAdapter extends ArrayAdapter<Currency> {

        private Context context;
        private Currency[] myObjs;

        public MySpinnerAdapter(Context context, int textViewResourceId,
                                Currency[] myObjs) {
            super(context, textViewResourceId, myObjs);
            this.context = context;
            this.myObjs = myObjs;
        }

        public int getCount() {
            return myObjs.length;
        }

        public Currency getItem(int position) {
            return myObjs[position];
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView label = new TextView(context);
            label.setText(myObjs[position].getText());
            return label;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            TextView label = new TextView(context);
            label.setText(myObjs[position].getText());
            return label;
        }
    }
}