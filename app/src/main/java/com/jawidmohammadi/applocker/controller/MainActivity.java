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

    // to  show list of  system installed app.(old version )
//    ListView iconList = findViewById(R.id.icon_list);
//    List<ApplicationInfo> packages = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
//    ImageAdapter adapter = new ImageAdapter(this, R.layout.icon_item, packages);
//    iconList.setAdapter(adapter);
//  }
//  private List<ApplicationInfo> getApplications() {
//    PackageManager manager = getPackageManager();
//    Intent intent = new Intent(Intent.ACTION_MAIN);
//    intent.addCategory(Intent.CATEGORY_LAUNCHER);
//    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//    List<ResolveInfo> intents = getPackageManager().queryIntentActivities(intent, 0);
//    List<ApplicationInfo> apps = new ArrayList<>();
//    for (ResolveInfo info : intents) {
//      try {
//        apps.add(manager.getApplicationInfo(info.activityInfo.packageName, 0));
//      } catch (NameNotFoundException e) {
//        Log.e(getClass().getName(), "Can't add app to list", e);
//      }
//    }
//    // HACK This is a terrible way to sort! You should instead create instances of your model class
//    //  (which should implement Comparable), and then sort list of those instances.
//    Collections.sort(apps, (app1, app2) ->
//        manager.getApplicationLabel(app1).toString().compareTo(
//            manager.getApplicationLabel(app2).toString()));
//    return apps;
//  }

    // to show list of install apps in recycler view

  }
}
