package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    FragmentTransaction fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.act_main_toolbar);
        NavigationView navView = findViewById(R.id.act_main_navigation_view);

        drawerLayout = findViewById(R.id.act_main_drawer_layout);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.syncState();

        fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.act_main_frame_layout, new NewsFragment());
        fm.commit();

        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        Bundle data = new Bundle();
        NewsFragment fragment = new NewsFragment();

        switch(item.getItemId()){
            case R.id.menu_item_news:
                fm = getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.act_main_frame_layout, new NewsFragment());
                fm.commit();
                break;

            case R.id.menu_item_favorites:
                fm = getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.act_main_frame_layout, new FavoritesFragment());
                fm.commit();
                break;
            case R.id.menu_lang_eng:
                fm = getSupportFragmentManager().beginTransaction();
                data.putString("lang", "en");
                fragment.setArguments(data);
                fm.replace(R.id.act_main_frame_layout, fragment);
                fm.commit();
                break;
            case R.id.menu_lang_hin:
                fm = getSupportFragmentManager().beginTransaction();
                data.putString("lang", "hi");
                fragment.setArguments(data);
                fm.replace(R.id.act_main_frame_layout, fragment);
                fm.commit();
                break;
            case R.id.menu_lang_fre:
                fm = getSupportFragmentManager().beginTransaction();
                data.putString("lang", "fr");
                fragment.setArguments(data);
                fm.replace(R.id.act_main_frame_layout, fragment);
                fm.commit();
                break;
            case R.id.menu_lang_ger:
                fm = getSupportFragmentManager().beginTransaction();
                data.putString("lang", "de");
                fragment.setArguments(data);
                fm.replace(R.id.act_main_frame_layout, fragment);
                fm.commit();
                break;
        }

        return true;
    }
}