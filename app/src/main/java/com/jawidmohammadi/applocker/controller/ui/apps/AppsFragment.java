package com.jawidmohammadi.applocker.controller.ui.apps;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jawidmohammadi.applocker.PasswordDialog;
import com.jawidmohammadi.applocker.R;
import com.jawidmohammadi.applocker.view.AppRecyclerAdapter;
import java.util.ArrayList;

/**
 * App
 */
public class AppsFragment extends Fragment {

  private AppsViewModel appsViewModel;

  private RecyclerView appList;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);



  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_apps, container, false);
    appList = root.findViewById(R.id.icon_list);
    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
      appList.setLayoutManager(new GridLayoutManager(getContext(), 2));
    } else {
      appList.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    appsViewModel = new ViewModelProvider(this).get(AppsViewModel.class);
    appsViewModel.getApps().observe(getViewLifecycleOwner(), (apps) -> {
      AppRecyclerAdapter adapter = new AppRecyclerAdapter(getContext(), new ArrayList<>(apps),
          (app, locked) -> {
            if (locked) {
              //TODO show dialog to get locking password. in the dialog the ok button should result in locking the app
              //the dialog should invoke appsViewModel.lock(app, password);
//              DialogFragment newFragment = new PasswordDialog();
//              AppsViewModel appsViewModel = new AppsViewModel(app, password);
//              appsViewModel.lock(app, password);
//              newFragment.show(getParentFragmentManager(), "missiles");
            } else {
              appsViewModel.unlock(app);
            }
          });
      appList.setAdapter(adapter);
    });
    appsViewModel.refreshApps();
  }


}