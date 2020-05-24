package com.kemosahbe.balance.Fragments;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kemosahbe.balance.R;

import java.util.jar.Attributes;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabMovimientos extends Fragment {

    //View item;
    //LinearLayout scrollLayout;
    //LayoutInflater inflator;
    //private ViewGroup containerLayout;
    public TabMovimientos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //containerLayout = container;
        //init();
        return inflater.inflate(R.layout.fragment_tab_movimientos, container, false);
    }

    public void init(){
        //scrollLayout = containerLayout.findViewById(R.id.scrollLayout);
        //item.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //item = inflator.inflate(R.layout.view_cuentas, scrollLayout, false);
        //scrollLayout.addView(item);
    }

}
