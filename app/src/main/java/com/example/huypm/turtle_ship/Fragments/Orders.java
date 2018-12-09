package com.example.huypm.turtle_ship.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.huypm.turtle_ship.ADapter.adapter_itemlist;
import com.example.huypm.turtle_ship.DBManager.TurtleShipManager;
import com.example.huypm.turtle_ship.R;

import java.util.ArrayList;
import java.util.List;

public class Orders extends Fragment  {
    private List<Cursor> items;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.oders,container,false);
        TurtleShipManager db = new TurtleShipManager(getContext());
        final Bundle bundle = getArguments();
        Cursor cursor = db.getOrders(bundle.getInt("ID"));
        ListView lv = view.findViewById(R.id.lv_orders);
        adapter_itemlist itemlist  = new adapter_itemlist(getActivity(),cursor);
        lv.setAdapter(itemlist);
        itemlist.changeCursor(cursor);
        FloatingActionButton add_order = (FloatingActionButton) view.findViewById(R.id.add_order);
        add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new order_step1();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft =  fm.beginTransaction();
                fragment.setArguments(bundle);
                ft.addToBackStack(null);
                ft.replace(R.id.content_main,fragment);
                ft.commit();
            }
        });
        return view;

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Đơn hàng");
    }


}
