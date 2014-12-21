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
 */package type.proy.com.inventarioandroid.actividades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import type.proy.com.inventarioandroid.R;
import type.proy.com.inventarioandroid.servicio.Autenticacion;
import type.proy.com.inventarioandroid.servicio.AutenticacionRepositorio;


public class RegistrarActivity extends ActionBarActivity {
    private String url;
    private String puerto;
    private String directorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        //Obteniendo datos del activity padre.
        Intent intent = getIntent();
        url =  intent.getStringExtra("servidor");
        puerto =  intent.getStringExtra("puerto");
        directorio =  intent.getStringExtra("directorio");

        //Obteniendo Componentes.
        final EditText txtUrlServidor = (EditText) findViewById(R.id.txtUrlServidor);
        final EditText txtPuerto = (EditText) findViewById(R.id.txtUsuario);
        final EditText txtDirectorio = (EditText) findViewById(R.id.txtContrasena);

        //Seteando los componentes vacios con los datos recibidos por el activity padre.
        txtUrlServidor.setText(url);
        txtPuerto.setText(puerto);
        txtDirectorio.setText(directorio);

        Button btnModificar = (Button) findViewById(R.id.btnModificarDatosAutenticacion);
        final Activity activity = this;
        btnModificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AutenticacionRepositorio autenticacionRepositorio = new AutenticacionRepositorio();
                final Autenticacion autenticar = autenticacionRepositorio.datosAutenticacion(activity);
                autenticar.setUsuario("");
                autenticar.setPassword("");
                autenticar.setGuardar(false);
                autenticar.setServidor(txtUrlServidor.getText().toString());
                autenticar.setPuerto(txtPuerto.getText().toString());
                autenticar.setDirectorio(txtDirectorio.getText().toString());
                autenticacionRepositorio.guardarDatosAutenticacion(activity, autenticar);
                Intent returnIntent = new Intent();
                if(!autenticar.getServidor().isEmpty()) {
                    returnIntent.putExtra("login", true);
                    setResult(RESULT_OK);
                }
                else {
                    returnIntent.putExtra("login", false);
                    setResult(RESULT_CANCELED);
                }
                    finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar, menu);
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
