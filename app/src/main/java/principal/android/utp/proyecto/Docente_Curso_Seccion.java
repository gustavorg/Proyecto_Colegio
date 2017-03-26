package principal.android.utp.proyecto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoDAO;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoSeccionDAO;

/**
 * Created by GRLIMA on 25/03/2017.
 */

public class Docente_Curso_Seccion extends AppCompatActivity {
    ListView lv;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private String[] cursos;
    String nombre_curso,seccion,grado,horainicio,horafin,turno,dia;
    ArrayAdapter<String> adaptador;
    DocenteCursoBean objDocenteCursoBean;
    DocenteCursoSeccionBean objDocenteCursoSeccionBean;
    DocenteCursoSeccionDAO objDocenteCursoSeccionDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docente_curso_seccion);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initNavigationDrawer();
        lv = (ListView)findViewById(R.id.lvseccion);

        Docente_Curso_Seccion.asyncMostrarCursosDocenteSeccion Listar = new Docente_Curso_Seccion.asyncMostrarCursosDocenteSeccion();
        Listar.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                if (position == 1) {
                    Intent objIntent = new Intent(Docente_Curso_Seccion.this, Lista_Alumnos.class);
                    startActivity(objIntent);
                }

            }
        });
    }


    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();
                Intent objIntent;
                switch (id){
                    case R.id.home:
                        objIntent = new Intent(Docente_Curso_Seccion.this, Docente.class);
                        objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                        objIntent.putExtra("nombre", getIntent().getStringExtra("nombre") );
                        startActivity(objIntent);
                        break;
                    case R.id.cursos:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.horario:
                        objIntent = new Intent(Docente_Curso_Seccion.this, Docente_Horario.class);
                        objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                        objIntent.putExtra("nombre", getIntent().getStringExtra("nombre") );
                        startActivity(objIntent);
                        break;
                    case R.id.logout:
                        finish();

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView codigo = (TextView)header.findViewById(R.id.codigo);
        TextView nombre = (TextView)header.findViewById(R.id.nombre);

        codigo.setText(getIntent().getStringExtra("codigo"));
        nombre.setText(getIntent().getStringExtra("nombre"));

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
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

                String codigo = getIntent().getStringExtra("codigo");
                String nomcurso = getIntent().getStringExtra("nomcurso");
                objDocenteCursoSeccionDAO  = new DocenteCursoSeccionDAO();
                objDocenteCursoBean = new DocenteCursoBean();
                objDocenteCursoBean.setCodigo(codigo);
                objDocenteCursoBean.setNombre_Curso(nomcurso);

                objDocenteCursoSeccionBean = new DocenteCursoSeccionBean();
                objDocenteCursoSeccionBean = objDocenteCursoSeccionDAO.CursosporSeccion(objDocenteCursoBean) ;
                List<String> list = new ArrayList<String>();
                list.add(objDocenteCursoSeccionBean.getGrado() + " - " + objDocenteCursoSeccionBean.getSeccion()+"\n"+
                objDocenteCursoSeccionBean.getDia() + " " + objDocenteCursoSeccionBean.getHora_inicio() + "" + objDocenteCursoSeccionBean.getHora_fin()+
                " " + objDocenteCursoSeccionBean.getTurno());
                int count = 0;
                count = list.size();
                /*if(list.size() == 1){
                    count = 0;
                }*/
                cursos = new String[count];
                for(int i=0;i <= count ;i++) {
                    nombre_curso = objDocenteCursoSeccionBean.getNom_curso();
                    grado = objDocenteCursoSeccionBean.getGrado();
                    seccion = objDocenteCursoSeccionBean.getSeccion();
                    horainicio = objDocenteCursoSeccionBean.getHora_inicio();
                    horafin = objDocenteCursoSeccionBean.getHora_fin();
                    turno = objDocenteCursoSeccionBean.getTurno();
                    dia = objDocenteCursoSeccionBean.getDia();
                    cursos[i] = grado + " " + seccion + "\n" +
                          dia  +" "+ horainicio + " " + horafin + " " + turno;
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (objDocenteCursoSeccionBean != null)
            {
                adaptador = new ArrayAdapter<String>(Docente_Curso_Seccion .this, android.R.layout.simple_list_item_1, cursos);
                lv.setAdapter(adaptador);
            }
        }
    }
}
