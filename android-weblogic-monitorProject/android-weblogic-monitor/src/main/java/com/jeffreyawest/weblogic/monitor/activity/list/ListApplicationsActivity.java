package com.jeffreyawest.weblogic.monitor.activity.list;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Application;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.activity.list.adapter.ApplicationListAdapter;

public class ListApplicationsActivity
    extends ListEntityActivity<Application>
{

  private static final String LOG_TAG = "ListApplicationsActivity";

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);

    arrayAdapter = new ApplicationListAdapter(this, R.layout.list_item_application, entities);
    setListAdapter(arrayAdapter);

    TextView tv = (TextView) findViewById(R.id.header);
    if (tv != null)
      tv.setText("Applications:");
  }

  @Override
  public Class<Application> getEntityClass()
  {

    return Application.class;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.list_servers, menu);
    return true;
  }
}
