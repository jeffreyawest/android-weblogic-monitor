package com.jeffreyawest.weblogic.monitor;

import android.app.Application;

/**
 * Created by jeffreyawest on 8/17/13.
 */
public class WebLogicMonitor extends Application
{

  public static WebLogicMonitor instance;

  public static WebLogicMonitor getInstance()
  {

    return instance;
  }

  @Override
  public void onCreate()
  {

    super.onCreate();

    instance = this;
  }
}
