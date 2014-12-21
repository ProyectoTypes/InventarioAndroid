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
package type.proy.com.inventarioandroid.actividades;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import type.proy.com.inventarioandroid.R;
import type.proy.com.inventarioandroid.servicio.AutenticacionRepositorio;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClickBtnSoporte(View view)
    {
        Intent intentOrigen = getIntent();

        Intent intentDestino = new Intent("android.intent.action.ADMINISTRAR_SOPORTE");
        intentDestino.putExtra("url", intentOrigen.getStringExtra("url"));
        intentDestino.putExtra("user", intentOrigen.getStringExtra("user"));
        intentDestino.putExtra("pass", intentOrigen.getStringExtra("pass"));
        startActivity(intentDestino);

    }
    public void onClickBtnComputadora(View view)
    {
        Intent intentOrigen = getIntent();

        //FIXME: Llamar al activity computadora
        Intent intentDestino = new Intent("android.intent.action.ADMINISTRAR_SOPORTE");
        intentDestino.putExtra("url", intentOrigen.getStringExtra("url"));
        intentDestino.putExtra("user", intentOrigen.getStringExtra("user"));
        intentDestino.putExtra("pass", intentOrigen.getStringExtra("pass"));
        startActivity(intentDestino);

    }
    public void onClickBtnEstadisticas(View view)
    {
        Intent intentOrigen = getIntent();

        //FIXME: Llamar al activity estadisticas.
        Intent intentDestino = new Intent("android.intent.action.ADMINISTRAR_SOPORTE");
        intentDestino.putExtra("url", intentOrigen.getStringExtra("url"));
        intentDestino.putExtra("user", intentOrigen.getStringExtra("user"));
        intentDestino.putExtra("pass", intentOrigen.getStringExtra("pass"));
        startActivity(intentDestino);
    }
}
