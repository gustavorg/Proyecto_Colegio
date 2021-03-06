package principal.android.utp.proyecto.dao.Alumno;

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

import principal.android.utp.proyecto.bean.Alumno.Alumno_SeccionBean;
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;

import static android.content.ContentValues.TAG;

/**
 * Created by GRLIMA on 25/03/2017.
 */

public class AlumnoDAO {
    Alumno_SeccionBean result;
    ArrayList<Alumno_SeccionBean> listado = new ArrayList<Alumno_SeccionBean>();
    String ruta="http://192.241.166.108/sistemacolegio/index.php/student/courseController/detailCourse2";

    public ArrayList<Alumno_SeccionBean> NotasAlumno(Alumno_SeccionBean objAlumno_seccionBean)
    {
        InputStream is = null;
        String linea;

        try
        {
            List<NameValuePair> parametros = new ArrayList<NameValuePair>();

            parametros.add(new BasicNameValuePair("COD_CURSO",objAlumno_seccionBean.getCodigo_Curso()));
            parametros.add(new BasicNameValuePair("COD_ALUMNO",objAlumno_seccionBean.getCodigo_Alumno()));

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
                JSONObject jsonobject = json.getJSONObject(i);
                result = new Alumno_SeccionBean();
                result.setNota_I(jsonobject.getString("Nota_I"));
                result.setNota_II(jsonobject.getString("Nota_II"));
                result.setNota_III(jsonobject.getString("Nota_III"));
                result.setNota_IV(jsonobject.getString("Nota_IV"));
                result.setPromedio(jsonobject.getString("Promedio"));
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
