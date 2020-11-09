package com.example.rfg2021;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.rfg2021.ui.gallery.GalleryFragment;
import com.example.rfg2021.ui.home.HomeFragment;
import com.example.rfg2021.ui.slideshow.SlideshowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Feed feed_activity;
    private Map map_activity;
    private Muckpot muckpot_activity;
    private Search search_activity;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 카카오 API 사용을 위한 디버그 키해시를 가져오는 함수
        getAppKeyHash();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // 드로어메뉴 관련 기능
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.feed_navigation:
                        setFrag(0);
                        break;
                    case R.id.map_navigation:
                        setFrag(1);
                        break;
                    case R.id.muckpot_navigation:
                        setFrag(2);
                        break;
                    case R.id.search_navigation:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        feed_activity = new Feed();
        map_activity = new Map();
        muckpot_activity = new Muckpot();
        search_activity = new Search();
        setFrag(4);
    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.show(feed_activity);
                ft.hide(map_activity);
                ft.hide(muckpot_activity);
                ft.hide(search_activity);
                ft.commit();
                break;
            case 1:
                ft.show(map_activity);
                ft.hide(feed_activity);
                ft.hide(muckpot_activity);
                ft.hide(search_activity);
                ft.commit();
                break;
            case 2:
                ft.show(muckpot_activity);
                ft.hide(map_activity);
                ft.hide(feed_activity);
                ft.hide(search_activity);
                ft.commit();
                break;
            case 3:
                ft.show(search_activity);
                ft.hide(muckpot_activity);
                ft.hide(map_activity);
                ft.hide(feed_activity);
                ft.commit();
                break;
            case 4:
                ft.add(R.id.nav_host_fragment, feed_activity);
                ft.add(R.id.nav_host_fragment, map_activity);
                ft.add(R.id.nav_host_fragment, muckpot_activity);
                ft.add(R.id.nav_host_fragment, search_activity);
                ft.hide(muckpot_activity);
                ft.hide(map_activity);
                ft.hide(search_activity);
                ft.commit();
                break;
        }
    }

    // 드로어 메뉴 함수 1
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_sort, menu);
        return true;
    }

    // 드로어 메뉴 함수 2
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // 디버그 키 해시 가져오는 함수
    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("key-hash", "key: " + something);
            }
        } catch (Exception e)
        {
            // TODO Auto-generated catch block Log.e("name not found", e.toString());}
        }
    }
}