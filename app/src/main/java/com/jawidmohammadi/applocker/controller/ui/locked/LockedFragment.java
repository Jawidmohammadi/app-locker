package com.jawidmohammadi.applocker.controller.ui.locked;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.jawidmohammadi.applocker.R;


public class LockedFragment extends Fragment {

  private RecyclerView lockedList;
  private LockedViewModel lockedViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    lockedViewModel =
        ViewModelProviders.of(this).get(LockedViewModel.class);
    View root = inflater.inflate(R.layout.fragment_locked, container, false);

    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}