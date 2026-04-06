package com.example.lab4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        topAppBar = findViewById(R.id.topAppBar);

        setSupportActionBar(topAppBar);

        // Afficher le premier fragment au démarrage
        if (savedInstanceState == null) {
            replaceFragment(new FragmentOne(), "Fragment 1", false);
        }

        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_fragment1) {
                replaceFragment(new FragmentOne(), "Fragment 1", true);
                return true;
            } else if (itemId == R.id.nav_fragment2) {
                replaceFragment(new FragmentTwo(), "Fragment 2", true);
                return true;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment, String title, boolean addToBackStack) {
        if (topAppBar != null) {
            topAppBar.setTitle(title);
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,  // enter
                        R.anim.slide_out_left,  // exit
                        R.anim.slide_in_left,   // popEnter
                        R.anim.slide_out_right  // popExit
                )
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            ft.addToBackStack(null);
        }

        ft.commit();
    }
}