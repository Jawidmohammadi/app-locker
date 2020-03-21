package com.jawidmohammadi.applocker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

public class PasswordDialog extends AppCompatDialogFragment {
private TextInputEditText editTextPassword;
private PasswordDialogListener listener;


  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    AlertDialog.Builder builder = new Builder(Objects.requireNonNull(getActivity()));
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.layout_dialog, null, false);
    builder.setView(view)
        .setTitle("Set Password")
        .setNegativeButton("Cancel", new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

          }
        })
        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){

          @Override
          public void onClick(DialogInterface dialog, int which) {

          }
        });

    editTextPassword = view.findViewById(R.id.input_password);

    return builder.create();
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);

    try {
      listener = (PasswordDialogListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
    }
  }

  public interface PasswordDialogListener{
    void applyTexts(String password);

  }
}
