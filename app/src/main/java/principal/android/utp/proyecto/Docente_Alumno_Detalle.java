package principal.android.utp.proyecto;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import principal.android.utp.proyecto.bean.Alumno.Alumno_SeccionBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.dao.Alumno.AlumnoDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoSeccionDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteSeccionAlumnosDAO;

/**
 * Created by GRLIMA on 25/03/2017.
 */

public class Docente_Alumno_Detalle extends Fragment {
    ListView lv;
    String calificaciones,codigo_curso,codigo_alumno;
    ArrayAdapter<String> adaptador;
    ArrayList<Alumno_SeccionBean> listado;
    ArrayList<String> listado_notas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alumno_detalle, container, false);
        view.setBackgroundColor(Color.WHITE);
        lv = (ListView)view.findViewById(R.id.listnota);
       /* codigo = (TextView)view.findViewById(R.id.lblCodigo);
         nombre = (TextView)view.findViewById(R.id.lblNombre);
         apP = (TextView)view.findViewById(R.id.lblApPat);
         apM = (TextView)view.findViewById(R.id.lblApMat);

        codigo.setText(getArguments().getString("codigo_Alumno"));
        nombre.setText(getArguments().getString("nom"));
        apP.setText(getArguments().getString("apP"));
        apM.setText(getArguments().getString("apM"));*/
       codigo_alumno = getArguments().getString("cod_alumno");
       codigo_curso = getArguments().getString("cod_curso");

      final Docente_Alumno_Detalle.asyncMostrarNotaAlumno Listar = new Docente_Alumno_Detalle.asyncMostrarNotaAlumno();
        Listar.execute();

        return view;
    }

    // Listar Cursos del Docente ////////////////////////////////////////////////////////////////////

    class asyncMostrarNotaAlumno extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

              /*  objDocenteCursoSeccionBean.setCodigo_docente(((ApplicationApp) getActivity().getApplication() ).getCodigo());
                objDocenteCursoSeccionBean.setNom_curso(getArguments().getString("curso"));
                objDocenteCursoSeccionBean.setHora_inicio(getArguments().getString("horaI"));
                objDocenteCursoSeccionBean.setHora_fin(getArguments().getString("horaF"));
                objDocenteCursoSeccionBean.setGrado(getArguments().getString("grado"));
                objDocenteCursoSeccionBean.setSeccion(getArguments().getString("seccion")); */

                listado_notas= new ArrayList<String>();
                int count = listado.size();
                for(int i=0;i <= count ;i++) {
                    calificaciones = "PC 1      " + getArguments().getString("nota1") + "\n" +
                                    " PC 2      " + getArguments().getString("nota2") + "\n" +
                                    " PC 3      " + getArguments().getString("nota3") + "\n" +
                                    " PC 4      " + getArguments().getString("nota4");
                    listado_notas.add(i,calificaciones);
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado_notas != null)
            {
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_notas);
                lv.setAdapter(adaptador);

            }
        }
    }

}
