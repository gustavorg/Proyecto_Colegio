package principal.android.utp.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by GRLIMA on 11/02/2017.
 */

public class Docente_Cursos extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docente_cursos);
        lv = (ListView)findViewById(R.id.lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                if (position == 1) {
                    Intent objIntent = new Intent(Docente_Cursos.this, Lista_Alumnos.class);
                    startActivity(objIntent);
                }

            }
        });
    }
}