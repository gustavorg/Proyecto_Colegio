package principal.android.utp.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by GRLIMA on 11/02/2017.
 */
public class Alumno extends AppCompatActivity {

    Button btnHorario,btnCursos,btnNotas,btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_alumno);

        btnHorario = (Button) findViewById(R.id.btnHorario);
        btnCursos = (Button) findViewById(R.id.btnCursos);
        btnNotas = (Button) findViewById(R.id.btnNotas);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnHorario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent objIntent = new Intent(Alumno.this, Alumno_Horario.class);
                    startActivity(objIntent);
            }
        });
        btnCursos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent objIntent = new Intent(Alumno.this, Alumno_Cursos.class);
                startActivity(objIntent);
            }
        });
        btnNotas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent objIntent = new Intent(Alumno.this, Alumno_Notas.class);
                startActivity(objIntent);
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent objIntent = new Intent(Alumno.this, Principal.class);
                startActivity(objIntent);
            }
        });



    }
}
