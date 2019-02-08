package mx.edu.ittepic.aplicacion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class adapter extends RecyclerView.Adapter<adapter.RecyclerViewHolder> {

    //arreglos para almacenar los datos
    private String[] nombres;
    private String[] nacionalidad;
    private Context contexto;



    public adapter(String[] nombres, String[] nacionalidad, Context contexto) {
        this.nombres = nombres;
        this.nacionalidad = nacionalidad;
        this.contexto = contexto;
    }

    //el encargado de inflar  el cotenido de un nuevo item para la lista, es el proceso qeus e realiza
    // para usar un
    //layout dentro de otro layout , los 2 layout forman una entidad
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jugador ,parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    //Realiza las moficaciones del contenido para cada item
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        holder.nombreJugador.setText(nombres[position]);
        holder.nacionalidad.setText(nacionalidad[position]);

        holder.actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(contexto, "Actualizaste a: " + nombres[position], Toast.LENGTH_LONG).show();
            }
        });
        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(contexto, "Eliminaste a: " + nombres[position], Toast.LENGTH_LONG).show();
            }
        });
    }

    //permite determinar al adaptador la cantidad de elemtnos que se procesaran
    @Override
    public int getItemCount() {
        return nombres.length;
    }

    //los adaptadores tienen que tener una clase interna que extiende de la clase Recyciletr, para manejar los datos
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView nombreJugador;
        TextView nacionalidad;
        ImageView actualizar, borrar;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            nombreJugador = itemView.findViewById(R.id.tvFutbolista);
            nacionalidad = itemView.findViewById(R.id.tvNacionalidad);

            actualizar = itemView.findViewById(R.id.imgFutbolista);
            borrar = itemView.findViewById(R.id.eliminar);


        }
    }

}
