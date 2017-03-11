package principal.android.utp.proyecto.dao.Docente;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import principal.android.utp.proyecto.bean.UsuarioBean;

import static android.content.ContentValues.TAG;

/**
 * Created by GRLIMA on 11/03/2017.
 */

public class DocenteCursoDAO {
    UsuarioBean result;
    String ruta1="http://192.241.166.108/sistemacolegio/index.php/student/login";
    public UsuarioBean ValidarDatos(UsuarioBean objUsuarioBean)
    {
        InputStream is = null;
        String linea,txtcodigo,txtpwd;

        try
        {
            txtcodigo=objUsuarioBean.getCodigo();
            txtpwd=objUsuarioBean.getPassword();

            List<NameValuePair> parametros = new ArrayList<NameValuePair>();

            parametros.add(new BasicNameValuePair("TXTCODIGO",txtcodigo));
            parametros.add(new BasicNameValuePair("TXTPWD",txtpwd));

            HttpClient cn = new DefaultHttpClient();
            HttpPost post = new HttpPost(ruta1);

            post.setEntity(new UrlEncodedFormEntity(parametros));

            HttpResponse respuesta = cn.execute(post);
            HttpEntity entidad = respuesta.getEntity();

            is = entidad.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder trama = new StringBuilder();

            while ((linea=reader.readLine())!=null)
            {
                trama.append(linea + "\n");
            }
            is.close();
            JSONObject json = new JSONObject(trama.toString());
            result = new UsuarioBean();
            if(json.has("Codigo_Docente")){
                result.setCodigo(json.getString("Codigo_Docente"));
                result.setTipo_Usuario("docente");
            }else{
                result.setTipo_Usuario("alumno");
                result.setCodigo(json.getString("Codigo_Alumno"));
            }

            result.setDes_Nombre(json.getString("Des_Nombre"));
            result.setDes_ApellidoPat(json.getString("Des_ApellidoPat"));
            result.setDes_ApeliidoMat(json.getString("Des_ApellidoMat"));



        } catch (Exception e)
        {
            Log.v(TAG, e.getMessage().toString());
            result = null;

        }
        return result;
    }
}
