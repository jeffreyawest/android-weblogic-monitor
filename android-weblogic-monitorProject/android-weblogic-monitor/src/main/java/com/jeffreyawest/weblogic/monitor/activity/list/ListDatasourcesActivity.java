package com.jeffreyawest.weblogic.monitor.activity.list;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Datasource;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.activity.list.adapter.DatasourceListAdapter;

public class ListDatasourcesActivity
    extends ListEntityActivity<Datasource>
{

  private static final String LOG_TAG = "ListServersActivity";

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);

    arrayAdapter = new DatasourceListAdapter(this, R.layout.list_item_datasource, entities);
    setListAdapter(arrayAdapter);

    TextView tv = (TextView) findViewById(R.id.header);
    if (tv != null)
      tv.setText("Datasources:");
  }

  @Override
  public Class<Datasource> getEntityClass()
  {

    return Datasource.class;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.list_servers, menu);
    return true;
  }
}
