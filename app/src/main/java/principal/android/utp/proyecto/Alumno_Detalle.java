package principal.android.utp.proyecto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import principal.android.utp.proyecto.bean.Alumno.Alumno_SeccionBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.dao.Alumno.AlumnoDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoSeccionDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteSeccionAlumnosDAO;

/**
 * Created by GRLIMA on 25/03/2017.
 */

public class Alumno_Detalle extends Fragment {
    ListView lv;
    String nombre_curso,seccion,grado,horainicio,horafin,turno,dia;
    ArrayAdapter<String> adaptador;
    ArrayList<Alumno_SeccionBean> listado;
    ArrayList<String> listado_notas;
    DocenteCursoSeccionBean objDocenteCursoSeccionBean;
    AlumnoDAO objAlumnoDAO;
    DocenteCursoSeccionDAO objDocenteCursoSeccionDAO;
    DocenteSeccionAlumnosDAO objDocenteSeccionAlumnosDAO;
    String[] lista;
    TextView  nombre,codigo,apP,apM;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alumno_detalle, container, false);
        view.setBackgroundColor(Color.WHITE);
       /* codigo = (TextView)view.findViewById(R.id.lblCodigo);
         nombre = (TextView)view.findViewById(R.id.lblNombre);
         apP = (TextView)view.findViewById(R.id.lblApPat);
         apM = (TextView)view.findViewById(R.id.lblApMat);

        codigo.setText(getArguments().getString("codigo_Alumno"));
        nombre.setText(getArguments().getString("nom"));
        apP.setText(getArguments().getString("apP"));
        apM.setText(getArguments().getString("apM"));*/

       lv = (ListView)view.findViewById(R.id.listnota);

      final Alumno_Detalle.asyncMostrarNotaAlumno Listar = new Alumno_Detalle.asyncMostrarNotaAlumno();
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
                objDocenteCursoSeccionDAO  = new DocenteCursoSeccionDAO();

                objDocenteCursoSeccionBean.setCodigo_docente(((ApplicationApp) getActivity().getApplication() ).getCodigo());
                objDocenteCursoSeccionBean.setNom_curso(getArguments().getString("curso"));
                objDocenteCursoSeccionBean.setHora_inicio(getArguments().getString("horaI"));
                objDocenteCursoSeccionBean.setHora_fin(getArguments().getString("horaF"));
                objDocenteCursoSeccionBean.setGrado(getArguments().getString("grado"));
                objDocenteCursoSeccionBean.setSeccion(getArguments().getString("seccion"));

                objDocenteSeccionAlumnosDAO  = new DocenteSeccionAlumnosDAO();
                listado = new ArrayList<Alumno_SeccionBean>();
                listado = objAlumnoDAO.AlumnosporSeccion(objDocenteCursoSeccionBean,getArguments().getString("codigo_Alumno")) ;
                listado_notas= new ArrayList<String>();
                int count = listado.size();
                for(int i=0;i <= count ;i++) {
                    nombre_curso = "PC 1 " + listado.get(i).getNota_I() + "\n" +
                                    " PC 2 " + listado.get(i).getNota_II() + "\n" +
                                    " PC 3 " + listado.get(i).getNota_III() + "\n" +
                                    " PC 4 " + listado.get(i).getNota_IV();
                    listado_notas.add(i,nombre_curso);
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_notas);
                lv.setAdapter(adaptador);

            }
        }
    }

}
