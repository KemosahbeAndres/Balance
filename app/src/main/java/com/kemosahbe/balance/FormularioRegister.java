package com.kemosahbe.balance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class FormularioRegister extends AppCompatActivity {

    TextView tituloToolbar;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        toolbar = (Toolbar) findViewById(R.id.tool_layout);

        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        getSupportActionBar().setTitle("");
        tituloToolbar = (TextView) findViewById(R.id.tituloToolbar);
        tituloToolbar.setText("Nueva Transaccion");
        tituloToolbar.setTextSize(20);

        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "Cancelado");
                setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        });
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aceptar:
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "Aceptado");
                setResult(RESULT_OK, resultIntent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
