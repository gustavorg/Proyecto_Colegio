package principal.android.utp.proyecto.bean.Docente;

import principal.android.utp.proyecto.Docente;
import principal.android.utp.proyecto.dao.Docente.DocenteCursoDAO;

/**
 * Created by GRLIMA on 11/03/2017.
 */

public class DocenteCursoBean {
    private String Codigo;
    private String Nombre_Curso;
    public DocenteCursoBean(String codigo, String nombre_Curso) {
        Codigo = codigo;
        Nombre_Curso = nombre_Curso;
    }

    public DocenteCursoBean() {

}

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre_Curso() {
        return Nombre_Curso;
    }

    public void setNombre_Curso(String nombre_Curso) {
        Nombre_Curso = nombre_Curso;
    }


}
