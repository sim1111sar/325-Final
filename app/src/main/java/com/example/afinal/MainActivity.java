package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CategoryFragment categoryFragment;
    private FoodListFragment foodListFragment;
    private OrdersFragment ordersFragment;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        categoryFragment = new CategoryFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.category_fragment_container, categoryFragment).commit();
        foodListFragment = new FoodListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.food_list_fragment_container, foodListFragment).commit();
        ordersFragment = new OrdersFragment();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.food_menu_nav:
                        getSupportFragmentManager().beginTransaction().remove(ordersFragment).commit();
                        break;
                    case R.id.orders_nav:

                        getSupportFragmentManager().beginTransaction().replace(R.id.orders_fragment_container, ordersFragment).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}