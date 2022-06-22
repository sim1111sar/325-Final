package com.example.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class OrdersAdapter extends ArrayAdapter<OrdersContent> {

    private Context mContext;
    private int mResource;
    private ArrayList<OrdersContent> arrayList;
    double total;

    public OrdersAdapter(@NonNull Context context, int resource, @NonNull ArrayList<OrdersContent> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);

        OrdersContent cl = arrayList.get(position);

        String name = cl.getName();
        String quantity = cl.getQuantity();
        String price = cl.getPrice();

        total = Double.parseDouble(price) * Integer.parseInt(quantity);


        TextView item_name = convertView.findViewById(R.id.Name_Cart_Value);
        TextView item_quantity = convertView.findViewById(R.id.Quantity_Cart_Value);
        TextView item_price = convertView.findViewById(R.id.Price_Cart_Value);
        TextView item_total = convertView.findViewById(R.id.Total_Cart_Value);

        item_name.setText(name);
        item_quantity.setText(quantity);
        item_price.setText("$" + String.format("%.2f", Double.parseDouble(price)));
        item_total.setText(String.format("%.2f", total));

        return convertView;
    }
}
