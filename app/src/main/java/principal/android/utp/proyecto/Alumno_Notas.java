package principal.android.utp.proyecto;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import principal.android.utp.proyecto.bean.Alumno.Alumno_SeccionBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.dao.Alumno.AlumnoDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoSeccionDAO;

public class Alumno_Notas extends Fragment {
    ListView lv_nota;
    String nombre_curso,seccion,grado,horainicio,horafin,turno,dia;
    ArrayAdapter<String> adaptador;
    ArrayList<String> listado_notas;
    ArrayList<Alumno_SeccionBean> listado;
    DocenteCursoBean objDocenteCursoBean;
    Alumno_SeccionBean objAlumno_seccionBean;
    AlumnoDAO objAlumnoDAO;
    List<String> list;
    TextView lblCursoNota;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alumno_notas, container, false);
        lv_nota = (ListView)view.findViewById(R.id.lv_nota);
        lblCursoNota = (TextView)view.findViewById(R.id.lblCursoNota);
        String  title = "Curso -" + getArguments().getString("nom_curso") ;
        lblCursoNota.setText(title);

        final Alumno_Notas.asyncMostrarNotas Listar = new Alumno_Notas.asyncMostrarNotas();
        Listar.execute();

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Listar Notas ////////////////////////////////////////////////////////////////////

    class asyncMostrarNotas extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                objAlumno_seccionBean = new Alumno_SeccionBean();
                objAlumno_seccionBean.setCodigo_Alumno(((ApplicationApp) getActivity().getApplication() ).getCodigo());
                objAlumno_seccionBean.setCodigo_Curso(getArguments().getString("cod_curso"));

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
            }
            return null;
        }


        public void onPostExecute(Void result)
        {
            if (!listado.isEmpty())
            {
                Log.d("resultado", "YA ESTA!!!!!!!!" + listado);
                adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listado_notas);
                lv_nota.setAdapter(adaptador);
            }
        }
    }

}
