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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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
import principal.android.utp.proyecto.bean.HorarioBean;
import principal.android.utp.proyecto.bean.UsuarioBean;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoSeccionDAO;
import principal.android.utp.proyecto.dao.HorarioDAO;
import principal.android.utp.proyecto.dao.UsuarioDAO;


public class Docente_Horario extends Fragment {

    ListView lv_Lunes,lv_Martes,lv_Miercoles,lv_Jueves,lv_Viernes;
    ArrayAdapter<String> adaptador;
    ArrayList<HorarioBean> listado;
    ArrayList<String> listado_horario;
    HorarioBean objHorarioBean;
    HorarioDAO objHorarioDAO;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.docente_horario, container, false);
        lv_Lunes = (ListView)view.findViewById(R.id.lv_Lunes);
        lv_Martes = (ListView)view.findViewById(R.id.lv_Martes);
        lv_Miercoles = (ListView)view.findViewById(R.id.lv_Miercoles);
        lv_Jueves = (ListView)view.findViewById(R.id.lv_Jueves);
        lv_Viernes = (ListView)view.findViewById(R.id.lv_Viernes);

        final Docente_Horario.asyncListarHorarioLunes Listar = new Docente_Horario.asyncListarHorarioLunes();
        final Docente_Horario.asyncListarHorarioMartes Listar2 = new Docente_Horario.asyncListarHorarioMartes();
        final Docente_Horario.asyncListarHorarioMiercoles Listar3 = new Docente_Horario.asyncListarHorarioMiercoles();
        final Docente_Horario.asyncListarHorarioJueves Listar4 = new Docente_Horario.asyncListarHorarioJueves();
        final Docente_Horario.asyncListarHorarioViernes Listar5 = new Docente_Horario.asyncListarHorarioViernes();
        Listar.execute(); Listar2.execute(); Listar3.execute(); Listar4.execute(); Listar5.execute();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Listar Horario Lunes del Docente ////////////////////////////////////////////////////////////////////

    class asyncListarHorarioLunes extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String codigo = ((ApplicationApp) getActivity().getApplication() ).getCodigo();
                objHorarioDAO  = new HorarioDAO();
                listado = new ArrayList<HorarioBean>();
                listado= objHorarioDAO.MostrarHorario(codigo,"Lunes") ;
                listado_horario = new ArrayList<String>();
                int count = listado.size();
                String curso,horas,seccion,grado;
                for(int i=0;i <= count ;i++) {
                    horas = listado.get(i).getHora_Inicio() + " - " + listado.get(i).getHora_Fin();
                    seccion = listado.get(i).getSeccion();
                    grado = listado.get(i).getGrado();
                    curso = listado.get(i).getCurso();
                    listado_horario.add(i,horas);
                    listado_horario.add(i,curso + " " + grado + " " + seccion);
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_horario);
                lv_Lunes.setAdapter(adaptador);
            }
        }
    }

    // Listar Horario Martes del Docente ////////////////////////////////////////////////////////////////////

    class asyncListarHorarioMartes extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String codigo = ((ApplicationApp) getActivity().getApplication() ).getCodigo();
                objHorarioDAO  = new HorarioDAO();
                listado = new ArrayList<HorarioBean>();
                listado= objHorarioDAO.MostrarHorario(codigo,"Martes") ;
                listado_horario = new ArrayList<String>();
                int count = listado.size();
                String curso,horas,seccion,grado;
                for(int i=0;i <= count ;i++) {
                    horas = listado.get(i).getHora_Inicio() + " - " + listado.get(i).getHora_Fin();
                    seccion = listado.get(i).getSeccion();
                    grado = listado.get(i).getGrado();
                    curso = listado.get(i).getCurso();
                    listado_horario.add(i,horas);
                    listado_horario.add(i,curso + " " + grado + " " + seccion);
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_horario);
                lv_Martes.setAdapter(adaptador);
            }
        }
    }



    // Listar Horario Miercoles del Docente ////////////////////////////////////////////////////////////////////

    class asyncListarHorarioMiercoles extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String codigo = ((ApplicationApp) getActivity().getApplication() ).getCodigo();
                objHorarioDAO  = new HorarioDAO();
                listado = new ArrayList<HorarioBean>();
                listado= objHorarioDAO.MostrarHorario(codigo,"Miércoles") ;
                listado_horario = new ArrayList<String>();
                int count = listado.size();
                String curso,horas,seccion,grado;
                for(int i=0;i <= count ;i++) {
                    horas = listado.get(i).getHora_Inicio() + " - " + listado.get(i).getHora_Fin();
                    seccion = listado.get(i).getSeccion();
                    grado = listado.get(i).getGrado();
                    curso = listado.get(i).getCurso();
                    listado_horario.add(i,horas);
                    listado_horario.add(i,curso + " " + grado + " " + seccion);
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_horario);
                lv_Miercoles.setAdapter(adaptador);
            }
        }
    }


    // Listar Horario Jueves del Docente ////////////////////////////////////////////////////////////////////

    class asyncListarHorarioJueves extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String codigo = ((ApplicationApp) getActivity().getApplication() ).getCodigo();
                objHorarioDAO  = new HorarioDAO();
                listado = new ArrayList<HorarioBean>();
                listado= objHorarioDAO.MostrarHorario(codigo,"Jueves") ;
                listado_horario = new ArrayList<String>();
                int count = listado.size();
                String curso,horas,seccion,grado;
                for(int i=0;i <= count ;i++) {
                    horas = listado.get(i).getHora_Inicio() + " - " + listado.get(i).getHora_Fin();
                    seccion = listado.get(i).getSeccion();
                    grado = listado.get(i).getGrado();
                    curso = listado.get(i).getCurso();
                    listado_horario.add(i,horas);
                    listado_horario.add(i,curso + " " + grado + " " + seccion);
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_horario);
                lv_Jueves.setAdapter(adaptador);
            }
        }
    }


    // Listar Horario Viernes del Docente ////////////////////////////////////////////////////////////////////

    class asyncListarHorarioViernes extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String codigo = ((ApplicationApp) getActivity().getApplication() ).getCodigo();
                objHorarioDAO  = new HorarioDAO();
                listado = new ArrayList<HorarioBean>();
                listado= objHorarioDAO.MostrarHorario(codigo,"Viernes") ;
                listado_horario = new ArrayList<String>();
                int count = listado.size();
                String curso,horas,seccion,grado;
                for(int i=0;i <= count ;i++) {
                    horas = listado.get(i).getHora_Inicio() + " - " + listado.get(i).getHora_Fin();
                    seccion = listado.get(i).getSeccion();
                    grado = listado.get(i).getGrado();
                    curso = listado.get(i).getCurso();
                    listado_horario.add(i,horas);
                    listado_horario.add(i,curso + " " + grado + " " + seccion);
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_horario);
                lv_Viernes.setAdapter(adaptador);
            }
        }
    }

}