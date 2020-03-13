package com.jawidmohammadi.applocker.controller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jawidmohammadi.applocker.R;
import com.jawidmohammadi.applocker.controller.ui.apps.AppsViewModel;
import com.jawidmohammadi.applocker.view.AppRecyclerAdapter;

public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    BottomNavigationView navView = findViewById(R.id.nav_view);
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_apps, R.id.navigation_locked, R.id.navigation_privacy,
        R.id.navigation_settings)
        .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(navView, navController);

    RecyclerView iconList = findViewById(R.id.icon_list);
    AppsViewModel viewModel = new ViewModelProvider(this).get(AppsViewModel.class);
    viewModel.getApps().observe(this, (apps) -> {
      AppRecyclerAdapter adapter = new AppRecyclerAdapter(this, apps);
      iconList.setAdapter(adapter);
    });
    viewModel.refreshApps();

  }
}
