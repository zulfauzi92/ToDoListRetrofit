package com.example.todolistretrofit.dashboard;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.todolistretrofit.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    private TextView textView;
    private Fragment selectedFragment = null;
    private int tmp = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        currentFragment();

    }

    private void currentFragment() {
        selectedFragment = new DashboardFragment(0);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, selectedFragment);
        fragmentTransaction.commit();
    }

    private void initialize() {
        BottomNavigationView botNavView = findViewById(R.id.bottom_navigation);
        botNavView.setOnNavigationItemSelectedListener(navListener);
        botNavView.setSelectedItemId(R.id.unchecked_botNav);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()){
                case R.id.unchecked_botNav:
                    if(tmp != 0) {
                        selectedFragment = new DashboardFragment(0);
                        overridePendingTransition(0, 0);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                                selectedFragment).commit();
                        tmp = 0;
                        break;
                    }
                    break;

                case R.id.checked_botNav:
                    if(tmp != 1) {
                        selectedFragment = new DashboardFragment(1);
                        overridePendingTransition(0, 0);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                                selectedFragment).commit();
                        tmp = 1;
                        break;
                    }
                    break;


            }

            
            return true;
        }
    };

}