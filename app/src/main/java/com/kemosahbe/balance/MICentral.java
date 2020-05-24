package com.kemosahbe.balance;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.EventListener;
import java.util.EventObject;

public abstract class MICentral
        implements View.OnClickListener, Toolbar.OnMenuItemClickListener, OnReloadPageEventListener, OnSaveTransactionEventListener{

    public static final String id = "[Central Information Module]";
    private static Context ctx;
    private static DBController dbManager = null;
    private static MICentralEventListener centrallistener;

    public void Begin(Context context){
        ctx = context;
        dbManager = DBController.Instance(ctx);
    }

    public void setCentralEventListener(MICentralEventListener source){
        if(centrallistener == null){
            centrallistener = source;
        }
    }

    @Override
    public void onClick(View view) {
        centrallistener.nothing(new CentralEvent(this), view);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}

interface MICentralEventListener extends EventListener {
    public void nothing(CentralEvent event, View view);
}

class CentralEvent extends EventObject{
    public CentralEvent(Object source){
        super(source);
    }
}
