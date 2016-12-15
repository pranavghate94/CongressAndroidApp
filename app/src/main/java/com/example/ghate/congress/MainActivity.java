package com.example.ghate.congress;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    Fragment fragment=null;
    Class fragmentClass=LegislatorsFragment.class;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentManager fragmentManager;
    private FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new AppPreferences(this).clearEverything();

        //Set Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.hamburger);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get Drawer View
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = setupDrawerToggle();

        navigationView = (NavigationView) findViewById(R.id.nav_drawer);
        setupNavDrawerContent(navigationView);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();


    }

    private void setupNavDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectItem(item);
                return true;
            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, R.string.opendrawer, R.string.closedrawer);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItem(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.nav_legislators:
                fragmentClass = LegislatorsFragment.class;
                break;
            case R.id.nav_bills:
                fragmentClass = BillsFragment.class;
                break;
            case R.id.nav_committees:
                fragmentClass = CommitteesFragment.class;
                break;
            case R.id.nav_favorites:
                fragmentClass = FavoritesFragment.class;
                break;
            case R.id.nav_about_me:
                Intent i = new Intent(this, AboutMe.class);
                startActivity(i);
                break;
            default:
                fragmentClass = LegislatorsFragment.class;
                break;

        }


        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();



        item.setChecked(true);
        if(item.getTitle().toString().compareTo("About Me") != 0)
            setTitle(item.getTitle());
        drawerLayout.closeDrawers();

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


}
