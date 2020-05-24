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

import com.kemosahbe.balance.Activitys.FormularioRegister;
import com.kemosahbe.balance.CustomViews.NoScrollListView;
import com.kemosahbe.balance.Fragments.TabCuentas;
import com.kemosahbe.balance.Fragments.TabMovimientos;
import com.kemosahbe.balance.Objetos.AdaptadorListaCuentas;
import com.kemosahbe.balance.Objetos.AdaptadorListaMovimientos;
import com.kemosahbe.balance.data.*;
import com.kemosahbe.balance.Objetos.ViewPagerAdapter;
import com.kemosahbe.balance.data.Transaction;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

public class MainActivity extends AppCompatActivity implements MICentralEventListener{

    //Definiciones para los eventos de la actividad.
    private MICentral central;
    private OnReloadPageEventListener reloadPageListener;
    private OnSaveTransactionEventListener saveTransactionListener;
    //public MainActivity activity;

    private View item;
    private LayoutInflater inflater;
    private LinearLayout scrollerAvisos;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private AdaptadorListaCuentas adaptadorCuentas;
    private AdaptadorListaMovimientos adaptadorMovimientos;
    private NoScrollListView listaCuentas, listaMovimientos;
    public String CC[] = {"Efectivo", "Cuenta RUT", "Ahorro Premium"};
    public ArrayList<Transaction> mm = new ArrayList<>();
    public ArrayList<ArrayList<String>> matriz = new ArrayList<>();

    public Log log;
    private final String TAG = "[MAIN ACTIVITY]";

    @Override
    public void nothing(CentralEvent event, View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.ftoolbar);
        toolbar.inflateMenu(R.menu.primary_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //setReloadPageEventListener(central);

        //central.Begin(this);
        Log.d("MainActivity","Creating DB.");
        DBController db = DBController.Instance(this);
        //setupListas();

        //toolbar.setOnMenuItemClickListener(central);
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

    public void setReloadPageEventListener(OnReloadPageEventListener listener){
        this.reloadPageListener = listener;
    }

    public void setupListas(){
        listaCuentas = (NoScrollListView) findViewById(R.id.listViewCuen);
        listaMovimientos = (NoScrollListView) findViewById(R.id.listViewMov);
        adaptadorCuentas = new AdaptadorListaCuentas(this,CC);
        String lista[] = new String[mm.size()];
        for(int i=0;i==mm.size()-1;i++){
            lista[i] = mm.get(i).DESCRIPCION;
        }
        //adaptadorMovimientos = new AdaptadorListaMovimientos(this,mm,lista);
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



//    ********** INTERFACE LISTENERS **********
//Interface publica para los listeners del evento recargar pagina.
interface OnReloadPageEventListener extends EventListener{
    public void OnReloadPage(ReloadPageEvent event);
}
//Interface publica para el listener del evento guardar transaccion.
interface OnSaveTransactionEventListener extends EventListener{
    public void OnSaveTransaction(SaveTransactionEvent event);
}
interface OnActivityCreatedEventListener extends EventListener{
    public void OnActivityCreated(ActivityCreatedEvent event);
}



//    ********** CLASS EVENT OBJECT **********
//Clase para los objetos del evento Recargar Pagina.
class ReloadPageEvent extends EventObject{
    public final String id = "[Reload Page Event]";
    public ReloadPageEvent(Object source) { super(source); }
}
//Clase para los objetos del evento Guardar Transaccion.
class SaveTransactionEvent extends EventObject{
    public final String id = "[Save Transaction Event]";
    private Transaction transaccion;
    public SaveTransactionEvent(Object source, Transaction transaction){
        super(source);
        this.transaccion = transaction;
    }
    public Transaction getTransaction(){
        return this.transaccion;
    }
}
class ActivityCreatedEvent extends EventObject{
    public final String id = "[Activity Created Event]";
    public ActivityCreatedEvent(Object source){
        super(source);
    }
}