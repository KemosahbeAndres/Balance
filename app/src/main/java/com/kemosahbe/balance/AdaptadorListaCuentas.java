package com.kemosahbe.balance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorListaCuentas extends ArrayAdapter<String> {
    private Context context;
    private String Titulos[];
    public Log log;
    private final String TAG = "[ACCOUNT ADAPTER]";

    public AdaptadorListaCuentas(@NonNull Context context, @NonNull String titulos[]) {
        super(context,R.layout.view_cuentas,R.id.Titulo_Cuenta,titulos);
        this.context = context;
        this.Titulos = titulos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cuenta = inflater.inflate(R.layout.view_cuentas,parent,false);
        TextView titulo = cuenta.findViewById(R.id.Titulo_Cuenta);
        titulo.setText(Titulos[position]);
        log.d(TAG,"Insertando titulo: " + Titulos[position]);
        return cuenta;
    }
}

