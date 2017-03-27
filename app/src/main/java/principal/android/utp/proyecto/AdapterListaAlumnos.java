package principal.android.utp.proyecto;

import android.app.Activity;
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

public class AdapterListaAlumnos extends ArrayAdapter<Alumno_SeccionBean> {

    ArrayList<Alumno_SeccionBean> lista_Alumnos = new ArrayList<>();
    Integer[] imageId = {R.id.imgAlumno};
    public AdapterListaAlumnos(Context context, int textViewResourceId, ArrayList<Alumno_SeccionBean> objects) {
        super(context, textViewResourceId, objects);
        lista_Alumnos = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grilla_alumnos, null);
        TextView textView = (TextView) v.findViewById(R.id.txtNombre);
        ImageView imageView = (ImageView) v.findViewById(R.id.imgAlumno);
        textView.setText(lista_Alumnos.get(position).getDes_Nombre());
        imageView.setImageResource(R.drawable.alumno);
        return v;

    }
}
