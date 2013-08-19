package com.jeffreyawest.weblogic.rest;

import com.jeffreyawest.weblogic.entity.WebLogicEntity;

import java.util.List;

/**
 * Created by jeffreyawest on 8/17/13.
 */
public abstract class WebLogicRestAdapter
{

  private static WebLogicRestAdapter instance;
  private static boolean initialized = false;

  public static void initializeHTTPAdapter(String host, int port, String username, String password)
  {

    WebLogicHTTPRestAdapter httpRestAdapter = new WebLogicHTTPRestAdapter();
    httpRestAdapter.init(host, port, username, password);

    instance = httpRestAdapter;
    initialized = true;
  }

  public static void initializeDemoAdapter()
  {

    instance = new WebLogicDemoRestAdapter();
  }

  public static WebLogicRestAdapter getInstance()
  {

    if (instance == null)
      instance = new WebLogicDemoRestAdapter();

    return instance;
  }

  public abstract <T extends WebLogicEntity> List<T> getResourcesList(Class<T> theClass);

  public abstract <T extends WebLogicEntity> T getResource(Class<T> theClass, String pName);
}
