package type.proy.com.inventarioandroid.servicio;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by munoz on 20/12/14.
 */
public class AutenticacionRepositorio {


    /**
     * Permite obtener una instancia de Autenticacion desde el archivo datosAutenticacion.json guardados en el celular.
     *
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
     * para autenticarse con el fin de  utilizar los servicios restful.Si el archivo no existe,
     * devuelve un string con el formato de json pero con la variables en null.
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

    /**
     * Permite guardar los datos de autenticacion, como usuario, pass, uri, etc. en un archivo json
     * llamado datosAutenticacion, almacenado en el celular.
     * @param activity
     * @param autenticacion
     */
    public void guardarDatosAutenticacion(Activity activity,Autenticacion autenticacion)
    {
        JSONObject obj = new JSONObject();

        try
        {
            obj.put("usuario", autenticacion.getUsuario());
            obj.put("password", autenticacion.getPassword());
            obj.put("servidor", autenticacion.getServidor());
            obj.put("puerto", autenticacion.getPuerto());
            obj.put("directorio", autenticacion.getDirectorio());
            obj.put("guardar", autenticacion.getGuardar());

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream file = activity.openFileOutput("datosAutenticacion.json", Context.MODE_PRIVATE);
            file.write(obj.toString().getBytes());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
