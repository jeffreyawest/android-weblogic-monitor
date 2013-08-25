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
import com.jeffreyawest.http.HTTPAdapter;
import com.jeffreyawest.http.HTTPAdapterImpl;
import com.jeffreyawest.weblogic.entity.WebLogicEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffreyawest on 8/9/13.
 */
public class WebLogicHTTPRestAdapter extends WebLogicRestAdapter
{

  private static final String LOG_TAG = "WebLogicRestAdapter";

  private String baseURL;
  private String username;
  private String password;
  private boolean fullQuery = false;

  private HTTPAdapter httpAdapter;

  protected WebLogicHTTPRestAdapter()
  {

  }

  protected void init(String host, int port, String username, String password)
  {

    httpAdapter = new HTTPAdapterImpl();
    this.username = username;
    this.password = password;
    baseURL = "http://" + host + ":" + port + "/management/tenant-monitoring";

    Log.v(LOG_TAG, "Initializing WebLogicRestAdapter: host=" + host + " port=" + port + " user=" + username);
    Log.v(LOG_TAG, "Initializing WebLogicRestAdapter: Base URL: " + baseURL);
  }

  @Override
  public <T extends WebLogicEntity> List<T> getResourcesList(Class<T> theClass)
  {

    Log.v(LOG_TAG, "Getting Resource Summary for class: " + theClass);
    String url = getSummaryURL(theClass.getSimpleName().toLowerCase());
    Log.v(LOG_TAG, "Getting Resource Summary from URL: " + url);

    List<T> returnMe = new ArrayList<T>();

    String json = httpAdapter.GET(url, username, password, "json", null);

    JSONObject jsonMessage = null;
    ObjectMapper om = new ObjectMapper();

    try
    {
      jsonMessage = new JSONObject(json);

      Log.v(LOG_TAG, "Class: " + theClass + " JSON: " + jsonMessage.toString(2));

      JSONArray array = jsonMessage.getJSONObject("body").getJSONArray("items");

      for (int i = 0; i < array.length(); i++)
      {
        T theType = om.readValue(array.getString(i), theClass);
        theType.setOriginalJSON(jsonMessage.toString(2));
        returnMe.add(theType);
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
  public <T extends WebLogicEntity> T getResource(Class<T> theClass, String pName)
  {

    String url = getResourceURL(theClass.getSimpleName(), pName);
    Log.v(LOG_TAG, "Getting Resource from URL: " + url);

    String json = httpAdapter.GET(url, username, password, "json", null);
    JSONObject jsonMessage = null;

    T theType = null;

    try
    {
      jsonMessage = new JSONObject(json);
      Log.v(LOG_TAG, "Class: " + theClass + " JSON: " + jsonMessage.toString(2));

      String tmpStr = jsonMessage.getJSONObject("body").getString("item");

      ObjectMapper om = new ObjectMapper();
      theType = om.readValue(tmpStr, theClass);
      theType.setOriginalJSON(jsonMessage.toString(2));
    } catch (JSONException e)
    {
      e.printStackTrace();
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    return theType;
  }

  private String getSummaryURL(String pEntityName)
  {

    Log.v(LOG_TAG, "getSummaryURL base=[" + pEntityName + "] entityName=[:" + pEntityName + "]");

    StringBuilder url = new StringBuilder(baseURL).append('/').append(pEntityName);

    if (!pEntityName.endsWith("s"))
    {
      url.append('s');
    }

    if (fullQuery)
    {
      url.append("?full");
    }

    return url.toString();
  }

  private String getResourceURL(String pEntityName, String pResourceName)
  {

    Log.v(LOG_TAG, "getResourceURL base=[" + pEntityName
        + "] entityName=[:" + pEntityName
        + "] + resourceName=[" + pResourceName
        + "]");

    StringBuilder url = new StringBuilder(baseURL).append('/').
        append(pEntityName.toLowerCase()).append('s')
        .append('/').append(pResourceName);

    if (fullQuery)
    {
      url.append("?format=full");
    }

    return url.toString();
  }
}
