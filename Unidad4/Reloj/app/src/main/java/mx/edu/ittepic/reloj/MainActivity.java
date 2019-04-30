package mx.edu.ittepic.reloj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    String mensaje;
    MediaPlayer inicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Relog(this));
        //inicio = MediaPlayer.create(this, R.raw.reloj);
        //inicio.start();

    }



    public class Relog extends View {
        private final long TIEMPO_REFRESCO = 20; // 20 o 1000 velocidad de refresco
        private Paint ColorFondoPintura;
        private Paint ManesillaPintura;
        private Paint Letrass;
        private Paint fondo1;
        private Paint bola;

        public Relog(Context context) {
            super(context);
            init(context, null);
            mensaje = "";


        }

        public Relog(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context, attrs);

        }

        public Relog(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context, attrs);
        }

        private void init(Context context, AttributeSet attrs) {
            ColorFondoPintura = new Paint();
            ColorFondoPintura.setColor(Color.YELLOW);
            ColorFondoPintura.setAntiAlias(true);
            ManesillaPintura = new Paint();
            ManesillaPintura.setColor(Color.rgb(25, 25, 112));
            ManesillaPintura.setAntiAlias(true);
            Letrass = new Paint();
            Letrass.setColor(Color.RED);
            Letrass.setTextSize(60);
            fondo1=new Paint();
            bola=new Paint();
            bola.setColor(Color.MAGENTA);


        }


        private Runnable invalidator = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            Paint p = new Paint();
            canvas.drawText(mensaje, 100, 100, p);

            // canvas.drawRGB(0,0,200);
            dibujarFondo(canvas);
            fondo2(canvas);

            dibjarManecillaHora(canvas);
            dibjarManecillaMinutos(canvas);
            dibjarManecillaSegundos(canvas);
            dibujarUna(canvas);
            letra(canvas);
            letra2(canvas);
            letra3(canvas);
            letra4(canvas);
            letra5(canvas);
            letra6(canvas);
            letra7(canvas);
            letra8(canvas);
            letra9(canvas);
            letra10(canvas);
            letra11(canvas);
            letra12(canvas);

            postDelayed(invalidator, TIEMPO_REFRESCO);
        }

        private void letra(Canvas canvas) {
            canvas.drawText("12", 500, 200, Letrass);
        }

        private void letra2(Canvas canvas) {
            canvas.drawText("1", 780, 310, Letrass);
        }

        private void letra3(Canvas canvas) {
            canvas.drawText("2", 900, 485, Letrass);
        }

        private void letra4(Canvas canvas) {
            canvas.drawText("3", 920, 650, Letrass);
        }

        private void letra5(Canvas canvas) {
            canvas.drawText("4", 850, 830, Letrass);
        }

        private void letra6(Canvas canvas) {
            canvas.drawText("5", 720, 950, Letrass);
        }

        private void letra7(Canvas canvas) {
            canvas.drawText("6", 540, 1000, Letrass);
        }

        private void letra8(Canvas canvas) {
            canvas.drawText("7", 360, 950, Letrass);
        }

        private void letra9(Canvas canvas) {
            canvas.drawText("8", 220, 810, Letrass);
        }

        private void letra10(Canvas canvas) {
            canvas.drawText("9", 140, 620, Letrass);
        }

        private void letra11(Canvas canvas) {
            canvas.drawText("10", 150, 460, Letrass);
        }

        private void letra12(Canvas canvas) {
            canvas.drawText("11", 230, 310, Letrass);
        }
        private void fondo2(Canvas canvas) {
            Bitmap fondoportada= BitmapFactory.decodeResource(getResources(),R.drawable.ame);
            canvas.drawBitmap(fondoportada,110,135,null);
        }


        private void dibjarManecillaHora(Canvas canvas) {
            float vistaRadio = getWidth() / 2f;
            float manoRadio = getWidth() * 0.2f;
            float espesor = getWidth() * 0.01f;
            ManesillaPintura.setStrokeWidth(espesor);
            double angulo = getAnguloHora();
            float x = getX(vistaRadio, manoRadio, angulo);
            float y = getY(vistaRadio, manoRadio, angulo);
            canvas.drawLine(vistaRadio, vistaRadio, x, y, ManesillaPintura);
        }

        private void dibujarFondo(Canvas canvas) {
            float circuloFondo = getHeight() / 4f;
            canvas.drawCircle(545, 570, 440, ColorFondoPintura);
        }

        private void dibjarManecillaMinutos(Canvas canvas) {
            float vistaRadio = getWidth() / 2f;
            float manoRadio = getWidth() * 0.3f;
            float espesor = getWidth() * 0.01f;
            ManesillaPintura.setStrokeWidth(espesor);
            double angulo = getAnguloMinutos();
            float x = getX(vistaRadio, manoRadio, angulo);
            float y = getY(vistaRadio, manoRadio, angulo);
            canvas.drawLine(vistaRadio, vistaRadio, x, y, ManesillaPintura);
        }

        private void dibjarManecillaSegundos(Canvas canvas) {
            float vistaRadio = getWidth() / 2f;
            float manoRadio = getWidth() * 0.4f;
            float espesor = getWidth() * 0.005f;
            ManesillaPintura.setStrokeWidth(espesor);
            double angulo = getAnguloSegundos();
            float x = getX(vistaRadio, manoRadio, angulo);
            float y = getY(vistaRadio, manoRadio, angulo);
            canvas.drawLine(vistaRadio, vistaRadio, x, y, ManesillaPintura);
        }

        private float getX(float vistaRadio, float manoRadio, double angulo) {
            return (float) (vistaRadio + manoRadio * Math.sin(angulo));
        }

        private float getY(float vistaRadio, float manoRadio, double angulo) {
            return (float) (vistaRadio - manoRadio * Math.cos(angulo));
        }

        private void dibujarUna(Canvas canvas) {

            canvas.drawCircle(550, 550, 40, bola);
        }

        private double getAnguloHora() {
            Calendar c = Calendar.getInstance();
            int horas = c.get(Calendar.HOUR);
            int minutos = c.get(Calendar.MINUTE);
            int minutosInicio = horas * 60 + minutos;
            return (2 * Math.PI * minutosInicio) / 720; //Minutos en 12 horas
        }

        private double getAnguloMinutos() {
            Calendar c = Calendar.getInstance();
            int segundosDesdeElInicio = c.get(Calendar.MINUTE) * 60 + c.get(Calendar.SECOND);
            return (2 * Math.PI * segundosDesdeElInicio) / 3600; // Divide los segundos en una hora
        }

        private double getAnguloSegundos() {
            Calendar c = Calendar.getInstance();
            int millisFromStart = c.get(Calendar.SECOND) * 1000 + c.get(Calendar.MILLISECOND);
            return (2 * Math.PI * millisFromStart) / 60000; // Milisegundos en 1 mnuto
        }
    }


}

