package com.jawidmohammadi.applocker.controller.ui.apps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.jawidmohammadi.applocker.R;
import com.jawidmohammadi.applocker.view.AppRecyclerAdapter;


public class AppsFragment extends Fragment {

  private AppsViewModel appsViewModel;

  private RecyclerView appList;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
//    View root = inflater.inflate(R.layout.fragment_apps, container, false);
//    appList = root.findViewById(R.id.icon_list);
//    return root;
    RecyclerView iconList = findViewById(R.id.icon_list);
    AppsViewModel viewModel = new ViewModelProvider(this).get(AppsViewModel.class);
    viewModel.getApps().observe(this, (apps) -> {
      AppRecyclerAdapter adapter = new AppRecyclerAdapter(this, apps);
      iconList.setAdapter(adapter);
    });
    viewModel.refreshApps();
    return iconList;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    appsViewModel = new ViewModelProvider(this).get(AppsViewModel.class);
    //TODO Observe viewmodel data.



  }


}