package type.proy.com.inventarioandroid.actividades;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import type.proy.com.inventarioandroid.R;
import type.proy.com.inventarioandroid.servicio.Autenticacion;
import type.proy.com.inventarioandroid.servicio.AutenticacionRepositorio;


public class LoginActivity extends ActionBarActivity {
    private Autenticacion autenticacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Creando instancias de los componentes del activity.
        final EditText usuario = (EditText) findViewById(R.id.txtPuerto);
        final EditText pass = (EditText) findViewById(R.id.txtDirectorio);
        final Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);

        //Obtenemos los valores del datosAutenticacion.json en una instancia de Autenticacion.
        AutenticacionRepositorio autenticacionRepositorio = new AutenticacionRepositorio();
        autenticacion = autenticacionRepositorio.datosAutenticacion(this);
        //Chequeamos si los valores son nulos, habilitar boton Registrarse y deshabilitar login.Viceversa
        if(!autenticacion.getUsuario().isEmpty())
        {
            btnRegistrar.setEnabled(true);
            btnRegistrar.setText("Modificar Url");
            btnLogin.setEnabled(true);
        }
        else
        {
            usuario.setText("NO LOGIN. NO HAY DATOS");
            btnRegistrar.setEnabled(true);
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
        Intent intent = new Intent("android.intent.action.REGISTRAR_DATOS");
        intent.putExtra("servidor", autenticacion.getServidor());
        intent.putExtra("puerto", autenticacion.getPuerto());
        intent.putExtra("directorio", autenticacion.getDirectorio());
        startActivity(intent);

    }

    /**
     * Muestra el activity Main.
     * @param view
     */
    public void onClickBtnLoguearse(View view)
    {
        final CheckBox ckbGuardarPass = (CheckBox) findViewById(R.id.ckbContrasena);
        Autenticacion nuevaAutenticacion = new Autenticacion();
        nuevaAutenticacion.setUsuario(autenticacion.getUsuario());
        nuevaAutenticacion.setDirectorio(autenticacion.getDirectorio());
        nuevaAutenticacion.setPuerto(autenticacion.getPuerto());
        nuevaAutenticacion.setServidor(autenticacion.getServidor());
        if(!ckbGuardarPass.isChecked())
        {
            nuevaAutenticacion.setPassword("");
            nuevaAutenticacion.setGuardar(false);

        }
        else {
            nuevaAutenticacion.setPassword(autenticacion.getPassword());
            nuevaAutenticacion.setGuardar(true);
        }
        AutenticacionRepositorio autenticacionRepositorio = new AutenticacionRepositorio();
        autenticacionRepositorio.guardarDatosAutenticacion(this,nuevaAutenticacion);

        Intent intent = new Intent("android.intent.action.MAIN");

        intent.putExtra("url", autenticacion.getUri());
        intent.putExtra("usuario", autenticacion.getUsuario());
        intent.putExtra("contrasena", autenticacion.getPassword().toString());

        startActivity(intent);
        this.toString("Operación Exitosa");

    }

    private void toString(CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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
