package principal.android.utp.proyecto.bean;

/**
 * Created by GRLIMA on 02/03/2017.
 */

public class UsuarioBean {
    private String Codigo;
    private String Password;
    private String Des_Nombre;
    private String Des_ApellidoPat;
    private String Des_ApeliidoMat;
    private String Tipo_Usuario;

    public UsuarioBean(String codigo, String password, String des_Nombre, String des_ApellidoPat, String des_ApeliidoMat, String Tipo_Usuario) {
        Codigo = codigo;
        Password = password;
        Des_Nombre = des_Nombre;
        Des_ApellidoPat = des_ApellidoPat;
        Des_ApeliidoMat = des_ApeliidoMat;
        Tipo_Usuario = Tipo_Usuario;
    }

    public UsuarioBean() {
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDes_Nombre() {
        return Des_Nombre;
    }

    public void setDes_Nombre(String des_Nombre) {
        Des_Nombre = des_Nombre;
    }

    public String getDes_ApellidoPat() {
        return Des_ApellidoPat;
    }

    public void setDes_ApellidoPat(String des_ApellidoPat) {
        Des_ApellidoPat = des_ApellidoPat;
    }

    public String getDes_ApeliidoMat() {
        return Des_ApeliidoMat;
    }

    public void setDes_ApeliidoMat(String des_ApeliidoMat) {
        Des_ApeliidoMat = des_ApeliidoMat;
    }

    public String getTipo_Usuario() {
        return Tipo_Usuario;
    }

    public void setTipo_Usuario(String tipo_Usuario) {
        Tipo_Usuario = tipo_Usuario;
    }
}
