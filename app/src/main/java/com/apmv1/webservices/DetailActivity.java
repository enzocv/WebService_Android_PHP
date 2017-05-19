package com.apmv1.webservices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Beto on 10/07/2015.
 */
public class DetailActivity extends AppCompatActivity{
    /**
     * Instancia global de la meta a detallar
     */
    private String idMeta;

    /**
     * Inicia una nueva instancia de la actividad
     *
     * @param activity Contexto desde donde se lanzará
     * @param idMeta   Identificador de la meta a detallar
     */
    public static void launch(Activity activity, String idMeta) {
        Intent intent = getLaunchIntent(activity, idMeta);
        activity.startActivityForResult(intent, Constantes.CODIGO_DETALLE);
    }

    /**
     * Construye un Intent a partir del contexto y la actividad
     * de detalle.
     *
     * @param context Contexto donde se inicia
     * @param idMeta  Identificador de la meta
     * @return Intent listo para usar
     */
    public static Intent getLaunchIntent(Context context, String idMeta) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constantes.EXTRA_ID, idMeta);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            // Dehabilitar titulo de la actividad
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            // Setear ícono "X" como Up button
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_close);
        }

        // Retener instancia
        if (getIntent().getStringExtra(Constantes.EXTRA_ID) != null)
            idMeta = getIntent().getStringExtra(Constantes.EXTRA_ID);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, DetailFragment.createInstance(idMeta), "DetailFragment")
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constantes.CODIGO_ACTUALIZACION) {
            if (resultCode == RESULT_OK) {
                DetailFragment fragment = (DetailFragment) getSupportFragmentManager().
                        findFragmentByTag("DetailFragment");
                fragment.cargarDatos();

                setResult(RESULT_OK); // Propagar código para actualizar
            } else if (resultCode == 203) {
                setResult(203);
                finish();
            } else {
                setResult(RESULT_CANCELED);
            }
        }
    }
}
