package principal.android.utp.proyecto.bean.Docente;

/**
 * Created by GRLIMA on 25/03/2017.
 */

public class DocenteCursoSeccionBean {

    private String codigo_curso;
    private String codigo_docente;
    private String nom_curso;
    private String dia;
    private String grado;
    private String seccion;
    private String turno;
    private String hora_inicio;
    private String hora_fin;

    public DocenteCursoSeccionBean(String codigo_curso,String codigo_docente, String nom_curso, String dia, String grado, String seccion, String turno, String hora_inicio, String hora_fin) {
        this.setNom_curso(nom_curso);
        this.setDia(dia);
        this.setGrado(grado);
        this.setSeccion(seccion);
        this.setTurno(turno);
        this.setHora_inicio(hora_inicio);
        this.setHora_fin(hora_fin);
        this.setCodigo_docente(codigo_docente);
        this.setCodigo_curso(codigo_curso);
    }

    public DocenteCursoSeccionBean() {
    }

    public String getCodigo_curso() {
        return codigo_curso;
    }

    public void setCodigo_curso(String codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    public String getNom_curso() {
        return nom_curso;
    }

    public void setNom_curso(String nom_curso) {
        this.nom_curso = nom_curso;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
    }
}
