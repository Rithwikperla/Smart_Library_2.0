package com.example.smartlibrary20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    EditText bookname;
    Button search;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlyt);
        toolbar = findViewById(R.id.toolbar);
        search = findViewById(R.id.search);
        bookname = findViewById(R.id.bookname);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book = bookname.getText().toString().trim();
                Intent detailsIntent = new Intent( HomeScreen.this,SearchList.class);
                replacefragment(new SearchList());
                book = book.replace(" ","+");
                if (bookname.getText().toString().isEmpty()) {
                    bookname.setError("Please enter search query");
                    return;
                }
                detailsIntent.putExtra("key",book );
                startActivity(detailsIntent);
                finish();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id)
                {
                    case R.id.Profile: {

                    }
                    case R.id.fav:
                    {

                    }
                    case R.id.logout:
                    {

                    }
                }
                return true;
            }
        });

    }

    private void replacefragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchlist,fragment);
        fragmentTransaction.commit();
    }

}