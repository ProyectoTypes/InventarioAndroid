package type.proy.com.inventarioandroid.actividades.soporte;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import type.proy.com.inventarioandroid.R;


public class AbmSoporteActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abm_soporte);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_abm_soporte, menu);
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
    public void onClickBtnComputadora(View view)
    {
        Intent intent = new Intent("android.intent.action.VER_SOPORTES");
        intent.putExtra("url", intent.getStringExtra("url"));
        intent.putExtra("user", intent.getStringExtra("user"));
        intent.putExtra("pass", intent.getStringExtra("pass"));
        startActivity(intent);

    }
}