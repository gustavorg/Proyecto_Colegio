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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class Lista_Alumnos  extends AppCompatActivity {

    ListView lv;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    ArrayAdapter<String> adaptador;
    ArrayList<Alumno_SeccionBean> listado;
    ArrayList<String> listado_cursos;
    DocenteCursoSeccionBean objDocenteCursoSeccionBean;
    DocenteSeccionAlumnosDAO objDocenteSeccionAlumnosDAO;
    String[] lista_Alumnos;

    Integer[] imageId = {R.id.imgAlumno};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docente_alumnos);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initNavigationDrawer();
        lv = (ListView)findViewById(R.id.listAlumnos);
        final Lista_Alumnos.asyncMostrarAlumnosporSeccion Listar = new Lista_Alumnos.asyncMostrarAlumnosporSeccion();
        Listar.execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                Intent objIntent = new Intent(Lista_Alumnos.this, Docente_Curso_Seccion.class);
                objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                objIntent.putExtra("nombre", getIntent().getStringExtra("nombre"));
                objIntent.putExtra("nomcurso", listado_cursos.get(position));
                startActivity(objIntent);
                finish();


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
                        objIntent = new Intent(Lista_Alumnos.this, Docente.class);
                        objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                        objIntent.putExtra("nombre", getIntent().getStringExtra("nombre") );
                        startActivity(objIntent);
                        break;
                    case R.id.cursos:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.horario:
                        objIntent = new Intent(Lista_Alumnos.this, Docente_Horario.class);
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
                objDocenteCursoSeccionBean.setCodigo_docente(getIntent().getStringExtra("codigo"));
                objDocenteCursoSeccionBean.setNom_curso(getIntent().getStringExtra("curso"));
                objDocenteCursoSeccionBean.setHora_inicio(getIntent().getStringExtra("horaI"));
                objDocenteCursoSeccionBean.setHora_fin(getIntent().getStringExtra("horaF"));
                objDocenteCursoSeccionBean.setGrado(getIntent().getStringExtra("grado"));
                objDocenteCursoSeccionBean.setSeccion(getIntent().getStringExtra("seccion"));


                objDocenteSeccionAlumnosDAO  = new DocenteSeccionAlumnosDAO();
                listado = new ArrayList<Alumno_SeccionBean>();
                listado = objDocenteSeccionAlumnosDAO.AlumnosporSeccion(objDocenteCursoSeccionBean) ;
                lista_Alumnos =  listado.toArray(new String[listado.size()]);
                listado_cursos = new ArrayList<String>();
                int count = listado.size();
                String nombre_alumno;
                for(int i=0;i <= count ;i++) {
                    nombre_alumno = listado.get(i).getDes_Nombre();
                    listado_cursos.add(i,nombre_alumno);
                }

            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result)
        {
            if (listado != null)
            {
             /* adaptador = new ArrayAdapter<String>(Lista_Alumnos.this, android.R.layout.simple_list_item_1, listado_cursos);
                lv.setAdapter(adaptador);*/
                AdapterListaAlumnos adapter = new AdapterListaAlumnos(Lista_Alumnos.this,R.layout.content_alumnos_seccion , listado);
                lv.setAdapter(adapter);
            }
        }
    }

}
