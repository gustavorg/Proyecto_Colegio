package principal.android.utp.proyecto;

import android.app.Application;

/**
 * Created by GRLIMA on 01/04/2017.
 */

public class ApplicationApp extends Application
{
    private String nombre;
    private String codigo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
