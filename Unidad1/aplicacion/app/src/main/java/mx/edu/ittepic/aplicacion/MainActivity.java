package mx.edu.ittepic.aplicacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    String[] nombres = {"Cuautemoc Blanco",
            "Piojo Lopez",
            "Salvador Cabañas",
            "Memo Ochoa",
            "Kleber boas",
            "Alfredo Tena",
            "Carlos Reynoso",
            "Rubens Sambueza",
            "Chucho",
            "Dulio Davino"};

    String[] nacionalidad = {
            "Mexicano",
            "Argentino",
            "Paraguayo",
            "Mexicano",
            "Brasileño",
            "Mexicano",
            "chileno",
            "Argentino",
            "Ecuatoriano",
            "Mexicano"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear referencias hacia el componente RecycleriView
        recyclerView = findViewById(R.id.recicleFutbol);


        // Crear un objeto de tipo RecyclerAdapter que recibe un arreglo de cadenas
        //ademas de recibir un context para que ejecute la clase
        adapter = new adapter(nombres, nacionalidad, this);
        //adapter2 = new RecyclerAdapter(numeroControl);

        // Crea un objeto de tipo LinearLayoutManager
        layoutManager = new LinearLayoutManager(this);

        // Establecer el LayautManager
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // Establecer el Adapter
        recyclerView.setAdapter(adapter);
        //    recyclerView.setAdapter(adapter2);

    }

}

