package com.kemosahbe.balance.data;

import android.content.ContentValues;

public class Transaction {
    public enum TransactionType {
        Cargo,
        Giro
    }
    public String ID;
    public String FECHA;
    public String DESCRIPCION;
    public String CUENTA;
    public int MONTO;
    public String TIPO;

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();

        return values;
    }
}
