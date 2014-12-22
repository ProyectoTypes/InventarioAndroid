/*
 * This is a software made for inventory control
 *
 * Copyright (C) 2014, ProyectoTypes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 *
 *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package type.proy.com.inventarioandroid.actividades.computadora;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

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


public class ComputadoraActivity extends ActionBarActivity {
    private String url="";
    private String user ="";
    private String pass ="";
    private String error="";
    private Computadora computadora =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computadora);
        ListView lstComputadora = (ListView)findViewById(R.id.lstComputadora);
        Intent intent = getIntent();
        url =  intent.getStringExtra("url");
        user =  intent.getStringExtra("user");
        pass =  intent.getStringExtra("pass");
        try {
            computadora = new getComputadoraThread().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(computadora==null) {
            this.generarAlerta(error);
            return;
        }
        //String[] memoriaArray = computadora.getMembers().getMemoria().getValue().getTitle().split("-");
        //Log.v("COPMUTADORA DATOS", computadora.getMembers().getMemoria().getValue().getTitle());

        //List<RestLink> LinksSoportesList = null;
        JsonObject json = new JsonObject();
        json.addProperty("foo", "bar");

        Ion.with(getBaseContext())
                .load("http://example.com/post")
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                    }
                });
        final List<String> list = new ArrayList<String>();
        list.add("[Nombre del Equipo]");
        list.add( computadora.getMembers().getNombreEquipo().getValue());
        list.add("[Usuario]");
        list.add( computadora.getMembers().getUsuario().getValue().getTitle());
        list.add("[Placa de Red]");
        list.add( computadora.getMembers().getPlacaDeRed().getValue().getTitle());
        list.add("[Motherboard]");
        list.add( computadora.getMembers().getMotherboard().getValue().getTitle());
        list.add("[Procesador]");
        list.add( computadora.getMembers().getProcesador().getValue().getTitle());
        list.add("[Memoria]");
        list.add( computadora.getMembers().getMemoria().getValue().getTitle());
        if(computadora.getMembers().getTecnico().getValue()!=null){
            list.add("[Tecnico]");
            list.add( computadora.getMembers().getTecnico().getValue().getTitle());
        }
        list.add("[Disco]");
        list.add( computadora.getMembers().getDisco().getValue().getTitle());
        if(computadora.getMembers().getImpresora().getValue()!=null){
            list.add("[Impresora]");
            list.add( computadora.getMembers().getImpresora().getValue().getTitle());
        }
        //llenar la lista
        final StableArrayAdapter adapter = new StableArrayAdapter(getBaseContext(),
                android.R.layout.simple_list_item_1, list);
        lstComputadora.setAdapter(adapter);
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
    private class getComputadoraThread extends AsyncTask<Void, Void, Computadora> {
        @Override
        protected Computadora doInBackground(Void... params) {
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
                ResponseEntity<Computadora> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Computadora.class);

                //return response.getBody();

                Computadora  unaComputadora = response.getBody();
                //if(unaComputadora!=null)
                  //  Log.v("leido VALORES DE LA COMPUTADORA", unaComputadora.getValue()+"");



                return unaComputadora;

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_computadora, menu);
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
