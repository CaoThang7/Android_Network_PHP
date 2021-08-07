package com.example.anroid_networking.mysql;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anroid_networking.R;
import com.example.anroid_networking.mysql.Utils.Common;
import com.example.anroid_networking.mysql.ui.gallery.GalleryFragment;
import com.example.anroid_networking.mysql.ui.home.HomeFragment;
import com.example.anroid_networking.mysql.ui.slideshow.SlideshowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
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

public class MainmicayActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private AppBarConfiguration mAppBarConfiguration;
    private BottomNavigationView mbottomNavigationView;
    UserManager userManager;
    ProgressDialog progressDialog;
    TextView Name,Email;
    NotificationBadge badge;
    ImageView cart_icon;
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
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.Open,R.string.Close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        //Set Name and email in headerView Navigation Drawer
        View headerView=navigationView.getHeaderView(0);
        Name=(TextView)headerView.findViewById(R.id.p_name);
        Email=(TextView)headerView.findViewById(R.id.p_email);
        Name.setText(mName);
        Email.setText(mEmail);

        navigationView.setNavigationItemSelectedListener(this);

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                 Fragment selectedFragment=null;
//                 int id=item.getItemId();
//                 switch (id){
//                     case R.id.logout:
//                         userManager.logout();
//                         break;
//                 }
//                drawer.closeDrawer(GravityCompat.START);
//                return true;
//
//            }
//        });


        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,new HomeFragment()).commit();


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

        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
//        View view=menu.findItem(R.id.cart_menu).getActionView();
////        badge =(NotificationBadge)view.findViewById(R.id.badge);
////        updateCartCount();
//        cart_icon=(ImageView)view.findViewById(R.id.cart_icon);
//        cart_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainmicayActivity.this, "Hello Bro", Toast.LENGTH_SHORT).show();
//            }
//        });
        return true;
    }

//    private void updateCartCount() {
//        if(badge== null) return;
//         runOnUiThread(new Runnable() {
//             @Override
//             public void run() {
//                 if(Common.cartRepository.countCartItems()==0){
//                     badge.setVisibility(View.INVISIBLE);
//                 }else {
//                     badge.setVisibility(View.VISIBLE);
//                     badge.setText(String.valueOf(Common.cartRepository.countCartItems()));
//                 }
//             }
//         });
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Override
    public void onBackPressed(){
        DrawerLayout drawer=findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id== R.id.nav_home){

        }else if(id== R.id.nav_gallery){

        }
        else if(id== R.id.nav_slideshow){

        }else if(id== R.id.logout){
            userManager.logout();

        }
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        updateCartCount();
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.cart_menu){
            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}