package com.kemosahbe.balance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.kemosahbe.balance.data.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBController extends SQLiteOpenHelper {
    private final String ID = "[DBController]";
    private static final String DB_PATH = "/data/data/com.kemosahbe.balance/databases/";
    private static final String DB_NAME = "BalanceDataBase.db";
    private static final String DB_Pathname = DB_PATH + DB_NAME;
    private static int DB_VERSION = 1;
    private static DBController mInstancia = null;
    private static SQLiteDatabase mDataBase;
    private static Context ctx;

    private DBController(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.ctx = context;
        Log.d(ID, "DBController instanciado.");
        mDataBase = getWritableDatabase();
    }

    public static DBController Instance(Context context) {
        if( mInstancia == null){
            mInstancia = new DBController(context);
        }
        return mInstancia;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //this.mDataBase = db;
        //Toast.makeText(ctx, "DataBase created in: " + db.getPath(), Toast.LENGTH_SHORT).show();
        Log.d(ID,"DataBase Creada.");

        // TABLA CUENTAS
        db.execSQL("CREATE TABLE " + AccountContract.AccountEntry.NOMBRE_TABLA + " ("
                + AccountContract.AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AccountContract.AccountEntry.ID + " TEXT NOT NULL,"
                + AccountContract.AccountEntry.NOMBRE + " TEXT NOT NULL, "
                + AccountContract.AccountEntry.SALDO + " INTEGER NOT NULL,"
                + "UNIQUE (" + AccountContract.AccountEntry.ID + "))");
        // TABLA DE RESPALDO PARA LAS CUENTAS
        db.execSQL("CREATE TABLE " + AccountContract.AccountEntry.TABLA_RESPALDO + " ("
                + AccountContract.AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AccountContract.AccountEntry.ID + " TEXT NOT NULL,"
                + AccountContract.AccountEntry.NOMBRE + " TEXT NOT NULL, "
                + AccountContract.AccountEntry.SALDO + " INTEGER NOT NULL"
                + ")");


        // TABLA TRANSACCIONES MES ACTUAL
        db.execSQL("CREATE TABLE " + TransactionContract.TransactionEntry.NOMBRE_TABLA + " ("
                + TransactionContract.TransactionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TransactionContract.TransactionEntry.ID + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.FECHA + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.DESCRIPCION + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.TIPO + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.CUENTA + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.MONTO + " INTEGER NOT NULL,"
                + "UNIQUE (" + TransactionContract.TransactionEntry.ID + "))");
        // TABLA TRANSACCIONES MES ANTERIOR
        db.execSQL("CREATE TABLE " + TransactionContract.TransactionEntry.TABLA_ANTERIOR + " ("
                + TransactionContract.TransactionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TransactionContract.TransactionEntry.ID + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.FECHA + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.DESCRIPCION + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.TIPO + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.CUENTA + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.MONTO + " INTEGER NOT NULL"
                + ")");
        // TABLA TRANSACCIONES HISTORICA
        db.execSQL("CREATE TABLE " + TransactionContract.TransactionEntry.TABLA_HISTORICA + " ("
                + TransactionContract.TransactionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TransactionContract.TransactionEntry.ID + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.FECHA + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.DESCRIPCION + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.TIPO + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.CUENTA + " TEXT NOT NULL,"
                + TransactionContract.TransactionEntry.MONTO + " INTEGER NOT NULL"
                + ")");


        // TABLA BALANCES
        db.execSQL("CREATE TABLE " + BalanceContract.BalanceEntry.NOMBRE_TABLA + " ( "
                + BalanceContract.BalanceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BalanceContract.BalanceEntry.ID + " TEXT NOT NULL,"
                + BalanceContract.BalanceEntry.FECHA + " TEXT NOT NULL,"
                + BalanceContract.BalanceEntry.SALDO + " INTEGER NOT NULL,"
                + BalanceContract.BalanceEntry.INGRESOS + " INTEGER NOT NULL,"
                + BalanceContract.BalanceEntry.GASTOS + " INTEGER NOT NULL,"
                + BalanceContract.BalanceEntry.CUENTA + " TEXT NOT NULL,"
                + "UNIQUE (" + BalanceContract.BalanceEntry.ID + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);
    }

    public Boolean pushTransaction(@NonNull Transaction transaction){
        ContentValues values = new ContentValues();
        try{
            values.put(TransactionContract.TransactionEntry.FECHA, transaction.FECHA);
            values.put(TransactionContract.TransactionEntry.DESCRIPCION, transaction.DESCRIPCION);
            values.put(TransactionContract.TransactionEntry.TIPO, transaction.TIPO);
            values.put(TransactionContract.TransactionEntry.CUENTA, transaction.CUENTA);
            values.put(TransactionContract.TransactionEntry.MONTO, transaction.MONTO);

            mDataBase.insert(TransactionContract.TransactionEntry.NOMBRE_TABLA, null, values);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Boolean pushAccount(Account account){
        return true;
    }

    public Boolean pushBalance(Balance balance){
        return true;
    }
    public void changeAccountName(Account account, String newname){

    }
    public void changeAccountValue(Account account, int value){

    }
    public ArrayList<Transaction> getAllTransactions(){
        return new ArrayList<>();
    }
    public Transaction getTransactionById(String id){
        return new Transaction();
    }
    public ArrayList<Account> getAllAccounts(){
        return new ArrayList<>();
    }

/*
    private void createDataBase() throws IOException {
        Boolean dbExists =  checkFile(DB_PATH + DB_NAME);
        SQLiteDatabase mdbRead = null;

        if(dbExists){
            //La base de datos ya existe.
        }else{
            mdbRead = this.getReadableDatabase();
            mdbRead.close();

            InputStream inputStream = ctx.getApplicationContext().getAssets().open(DB_NAME);
            OutputStream outputStream = new FileOutputStream(DB_Pathname);

            byte[] buffer = new byte[1024];
            int length;
            while( (length = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        }
    }

    private void openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_Pathname, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private Boolean checkFile(String fullpath){
        File dbFile = new File(fullpath);
        return dbFile.exists();
    }

    private Cursor select(String query){
        return mDataBase.rawQuery(query,null);
    }
    */
}



