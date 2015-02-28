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

import com.jeffreyawest.weblogic.entity.Application;
import com.jeffreyawest.weblogic.monitor.R;

import java.util.List;

/**
 * Created by jeffreyawest on 8/15/13.
 */
public class ApplicationListAdapter extends ArrayAdapter<Application>
{
  private static final String LOG_TAG = "ApplicationListAdapter";

  private final Context context;
  private final List<Application> list;

  public ApplicationListAdapter(Context context, int resource, List<Application> list)
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

    View rowView = inflater.inflate(R.layout.list_item_application, parent, false);

    if (rowView != null)
    {

      TextView textView = (TextView) rowView.findViewById(R.id.entity_name);

      Application app = list.get(position);

      if (textView != null)
      {
        textView.setText(app.getName());
      }

      if (app.getTargetStates() != null
          && app.getTargetStates().size() > 0)
      {

        TextView stateText = (TextView) rowView.findViewById(R.id.state_text);
        if (stateText != null)
          stateText.setText(app.getState().toString());

        View stateGraphic = rowView.findViewById(R.id.state_graphic);
        if (stateGraphic != null)
        {
          int color = context.getResources().getColor(app.getState().getColorID());
          stateGraphic.setBackgroundColor(color);
        }

        TextView healthText = (TextView) rowView.findViewById(R.id.health_text);
        if (healthText != null)
          healthText.setText(app.getHealth().toString());

        View healthGraphic = rowView.findViewById(R.id.health_graphic);
        if (healthGraphic != null)
        {
          int color = context.getResources().getColor(app.getHealth().getColorID());
          healthGraphic.setBackgroundColor(color);
        }
      }
    }

    return rowView;
  }
}
