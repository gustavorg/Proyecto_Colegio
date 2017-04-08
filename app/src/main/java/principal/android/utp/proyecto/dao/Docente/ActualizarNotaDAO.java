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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import principal.android.utp.proyecto.ApplicationApp;
import principal.android.utp.proyecto.bean.Alumno.Alumno_SeccionBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;

import static android.content.ContentValues.TAG;

public class ActualizarNotaDAO {
    boolean result;
    ArrayList<DocenteCursoBean> listado = new ArrayList<DocenteCursoBean>();
    String ruta="http://192.241.166.108/sistemacolegio/index.php/teacher/notaController";

    public boolean EditarNota(String cod_curso , String cod_alumno, String cod_docente ,String n1, String n2,String n3,String n4)
    {
        InputStream is = null;
        String linea;

        try
        {

            List<NameValuePair> parametros = new ArrayList<NameValuePair>();

            parametros.add(new BasicNameValuePair("COD_CURSO",cod_curso));
            parametros.add(new BasicNameValuePair("COD_DOCENTE",cod_docente));
            parametros.add(new BasicNameValuePair("COD_ALUMNO",cod_alumno));
            parametros.add(new BasicNameValuePair("N1",n1));
            parametros.add(new BasicNameValuePair("N2",n2));
            parametros.add(new BasicNameValuePair("N3",n3));
            parametros.add(new BasicNameValuePair("N4",n4));


            HttpClient cn = new DefaultHttpClient();
            HttpPost post = new HttpPost(ruta);

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

            JSONArray json = new JSONArray(trama.toString());
            if(json.equals(true)) result = true;
            else result = false;
        } catch (Exception e)
        {
            result = false;

        }
        return result;
    }
}
