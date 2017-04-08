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
import android.widget.TextView;

/**
 * Created by GRLIMA on 11/02/2017.
 */

public class Alumno extends AppCompatActivity {

    private Toolbar toolbar_a;

    private ActionBarDrawerToggle drawerToggle_a;
    private DrawerLayout mDrawer_a;
    private NavigationView nvDrawer_a;


    //  ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_alumno);
        toolbar_a = (Toolbar) findViewById(R.id.toolbar_a);
        setSupportActionBar(toolbar_a);
        toolbar_a.setTitleTextColor(Color.WHITE);


        mDrawer_a = (DrawerLayout) findViewById(R.id.drawer_a);
        nvDrawer_a = (NavigationView) findViewById(R.id.navigation_view_a);

        drawerToggle_a = setupDrawerToggle();
        setupDrawerContent(nvDrawer_a);


        mDrawer_a.addDrawerListener(drawerToggle_a);

        // INICIALIZACION DE FRAGMENT
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent_a, new InicioFragment() ).commit();

        mDrawer_a.closeDrawers();


        View headerView = nvDrawer_a.getHeaderView( 0 );

      //  ImageView teacherImageView = ( ImageView ) headerView.findViewById(R.id.imageView8);
        TextView studentCodeView = (TextView) headerView.findViewById(R.id.codigo_a);
        TextView studentNameView = ( TextView ) headerView.findViewById(R.id.nombre_a);

       /* Picasso.with( this ).load( currentUser.getAvatar() ).transform( new CircleTransform() ).into(teacherImageView);*/
        studentCodeView.setText( ((ApplicationApp)getApplication() ).getCodigo());
        studentNameView.setText( ((ApplicationApp)getApplication() ).getNombre());




    }




    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle_a.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle_a.onConfigurationChanged(newConfig);
    }
    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer_a, toolbar_a, R.string.drawer_open,  R.string.drawer_close);
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
            case R.id.cursos_a:
                fragmentClass = Alumno_Cursos.class;
                break;
            case R.id.home_a:
                fragmentClass = InicioFragment.class;

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
        fragmentManager.beginTransaction().replace(R.id.flContent_a, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer_a.closeDrawers();

    }
}
