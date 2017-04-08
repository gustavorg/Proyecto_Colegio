package principal.android.utp.proyecto.bean.Alumno;

/**
 * Created by GRLIMA on 26/03/2017.
 */

public class Alumno_SeccionBean {


    private String codigo_Alumno;
    private String codigo_Curso;
    private String Des_ApellidoPat;
    private String Des_ApellidoMat;
    private String Des_Nombre;
    private String Nota_I;
    private String Nota_II;
    private String Nota_III;
    private String Nota_IV;
    private String Promedio;

    public Alumno_SeccionBean() {
    }

    public Alumno_SeccionBean(String codigo_Alumno ,String codigo_Curso, String des_ApellidoPat, String des_ApellidoMat, String des_Nombre, String nota_I, String nota_II, String nota_III, String nota_IV, String promedio) {
        setDes_ApellidoPat(des_ApellidoPat);
        setDes_ApellidoMat(des_ApellidoMat);
        setDes_Nombre(des_Nombre);
        setNota_I(nota_I);
        setNota_II(nota_II);
        setNota_III(nota_III);
        setNota_IV(nota_IV);
        setPromedio(promedio);
        setCodigo_Alumno(codigo_Alumno);
        setCodigo_Curso(codigo_Curso);
    }

    public String getCodigo_Curso() {
        return codigo_Curso;
    }

    public void setCodigo_Curso(String codigo_Curso) {
        this.codigo_Curso = codigo_Curso;
    }
    public String getCodigo_Alumno() {
        return codigo_Alumno;
    }

    public void setCodigo_Alumno(String codigo_Alumno) {
        this.codigo_Alumno = codigo_Alumno;
    }
    public String getDes_ApellidoPat() {
        return Des_ApellidoPat;
    }

    public void setDes_ApellidoPat(String des_ApellidoPat) {
        Des_ApellidoPat = des_ApellidoPat;
    }

    public String getDes_ApellidoMat() {
        return Des_ApellidoMat;
    }

    public void setDes_ApellidoMat(String des_ApellidoMat) {
        Des_ApellidoMat = des_ApellidoMat;
    }

    public String getDes_Nombre() {
        return Des_Nombre;
    }

    public void setDes_Nombre(String des_Nombre) {
        Des_Nombre = des_Nombre;
    }

    public String getNota_I() {
        return Nota_I;
    }

    public void setNota_I(String nota_I) {
        Nota_I = nota_I;
    }

    public String getNota_II() {
        return Nota_II;
    }

    public void setNota_II(String nota_II) {
        Nota_II = nota_II;
    }

    public String getNota_III() {
        return Nota_III;
    }

    public void setNota_III(String nota_III) {
        Nota_III = nota_III;
    }

    public String getNota_IV() {
        return Nota_IV;
    }

    public void setNota_IV(String nota_IV) {
        Nota_IV = nota_IV;
    }

    public String getPromedio() {
        return Promedio;
    }

    public void setPromedio(String promedio) {
        Promedio = promedio;
    }
}
