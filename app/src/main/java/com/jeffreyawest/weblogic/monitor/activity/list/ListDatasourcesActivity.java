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
