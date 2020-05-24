package com.kemosahbe.balance.Objetos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kemosahbe.balance.data.*;
import com.kemosahbe.balance.R;

import java.util.ArrayList;

public class AdaptadorListaMovimientos extends ArrayAdapter<String> {
    private Context context;
    public ArrayList<Transaction> movimientos;
    public Log log;
    private final String TAG = "[TRANSACTION ADAPTER]";
    private String s[];

    public AdaptadorListaMovimientos(@NonNull Context context, @NonNull ArrayList<Transaction> mov, @NonNull String titulos[]) {
        super(context, R.layout.view_movimiento,R.id.txtDescripcion,titulos);
        this.context = context;
        this.movimientos = new ArrayList<Transaction>();
        this.movimientos = mov;
        log.d(TAG,"Constructor: " + movimientos.get(0).DESCRIPCION);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View movimiento = inflater.inflate(R.layout.view_movimiento, parent,false);
        TextView desc = movimiento.findViewById(R.id.txtDescripcion);
        desc.setText(movimientos.get(position).DESCRIPCION);
        log.d(TAG, "Insertando Movimiento: " + movimientos.get(position).DESCRIPCION);
        return movimiento;
    }
}
