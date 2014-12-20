package type.proy.com.inventarioandroid.actividades.soporte;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

import type.proy.com.inventarioandroid.R;
import type.proy.com.inventarioandroid.dom.soporte.Soporte;


public class SoporteActivity extends ActionBarActivity {
    private String url = "";
    private String user ="";
    private String pass ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);

        Button btnListarSoporte = (Button) findViewById(R.id.btnListarSoporte);
        //Obtenemos datos para el restful.
        Intent intent = getIntent();
        url =  intent.getStringExtra("url");
        user =  intent.getStringExtra("user");
        pass =  intent.getStringExtra("pass");

        try {
            soporte = new listAllSoporteThread().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private class listAllSoporteThread extends AsyncTask<Void, Void, Alumno> {
        @Override
        protected Soporte doInBackground(Void... params) {
            try {

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
