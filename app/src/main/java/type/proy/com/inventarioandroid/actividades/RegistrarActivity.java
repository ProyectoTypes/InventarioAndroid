package type.proy.com.inventarioandroid.actividades;

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
        final EditText txtPuerto = (EditText) findViewById(R.id.txtPuerto);
        final EditText txtDirectorio = (EditText) findViewById(R.id.txtDirectorio);

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
                autenticar.setServidor(txtUrlServidor.getText().toString());
                autenticar.setPuerto(txtPuerto.getText().toString());
                autenticar.setDirectorio(txtDirectorio.getText().toString());
                autenticacionRepositorio.guardarDatosAutenticacion(activity, autenticar);

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
