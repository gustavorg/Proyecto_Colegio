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

import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoSeccionDAO;



public class Docente_Curso_Seccion extends Fragment {
    ListView lv;
    String nombre_curso,seccion,grado,horainicio,horafin,turno,dia;
    ArrayAdapter<String> adaptador;
    ArrayList<String> listado_secciones;
    ArrayList<DocenteCursoSeccionBean> listado;
    DocenteCursoBean objDocenteCursoBean;
    DocenteCursoSeccionBean objDocenteCursoSeccionBean;
    DocenteCursoSeccionDAO objDocenteCursoSeccionDAO;
    List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.docente_curso_seccion, container, false);
        TextView  cursoseccion = (TextView)view.findViewById(R.id.SeccionCurso);
        lv = (ListView)view.findViewById(R.id.lvseccion);
        String  title = "Secciones del curso " + getArguments().getString("nomcurso") ;
        cursoseccion.setText(title);

        final Docente_Curso_Seccion.asyncMostrarCursosDocenteSeccion Listar = new Docente_Curso_Seccion.asyncMostrarCursosDocenteSeccion();
        Listar.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                Bundle args = new Bundle();
                args.putString("curso",  getArguments().getString("nomcurso"));
                args.putString("horaI",  listado.get(position).getHora_inicio());
                args.putString("horaF",  listado.get(position).getHora_fin());
                args.putString("grado",  listado.get(position).getGrado());
                args.putString("codigo", ((ApplicationApp) getActivity().getApplication() ).getCodigo());
                args.putString("seccion",  listado.get(position).getSeccion());

                Lista_Alumnos fragment3 = new Lista_Alumnos();
                fragment3.setArguments(args);

          /*      FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flContent, fragment3);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
                fragmentTransaction.commit();*/
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.seccionesframe, fragment3);
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

    // Listar Cursos del Docente ////////////////////////////////////////////////////////////////////

    class asyncMostrarCursosDocenteSeccion extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {



                String codigo =(( ApplicationApp ) getActivity().getApplication() ).getCodigo();
                String nomcurso = getArguments().getString("nomcurso");
                objDocenteCursoSeccionDAO  = new DocenteCursoSeccionDAO();
                objDocenteCursoBean = new DocenteCursoBean();
                objDocenteCursoBean.setCodigo(codigo);
                objDocenteCursoBean.setNombre_Curso(nomcurso);


                listado = new ArrayList<DocenteCursoSeccionBean>();
                listado = objDocenteCursoSeccionDAO.CursosporSeccion(objDocenteCursoBean);
                listado_secciones = new ArrayList<String>();
                int count = listado.size();
                for(int i=0;i <= count ;i++) {
                    grado = listado.get(i).getGrado();
                    seccion =  listado.get(i).getSeccion();
                    horainicio =  listado.get(i).getHora_inicio();
                    horafin =  listado.get(i).getHora_fin();
                    turno =  listado.get(i).getTurno();
                    dia =  listado.get(i).getDia();
                    listado_secciones.add(i,grado + " " + seccion + "\n" + dia  +" "+ horainicio + " " + horafin + " " + turno);
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,listado_secciones);
                lv.setAdapter(adaptador);

            }
        }
    }

}
