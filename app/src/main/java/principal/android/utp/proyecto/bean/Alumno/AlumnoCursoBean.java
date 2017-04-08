package principal.android.utp.proyecto.bean.Alumno;

/**
 * Created by GRLIMA on 11/03/2017.
 */

public class AlumnoCursoBean {
    private String Codigo;
    private String Nombre_Curso;
    public AlumnoCursoBean(String codigo, String nombre_Curso) {
        Codigo = codigo;
        Nombre_Curso = nombre_Curso;
    }

    public AlumnoCursoBean() {

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
