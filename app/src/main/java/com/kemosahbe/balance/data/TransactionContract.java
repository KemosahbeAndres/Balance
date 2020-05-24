package com.kemosahbe.balance.data;

import android.provider.BaseColumns;

public class TransactionContract {
    public static abstract class TransactionEntry implements BaseColumns{
        public static final String NOMBRE_TABLA = "transaccionesActual";
        public static final String TABLA_ANTERIOR = "transaccionesAnterior";
        public static final String TABLA_HISTORICA = "transaccionesHistoricas";

        public static final String ID = "id";
        public static final String FECHA = "fecha";
        public static final String DESCRIPCION = "descripcion";
        public static final String TIPO = "tipo";
        public static final String CUENTA = "cuenta";
        public static final String MONTO = "monto";
    }
}
