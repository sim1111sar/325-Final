package com.example.afinal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CategoryFragment extends Fragment implements CategoryAdapter.RecyclerViewClickListener {
    private RecyclerView rv;
    private LinearLayoutManager linearLayoutManager;
    private CategoryAdapter categoryAdapter;
    private Context context;
    private ArrayList<Integer> images;
    private ArrayList<String> categories, burger_list, steak_list, drink_list, salad_list;
    private FoodListFragment foodListFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment_layout, container, false);
        context = inflater.getContext();
        categories = new ArrayList<>();
        images = new ArrayList<>();
        rv = view.findViewById(R.id.category_rv);
        categories = new ArrayList<>();
        images = new ArrayList<>();
        burger_list = new ArrayList<>();
        steak_list = new ArrayList<>();
        drink_list = new ArrayList<>();
        salad_list = new ArrayList<>();

        burger_list.add("Chicken Burger");
        burger_list.add("Buffalo Chicken Burger");
        burger_list.add("Beef Burger");
        burger_list.add("Veggie Burger");

        steak_list.add("Flank Steak");
        steak_list.add("Skirt Steak");
        steak_list.add("Sirloin Steak");
        steak_list.add("Filet Mignon");

        drink_list.add("Brandy");
        drink_list.add("Cognac");
        drink_list.add("Vodka");
        drink_list.add("Wiskey");

        salad_list.add("Leafy Green Salad");
        salad_list.add("Greek Salad");
        salad_list.add("Fattoush");
        salad_list.add("Chicken Salad");

        categories.add("Hamburger");
        categories.add("Steak");
        categories.add("Drinks");
        categories.add("Salad");

        images.add(R.drawable.burger);
        images.add(R.drawable.steaks);
        images.add(R.drawable.drinks);
        images.add(R.drawable.salads);

        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        categoryAdapter = new CategoryAdapter(context, categories, images, this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(categoryAdapter);
        return view;
    }

    @Override
    public void onCategoryClick(int position) {
        Bundle bundle = new Bundle();
        foodListFragment = new FoodListFragment();

        switch (position) {
            case 0:

                bundle.putStringArrayList("food_list", burger_list);
                foodListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.food_list_fragment_container, foodListFragment).commit();
                break;
            case 1:

                bundle.putStringArrayList("food_list", steak_list);
                foodListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.food_list_fragment_container, foodListFragment).commit();
                break;
            case 2:

                bundle.putStringArrayList("food_list", drink_list);
                foodListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.food_list_fragment_container, foodListFragment).commit();
                break;
            case 3:

                bundle.putStringArrayList("food_list", salad_list);
                foodListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.food_list_fragment_container, foodListFragment).commit();
                break;
        }
    }
}
