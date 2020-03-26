package com.jawidmohammadi.applocker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.jawidmohammadi.applocker.controller.ui.apps.AppsViewModel;
import com.jawidmohammadi.applocker.model.entity.App;
import java.util.Objects;

/**
 * this is the password popup dialog where user can enter there password and set the restriction for
 * there apps.
 * @author Jawid Mohammadi
 */
public class PasswordDialogFragment extends DialogFragment {

  private static final String PKG_KEY = "pkg";
  private EditText inputPassword;
  private String pkg;
  private App app;
  private View view;
  private AppsViewModel viewModel;

  public static PasswordDialogFragment createInstance(String pkg) {
    PasswordDialogFragment fragment = new PasswordDialogFragment();
    Bundle args = new Bundle();
    args.putString(PKG_KEY, pkg);
    fragment.setArguments(args);
    return fragment;
  }

  /**
   * this method show the dialog and it has two buttons this specify what each positive and negative
   * buttons does.
   *
   * @param savedInstanceState bundle saveInstanceState
   * @return dialog.
   */

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    pkg = getArguments().getString(PKG_KEY);
    AlertDialog.Builder builder = new Builder(Objects.requireNonNull(getActivity()));
    LayoutInflater inflater = getActivity().getLayoutInflater();
    view = inflater.inflate(R.layout.fragment_password_dialog, null, false);
    inputPassword = view.findViewById(R.id.input_password);
    builder.setView(view)
        .setTitle("Set Password")
        .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
        })
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            PackageManager manager = getContext().getPackageManager();
            App app = new App();
            app.setPkg(pkg);
            try {
              app.setLabel(manager.getApplicationLabel(
                  manager.getApplicationInfo(app.getPkg(), 0)).toString());
            } catch (NameNotFoundException e) {
              Log.e(getClass().getName(), e.getMessage(), e);
            }
            viewModel.lock(app, inputPassword.getText().toString().trim());
          }
        });

    return builder.create();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(AppsViewModel.class);

  }
}

