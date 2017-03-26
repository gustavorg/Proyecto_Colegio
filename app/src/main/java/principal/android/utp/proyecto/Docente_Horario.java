package principal.android.utp.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by GRLIMA on 11/02/2017.
 */

public class Docente_Horario extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docente_horario);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initNavigationDrawer();
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
                        objIntent = new Intent(Docente_Horario.this, Docente.class);
                        objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                        objIntent.putExtra("nombre", getIntent().getStringExtra("nombre") );
                        startActivity(objIntent);
                        break;
                    case R.id.cursos:
                        objIntent = new Intent(Docente_Horario.this,Docente_Cursos.class);
                        objIntent.putExtra("codigo", getIntent().getStringExtra("codigo"));
                        objIntent.putExtra("nombre", getIntent().getStringExtra("nombre") );
                        startActivity(objIntent);
                        break;
                    case R.id.horario:
                        drawerLayout.closeDrawers();
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
}