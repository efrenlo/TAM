package mx.edu.ittepic.labu3_recycler.Helper;

import android.support.v7.widget.RecyclerView;

public interface RecyclerItemTouchHelperListener {
    void onSwipe(RecyclerView.ViewHolder viewHolder, int direccion, int posicion);
}
