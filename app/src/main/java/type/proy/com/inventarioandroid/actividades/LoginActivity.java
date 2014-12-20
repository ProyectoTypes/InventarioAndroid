package type.proy.com.inventarioandroid.actividades;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import type.proy.com.inventarioandroid.R;
import type.proy.com.inventarioandroid.servicio.Autenticacion;
import type.proy.com.inventarioandroid.servicio.AutenticacionRepositorio;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("onCreate", "INICIANDO");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Creando instancias de los componentes del activity.
        final EditText usuario = (EditText) findViewById(R.id.txtUsuario);
        final EditText pass = (EditText) findViewById(R.id.txtContrasena);
        final CheckBox ckbGuardarPass = (CheckBox) findViewById(R.id.ckbContrasena);
        final Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);

        //Obtenemos los valores del datosAutenticacion.json en una instancia de Autenticacion.
        AutenticacionRepositorio autenticacionRepositorio = new AutenticacionRepositorio();
        Autenticacion autenticacion = autenticacionRepositorio.autenticar(this);
        //Chequeamos si los valores son nulos, habilitar boton Registrarse y deshabilitar login. Registrarse muestra nueva ventana.
        //Si los valores no son nulos, habilitar login y deshabilitar Registrarse. Login muestra ventana main.
        if(!autenticacion.getUsuario().isEmpty() && !autenticacion.getPassword().isEmpty())
        {
            Log.v("onCreate", "----------"+ autenticacion.getUsuario() +"---------------");
            Log.v("onCreate", "----------"+ autenticacion.getPassword() +"---------------");
            btnRegistrar.setEnabled(false);
            btnLogin.setEnabled(true);
            usuario.setText("habria que permitir cambiar datos.");


        }
        else
        {
            usuario.setText("NO LOGIN. NO HAY DATOS");
            btnRegistrar.setEnabled(true);
            btnLogin.setEnabled(false);
        }



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
