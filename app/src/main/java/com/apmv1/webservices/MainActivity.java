package com.apmv1.webservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creación del fragmento principal
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment(), "MainFragment")
                    .commit();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constantes.CODIGO_DETALLE || requestCode == 3) {
            if (resultCode == RESULT_OK || resultCode == 203) {
                MainFragment fragment = (MainFragment) getSupportFragmentManager().
                        findFragmentByTag("MainFragment");
                fragment.cargarAdaptador();
            }
        }
    }
}
