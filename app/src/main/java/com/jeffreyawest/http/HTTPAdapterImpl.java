/*
 * *************************************************************************
 *
 * Copyright (c) 2013 - Jeffrey A. West Designs
 *
 * This code is provided for example purposes only.  Neither Oracle nor
 * Jeffrey A. West assume any responsibility or liability for the consequences
 *  of using this code. If you choose to use this code for any reason,
 * including but not limited to its use as an example you do so at your own
 * risk and without the support of Oracle.
 *
 * This code is provided under the following licenses:
 *  - GNU General Public License (GPL-2.0)
 *
 * **************************************************************************
 */

package com.jeffreyawest.http;

import android.util.Base64;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**

 * Created by jeffreyawest  on 8/9/13.
 */
public class HTTPAdapterImpl implements HTTPAdapter
{

  private static final String LOG_TAG = HTTPAdapterImpl.class.getName();

  @Override
  public String GET(String pURL,
                    String pUsername,
                    String pPassword,
                    String pAccept,
                    HashMap<String, String> pAdditionalHeaders)
  {

    Log.v(LOG_TAG, "GET()");

    // Making HTTP request
    HttpGet httpGet = new HttpGet(pURL);
    httpGet.setHeader(ACCEPT_HEADER_KEY, "application/json");

    if (pUsername != null || pPassword != null)
    {
      String authorizationString = "Basic "
          + Base64.encodeToString((pUsername + ":" + pPassword).getBytes(), Base64.NO_WRAP);
      httpGet.setHeader("Authorization", authorizationString);
      Log.v(LOG_TAG, "Setting AUTH header: " + authorizationString);
    }

    String result = null;

    try
    {
      result = doHTTPMethod(httpGet);
    } catch (Exception e)
    {
      e.printStackTrace();
    }

    return result;
  }

  private static String doHTTPMethod(HttpRequestBase pRequest) throws Exception
  {

    InputStream is = null;
    StringBuilder sb = new StringBuilder();
    DefaultHttpClient httpClient = new DefaultHttpClient();

    Log.v(LOG_TAG, "doHTTPMethod()");

    try
    {
      HttpResponse httpResponse = httpClient.execute(pRequest);
      Log.v("HTTPAdapter.doHTTPMethod", "HTTP Status:" + httpResponse.getStatusLine());
      if (httpResponse.getStatusLine().getStatusCode() != 200)
      {
        throw new Exception("HTTP Error: " + httpResponse.getStatusLine());
      }
      HttpEntity httpEntity = httpResponse.getEntity();
      is = httpEntity.getContent();
    } catch (Exception e)
    {
      throw e;
    }

    try
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);

      String line = null;
      while ((line = reader.readLine()) != null)
      {
        sb.append(line).append("\n");
      }
      is.close();
    } catch (Exception e)
    {
      Log.e("Buffer Error", "Error converting result " + e.toString());
    }
    return sb.toString();
  }
}
