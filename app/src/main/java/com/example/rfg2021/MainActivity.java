package com.example.rfg2021;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
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
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Context context = this;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 카카오 API 사용을 위한 디버그 키해시를 가져오는 함수
        getAppKeyHash();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // 드로어메뉴 관련 기능
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawer.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.nav_home){
                    Intent intent = new Intent(MainActivity.this, Setting.class);
                    startActivity(intent);
                    Toast.makeText(context, title + ": 계정 정보를 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_gallery){
                    Toast.makeText(context, title + ": 설정 정보를 확인합니다.", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.nav_slideshow){
                    Toast.makeText(context, title + ": 로그아웃 시도중", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

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

    // 드로어메뉴 페이지이동
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                drawer.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
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