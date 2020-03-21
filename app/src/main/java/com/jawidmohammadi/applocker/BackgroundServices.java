//package com.jawidmohammadi.applocker;
//
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.os.IBinder;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.room.DatabaseConfiguration;
//import androidx.room.InvalidationTracker;
//import androidx.sqlite.db.SupportSQLiteOpenHelper;
//import com.jawidmohammadi.applocker.model.dao.AppDao;
//import com.jawidmohammadi.applocker.model.dao.AttemptDao;
//import com.jawidmohammadi.applocker.services.AppLockerDatabase;
//
//public class BackgroundServices extends Service {
//
//private class BroadcastReceiver receiver;
//public int counter = 0;
//LockScreen obj = new LockScreen();
//Context mContext;
//AppLockerDatabase db = new AppLockerDatabase(this) {
//
//  static int flag = 0;
//  static int flag2 = 0;
//
//  String current_app = "";
//
//  public BackgroundServices() {
//  }
//
//
//
//  @Override
//  public AppDao getApplicationDao() {
//    return null;
//  }
//
//  @Override
//  public AttemptDao getAttemptDao() {
//    return null;
//  }
//
//  @NonNull
//  @Override
//  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
//    return null;
//  }
//
//  @NonNull
//  @Override
//  protected InvalidationTracker createInvalidationTracker() {
//    return null;
//  }
//
//  @Override
//  public void clearAllTables() {
//
//  }
//}
//  @Nullable
//  @Override
//  public IBinder onBind(Intent intent) {
//    return null;
//  }
//}
