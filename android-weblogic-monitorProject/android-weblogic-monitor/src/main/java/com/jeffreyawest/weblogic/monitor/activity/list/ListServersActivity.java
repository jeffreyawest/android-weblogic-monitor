package com.jeffreyawest.weblogic.monitor.activity.list;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Server;
import com.jeffreyawest.weblogic.monitor.R;
import com.jeffreyawest.weblogic.monitor.activity.list.adapter.ServerListAdapter;

public class ListServersActivity
    extends ListEntityActivity<Server>
{

  private static final String LOG_TAG = "ListServersActivity";

  @Override
  public void onCreate(Bundle savedInstanceState)
  {

    super.onCreate(savedInstanceState);

    arrayAdapter = new ServerListAdapter(this, R.layout.list_item_server, entities);
    setListAdapter(arrayAdapter);

    TextView tv = (TextView) this.findViewById(R.id.header);
    if (tv != null)
    {
      tv.setText("Servers:");
    }
  }

  @Override
  public Class<Server> getEntityClass()
  {

    return Server.class;
  }
}
