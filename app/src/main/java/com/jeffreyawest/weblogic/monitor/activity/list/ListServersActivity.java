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

  }

  @Override
  public Class<Server> getEntityClass()
  {

    return Server.class;
  }
}
