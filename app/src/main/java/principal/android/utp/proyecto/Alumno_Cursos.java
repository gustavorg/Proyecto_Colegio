package principal.android.utp.proyecto;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import principal.android.utp.proyecto.bean.Alumno.AlumnoCursoBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.bean.UsuarioBean;
import principal.android.utp.proyecto.dao.Alumno.AlumnoCursoDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoDAO;
import principal.android.utp.proyecto.dao.UsuarioDAO;

/**
 * Created by GRLIMA on 11/02/2017.
 */

public class Alumno_Cursos extends Fragment {

    ListView lv;
    ArrayAdapter<String> adaptador;
    ArrayList<AlumnoCursoBean> listado;
    ArrayList<String> listado_cursos;
    AlumnoCursoBean objAlumnoCursoBean;
    AlumnoCursoDAO objAlumnoCursoDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alumno_cursos, container, false);
        lv = (ListView)view.findViewById(R.id.lv_alumno_cursos);
        final asyncMostrarCursosAlumno Listar = new asyncMostrarCursosAlumno();
        Listar.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                Bundle args = new Bundle();
                args.putString("cod_curso",  listado.get(position).getCodigo());
                args.putString("nom_curso",  listado.get(position).getNombre_Curso());

                Alumno_Notas fragment2 = new Alumno_Notas();
                fragment2.setArguments(args);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.alumno_cursosframe, fragment2);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    // Listar Cursos  ////////////////////////////////////////////////////////////////////

    class asyncMostrarCursosAlumno extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String codigo = ((ApplicationApp) getActivity().getApplication() ).getCodigo();
                objAlumnoCursoDAO  = new AlumnoCursoDAO();
                listado = new ArrayList<AlumnoCursoBean>();
                listado= objAlumnoCursoDAO.MostrarCursos(codigo) ;
                listado_cursos = new ArrayList<String>();
                int count = listado.size();
                String nombre_curso;
                for(int i=0;i <= count ;i++) {
                    nombre_curso = listado.get(i).getNombre_Curso();
                    listado_cursos.add(i,nombre_curso);
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_cursos);
                lv.setAdapter(adaptador);
            }
        }
    }

}

