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

package com.jeffreyawest.weblogic.monitor.activity.list;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeffreyawest.weblogic.monitor.Constants;
import com.jeffreyawest.weblogic.monitor.R;

public class ListDomainEntitiesActivity
    extends ListActivity
{

  private static final String LOG_TAG = "ListDomainEntitiesActivity";

  private static final String[] listValues = {
      "Servers",
      "Clusters",
      "Datasources",
      "Applications"};

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_domain_entities);

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                                                                 R.layout.list_item_domain_entity,
                                                                 R.id.entity_name,
                                                                 listValues);

    setListAdapter(arrayAdapter);
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id)
  {

    super.onListItemClick(l, v, position, id);

    TextView tv = (TextView) v.findViewById(R.id.entity_name);
    String entityType = tv.getText().toString();

    Log.v(LOG_TAG, "onListItemClick: view=" + v + " label=[" + tv.getText().toString() + "]");

    String className = "com.jeffreyawest.weblogic.monitor.activity.list.List" + entityType + "Activity";

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
}
