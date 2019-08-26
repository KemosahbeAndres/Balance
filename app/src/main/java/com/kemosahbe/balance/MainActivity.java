package com.kemosahbe.balance;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kemosahbe.balance.CustomViews.NoScrollListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    View item;
    LayoutInflater inflater;
    LinearLayout scrollerAvisos;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private AdaptadorListaCuentas adaptadorCuentas;
    private AdaptadorListaMovimientos adaptadorMovimientos;
    private NoScrollListView listaCuentas, listaMovimientos;
    public String CC[] = {"Efectivo", "Cuenta RUT", "Ahorro Premium"};
    public ArrayList<Movimiento> mm = new ArrayList<Movimiento>();

    public Log log;
    private final String TAG = "[MAIN ACTIVITY]";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.ftoolbar);
        toolbar.inflateMenu(R.menu.primary_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Movimiento m = new Movimiento();
        m.Descripcion="AAA";
        mm.add(m);
        m=null;

        m = new Movimiento();
        m.Descripcion="BBB";
        mm.add(m);
        m=null;

        m = new Movimiento();
        m.Descripcion="CCC";
        mm.add(m);
        m=null;

        log.d(TAG,"" + mm.get(1).Descripcion);

        setupListas();

        //setupTabs();

        //scrollerAvisos = findViewById(R.id.layoutAvisos);
        /*LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //ListView list = findViewById(R.id.listah);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        //list.setAdapter(adapter);
        RecyclerView lista = findViewById(R.id.RecyclerList);
        lista.setLayoutManager(manager);
        lista.setAdapter(adapter);*/

        /*for(int i=0; i <6; i++) {
            item = inflater.inflate(R.layout.view_recordatorio, scrollerAvisos, false);
            item.setId(i);
            item.setClickable(true);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"Identificador: " + view.getId(), Toast.LENGTH_SHORT).show();
                }
            });
            scrollerAvisos.addView(item);
        }
        scrollerAvisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Identificador: " + view.getId(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.primary_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.settings:
                Intent intento = new Intent(this, FormularioRegister.class);
                startActivityForResult(intento,01);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 01){
            switch (resultCode){
                case RESULT_CANCELED:
                    Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_OK:
                    Toast.makeText(this,"Aceptado", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public void setupListas(){
        listaCuentas = (NoScrollListView) findViewById(R.id.listViewCuen);
        listaMovimientos = (NoScrollListView) findViewById(R.id.listViewMov);
        adaptadorCuentas = new AdaptadorListaCuentas(this,CC);
        String lista[] = new String[mm.size()];
        for(int i=0;i==mm.size()-1;i++){
            lista[i] = mm.get(i).Descripcion;
        }
        adaptadorMovimientos = new AdaptadorListaMovimientos(this,mm,lista);
        listaCuentas.setAdapter(adaptadorCuentas);
        listaMovimientos.setAdapter(adaptadorMovimientos);
    }

    public void setupTabs(){
        //tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //  viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new TabMovimientos(), "Movimientos");
        viewPagerAdapter.addFragment(new TabCuentas(), "Cuentas");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        Context context;
        ArrayList<String> itemList;


        public RecyclerViewAdapter(Context context){
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_recordatorio,null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.txtDia.setText("24");
            viewHolder.txtMes.setText("Julio");
            viewHolder.txtDescripcion.setText("Blablabla.");
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtDia;
            TextView txtMes;
            TextView txtDescripcion;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtDia = itemView.findViewById(R.id.textoDia);
                txtMes = itemView.findViewById(R.id.textoMes);
                txtDescripcion = itemView.findViewById(R.id.textoDescripcion);
            }
        }

    }
}
