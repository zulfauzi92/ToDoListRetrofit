package com.example.todolistretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.todolistretrofit.R;
import com.example.todolistretrofit.fragment.TaskFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Fragment selectedFragment = null;
    private int tmp = 0;
    private int check;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView botNavView = findViewById(R.id.bottom_navigation);
        botNavView.setOnNavigationItemSelectedListener(navListener);

        selectedFragment = new TaskFragment(0);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, selectedFragment);
        fragmentTransaction.commit();
        botNavView.setSelectedItemId(R.id.unchecked_botNav);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()){
                case R.id.unchecked_botNav:
                    if(tmp != 0) {
                        selectedFragment = new TaskFragment(0);
                        overridePendingTransition(0, 0);
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                                selectedFragment).commit();
                        tmp = 0;
                        break;
                    }
                    break;

                case R.id.checked_botNav:
                    if(tmp != 1) {
                        selectedFragment = new TaskFragment(1);
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