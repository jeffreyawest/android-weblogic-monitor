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

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeffreyawest.weblogic.entity.Application;
import com.jeffreyawest.weblogic.entity.Cluster;
import com.jeffreyawest.weblogic.entity.Datasource;
import com.jeffreyawest.weblogic.entity.Server;
import com.jeffreyawest.weblogic.entity.WebLogicEntity;
import com.jeffreyawest.weblogic.monitor.WebLogicMonitor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jeffreyawest on 8/17/13.
 */
public class WebLogicDemoRestAdapter extends WebLogicRestAdapter
{

  private static final String LOG_TAG = "WebLogicDemoRestAdapter";
  private HashMap<String, Server> servers;
  private HashMap<String, Cluster> clusters;
  private HashMap<String, Datasource> datasources;
  private HashMap<String, Application> applications;
  private HashMap<Class<? extends WebLogicEntity>, HashMap<String, ? extends WebLogicEntity>> entityHashMap;

  public WebLogicDemoRestAdapter()
  {

    entityHashMap = new HashMap<Class<? extends WebLogicEntity>, HashMap<String, ? extends WebLogicEntity>>(7);

    try
    {
      servers = initObjects("servers.json", Server.class);
      entityHashMap.put(Server.class, servers);

      clusters = initObjects("clusters.json", Cluster.class);
      entityHashMap.put(Cluster.class, clusters);

      applications = initObjects("applications.json", Application.class);
      entityHashMap.put(Application.class, applications);

      datasources = initObjects("datasources.json", Datasource.class);
      entityHashMap.put(Datasource.class, datasources);
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private String getFileData(String assetName) throws IOException
  {

    String returnMe = null;
    InputStream is = null;

    try
    {

      is = WebLogicMonitor.getInstance().getResources().getAssets().open(assetName);
      byte[] buffer = new byte[is.available()];
      int bytesRead = is.read(buffer);

      if (bytesRead != buffer.length)
      {
        throw new IOException("Read [" + bytesRead + "] bytes but expected [" + buffer.length + "]");
      }
      is.close();
      returnMe = new String(buffer);
    } finally
    {
      if (is != null)
      {
        try
        {
          is.close();
        } catch (Exception ignore)
        {
        }
      }
    }
    return returnMe;
  }

  private <T extends WebLogicEntity> HashMap<String, T> initObjects(String assetFileName, Class<T> theClass)
  {

    HashMap<String, T> returnMe = new HashMap<String, T>(7);

    JSONObject jsonMessage = null;
    ObjectMapper om = new ObjectMapper();

    try
    {
      String json = getFileData(assetFileName);
      jsonMessage = new JSONObject(json);

      Log.v(LOG_TAG, "Class: " + theClass + " JSON: " + jsonMessage.toString(2));

      JSONArray array = jsonMessage.getJSONObject("body").getJSONArray("items");

      for (int i = 0; i < array.length(); i++)
      {
        T theType = om.readValue(array.getString(i), theClass);
        theType.setOriginalJSON(jsonMessage.toString(2));

        returnMe.put(theType.getName(), theType);
      }
    } catch (JSONException e)
    {
      e.printStackTrace();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
    return returnMe;
  }

  @Override
  public <T extends WebLogicEntity> List<T> getResourcesList(Class<T> theClass)
  {

    return new ArrayList(entityHashMap.get(theClass).values());
  }

  @Override
  public <T extends WebLogicEntity> T getResource(Class<T> theClass, String pName)
  {

    return (T) entityHashMap.get(theClass).get(pName);
  }
}
