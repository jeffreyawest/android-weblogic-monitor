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

package com.jeffreyawest.weblogic.monitor.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.jeffreyawest.weblogic.entity.WebLogicEntity;
import com.jeffreyawest.weblogic.monitor.activity.display.DisplayEntityActivity;
import com.jeffreyawest.weblogic.rest.WebLogicHTTPRestAdapter;

/**
 * Created by jeffreyawest on 8/13/13.
 */
public final class RetrieveWebLogicEntityTask<T extends WebLogicEntity> extends AsyncTask<String, Integer, T>
{

  private static String LOG_TAG = "RetrieveWebLogicEntityTask";

  private ProgressDialog progressDialog;
  private Class<T> theClass;
  private DisplayEntityActivity<T> activity;

  public RetrieveWebLogicEntityTask(DisplayEntityActivity<T> activity, Class<T> theClass)
  {

    this.activity = activity;
    this.theClass = theClass;
    progressDialog = new ProgressDialog(activity);
    String message = "Retrieving Detail for " + theClass.getSimpleName() + "...";

    progressDialog.setMessage(message);
    progressDialog.setCancelable(true);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    progressDialog.setMax(100);
    progressDialog.setProgress(0);
    progressDialog.show();
  }

  @Override
  protected T doInBackground(String... strings)
  {

    String entityName = strings[0];
    String message = "Retrieving Detail for " + theClass.getSimpleName() + "name: " + entityName + "...";

    publishProgress(50);
    Log.v(LOG_TAG, "doInBackground: " + message);
    return WebLogicHTTPRestAdapter.getInstance().getResource(theClass, entityName);
  }

  protected void onProgressUpdate(Integer... progress)
  {

    progressDialog.setProgress(progress[0]);
  }

  protected void onPostExecute(T result)
  {

    publishProgress(100);
    Log.v(LOG_TAG, "onPostExecute: Updated " + theClass.getSimpleName() + ": " + result.getName());
    activity.updateDisplay(result);
    progressDialog.dismiss();
  }
}