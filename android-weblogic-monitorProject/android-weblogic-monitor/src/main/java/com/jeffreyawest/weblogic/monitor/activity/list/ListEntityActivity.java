package com.jeffreyawest.weblogic.monitor.activity.list;

/*
 * **************************************************************************
 * This code is provided for example purposes only.  Neither Oracle nor Jeffrey
 * A. West assume any responsibility or liability for the consequences of using
 * this code.
 * <p/>
 * If you choose to use this code for any reason, including but not limited
 * to its use as an example you do so at your own risk and without the support
 * of Oracle.
 * <p/>
 * This code is provided under the following licenses:
 * <p/>
 * GNU General Public License (GPL-2.0)
 * <p/>
 * ****************************************************************************
 * <p/>
 * Created by jeffreyawest
 */

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.WebLogicEntity;
import com.jeffreyawest.weblogic.monitor.Constants;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.rest.WebLogicHTTPRestAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class ListEntityActivity<T extends WebLogicEntity>
    extends ListActivity
{

  private static final String LOG_TAG = "ListEntityActivity<T>";

  protected List<T> entities;
  protected ArrayAdapter<T> arrayAdapter;

  @Override
  public void onListItemClick(ListView l, View v, int position, long id)
  {

    super.onListItemClick(l, v, position, id);

    TextView tv = (TextView) v.findViewById(R.id.entity_name);

    Log.v(LOG_TAG, "onListItemClick: view=" + v + " label=[" + tv.getText().toString() + "]");

    String className = "com.jeffreyawest.weblogic.monitor.activity.display.Display" + getEntityClass().getSimpleName() + "Activity";

    try
    {
      Intent intent = new Intent(this, Class.forName(className));
      intent.putExtra(Constants.ENTITY_NAME, tv.getText().toString());
      startActivity(intent);
    } catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  protected abstract Class<T> getEntityClass();

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);

    entities = new ArrayList<T>(17);

    Log.v(LOG_TAG, "OnCreate - entityClass=" + getEntityClass());

    try
    {
      Class.forName("android.os.AsyncTask");
    } catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }

    new RetrieveEntitySummaryTask<T>(ListEntityActivity.this).execute(getEntityClass());
  }

  public final void updateDisplay(List<T> list)
  {

    Log.v(LOG_TAG, "updateDisplay: " + list.size() + " elements:" + list.size());

    entities.clear();
    entities.addAll(list);
    arrayAdapter.notifyDataSetChanged();
  }

  protected class RetrieveEntitySummaryTask<T extends WebLogicEntity> extends AsyncTask<Class<T>, Integer, List<T>>
  {

    public static final String LOG_TAG = "RetrieveEntitySummaryTask";
    private ProgressDialog progressDialog;
    private Class<T> theClass;
    private ListEntityActivity<T> activity;

    public RetrieveEntitySummaryTask(ListEntityActivity<T> activity)
    {

      this.activity = activity;
      theClass = activity.getEntityClass();

      progressDialog = new ProgressDialog(ListEntityActivity.this);
      progressDialog.setCancelable(false);
      progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
      progressDialog.setMax(100);
      progressDialog.setProgress(0);
      progressDialog.show();

      String message = "Retrieving " + theClass.getSimpleName() + "s...";
      progressDialog.setMessage(message);
    }

    @Override
    protected List<T> doInBackground(Class<T>... classes)
    {

      Log.v(LOG_TAG, "doInBackground: Updating " + classes[0] + "...");

      theClass = classes[0];
      publishProgress(50);

      return WebLogicHTTPRestAdapter.getInstance().getResourcesList(classes[0]);
    }

    @Override
    protected void onProgressUpdate(Integer... progress)
    {

      progressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(List<T> ts)
    {

      super.onPostExecute(ts);
      publishProgress(100);
      Log.v(LOG_TAG, "onPostExecute: Result: " + ts.toString());
      activity.updateDisplay(ts);
      progressDialog.hide();
    }
  }
}
