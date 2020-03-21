//package com.jawidmohammadi.applocker;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Build.VERSION;
//import android.os.Build.VERSION_CODES;
//import android.util.Log;
//import androidx.annotation.RequiresApi;
//
//public class RestartService extends BroadcastReceiver {
//
//  @RequiresApi(api = VERSION_CODES.O)
//  @Override
//  public void onReceive(Context context, Intent intent) {
//    if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
//      Log.i("Broadcast Listened" , "Service tried to stop");
//      if (VERSION.SDK_INT >= Build.VERSION_CODES.0){
//        Context.startForgroundServices(new Intent(context, BackgroundServices.class));
//      }
//
//
//    }
//    Log.i("Broadcast Listened" , "Service tried to stop");
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.0) {
//      context.startForegroundService(new Intent(context, BackgroundServices.class));
//    } else {
//      context.startService(new Intent(context, BackgroundServices.class));
//    }
//  }
//}
