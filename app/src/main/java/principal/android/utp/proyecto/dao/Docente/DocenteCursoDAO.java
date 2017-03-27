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

import principal.android.utp.proyecto.bean.Docente.DocenteCursoBean;

import static android.content.ContentValues.TAG;

public class DocenteCursoDAO {
    DocenteCursoBean result;
    ArrayList<DocenteCursoBean> listado = new ArrayList<DocenteCursoBean>();
    String ruta="http://10.0.3.2:8080/sistemacolegio/index.php/teacher/courseController";

    public ArrayList<DocenteCursoBean> MostrarCursos(String codigo)
    {
        InputStream is = null;
        String linea;

        try
        {
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();

            parametros.add(new BasicNameValuePair("TXTCODIGO",codigo));

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
            for (int i = 0; i < json.length(); i++) {
                result = new DocenteCursoBean();
                JSONObject jsonobject = json.getJSONObject(i);
                result.setNombre_Curso(jsonobject.getString("Des_Nombre"));
                listado.add(result);
            }

        } catch (Exception e)
        {
            Log.v(TAG, e.getMessage().toString());
            result = null;

        }
        return listado;
    }
}
