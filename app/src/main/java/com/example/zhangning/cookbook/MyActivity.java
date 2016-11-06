package com.example.zhangning.cookbook;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.zhangning.cookbook.Fragment.HomeFragment;
import com.example.zhangning.cookbook.Fragment.SearchFragment;
import com.example.zhangning.cookbook.Fragment.SettingFragment;
import com.example.zhangning.cookbook.Fragment.CategoryFragment;

public class MyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    HomeFragment homeFragment;
    CategoryFragment categoryFragment;
//    SearchFragment searchFragment;
    SettingFragment settingFragment;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentInitviews();
        getSupportFragmentManager().beginTransaction().add(R.id.container,homeFragment,null).commit();

        searchView =(SearchView)findViewById(R.id.Search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchFragment searchFragment=SearchFragment.newInstance(query);
                getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment,null).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void FragmentInitviews() {
        homeFragment=new HomeFragment();
        categoryFragment =new CategoryFragment();
        settingFragment=new SettingFragment();
//        searchFragment=new SearchFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.category) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment,null).commit();
        } else if (id == R.id.share) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, categoryFragment,null).commit();
        } else if (id == R.id.setting) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,settingFragment,null).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
