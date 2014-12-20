package type.proy.com.inventarioandroid.servicio;

/**
 * Created by munoz on 20/12/14.
 *
 */
public class Autenticacion {

    private String usuario;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String servidor;

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }
    private String puerto;

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    private String directorio;

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    private Boolean guardar;
    public Boolean getGuardar() {
        return guardar;
    }

    public void setGuardar(Boolean guardar) {
        this.guardar = guardar;
    }

    public String getUri(){

        String servidor = getServidor();
        String puerto = getPuerto();
        String directorio = getDirectorio();
        if (servidor != null) {
            if (servidor.isEmpty()) {
                return "";
            }
            if (puerto.isEmpty()){
                puerto = "8080";
            }

            if (directorio.isEmpty()){
                directorio = "/restful";
            }

            String url = "http://" + servidor + ":" + puerto + directorio + "/";

            return url;
        }
        return "";
    }


}
