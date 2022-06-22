package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class FoodListFragment extends Fragment {

    private ListView listView;
    private Context context;
    private ArrayList<String> food_list;
    private ArrayAdapter<String> foodListAdapter;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_list_fragment_layout, container, false);
        context = inflater.getContext();
        listView = view.findViewById(R.id.food_list_listview);
        food_list = new ArrayList<>();

        food_list.add("Chicken Burger");
        food_list.add("Buffalo Chicken Burger");
        food_list.add("Beef Burger");
        food_list.add("Veggie Burger");

        Bundle bundle = this.getArguments();
        if (bundle != null){
            food_list.clear();
            food_list.addAll(bundle.getStringArrayList("food_list"));
        }
        intent = new Intent(context, DetailActivity.class);
        foodListAdapter = new ArrayAdapter<String>(context, R.layout.food_list_layout, R.id.food_list_text, food_list);
        listView.setAdapter(foodListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0:
                        intent.putExtra("list_name", food_list.get(0));
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("list_name", food_list.get(1));
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("list_name", food_list.get(2));
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("list_name", food_list.get(3));
                        startActivity(intent);
                        break;
                }
            }
        });
        return view;
    }
}

