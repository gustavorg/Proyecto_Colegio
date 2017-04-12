package principal.android.utp.proyecto;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by GRLIMA on 11/02/2017.
 */

public class Docente extends AppCompatActivity {

    private Toolbar toolbar;

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;


  //  ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_docente);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);


        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        nvDrawer = (NavigationView) findViewById(R.id.navigation_view_d);

        drawerToggle = setupDrawerToggle();
        setupDrawerContent(nvDrawer);


        mDrawer.addDrawerListener(drawerToggle);

        // INICIALIZACION DE FRAGMENT
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent_d, new InicioFragment() ).commit();

        mDrawer.closeDrawers();


        View headerView = nvDrawer.getHeaderView( 0 );

        ImageView teacherImageView = ( ImageView ) headerView.findViewById(R.id.imageView5);
        TextView teacherNameView = (TextView) headerView.findViewById(R.id.codigo);
        TextView teacherEmailView = ( TextView ) headerView.findViewById(R.id.nombre);

       /* Picasso.with( this ).load( currentUser.getAvatar() ).transform( new CircleTransform() ).into(teacherImageView);*/
        teacherNameView.setText( ((ApplicationApp)getApplication() ).getCodigo());
        teacherEmailView.setText( ((ApplicationApp)getApplication() ).getNombre());




    }




    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });

    }

    private void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.cursos:
                fragmentClass = Docente_Cursos.class;
                break;
            case R.id.home:
                fragmentClass = InicioFragment.class;
                break;
            case R.id.horario:
                fragmentClass = Docente_Horario.class;
                break;
            default:
                fragmentClass = InicioFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent_d, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();

    }
}
