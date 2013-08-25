/*
 *  Copyright (c) 2013 - Jeffrey A. West Designs
 * ************************************************************************
 * This code is provided for example purposes only.  Neither Oracle nor Jeffrey
 * A. West assume any responsibility or liability for the consequences of using
 * this code. If you choose to use this code for any reason, including but not limited to its use as an example you do so at your own risk and without the support of Oracle.
 * This code is provided under the following licenses:
 *  - GNU General Public License (GPL-2.0)
 * **************************************************************************
 */

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
