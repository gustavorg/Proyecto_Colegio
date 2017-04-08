package principal.android.utp.proyecto;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import principal.android.utp.proyecto.bean.Alumno.Alumno_SeccionBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.dao.Alumno.AlumnoDAO;
import principal.android.utp.proyecto.dao.Docente.ActualizarNotaDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoSeccionDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteSeccionAlumnosDAO;

/**
 * Created by GRLIMA on 25/03/2017.
 */

public class Alumno_Detalle extends Fragment{
    ListView lv_NOTAS;
    String lista;
    ArrayAdapter<String> adaptador;
    ArrayList<String> listado_notas;
    String cod_curso,cod_alumno;
    ArrayList<Alumno_SeccionBean> listado;
    ArrayList<String> listado_alumnos;
    Alumno_SeccionBean objAlumno_seccionBean;
    DocenteCursoSeccionBean objDocenteCursoSeccionBean;
    AlumnoDAO objAlumnoDAO;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alumno_detalle, container, false);
        view.setBackgroundColor(Color.WHITE);
       lv_NOTAS = (ListView)view.findViewById(R.id.listnota);

        cod_curso = getArguments().getString("cod_curso");
        cod_alumno = getArguments().getString("cod_alumno");

      final Alumno_Detalle.asyncMostrarNotaAlumno Listar = new Alumno_Detalle.asyncMostrarNotaAlumno();
        Listar.execute();

        lv_NOTAS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, final int position, long arg) {

                Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Bimestre" + (position + 1));
                dialog.setContentView(R.layout.modal_nota);
                dialog.show();

                String n;
                final EditText nota = (EditText)dialog.findViewById(R.id.nota_update);
                if(getArguments().getString("nota"+(position+1)) == "null"){
                    n = "";
                }else{
                    n = getArguments().getString("nota"+(position+1));
                }
                nota.setText(n);

                Button btnActualizar = (Button)dialog.findViewById(R.id.btn_actualizar);
                btnActualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean rpt;
                        String cod_docente,n1="",n2="",n3="",n4="";
                         if(position+1 == 1){
                             n1 = nota.getText().toString();
                             n2 = (getArguments().getString("nota2"));
                             n3 = (getArguments().getString("nota3"));
                             n4 = (getArguments().getString("nota4"));
                         }
                         else if(position+1 == 2){
                             n2 = nota.getText().toString();
                             n1 = (getArguments().getString("nota1"));
                             n3 = (getArguments().getString("nota3"));
                             n4 = (getArguments().getString("nota4"));
                         }
                         else if(position+1 == 3){
                             n3 = nota.getText().toString();
                             n2 = (getArguments().getString("nota2"));
                             n4 = (getArguments().getString("nota4"));
                             n1 = (getArguments().getString("nota1"));
                         }
                         else if(position+1 == 4){
                             n4 = nota.getText().toString();
                             n3 = (getArguments().getString("nota3"));
                             n1 = (getArguments().getString("nota4"));
                             n2 = (getArguments().getString("nota2"));
                         }

                        cod_docente = ((ApplicationApp) getActivity().getApplication() ).getCodigo();

                        ActualizarNotaDAO objActualizarNotaDAO = new ActualizarNotaDAO();
                        rpt = objActualizarNotaDAO.EditarNota(cod_curso,cod_alumno,cod_docente,n1,n2,n3,n4);
                        if(rpt){ Listar.execute(); }
                    }
                });





            }
        });

        return view;


    }


    //Notas del Alumno - Docente ////////////////////////////////////////////////////////////////////

    class asyncMostrarNotaAlumno extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
        try {

            objAlumno_seccionBean = new Alumno_SeccionBean();
            objAlumno_seccionBean.setCodigo_Alumno(cod_alumno);
            objAlumno_seccionBean.setCodigo_Curso(cod_curso);

            listado = new ArrayList<Alumno_SeccionBean>();
            objAlumnoDAO = new AlumnoDAO();
            listado = objAlumnoDAO.NotasAlumno(objAlumno_seccionBean);

            String nota1,nota2,nota3,nota4;
            listado_notas = new ArrayList<String>();
            nota1 = listado.get(0).getNota_I();
            nota2 = listado.get(0).getNota_II();
            nota3 = listado.get(0).getNota_III();
            nota4 = listado.get(0).getNota_IV();

            if(nota1 == null) nota1 = "" ;
            if(nota2 == null) nota2 = "";
            if(nota3.equals("null")) nota3 = "";
            if(nota4.equals("null")) nota4 = "";

            listado_notas.add(0,"BIMESTE 4     " + nota4);
            listado_notas.add(0,"BIMESTE 3     " + nota3);
            listado_notas.add(0,"BIMESTE 2     " + nota2);
            listado_notas.add(0,"BIMESTE 1     " + nota1);


        }catch (Exception e){
            e.getMessage();
            Log.d("resultadoF", "AUN FALTA!!!!!!!!" + listado);
        }
            return null;
        }


        public void onPostExecute(Void result)
        {
            if (!listado.isEmpty())
            {
                Log.d("resultado", "YA ESTA!!!!!!!!" + listado);
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_notas);
                lv_NOTAS.setAdapter(adaptador);
            }
        }
    }



    //Actualizar Nota del Alumno - Docente ////////////////////////////////////////////////////////////////////

    class asyncActualizarNota extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                objAlumno_seccionBean = new Alumno_SeccionBean();
                objAlumno_seccionBean.setCodigo_Alumno(cod_alumno);
                objAlumno_seccionBean.setCodigo_Curso(cod_curso);

                listado = new ArrayList<Alumno_SeccionBean>();
                objAlumnoDAO = new AlumnoDAO();
                listado = objAlumnoDAO.NotasAlumno(objAlumno_seccionBean);

                String nota1,nota2,nota3,nota4;
                listado_notas = new ArrayList<String>();
                nota1 = listado.get(0).getNota_I();
                nota2 = listado.get(0).getNota_II();
                nota3 = listado.get(0).getNota_III();
                nota4 = listado.get(0).getNota_IV();

                if(nota1 == null) nota1 = "" ;
                if(nota2 == null) nota2 = "";
                if(nota3.equals("null")) nota3 = "";
                if(nota4.equals("null")) nota4 = "";

                listado_notas.add(0,"BIMESTE 4     " + nota4);
                listado_notas.add(0,"BIMESTE 3     " + nota3);
                listado_notas.add(0,"BIMESTE 2     " + nota2);
                listado_notas.add(0,"BIMESTE 1     " + nota1);


            }catch (Exception e){
                e.getMessage();
                Log.d("resultadoF", "AUN FALTA!!!!!!!!" + listado);
            }
            return null;
        }


        public void onPostExecute(Void result)
        {
            if (!listado.isEmpty())
            {
                Log.d("resultado", "YA ESTA!!!!!!!!" + listado);
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_notas);
                lv_NOTAS.setAdapter(adaptador);
            }
        }
    }

}
