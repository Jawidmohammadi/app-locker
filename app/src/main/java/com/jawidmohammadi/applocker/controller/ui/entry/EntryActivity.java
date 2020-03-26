package com.jawidmohammadi.applocker.controller.ui.entry;

import android.content.Intent;
import android.content.SharedPreferences;

import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import com.jawidmohammadi.applocker.R;
import com.jawidmohammadi.applocker.controller.MainActivity;
import java.nio.channels.Pipe;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * App Authentication when a user want to enter the app it will ask for the password that they created last time.
 * @author Jawid Mohammadi
 */
public class EntryActivity extends AppCompatActivity {

  private static final String HASHED_PIN = "pin";
  private static final String PIN_SALT = "salt";

  private final Random rng = new SecureRandom();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    setContentView(R.layout.activity_entry);
    TextView actionMessage = findViewById(R.id.action_message);
    if (preferences.contains(HASHED_PIN)) {
      //TODO Change text of action message if there is already a pin setup.
      actionMessage = findViewById(R.id.pin_entry);
    }
    EditText pinEntry = findViewById(R.id.pin_entry);
    findViewById(R.id.enter).setOnClickListener((view) -> {
      try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String pin = pinEntry.getText().toString().trim();
        if (preferences.contains(HASHED_PIN)) {
          unlock(preferences, digest, pin);
        } else {
          setLock(preferences, digest, pin);
        }
      } catch (NoSuchAlgorithmException e) {
        Log.e(getClass().getName(), e.getMessage(), e);
        Toast.makeText(this, "Unable to create Hashed Object!", Toast.LENGTH_LONG).show();
      }
    });
  }

  private void setLock(SharedPreferences preferences, MessageDigest digest, String pin) {
    digest.update(pin.getBytes());
    byte[] saltBytes = new byte[16];
    rng.nextBytes(saltBytes);
    digest.update(saltBytes);
    String hashedPin = Base64
        .encodeToString(digest.digest(), Base64.NO_PADDING | Base64.NO_WRAP);
    String salt = Base64.encodeToString(saltBytes, Base64.NO_PADDING | Base64.NO_WRAP);
    preferences.edit()
        .putString(HASHED_PIN, hashedPin)
        .putString(PIN_SALT, salt)
        .commit();
    switchToMain();
  }

  private void unlock(SharedPreferences preferences, MessageDigest digest, String pin) {
    digest.update(pin.getBytes());
    byte[] saltBytes = Base64
        .decode(preferences.getString(PIN_SALT, ""), Base64.NO_PADDING | Base64.NO_WRAP);
    digest.update(saltBytes);
    String hashedPin = Base64
        .encodeToString(digest.digest(), Base64.NO_PADDING | Base64.NO_WRAP);
    if  (hashedPin.equals(preferences.getString(HASHED_PIN, ""))) {
      switchToMain();
    } else {
      //TODO Display Message to the User that login failed.
    }
  }

  private void switchToMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
}
