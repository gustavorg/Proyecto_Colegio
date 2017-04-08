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
import principal.android.utp.proyecto.bean.Docente.DocenteCursoSeccionBean;

import static android.content.ContentValues.TAG;

public class DocenteCursoSeccionDAO {
    DocenteCursoSeccionBean result;
    ArrayList<DocenteCursoSeccionBean> listado = new ArrayList<DocenteCursoSeccionBean>();
    String ruta="http://192.241.166.108/sistemacolegio/index.php/teacher/courseController/detailCourse2";

    public ArrayList<DocenteCursoSeccionBean> CursosporSeccion(DocenteCursoBean objdocenteCursoBean)
    {
        InputStream is = null;
        String linea,txtcodigo,txtnom;

        try
        {
            txtcodigo = objdocenteCursoBean.getCodigo();
            txtnom = objdocenteCursoBean.getNombre_Curso();

            List<NameValuePair> parametros = new ArrayList<NameValuePair>();

            parametros.add(new BasicNameValuePair("CODIGO",txtcodigo));
            parametros.add(new BasicNameValuePair("NOMCUR",txtnom));

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
                result = new DocenteCursoSeccionBean();
               // result.setNom_curso(jsonobject.getString("Des_Nombre"));
                result.setCodigo_curso(jsonobject.getString("Cod_Curso"));
                result.setDia(jsonobject.getString("Dia"));
                result.setGrado(jsonobject.getString("Grado"));
                result.setSeccion(jsonobject.getString("Seccion"));
                result.setTurno(jsonobject.getString("Turno"));
                result.setHora_inicio(jsonobject.getString("Hora_Inicio"));
                result.setHora_fin(jsonobject.getString("Hora_Fin"));
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
