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
package type.proy.com.inventarioandroid.actividades.soporte;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
import type.proy.com.inventarioandroid.dom.soporte.Soporte;
import type.proy.com.inventarioandroid.servicio.RestLink;


public class SoporteActivity extends ActionBarActivity {
    private String url="";
    private String user="";
    private String pass="";
    private Soporte soporte =null;
    private String urlComputadora ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);
        Intent intent = getIntent();
        url =  intent.getStringExtra("url");
        user =  intent.getStringExtra("user");
        pass =  intent.getStringExtra("pass");


        EditText txtObservaciones = (EditText) findViewById(R.id.mltxtObservaciones);
        EditText dtFecha = (EditText) findViewById(R.id.dtFechaSoporte);
        TextView lblEstado = (TextView)findViewById(R.id.lblEstado);
        TextView lblIDComputadora = (TextView)findViewById(R.id.lblIDComputadora);
        TextView lblTecnico = (TextView)findViewById(R.id.lblTecnicoSoporte);
        EditText txtIpComputadora = (EditText)findViewById(R.id.txtIPComputadora);
        EditText txtTecnicoSoporte = (EditText)findViewById(R.id.txtTecnicoSoporte);


        try {
            soporte = new getSoporteThread().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        txtObservaciones.setText(soporte.getMembers().getObservaciones().getValue());
        lblEstado.setText(lblEstado.getText()+" "+soporte.getMembers().getEstado().getValue().getTitle());
        //llenar la lista
        txtIpComputadora.setText(soporte.getMembers().getComputadora().getValue().getTitle());
        if(soporte.getMembers().getTecnico().getValue()!=null)
            txtIpComputadora.setText(soporte.getMembers().getTecnico().getValue().getTitle());
        else
            txtIpComputadora.setText("SIN ASIGNAR");

        dtFecha.setText(soporte.getMembers().getFecha().getValue());

        //Log.v("*********************USUARIO DE COMPUTADORA",soporte.getMembers().getComputadora().getMembers().getTecnico().getValue().getTitle());



    }
    public void MostrarComputadora(View view)
    {
        Intent newIntent = new Intent("android.intent.action.COMPUTADORA");
        newIntent.putExtra("user",user);
        newIntent.putExtra("pass",pass);
        Log.v("MADAFAKAAAAAAA",soporte.getMembers().getComputadora().getValue().getHref());

        newIntent.putExtra("url",soporte.getMembers().getComputadora().getValue().getHref());
        startActivity(newIntent);


    }

    private class getSoporteThread extends AsyncTask<Void, Void, Soporte> {
        @Override
        protected Soporte doInBackground(Void... params) {
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
                ResponseEntity<Soporte> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Soporte.class);

                //return response.getBody();

                Soporte  unSoporte = response.getBody();

                //Log.v("leido", restLinks.getLinks().size()+"");



                return unSoporte;

            } catch (Exception e) {
                Log.e("main_activity", e.getMessage(), e);
            }

            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_soporte, menu);
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
