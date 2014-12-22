package type.proy.com.inventarioandroid.actividades.computadora;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.databind.DeserializationFeature;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import type.proy.com.inventarioandroid.R;
import type.proy.com.inventarioandroid.dom.soporte.Computadora;
import type.proy.com.inventarioandroid.dom.soporte.Soportes;
import type.proy.com.inventarioandroid.servicio.RestLink;


public class ListaComputadoraActivity extends ActionBarActivity {
    private String url = "";
    private String user ="";
    private String pass ="";
    //FIXME: Utilizamos SOportes, si funciona habria que cambiar el nombre a uno mas generico
    private Soportes computadoras=null;
    private Computadora computadora = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_computadora);
        ListView lstComputadoras = (ListView) findViewById(R.id.lstComputadoras);
        //Obtenemos datos para el restful.
        Intent intent = getIntent();
        url =  intent.getStringExtra("url")+"services/computadora/actions/listAll/invoke";
        user =  intent.getStringExtra("user");
        pass =  intent.getStringExtra("pass");
        try {
            computadoras = new listAllComputadoraThread().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<RestLink> LinksComputadoraList = null;
        final List<String> list = new ArrayList<String>();
        if (computadoras !=null) {
            LinksComputadoraList = computadoras.getResult().getValue();
            //tomar nombres de los alumnos
            for (RestLink link : LinksComputadoraList) {
                list.add(link.getTitle());
                Log.v("Un &%%%%%%%%%%%%%%", link.toString());

            }
            //final List<String> list = new ArrayList<String>();
            //list.add("[Computadora]");
            //list.add(computadoras.getValue().getTitle());
        }
        //llenar la lista
        final StableArrayAdapter adapter = new StableArrayAdapter(getBaseContext(),
                android.R.layout.simple_list_item_1, list);
        lstComputadoras.setAdapter(adapter);
    }

    private class listAllComputadoraThread extends AsyncTask<Void, Void, Soportes> {
        @Override
        protected Soportes doInBackground(Void... params) {
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
                ResponseEntity<Soportes> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Soportes.class);


                Soportes computadoras = response.getBody();
                //if(soportes==null){
                //  Log.v("NULLLL ***********************************************************","NULL");
                //
                //                  return null;}
                //            Log.v("listado Soporte contiene", soportes.getResult().getValue().size() +"");
                //          int arraySize = soportes.getResult().getValue().size();
//
                //              RestLink[] array = new RestLink[arraySize];
                //            for (int i=0; i< arraySize;i++){
                //              array[i] = soportes.getResult().getValue().get(i);
                //            Log.v(" Encontrado", array[i].getTitle());
                //      }


                return computadoras;
            }
            catch (Exception e) {
                Log.e("main_activity", e.getMessage(), e);
            }
            return null;
        }
    }
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_computadora, menu);
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
