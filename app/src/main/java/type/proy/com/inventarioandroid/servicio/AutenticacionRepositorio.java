package type.proy.com.inventarioandroid.servicio;

import android.app.Activity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by munoz on 20/12/14.
 */
public class AutenticacionRepositorio {


    /**
     * Permite obtener una instancia de Autenticacion desde el archivo datosAutenticacion.json guardados en el celular.
     * En caso que no exista el archivo lo crea con valores nulos.
     * @param activity
     * @return
     */
    public Autenticacion autenticar(Activity activity)
    {
        Autenticacion autenticacion = new Autenticacion();
        String json = leerJSON(activity);
        try
        {
            JSONObject obj      = new JSONObject(json);
            String usuario      = obj.getString("usuario");
            String password     = obj.getString("password");
            String servidor     = obj.getString("servidor");
            String puerto       = obj.getString("puerto");
            String directorio   = obj.getString("directorio");
            Boolean guardar      = Boolean.valueOf(obj.getString("guardar"));
            autenticacion.setUsuario(usuario);
            autenticacion.setPassword(password);
            autenticacion.setServidor(servidor);
            autenticacion.setPuerto(puerto);
            autenticacion.setDirectorio(directorio);
            autenticacion.setGuardar(guardar);

        }
        catch (JSONException e) {
             e.printStackTrace();
        }

        return autenticacion;
    }

    /**
     * Se encarga de leer el archivo datosAutenticacion.json. Devuelve un String con los datos
     * para autenticarse con el fin de  utilizar los servicios restful.
     * @param activity
     * @return
     */
    private String leerJSON(Activity activity)
    {
        String json = null;

        Activity a = new Activity();
        try {
            FileInputStream fis = activity.openFileInput("datosAutenticacion.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            json = sb.toString();

        } catch (FileNotFoundException e) {
            json =  "{ " +
                    "\"urlRestful\" : \"\",  " +
                    "\"usuario\" : \"\",  " +
                    "\"password\" : \"\",  " +
                    "\"guardar\" : \"false\"," +
                    "\"servidor\" : \"\",  " +
                    "\"puerto\" : \"8080\",  " +
                    "\"directorio\" : \"/restful\"  " +
                    "}";
            e.printStackTrace();
            Log.v("JSON", "Creando Archivo en blanco");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
