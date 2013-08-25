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

package com.jeffreyawest.weblogic.monitor.activity.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jeffreyawest.weblogic.entity.Cluster;
import com.jeffreyawest.weblogic.entity.ClusterServer;
import com.jeffreyawest.weblogic.entity.enums.ServerHealth;
import com.jeffreyawest.weblogic.entity.enums.ServerState;
import com.jeffreyawest.weblogic.monitor.R;

import java.util.List;

/**
 * Created by jeffreyawest on 8/15/13.
 */
public class ClusterListAdapter extends ArrayAdapter<Cluster>
{

  private static final String LOG_TAG = "ClusterListAdapter";
  private final Context context;
  private final List<Cluster> list;

  public ClusterListAdapter(Context context, int resource, List<Cluster> list)
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

    View rowView = inflater.inflate(R.layout.list_item_cluster, parent, false);

    if (rowView != null)
    {

      TextView textView = (TextView) rowView.findViewById(R.id.entity_name);

      Cluster cluster = list.get(position);

      if (textView != null)
      {
        textView.setText(cluster.getName());
      }

      if (cluster.getServers() != null
          && cluster.getServers().size() > 0)
      {

        ServerHealth maxHealth = ServerHealth.HEALTH_OK;
        ServerState maxState = ServerState.RUNNING;

        for (ClusterServer server : cluster.getServers())
        {

          if (server.getState() != null
              && maxState.getIntValue() < server.getState().getIntValue())
          {

            maxState = server.getState();
          }

          if (server.getHealth() != null
              && maxHealth.getIntValue() < server.getHealth().getIntValue())
          {

            maxHealth = server.getHealth();
          }
        }

        TextView stateText = (TextView) rowView.findViewById(R.id.state_text);
        if (stateText != null)
          stateText.setText(maxState.toString());

        View stateGraphic = rowView.findViewById(R.id.state_graphic);
        if (stateGraphic != null)
        {
          int color = context.getResources().getColor(maxState.getColorID());
          stateGraphic.setBackgroundColor(color);
        }

        TextView healthText = (TextView) rowView.findViewById(R.id.health_text);
        if (healthText != null)
          healthText.setText(maxHealth.toString());

        View healthGraphic = rowView.findViewById(R.id.health_graphic);
        if (healthGraphic != null)
        {
          int color = context.getResources().getColor(maxHealth.getColorID());
          healthGraphic.setBackgroundColor(color);
        }
      }
    }

    return rowView;
  }
}