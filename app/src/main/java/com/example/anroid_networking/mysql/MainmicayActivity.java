package com.example.anroid_networking.mysql;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.anroid_networking.R;
import com.example.anroid_networking.mysql.ui.gallery.GalleryFragment;
import com.example.anroid_networking.mysql.ui.home.HomeFragment;
import com.example.anroid_networking.mysql.ui.slideshow.SlideshowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;

public class MainmicayActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private BottomNavigationView mbottomNavigationView;
    UserManager userManager;
    ProgressDialog progressDialog;
    TextView Name,Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmicay);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Check login
        userManager=new UserManager(this);
        userManager.checkLogin();

        //Loading
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        HashMap<String,String> user=userManager.userDatails();
        String mName=user.get(userManager.NAME);
        final String mEmail=user.get(userManager.EMAIL);
        String mPhone=user.get(userManager.PHONE);

        //Bottom navigation
        mbottomNavigationView=findViewById(R.id.bottom_navigation);
        mbottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);




        //Set Name and email in headerView Navigation Drawer
        View headerView=navigationView.getHeaderView(0);
        Name=(TextView)headerView.findViewById(R.id.p_name);
        Email=(TextView)headerView.findViewById(R.id.p_email);
        Name.setText(mName);
        Email.setText(mEmail);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int id=item.getItemId();
                 switch (id){
                     case R.id.logout:
                         userManager.logout();
                         break;
                 }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });



    }

    //Private BottomNavigation Click
    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment=null;
                    switch (item.getItemId()){
                        case R.id.action_home:
                            selectedFragment=new HomeFragment();
                            break;
                        case R.id.action_menu:
                            selectedFragment=new GalleryFragment();
                            break;
                        case R.id.action_cart:
                            selectedFragment=new SlideshowFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,selectedFragment).commit();
                    return true;
                }
            };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmicay, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}