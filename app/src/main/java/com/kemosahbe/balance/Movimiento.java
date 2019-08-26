package com.kemosahbe.balance;

import java.util.Date;

public class Movimiento {
    public enum TipoMovimiento {
        Cargo,
        Giro
    }
    public Date Fecha;
    public String Descripcion;
    public int Monto;
    public TipoMovimiento Tipo;
}
