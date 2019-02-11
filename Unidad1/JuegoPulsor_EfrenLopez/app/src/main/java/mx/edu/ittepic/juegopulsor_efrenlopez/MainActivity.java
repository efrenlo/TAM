package mx.edu.ittepic.juegopulsor_efrenlopez;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button boton,boton2;
    TextView txt;
    float numero,nfinal,nCambia,comparar;
    String num1,num2,num3;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.btn1);
        txt = findViewById(R.id.txtnumero);
        boton2 = findViewById(R.id.btn2);
        nCambia=0;

        // permite simplicar los decimales arrojados
        final DecimalFormat df = new DecimalFormat("#.0");
        //generacion  de numero aleatorio, y le multiplica 3
        numero = (float) (Math.random()*3);
        //numero aleatorio que se genera y lo metes en un string
        num1 = df.format(numero);

        txt.setText(""+num1);
        nfinal=Float.parseFloat(num1);

        //tiempo transcurrido en timer
        timer = new CountDownTimer(10000,400) {
            @Override
            public void onTick(long millisUntilFinished) {
                nCambia= (float) (nCambia+0.1);
                //numero que se va generando en el timer, en el boton, y le aliclamos la clase fromat
                num2 = df.format(nCambia);
                //numero que aparece en el botonn
                boton.setText(""+num2);

                //cambiando el numerom cuando llegue a 3
                if (nCambia>=2.9){
                    nCambia= (float) 0.0;
                }

            }

            @Override
            public void onFinish() {
                timer.start();

            }
        };timer.start();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //comparando numeros, tipo metodo de la burbuja
                num3=num2;
                //convertiendo la caden a flotante
                comparar= Float.parseFloat(num3);
                if (nfinal==comparar){
                    Toast.makeText(MainActivity.this, "Juegas bien", Toast.LENGTH_SHORT).show();
                    timer.cancel();
                    boton2.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(MainActivity.this, "Lo siento, fallaste", Toast.LENGTH_SHORT).show();
                }


            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = (float) (Math.random()*3);
                num1 = df.format(numero);
                txt.setText(""+num1);
                nfinal=Float.parseFloat(num1);
                timer.start();

            }
        });
    }
}