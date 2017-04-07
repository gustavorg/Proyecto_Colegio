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

import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.bean.UsuarioBean;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoDAO;
import principal.android.utp.proyecto.dao.UsuarioDAO;

/**
 * Created by GRLIMA on 11/02/2017.
 */

public class Docente_Cursos extends Fragment {

    ListView lv;
    private DrawerLayout drawerLayout;
    private Toolbar toolbarD;
    String nombre_curso;
    ArrayAdapter<String> adaptador;
    ArrayList<DocenteCursoBean> listado;
    ArrayList<String> listado_cursos;
    DocenteCursoBean objDocenteCursoBean;
    DocenteCursoDAO objDocenteCursoDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.docente_cursos, container, false);
        lv = (ListView)view.findViewById(R.id.lv);
        final asyncMostrarCursosDocente Listar = new asyncMostrarCursosDocente();
        Listar.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                Bundle args = new Bundle();
                args.putString("nomcurso",  listado.get(position).getNombre_Curso());

                Docente_Curso_Seccion fragment2 = new Docente_Curso_Seccion();
                fragment2.setArguments(args);

               FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                fragmentTransaction.commit();
               /* FragmentManager childFragMan = getChildFragmentManager();

                FragmentTransaction childFragTrans = childFragMan.beginTransaction();
                childFragTrans.add(android.R.id.content, fragment2);
                childFragTrans.addToBackStack("B");
                childFragTrans.commit();*/

            }
        });

        return view;
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    // Listar Cursos del Docente ////////////////////////////////////////////////////////////////////

    class asyncMostrarCursosDocente extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String codigo = ((ApplicationApp) getActivity().getApplication() ).getCodigo();
                objDocenteCursoDAO  = new DocenteCursoDAO();
                listado = new ArrayList<DocenteCursoBean>();
                listado= objDocenteCursoDAO.MostrarCursos(codigo) ;
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

