package principal.android.utp.proyecto;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import principal.android.utp.proyecto.bean.Alumno.Alumno_SeccionBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.dao.Docente.DocenteSeccionAlumnosDAO;

/**
 * Created by GRLIMA on 17/02/2017.
 */

public class Lista_Alumnos extends Fragment {

    ListView lv;
    ArrayList<Alumno_SeccionBean> listado;
    ArrayList<String> listado_alumnos;
    DocenteCursoSeccionBean objDocenteCursoSeccionBean;
    DocenteSeccionAlumnosDAO objDocenteSeccionAlumnosDAO;
    String[] lista;

    Integer[] imageId = {R.id.imgAlumno};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.docente_alumnos, container, false);
        TextView alumnosseccion = (TextView) view.findViewById(R.id.alumnoseccion);
        lv = (ListView) view.findViewById(R.id.listAlumnos);
        String title = "Lista de alumnos";
        alumnosseccion.setText(title);

        final Lista_Alumnos.asyncMostrarAlumnosporSeccion Listar = new Lista_Alumnos.asyncMostrarAlumnosporSeccion();
        Listar.execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                Bundle args = new Bundle();
                //Alumno
                args.putString("codigo_Alumno", listado.get(position).getCodigo_Alumno());
                args.putString("apP",  listado.get(position).getDes_ApellidoPat());
                args.putString("apM",  listado.get(position).getDes_ApellidoMat());
                args.putString("nom",  listado.get(position).getDes_Nombre());
              /*  args.putString("nota1",  listado.get(position).getNota_I());
                args.putString("nota2",  listado.get(position).getNota_II());
                args.putString("nota3",  listado.get(position).getNota_III());
                args.putString("nota4",  listado.get(position).getNota_IV());*/


                //Seccion
                args.putString("horaI",  getArguments().getString("horaI"));
                args.putString("horaF",  getArguments().getString("horaF"));
                args.putString("grado",  getArguments().getString("grado"));
                args.putString("seccion", getArguments().getString("seccion"));
                args.putString("curso",  getArguments().getString("nomcurso"));



                Alumno_Detalle fragment4 = new Alumno_Detalle();
                fragment4.setArguments(args);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.alumnosframe, fragment4);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        return view;
    }

    // Listar Alumnos ////////////////////////////////////////////////////////////////////

    class asyncMostrarAlumnosporSeccion extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                objDocenteCursoSeccionBean = new DocenteCursoSeccionBean();
                objDocenteCursoSeccionBean.setCodigo_docente(((ApplicationApp) getActivity().getApplication() ).getCodigo());
                objDocenteCursoSeccionBean.setNom_curso(getArguments().getString("curso"));
                objDocenteCursoSeccionBean.setHora_inicio(getArguments().getString("horaI"));
                objDocenteCursoSeccionBean.setHora_fin(getArguments().getString("horaF"));
                objDocenteCursoSeccionBean.setGrado(getArguments().getString("grado"));
                objDocenteCursoSeccionBean.setSeccion(getArguments().getString("seccion"));


                objDocenteSeccionAlumnosDAO  = new DocenteSeccionAlumnosDAO();
                listado = new ArrayList<Alumno_SeccionBean>();
                listado = objDocenteSeccionAlumnosDAO.AlumnosporSeccion(objDocenteCursoSeccionBean) ;

            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
                AdapterListaAlumnos adapter = new AdapterListaAlumnos(getActivity(),R.layout.content_alumnos_seccion , listado);
                lv.setAdapter(adapter);
            }
        }
    }

}
