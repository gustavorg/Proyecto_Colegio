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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    ArrayAdapter<String> adaptador,adaptador2;
    ArrayList<String> listado_notas,listado_notas2;
    String cod_curso,cod_alumno;
    ArrayList<Alumno_SeccionBean> listado,listado2;
    ArrayList<String> listado_alumnos;
    Alumno_SeccionBean objAlumno_seccionBean,objAlumno_seccionBean2;
    DocenteCursoSeccionBean objDocenteCursoSeccionBean;
    AlumnoDAO objAlumnoDAO,objAlumnoDAO2 ;
    Dialog dialog;
    EditText nota;
    String C1,C2,C3,C4;
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

                dialog = new Dialog(getActivity());
                dialog.setTitle("Bimestre" + (position + 1));
                dialog.setContentView(R.layout.modal_nota);
                dialog.show();

                 nota = (EditText)dialog.findViewById(R.id.nota_update);

                if(position == 0) nota.setText(C1);else if(position == 1) nota.setText(C2);
                else if(position == 2) nota.setText(C3);else if(position == 3) nota.setText(C4);

                Button btnActualizar = (Button)dialog.findViewById(R.id.btn_actualizar);
                btnActualizar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cod_docente,n1="",n2="",n3="",n4="";
                        if(position+1 == 1){
                            n1 = nota.getText().toString();
                            n2 = C2;
                            n3 = C3;
                            n4 = C4;

                        }
                        else if(position+1 == 2){
                            n2 = nota.getText().toString();
                            n1 = C1;
                            n3 = C3;
                            n4 = C4;
                        }
                        else if(position+1 == 3){
                            n3 = nota.getText().toString();
                            n2 = C2;
                            n4 = C4;
                            n1 = C1;
                        }
                        else if(position+1 == 4){
                            n4 = nota.getText().toString();
                            n3 = C3;
                            n1 = C1;
                            n2 = C2;
                        }

                        cod_docente = ((ApplicationApp) getActivity().getApplication() ).getCodigo();
                        ActualizarNota(cod_curso,cod_alumno,cod_docente,n1,n2,n3,n4);
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

            C1 = nota1; C2 = nota2 ; C3 = nota3 ; C4 = nota4;

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
                lv_NOTAS.setAdapter(adaptador);
            }
        }
    }


    private void ActualizarNota(String cod_curso, String cod_alumno, String cod_docente, String n1, String n2, String n3, String n4) {

        final String codCurso = cod_curso;
        final String codAlumno = cod_alumno;
        final String codDocente = cod_docente;
        final String N1 = n1; final String N2 = n2; final String N3 = n3; final String N4 = n4;


        final String LOGIN_URL = "http://192.241.166.108/sistemacolegio/index.php/teacher/notaController/editarNota";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            final Alumno_Detalle.asyncMostrarNotaAlumno Listar = new Alumno_Detalle.asyncMostrarNotaAlumno();
                            Listar.execute();
                            dialog.dismiss();
                            Toast.makeText(getActivity(),"Nota Actualizada",Toast.LENGTH_LONG).show();
                            nota.setText("");

                        }else{
                            Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("COD_CURSO",codCurso);
                map.put("COD_DOCENTE",codDocente);
                map.put("COD_ALUMNO",codAlumno);
                map.put("N1",N1);
                map.put("N2",N2);
                map.put("N3",N3);
                map.put("N4",N4);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}
