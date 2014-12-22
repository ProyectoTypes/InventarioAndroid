package type.proy.com.inventarioandroid.actividades.sector;

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
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.DeserializationFeature;

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
import type.proy.com.inventarioandroid.dom.soporte.Computadora;
import type.proy.com.inventarioandroid.dom.soporte.Sector;


public class BuscarSectorActivity extends ActionBarActivity {
    private String url="";
    private String user ="";
    private String pass ="";
    private String error="";
    private Sector sector =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_sector);
        Intent intent = getIntent();
        url =  intent.getStringExtra("url");
        user =  intent.getStringExtra("user");
        pass =  intent.getStringExtra("pass");
        Button btnBuscarNombreSector = (Button) findViewById(R.id.btnBuscarNombreSector);
        EditText txtBuscarNombreSector = (EditText) findViewById(R.id.txtBuscarNombreSector);
        try {
            sector = new getSectorThread().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(sector==null) {
            this.generarAlerta(error);
            return;
        }


    }
    private class getSectorThread extends AsyncTask<Void, Void, Sector> {
        @Override
        protected Sector doInBackground(Void... params) {
            try {


                //Services services = null;
                Log.v("ingresando User y Pass", user + " : " + pass);
                // Set the username and password for creating a Basic Auth request
                HttpAuthentication authHeader = new HttpBasicAuthentication(user, pass);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

                Log.v("ingresando URL",url);
                RestTemplate restTemplate = new RestTemplate();

                MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                converter.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                restTemplate.getMessageConverters().add(converter);

                // Make the HTTP GET request to the Basic Auth protected URL
                ResponseEntity<Sector> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Sector.class);

                //return response.getBody();

                Sector  obj = response.getBody();
                //if(unaComputadora!=null)
                //  Log.v("leido VALORES DE LA COMPUTADORA", unaComputadora.getValue()+"");



                return obj;

            }catch (Exception e) {
                if (e.getMessage().contains("401")) {
                    error = "Nombre de usuario o contraseña incorrectos.";
                } else {
                    error = "No se puede acceder al servidor. Verifique la dirección.";
                }
                Log.e("Error", "Error de conexion"+e.getMessage());

            }
            return null;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buscar_sector, menu);
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
    public void onClickBtnAceptarBusquedaSector(View view)
    {
        Intent intentOrigen = getIntent();

        //FIXME: Llamar al activity estadisticas.
        Intent intentDestino = new Intent("android.intent.action.MODIFICAR_SECTOR");
        intentDestino.putExtra("user", intentOrigen.getStringExtra("user"));
        intentDestino.putExtra("pass", intentOrigen.getStringExtra("pass"));
        intentDestino.putExtra("nombre", sector.getValue().getTitle());
        intentDestino.putExtra("url", sector.getValue().getHref());

        startActivity(intentDestino);
    }
}
