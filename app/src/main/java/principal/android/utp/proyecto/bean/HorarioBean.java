package principal.android.utp.proyecto.bean;

/**
 * Created by GRLIMA on 12/04/2017.
 */

public class HorarioBean {
    private String curso;
    private String seccion;
    private String Hora_Inicio;
    private String Hora_Fin;
    private String grado;

    public HorarioBean(String curso, String seccion, String hora_Inicio, String hora_Fin, String grado) {
        this.curso = curso;
        this.seccion = seccion;
        this.Hora_Inicio = hora_Inicio;
        this.Hora_Fin = hora_Fin;
        this.grado = grado;
    }

    public HorarioBean(){

    }
    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getHora_Inicio() {
        return Hora_Inicio;
    }

    public void setHora_Inicio(String hora_Inicio) {
        Hora_Inicio = hora_Inicio;
    }

    public String getHora_Fin() {
        return Hora_Fin;
    }

    public void setHora_Fin(String hora_Fin) {
        Hora_Fin = hora_Fin;
    }
}
