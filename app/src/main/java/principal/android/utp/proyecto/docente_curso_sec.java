package principal.android.utp.proyecto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoSeccionDAO;

public class docente_curso_sec extends AppCompatActivity {
    ListView lv;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    String nombre_curso,seccion,grado,horainicio,horafin,turno,dia;
    ArrayAdapter<String> adaptador;
    ArrayList<String> listado_secciones;
    ArrayList<DocenteCursoSeccionBean> listado;
    DocenteCursoBean objDocenteCursoBean;
    DocenteCursoSeccionBean objDocenteCursoSeccionBean;
    DocenteCursoSeccionDAO objDocenteCursoSeccionDAO;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docente_curso_sec);
        toolbar = (Toolbar) findViewById(R.id.toolbar_secciones);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Secciones de " + getIntent().getStringExtra("nomcurso") );
        toolbar.setTitleTextColor(Color.WHITE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initNavigationDrawer();
        lv = (ListView)findViewById(R.id.lvseccion2);

        docente_curso_sec.asyncMostrarCursosDocenteSeccion Listar = new docente_curso_sec.asyncMostrarCursosDocenteSeccion();
        Listar.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent objIntent = new Intent(docente_curso_sec.this, Lista_Alumnos.class);
                objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                objIntent.putExtra("nombre", getIntent().getStringExtra("nombre"));
                objIntent.putExtra("curso", getIntent().getStringExtra("nomcurso"));
                objIntent.putExtra("horaI", listado.get(position).getHora_inicio());
                objIntent.putExtra("horaF", listado.get(position).getHora_fin());
                objIntent.putExtra("grado", listado.get(position).getGrado());
                objIntent.putExtra("seccion", listado.get(position).getSeccion());
                startActivity(objIntent);
                finish();

                if(listado_secciones.get(position).toString() == ""){
                    Toast.makeText(getApplicationContext(), "No tiene Alumnos en esta Seccion",
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
                                objIntent = new Intent(docente_curso_sec.this, Docente.class);
                                objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                                objIntent.putExtra("nombre", getIntent().getStringExtra("nombre") );
                                startActivity(objIntent);
                                break;
                            case R.id.cursos:
                                drawerLayout.closeDrawers();
                                break;
                            case R.id.horario:
                                objIntent = new Intent(docente_curso_sec.this, Docente_Horario.class);
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
                adaptador = new ArrayAdapter<String>(docente_curso_sec .this, android.R.layout.simple_list_item_1,listado_secciones);
                lv.setAdapter(adaptador);
            }
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(docente_curso_sec.this, Docente_Cursos.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("codigo",getIntent().getStringExtra("codigo"));
                intent.putExtra("nombre",getIntent().getStringExtra("nombre"));
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
