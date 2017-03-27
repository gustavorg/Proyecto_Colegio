package principal.android.utp.proyecto;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

public class Docente_Cursos extends AppCompatActivity {

    ListView lv;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    String nombre_curso;
    ArrayAdapter<String> adaptador;
    ArrayList<DocenteCursoBean> listado;
    ArrayList<String> listado_cursos;
    DocenteCursoBean objDocenteCursoBean;
    DocenteCursoDAO objDocenteCursoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docente_cursos);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initNavigationDrawer();
        lv = (ListView)findViewById(R.id.lv);
        final asyncMostrarCursosDocente Listar = new asyncMostrarCursosDocente();
        Listar.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                    Intent objIntent = new Intent(Docente_Cursos.this, Docente_Curso_Seccion.class);
                    objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                    objIntent.putExtra("nombre", getIntent().getStringExtra("nombre"));
                    objIntent.putExtra("nomcurso", listado.get(position).getNombre_Curso());
                    startActivity(objIntent);
                    finish();

                if(listado_cursos.get(position).toString() == ""){
                    Toast.makeText(getApplicationContext(), "No tiene Secciones en este curso",
                            Toast.LENGTH_LONG).show();
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
                        objIntent = new Intent(Docente_Cursos.this, Docente.class);
                        objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                        objIntent.putExtra("nombre", getIntent().getStringExtra("nombre") );
                        startActivity(objIntent);
                        break;
                    case R.id.cursos:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.horario:
                        objIntent = new Intent(Docente_Cursos.this, Docente_Horario.class);
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

    class asyncMostrarCursosDocente extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String codigo = getIntent().getStringExtra("codigo");
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
                     adaptador = new ArrayAdapter<String>(Docente_Cursos.this, android.R.layout.simple_list_item_1, listado_cursos);
                     lv.setAdapter(adaptador);
                   }
            }
        }

    }

