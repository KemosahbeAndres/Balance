package com.kemosahbe.balance.data;

import android.provider.BaseColumns;

public class AccountContract {
    private AccountContract(){}
    public static abstract class AccountEntry implements BaseColumns{
        public static final String NOMBRE_TABLA = "cuentas";
        public static final String TABLA_RESPALDO = "cuentasRespaldo";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String SALDO = "saldo";
    }
}
