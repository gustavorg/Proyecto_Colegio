package principal.android.utp.proyecto.dao;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import static android.content.ContentValues.TAG;

import principal.android.utp.proyecto.bean.UsuarioBean;

/**
 * Created by GRLIMA on 02/03/2017.
 */

public class UsuarioDAO {
    UsuarioBean result;
    String ruta1="http://10.0.3.2:8080/sistemacolegio/index.php/student/login";
    String ruta2="http://10.0.3.2:8080/sistemacolegio/index.php/teacher/loginController";

    public UsuarioBean ValidarDatos(UsuarioBean objUsuarioBean)
    {
        InputStream is = null,is2 = null;
        String linea,txtcodigo,txtpwd,linea2;

        try
        {
            txtcodigo=objUsuarioBean.getCodigo();
            txtpwd=objUsuarioBean.getPassword();

            List<NameValuePair> parametros = new ArrayList<NameValuePair>();

            parametros.add(new BasicNameValuePair("TXTCODIGO",txtcodigo));
            parametros.add(new BasicNameValuePair("TXTPWD",txtpwd));

            HttpClient cn = new DefaultHttpClient();
            HttpPost post = new HttpPost(ruta1);

            HttpClient cn2 = new DefaultHttpClient();
            HttpPost post2 = new HttpPost(ruta2);

            post.setEntity(new UrlEncodedFormEntity(parametros));
            post2.setEntity(new UrlEncodedFormEntity(parametros));

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


            HttpResponse respuesta2 = cn2.execute(post2);
            HttpEntity entidad2 = respuesta2.getEntity();

            is2= entidad2.getContent();

            BufferedReader reader2 = new BufferedReader(new InputStreamReader(is2));
            StringBuilder trama2 = new StringBuilder();

            while ((linea2=reader2.readLine())!=null)
            {
                trama2.append(linea2 + "\n");
            }
            is2.close();


            JSONArray json2 = new JSONArray(trama2.toString());
            JSONArray json = new JSONArray(trama.toString());

            result = new UsuarioBean();

            if(json.length() != 0) {

                    for (int i = 0; i < json.length(); i++) {
                        JSONObject jsonobject = json.getJSONObject(i);
                        result.setCodigo(jsonobject.getString("Codigo_Alumno"));
                        result.setDes_Nombre(jsonobject.getString("Des_Nombre"));
                        result.setDes_ApellidoPat(jsonobject.getString("Des_ApellidoPat"));
                        result.setDes_ApeliidoMat(jsonobject.getString("Des_ApellidoMat"));
                        result.setTipo_Usuario("alumno");
                    }

            }else if(json2.length() != 0){

                for (int i = 0; i < json2.length(); i++) {
                        JSONObject jsonobject = json2.getJSONObject(i);
                        result.setCodigo(jsonobject.getString("Codigo_Docente"));
                        result.setDes_Nombre(jsonobject.getString("Des_Nombre"));
                        result.setDes_ApellidoPat(jsonobject.getString("Des_ApellidoPat"));
                        result.setDes_ApeliidoMat(jsonobject.getString("Des_ApellidoMat"));
                        result.setTipo_Usuario("docente");
                }
            }else{
                result = null;
            }
        } catch (Exception e)
        {
            Log.v(TAG, e.getMessage().toString());
            result = null;

        }
        return result;
    }
}