package principal.android.utp.proyecto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import principal.android.utp.proyecto.bean.UsuarioBean;
import principal.android.utp.proyecto.dao.UsuarioDAO;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnIngresar;
    EditText txtUsuario,txtContraseña;
    UsuarioBean objUsuarioBean ;
    UsuarioDAO objUsuarioDAO ;

    UsuarioBean resultado ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtContraseña = (EditText)findViewById(R.id.txtContraseña);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if(v==btnIngresar)
        {
            Validar();
        }

    }

    public   void Validar()
    {
        new asyncValidarUsuario().execute();
    }

    public String IngresarCODIGO()
    {
        String codigo = txtUsuario.getText().toString();
        return codigo;
    }

    public String IngresarPWD()
    {
        String pwd = txtContraseña.getText().toString();
        return pwd;
    }



    class asyncValidarUsuario extends AsyncTask<String,Void,Void>
    {
        private ProgressDialog progressDialog;

        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(MainActivity.this,"Validando Datos!!","Espere unos segundos!!",true);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                String codigo = IngresarCODIGO();
                String pwd = IngresarPWD();
                objUsuarioBean = new UsuarioBean();
                objUsuarioBean.setCodigo(codigo);
                objUsuarioBean.setPassword(pwd);
                objUsuarioDAO  = new UsuarioDAO();
                resultado = objUsuarioDAO.ValidarDatos(objUsuarioBean);
                String tipo = resultado.getTipo_Usuario().toString();

                if(tipo.equals("docente")){
                    Intent objIntent = new Intent(MainActivity.this,Docente.class);
                    objIntent.putExtra("codigo", resultado.getCodigo().toString());
                    objIntent.putExtra("nombre", resultado.getDes_Nombre().toString()+ " "+ resultado.getDes_ApellidoPat().toString() + " " + resultado.getDes_ApeliidoMat().toString() );
                    startActivity(objIntent);
                    finish();
                }else if (tipo.equals("alumno")){
                    Intent objIntent = new Intent(MainActivity.this,Alumno.class);
                    objIntent.putExtra("codigo", resultado.getCodigo().toString());
                    objIntent.putExtra("nombre", resultado.getDes_Nombre().toString()+ " "+ resultado.getDes_ApellidoPat().toString() + " " + resultado.getDes_ApeliidoMat().toString() );
                    startActivity(objIntent);
                    finish();
                }
            }catch (Exception e){

            }

            return null;
        }


        public void onPostExecute(Void result){
            if (resultado == null) {
                Toast.makeText(getApplicationContext(), "Error",
                        Toast.LENGTH_SHORT).show();
            } else {
                progressDialog.setTitle("Cargando...");
            }

            progressDialog.dismiss();
        }
    }
}
