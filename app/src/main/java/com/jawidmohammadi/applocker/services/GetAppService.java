package com.jawidmohammadi.applocker.services;

public class GetAppService {

  public static AppGetterInterface getInstance(){
    return new AppGetterTemp();
  }

}
