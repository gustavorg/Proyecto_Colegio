package principal.android.utp.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import principal.android.utp.proyecto.bean.Alumno.Alumno_SeccionBean;

/**
 * Created by GRLIMA on 26/03/2017.
 */

public class AdapterListaNotas extends ArrayAdapter<Alumno_SeccionBean> {

    ArrayList<Alumno_SeccionBean> lista_notas = new ArrayList<>();
    public AdapterListaNotas(Context context, int textViewResourceId, ArrayList<Alumno_SeccionBean> objects) {
        super(context, textViewResourceId, objects);
        lista_notas = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grilla_notas, null);
        TextView textView = (TextView) v.findViewById(R.id.lblNota);
        textView.setText(( " PC 1 " + lista_notas.get(position).getNota_I() + "\n" +
                           " PC 2 " + lista_notas.get(position).getNota_II() + "\n" +
                           " PC 3 " + lista_notas.get(position).getNota_III() + "\n" +
                           " PC 4 " + lista_notas.get(position).getNota_IV() + "\n" ));
        return v;

    }
}
