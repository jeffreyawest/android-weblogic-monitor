package com.jeffreyawest.weblogic.monitor.activity.list;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Cluster;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.activity.list.adapter.ClusterListAdapter;

public class ListClustersFragment
    extends ListEntityActivity<Cluster>
{

  private static final String LOG_TAG = "ListClustersFragment";

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);

    arrayAdapter = new ClusterListAdapter(this, R.layout.list_item_cluster, entities);
    setListAdapter(arrayAdapter);

    TextView tv = (TextView) findViewById(R.id.header);
    if (tv != null)
      tv.setText("Clusters:");
  }

  @Override
  public Class<Cluster> getEntityClass()
  {

    return Cluster.class;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.list_servers, menu);
    return true;
  }
}
