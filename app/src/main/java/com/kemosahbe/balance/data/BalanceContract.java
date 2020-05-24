package com.kemosahbe.balance.data;

import android.provider.BaseColumns;

public class BalanceContract {
    private BalanceContract(){}
    public static abstract class BalanceEntry implements BaseColumns{
        public static final String NOMBRE_TABLA = "balancehistorico";

        public static final String ID = "id";
        public static final String FECHA = "fecha";
        public static final String SALDO = "saldo";
        public static final String INGRESOS = "ingresos";
        public static final String GASTOS = "gastos";
        public static final String CUENTA = "cuenta";
    }
}
