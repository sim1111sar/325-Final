package com.example.afinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class OrdersFragment extends Fragment {
    private TextView total_bottom;
    private ListView list;
    private ArrayList<OrdersContent> arrayList;
    private RestaurantHelper helper;
    private SQLiteDatabase db_readable;
    private SQLiteDatabase db_writable;
    private Context context;
    private double total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_fragment_layout, container, false);
        context = inflater.getContext();
        list = view.findViewById(R.id.Name_Cart_Value);
        total_bottom = view.findViewById(R.id.Total_Value_Cart);
        arrayList = new ArrayList<>();
        helper = new RestaurantHelper(context);
        db_readable = helper.getReadableDatabase();
        db_writable = helper.getWritableDatabase();
        total = 0;
        if (db_readable != null) {
            Cursor cursor = db_readable.rawQuery("SELECT name, SUM(quantity), price FROM restaurant GROUP BY name", null);
            if (cursor != null) {
                while (cursor.moveToNext())
                    arrayList.add(new OrdersContent(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
        for (int i = 0; i < arrayList.size(); ++i) {
            total += Double.parseDouble(arrayList.get(i).Price) * Integer.parseInt(arrayList.get(i).Quantity);
        }
        total_bottom.setText("" + (double) (Math.round(total * 100.0) / 100.0));
        OrdersAdapter cartListAdapter = new OrdersAdapter(context, R.layout.orders_list_layout, arrayList);
        list.setAdapter(cartListAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                new AlertDialog.Builder(context)
                        .setTitle("Delete From Cart?")
                        .setMessage("Are you sure you want to remove the " + arrayList.get(position).Name + " from your cart?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Toast.makeText(context, arrayList.get(position).Name + " Has been removed from your order", Toast.LENGTH_SHORT).show();
                                double total_new = 0;
                                db_writable.execSQL("DELETE FROM restaurant WHERE name = '" + arrayList.get(position).Name + "'" + ";");
                                arrayList.remove(position);
                                for (int j = 0; j < arrayList.size(); ++j) {
                                    total_new += Double.parseDouble(arrayList.get(j).Price) * Integer.parseInt(arrayList.get(j).Quantity);
                                }
                                total_bottom.setText("" + (double) (Math.round(total_new * 100.0) / 100.0));
                                cartListAdapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return view;
    }
}
