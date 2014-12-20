package type.proy.com.inventarioandroid.actividades;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

import type.proy.com.inventarioandroid.R;
import type.proy.com.inventarioandroid.servicio.Autenticacion;
import type.proy.com.inventarioandroid.servicio.AutenticacionRepositorio;
import type.proy.com.inventarioandroid.servicio.RestLink;


public class LoginActivity extends ActionBarActivity {
    private Autenticacion autenticacion;
    private String error="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Creando instancias de los componentes del activity.
        final EditText usuario = (EditText) findViewById(R.id.txtPuerto);
        final EditText pass = (EditText) findViewById(R.id.txtDirectorio);
        final Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final CheckBox ckbGuardarPass = (CheckBox) findViewById(R.id.ckbContrasena);


        //Obtenemos los valores del datosAutenticacion.json en una instancia de Autenticacion.
        AutenticacionRepositorio autenticacionRepositorio = new AutenticacionRepositorio();
        autenticacion = autenticacionRepositorio.datosAutenticacion(this);
        //Llenamos los txtbox con los datos guardados en el archivo
        usuario.setText(autenticacion.getUsuario());
        pass.setText(autenticacion.getPassword());
        ckbGuardarPass.setChecked(autenticacion.getGuardar());
        //Chequeamos si los valores son nulos, habilitar boton Registrarse y deshabilitar login.Viceversa
        if(!autenticacion.getUri().isEmpty())
        {
            btnRegistrar.setText("Configurar URL");
            btnLogin.setEnabled(true);
        }
        else
        {
            btnLogin.setEnabled(false);
        }

    }

    /**
     * Muestra un nuevo activity en donde se puedan ingresar los datos de registro, mas
     * la url al restful.
     * @param view
     */
    public void onClickBtnRegistrar(View view)
    {
        final Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        Intent intent = new Intent("android.intent.action.REGISTRAR_URL");
        AutenticacionRepositorio autenticacionRepositorio = new AutenticacionRepositorio();
        autenticacion = autenticacionRepositorio.datosAutenticacion(this);
        intent.putExtra("servidor", autenticacion.getServidor());
        intent.putExtra("puerto", autenticacion.getPuerto());
        intent.putExtra("directorio", autenticacion.getDirectorio());
        startActivityForResult(intent, 1);


    }

    /**
     * Muestra el activity Main.
     * @param view
     */
    public void onClickBtnLoguearse(View view)
    {
        final EditText usuario = (EditText) findViewById(R.id.txtPuerto);
        final EditText pass = (EditText) findViewById(R.id.txtDirectorio);
        final CheckBox ckbGuardarPass = (CheckBox) findViewById(R.id.ckbContrasena);
        Autenticacion nuevaAutenticacion = new Autenticacion();
        if (usuario.getText().toString().isEmpty()){

            generarAlerta("El campo \"usuario\" no puede estar en blanco");
            return;
        }

        if (pass.getText().toString().isEmpty()){

            generarAlerta("El campo \"contraseña\" no puede estar en blanco");
            return;
        }

        autenticacion.setUsuario(usuario.getText().toString());
        if(!ckbGuardarPass.isChecked())
        {
            autenticacion.setPassword("");
            autenticacion.setGuardar(false);

        }
        else {
            autenticacion.setPassword(pass.getText().toString());
            autenticacion.setGuardar(true);
        }
        //check connection

        RestLink restLinks= null;
        try {
            restLinks = new verificarLoginThread().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (restLinks == null){
            generarAlerta(error);
            return;
        }
        AutenticacionRepositorio autenticacionRepositorio = new AutenticacionRepositorio();
        autenticacionRepositorio.guardarDatosAutenticacion(this,nuevaAutenticacion);

        Intent intent = new Intent("android.intent.action.MAIN");

        intent.putExtra("url", autenticacion.getUri());
        intent.putExtra("usuario", autenticacion.getUsuario());
        intent.putExtra("contrasena", autenticacion.getPassword().toString());

        startActivity(intent);


    }

    /**
     * Maneja los datos que el devuelve el Activity hijo.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
               btnLogin.setEnabled(true);
                btnRegistrar.setText("Configurar URL");
            }
            if (resultCode == RESULT_CANCELED) {
                btnLogin.setEnabled(false);
                btnRegistrar.setText("Registrar URL");

            }
        }
    }

    /**
     * Muestra una pequeña ventana en el celular con un mensaje enviado por parametro.
     * @param text
     */
    private void generarAlerta(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    private class verificarLoginThread  extends AsyncTask<Void, Void, RestLink> {
        @Override
        protected RestLink doInBackground(Void... params) {
            try {
//Services services = null;
                //Log.v("ingresando User y Pass", user + " : " + pass);
                // Set the username and password for creating a Basic Auth request
                HttpAuthentication authHeader = new HttpBasicAuthentication(autenticacion.getUsuario(), autenticacion.getPassword());
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

                Log.v("ingresando URL",autenticacion.getUri());
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                // Make the HTTP GET request to the Basic Auth protected URL

                ResponseEntity<RestLink> response = null;

                response = restTemplate.exchange(autenticacion.getUri(), HttpMethod.GET, requestEntity, RestLink.class);


                //return response.getBody();

                RestLink restLinks = response.getBody();

                Log.v("leido", restLinks.getLinks().size()+"");


                return restLinks;

            } catch (Exception e) {
                if (e.getMessage().contains("401")) {
                    error = "Nombre de usuario o contraseña incorrectos.";
                } else {
                    error = "No se puede acceder al servidor. Verifique la dirección.";
                }
                Log.e("Error", "Error de conexion");

            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
