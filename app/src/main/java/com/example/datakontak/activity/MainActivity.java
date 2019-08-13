//15-08-2019
//Muhammad Ilyas
//10116339
//IF -8

package com.example.datakontak.activity;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.datakontak.data.AppDatabase;
import com.example.datakontak.data.KontakDAO;
import com.example.datakontak.fragment.UserFragment;
import com.example.datakontak.model.Kontak;
import com.example.datakontak.preferences.UserPreferences;
import com.example.datakontak.R;
import com.example.datakontak.fragment.HomeFragment;
import com.example.datakontak.fragment.KontakFragment;
import com.example.datakontak.fragment.ProfilFragment;

public class MainActivity extends AppCompatActivity {
    public static final String FRAGMENT_VIEWPAGER = "FRAGMENT_VIEWPAGER";
    public static final String FRAGMENT_FIRST = "FRAGMENT_FIRST";
    public static final String FRAGMENT_SECOND = "FRAGMENT_SECOND";
    public static final String FRAGMENT_THIRD = "FRAGMENT_THIRD";
    public TextView telp;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(UserFragment.newInstance(), FRAGMENT_FIRST);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(ProfilFragment.newInstance(), FRAGMENT_SECOND);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment(KontakFragment.newInstance(), FRAGMENT_THIRD);
                    return true;
                    case R.id.navigation_about:
                    replaceFragment(HomeFragment.newInstance(), FRAGMENT_VIEWPAGER);
                    return true;

            }
            return false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, UserFragment.newInstance())
                .commit();

    }

    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, newFragment, tag)
                .commit();

    }

}
