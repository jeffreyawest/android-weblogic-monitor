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

package com.jeffreyawest.weblogic.monitor.activity.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jeffreyawest.weblogic.monitor.R;

import java.util.List;

/**
 * Created by jeffreyawest on 8/20/13.
 */
public class DomainEntityListAdapter extends ArrayAdapter<String>
{
  private static final String LOG_TAG = "ServerListAdapter";
  private final Context context;
  private final List<String> list;

  public DomainEntityListAdapter(Context context, int resource, List<String> list)
  {

    super(context, resource, list);

    this.context = context;
    this.list = list;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {

    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View rowView = inflater.inflate(R.layout.list_item_domain_entity, parent, false);

    if (rowView != null)
    {
      TextView textView = null;
      textView = (TextView) rowView.findViewById(R.id.entity_name);

      String entity = list.get(position);

      if (textView != null)
      {
        textView.setText(entity);
      }
    }

    return rowView;
  }
}